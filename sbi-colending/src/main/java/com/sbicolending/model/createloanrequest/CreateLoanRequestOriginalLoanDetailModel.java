package com.sbicolending.model.createloanrequest;

import lombok.Data;

@Data
public class CreateLoanRequestOriginalLoanDetailModel {

    public double loan_amount;
    public int tenure;
    public String tenure_frequency;

}
