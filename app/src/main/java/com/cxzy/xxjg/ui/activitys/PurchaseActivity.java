package com.cxzy.xxjg.ui.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.ui.adapter.PurchaseAdapter;
import com.cxzy.xxjg.ui.test.contract.IPurchaseActivityContract;
import com.cxzy.xxjg.ui.test.presenter.PurchaseActivityPresenterImpl;
import com.cxzy.xxjg.utils.ScreenUtils;
import com.cxzy.xxjg.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 食材采购
 */
public class PurchaseActivity extends BaseActivity<PurchaseActivityPresenterImpl> implements IPurchaseActivityContract.View, PurchaseAdapter.RecyclerViewItemClickListener {

    @BindView(R.id.rv_add_pic)
    RecyclerView rvAddPic;
    private List<String> picList = new ArrayList<>();
    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;
    private PurchaseAdapter mAdapter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_purchase;
    }

    @Override
    public void initInjector(AppComponent appComponent) {
        DaggerHttpComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        rvAddPic.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new PurchaseAdapter(picList);
        rvAddPic.setAdapter(mAdapter);
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void onItemClick(int position) {
        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        } else {
            ScreenUtils.initScreen(this);
            Intent intent = new Intent(mContext, PhotoWallActivity.class);
//            intent.putExtra("number", picList.size());
            startActivityForResult(intent, Constants.FLAG_CHOOSE_IMG);
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
                    ScreenUtils.initScreen(this);
                    Intent picintent = new Intent(mContext, PhotoWallActivity.class);
//                    picintent.putExtra("number", picList.size());
                    startActivityForResult(picintent, Constants.FLAG_CHOOSE_IMG);
                } else {
                    //用户授权拒绝之后，友情提示一下就可以了
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        ToastUtil.showShort(mContext, "权限已拒绝请到设置页面手动开启");
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        picList = mPresenter.dealPicResult(this, requestCode, resultCode, data);
    }

    @Override
    public void refreshPicAdapter() {
        mAdapter.setDatas(picList);
        mAdapter.notifyDataSetChanged();
    }
}
