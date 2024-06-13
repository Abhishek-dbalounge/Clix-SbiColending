package com.sbicolending.dao.impl;

import com.sbicolending.dao.CustomerDetailDao;
import com.sbicolending.model.CustomerDetailDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.sbicolending.utils.DBQuery;

import java.util.List;

@Repository
public class CustomerDetailDaoIMPL implements CustomerDetailDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CustomerDetailDataModel> getCustomerDetailsforLanSP(String lanSp) {

        List<CustomerDetailDataModel> customerDetailDataList = jdbcTemplate.query(DBQuery.CUSTOMER_DETAILS_DATA,new Object[] { lanSp}, new BeanPropertyRowMapper<>(CustomerDetailDataModel.class));

        return customerDetailDataList;
    }

}
