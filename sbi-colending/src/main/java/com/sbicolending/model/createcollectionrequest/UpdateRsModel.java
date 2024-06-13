package com.sbicolending.model.createcollectionrequest;

import lombok.Data;

@Data
public class UpdateRsModel {

    public String due_date;
    public int installment_no;
    public int principal;
    public int pos;
    public int amount;
    public int interest;
    public String comment;
}
