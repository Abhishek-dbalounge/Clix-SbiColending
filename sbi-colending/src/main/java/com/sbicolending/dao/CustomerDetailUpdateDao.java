package com.sbicolending.dao;

import com.sbicolending.model.CommonResponseModel;
import com.sbicolending.model.updatecustomerdetailsrequest.CustomerDetailUpdateModel;

public interface CustomerDetailUpdateDao {

    boolean updateCustomerDetails(CustomerDetailUpdateModel customerDetailUpdateData, String lanSp);

}
