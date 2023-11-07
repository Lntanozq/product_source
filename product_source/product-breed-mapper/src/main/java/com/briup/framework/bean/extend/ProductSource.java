package com.briup.framework.bean.extend;

import java.util.List;

/**
 * @author pgc
 * @Description: 溯源类
 * @date 2022/4/11 14:06
 */
public class ProductSource {
    //基本信息
     private ManagerAnimalExtend mManagerAnimalExtend;
     //移圈历史
    private List<ShiftCircleExtend> mShiftCircleExtends;
    //接种记录
    private List<InoculationRecordExtend> mInoculationRecordExtends;
    //饲料喂养
    private List<FeedRecordExtend> mFeedRecordExtends;
    //病症记录
    private List<DiseaseRecordExtend> mDiseaseRecordExtends;
    //指标记录
    private List<IndexRecordExtend> mIndexRecordExtends;
    //检疫阶段
    private QuarantineRegistrationExtend mQuarantineRegistrationExtend;
    //出栏阶段
    private OutCircleExtend mOutCircleExtend;
    //屠宰阶段
    private SlaughterRegistrationExtend slaughterRegistrationExtend;
    //仓储阶段
    private ColdStorageRecordExtend1 mColdStorageRecordExtend1;
    //销售阶段
    private IssueRecordExtend mIssueRecordExtend;

    public ManagerAnimalExtend getManagerAnimalExtend() {
        return mManagerAnimalExtend;
    }

    public void setManagerAnimalExtend(ManagerAnimalExtend managerAnimalExtend) {
        mManagerAnimalExtend = managerAnimalExtend;
    }

    public List<ShiftCircleExtend> getShiftCircleExtends() {
        return mShiftCircleExtends;
    }

    public void setShiftCircleExtends(List<ShiftCircleExtend> shiftCircleExtends) {
        mShiftCircleExtends = shiftCircleExtends;
    }

    public List<InoculationRecordExtend> getInoculationRecordExtends() {
        return mInoculationRecordExtends;
    }

    public void setInoculationRecordExtends(List<InoculationRecordExtend> inoculationRecordExtends) {
        mInoculationRecordExtends = inoculationRecordExtends;
    }

    public List<FeedRecordExtend> getFeedRecordExtends() {
        return mFeedRecordExtends;
    }

    public void setFeedRecordExtends(List<FeedRecordExtend> feedRecordExtends) {
        mFeedRecordExtends = feedRecordExtends;
    }

    public List<DiseaseRecordExtend> getDiseaseRecordExtends() {
        return mDiseaseRecordExtends;
    }

    public void setDiseaseRecordExtends(List<DiseaseRecordExtend> diseaseRecordExtends) {
        mDiseaseRecordExtends = diseaseRecordExtends;
    }

    public List<IndexRecordExtend> getIndexRecordExtends() {
        return mIndexRecordExtends;
    }

    public void setIndexRecordExtends(List<IndexRecordExtend> indexRecordExtends) {
        mIndexRecordExtends = indexRecordExtends;
    }

    public QuarantineRegistrationExtend getQuarantineRegistrationExtend() {
        return mQuarantineRegistrationExtend;
    }

    public void setQuarantineRegistrationExtend(QuarantineRegistrationExtend quarantineRegistrationExtend) {
        mQuarantineRegistrationExtend = quarantineRegistrationExtend;
    }

    public OutCircleExtend getOutCircleExtend() {
        return mOutCircleExtend;
    }

    public void setOutCircleExtend(OutCircleExtend outCircleExtend) {
        mOutCircleExtend = outCircleExtend;
    }

    public SlaughterRegistrationExtend getSlaughterRegistrationExtend() {
        return slaughterRegistrationExtend;
    }

    public void setSlaughterRegistrationExtend(SlaughterRegistrationExtend slaughterRegistrationExtend) {
        this.slaughterRegistrationExtend = slaughterRegistrationExtend;
    }

    public ColdStorageRecordExtend1 getColdStorageRecordExtend1() {
        return mColdStorageRecordExtend1;
    }

    public void setColdStorageRecordExtend1(ColdStorageRecordExtend1 coldStorageRecordExtend1) {
        mColdStorageRecordExtend1 = coldStorageRecordExtend1;
    }

    public IssueRecordExtend getIssueRecordExtend() {
        return mIssueRecordExtend;
    }

    public void setIssueRecordExtend(IssueRecordExtend issueRecordExtend) {
        mIssueRecordExtend = issueRecordExtend;
    }
}
