package com.sbicolending.utils;

public class DBQuery {

    public static final String CUSTOMER_DETAILS_DATA ="select distinct \n" +
            "customer_type_sp,\n" +
            "first_name_ia,\n" +
            "current_address_ia,\n" +
            "State_ia,\n" +
            "customer_category_sp,\n" +
            "client_customer_id_sp,\n" +
            "title_ia,\n" +
            "last_name_ia,\n" +
            "gender_ia,\n" +
            "mobile_number_ia,\n" +
            "place_of_birth_ia,\n" +
            "City_ia,\n" +
            "current_district_ia,\n" +
            "current_pincode_ia,\n" +
            "residence_current_address_ic,\n" +
            "permanent_district_ia,\n" +
            "permanent_pincode_ia,\n" +
            "residence_permanent_address_ic,\n" +
            "religion_ia,\n" +
            "caste_ia,\n" +
            "occupation_ic,\n" +
            "income_source_sp,\n"+
            "property_ownership_flag_sp,\n"+
            "business_udhyam_regn_number_nia,\n" +
            "name_of_bureau_sp,\n" +
            "bureau_score_sp,\n" +
            "bureau_report_link_SP,\n"+
            "product_id_sp,\n" +
            "application_id_sp,\n" +
            "category_sp,\n" +
            "sub_category_sp,\n"+
            "purpose_sp,\n" +
            "disbursement_type_sp,\n" +
            "number_of_tranches_sp,\n"+
            "tenure_sp,\n" +
            "number_of_repayments_sp,\n" +
            "psl_description_sp,\n" +
            "impacted_industry_description_sp,\n" +
            "applicant_never_in_30_plus_sma_sub_last_12m_sp,\n"+
            "tax_slab_sp,\n" +
            "psl_msme_type_of_enterprise_sp,\n" +
            "cersai_security_int_id_ac,\n" +
            "repayment_frequency_sp,\n" +
            "father_first_name_ia,\n" +
            "father_last_name_ia,\n" +
            "tenure_frequency_sp,\n"+
            "total_value_of_security_sp,\n" +
            "marital_status_ia,\n" +
            "ckyc_id_ia,\n" +
            "date_of_birth_ia,\n" +
            "first_repayment_date_sp,\n" +
            "itr_filed_date_current_fy_sp,\n"+
            "application_date_sp,\n" +
            "fcu_done_date_sp,\n" +
            "e_verification_of_kyc_done_date_sp,\n" +
            "pd_done_date_sp,\n" +
            "search_with_registrar_of_companies_obtained_date_sp,\n" +
            "cersai_registration_reference_number_verification_date_sp,\n" +
            "cibil_report_done_date_sp,\n" +
            "repayment_period_end_date_sp,\n" +
            "date_of_sanction_by_nbfc_sp,\n" +
            "date_of_documentation_by_nbfc_sp,\n" +
            "annual_income_sp,\n"+
            "principal_amount_sp,\n" +
            "interest_rate_sp,\n" +
            "loan_emi_sp,\n" +
            "applicant_age_at_maturity_ia,\n" +
    //=========Bussiness=============================
            "name_of_business_nia,\n" +
            "nature_of_business_nia,\n" +
            "type_of_constitution_nia,\n" +
            "registration_date_nia,\n" +
            "incorporation_date_nia,\n" +
            "industry_type_nia,\n" +
            "sector_type_nic,\n" +
            "sub_sector_type_nia,\n" +
            "business_vintage_nia,\n" +
            "business_registered_office_address_nia,\n" +
            "business_mailing_office_city_nia,\n" +
            "business_registered_office_state_nia,\n"+
            "business_mailing_office_pincode_nia,\n" +
            "business_mailing_office_address1_nia,\n" +
            "business_phone_number_nia,\n" +
            "business_email_id_nia,\n" +
            "property_ownership_flag_nic,\n" +
            "business_pan_number_nia,\n" +
            "business_rc_number_nia,\n" +
            "business_mailing_office_address2_nia,\n" +
            "business_mailing_office_address3_nia,\n" +
            "enterprise_activity_nia,\n"+
            "customer_type_nia,\n" +
            "exisiting_shareholding_of_promoters_nia,\n" +
            "exisiting_shareholding_of_mutual_funds_nia,\n" +
            "exisiting_shareholding_of_financial_institutions_banks_nia,\n" +
            "exisiting_shareholding_of_others_nia,\n" +
            "exisiting_shareholding_total_nia,\n" +
            "proposed_shareholding_of_promoters_nia,\n" +
            "proposed_shareholding_of_mutual_funds_nia,\n" +
            "proposed_shareholding_of_financial_institutions_banks_nia,\n" +
            "proposed_shareholding_of_others_nia,\n" +
            "proposed_shareholding_total_nia,\n" +
            "annual_income_nia,\n" +
            "Balance_sheet_year_nia,\n" +
            "ckyc_id_nia, \n" +
            //=========Guarantors=============================
            "date_of_birth_ig,\n" +
            "gender_ig,\n" +
            "current_address_ig,\n" +
            "current_district_ig,\n" +
            "City_ig,\n" +
            "State_ig,\n" +
            "current_pincode_ig,\n" +
            "permanent_district_ig,\n" +
            "permanent_pincode_ig,\n" +
            "mobile_number_ig,\n" +
            "marital_status_ig,\n" +
            "religion_ig,\n" +
            "bureau_score_ig,\n" +
            "age_at_maturity_ig,\n" +
            "employment_details_designation_sp,\n" +
            "caste_ig,\n"+
            "father_first_name_ig,\n" +
            "father_last_name_ig,\n" +
            "occupation_ig,\n" +
            "place_of_birth_ig,\n" +
            "title_ig,\n" +
            "income_source_ig,\n"+
            "current_address_line1_ig,\n" +
            "education_ig,\n" +
            "other_than_agricultural_income_ig,\n" +
            "total_net_worth_ig,\n"+
            "customer_type_ig,\n" +
            "ckyc_id_ig,\n" +
            "annual_income_ig,\n" +
            //==========CoApplicants(Multipul Array)====================
            "relationship_with_applicant_ic,\n"+
            "first_name_ic,\n" +
            "last_name_ic,\n" +
            "gender_ic,\n"+
            "occupation_ic,\n" +
            "date_of_birth_ic,\n" +
            "mobile_number_ic,\n" +
            "current_address_ic,\n" +
            "City_ic,\n" +
            "current_district_ic,\n" +
            "State_ic,\n" +
            "current_pincode_ic,\n" +
            "permanent_district_ic,\n" +
            "permanent_pincode_ic,\n" +
            "marital_status_ic,\n" +
            "religion_ic,\n" +
            "education_ic,\n" +
            "annual_income_ic,\n" +
            "bureau_score_ic,\n" +
            "age_at_maturity_ic,\n" +
            "employment_details_designation_sp,\n"+
            "caste_ic,\n" +
            "father_first_name_ic,\n" +
            "father_last_name_ic,\n" +
            "place_of_birth_ic,\n" +
            "title_ic,\n" +
            "income_source_ic,\n"+
            "total_net_worth_ic,\n"+
            "customer_type_ic,\n" +
            "ckyc_id_ic,\n"+
                //---Array1------
                "relationship_with_applicant_ic_1,\n"+
                "first_name_ic_1,\n" +
                "last_name_ic_1,\n"+
                "gender_ic_1,\n"+
                "occupation_ic_1,\n"+
                "date_of_birth_ic_1,\n"+
                "mobile_number_ic_1,\n"+
                "current_address_ic_1,\n"+
                "city_ic_1,\n"+
                "current_district_ic_1,\n"+
                "state_ic_1,\n"+
                "current_pincode_ic_1,\n"+
                "permanent_district_ic_1,\n"+
                "permanent_pincode_ic_1,\n"+
                "marital_status_ic_1,\n"+
                "religion_ic_1,\n"+
                "education_ic_1,\n"+
                "annual_income_ic_1,\n"+
                "bureau_score_ic_1,\n"+
                "age_at_maturity_ic_1,\n" +
                "employment_details_designation_sp_1,\n"+
                "caste_ic_1,\n"+
                "father_first_name_ic_1,\n" +
                "father_last_name_ic_1,\n"+
                "place_of_birth_ic_1,\n" +
                "title_ic_1 ,\n" +
                "income_source_ic_1,\n" +
                "total_net_worth_ic_1,\n" +
                "customer_type_ic_1,\n" +
                "ckyc_id_ic_1,\n" +
                //---Array2-----------
                "relationship_with_applicant_ic_2,\n" +
                " first_name_ic_2,\t\n" +
                " last_name_ic_2,\n" +
                " gender_ic_2,\n" +
                " occupation_ic_2,\n" +
                " date_of_birth_ic_2,\n" +
                " mobile_number_ic_2,\n" +
                " current_address_ic_2,\n" +
                " city_ic_2,\n" +
                " current_district_ic_2,\n" +
                " state_ic_2,\n" +
                " current_pincode_ic_2,\n" +
                " permanent_district_ic_2,\n" +
                " permanent_pincode_ic_2,\n" +
                " marital_status_ic_2,\n" +
                " religion_ic_2,\n" +
                " education_ic_2,\n" +
                " annual_income_ic_2,\n" +
                " bureau_score_ic_2,\n" +
                " age_at_maturity_ic_2,\n" +
                " employment_details_designation_sp_2,\n" +
                " caste_ic_2,\n"+
                " father_first_name_ic_2,\n" +
                " father_last_name_ic_2,\n" +
                " place_of_birth_ic_2,\n" +
                " title_ic_2,\n" +
                " income_source_ic_2,\n" +
                " total_net_worth_ic_2,\n" +
                " customer_type_ic_2,\n" +
                " ckyc_id_ic_2,\n"+
                //----Array3---
                "relationship_with_applicant_ic_3,\n" +
                "first_name_ic_3,\n" +
                "last_name_ic_3,\t\n" +
                "gender_ic_3,\n" +
                "occupation_ic_3,\n" +
                "date_of_birth_ic_3,\n" +
                "mobile_number_ic_3,\n" +
                "current_address_ic_3,\n" +
                "city_ic_3,\n" +
                "current_district_ic_3,\n" +
                "state_ic_3,\n" +
                "current_pincode_ic_3,\n" +
                "permanent_district_ic_3,\n" +
                "permanent_pincode_ic_3,\n" +
                "marital_status_ic_3,\n" +
                "religion_ic_3,\n" +
                "education_ic_3,\n" +
                "annual_income_ic_3,\n" +
                "bureau_score_ic_3,\n" +
                "age_at_maturity_ic_3,\n" +
                "employment_details_designation_sp_3,\n" +
                "caste_ic_3,\n" +
                "father_first_name_ic_3,\n" +
                "father_last_name_ic_3,\n" +
                "place_of_birth_ic_3,\n" +
                "title_ic_3,\n" +
                "income_source_ic_3,\n" +
                "total_net_worth_ic_3,\n" +
                "customer_type_ic_3,\n" +
                "ckyc_id_ic_3,\n"+
                //-----Array4----
                "relationship_with_applicant_ic_4,\n" +
                "first_name_ic_4\tlast_name_ic_4,\n" +
                "gender_ic_4,\n" +
                "occupation_ic_4,\n" +
                "date_of_birth_ic_4,\n" +
                "mobile_number_ic_4,\n" +
                "current_address_ic_4,\n" +
                "city_ic_4,\n" +
                "current_district_ic_4,\n" +
                "state_ic_4,\n" +
                "current_pincode_ic_4,\n" +
                "permanent_district_ic_4,\n" +
                "permanent_pincode_ic_4,\n" +
                "marital_status_ic_4,\n" +
                "religion_ic_4,\n" +
                "education_ic_4,\n" +
                "annual_income_ic_4,\n" +
                "bureau_score_ic_4,\n" +
                "age_at_maturity_ic_4,\n" +
                "employment_details_designation_sp_4,\n" +
                "caste_ic_4,\n" +
                "father_first_name_ic_4,\n" +
                "father_last_name_ic_4,\n" +
                "place_of_birth_ic_4,\n" +
                "title_ic_4,\n" +
                "income_source_ic_4,\n" +
                "total_net_worth_ic_4,\n" +
                "customer_type_ic_4,\n" +
                "ckyc_id_ic_4,\n"+
               //-----Array5-----
                "relationship_with_applicant_ic_5,\n" +
                "first_name_ic_5,\n" +
                "last_name_ic_5,\n" +
                "gender_ic_5,\n" +
                "occupation_ic_5,\n" +
                "date_of_birth_ic_5,\n" +
                "mobile_number_ic_5,\n" +
                "current_address_ic_5,\n" +
                "city_ic_5,\n" +
                "current_district_ic_5,\n" +
                "state_ic_5,\n" +
                "current_pincode_ic_5,\n" +
                "permanent_district_ic_5,\n" +
                "permanent_pincode_ic_5,\n" +
                "marital_status_ic_5,\n" +
                "religion_ic_5,\n" +
                "education_ic_5,\n" +
                "annual_income_ic_5,\n" +
                "bureau_score_ic_5,\n" +
                "age_at_maturity_ic_5,\n" +
                "employment_details_designation_sp_5,\n" +
                "caste_ic_5,\n" +
                "father_first_name_ic_5,\n" +
                "father_last_name_ic_5,\n" +
                "place_of_birth_ic_5,\n" +
                "title_ic_5,\n" +
                "income_source_ic_5,\n" +
                "total_net_worth_ic_5,\n" +
                "customer_type_ic_5,\n" +
                "ckyc_id_ic_5,\n"+

