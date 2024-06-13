package com.sbicolending.model.updatecustomerdetailsrequest;

import lombok.Data;

@Data
public class CustomerDetailUpdateOriginalLoanDetailModel {

    public double loan_amount;
    public int tenure;
    public String tenure_frequency;

}
