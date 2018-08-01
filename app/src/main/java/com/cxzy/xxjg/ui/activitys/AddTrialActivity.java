package com.cxzy.xxjg.ui.activitys;

import android.app.DatePickerDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.ui.test.presenter.AddTrialPresenterImpl;
import com.cxzy.xxjg.utils.DateUtil;
import com.cxzy.xxjg.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加试吃
 */
public class AddTrialActivity extends BaseActivity<AddTrialPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.tv_select_canteen)
    TextView tvSelectCanteen;
    @BindView(R.id.et_put_food_name)
    EditText etFoodName;
    @BindView(R.id.et_trial_person)
    EditText etTrialPerson;
    @BindView(R.id.tv_trial_time)
    TextView tvTrialTime;
    @BindView(R.id.et_trial_time_interval)
    EditText etTimeInterval;
    @BindView(R.id.tv_select_trial_reaction)
    TextView tvSelectTrialReaction;
    @BindView(R.id.et_trial_des)
    EditText etTrialDes;
    @BindView(R.id.iv_add_trial_pic)
    ImageView ivAddTrialPic;
    private ArrayList<SchoolCanteenBean> dataList;
    private String createDateStart = "";
    private String canteenId = "";
    private String canteenName = "";

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
        setStatusBarColor(ContextCompat.getColor(mContext , R.color.main_style_color));
        dataList = (ArrayList<SchoolCanteenBean>) getIntent().getSerializableExtra("canteenList");

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        ToastUtil.showShort(this , "添加试吃成功");
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id , R.id.btn_add_trial , R.id.tv_select_canteen , R.id.tv_trial_time})
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
            case R.id.tv_trial_time://选择时间
                SelectTimeDialog timeDialog = new SelectTimeDialog(this, this);
                timeDialog.show();
                break;
            case R.id.btn_add_trial ://添加试吃
//                @BindView(R.id.tv_select_canteen)
//                TextView tvSelectCanteen;
//                @BindView(R.id.et_put_food_name)
//                EditText etFoodName;
//                @BindView(R.id.et_trial_person)
//                EditText etTrialPerson;
//                @BindView(R.id.tv_trial_time)
//                TextView tvTrialTime;
//                @BindView(R.id.et_trial_time_interval)
//                EditText etTimeInterval;
//                @BindView(R.id.tv_select_trial_reaction)
//                TextView tvSelectTrialReaction;
//                @BindView(R.id.et_trial_des)
//                EditText etTrialDes;
//                @BindView(R.id.iv_add_trial_pic)
//                ImageView ivAddTrialPic;
                File file = new File("F:\\myLearnMvp\\xuexiaojianguan\\app\\src\\main\\res\\drawable-xhdpi\\camera.png");
                String foodName = etFoodName.getText().toString().trim();
                String trialPerson = etTrialPerson.getText().toString().trim();
                String timeInterval = etTimeInterval.getText().toString().trim();
                String trialDes = etTrialDes.getText().toString().trim();
                Map<String , Object> param = new HashMap<>();
                param.put("canteenId" , canteenId);
                param.put("foodName" , foodName);
                param.put("eatPerson" , trialPerson);
                param.put("eatTime" , createDateStart);
                param.put("internalTime" , timeInterval);
                param.put("status" , canteenId);
                param.put("remarks" , trialDes);
                param.put("eatImage" , file);
                mPresenter.saveTrial(param);
                break;
        }
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId ;
        this.canteenName = canteenName ;
        tvSelectCanteen.setText(canteenName);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR,year);
        startcal.set(Calendar.MONTH,month);
        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        createDateStart = DateUtil.date2MMddWeek(startcal.getTime());
        tvTrialTime.setText(createDateStart);
    }
}