            //==========BankStatement==================
            "sma_account_sp,\n" +
            "business_transactions_sp,\n" +
            //==============FinancialData==================
            "annual_business_turnover_sp,\n" +
            //==============Tranche========================
            "tranche_number,\n" +
            "principal_amount,\n"+
            //==============LinkedLoanInfos==========================
            "linked_loan_id,\n" +
            "foreclosure,\n"+
            //===================================
            "connected_with_rp,\n" +
            "connection_name_rp,\n" +
            "rp_type_rp,\n" +
            "name_rp,\n" +
            "share_holding_rp,\n" +
            "designation_rp,\n" +
            "proposed_share_holding_rp,\n"+
            //==============BusinessCoApplicants(Multipul Array)=============
            "name_of_business_nic,\n" +
            "nature_of_business_nic,\n" +
            "type_of_constitution_nic,\n" +
            "registration_date_nic,\n" +
            "incorporation_date_nic,\n" +
            "industry_type_nic,\n" +
            "sector_type_nic,\n" +
            "sub_sector_type_nic,\n" +
            "business_vintage_nic,\n" +
            "business_registered_office_address_nic,\n" +
            "business_mailing_office_city_nic,\n" +
            "State_nic ,\n" +
            "business_mailing_office_pincode_nic,\n" +
           // "business_registered_office_address_nic,\n" +
            "business_mailing_office_address1_nic,\n" +
            "business_mailing_office_address2_nic,\n" +
            "business_mailing_office_address3_nic,\n" +
            "business_phone_number_nic,\n" +
            "business_pan_number_nic,\n" +
            "business_pan_link_nic,\n" +
            "business_rc_number_nic,\n" +
            "business_rc_link_nic,\n" +
            "ckyc_id_nic,\n"+

