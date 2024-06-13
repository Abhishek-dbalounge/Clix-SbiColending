package com.sbicolending.service;


import com.sbicolending.model.customerdetailsresponse.CustomerDetailResponseModel;

public interface CustomerDetailService {
    CustomerDetailResponseModel getCustomerDetails(String lanSp);
}
