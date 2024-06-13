package com.sbicolending.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbicolending.exception.SystemException;
import com.sbicolending.model.clm2agreementsignedrequest.Clm2AgreementSignedModel;
import com.sbicolending.model.createcollectionrequest.CreateCollectionModel;
import com.sbicolending.model.createloanresponse.CreateLoanResponse;
import com.sbicolending.model.createrepaymentschedulerequest.CreateRepaymentScheduleModel;
import com.sbicolending.model.createloanrequest.CreateLoanRequestModel;
import com.sbicolending.model.updateloanrequest.UpdateLoanRequestModel;
import com.sbicolending.service.CallYubiApiService;
import com.sbicolending.utils.HttpClient;
import com.sbicolending.utils.NoResponseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CallYubiApiServiceIMPL implements CallYubiApiService {


    @Value("${yubi.url}")
    private String yubiUrl;
    @Value("${yubi.key}")
    private String yubiKey;
    @Value("${yubi.productId}")
    private String productId;



    private static ObjectMapper mapper= new ObjectMapper();
    @Override
    public Object getCreateLoan(CreateLoanRequestModel request) {

        try {

            Map<String, String> header = getCreateLoanAPIRequestHeaders();
            String requestString = getCreateLoanRequest(request);
            Map<String,String> responseMap = new HashMap<>();

            responseMap = HttpClient.handlePOSTWebRequest(yubiUrl,requestString,header);
            String responseCode = responseMap.get("responseCode");
            String responseString = responseMap.get("responseString");

            if (StringUtils.isBlank(responseString)) {
                throw new SystemException("1115","Api no any response");
            }

            return mapper.readValue(responseString, Object.class);
        }
        catch (NoResponseException npe) {
            throw new SystemException(npe.getErrorCode(),npe.getErrorMsg());
        }
        catch (Exception e){
            throw new SystemException("1111","something went worng");
        }
    }

    private String getCreateLoanRequest(CreateLoanRequestModel requestModel) throws Exception{

        return  mapper.writeValueAsString(requestModel);
    }

    private  Map<String,String>getCreateLoanAPIRequestHeaders(){

        Map<String,String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Product-Id",productId);
        headers.put("Accept","application/json");
        headers.put("Api-key",yubiKey);

        return headers;
    }









    @Override
    public Object getLoanDetails(String loanId) {
        try {

            Map<String, String> header = getLoanDetailsAPIRequestHeaders();
            Map<String,String> responseMap = new HashMap<>();

            responseMap = HttpClient.handleGETWebRequest(yubiUrl+"/"+loanId,header);
            String responseCode = responseMap.get("responseCode");
            String responseString = responseMap.get("responseString");

            if (StringUtils.isBlank(responseString)) {
                throw new SystemException("1115","Api no any response");
            }

            return mapper.readValue(responseString,Object.class);
        }
        catch (NoResponseException npe) {
            throw new SystemException(npe.getErrorCode(),npe.getErrorMsg());
        }
        catch (Exception e){
            throw new SystemException("1111","something went worng");
        }
    }

    private  Map<String,String>getLoanDetailsAPIRequestHeaders(){

        Map<String,String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Product-Id",productId);
        headers.put("Accept","application/json");
        headers.put("Api-key",yubiKey);

        return headers;
    }







    public Object getUpdateLoanDetails(String loanId,UpdateLoanRequestModel request) {

        try {

            Map<String, String> header = getUpdateLoanAPIRequestHeaders();
            String requestString = getUpdateLoanRequest(request);
            Map<String, String> responseMap = new HashMap<>();

            responseMap = HttpClient.handlePUTWebRequest(yubiUrl+"/"+loanId, requestString, header);
            String responseCode = responseMap.get("responseCode");
            String responseString = responseMap.get("responseString");

            if (StringUtils.isBlank(responseString)) {
                throw new SystemException("1115", "Api no any response");
            }

            return mapper.readValue(responseString, Object.class);

        }catch (NoResponseException npe) {

            throw new SystemException(npe.getErrorCode(),npe.getErrorMsg());
        }
        catch (Exception e){
            throw new SystemException("1111","something went worng");
        }

    }

    private String getUpdateLoanRequest(UpdateLoanRequestModel requestModel) throws Exception {

        return  mapper.writeValueAsString(requestModel);
    }
    private Map<String, String> getUpdateLoanAPIRequestHeaders() {
        Map<String,String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Product-Id",productId);
        headers.put("Accept","application/json");
        headers.put("Api-key",yubiKey);

        return headers;
    }




    @Value("${yubi.clm2AgreementSignedUrl}")
    private String clm2AgreementSignedUrl;

    @Override
    public Object getClm2AgreementSigned(Clm2AgreementSignedModel request) {
        try {

            Map<String, String> header = getClm2AgreementSignedAPIRequestHeaders();
            String requestString = getClm2AgreementSignedRequest(request);
            Map<String, String> responseMap = new HashMap<>();

            responseMap = HttpClient.handlePUTWebRequest(clm2AgreementSignedUrl, requestString, header);
            String responseCode = responseMap.get("responseCode");
            String responseString = responseMap.get("responseString");

            if (StringUtils.isBlank(responseString)) {
                throw new SystemException("1115", "Api no any response");
            }

            return mapper.readValue(responseString, Object.class);

        }catch (NoResponseException npe) {

            throw new SystemException(npe.getErrorCode(),npe.getErrorMsg());
        }
        catch (Exception e){
            throw new SystemException("1111","something went worng");
        }
    }

    private String getClm2AgreementSignedRequest(Clm2AgreementSignedModel requestModel) throws Exception {
        return  mapper.writeValueAsString(requestModel);
    }

    private Map<String, String> getClm2AgreementSignedAPIRequestHeaders() {
        Map<String,String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Product-Id",productId);
        headers.put("Accept","application/json");
        headers.put("Api-key",yubiKey);

        return headers;
    }






    @Value("${yubi.getPoolDetailsUrl}")
    private String getPoolDetailsUrl;

    @Override
    public Object getPoolDetails(String pool_id) {
        try {

            Map<String, String> header = getPoolDetailsAPIRequestHeaders();
            Map<String,String> responseMap = new HashMap<>();

            responseMap = HttpClient.handleGETWebRequest(getPoolDetailsUrl+"/"+pool_id,header);
            String responseCode = responseMap.get("responseCode");
            String responseString = responseMap.get("responseString");

            if (StringUtils.isBlank(responseString)) {
                throw new SystemException("1115","Api no any response");
            }

            return mapper.readValue(responseString,Object.class);
        }
        catch (NoResponseException npe) {
            throw new SystemException(npe.getErrorCode(),npe.getErrorMsg());
        }
        catch (Exception e){
            throw new SystemException("1111","something went worng");
        }
    }

    private Map<String, String> getPoolDetailsAPIRequestHeaders() {

        Map<String,String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Product-Id",productId);
        headers.put("Accept","application/json");
        headers.put("Api-key",yubiKey);

        return headers;
    }




    @Value("${yubi.createRepaymentScheduleUrl}")
    private String createRepaymentScheduleUrl;
    @Override
    public Object getCreateRepaymentSchedule(CreateRepaymentScheduleModel request,String loanId) {

        try {

            Map<String, String> header = getCreateRepaymentScheduleAPIRequestHeaders();
            String requestString = getCreateRepaymentScheduleRequest(request);
            Map<String,String> responseMap = new HashMap<>();


            responseMap = HttpClient.handlePOSTWebRequest(createRepaymentScheduleUrl+"/"+loanId +"/"+"rs",requestString,header);
            String responseCode = responseMap.get("responseCode");
            String responseString = responseMap.get("responseString");

            if (StringUtils.isBlank(responseString)) {
                throw new SystemException("1115","Api no any response");
            }

            return mapper.readValue(responseString, Object.class);
        }
        catch (NoResponseException npe) {
            throw new SystemException(npe.getErrorCode(),npe.getErrorMsg());
        }
        catch (Exception e){
            throw new SystemException("1111","something went worng");
        }

    }
    private String getCreateRepaymentScheduleRequest(CreateRepaymentScheduleModel requestModel) throws Exception{

        return  mapper.writeValueAsString(requestModel);
    }

    private  Map<String,String>getCreateRepaymentScheduleAPIRequestHeaders(){

        Map<String,String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Product-Id",productId);
        headers.put("Accept","application/json");
        headers.put("Api-key",yubiKey);

        return headers;
    }








    @Value("${yubi.createCollectionUrl}")
    private String createCollectionUrl;
    @Override
    public Object getCreateCollection(CreateCollectionModel request, String loanId) {
        try {

            Map<String, String> header = getCreateCollectionRequestHeaders();
            String requestString = getCreateCollectionRequest(request);
            Map<String,String> responseMap = new HashMap<>();

            responseMap = HttpClient.handlePOSTWebRequest(createCollectionUrl+"/"+loanId +"/"+"payments"+"/"+"record_payment",requestString,header);
            String responseCode = responseMap.get("responseCode");
            String responseString = responseMap.get("responseString");

            if (StringUtils.isBlank(responseString)) {
                throw new SystemException("1115","Api no any response");
            }

            return mapper.readValue(responseString, Object.class);
        }
        catch (NoResponseException npe) {
            throw new SystemException(npe.getErrorCode(),npe.getErrorMsg());
        }
        catch (Exception e){
            throw new SystemException("1111","something went worng");
        }
    }

    private String getCreateCollectionRequest(CreateCollectionModel requestModel) throws Exception{
        return  mapper.writeValueAsString(requestModel);
    }

    private Map<String, String> getCreateCollectionRequestHeaders() {

        Map<String,String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Product-Id",productId);
        headers.put("Accept","application/json");
        headers.put("Api-key",yubiKey);

        return headers;
    }





    @Value("${yubi.getCollectionDetailsUrl}")
    private String getCollectionDetailsUrl;
    @Override
    public Object getCollectionDetails(String loanId) {
        try {

            Map<String, String> header = getCollectionDetailsAPIRequestHeaders();
            Map<String,String> responseMap = new HashMap<>();

            responseMap = HttpClient.handleGETWebRequest(getCollectionDetailsUrl+"/"+loanId+"/"+"payments",header);
            String responseCode = responseMap.get("responseCode");
            String responseString = responseMap.get("responseString");

            if (StringUtils.isBlank(responseString)) {
                throw new SystemException("1115","Api no any response");
            }

            return mapper.readValue(responseString,Object.class);
        }
        catch (NoResponseException npe) {
            throw new SystemException(npe.getErrorCode(),npe.getErrorMsg());
        }
        catch (Exception e){
            throw new SystemException("1111","something went worng");
        }
    }

    private Map<String, String> getCollectionDetailsAPIRequestHeaders() {

        Map<String,String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Product-Id",productId);
        headers.put("Accept","application/json");
        headers.put("Api-key",yubiKey);

        return headers;
    }


}
