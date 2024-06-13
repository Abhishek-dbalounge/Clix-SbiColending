package com.sbicolending.model.createloanresponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateLoanResponse {

    private  String clientLoanId;
    private  String status;

}
