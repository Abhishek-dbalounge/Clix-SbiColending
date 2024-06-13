package com.sbicolending.controller;

import com.google.gson.Gson;
import com.sbicolending.exception.SystemException;
import com.sbicolending.model.clm2agreementsignedrequest.Clm2AgreementSignedModel;
import com.sbicolending.model.CommonResponseModel;
import com.sbicolending.model.createcollectionrequest.CreateCollectionModel;
import com.sbicolending.model.createrepaymentschedulerequest.CreateRepaymentScheduleModel;
import com.sbicolending.model.createloanrequest.CreateLoanRequestModel;
import com.sbicolending.model.updateloanrequest.UpdateLoanRequestModel;
import com.sbicolending.service.CallYubiApiService;
import com.sbicolending.utils.BaseLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/yubi")
public class CallYubiApiController {

    private Logger logger = BaseLogger.getLogger(CallYubiApiController.class);
    @Autowired
    private CallYubiApiService callYubiApiService;

    @PostMapping("/createLoan")
    public ResponseEntity<?> createLoan(@RequestBody CreateLoanRequestModel request){

        try {
            if(StringUtils.isEmpty(request)){
                CommonResponseModel commonResponse = new CommonResponseModel();
                logger.info("createLoan : request filed is empty");
                commonResponse.setErrorMsg("request filed is empty");
                commonResponse.setErrorCode("1112");
                return new ResponseEntity<>(commonResponse,HttpStatus.OK);
            }
            Object createLoanResponse = callYubiApiService.getCreateLoan(request);

            String createLoanResponseJSONtoString = createLoanResponse.toString();
            Map jsonJavaRootObject = new Gson().fromJson(createLoanResponseJSONtoString, Map.class);

            String client_loan_id= (String) jsonJavaRootObject.get("client_loan_id");
            String status= (String) jsonJavaRootObject.get("status");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String localDateTime = dtf.format(now);

            System.out.println("client_loan_id :- "+client_loan_id);
            System.out.println("status :- "+status);
            System.out.println("loan_create_time :- "+localDateTime);

            return new ResponseEntity<>(createLoanResponse, HttpStatus.OK);

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



    @GetMapping("/getLoanDetails/{loan_id}")
    public ResponseEntity<?> getLoanDetails(@PathVariable("loan_id") String loan_id){

        try {

            Object getLoanDetailsResponse = callYubiApiService.getLoanDetails(loan_id);

            return new ResponseEntity<>(getLoanDetailsResponse, HttpStatus.OK);

        }catch (Exception e){

            CommonResponseModel commonResponse = new CommonResponseModel();
            logger.error(e.toString());
            commonResponse.setErrorMsg("something went worng");
            commonResponse.setErrorCode("1111");
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }


    }




    @PutMapping("/updateCustomerDetails/{loan_id}")
    public ResponseEntity<?> updateLoanDetails(@PathVariable("loan_id") String loan_id, @RequestBody UpdateLoanRequestModel request) {

        try {
            if (StringUtils.isEmpty(request)) {
                CommonResponseModel commonResponse = new CommonResponseModel();
                logger.info("updateLoanDetails : request filed is empty");
                commonResponse.setErrorMsg("request filed is empty");
                commonResponse.setErrorCode("1112");
                return new ResponseEntity<>(commonResponse, HttpStatus.OK);
            }

            Object updateLoanDetailsResponse = callYubiApiService.getUpdateLoanDetails(loan_id, request);

            return new ResponseEntity<>(updateLoanDetailsResponse, HttpStatus.OK);


        } catch (Exception e) {
            CommonResponseModel commonResponse = new CommonResponseModel();
            logger.error(e.toString());
            commonResponse.setErrorMsg("something went worng");
            commonResponse.setErrorCode("1111");
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }

    }





    @PutMapping("/clm2AgreementSigned")
    public ResponseEntity<?> clm2AgreementSigned(@RequestBody Clm2AgreementSignedModel request) {

        try {
            if(StringUtils.isEmpty(request)){
                CommonResponseModel commonResponse = new CommonResponseModel();
                logger.info("clm2AgreementSigned : request filed is empty");
                commonResponse.setErrorMsg("request filed is empty");
                commonResponse.setErrorCode("1112");
                return new ResponseEntity<>(commonResponse,HttpStatus.OK);
            }

            Object clm2AgreementSignedResponse = callYubiApiService.getClm2AgreementSigned(request);

            return new ResponseEntity<>(clm2AgreementSignedResponse, HttpStatus.OK);



        }catch (Exception e){
            CommonResponseModel commonResponse = new CommonResponseModel();
            logger.error(e.toString());
            commonResponse.setErrorMsg("something went worng");
            commonResponse.setErrorCode("1111");
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }

    }





    @GetMapping("/getPoolDetails/{pool_id}")
    public ResponseEntity<?> getPoolDetails(@PathVariable("pool_id") String pool_id){

        try {

            Object getPoolDetailsResponse = callYubiApiService.getPoolDetails(pool_id);

            return new ResponseEntity<>(getPoolDetailsResponse, HttpStatus.OK);

        }catch (Exception e){

            CommonResponseModel commonResponse = new CommonResponseModel();
            logger.error(e.toString());
            commonResponse.setErrorMsg("something went worng");
            commonResponse.setErrorCode("1111");
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
    }



    @PostMapping("/createRepaymentSchedule/{loan_id}")
    public ResponseEntity<?> createRepaymentSchedule(@RequestBody CreateRepaymentScheduleModel request,@PathVariable ("loan_id") String loan_id ){

        try {
            if(StringUtils.isEmpty(request)){
                CommonResponseModel commonResponse = new CommonResponseModel();
                logger.info("createRepaymentSchedule : request filed is empty");
                commonResponse.setErrorMsg("request filed is empty");
                commonResponse.setErrorCode("1112");
                return new ResponseEntity<>(commonResponse,HttpStatus.OK);
            }
            Object createRepaymentScheduleResponse = callYubiApiService.getCreateRepaymentSchedule(request,loan_id);

            return new ResponseEntity<>(createRepaymentScheduleResponse, HttpStatus.OK);

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




    @PostMapping("/createCollection/{loan_id}")
    public ResponseEntity<?> createCollection(@RequestBody CreateCollectionModel request, @PathVariable ("loan_id") String loan_id ){

        try {
            if(StringUtils.isEmpty(request)){
                CommonResponseModel commonResponse = new CommonResponseModel();
                logger.info("createCollection : request filed is empty");
                commonResponse.setErrorMsg("request filed is empty");
                commonResponse.setErrorCode("1112");
                return new ResponseEntity<>(commonResponse,HttpStatus.OK);
            }
            Object createCollectionResponse = callYubiApiService.getCreateCollection(request,loan_id);

            return new ResponseEntity<>(createCollectionResponse, HttpStatus.OK);

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





    @GetMapping("/getCollectionDetails/{loan_id}")
    public ResponseEntity<?> getCollectionDetails(@PathVariable("pool_id") String loan_id){

        try {

            Object getPoolDetailsResponse = callYubiApiService.getCollectionDetails(loan_id);

            return new ResponseEntity<>(getPoolDetailsResponse, HttpStatus.OK);

        }catch (Exception e){

            CommonResponseModel commonResponse = new CommonResponseModel();
            logger.error(e.toString());
            commonResponse.setErrorMsg("something went worng");
            commonResponse.setErrorCode("1111");
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
    }


}
