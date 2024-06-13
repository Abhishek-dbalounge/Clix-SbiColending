package com.sbicolending.model.updateloanrequest;

import lombok.Data;

import java.util.List;

@Data
public class UpdateLoanRequestModel {


 private String primary_borrower_type;
 private String first_name;
 private String current_address;
 private String current_state;
 private String customer_category;
 private String client_customer_id;
 private String title;
 private String last_name;
 private String gender;
 private String mobile_number;
 private String place_of_birth;
 private String current_city;
 private String current_district;
 private int current_pincode;//(int-Done)
 private String residence_type_current_address;
 private String permanent_district;
 private int permanent_pincode;//(int-Done)
 private String residence_type_permanent_address;
 private String religion;
 private String caste;
 private String occupation;
 private String income_source;
 private String property_ownership_flag;
 private String udyam_number;
 private String name_of_bureau;
 private int bureau_score;//(int-NotDone Because in Db String type but data is empty)
 private List<String> bureau_report_link;
 private String product_id;
 private String application_id;
 private String category;
 private String sub_category;
 private String purpose;
 private String disbursement_type;
 private int number_of_tranches;//(int)
 private int tenure;//(int)
 private int number_of_repayments;//(int)
 private String psl_description;
 private String impacted_industry_description;
 private String applicant_never_in_30_plus_sma_sub_last_12m;
 private String tax_slab;
 private String psl_msme_type_of_enterprise;
 private String current_address_line1;
 private String cersai_security_int_id;
 private String repayment_frequency;
 private String father_first_name;
 private String father_last_name;
 private String total_value_of_security;
 private String tenure_frequency;
 private String marital_status;
 private String ckyc_id;
 private String date_of_birth;
 private String first_repayment_date;
 private String itr_filed_date_current_fy;//(Date)
 private String application_date;
 private String fcu_done_date;//(Date)
 private String e_verification_of_kyc_done_date;//(Date)
 private String pd_done_date;//(Date)
 private String search_with_registrar_of_companies_obtained_date;//(Date)
 private String cersai_registration_reference_number_verification_date;//(Date)
 private String cibil_report_done_date;//(Date)
 private String repayment_period_end_date;//(Date)
 private String date_of_sanction_by_nbfc;//(Date)
 private String date_of_documentation_by_nbfc;//(Date)
 private float annual_income;//(float)
 private float principal_amount;//(float)
 private float interest_rate;//(float)
 private float loan_emi;//(float)
 private float applicant_age_at_maturity;//(float)
 private UpdateLoanRequestBusinessModel business;
 private List<UpdateLoanRequestGuarantorsModel> guarantors;
 //================Set Multiple array=====================
 private List<UpdateLoanRequestCoApplicantsModel> coApplicants;
 //====================================
 private UpdateLoanRequestBankStatementModel bankStatement;
 private UpdateLoanRequestFinancialDataModel financialData;
 private List<UpdateLoanRequestTranchesModel> tranchesModel;
 private UpdateLoanRequestLinkedLoanInfosModel linkedLoanInfosModel;
 //===================Set Multiple array=========================
 private List<UpdateLoanRequestBusinessCoApplicantsModel> businessCoApplicants;
 //========================================
 private UpdateLoanRequestRelatedPartiesModel relatedPartiesModel;
 private UpdateLoanRequestOriginalLoanDetailModel original_loan_detail;
 private List<UpdateLoanRequestOriginalDisbursementDetailsModel> original_disbursement_details;
 private List<UpdateLoanRequestAssetsModel> assets;
 private UpdateLoanRequestPropertyReviewDocumentsModel propertyReviewDocumentsModel;

}
