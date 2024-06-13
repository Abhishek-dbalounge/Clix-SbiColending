package com.sbicolending.model.updateloanrequest;

import lombok.Data;

@Data
public class UpdateLoanRequestOriginalLoanDetailModel {

    public double loan_amount;
    public int tenure;
    public String tenure_frequency;

}
