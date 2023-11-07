package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.SituationReporting;

/**
 * @author pgc
 * @Description: 情况上报扩展类
 * @date 2022/3/31 18:11
 */
public class SituationReportingExtend extends SituationReporting {
    private BaseAccountExtend reportUser;
    private BaseAccount doctorUser;

    public BaseAccount getReportUser() {
        return reportUser;
    }

    public void setReportUser(BaseAccountExtend reportUser) {
        this.reportUser = reportUser;
    }

    public BaseAccount getDoctorUser() {
        return doctorUser;
    }

    public void setDoctorUser(BaseAccount doctorUser) {
        this.doctorUser = doctorUser;
    }
}
