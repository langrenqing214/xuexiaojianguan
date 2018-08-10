package com.cxzy.xxjg.ui.activitys;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.PicBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.ui.adapter.PhotoWallAdapter;
import com.cxzy.xxjg.utils.BitmapUtil;
import com.cxzy.xxjg.utils.ScreenUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.blankj.utilcode.utils.ImageUtils.isImage;

/**
 * 照片墙 选择照片页面
 */
public class PhotoWallActivity extends BaseActivity implements PhotoWallAdapter.OnCameraListener {

    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;
    private String picName;//图片名字
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<PicBean> picList = new ArrayList<PicBean>();
    private GridView mPhotoWall;
    private PhotoWallAdapter adapter;

    /**
     * 当前文件夹路径
     */
    private String currentFolder = null;
    /**
     * 当前展示的是否为最近照片
     */
    private boolean isLatest = true;

    private int number;

    private String recentlyPic;//最近照片
    private int recentLyNum;//最近照片数量

    private boolean isRadio;//是否单选(true为单选)

    @BindView(R.id.topbar_title_tv)
    TextView titleTV;
    @BindView(R.id.topbar_left_btn)
    Button backBtn;
    @BindView(R.id.topbar_left_iv)
    ImageView ivLeft;
    @BindView(R.id.topbar_right_btn)
    Button confirmBtn;
    @BindView(R.id.photo_wall_ok)
    Button okBtn;
    private int maxNumber;

    @Override
    public int getContentLayout() {
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        return R.layout.activity_photo_wall;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        titleTV.setText(R.string.latest_image);
        //显示返回箭头
        ivLeft.setVisibility(View.VISIBLE);
        backBtn.setText(R.string.photo_album);

    }

