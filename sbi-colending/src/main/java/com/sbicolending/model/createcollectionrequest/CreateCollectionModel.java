package com.sbicolending.model.createcollectionrequest;

import lombok.Data;

import java.util.List;

@Data
public class CreateCollectionModel {

    private String principal_int_split_flag;
    private int installment_no;
    private String due_date;
    private int principal;
    private int interest;
    private int other_charges;
    private int overdue_charges;
    private int penalty_charges;
    private int fee_charges;
    private int bounce_charges;
    private String reference_no;
    private String instrument_type;
    private String emi_closed;
    private String foreclosure;
    private String disbursement_cancellation;
    private List<UpdateRsModel> updated_rs;
    private int escrow_transferred_amount;
    private String escrow_utr_number;
    private String emi_changed;
    private String charges_collection_closed;
    private String payment_type;
    private String paid_date;
    private String investor_transfer_date;
    private String escrow_transferred_date;
    private int amount;
    private int pos;
    private int investor_transferred_amount;
    private int penal_charges_waiver;
    private int overdue_charges_waiver;
    private int bounce_charges_waiver;
    private int fee_charges_waiver;
    private int total_outstanding_waiver;
    private int borrower_pos;
}
