package com.sbicolending.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbicolending.dao.CustomerDetailUpdateDao;
import com.sbicolending.exception.SystemException;
import com.sbicolending.model.CommonResponseModel;
import com.sbicolending.model.updatecustomerdetailsrequest.CustomerDetailUpdateBusinessModel;
import com.sbicolending.model.updatecustomerdetailsrequest.CustomerDetailUpdateModel;
import com.sbicolending.service.CustomerDetailUpdateService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerDetailUpdateServiceIMPL  implements CustomerDetailUpdateService {


    @Autowired
    private CustomerDetailUpdateDao customerDetailUpdateDao;


    @Override
    public CommonResponseModel updateCustomerDetailsData(String lanSp, MultipartFile multipartFile, List<String> header) throws SystemException {


        CommonResponseModel commonResponseModel = new CommonResponseModel();
        InputStream inputStream;
        boolean checkheader = false;

        try {

            inputStream = new ByteArrayInputStream(multipartFile.getBytes());

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();

            for (int i = 0; i < 1; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < header.size(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    if (cell.getCellType() == CellType.BLANK) {
                        throw new SystemException("1111","Cell is empty");

                    } else {
                        if (cell.getStringCellValue().equals(header.get(j).toString())) {
                            checkheader = true;
                        } else {
                            checkheader = false;
                            throw new SystemException("1111","Used Header Name " + header.get(j).toString() + " at index"+j);
                        }
                    }
                }
            }

            if (checkheader) {
                commonResponseModel = validExcelCustomerDetailsUpload(sheet, header, lanSp);
                return commonResponseModel;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SystemException se) {
            se.printStackTrace();
            throw se;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e.getMessage());
        }
        return commonResponseModel;
    }


    private CommonResponseModel validExcelCustomerDetailsUpload(Sheet sheet, List<String> header,String lanSp) {

        CommonResponseModel commonResponseModel = new CommonResponseModel();
        List<CustomerDetailUpdateModel> customerDetailUpdateDataList = new ArrayList<>();
        CustomerDetailUpdateModel customerDetailUpdateData = new CustomerDetailUpdateModel();
        CustomerDetailUpdateBusinessModel customerDetailUpdateBusinessData = new CustomerDetailUpdateBusinessModel();


        try {

             String primary_borrower_type = null;
             String first_name = null;
             String current_address = null;
             String current_state = null;
             String customer_category =null;
             String client_customer_id = null;
             String title = null;
             String last_name = null;
             String gender = null;
             String mobile_number = null;
             String place_of_birth = null;
             String current_city = null;
             String current_district = null;
             int current_pincode = 0;
             String residence_type_current_address = null;
             String permanent_district = null;
             int permanent_pincode = 0;
             String residence_type_permanent_address = null;
             String religion = null;
             String caste = null;
             String occupation = null;
             String income_source = null;
             String property_ownership_flag = null;
             String udyam_number = null;
             String name_of_bureau = null;
             int bureau_score = 0;
             String product_id = null;
             String application_id = null;
             String category = null;
             String sub_category = null;
             String purpose = null;
             String disbursement_type = null;
             int number_of_tranches = 0;
             int tenure = 0;
             int number_of_repayments = 0;
             String psl_description = null;
             String impacted_industry_description = null;
             String applicant_never_in_30_plus_sma_sub_last_12m = null;
             String tax_slab = null;
             String psl_msme_type_of_enterprise = null;
             String current_address_line1 = null;
             String cersai_security_int_id = null;
             String repayment_frequency = null;
             String father_first_name = null;
             String father_last_name = null;
             String total_value_of_security = null;
             String tenure_frequency = null;
             String marital_status = null;
             String ckyc_id = null;
             String date_of_birth = null;
             String first_repayment_date = null;
             String itr_filed_date_current_fy = null;
             String application_date = null;
             String fcu_done_date = null;
             String e_verification_of_kyc_done_date = null;
             String pd_done_date = null;
             String search_with_registrar_of_companies_obtained_date = null;
             String cersai_registration_reference_number_verification_date = null;
             String cibil_report_done_date = null;
             String repayment_period_end_date = null;
             String date_of_sanction_by_nbfc = null;
             String date_of_documentation_by_nbfc = null;
             float annual_income = 0f;
             float principal_amount = 0f;
             float interest_rate = 0f;
             float loan_emi = 0f;
             float applicant_age_at_maturity = 0f;
             //========Business=======
             String name_of_business=null;
             String nature_of_business=null;
             String type_of_constitution=null;
             String registration_date=null;
             String incorporation_date=null;
             String industry_type=null;
             String sector_type=null;
             String sub_sector_type=null;
             int business_vintage = 0;
             String business_registered_office_address = null;
             String business_registered_office_city = null;
             String business_registered_office_state = null;
             int business_registered_office_pincode = 0;
             String business_mailing_office_address = null;
             String business_mailing_office_city = null;
             int business_mailing_office_pincode = 0;
             Long  business_phone_number = 0l;
             String business_email_id = null;
             String business_property_ownership_flag = null;//(Repeat)
             String business_pan_number = null;
             String business_rc_number = null;
             String business_udhyam_regn_number = null;
             String business_mailing_office_address1 = null;
             String business_mailing_office_address2 = null;
             String business_mailing_office_address3 = null;
             String enterprise_activity = null;
             String customer_type = null;
             float exisiting_shareholding_of_promoters = 0f;
             float exisiting_shareholding_of_mutual_funds = 0f;
             float exisiting_shareholding_of_financial_institutions_banks = 0f;
             float exisiting_shareholding_of_others = 0f;
             float exisiting_shareholding_total = 0f;
             float proposed_shareholding_of_promoters = 0f;
             float proposed_shareholding_of_mutual_funds = 0f;
             float proposed_shareholding_of_financial_institutions_banks = 0f;
             float proposed_shareholding_of_others = 0f;
             float proposed_shareholding_total = 0f;
             int business_annual_income = 0;//(repeate)
             String balance_sheet_year = null;
             String business_ckyc_id = null;//(repeate)
            //========Guarantors=======
             /*String date_of_birth = null;
             String gender= null;
             String current_address= null;
             String current_district= null;
             String current_city= null;
             String current_state= null;
             int current_pincode= 0;
             String permanent_district= null;
             int permanent_pincode= 0;
             Long mobile_number= null;
             String marital_status= null;
             String religion= null;
             int bureau_score= 0;
             int age_at_maturity= 0;
             String employment_details_designation= null;
             String caste= null;
             String father_first_name= null;
             String father_last_name= null;
             String occupation= null;
             String place_of_birth= null;
             String title= null;
             String income_source= null;
             String current_address_line1= null;
             String education;
             float other_than_agricultural_income= 0f;
             float total_net_worth= 0f;
             String customer_type= null;
             String ckyc_id= null;
             String annual_income= null;*/








            int rownum = sheet.getPhysicalNumberOfRows();
            if (rownum == 1) {
                throw new SystemException("1111","empty excel file");
            }


            for (int i = 1; i < rownum; i++) {

                CustomerDetailUpdateModel customerDetailUpdate = new CustomerDetailUpdateModel();

                Row row = sheet.getRow(i);

                if (row == null) {
                    throw new SystemException("1111","Empty Cell Please Check the file Row Number " + i);

                }

                    for (int j = 0; j < header.size(); j++) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                     try {
                        switch (cell.getCellType()) {

                            case ERROR:
                                throw new SystemException("1111","Error Cell Please Check the file.");


                            case BLANK:

                                if (j == 0)
                                    throw new SystemException("1111","Empty Cell primary_borrower_type Please Check the file Column Number " + j);

                                else if (j == 1)
                                    throw new SystemException("1111","Empty Cell first_name Please Check the file Column Number " + j);

                                else if (j == 2)
                                    throw new SystemException("1111","Empty Cell current_address Please Check the file Column Number " + j);

                                else if (j == 3)
                                    throw new SystemException("1111","Empty Cell current_state Please Check the file Column Number " + j);

                                else if (j == 4)
                                    throw new SystemException("1111","Empty Cell customer_category Please Check the file Column Number " + j);

                                else if (j == 5)
                                    throw new SystemException("1111","Empty Cell client_customer_id Please Check the file Column Number " + j);

                                else if (j == 6)
                                    throw new SystemException("1111","Empty Cell title Please Check the file Column Number " + j);

                                else if (j == 7)
                                    throw new SystemException("1111","Empty Cell last_name Please Check the file Column Number " + j);

                                else if (j == 8)
                                    throw new SystemException("1111","Empty Cell gender Please Check the file Column Number " + j);

                                else if (j == 9)
                                    throw new SystemException("1111","Empty Cell mobile_number Please Check the file Column Number " + j);

                                else if (j == 10)
                                    throw new SystemException("1111","Empty Cell place_of_birth Please Check the file Column Number " + j);

                                else if (j == 11)
                                    throw new SystemException("1111","Empty Cell current_city Please Check the file Column Number " + j);

                                else if (j == 12)
                                    throw new SystemException("1111","Empty Cell current_district Please Check the file Column Number " + j);

                                else if (j == 13)
                                    throw new SystemException("1111","Empty Cell current_pincode Please Check the file Column Number " + j);

                                else if (j == 14)
                                    throw new SystemException("1111","Empty Cell residence_type_current_address Please Check the file Column Number " + j);

                                else if (j == 15)
                                    throw new SystemException("1111","Empty Cell permanent_district Please Check the file Column Number " + j);

                                else if (j == 16)
                                    throw new SystemException("1111","Empty Cell permanent_pincode Please Check the file Column Number " + j);

                                else if (j == 17)
                                    throw new SystemException("1111","Empty Cell residence_type_permanent_address Please Check the file Column Number " + j);

                                else if (j == 18)
                                    throw new SystemException("1111","Empty Cell religion Please Check the file Column Number " + j);

                                else if (j == 19)
                                   throw new SystemException("1111","Empty Cell caste Please Check the file Column Number " + j);

                                else if (j == 20)
                                    throw new SystemException("1111","Empty Cell occupation Please Check the file Column Number " + j);

                                else if (j == 21)
                                     throw new SystemException("1111","Empty Cell income_source Please Check the file Column Number " + j);

                                else if (j == 22)
                                     throw new SystemException("1111","Empty Cell property_ownership_flag Please Check the file Column Number " + j);

                                else if (j == 23)
                                     throw new SystemException("1111","Empty Cell udyam_number Please Check the file Column Number " + j);

                                else if (j == 24)
                                     throw new SystemException("1111","Empty Cell name_of_bureau Please Check the file Column Number " + j);

                                else if (j == 25)
                                     throw new SystemException("1111","Empty Cell bureau_score Please Check the file Column Number " + j);

                                else if (j == 26)
                                     throw new SystemException("1111","Empty Cell product_id Please Check the file Column Number " + j);

                                else if (j == 27)
                                     throw new SystemException("1111","Empty Cell application_id Please Check the file Column Number " + j);

                                else if (j == 28)
                                     throw new SystemException("1111","Empty Cell category Please Check the file Column Number " + j);

                                else if (j == 29)
                                     throw new SystemException("1111","Empty Cell sub_category Please Check the file Column Number " + j);

                                else if (j == 30)
                                     throw new SystemException("1111","Empty Cell purpose Please Check the file Column Number " + j);

                                else if (j == 31)
                                     throw new SystemException("1111","Empty Cell disbursement_type Please Check the file Column Number " + j);

                                else if (j == 32)
                                     throw new SystemException("1111","Empty Cell number_of_tranches Please Check the file Column Number " + j);

                                else if (j == 33)
                                     throw new SystemException("1111","Empty Cell tenure Please Check the file Column Number " + j);

                                else if (j == 34)
                                     throw new SystemException("1111","Empty Cell number_of_repayments Please Check the file Column Number " + j);

                                else if (j == 35)
                                     throw new SystemException("1111","Empty Cell psl_description Please Check the file Column Number " + j);

                                else if (j == 36)
                                     throw new SystemException("1111","Empty Cell impacted_industry_description Please Check the file Column Number " + j);

                                else if (j == 37)
                                     throw new SystemException("1111","Empty Cell applicant_never_in_30_plus_sma_sub_last_12m Please Check the file Column Number " + j);

                                else if (j == 38)
                                     throw new SystemException("1111","Empty Cell tax_slab Please Check the file Column Number " + j);

                                else if (j == 39)
                                     throw new SystemException("1111","Empty Cell psl_msme_type_of_enterprise Please Check the file Column Number " + j);

                                else if (j == 40)
                                     throw new SystemException("1111","Empty Cell current_address_line1 Please Check the file Column Number " + j);

                                else if (j == 41)
                                     throw new SystemException("1111","Empty Cell cersai_security_int_id Please Check the file Column Number " + j);

                                else if (j == 42)
                                     throw new SystemException("1111","Empty Cell repayment_frequency Please Check the file Column Number " + j);

                                else if (j == 43)
                                     throw new SystemException("1111","Empty Cell father_first_name Please Check the file Column Number " + j);

                                else if (j == 44)
                                     throw new SystemException("1111","Empty Cell father_last_name Please Check the file Column Number " + j);

                                else if (j == 45)
                                     throw new SystemException("1111","Empty Cell total_value_of_security Please Check the file Column Number " + j);

                                else if (j == 46)
                                     throw new SystemException("1111","Empty Cell tenure_frequency Please Check the file Column Number " + j);

                                else if (j == 47)
                                     throw new SystemException("1111","Empty Cell marital_status Please Check the file Column Number " + j);

                                else if (j == 48)
                                     throw new SystemException("1111","Empty Cell ckyc_id Please Check the file Column Number " + j);

                                else if (j == 49)
                                     throw new SystemException("1111","Empty Cell date_of_birth Please Check the file Column Number " + j);

                                else if (j == 50)
                                     throw new SystemException("1111","Empty Cell first_repayment_date Please Check the file Column Number " + j);

                                else if (j == 51)
                                     throw new SystemException("1111","Empty Cell itr_filed_date_current_fy Please Check the file Column Number " + j);

                                else if (j == 52)
                                     throw new SystemException("1111","Empty Cell application_date Please Check the file Column Number " + j);

                                else if (j == 53)
                                     throw new SystemException("1111","Empty Cell fcu_done_date Please Check the file Column Number " + j);

                                else if (j == 54)
                                     throw new SystemException("1111","Empty Cell e_verification_of_kyc_done_date Please Check the file Column Number " + j);

                                else if (j == 55)
                                     throw new SystemException("1111","Empty Cell pd_done_date Please Check the file Column Number " + j);

                                else if (j == 56)
                                     throw new SystemException("1111","Empty Cell search_with_registrar_of_companies_obtained_date Please Check the file Column Number " + j);

                                else if (j == 57)
                                     throw new SystemException("1111","Empty Cell cersai_registration_reference_number_verification_date Please Check the file Column Number " + j);

                                else if (j == 58)
                                     throw new SystemException("1111","Empty Cell cibil_report_done_date Please Check the file Column Number " + j);

                                else if (j == 59)
                                     throw new SystemException("1111","Empty Cell repayment_period_end_date Please Check the file Column Number " + j);

                                else if (j == 60)
                                     throw new SystemException("1111","Empty Cell date_of_sanction_by_nbfc Please Check the file Column Number " + j);

                                else if (j == 61)
                                     throw new SystemException("1111","Empty Cell date_of_documentation_by_nbfc Please Check the file Column Number " + j);

                                else if (j == 62)
                                     throw new SystemException("1111","Empty Cell annual_income Please Check the file Column Number " + j);

                                else if (j == 63)
                                     throw new SystemException("1111","Empty Cell principal_amount Please Check the file Column Number " + j);

                                else if (j == 64)
                                     throw new SystemException("1111","Empty Cell interest_rate Please Check the file Column Number " + j);

                                else if (j == 65)
                                     throw new SystemException("1111","Empty Cell loan_emi Please Check the file Column Number " + j);

                                else if (j == 66)
                                     throw new SystemException("1111","Empty Cell applicant_age_at_maturity Please Check the file Column Number " + j);


                                //==================Business===========
                                else if (j == 67)
                                    throw new SystemException("1111","Empty Cell Business.name_of_business Please Check the file Column Number " + j);

                                else if (j == 68)
                                    throw new SystemException("1111","Empty Cell Business.nature_of_business Please Check the file Column Number " + j);

                                else if (j == 69)
                                    throw new SystemException("1111","Empty Cell Business.type_of_constitution Please Check the file Column Number " + j);

                                else if (j == 70)
                                    throw new SystemException("1111","Empty Cell Business.registration_date Please Check the file Column Number " + j);

                                else if (j == 71)
                                    throw new SystemException("1111","Empty Cell Business.incorporation_date Please Check the file Column Number " + j);

                                else if (j == 72)
                                    throw new SystemException("1111","Empty Cell Business.industry_type Please Check the file Column Number " + j);

                                else if (j == 73)
                                    throw new SystemException("1111","Empty Cell Business.sector_type Please Check the file Column Number " + j);

                                else if (j == 74)
                                    throw new SystemException("1111","Empty Cell Business.sub_sector_type Please Check the file Column Number " + j);

                                else if (j == 75)
                                    throw new SystemException("1111","Empty Cell Business.business_vintage Please Check the file Column Number " + j);

                                else if (j == 76)
                                    throw new SystemException("1111","Empty Cell Business.business_registered_office_address Please Check the file Column Number " + j);

                                else if (j == 77)
                                    throw new SystemException("1111","Empty Cell Business.business_registered_office_city Please Check the file Column Number " + j);

                                else if (j == 78)
                                    throw new SystemException("1111","Empty Cell Business.business_registered_office_state Please Check the file Column Number " + j);

                                else if (j == 79)
                                    throw new SystemException("1111","Empty Cell Business.business_registered_office_pincode Please Check the file Column Number " + j);

                                else if (j == 80)
                                    throw new SystemException("1111","Empty Cell Business.business_mailing_office_address Please Check the file Column Number " + j);

                                else if (j == 81)
                                    throw new SystemException("1111","Empty Cell Business.business_mailing_office_city Please Check the file Column Number " + j);

                                else if (j == 82)
                                    throw new SystemException("1111","Empty Cell Business.business_mailing_office_pincode Please Check the file Column Number " + j);

                                else if (j == 83)
                                throw new SystemException("1111","Empty Cell Business.business_mailing_office_pincode Please Check the file Column Number " + j);

                                else if (j == 84)
                                   throw new SystemException("1111","Empty Cell Business.business_email_id Please Check the file Column Number " + j);

                                else if (j == 85)
                                    throw new SystemException("1111","Empty Cell Business.business_property_ownership_flag Please Check the file Column Number " + j);

                                else if (j == 86)
                                    throw new SystemException("1111","Empty Cell Business.business_pan_number Please Check the file Column Number " + j);

                                else if (j == 87)
                                    throw new SystemException("1111","Empty Cell Business.business_rc_number Please Check the file Column Number " + j);

                                else if (j == 88)
                                    throw new SystemException("1111","Empty Cell Business.business_udhyam_regn_number Please Check the file Column Number " + j);

                                else if (j == 89)
                                    throw new SystemException("1111","Empty Cell Business.business_mailing_office_address1 Please Check the file Column Number " + j);

                                else if (j == 90)
                                    throw new SystemException("1111","Empty Cell Business.business_mailing_office_address2 Please Check the file Column Number " + j);

                                else if (j == 91)
                                    throw new SystemException("1111","Empty Cell Business.business_mailing_office_address3 Please Check the file Column Number " + j);

                                else if (j == 92)
                                    throw new SystemException("1111","Empty Cell Business.enterprise_activity Please Check the file Column Number " + j);

                                else if (j == 93)
                                    throw new SystemException("1111","Empty Cell Business.customer_type Please Check the file Column Number " + j);

                                else if (j == 94)
                                    throw new SystemException("1111","Empty Cell Business.exisiting_shareholding_of_promoters Please Check the file Column Number " + j);

                                else if (j == 95)
                                    throw new SystemException("1111","Empty Cell Business.exisiting_shareholding_of_mutual_funds Please Check the file Column Number " + j);

                                else if (j == 96)
                                    throw new SystemException("1111","Empty Cell Business.exisiting_shareholding_of_financial_institutions_banks Please Check the file Column Number " + j);

                                else if (j == 97)
                                    throw new SystemException("1111","Empty Cell Business.exisiting_shareholding_of_others Please Check the file Column Number " + j);

                                else if (j == 98)
                                     throw new SystemException("1111","Empty Cell Business.exisiting_shareholding_total Please Check the file Column Number " + j);

                                else if (j == 99)
                                     throw new SystemException("1111","Empty Cell Business.proposed_shareholding_of_promoters Please Check the file Column Number " + j);

                                else if (j == 100)
                                     throw new SystemException("1111","Empty Cell Business.proposed_shareholding_of_mutual_funds Please Check the file Column Number " + j);

                                else if (j == 101)
                                     throw new SystemException("1111","Empty Cell Business.proposed_shareholding_of_financial_institutions_banks Please Check the file Column Number " + j);

                                else if (j == 102)
                                     throw new SystemException("1111","Empty Cell Business.proposed_shareholding_of_others Please Check the file Column Number " + j);

                                else if (j == 103)
                                     throw new SystemException("1111","Empty Cell Business.proposed_shareholding_total Please Check the file Column Number " + j);

                                else if (j == 104)
                                     throw new SystemException("1111","Empty Cell Business.business_annual_income Please Check the file Column Number " + j);

                                else if (j == 105)
                                     throw new SystemException("1111","Empty Cell Business.balance_sheet_year Please Check the file Column Number " + j);

                                else if (j == 106)
                                    throw new SystemException("1111","Empty Cell Business.business_ckyc_id Please Check the file Column Number " + j);


                                break;

                            case NUMERIC:

                                if (j == 0)
                                    primary_borrower_type = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 1)
                                    first_name = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 2)
                                    current_address = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 3)
                                    current_state = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 4)
                                    customer_category = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 5)
                                    client_customer_id = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 6)
                                    title = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 7)
                                    last_name = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 8)
                                    gender = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 9)
                                    mobile_number = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 10)
                                    place_of_birth = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 11)
                                    current_city = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 12)
                                    current_district = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 13)
                                    current_pincode = (int) cell.getNumericCellValue();

                                else if (j == 14)
                                    residence_type_current_address = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 15)
                                    permanent_district = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 16)
                                    permanent_pincode = (int) cell.getNumericCellValue();

                                else if (j == 17)
                                    residence_type_permanent_address = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 18)
                                    religion = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 19)
                                    caste = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 20)
                                    occupation = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 21)
                                    income_source = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 22)
                                    property_ownership_flag = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 23)
                                    udyam_number = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 24)
                                    name_of_bureau = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 25)
                                    bureau_score = (int) cell.getNumericCellValue();

                                else if (j == 26)
                                    product_id = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 27)
                                    application_id = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 28)
                                    category = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 29)
                                    sub_category = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 30)
                                    purpose = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 31)
                                    disbursement_type = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 32)
                                    number_of_tranches = (int) cell.getNumericCellValue();

                                else if (j == 33)
                                    tenure = (int) cell.getNumericCellValue();

                                else if (j == 34)
                                    number_of_repayments = (int) cell.getNumericCellValue();

                                else if (j == 35)
                                    psl_description = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 36)
                                    impacted_industry_description = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 37)
                                    applicant_never_in_30_plus_sma_sub_last_12m = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 38)
                                    tax_slab = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 39)
                                    psl_msme_type_of_enterprise = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 40)
                                    current_address_line1 = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 41)
                                    cersai_security_int_id = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 42)
                                    repayment_frequency = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 43)
                                    father_first_name = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 44)
                                    father_last_name = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 45)
                                    total_value_of_security = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 46)
                                    tenure_frequency = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 47)
                                    marital_status = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 48)
                                    ckyc_id = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 49)
                                    date_of_birth =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 50)
                                    first_repayment_date =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 51)
                                    itr_filed_date_current_fy =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 52)
                                    application_date =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 53)
                                     fcu_done_date =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 54)
                                     e_verification_of_kyc_done_date =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 55)
                                     pd_done_date =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 56)
                                     search_with_registrar_of_companies_obtained_date =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 57)
                                     cersai_registration_reference_number_verification_date = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 58)
                                     cibil_report_done_date =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 59)
                                     repayment_period_end_date = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 60)
                                     date_of_sanction_by_nbfc = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 61)
                                     date_of_documentation_by_nbfc = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 62)
                                     annual_income = (float) cell.getNumericCellValue();

                                else if (j == 63)
                                     principal_amount = (float) cell.getNumericCellValue();

                                else if (j == 64)
                                     interest_rate = (float) cell.getNumericCellValue();

                                else if (j == 65)
                                     loan_emi = (float) cell.getNumericCellValue();

                                else if (j == 66)
                                     applicant_age_at_maturity = (float) cell.getNumericCellValue();

                                //Business==========
                                else if (j == 67)
                                name_of_business =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 68)
                                nature_of_business =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 69)
                                type_of_constitution = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 70)
                                registration_date = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 71)
                                incorporation_date = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 72)
                                industry_type = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 73)
                                sector_type = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 74)
                                sub_sector_type = NumberToTextConverter.toText(cell.getNumericCellValue());


                                else if (j == 75)
                                business_vintage =  (int) cell.getNumericCellValue();

                                else if (j == 76)
                                business_registered_office_address =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 77)
                                business_registered_office_city =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 78)
                                business_registered_office_state =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 79)
                                business_registered_office_pincode =  (int) cell.getNumericCellValue();

                                else if (j == 80)
                                business_mailing_office_address =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 81)
                                business_mailing_office_city =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 82)
                                business_mailing_office_pincode = (int) cell.getNumericCellValue();

                                else if (j == 83)
                                business_phone_number = (long)  cell.getNumericCellValue();

                                else if (j == 84)
                                business_email_id = NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 85)
                                business_property_ownership_flag =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 86)
                                business_pan_number =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 87)
                                business_rc_number =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 88)
                                business_udhyam_regn_number =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 89)
                                business_mailing_office_address1 =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 90)
                                business_mailing_office_address2 =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 91)
                                business_mailing_office_address3 =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 92)
                                enterprise_activity =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 93)
                                customer_type =   NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 94)
                                exisiting_shareholding_of_promoters =  (float) cell.getNumericCellValue();

                                else if (j == 95)
                                exisiting_shareholding_of_mutual_funds = (float) cell.getNumericCellValue();

                                else if (j == 96)
                                exisiting_shareholding_of_financial_institutions_banks = (float) cell.getNumericCellValue();

                                else if (j == 97)
                                exisiting_shareholding_of_others =  (float) cell.getNumericCellValue();

                                else if (j == 98)
                                exisiting_shareholding_total =  (float) cell.getNumericCellValue();

                                else if (j == 99)
                                proposed_shareholding_of_promoters =  (float) cell.getNumericCellValue();

                                else if (j == 100)
                                proposed_shareholding_of_mutual_funds =  (float) cell.getNumericCellValue();

                                else if (j == 101)
                                proposed_shareholding_of_financial_institutions_banks =   (float) cell.getNumericCellValue();

                                else if (j == 102)
                                proposed_shareholding_of_others =   (float) cell.getNumericCellValue();

                                else if (j == 103)
                                proposed_shareholding_total = (float) cell.getNumericCellValue();

                                else if (j == 104)
                                business_annual_income = (int) cell.getNumericCellValue();

                                else if (j == 105)
                                balance_sheet_year =  NumberToTextConverter.toText(cell.getNumericCellValue());

                                else if (j == 106)
                                business_ckyc_id = NumberToTextConverter.toText(cell.getNumericCellValue());


                                break;

                            case STRING:

                                if (j == 0) {
                                    primary_borrower_type = cell.getStringCellValue();

                                } else if (j == 1) {
                                    first_name = cell.getStringCellValue();

                                } else if (j == 2) {
                                    current_address = cell.getStringCellValue();

                                } else if (j == 3) {
                                    current_state = cell.getStringCellValue();

                                }else if (j == 4)
                                    customer_category = cell.getStringCellValue();

                                else if (j == 5)
                                    client_customer_id = cell.getStringCellValue();

                                else if (j == 6)
                                    title = cell.getStringCellValue();

                                else if (j == 7)
                                    last_name = cell.getStringCellValue();

                                else if (j == 8)
                                    gender = cell.getStringCellValue();

                                else if (j == 9)
                                    mobile_number = cell.getStringCellValue();

                                else if (j == 10)
                                    place_of_birth = cell.getStringCellValue();

                                else if (j == 11)
                                    current_city = cell.getStringCellValue();

                                else if (j == 12)
                                    current_district = cell.getStringCellValue();

                                else if (j == 13)
                                    current_pincode = Integer.parseInt(cell.getStringCellValue());

                                else if (j == 14)
                                    residence_type_current_address = cell.getStringCellValue();

                                else if (j == 15)
                                    permanent_district = cell.getStringCellValue();

                                else if (j == 16)
                                    permanent_pincode = Integer.parseInt(cell.getStringCellValue());

                                else if (j == 17)
                                    residence_type_permanent_address = cell.getStringCellValue();

                                else if (j == 18)
                                    religion = cell.getStringCellValue();

                                else if (j == 19)
                                    caste = cell.getStringCellValue();

                                else if (j == 20)
                                    occupation = cell.getStringCellValue();

                                else if (j == 21)
                                    income_source = cell.getStringCellValue();

                                else if (j == 22)
                                    property_ownership_flag = cell.getStringCellValue();

                                else if (j == 23)
                                    udyam_number = cell.getStringCellValue();

                                else if (j == 24)
                                    name_of_bureau = cell.getStringCellValue();

                                else if (j == 25)
                                    bureau_score = Integer.parseInt(cell.getStringCellValue());

                                else if (j == 26)
                                    product_id = cell.getStringCellValue();

                                else if (j == 27)
                                    application_id = cell.getStringCellValue();

                                else if (j == 28)
                                    category = cell.getStringCellValue();

                                else if (j == 29)
                                    sub_category = cell.getStringCellValue();

                                else if (j == 30)
                                    purpose = cell.getStringCellValue();

                                else if (j == 31)
                                    disbursement_type = cell.getStringCellValue();

                                else if (j == 32)
                                    number_of_tranches = Integer.parseInt(cell.getStringCellValue());

                                else if (j == 33)
                                    tenure = Integer.parseInt(cell.getStringCellValue());

                                else if (j == 34)
                                    number_of_repayments = Integer.parseInt(cell.getStringCellValue());

                                else if (j == 35)
                                    psl_description = cell.getStringCellValue();

                                else if (j == 36)
                                    impacted_industry_description = cell.getStringCellValue();

                                else if (j == 37)
                                    applicant_never_in_30_plus_sma_sub_last_12m = cell.getStringCellValue();

                                else if (j == 38)
                                    tax_slab = cell.getStringCellValue();

                                else if (j == 39)
                                    psl_msme_type_of_enterprise = cell.getStringCellValue();

                                else if (j == 40)
                                    current_address_line1 = cell.getStringCellValue();

                                else if (j == 41)
                                    cersai_security_int_id = cell.getStringCellValue();

                                else if (j == 42)
                                    repayment_frequency = cell.getStringCellValue();

                                else if (j == 43)
                                    father_first_name = cell.getStringCellValue();

                                else if (j == 44)
                                    father_last_name = cell.getStringCellValue();

                                else if (j == 45)
                                    total_value_of_security = cell.getStringCellValue();

                                else if (j == 46)
                                    tenure_frequency = cell.getStringCellValue();

                                else if (j == 47)
                                    marital_status = cell.getStringCellValue();

                                else if (j == 48)
                                    ckyc_id = cell.getStringCellValue();

                                else if (j == 49)

                                    date_of_birth =  cell.getStringCellValue();

                                else if (j == 50)
                                    first_repayment_date =  cell.getStringCellValue();

                                else if (j == 51)
                                    itr_filed_date_current_fy = cell.getStringCellValue();

                                else if (j == 52)
                                    application_date =cell.getStringCellValue();

                                else if (j == 53)
                                    fcu_done_date =cell.getStringCellValue();

                                else if (j == 54)
                                    e_verification_of_kyc_done_date =  cell.getStringCellValue();

                                else if (j == 55)
                                    pd_done_date = cell.getStringCellValue();

                                else if (j == 56)
                                    search_with_registrar_of_companies_obtained_date = cell.getStringCellValue();

                                else if (j == 57)
                                    cersai_registration_reference_number_verification_date = cell.getStringCellValue();

                                else if (j == 58)
                                    cibil_report_done_date = cell.getStringCellValue();

                                else if (j == 59)
                                    repayment_period_end_date = cell.getStringCellValue();

                                else if (j == 60)
                                    date_of_sanction_by_nbfc = cell.getStringCellValue();

                                else if (j == 61)
                                    date_of_documentation_by_nbfc = cell.getStringCellValue();

                                else if (j == 62)
                                    annual_income = Float.parseFloat(cell.getStringCellValue());

                                else if (j == 63)
                                    principal_amount =  Float.parseFloat(cell.getStringCellValue());

                                else if (j == 64)
                                    interest_rate =  Float.parseFloat(cell.getStringCellValue());

                                else if (j == 65)
                                    loan_emi = Float.parseFloat(cell.getStringCellValue());

                                else if (j == 66)
                                    applicant_age_at_maturity =  Float.parseFloat(cell.getStringCellValue());

                                //==Business=======

                                else if (j == 67)
                                name_of_business =  cell.getStringCellValue();

                                else if (j == 68)
                                nature_of_business =  cell.getStringCellValue();

                                else if (j == 69)
                                type_of_constitution = cell.getStringCellValue();

                                else if (j == 70)
                                registration_date =  cell.getStringCellValue();

                                else if (j == 71)
                                incorporation_date =  cell.getStringCellValue();

                                else if (j == 72)
                                industry_type =  cell.getStringCellValue();

                                else if (j == 73)
                                sector_type =  cell.getStringCellValue();

                                else if (j == 74)
                                sub_sector_type =  cell.getStringCellValue();

                                else if (j == 75)
                                    business_vintage = Integer.parseInt(cell.getStringCellValue());

                                else if (j == 76)
                                    business_registered_office_address =  cell.getStringCellValue();

                                else if (j == 77)
                                    business_registered_office_city =  cell.getStringCellValue();

                                else if (j == 78)
                                    business_registered_office_state =  cell.getStringCellValue();

                                else if (j == 79)
                                    business_registered_office_pincode =Integer.parseInt(cell.getStringCellValue());

                                else if (j == 80)
                                    business_mailing_office_address = cell.getStringCellValue();

                                else if (j == 81)
                                    business_mailing_office_city = cell.getStringCellValue();

                                else if (j == 82)
                                    business_mailing_office_pincode = Integer.parseInt(cell.getStringCellValue());

                                else if (j == 83)
                                    business_phone_number = Long.parseLong(cell.getStringCellValue());

                                else if (j == 84)
                                    business_email_id =cell.getStringCellValue();

                                else if (j == 85)
                                    business_property_ownership_flag = cell.getStringCellValue();

                                else if (j == 86)
                                    business_pan_number = cell.getStringCellValue();

                                else if (j == 87)
                                    business_rc_number =  cell.getStringCellValue();

                                else if (j == 88)
                                    business_udhyam_regn_number =  cell.getStringCellValue();

                                else if (j == 89)
                                    business_mailing_office_address1 = cell.getStringCellValue();

                                else if (j == 90)
                                    business_mailing_office_address2 =  cell.getStringCellValue();

                                else if (j == 91)
                                    business_mailing_office_address3 =  cell.getStringCellValue();

                                else if (j == 92)
                                    enterprise_activity = cell.getStringCellValue();

                                else if (j == 93)
                                    customer_type = cell.getStringCellValue();

                                else if (j == 94)
                                    exisiting_shareholding_of_promoters = Float.parseFloat(cell.getStringCellValue());

                                else if (j == 95)
                                    exisiting_shareholding_of_mutual_funds = Float.parseFloat(cell.getStringCellValue());

                                else if (j == 96)
                                    exisiting_shareholding_of_financial_institutions_banks = Float.parseFloat(cell.getStringCellValue());

                                else if (j == 97)
                                    exisiting_shareholding_of_others =  Float.parseFloat(cell.getStringCellValue());

                                else if (j == 98)
                                    exisiting_shareholding_total =  Float.parseFloat(cell.getStringCellValue());

                                else if (j == 99)
                                    proposed_shareholding_of_promoters = Float.parseFloat(cell.getStringCellValue());

                                else if (j == 100)
                                    proposed_shareholding_of_mutual_funds =  Float.parseFloat(cell.getStringCellValue());

                                else if (j == 101)
                                    proposed_shareholding_of_financial_institutions_banks =  Float.parseFloat(cell.getStringCellValue());

                                else if (j == 102)
                                    proposed_shareholding_of_others = Float.parseFloat(cell.getStringCellValue());

                                else if (j == 103)
                                    proposed_shareholding_total =  Float.parseFloat(cell.getStringCellValue());

                                else if (j == 104)
                                    business_annual_income =  Integer.parseInt(cell.getStringCellValue());

                                else if (j == 105)
                                    balance_sheet_year = cell.getStringCellValue();

                                else if (j == 106)
                                    business_ckyc_id = cell.getStringCellValue();


                                break;

                            default:
                                break;

                        }
                     } catch (NumberFormatException e) {
                            throw new SystemException("1111","Invalid Record(Unparseable) of Column Name " + header.get(j).toString()+ " at index"+j);
                     }
                    }

                try {

                    customerDetailUpdate.setPrimary_borrower_type(primary_borrower_type);
                    customerDetailUpdate.setFirst_name(first_name);
                    customerDetailUpdate.setCurrent_address(current_address);
                    customerDetailUpdate.setCurrent_state(current_state);
                    customerDetailUpdate.setCustomer_category(customer_category);
                    customerDetailUpdate.setClient_customer_id(client_customer_id);
                    customerDetailUpdate.setTitle(title);
                    customerDetailUpdate.setLast_name(last_name);
                    customerDetailUpdate.setGender(gender);
                    customerDetailUpdate.setMobile_number(mobile_number);
                    customerDetailUpdate.setPlace_of_birth(place_of_birth);
                    customerDetailUpdate.setCurrent_city(current_city);
                    customerDetailUpdate.setCurrent_district(current_district);
                    customerDetailUpdate.setCurrent_pincode(current_pincode);
                    customerDetailUpdate.setResidence_type_current_address(residence_type_current_address);
                    customerDetailUpdate.setPermanent_district(permanent_district);
                    customerDetailUpdate.setPermanent_pincode(permanent_pincode);
                    customerDetailUpdate.setResidence_type_permanent_address(residence_type_permanent_address);
                    customerDetailUpdate.setReligion(religion);
                    customerDetailUpdate.setCaste(caste);
                    customerDetailUpdate.setOccupation(occupation);
                    customerDetailUpdate.setIncome_source(income_source);
                    customerDetailUpdate.setProperty_ownership_flag(property_ownership_flag);
                    customerDetailUpdate.setUdyam_number(udyam_number);
                    customerDetailUpdate.setName_of_bureau(name_of_bureau);
                    customerDetailUpdate.setBureau_score(bureau_score);
                    customerDetailUpdate.setProduct_id(product_id);
                    customerDetailUpdate.setApplication_id(application_id);
                    customerDetailUpdate.setCategory(category);
                    customerDetailUpdate.setSub_category(sub_category);
                    customerDetailUpdate.setPurpose(purpose);
                    customerDetailUpdate.setDisbursement_type(disbursement_type);
                    customerDetailUpdate.setNumber_of_tranches(number_of_tranches);
                    customerDetailUpdate.setTenure(tenure);
                    customerDetailUpdate.setNumber_of_repayments(number_of_repayments);
                    customerDetailUpdate.setPsl_description(psl_description);
                    customerDetailUpdate.setImpacted_industry_description(impacted_industry_description);
                    customerDetailUpdate.setApplicant_never_in_30_plus_sma_sub_last_12m(applicant_never_in_30_plus_sma_sub_last_12m);
                    customerDetailUpdate.setTax_slab(tax_slab);
                    customerDetailUpdate.setPsl_msme_type_of_enterprise(psl_msme_type_of_enterprise);
                    customerDetailUpdate.setCurrent_address_line1(current_address_line1);
                    customerDetailUpdate.setCersai_security_int_id(cersai_security_int_id);
                    customerDetailUpdate.setRepayment_frequency(repayment_frequency);
                    customerDetailUpdate.setFather_first_name(father_first_name);
                    customerDetailUpdate.setFather_last_name(father_last_name);
                    customerDetailUpdate.setTotal_value_of_security(total_value_of_security);
                    customerDetailUpdate.setTenure_frequency(tenure_frequency);
                    customerDetailUpdate.setMarital_status(marital_status);
                    customerDetailUpdate.setCkyc_id(ckyc_id);
                    customerDetailUpdate.setDate_of_birth(date_of_birth);
                    customerDetailUpdate.setFirst_repayment_date(first_repayment_date);
                    customerDetailUpdate.setItr_filed_date_current_fy(itr_filed_date_current_fy);
                    customerDetailUpdate.setApplication_date(application_date);
                    customerDetailUpdate.setFcu_done_date(fcu_done_date);
                    customerDetailUpdate.setE_verification_of_kyc_done_date(e_verification_of_kyc_done_date);
                    customerDetailUpdate.setPd_done_date(pd_done_date);
                    customerDetailUpdate.setSearch_with_registrar_of_companies_obtained_date(search_with_registrar_of_companies_obtained_date);
                    customerDetailUpdate.setCersai_registration_reference_number_verification_date(cersai_registration_reference_number_verification_date);
                    customerDetailUpdate.setCibil_report_done_date(cibil_report_done_date);
                    customerDetailUpdate.setRepayment_period_end_date(repayment_period_end_date);
                    customerDetailUpdate.setDate_of_sanction_by_nbfc(date_of_sanction_by_nbfc);
                    customerDetailUpdate.setDate_of_documentation_by_nbfc(date_of_documentation_by_nbfc);
                    customerDetailUpdate.setAnnual_income(annual_income);
                    customerDetailUpdate.setPrincipal_amount(principal_amount);
                    customerDetailUpdate.setInterest_rate(interest_rate);
                    customerDetailUpdate.setLoan_emi(loan_emi);
                    customerDetailUpdate.setApplicant_age_at_maturity(applicant_age_at_maturity);

                    //==Business==========
                    customerDetailUpdateBusinessData.setName_of_business(name_of_business);
                    customerDetailUpdateBusinessData.setNature_of_business(nature_of_business);
                    customerDetailUpdateBusinessData.setType_of_constitution(type_of_constitution);
                    customerDetailUpdateBusinessData.setRegistration_date(registration_date);
                    customerDetailUpdateBusinessData.setIncorporation_date(incorporation_date);
                    customerDetailUpdateBusinessData.setIndustry_type(industry_type);
                    customerDetailUpdateBusinessData.setSector_type(sector_type);
                    customerDetailUpdateBusinessData.setSub_sector_type(sub_sector_type);


                    customerDetailUpdateBusinessData.setBusiness_vintage(business_vintage);
                    customerDetailUpdateBusinessData.setBusiness_registered_office_address(business_registered_office_address);
                    customerDetailUpdateBusinessData.setBusiness_registered_office_city(business_registered_office_city);
                    customerDetailUpdateBusinessData.setBusiness_registered_office_state(business_registered_office_state);
                    customerDetailUpdateBusinessData.setBusiness_registered_office_pincode(business_registered_office_pincode);
                    customerDetailUpdateBusinessData.setBusiness_mailing_office_address(business_mailing_office_address);
                    customerDetailUpdateBusinessData.setBusiness_mailing_office_city(business_mailing_office_city);
                    customerDetailUpdateBusinessData.setBusiness_mailing_office_pincode(business_mailing_office_pincode);
                    customerDetailUpdateBusinessData.setBusiness_phone_number(Collections.singletonList(10000l));
                    customerDetailUpdateBusinessData.setBusiness_email_id(Collections.singletonList("Abhishek"));
                    customerDetailUpdateBusinessData.setProperty_ownership_flag(business_property_ownership_flag);
                    customerDetailUpdateBusinessData.setBusiness_pan_number(business_pan_number);
                    customerDetailUpdateBusinessData.setBusiness_rc_number(business_rc_number);
                    customerDetailUpdateBusinessData.setBusiness_udhyam_regn_number(business_udhyam_regn_number);
                    customerDetailUpdateBusinessData.setBusiness_mailing_office_address1(business_mailing_office_address1);
                    customerDetailUpdateBusinessData.setBusiness_mailing_office_address2(business_mailing_office_address2);
                    customerDetailUpdateBusinessData.setBusiness_mailing_office_address3(business_mailing_office_address3);
                    customerDetailUpdateBusinessData.setEnterprise_activity(enterprise_activity);
                    customerDetailUpdateBusinessData.setCustomer_type(customer_type);
                    customerDetailUpdateBusinessData.setExisiting_shareholding_of_promoters(exisiting_shareholding_of_promoters);
                    customerDetailUpdateBusinessData.setExisiting_shareholding_of_mutual_funds(exisiting_shareholding_of_mutual_funds);
                    customerDetailUpdateBusinessData.setExisiting_shareholding_of_financial_institutions_banks(exisiting_shareholding_of_financial_institutions_banks);
                    customerDetailUpdateBusinessData.setExisiting_shareholding_of_others(exisiting_shareholding_of_others);
                    customerDetailUpdateBusinessData.setExisiting_shareholding_total(exisiting_shareholding_total);
                    customerDetailUpdateBusinessData.setProposed_shareholding_of_promoters(proposed_shareholding_of_promoters);
                    customerDetailUpdateBusinessData.setProposed_shareholding_of_mutual_funds(proposed_shareholding_of_mutual_funds);
                    customerDetailUpdateBusinessData.setProposed_shareholding_of_financial_institutions_banks(proposed_shareholding_of_financial_institutions_banks);
                    customerDetailUpdateBusinessData.setProposed_shareholding_of_others(proposed_shareholding_of_others);
                    customerDetailUpdateBusinessData.setProposed_shareholding_total(proposed_shareholding_total);
                    customerDetailUpdateBusinessData.setAnnual_income(business_annual_income);
                    customerDetailUpdateBusinessData.setBalance_sheet_year(balance_sheet_year);
                    customerDetailUpdateBusinessData.setCkyc_id(business_ckyc_id);



                    customerDetailUpdate.setBusiness(customerDetailUpdateBusinessData);


                    customerDetailUpdateDataList.add(customerDetailUpdate);
                    customerDetailUpdateData = customerDetailUpdateDataList.get(0);


                    System.out.println("=======Upadtae Data" + customerDetailUpdateData);
                    boolean updateStatus = customerDetailUpdateDao.updateCustomerDetails(customerDetailUpdateData, lanSp);
                    if(updateStatus == true) {
                        commonResponseModel.setErrorCode("0000");
                        commonResponseModel.setErrorMsg("file update successfully ");
                    }else {
                        commonResponseModel.setErrorCode("1111");
                        commonResponseModel.setErrorMsg("file not update successfully ");
                    }

                } catch (Exception e) {
                    throw new SystemException("1111",e.getMessage());
                }
            }

            return commonResponseModel;
        } catch (Exception e) {
            throw new SystemException("1111",e.getMessage());
        }

    }





    }