            //----Array1------
            "name_of_business_nic_1,\n" +
            "nature_of_business_nic_1,\n" +
            "type_of_constitution_nic_1,\n" +
            "registration_date_nic_1,\n" +
            "incorporation_date_nic_1,\n" +
            "industry_type_nic_1,\n" +
            "sector_type_nic_1,\n" +
            "sub_sector_type_nic_1,\n" +
            "business_vintage_nic_1,\n" +
            "business_registered_office_address_nic_1,\n" +
            "business_mailing_office_city_nic_1,\n" +
            "state_nic_1,\n" +
            "business_mailing_office_pincode_nic_1,\n" +
            "business_mailing_office_address1_nic_1,\n" +
            "business_mailing_office_address2_nic_1,\n" +
            "business_mailing_office_address3_nic_1,\n" +
            "business_phone_number_nic_1\t,\n" +
            "business_pan_number_nic_1,\n" +
            "business_pan_link_nic_1\t,\n" +
            "business_rc_number_nic_1,\n" +
            "business_rc_link_nic_1,\n" +
            "annual_business_turnover_sp_1,\n" +
            "ckyc_id_nic_1,\n"+

            //----Array2------
            "name_of_business_nic_2,\n" +
            "nature_of_business_nic_2,\n" +
            "type_of_constitution_nic_2,\n" +
            "registration_date_nic_2\t,\n" +
            "incorporation_date_nic_2,\n" +
            "industry_type_nic_2\t,\n" +
            "sector_type_nic_2,\n" +
            "sub_sector_type_nic_2,\n" +
            "business_vintage_nic_2,\n" +
            "business_registered_office_address_nic_2,\n" +
            "business_mailing_office_city_nic_2,\n" +
            "state_nic_2\t,\n" +
            "business_mailing_office_pincode_nic_2,\n" +
            "business_mailing_office_address1_nic_2,\n" +
            "business_mailing_office_address2_nic_2,\n" +
            "business_mailing_office_address3_nic_2,\n" +
            "business_phone_number_nic_2\t,\n" +
            "business_pan_number_nic_2,\n" +
            "business_pan_link_nic_2\t,\n" +
            "business_rc_number_nic_2,\n" +
            "business_rc_link_nic_2,\n" +
            "annual_business_turnover_sp_2,\n" +
            "ckyc_id_nic_2,\n"+

