package com.sbicolending.model.customerdetailsresponse;

import lombok.Data;

@Data
public class OriginalLoanDetailModel {

    public double loan_amount;
    public int tenure;
    public String tenure_frequency;

}
