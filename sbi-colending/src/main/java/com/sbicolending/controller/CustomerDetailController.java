package com.sbicolending.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbicolending.exception.SystemException;
import com.sbicolending.model.*;
import com.sbicolending.model.customerdetailsresponse.CustomerDetailResponseModel;
import com.sbicolending.service.CustomerDetailService;
import com.sbicolending.utils.BaseLogger;
import com.sbicolending.utils.HttpClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer")
public class CustomerDetailController {

    private Logger logger = BaseLogger.getLogger(CustomerDetailController.class);
    @Autowired
    private CustomerDetailService customerDetailService;

    @GetMapping("/customerDetails/{lanSp}")
    public ResponseEntity<?> customerDetails(@PathVariable("lanSp") String lanSp){

        try {
        if(StringUtils.isEmpty(lanSp)){
            CommonResponseModel commonResponse = new CommonResponseModel();
            logger.info("customerDetails : LenSp filed is empty");
            commonResponse.setErrorMsg("LenSp filed is empty");
            commonResponse.setErrorCode("1112");
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
                CustomerDetailResponseModel customerDetailResponse = customerDetailService.getCustomerDetails(lanSp);
                if (customerDetailResponse == null) {
                    logger.info("customerDetails : Invalid LenSp");
                    throw new SystemException("1113","Invalid LenSp");
                }
               return new ResponseEntity<>(customerDetailResponse, HttpStatus.OK);
            }
            catch (SystemException se){
                CommonResponseModel commonResponse = new CommonResponseModel();
                logger.error(se.toString());
                commonResponse.setErrorMsg(se.getMessage());
                commonResponse.setErrorCode(se.getRespCode());
                return new ResponseEntity<>(commonResponse,HttpStatus.OK);
            }
            catch (Exception e){
                CommonResponseModel commonResponse = new CommonResponseModel();
                logger.error(e.toString());
                commonResponse.setErrorMsg("something went worng");
                commonResponse.setErrorCode("1111");
                return new ResponseEntity<>(commonResponse,HttpStatus.OK);
            }
    }
}
