package com.sbicolending.service;

import com.sbicolending.model.clm2agreementsignedrequest.Clm2AgreementSignedModel;
import com.sbicolending.model.createcollectionrequest.CreateCollectionModel;
import com.sbicolending.model.createloanresponse.CreateLoanResponse;
import com.sbicolending.model.createrepaymentschedulerequest.CreateRepaymentScheduleModel;
import com.sbicolending.model.createloanrequest.CreateLoanRequestModel;
import com.sbicolending.model.updateloanrequest.UpdateLoanRequestModel;

public interface CallYubiApiService {
    Object getCreateLoan(CreateLoanRequestModel request);

    Object getLoanDetails(String loanId);

    Object getUpdateLoanDetails(String loanId, UpdateLoanRequestModel request);

    Object getClm2AgreementSigned(Clm2AgreementSignedModel request);

    Object getPoolDetails(String pool_id);

    Object getCreateRepaymentSchedule(CreateRepaymentScheduleModel request, String loanId);

    Object getCreateCollection(CreateCollectionModel request, String loanId);

    Object getCollectionDetails(String loanId);
}
