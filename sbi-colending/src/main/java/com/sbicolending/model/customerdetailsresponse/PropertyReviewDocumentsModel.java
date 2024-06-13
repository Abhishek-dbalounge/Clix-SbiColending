package com.sbicolending.model.customerdetailsresponse;

import lombok.Data;

import java.util.List;

@Data
public class PropertyReviewDocumentsModel {

    private List<String> field_verification_report_link;
    private List<String> cersai_check_report_link;
    private List<String> entire_set_of_property_documents_link;

}
