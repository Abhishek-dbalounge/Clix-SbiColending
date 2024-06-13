package com.sbicolending.model.createrepaymentschedulerequest;

import lombok.Data;

@Data
public class RepaymentScheduleModel {

    private int interest;
    private int amount;
    private int other_charges;
    private String due_date;
    private int pos;
    private int principal;
    private int installment_no;
}
