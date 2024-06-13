package com.sbicolending.dao.impl;

import com.sbicolending.dao.CustomerDetailUpdateDao;
import com.sbicolending.exception.SystemException;
import com.sbicolending.model.updatecustomerdetailsrequest.CustomerDetailUpdateBusinessModel;
import com.sbicolending.model.updatecustomerdetailsrequest.CustomerDetailUpdateModel;
import com.sbicolending.utils.DataTypeConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class CustomerDetailUpdateDaoIMPL implements CustomerDetailUpdateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataTypeConversion dataTypeConversion;


    @Override
    public boolean updateCustomerDetails(CustomerDetailUpdateModel customerDetailUpdateData, String lanSp) {

        boolean status=false;
        try {

            String primary_borrower_type = customerDetailUpdateData.getPrimary_borrower_type();
            String first_name = customerDetailUpdateData.getFirst_name();
            String current_address = customerDetailUpdateData.getCurrent_address();
            String current_state = customerDetailUpdateData.getCurrent_state();
            String customer_category =customerDetailUpdateData.getCustomer_category();
            String client_customer_id = customerDetailUpdateData.getClient_customer_id();
            String title = customerDetailUpdateData.getTitle();
            String last_name = customerDetailUpdateData.getLast_name();
            String gender = customerDetailUpdateData.getGender();
            String mobile_number = customerDetailUpdateData.getMobile_number();
            String place_of_birth = customerDetailUpdateData.getPlace_of_birth();
            String current_city = customerDetailUpdateData.getCurrent_city();
            String current_district = customerDetailUpdateData.getCurrent_district();
            int current_pincode = customerDetailUpdateData.getCurrent_pincode();
            String residence_type_current_address = customerDetailUpdateData.getResidence_type_current_address();
            String permanent_district = customerDetailUpdateData.getPermanent_district();
            int permanent_pincode = customerDetailUpdateData.getPermanent_pincode();
            String residence_type_permanent_address = customerDetailUpdateData.getResidence_type_permanent_address();
            String religion = customerDetailUpdateData.getReligion();
            String caste = customerDetailUpdateData.getCaste();
            String occupation = customerDetailUpdateData.getOccupation();
            String income_source = customerDetailUpdateData.getIncome_source();
            String property_ownership_flag = customerDetailUpdateData.getProperty_ownership_flag();
            String udyam_number = customerDetailUpdateData.getUdyam_number();
            String name_of_bureau = customerDetailUpdateData.getName_of_bureau();
            int bureau_score = customerDetailUpdateData.getBureau_score();
            String product_id = customerDetailUpdateData.getProduct_id();
            String application_id = customerDetailUpdateData.getApplication_id();
            String category = customerDetailUpdateData.getCategory();
            String sub_category = customerDetailUpdateData.getSub_category();
            String purpose = customerDetailUpdateData.getPurpose();
            String disbursement_type = customerDetailUpdateData.getDisbursement_type();
            int number_of_tranches = customerDetailUpdateData.getNumber_of_tranches();
            int tenure = customerDetailUpdateData.getTenure();
            int number_of_repayments = customerDetailUpdateData.getNumber_of_repayments();
            String psl_description = customerDetailUpdateData.getPsl_description();
            String impacted_industry_description = customerDetailUpdateData.getImpacted_industry_description();
            String applicant_never_in_30_plus_sma_sub_last_12m = customerDetailUpdateData.getApplicant_never_in_30_plus_sma_sub_last_12m();
            String tax_slab = customerDetailUpdateData.getTax_slab();
            String psl_msme_type_of_enterprise = customerDetailUpdateData.getPsl_msme_type_of_enterprise();
            String current_address_line1 = customerDetailUpdateData.getCurrent_address_line1();
            String cersai_security_int_id = customerDetailUpdateData.getCersai_security_int_id();
            String repayment_frequency = customerDetailUpdateData.getRepayment_frequency();
            String father_first_name = customerDetailUpdateData.getFather_first_name();
            String father_last_name = customerDetailUpdateData.getFather_last_name();
            String total_value_of_security = customerDetailUpdateData.getTotal_value_of_security();
            String tenure_frequency = customerDetailUpdateData.getTenure_frequency();
            String marital_status = customerDetailUpdateData.getMarital_status();
            String ckyc_id = customerDetailUpdateData.getCkyc_id();
            Date date_of_birth = null;
            try {
                Date date_of_birth_converter= dataTypeConversion.getDateFromString("yyyy-MM-dd",customerDetailUpdateData.getDate_of_birth());
                 date_of_birth = date_of_birth_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date_of_birth");
            }
            Date first_repayment_date =null;
            try {
                Date first_repayment_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd",customerDetailUpdateData.getFirst_repayment_date());
                first_repayment_date =first_repayment_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable first_repayment_date");
            }
            Date itr_filed_date_current_fy = null;
            try {
                Date itr_filed_date_current_fy_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getItr_filed_date_current_fy());
                itr_filed_date_current_fy =itr_filed_date_current_fy_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date application_date = null;
            try {
                Date application_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getApplication_date());
                application_date=application_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date fcu_done_date=null;
            try {
                Date fcu_done_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getFcu_done_date());
                fcu_done_date=fcu_done_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date e_verification_of_kyc_done_date = null;
            try {
                Date e_verification_of_kyc_done_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd",customerDetailUpdateData.getE_verification_of_kyc_done_date());
                e_verification_of_kyc_done_date=e_verification_of_kyc_done_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date pd_done_date = null;
            try {
                Date pd_done_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getPd_done_date());
                pd_done_date=pd_done_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date search_with_registrar_of_companies_obtained_date = null;
            try {
                Date search_with_registrar_of_companies_obtained_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getSearch_with_registrar_of_companies_obtained_date());
                search_with_registrar_of_companies_obtained_date=search_with_registrar_of_companies_obtained_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date cersai_registration_reference_number_verification_date = null;
            try {
                Date cersai_registration_reference_number_verification_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getCersai_registration_reference_number_verification_date());
                cersai_registration_reference_number_verification_date=cersai_registration_reference_number_verification_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date cibil_report_done_date = null;
            try {
                Date cibil_report_done_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getCibil_report_done_date());
                cibil_report_done_date=cibil_report_done_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date repayment_period_end_date =null;
            try {
                Date repayment_period_end_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getRepayment_period_end_date());
                repayment_period_end_date=repayment_period_end_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date date_of_sanction_by_nbfc =null;
            try {
                Date date_of_sanction_by_nbfc_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getDate_of_sanction_by_nbfc());
                date_of_sanction_by_nbfc=date_of_sanction_by_nbfc_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date date_of_documentation_by_nbfc = null;
            try {
                Date date_of_documentation_by_nbfc_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateData.getDate_of_documentation_by_nbfc());
                date_of_documentation_by_nbfc=date_of_documentation_by_nbfc_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            float annual_income = customerDetailUpdateData.getAnnual_income();
            float principal_amount = customerDetailUpdateData.getPrincipal_amount();
            float interest_rate = customerDetailUpdateData.getInterest_rate();
            float loan_emi = customerDetailUpdateData.getLoan_emi();
            float applicant_age_at_maturity = customerDetailUpdateData.getApplicant_age_at_maturity();

            //=====Business========
            CustomerDetailUpdateBusinessModel customerDetailUpdateBusinessModel=  customerDetailUpdateData.getBusiness();
            String business_name_of_business = customerDetailUpdateBusinessModel.getName_of_business();
            String business_nature_of_business= customerDetailUpdateBusinessModel.getNature_of_business();
            String business_type_of_constitution= customerDetailUpdateBusinessModel.getType_of_constitution();
            Date business_registration_date =null;
            try {
                Date registration_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateBusinessModel.getRegistration_date());
                business_registration_date=registration_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            Date business_incorporation_date = null;
            try {
                Date incorporation_date_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateBusinessModel.getIncorporation_date());
                business_incorporation_date=incorporation_date_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            String business_industry_type= customerDetailUpdateBusinessModel.getIndustry_type();
            String business_sector_type= customerDetailUpdateBusinessModel.getSector_type() ;
            String business_sub_sector_type= customerDetailUpdateBusinessModel.getSub_sector_type();


            int business_vintage = customerDetailUpdateBusinessModel.getBusiness_vintage();
            String business_registered_office_address = customerDetailUpdateBusinessModel.getBusiness_registered_office_address();
            String business_registered_office_city = customerDetailUpdateBusinessModel.getBusiness_registered_office_city();
            String business_registered_office_state = customerDetailUpdateBusinessModel.getBusiness_registered_office_state();
            int business_registered_office_pincode = customerDetailUpdateBusinessModel.getBusiness_registered_office_pincode();
            String business_mailing_office_address = customerDetailUpdateBusinessModel.getBusiness_mailing_office_address();
            String business_mailing_office_city = customerDetailUpdateBusinessModel.getBusiness_mailing_office_city();
            int business_mailing_office_pincode = customerDetailUpdateBusinessModel.getBusiness_mailing_office_pincode();
            /*List<Long>  business_phone_number = customerDetailUpdateBusinessModel.getBusiness_phone_number();
            List<String> business_email_id = customerDetailUpdateBusinessModel.getBusiness_email_id();*/
           /* String  business_phone_number = "{09999889,0988998}";
            String business_email_id = "holmes@credavenue.com";*/
            String business_property_ownership_flag = customerDetailUpdateBusinessModel.getProperty_ownership_flag();
            String business_pan_number = customerDetailUpdateBusinessModel.getBusiness_pan_number();
            String business_rc_number = customerDetailUpdateBusinessModel.getBusiness_rc_number();
            String business_udhyam_regn_number = customerDetailUpdateBusinessModel.getBusiness_udhyam_regn_number();
            String business_mailing_office_address1 = customerDetailUpdateBusinessModel.getBusiness_mailing_office_address1();
            String business_mailing_office_address2 = customerDetailUpdateBusinessModel.getBusiness_mailing_office_address2();
            String business_mailing_office_address3 = customerDetailUpdateBusinessModel.getBusiness_mailing_office_address3();
            String enterprise_activity = customerDetailUpdateBusinessModel.getEnterprise_activity();
            String customer_type = customerDetailUpdateBusinessModel.getCustomer_type();
            float exisiting_shareholding_of_promoters = customerDetailUpdateBusinessModel.getExisiting_shareholding_of_promoters();
            float exisiting_shareholding_of_mutual_funds = customerDetailUpdateBusinessModel.getExisiting_shareholding_of_mutual_funds();
            float exisiting_shareholding_of_financial_institutions_banks = customerDetailUpdateBusinessModel.getExisiting_shareholding_of_financial_institutions_banks();
            float exisiting_shareholding_of_others = customerDetailUpdateBusinessModel.getExisiting_shareholding_of_others();
            float exisiting_shareholding_total = customerDetailUpdateBusinessModel.getExisiting_shareholding_total();
            float proposed_shareholding_of_promoters = customerDetailUpdateBusinessModel.getProposed_shareholding_of_promoters();
            float proposed_shareholding_of_mutual_funds = customerDetailUpdateBusinessModel.getProposed_shareholding_of_mutual_funds();
            float proposed_shareholding_of_financial_institutions_banks = customerDetailUpdateBusinessModel.getProposed_shareholding_of_financial_institutions_banks();
            float proposed_shareholding_of_others = customerDetailUpdateBusinessModel.getProposed_shareholding_of_others();
            float proposed_shareholding_total = customerDetailUpdateBusinessModel.getProposed_shareholding_total();
            int business_annual_income = customerDetailUpdateBusinessModel.getAnnual_income();
            Date balance_sheet_year = null;
            try {
                Date balance_sheet_year_converter = dataTypeConversion.getDateFromString("yyyy-MM-dd", customerDetailUpdateBusinessModel.getBalance_sheet_year());
                balance_sheet_year=balance_sheet_year_converter;
            }catch (Exception e){
                throw new SystemException("1110","Unparseable date");
            }
            String business_ckyc_id = customerDetailUpdateBusinessModel.getCkyc_id();


            String sql= "UPDATE public.create_loan SET \n" +
                    "customer_type_sp = '"+primary_borrower_type+"' ,\n" +
                    "first_name_ia = '"+first_name+"' ,\n" +
                    "current_address_ia = '"+current_address+"' ,\n" +
                    "State_ia = '"+current_state+"' ,\n" +
                    "customer_category_sp = '"+customer_category+"' ,\n" +
                    "client_customer_id_sp = '"+client_customer_id+"' ,\n" +
                    "title_ia = '"+title+"' ,\n" +
                    "last_name_ia = '"+last_name+"' ,\n" +
                    "gender_ia = '"+gender+"' ,\n" +
                    "mobile_number_ia = '"+mobile_number+"' ,\n" +
                    "place_of_birth_ia = '"+place_of_birth+"' ,\n" +
                    "City_ia = '"+current_city+"' ,\n" +
                    "current_district_ia = '"+current_district+"' ,\n" +
                    "current_pincode_ia = '"+current_pincode+"' ,\n" +
                    "residence_current_address_ic = '"+residence_type_current_address+"' ,\n" +
                    "permanent_district_ia = '"+permanent_district+"' ,\n" +
                    "permanent_pincode_ia = '"+permanent_pincode+"' ,\n" +
                    "residence_permanent_address_ic = '"+residence_type_permanent_address+"' ,\n" +
                    "religion_ia = '"+religion+"' ,\n" +
                    "caste_ia = '"+caste+"' ,\n" +
                    "occupation_ic = '"+occupation+"' ,\n" +
                    "income_source_sp = '"+income_source+"' ,\n"+
                    "property_ownership_flag_sp = '"+property_ownership_flag+"' ,\n"+
                    "business_udhyam_regn_number_nia = '"+udyam_number+"' ,\n" +
                    "name_of_bureau_sp = '"+name_of_bureau+"' ,\n" +
                    "bureau_score_sp = '"+bureau_score+"' ,\n" +
                    "product_id_sp = '"+product_id+"' ,\n" +
                    "application_id_sp = '"+application_id+"' ,\n" +
                    "category_sp = '"+category+"' ,\n" +
                    "sub_category_sp = '"+sub_category+"' ,\n"+
                    "purpose_sp = '"+purpose+"' ,\n" +
                    "disbursement_type_sp = '"+disbursement_type+"',\n" +
                    "number_of_tranches_sp = '"+number_of_tranches+"' ,\n"+
                    "tenure_sp = '"+tenure+"' ,\n" +
                    "number_of_repayments_sp = '"+number_of_repayments+"' ,\n" +
                    "psl_description_sp = '"+psl_description+"' ,\n" +
                    "impacted_industry_description_sp = '"+impacted_industry_description+"' ,\n" +
                    "applicant_never_in_30_plus_sma_sub_last_12m_sp = '"+applicant_never_in_30_plus_sma_sub_last_12m+"' ,\n"+
                    "tax_slab_sp = '"+tax_slab+"' ,\n" +
                    "psl_msme_type_of_enterprise_sp = '"+psl_msme_type_of_enterprise+"' ,\n" +
                    //current_address_line1
                    "cersai_security_int_id_ac = '"+cersai_security_int_id+"',\n" +
                    "repayment_frequency_sp = '"+repayment_frequency+"' ,\n" +
                    "father_first_name_ia = '"+father_first_name+"' ,\n" +
                    "father_last_name_ia = '"+father_last_name+"' ,\n" +
                    "tenure_frequency_sp = '"+tenure_frequency+"' ,\n"+
                    "total_value_of_security_sp = '"+total_value_of_security+"' ,\n" +
                    "marital_status_ia = '"+marital_status+"' , \n" +
                    "ckyc_id_ia = '"+ckyc_id+"' ,\n" +
                    "date_of_birth_ia = '"+date_of_birth+"' ,\n" +
                    "first_repayment_date_sp = '"+first_repayment_date+"' ,\n" +
                    "itr_filed_date_current_fy_sp = '"+itr_filed_date_current_fy+"' ,\n"+
                    "application_date_sp = '"+application_date+"' ,\n" +
                    "fcu_done_date_sp = '"+fcu_done_date+"' ,\n" +
                    "e_verification_of_kyc_done_date_sp = '"+e_verification_of_kyc_done_date+"' ,\n" +
                    "pd_done_date_sp = '"+pd_done_date+"' ,\n" +
                    "search_with_registrar_of_companies_obtained_date_sp = '"+search_with_registrar_of_companies_obtained_date+"' ,\n" +
                    "cersai_registration_reference_number_verification_date_sp = '"+cersai_registration_reference_number_verification_date+"' ,\n" +
                    "cibil_report_done_date_sp = '"+cibil_report_done_date+"' ,\n" +
                    "repayment_period_end_date_sp = '"+repayment_period_end_date+"' ,\n" +
                    "date_of_sanction_by_nbfc_sp = '"+date_of_sanction_by_nbfc+"' ,\n" +
                    "date_of_documentation_by_nbfc_sp = '"+date_of_documentation_by_nbfc+"' ,\n" +
                    "annual_income_sp = '"+annual_income+"' ,\n"+
                    "principal_amount_sp = '"+principal_amount+"' ,\n" +
                    "interest_rate_sp = '"+interest_rate+"' ,\n" +
                    "loan_emi_sp = '"+loan_emi+"' ,\n" +
                    "applicant_age_at_maturity_ia = '"+applicant_age_at_maturity+"', \n" +

                    //=========Bussiness=============================
                    "name_of_business_nia = '"+business_name_of_business+"' ,\n" +
                    "nature_of_business_nia = '"+business_nature_of_business+"' ,\n" +
                    "type_of_constitution_nia = '"+business_type_of_constitution+"' ,\n" +
                    "registration_date_nia = '"+business_registration_date+"' ,\n" +
                    "incorporation_date_nia = '"+business_incorporation_date+"' ,\n" +
                    "industry_type_nia = '"+business_industry_type+"' ,\n" +
                    "sector_type_nic = '"+business_sector_type+"' ,\n" +
                    "sub_sector_type_nia = '"+business_sub_sector_type+"', \n" +

                    "business_vintage_nia= '"+business_vintage+"', \n" +
                    "business_registered_office_address_nia= '"+business_registered_office_address+"' ,\n" +
                    "business_mailing_office_city_nia= '"+business_registered_office_city+"', \n" +
                    "business_registered_office_state_nia= '"+business_registered_office_state+"', \n" +
                    "business_mailing_office_pincode_nia= '"+business_registered_office_pincode+"', \n" +
                    "business_mailing_office_address1_nia= '"+business_mailing_office_address+"', \n" +
                    //"business_mailing_office_city_nia= '"+business_mailing_office_city+"' ,\n" +
                    //"business_mailing_office_pincode_nia= '"+business_mailing_office_pincode+"' ,\n" +
                    /*"business_phone_number_nia= '"+business_phone_number+"' ,\n" +
                    "business_email_id_nia= '"+business_email_id+"' ,\n" +*/
                   // "property_ownership_flag_nic= '"+business_property_ownership_flag+"' ,\n" +
                    "business_pan_number_nia= '"+business_pan_number+"', \n" +
                    "business_rc_number_nia= '"+business_rc_number+"' ,\n" +
                    //"business_udhyam_regn_number_nia= '"+business_udhyam_regn_number+"' ,\n" +
                    //"business_mailing_office_address1_nia= '"+business_mailing_office_address1+"' ,\n" +
                    "business_mailing_office_address2_nia= '"+business_mailing_office_address2+"' ,\n" +
                    "business_mailing_office_address3_nia= '"+business_mailing_office_address3+"' ,\n" +
                    "enterprise_activity_nia= '"+enterprise_activity+"', \n" +
                    "customer_type_nia= '"+customer_type+"', \n" +
                    "exisiting_shareholding_of_promoters_nia= '"+exisiting_shareholding_of_promoters+"', \n" +
                    "exisiting_shareholding_of_mutual_funds_nia= '"+exisiting_shareholding_of_mutual_funds+"', \n" +
                    "exisiting_shareholding_of_financial_institutions_banks_nia= '"+exisiting_shareholding_of_financial_institutions_banks+"', \n" +
                    "exisiting_shareholding_of_others_nia= '"+exisiting_shareholding_of_others+"' ,\n" +
                    "exisiting_shareholding_total_nia= '"+exisiting_shareholding_total+"', \n" +
                    "proposed_shareholding_of_promoters_nia= '"+proposed_shareholding_of_promoters+"' ,\n" +
                    "proposed_shareholding_of_mutual_funds_nia= '"+proposed_shareholding_of_mutual_funds+"' ,\n" +
                    "proposed_shareholding_of_financial_institutions_banks_nia= '"+proposed_shareholding_of_financial_institutions_banks+"', \n" +
                    "proposed_shareholding_of_others_nia= '"+proposed_shareholding_of_others+"', \n" +
                    "proposed_shareholding_total_nia= '"+proposed_shareholding_total+"' ,\n" +
                   // "annual_income_nia= '"+business_annual_income+"' ,\n" +
                    "Balance_sheet_year_nia= '"+balance_sheet_year+"' \n" +
                   // "ckyc_id_nia= '"+business_ckyc_id+"' \n" +

                    "WHERE lan_sp ='"+lanSp+"' \n";

            int i = jdbcTemplate.update(sql);
            if (i>0){
                status = true;
            }


        }catch (Exception e){
            throw new SystemException("1111",e.getMessage());
        }

        return status;
    }
}
