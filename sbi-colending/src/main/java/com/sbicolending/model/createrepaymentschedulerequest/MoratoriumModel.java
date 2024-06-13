package com.sbicolending.model.createrepaymentschedulerequest;

import lombok.Data;

@Data
public class MoratoriumModel {

    private String moratorium_type;
    private String end_date;
    private String start_date;
}
