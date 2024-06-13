package com.sbicolending.service;

import com.sbicolending.model.CommonResponseModel;
import com.sbicolending.model.updatecustomerdetailsrequest.CustomerDetailUpdateModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerDetailUpdateService {

    CommonResponseModel updateCustomerDetailsData(String lanSp, MultipartFile file,List<String> header);
}
