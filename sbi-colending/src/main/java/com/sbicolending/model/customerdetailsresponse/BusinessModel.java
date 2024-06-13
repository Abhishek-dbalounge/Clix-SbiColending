package com.sbicolending.model.customerdetailsresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BusinessModel {


    private String name_of_business;
    private String nature_of_business;
    private String type_of_constitution;
    private String registration_date;
    private String incorporation_date;
    private String industry_type;
    private String sector_type;
    private String sub_sector_type;
    private int business_vintage;
    private String business_registered_office_address;
    private String business_registered_office_city;
    private String business_registered_office_state;
    private int business_registered_office_pincode;
    private String business_mailing_office_address;
    private String business_mailing_office_city;
    private int business_mailing_office_pincode;
    private List<Long> business_phone_number;
    private List<String> business_email_id;
    private String property_ownership_flag;
    private String business_pan_number;
    private String business_rc_number;
    private String business_udhyam_regn_number;
    private String business_mailing_office_address1;
    private String business_mailing_office_address2;
    private String business_mailing_office_address3;
    private String enterprise_activity;
    private String customer_type;
    private float exisiting_shareholding_of_promoters;
    private float exisiting_shareholding_of_mutual_funds;
    @JsonProperty("exisiting_shareholding_of_financial institutions_banks")
    private float exisiting_shareholding_of_financial_institutions_banks;
    private float exisiting_shareholding_of_others;
    private float exisiting_shareholding_total;
    private float proposed_shareholding_of_promoters;
    private float proposed_shareholding_of_mutual_funds;
    private float proposed_shareholding_of_financial_institutions_banks;
    private float proposed_shareholding_of_others;
    private float proposed_shareholding_total;
    private int annual_income;
    private String balance_sheet_year;
    private String ckyc_id;

}