    @Override
    public void initData() {
        number = getIntent().getIntExtra("number", 0);
        maxNumber = getIntent().getIntExtra("maxNumber", 5);
        isRadio = getIntent().getBooleanExtra("isRadio", false);
        if (isRadio) {
            number = 0;
        }


        if (!isRadio && number != 0) {
            okBtn.setText("下一步（" + number + "）");
        } else {
            okBtn.setText("下一步");
        }
        backBtn.setVisibility(View.VISIBLE);
        confirmBtn.setText("取消");
        confirmBtn.setVisibility(View.VISIBLE);

        mPhotoWall = (GridView) findViewById(R.id.photo_wall_grid);
        list = getLatestImagePaths(40);
        if (list != null) {
            for (String s : list) {
                PicBean p = new PicBean();
                p.path = s;
                picList.add(p);
            }
        }
        adapter = new PhotoWallAdapter(this, picList, number,maxNumber , this);
        mPhotoWall.setAdapter(adapter);

        //选择照片取消
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选择图片完成,回到起始页面
                ArrayList<String> paths = getSelectImagePaths();
                Intent intent = new Intent();
                intent.putExtra("code", paths != null ? 100 : 101);
                intent.putStringArrayListExtra("paths", paths);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //点击返回，回到选择相册页面
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAction();
            }
        });

    }

    @Override
    public void refreshView(Object mData) {

    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }

    /**
     * 第一次跳转至相册页面时，传递最新照片信息
     */
    private boolean firstIn = true;

    /**
     * 点击返回时，跳转至相册页面
     */
    private void backAction() {
        Intent intent = new Intent(this, PhotoAlbumActivity.class);
        //传递“最近照片”分类信息
        if (firstIn && list != null && list.size() > 0) {
            recentlyPic = list.get(0);
            recentLyNum = list.size();
            firstIn = false;
        }
        if (list != null && list.size() > 0) {
            intent.putExtra("latest_count", recentLyNum);
            intent.putExtra("latest_first_img", recentlyPic);
        }
        startActivityForResult(intent, 1);
    }


    /**
     * 根据图片所属文件夹路径，刷新页面
     */
    private void updateView(int code, String folderPath) {
        list.clear();
        picList.clear();
        adapter.clearSelectionMap();
        adapter.notifyDataSetChanged();

        if (code == 100) {   //某个相册
            int lastSeparator = folderPath.lastIndexOf(File.separator);
            String folderName = folderPath.substring(lastSeparator + 1);
            titleTV.setText(folderName);
            List<String> tempList = getAllImagePathsByFolder(folderPath);
            if (tempList == null) {
                return;
            }
            list.addAll(tempList);
            for (String s : list) {
                PicBean p = new PicBean();
                p.path = s;
                picList.add(p);
            }
        } else if (code == 200) {  //最近照片
            titleTV.setText(R.string.latest_image);
            list.addAll(getLatestImagePaths(40));
            for (String s : list) {
                PicBean p = new PicBean();
                p.path = s;
                picList.add(p);
            }
        }

        adapter.notifyDataSetChanged();
        if (list.size() > 0) {
            //滚动至顶部
            mPhotoWall.smoothScrollToPosition(0);
        }
    }


    /**
     * 获取指定路径下的所有图片文件。
     */
    private ArrayList<String> getAllImagePathsByFolder(String folderPath) {
        File folder = new File(folderPath);
        String[] allFileNames = folder.list();
        if (allFileNames == null || allFileNames.length == 0) {
            return null;
        }

        ArrayList<String> imageFilePaths = new ArrayList<String>();
        for (int i = allFileNames.length - 1; i >= 0; i--) {
            if (isImage(allFileNames[i])) {
                imageFilePaths.add(folderPath + File.separator + allFileNames[i]);
            }
        }

        return imageFilePaths;
    }

    /**
     * 使用ContentProvider读取SD卡最近图片。
     */
    private ArrayList<String> getLatestImagePaths(int maxCount) {
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String key_MIME_TYPE = MediaStore.Images.Media.MIME_TYPE;
        String key_DATA = MediaStore.Images.Media.DATA;

        ContentResolver mContentResolver = getContentResolver();

        // 只查询jpg和png的图片,按最新修改排序
        Cursor cursor = mContentResolver.query(mImageUri, new String[]{key_DATA},
                key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=?",
                new String[]{"image/jpg", "image/jpeg", "image/png"},
                MediaStore.Images.Media.DATE_MODIFIED);

        ArrayList<String> latestImagePaths = null;
        if (cursor != null) {
            //从最新的图片开始读取.
            //当cursor中没有数据时，cursor.moveToLast()将返回false
            if (cursor.moveToLast()) {
                latestImagePaths = new ArrayList<String>();

                while (true) {
                    // 获取图片的路径
                    String path = cursor.getString(0);
                    latestImagePaths.add(path);

                    if (latestImagePaths.size() >= maxCount || !cursor.moveToPrevious()) {
                        break;
                    }
                }
            }
            cursor.close();
        }

        return latestImagePaths;
    }

    //获取已选择的图片路径
    private ArrayList<String> getSelectImagePaths() {
        SparseBooleanArray map = adapter.getSelectionMap();
        if (map.size() == 0) {
            return null;
        }

        ArrayList<String> selectedImageList = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            if (map.get(i)) {
                selectedImageList.add(list.get(i));
            }
        }

        return selectedImageList;
    }


    //从相册页面回调至此页
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        int code = data.getIntExtra("code", -1);
                        if (code == 100) {
                            //某个相册
                            String folderPath = data.getStringExtra("folderPath");
                            if (isLatest || (folderPath != null && !folderPath.equals(currentFolder))) {
                                currentFolder = folderPath;
                                updateView(100, currentFolder);
                                isLatest = false;
                                number = 0;
                                adapter.setNumber(0);
                                okBtn.setText("下一步");
                            }
                        } else if (code == 200) {
                            //“最近照片”
                            if (!isLatest) {
                                updateView(200, null);
                                isLatest = true;
                                number = 0;
                                adapter.setNumber(0);
                                okBtn.setText("下一步");
                            }
                        }

                    }
                }
                break;
            case Constants.FLAG_CHOOSE_CAMERA:
                //照相
                if (resultCode == RESULT_OK) {
//                    picNameList.add(picName);
                    // File.separator是分隔符 "/"
                    String string = MyApp.tempDir + File.separator + picName;// 定义图片路径
                    try {
                        Bitmap bm = BitmapUtil.getBitmapByPath(string, BitmapUtil.getOptions(string), ScreenUtils.getScreenW(), ScreenUtils.getScreenH());
                        BitmapUtil.saveMyBitmap(bm, string, string, ScreenUtils.getScreenW(), ScreenUtils.getScreenH());

//                        ByteArrayOutputStream baos=new ByteArrayOutputStream();
//                        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                        byte [] bitmapByte =baos.toByteArray();
                        Intent intent = new Intent();
                        intent.putExtra("camera", string);
//                        intent.putExtra("bitmap",bitmapByte);
                        setResult(RESULT_OK, intent);
                        finish();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    @Override
    public void camera() {
        //照相
        if (!hasPermission(Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        } else {
            picName = System.currentTimeMillis() + ".jpg";

            //拍照
            Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用快速拍照功能
            // 下面这句指定调用相机拍照后的照片存储的路径
            photoIntent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(MyApp.tempDir, picName)));
            startActivityForResult(photoIntent, Constants.FLAG_CHOOSE_CAMERA);// 取回跳转方法，这样才能获取到截取的图片
        }


    }

    @Override
    public void selectNum(boolean isAdd) {
        if (isAdd) {
            number++;
        } else {
            number--;
        }
        if (!isRadio) {
            okBtn.setText(number != 0 ? "下一步（" + number + "）" : "下一步");
        } else {
            okBtn.setText("下一步");
        }
    }


    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case MY_PERMISSIONS_READ_EXTERNAL_STORAGE:
                if (grantResults == null || grantResults.length == 0) {
                    return;
                }
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {
                    //授权成功之后，调用系统相机进行拍照操作等
                    picName = System.currentTimeMillis() + ".jpg";

                    //拍照
                    Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用快速拍照功能
                    // 下面这句指定调用相机拍照后的照片存储的路径
                    photoIntent.putExtra(
                            MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(MyApp.tempDir, picName)));
                    startActivityForResult(photoIntent, Constants.FLAG_CHOOSE_CAMERA);// 取回跳转方法，这样才能获取到截取的图片
                } else {
                    //用户授权拒绝之后，友情提示一下就可以了
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        Toast.makeText(mContext, "权限已拒绝请到设置页面手动开启", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
}