            //------Array3-----
            "name_of_business_nic_3,\n" +
            "nature_of_business_nic_3,\n" +
            "type_of_constitution_nic_3,\n" +
            "registration_date_nic_3\t,\n" +
            "incorporation_date_nic_3,\n" +
            "industry_type_nic_3,\n" +
            "sector_type_nic_3\t,\n" +
            "sub_sector_type_nic_3,\n" +
            "business_vintage_nic_3\t,\n" +
            "business_registered_office_address_nic_3,\n" +
            "business_mailing_office_city_nic_3,\n" +
            "state_nic_3\tbusiness_mailing_office_pincode_nic_3,\n" +
            "business_mailing_office_address1_nic_3,\n" +
            "business_mailing_office_address2_nic_3,\n" +
            "business_mailing_office_address3_nic_3,\n" +
            "business_phone_number_nic_3,\n" +
            "business_pan_number_nic_3,\n" +
            "business_pan_link_nic_3\t,\n" +
            "business_rc_number_nic_3,\n" +
            "business_rc_link_nic_3,\n" +
            "annual_business_turnover_sp_3,\n" +
            "ckyc_id_nic_3,\n"+

            //-----Array4-----
            "name_of_business_nic_4,\n" +
            "nature_of_business_nic_4,\n" +
            "type_of_constitution_nic_4,\n" +
            "registration_date_nic_4,\n" +
            "incorporation_date_nic_4,\n" +
            "industry_type_nic_4\t,\n" +
            "sector_type_nic_4,\n" +
            "sub_sector_type_nic_4,\n" +
            "business_vintage_nic_4,\n" +
            "business_registered_office_address_nic_4,\n" +
            "business_mailing_office_city_nic_4,\n" +
            "state_nic_4,\n" +
            "business_mailing_office_pincode_nic_4,\n" +
            "business_mailing_office_address1_nic_4,\n" +
            "business_mailing_office_address2_nic_4,\n" +
            "business_mailing_office_address3_nic_4,\n" +
            "business_phone_number_nic_4,\n" +
            "business_pan_number_nic_4,\n" +
            "business_pan_link_nic_4,\n" +
            "business_rc_number_nic_4,\n" +
            "business_rc_link_nic_4,\n" +
            "annual_business_turnover_sp_4,\n" +
            "ckyc_id_nic_4,\n"+

