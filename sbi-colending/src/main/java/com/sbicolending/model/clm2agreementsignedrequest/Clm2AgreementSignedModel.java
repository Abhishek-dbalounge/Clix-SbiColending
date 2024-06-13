package com.sbicolending.model.clm2agreementsignedrequest;

import lombok.Data;

import java.util.List;

@Data
public class Clm2AgreementSignedModel {

    private String client_pool_id;
    private List<String> client_loan_ids;
    private String doa_link;

}
