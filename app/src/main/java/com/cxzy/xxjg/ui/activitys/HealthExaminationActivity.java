package com.cxzy.xxjg.ui.activitys;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.HealthExaminationBean;
import com.cxzy.xxjg.bean.ItemsBean;
import com.cxzy.xxjg.bean.PersonsBean;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectPersonDialog;
import com.cxzy.xxjg.ui.adapter.CheckItemsAdapter;
import com.cxzy.xxjg.ui.adapter.PersonLabelAdapter;
import com.cxzy.xxjg.ui.test.presenter.HealthExaminationPresenterImpl;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 卫生检查
 */
public class HealthExaminationActivity extends BaseActivity<HealthExaminationPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rg_check)
    RadioGroup rgCheck;
    @BindView(R.id.tv_check_des)
    TextView tvCheckDes;
    @BindView(R.id.lv_check_items)
    ListView lvCheckItems;
    @BindView(R.id.ll_morning_check)
    LinearLayout llMorningCheck;
    @BindView(R.id.rv_through_person)
    RecyclerView rvThroughPerson;
    @BindView(R.id.rv_no_through_person)
    RecyclerView rvNoThroughPerson;
    @BindView(R.id.tv_positive_pic)
    TextView tvPositivePic;
    @BindView(R.id.main_title_id)
    TextView tvTitle;
    @BindView(R.id.tv_check_name)
    TextView tvCheckName;
    private String canteenId;
    private ArrayList<SchoolCanteenBean> dataList = new ArrayList<>();
    private HealthExaminationBean bean = new HealthExaminationBean();
    private List<ItemsBean> itemList = new ArrayList<>();
    private CheckItemsAdapter mAdapter;
    private List<PersonsBean> personBean = new ArrayList<>();
    private List<PersonsBean> throughPerson = new ArrayList<>(); //检查通过
    private List<PersonsBean> noThroughPerson = new ArrayList<>(); //检查未通过


    @Override
    public int getContentLayout() {
        return R.layout.activity_health_examination;
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
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id;
        String canteenName = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name;
        tvTitle.setText(canteenName);
        rgCheck.setOnCheckedChangeListener(this);
        mPresenter.getHealthCheck(canteenId);
        mAdapter = new CheckItemsAdapter(this, itemList);
        lvCheckItems.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        bean = (HealthExaminationBean) mData;
        rgCheck.check(R.id.rb_morning_check);
        personBean.clear();
        personBean.addAll(bean.persons);
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.ll_select_canteen, R.id.btn_add_through_person, R.id.btn_save_morningcheck ,R.id.btn_add_no_through_person})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.ll_select_canteen://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this, dataList, this);
                canteenDialog.show();
                break;
            case R.id.btn_save_morningcheck://提交晨检
                List<File> fileList = new ArrayList<>();
                File file = new File("/Users/tianwei/Desktop/xuexiaojianguan/app/src/main/res/mipmap-xhdpi/ic_launcher.png");
                fileList.add(file);
                mPresenter.saveMorningCheck(personBean , fileList);
                break;
            case R.id.btn_add_through_person://添加检查通过
                SelectPersonDialog dialog = new SelectPersonDialog(this,0 , personBean, new SelectPersonDialog.SelectPersonClickLister() {
                    @Override
                    public void selectItemPerson(List<PersonsBean> infoList) {
                        throughPerson.addAll(infoList);
                        for (PersonsBean bean:infoList) {
                            if (personBean != null && personBean.contains(bean)){
                                personBean.remove(bean);
                            }
                        }
                        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
                        layoutManager.setFlexWrap(FlexWrap.WRAP);
                        layoutManager.setFlexDirection(FlexDirection.ROW);
                        layoutManager.setAlignItems(AlignItems.STRETCH);
                        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
                        rvThroughPerson.setLayoutManager(layoutManager);
                        PersonLabelAdapter adapter = new PersonLabelAdapter(throughPerson);
                        rvThroughPerson.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.show();
                break;
            case R.id.btn_add_no_through_person://添加检查未通过
                SelectPersonDialog dialog1 = new SelectPersonDialog(this,1 , personBean, new SelectPersonDialog.SelectPersonClickLister() {
                    @Override
                    public void selectItemPerson(List<PersonsBean> infoList) {
                        noThroughPerson.addAll(infoList);
                        for (PersonsBean bean:infoList) {
                            if (personBean != null && personBean.contains(bean)){
                                personBean.remove(bean);
                            }
                        }
                        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
                        layoutManager.setFlexWrap(FlexWrap.WRAP);
                        layoutManager.setFlexDirection(FlexDirection.ROW);
                        layoutManager.setAlignItems(AlignItems.STRETCH);
                        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
                        rvNoThroughPerson.setLayoutManager(layoutManager);
                        PersonLabelAdapter adapter = new PersonLabelAdapter(noThroughPerson);
                        rvNoThroughPerson.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog1.show();
                break;
        }

    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId;
        tvTitle.setText(canteenName);
        mPresenter.getHealthCheck(canteenId);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_morning_check) {//晨检
            llMorningCheck.setVisibility(View.VISIBLE);
            tvCheckName.setText(bean.types.get(0).typeName);
            tvCheckDes.setText(bean.types.get(0).typeDesc);
            itemList.clear();
            itemList.addAll(bean.types.get(0).items);
            mAdapter.notifyDataSetChanged();
        } else {//环境卫生
            llMorningCheck.setVisibility(View.GONE);
            tvCheckName.setText(bean.types.get(1).typeName);
            tvCheckDes.setText(bean.types.get(1).typeDesc);
            itemList.clear();
            itemList.addAll(bean.types.get(1).items);
            mAdapter.notifyDataSetChanged();
        }
    }
}
