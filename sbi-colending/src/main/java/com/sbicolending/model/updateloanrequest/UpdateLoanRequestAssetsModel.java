package com.sbicolending.model.updateloanrequest;

import lombok.Data;

@Data
public class UpdateLoanRequestAssetsModel {


    private float sanction_ltv;
    private String collateral_created_date;//(Date)
    private String cersai_date;//(Date)
    private float security_valuation;//(flot)
    private String survey_or_gat_number;
    private String bound_by_north;
    private String bound_by_south;
    private String bound_by_east;
    private String bound_by_west;
    private int purchase_cost;//(int)
    private String purchase_date;//(Date)
    private String carpet_area_unit;
    private String plot_id_number;
    private String property_nature;
    private String property_locality;
    private String valuation_date;//(Date)

}
