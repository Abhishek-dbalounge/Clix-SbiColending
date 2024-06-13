package com.sbicolending.model.updateloanrequest;

import lombok.Data;

@Data
public class UpdateLoanRequestRelatedPartiesModel {

    private String connected_with;
    private String connection_name;
    private String rp_type;
    private String name;
    private String share_holding;
    private String designation;
    private String proposed_share_holding;

}
