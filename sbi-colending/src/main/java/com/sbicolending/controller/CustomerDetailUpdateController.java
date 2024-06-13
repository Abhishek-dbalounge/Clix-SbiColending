package com.sbicolending.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbicolending.exception.SystemException;
import com.sbicolending.model.CommonResponseModel;
import com.sbicolending.service.CustomerDetailUpdateService;
import com.sbicolending.utils.BaseLogger;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/customer")
public class CustomerDetailUpdateController {

    private Logger logger = BaseLogger.getLogger(CustomerDetailUpdateController.class);

    @Autowired
    private CustomerDetailUpdateService customerDetailUpdateService;



    @PutMapping("/updateCustomerDetails/{lanSp}")
    public ResponseEntity<?> customerDetails(@PathVariable("lanSp") String lanSp, @RequestParam("file") MultipartFile file){

        try {
            if(StringUtils.isEmpty(file)){
                CommonResponseModel commonResponse = new CommonResponseModel();
                logger.info("updateCustomerDetails : file is empty");
                commonResponse.setErrorMsg("file is empty");
                commonResponse.setErrorCode("1112");
                return new ResponseEntity<>(commonResponse, HttpStatus.OK);
            }

            List<String> header = getCustomerDetailsUpdateExcelHeaderList();
            CommonResponseModel commonResponseModel = customerDetailUpdateService.updateCustomerDetailsData(lanSp,file, header);

            return new ResponseEntity<>(commonResponseModel, HttpStatus.OK);

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

    private List<String> getCustomerDetailsUpdateExcelHeaderList() {

        List<String> header = new ArrayList<String>();

        header.add("primary_borrower_type");
        header.add("first_name");
        header.add("current_address");
        header.add("current_state");
        header.add("customer_category");
        header.add("client_customer_id");
        header.add("title");
        header.add("last_name");
        header.add("gender");
        header.add("mobile_number");
        header.add("place_of_birth");
        header.add("current_city");
        header.add("current_district");
        header.add("current_pincode");
        header.add("residence_type_current_address");
        header.add("permanent_district");
        header.add("permanent_pincode");
        header.add("residence_type_permanent_address");
        header.add("religion");
        header.add("caste");
        header.add("occupation");
        header.add("income_source");
        header.add("property_ownership_flag");
        header.add("udyam_number");
        header.add("name_of_bureau");
        header.add("bureau_score");
        header.add("product_id");
        header.add("application_id");
        header.add("category");
        header.add("sub_category");
        header.add("purpose");
        header.add("disbursement_type");
        header.add("number_of_tranches");
        header.add("tenure");
        header.add("number_of_repayments");
        header.add("psl_description");
        header.add("impacted_industry_description");
        header.add("applicant_never_in_30_plus_sma_sub_last_12m");
        header.add("tax_slab");
        header.add("psl_msme_type_of_enterprise");
        header.add("current_address_line1");
        header.add("cersai_security_int_id");
        header.add("repayment_frequency");
        header.add("father_first_name");
        header.add("father_last_name");
        header.add("total_value_of_security");
        header.add("tenure_frequency");
        header.add("marital_status");
        header.add("ckyc_id");
        header.add("date_of_birth");
        header.add("first_repayment_date");
        header.add("itr_filed_date_current_fy");
        header.add("application_date");
        header.add("fcu_done_date");
        header.add("e_verification_of_kyc_done_date");
        header.add("pd_done_date");
        header.add("search_with_registrar_of_companies_obtained_date");
        header.add("cersai_registration_reference_number_verification_date");
        header.add("cibil_report_done_date");
        header.add("repayment_period_end_date");
        header.add("date_of_sanction_by_nbfc");
        header.add("date_of_documentation_by_nbfc");
        header.add("annual_income");
        header.add("principal_amount");
        header.add("interest_rate");
        header.add("loan_emi");
        header.add("applicant_age_at_maturity");
        //=======Business======
        header.add("Business.name_of_business");
        header.add("Business.nature_of_business");
        header.add("Business.type_of_constitution");
        header.add("Business.registration_date");
        header.add("Business.incorporation_date");
        header.add("Business.industry_type");
        header.add("Business.sector_type");
        header.add("Business.sub_sector_type");
        header.add("Business.business_vintage");
        header.add("Business.business_registered_office_address");
        header.add("Business.business_registered_office_city");
        header.add("Business.business_registered_office_state");
        header.add("Business.business_registered_office_pincode");
        header.add("Business.business_mailing_office_address");
        header.add("Business.String business_mailing_office_city");
        header.add("Business.business_mailing_office_pincode");
        header.add("Business.business_phone_number");
        header.add("Business.business_email_id");
        header.add("Business.property_ownership_flag");
        header.add("Business.business_pan_number");
        header.add("Business.business_rc_number");
        header.add("Business.business_udhyam_regn_number");
        header.add("Business.business_mailing_office_address1");
        header.add("Business.business_mailing_office_address2");
        header.add("Business.business_mailing_office_address3");
        header.add("Business.enterprise_activity");
        header.add("Business.customer_type");
        header.add("Business.exisiting_shareholding_of_promoters");
        header.add("Business.exisiting_shareholding_of_mutual_funds");
        header.add("Business.exisiting_shareholding_of_financial_institutions_banks");
        header.add("Business.exisiting_shareholding_of_others");
        header.add("Business.exisiting_shareholding_total");
        header.add("Business.proposed_shareholding_of_promoters");
        header.add("Business.proposed_shareholding_of_mutual_funds");
        header.add("Business.proposed_shareholding_of_financial_institutions_banks");
        header.add("Business.proposed_shareholding_of_others");
        header.add("Business.proposed_shareholding_total");
        header.add("Business.annual_income");
        header.add("Business.balance_sheet_year");
        header.add("Business.ckyc_id");
        //=======Guarantors======
       /* header.add("Guarantors.date_of_birth");
        header.add("Guarantors.gender");
        header.add("Guarantors.current_address");
        header.add("Guarantors.current_district");
        header.add("Guarantors.current_city");
        header.add("Guarantors.current_state");
        header.add("Guarantors.current_pincode");
        header.add("Guarantors.permanent_district");
        header.add("Guarantors.permanent_pincode");
        header.add("Guarantors.mobile_number");
        header.add("Guarantors.marital_status");
        header.add("Guarantors.religion");
        header.add("Guarantors.bureau_score");
        header.add("Guarantors.age_at_maturity");
        header.add("Guarantors.employment_details_designation");
        header.add("Guarantors.caste");
        header.add("Guarantors.father_first_name");
        header.add("Guarantors.father_last_name");
        header.add("Guarantors.occupation");
        header.add("Guarantors.place_of_birth");
        header.add("Guarantors.title");
        header.add("Guarantors.income_source");
        header.add("Guarantors.current_address_line1");
        header.add("Guarantors.education");
        header.add("Guarantors.other_than_agricultural_income");
        header.add("Guarantors.total_net_worth");
        header.add("Guarantors.customer_type");
        header.add("Guarantors.ckyc_id");
        header.add("Guarantors.annual_income");*/

        return header;

    }






    @GetMapping("/dwCustomerDetailsExcelTemplet")
    public void dwExpenseUploadRegFinExcelTemplet(HttpServletResponse response) throws IOException, IOException {

        Locale locale = Locale.ENGLISH;
        List<String> headerList = getExpenseUploadRegFinHeaderList(locale);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=CUSTOMER_DETAILS.xlsx");
        ByteArrayInputStream stream = contactListRegFinToExcelFile(headerList);
        IOUtils.copy(stream, response.getOutputStream());
    }

    public static ByteArrayInputStream contactListRegFinToExcelFile(List<String> headerList) {
        try {

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("CUSTOMER_DETAILS");

                Row row = sheet.createRow(0);
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
                headerCellStyle.setFillPattern(FillPatternType.DIAMONDS);

                for (int i = 0; i < headerList.size(); i++) {
                    // Creating header
                    Cell cell = row.createCell(i);
                    cell.setCellValue(headerList.get(i).toString());
                    cell.setCellStyle(headerCellStyle);

                }

                // Making size of column auto resize to fit with data
                for (int i = 0; i < headerList.size(); i++) {
                    sheet.autoSizeColumn(i);
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                return new ByteArrayInputStream(outputStream.toByteArray());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    List<String> getExpenseUploadRegFinHeaderList(Locale locale) {
        List<String> header = new ArrayList<String>();

        header.add("primary_borrower_type");
        header.add("first_name");
        header.add("current_address");
        header.add("current_state");
        header.add("customer_category");
        header.add("client_customer_id");
        header.add("title");
        header.add("last_name");
        header.add("gender");
        header.add("mobile_number");
        header.add("place_of_birth");
        header.add("current_city");
        header.add("current_district");
        header.add("current_pincode");
        header.add("residence_type_current_address");
        header.add("permanent_district");
        header.add("permanent_pincode");
        header.add("residence_type_permanent_address");
        header.add("religion");
        header.add("caste");
        header.add("occupation");
        header.add("income_source");
        header.add("property_ownership_flag");
        header.add("udyam_number");
        header.add("name_of_bureau");
        header.add("bureau_score");
        header.add("product_id");
        header.add("application_id");
        header.add("category");
        header.add("sub_category");
        header.add("purpose");
        header.add("disbursement_type");
        header.add("number_of_tranches");
        header.add("tenure");
        header.add("number_of_repayments");
        header.add("psl_description");
        header.add("impacted_industry_description");
        header.add("applicant_never_in_30_plus_sma_sub_last_12m");
        header.add("tax_slab");
        header.add("psl_msme_type_of_enterprise");
        header.add("current_address_line1");
        header.add("cersai_security_int_id");
        header.add("repayment_frequency");
        header.add("father_first_name");
        header.add("father_last_name");
        header.add("total_value_of_security");
        header.add("tenure_frequency");
        header.add("marital_status");
        header.add("ckyc_id");
        header.add("date_of_birth");
        header.add("first_repayment_date");
        header.add("itr_filed_date_current_fy");
        header.add("application_date");
        header.add("fcu_done_date");
        header.add("e_verification_of_kyc_done_date");
        header.add("pd_done_date");
        header.add("search_with_registrar_of_companies_obtained_date");
        header.add("cersai_registration_reference_number_verification_date");
        header.add("cibil_report_done_date");
        header.add("repayment_period_end_date");
        header.add("date_of_sanction_by_nbfc");
        header.add("date_of_documentation_by_nbfc");
        header.add("annual_income");
        header.add("principal_amount");
        header.add("interest_rate");
        header.add("loan_emi");
        header.add("applicant_age_at_maturity");
        header.add("Business.name_of_business");
        header.add("Business.nature_of_business");
        header.add("Business.type_of_constitution");
        header.add("Business.registration_date");
        header.add("Business.incorporation_date");
        header.add("Business.industry_type");
        header.add("Business.sector_type");
        header.add("Business.sub_sector_type");
        header.add("Business.business_vintage");
        header.add("Business.business_registered_office_address");
        header.add("Business.business_registered_office_city");
        header.add("Business.business_registered_office_state");
        header.add("Business.business_registered_office_pincode");
        header.add("Business.business_mailing_office_address");
        header.add("Business.String business_mailing_office_city");
        header.add("Business.business_mailing_office_pincode");
        header.add("Business.business_phone_number");
        header.add("Business.business_email_id");
        header.add("Business.property_ownership_flag");
        header.add("Business.business_pan_number");
        header.add("Business.business_rc_number");
        header.add("Business.business_udhyam_regn_number");
        header.add("Business.business_mailing_office_address1");
        header.add("Business.business_mailing_office_address2");
        header.add("Business.business_mailing_office_address3");
        header.add("Business.enterprise_activity");
        header.add("Business.customer_type");
        header.add("Business.exisiting_shareholding_of_promoters");
        header.add("Business.exisiting_shareholding_of_mutual_funds");
        header.add("Business.exisiting_shareholding_of_financial_institutions_banks");
        header.add("Business.exisiting_shareholding_of_others");
        header.add("Business.exisiting_shareholding_total");
        header.add("Business.proposed_shareholding_of_promoters");
        header.add("Business.proposed_shareholding_of_mutual_funds");
        header.add("Business.proposed_shareholding_of_financial_institutions_banks");
        header.add("Business.proposed_shareholding_of_others");
        header.add("Business.proposed_shareholding_total");
        header.add("Business.annual_income");
        header.add("Business.balance_sheet_year");
        header.add("Business.ckyc_id");


        return header;
    }



}
