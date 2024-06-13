package com.sbicolending.model.updatecustomerdetailsrequest;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDetailUpdateCoApplicantsModel {

    private String relationship_with_applicant;
    private String first_name;
    private String last_name;
    private String gender;
    private String occupation;
    private Date date_of_birth;//(Date)
    private Long mobile_number;//(int)
    private String current_address;
    private String current_city;
    private String current_district;
    private String current_state;
    private int current_pincode;//(int)
    private String permanent_district;
    private int permanent_pincode;//(int)
    private String marital_status;
    private String religion;
    private String education;
    private float annual_income;
    private int bureau_score;//(int)
    private int age_at_maturity;//(int)
    private String employment_details_designation;
    private String caste;
    private String father_first_name;
    private String father_last_name;
    private String place_of_birth;
    private String title;
    private String income_source;
    private String current_address_line1;
    private float total_net_worth;//(float)
    private String customer_type;
    private String ckyc_id;

}
