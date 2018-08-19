package com.cxzy.xxjg.ui.activitys;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.ResultItemBean;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.bean.TrialListBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectFoodStyleDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.dialog.SelectTrialReactionDialog;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.ui.test.contract.IAddTrialContract;
import com.cxzy.xxjg.ui.test.presenter.AddTrialPresenterImpl;
import com.cxzy.xxjg.utils.DateUtil;
import com.cxzy.xxjg.utils.NetUtil;
import com.cxzy.xxjg.utils.ScreenUtils;
import com.cxzy.xxjg.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 添加试吃
 */
public class AddTrialActivity extends BaseActivity<AddTrialPresenterImpl> implements IAddTrialContract.View , SelectCanteenDialog.SelectCanteenItemListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, SelectTrialReactionDialog.SelectTrialListener, SelectFoodStyleDialog.SelectFoodStyleListener {

    @BindView(R.id.tv_select_canteen)
    TextView tvSelectCanteen;
    @BindView(R.id.et_put_food_name)
    EditText etFoodName;
    @BindView(R.id.et_trial_person)
    EditText etTrialPerson;
    @BindView(R.id.tv_trial_time)
    TextView tvTrialTime;
    @BindView(R.id.et_trial_time_interval)
    TextView etTimeInterval;
    @BindView(R.id.tv_select_trial_reaction)
    TextView tvSelectTrialReaction;
    @BindView(R.id.et_trial_des)
    EditText etTrialDes;
    @BindView(R.id.iv_add_trial_pic)
    ImageView ivAddTrialPic;
    @BindView(R.id.tv_reaction_time)
    TextView tvReactionTime ;
    private ArrayList<SchoolCanteenBean> dataList;
    private String createDateStart = "";
    private String canteenId = "";
    private String canteenName ;
    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;
    private File file;
    private String trialState = "";//试吃反应
    private ArrayList<ResultItemBean> timeList;
    private String timeId;
    private int timeType = 0 ; //0为试吃时间 1为反应时间
    private String reactionTime;

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_trial;
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
        dataList = (ArrayList<SchoolCanteenBean>) getIntent().getSerializableExtra("canteenList");
        timeList = (ArrayList<ResultItemBean>) getIntent().getSerializableExtra("REACTION_INTERVAL");
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        ToastUtil.showShort(this, "添加试吃成功");
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.btn_add_trial, R.id.tv_select_canteen, R.id.tv_trial_time, R.id.iv_add_trial_pic, R.id.tv_select_trial_reaction ,
            R.id.et_trial_time_interval , R.id.tv_reaction_time})
    @Override
    public void onViewClicked(View view) {

        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.tv_select_canteen://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this, dataList, this);
                canteenDialog.show();
                break;
            case R.id.tv_reaction_time ://选择反应时间
                timeType = 1 ;
                SelectTimeDialog reactionTimeDialog = new SelectTimeDialog(this, this);
                reactionTimeDialog.show();
                break;
            case R.id.tv_trial_time://选择时间
                timeType = 0 ;
                SelectTimeDialog timeDialog = new SelectTimeDialog(this, this);
                timeDialog.show();
                break;
            case R.id.iv_add_trial_pic://拍照
                if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE) || !hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
                } else {
                    ScreenUtils.initScreen(this);
                    Intent intent = new Intent(mContext, PhotoWallActivity.class);
                    intent.putExtra("isRadio", true);
                    intent.putExtra("maxNumber", 1);
                    startActivityForResult(intent, Constants.FLAG_CHOOSE_IMG);
                }
                break;
            case R.id.tv_select_trial_reaction://选择试吃反应
                List<String> trialList = new ArrayList<>();
                trialList.add("未出结果");
                trialList.add("正常");
                trialList.add("异常");
                SelectTrialReactionDialog dialog = new SelectTrialReactionDialog(this , trialList , this);
                dialog.show();
                break;
            case R.id.btn_add_trial://添加试吃
                String foodName = etFoodName.getText().toString().trim();
                String trialPerson = etTrialPerson.getText().toString().trim();
                String timeInterval = etTimeInterval.getText().toString().trim();
                String trialDes = etTrialDes.getText().toString().trim();
                Map<String , RequestBody> param = new HashMap<>();
                param.put("canteenId", RequestBody.create(MediaType.parse("form-data"),canteenId));
                param.put("foodName", RequestBody.create(MediaType.parse("form-data"),foodName));
                param.put("eatPerson", RequestBody.create(MediaType.parse("form-data"),trialPerson));
                param.put("eatTime", RequestBody.create(MediaType.parse("form-data"),createDateStart));
                param.put("internalTime", RequestBody.create(MediaType.parse("form-data"),timeId));
                param.put("status", RequestBody.create(MediaType.parse("form-data"),trialState));
                param.put("remarks", RequestBody.create(MediaType.parse("form-data"),trialDes));
                param.put("statusTime", RequestBody.create(MediaType.parse("form-data"),reactionTime));
//                param.put("eatImage", file);
                param.put("eatImage\"; filename=\"" + file.getName() , RequestBody.create(MediaType.parse("form-data"), file) );
                mPresenter.saveTrial(param);
                break;
            case R.id.et_trial_time_interval ://选择间隔时间
                SelectFoodStyleDialog styleDialog = new SelectFoodStyleDialog(this , timeList , this);
                styleDialog.show();
                break;
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
                    startActivityForResult(picintent, Constants.FLAG_CHOOSE_IMG);
                } else {
                    //用户授权拒绝之后，友情提示一下就可以了
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) || !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        ToastUtil.showShort(mContext, "权限已拒绝请到设置页面手动开启");
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        file = mPresenter.getPicUrl(requestCode, resultCode, data, ivAddTrialPic);
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId;
        this.canteenName = canteenName;
        tvSelectCanteen.setText(canteenName);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR, year);
        startcal.set(Calendar.MONTH, month);
        startcal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (timeType == 0) {
            createDateStart = DateUtil.date2NYR(startcal.getTime());
        }else {
            reactionTime = DateUtil.date2NYR(startcal.getTime());
        }
        TimePickerDialog dialog = new TimePickerDialog(AddTrialActivity.this, TimePicker.AUTOFILL_TYPE_LIST, this, 8, 00, true);
        dialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.HOUR, hour);
        startcal.set(Calendar.MINUTE, minute);
        if (timeType == 0) {
            createDateStart = createDateStart + " " + new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(startcal.getTimeInMillis()));
            tvTrialTime.setText(createDateStart);
        }else {
            reactionTime = reactionTime + " " + new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(startcal.getTimeInMillis()));
            tvReactionTime.setText(reactionTime);
        }
    }

    @Override
    public void selectTrialItem(int positon, String trial) {
        if ("正常".equals(trial)){
            trialState = "NORMAL";
        }else if ("异常".equals(trial)){
            trialState = "ERROR";
        }else {
            trialState = "INIT" ;
        }
        tvSelectTrialReaction.setText(trial);
    }

    @Override
    public void getPicFile(File file) {
        this.file = file ;
    }

    @Override
    public void selectFoodStyleItem(int positon, ResultItemBean info) {
        etTimeInterval.setText(info.name);
        timeId = info.key;
    }
}