            //----Array5------
            "name_of_business_nic_5,\n" +
            "nature_of_business_nic_5,\n" +
            "type_of_constitution_nic_5,\n" +
            "registration_date_nic_5\t,\n" +
            "incorporation_date_nic_5,\n" +
            "industry_type_nic_5,\n" +
            "sector_type_nic_5,\n" +
            "sub_sector_type_nic_5,\n" +
            "business_vintage_nic_5,\n" +
            "business_registered_office_address_nic_5,\n" +
            "business_mailing_office_city_nic_5,\n" +
            "state_nic_5\t,\n" +
            "business_mailing_office_pincode_nic_5,\n" +
            "business_mailing_office_address1_nic_5,\n" +
            "business_mailing_office_address2_nic_5,\n" +
            "business_mailing_office_address3_nic_5,\n" +
            "business_phone_number_nic_5,\n" +
            "business_pan_number_nic_5,\n" +
            "business_pan_link_nic_5\t,\n" +
            "business_rc_number_nic_5,\n" +
            "business_rc_link_nic_5,\n" +
            "annual_business_turnover_sp_5,\n" +
            "ckyc_id_nic_5,\n"+

            //===========OriginalDisbursementDetails==========
            "disbursement_amount_sp, \n" +
            "disbursement_date_sp, \n" +
            //==========orignalloandetails===========
            "loan_amount,\n" +
            "tenure,\n" +
            "repayment_frequency_sp,\n"+
            //==========Assets===============
            "sanction_ltv_ac,\n"+
            "collateral_created_date_ac,\n" +
            "cersai_date_ac,\n" +
            "security_valuation_ac,\n" +
            "survey_or_gat_number_ac,\n" +
            "bound_by_north_ac,\n" +
            "bound_by_south_ac,\n" +
            "bound_by_east_ac,\n" +
            "bound_by_west_ac,\n" +
            "purchase_cost_ac,\n" +
            "purchase_date_ac,\n" +
            "carpet_area_unit_ac,\n" +
            "plot_id_number_ac,\n" +
            "property_nature_ac,\n" +
            "property_locality_ac,\n" +
            "valuation_date_ac,\n" +
            //=========PropertyReviewDocuments=========
            "Field_verification_report_link, \n"+
            "cersai_check_report_link,\n" +
            "entire_set_of_property_documents_link\n"+
            "from public.create_loan  where lan_sp = ?";











    public static final String CUSTOMER_DETAILS_UPDATE_DATA ="";



}
