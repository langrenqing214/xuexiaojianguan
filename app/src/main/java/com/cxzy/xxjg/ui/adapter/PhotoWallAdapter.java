package com.cxzy.xxjg.ui.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ScreenUtils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.PicBean;
import com.cxzy.xxjg.utils.SDCardImageLoader;

import java.util.ArrayList;

/**
 * PhotoWall中GridView的适配器
 *
 * @author hanj
 */

public class PhotoWallAdapter extends BaseAdapter {
    private final int CAMERA_TYPE = 0;//照相
    private final int SELECT_TYPE = 1;//选图
    private final int TYPE_COUNT = 2;//布局数量

    private OnCameraListener listener;
    private Context context;
    private ArrayList<PicBean> imagePathList = new ArrayList<PicBean>();

    private SDCardImageLoader loader;

    //记录是否被选择
    private SparseBooleanArray selectionMap;

    private int number;

    private boolean isType = true;//标记

    public PhotoWallAdapter(Context context, ArrayList<PicBean> imagePathList, int number, OnCameraListener listener) {
        this.context = context;
        this.imagePathList = imagePathList;
        this.number = number;
        this.listener = listener;
        loader = new SDCardImageLoader(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
        selectionMap = new SparseBooleanArray();
    }

    public void setNumber(int num) {
        number = num;
    }


    /**
     * 该方法返回多少个不同的布局
     */
    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    /**
     * 根据position返回相应的Item
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                //第一项为照相机
                return CAMERA_TYPE;
            default:
                return SELECT_TYPE;
        }


    }

    @Override
    public int getCount() {
        return imagePathList == null ? 1 : imagePathList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position != 0) {
            return imagePathList.get(position - 1);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case CAMERA_TYPE:
                    CameraViewHolder cameraViewHolder = new CameraViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.photo_wall_item_one, null);
                    cameraViewHolder.imageView = (ImageView) convertView.findViewById(R.id.photo_wall_item_camera);
                    cameraViewHolder.type = CAMERA_TYPE;
                    holder = cameraViewHolder;
                    break;
                case SELECT_TYPE:
                    ImageViewHolder imageViewHolder = new ImageViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.photo_wall_item, null);
                    imageViewHolder.imageView = (ImageView) convertView.findViewById(R.id.photo_wall_item_photo);
                    imageViewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.photo_wall_item_cb);
                    imageViewHolder.type = SELECT_TYPE;
                    holder = imageViewHolder;
                    break;
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        switch (type) {
            case CAMERA_TYPE:
                CameraViewHolder cameraViewHolder = (CameraViewHolder) holder;
                cameraViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.camera();
                    }
                });
                break;
            case SELECT_TYPE:
                String filePath = imagePathList.get(position - 1).path;
                final ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (imageViewHolder.checkBox.isChecked()) {
                            imageViewHolder.checkBox.setChecked(false);

                        } else {
                            imageViewHolder.checkBox.setChecked(true);

                        }
                    }
                });

                final PicBean pic = imagePathList.get(position - 1);
                //tag的key必须使用id的方式定义以保证唯一，否则会出现IllegalArgumentException.
                imageViewHolder.checkBox.setTag(R.id.tag_first, position - 1);
                imageViewHolder.checkBox.setTag(R.id.tag_second, imageViewHolder.imageView);
                isType = false;
                imageViewHolder.checkBox.setChecked(pic.flag);
                isType = true;
                imageViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //图片最多选10张
                        if (isType) {
                            if (number >= 9 && isChecked) {
                                imageViewHolder.checkBox.setChecked(false);
//                                Toast.makeText(context, "最多只能选择10张图片", Toast.LENGTH_LONG).show();
                                Toast.makeText(context, "不能选择更多的图片了", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Integer position = (Integer) buttonView.getTag(R.id.tag_first);
                            ImageView image = (ImageView) buttonView.getTag(R.id.tag_second);

                            selectionMap.put(position, isChecked);
                            if (isChecked) {
                                pic.flag = true;
                                number++;
                                image.setColorFilter(context.getResources().getColor(R.color.image_checked_bg));
                                listener.selectNum(true);
                            } else {
                                pic.flag = false;
                                number--;
                                image.setColorFilter(null);
                                listener.selectNum(false);
                            }
                        } else {
                            ImageView image = (ImageView) buttonView.getTag(R.id.tag_second);
                            if (isChecked) {
//                                number++;
                                image.setColorFilter(context.getResources().getColor(R.color.image_checked_bg));
//                                listener.selectNum(true);
                            } else {
//                                number--;
                                image.setColorFilter(null);
//                                listener.selectNum(false);
                            }
                        }
                    }
                });

                imageViewHolder.checkBox.setChecked(selectionMap.get(position - 1));

                imageViewHolder.imageView.setTag(filePath);
                loader.loadImage(4, filePath, imageViewHolder.imageView);

                break;
        }

        return convertView;
    }

    private class ViewHolder {
        int type;
    }

    private class ImageViewHolder extends ViewHolder {
        ImageView imageView;
        CheckBox checkBox;
    }

    private class CameraViewHolder extends ViewHolder {
        ImageView imageView;
    }

    public SparseBooleanArray getSelectionMap() {
        return selectionMap;
    }

    public void clearSelectionMap() {
        selectionMap.clear();
    }


    public interface OnCameraListener {
        void camera();

        void selectNum(boolean isAdd);
    }
}
