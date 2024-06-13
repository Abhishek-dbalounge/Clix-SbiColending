package com.sbicolending.dao;

import com.sbicolending.model.CustomerDetailDataModel;

import java.util.List;

public interface CustomerDetailDao {
    List<CustomerDetailDataModel> getCustomerDetailsforLanSP(String lanSp);
}
