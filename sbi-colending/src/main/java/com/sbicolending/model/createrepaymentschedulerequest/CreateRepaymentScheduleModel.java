package com.sbicolending.model.createrepaymentschedulerequest;

import lombok.Data;

import java.util.List;

@Data
public class CreateRepaymentScheduleModel {

    private List<RepaymentScheduleModel> repayment_schedules;
    public MoratoriumModel moratorium;
}
