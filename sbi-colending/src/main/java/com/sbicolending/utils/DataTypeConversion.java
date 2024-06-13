package com.sbicolending.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataTypeConversion {

    public int getStringFromInt(String stringVal)  {
        int convarteVal = 0;

            if (stringVal == null || stringVal.trim().isEmpty()) {

                return convarteVal;
            }else {

                int intVal = Integer.parseInt(stringVal);
                convarteVal = intVal;
            }

        return convarteVal;
    }


    public float getIntegerFromFloat(Integer intVal) {

        float convertVal = 0f;

            if(intVal == null || StringUtils.isEmpty(intVal) ){

                return convertVal;
            }else {
                float floatVal = (float) intVal;
                convertVal =floatVal;
            }
        return convertVal;
    }


    public Float getStringFromFloat(String stringVal) {


        Float convertVal = 0f;

        if(stringVal == null || StringUtils.isEmpty(stringVal) || stringVal.trim().isEmpty()){

            return convertVal;
        }else {
            float floatValue = Float.parseFloat(stringVal);
            convertVal =floatValue;
        }

        return convertVal;

    }


    public int getDoubletoFromInt(Double doubleVal) {

        int convertVal = 0;

            if (doubleVal == null || StringUtils.isEmpty(doubleVal)) {

                return convertVal;
            } else {
                int value1 = (int)Math.round(doubleVal);
                convertVal = value1;
            }
        return convertVal;
    }


    public Date getDateFromString(String formatOfDate,String value) throws ParseException {
        if(StringUtils.isEmpty(value)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatOfDate);
        sdf.setLenient(false);
        Date date = sdf.parse(value);
        return date;
    }

    public String getStringFromDate(String formatOfDate,Date value) throws ParseException {
        if(value == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatOfDate);
        sdf.setLenient(false);
        String date = sdf.format(value);
        return date;
    }

    public Long getStringFromLong(String stringVal) {

        long convarteVal = 0l;

        if (stringVal == null || stringVal.trim().isEmpty()) {

            return convarteVal;
        }else {

            Long intVal = Long.parseLong(stringVal);
            convarteVal = intVal;
        }

        return convarteVal;
    }




//=========UpdateCustomerDetails====================================================


    public String getIntFromString(Integer intVal)  {
        String convarteVal = null;

        if (intVal == null || StringUtils.isEmpty(intVal)) {

            return convarteVal;
        }else {

            String stringVal = String.valueOf(intVal);
            convarteVal = stringVal;
        }

        return convarteVal;
    }


    public String getStringFromFloat(Float floatVal) {


        String convertVal = null;

        if(floatVal == null || StringUtils.isEmpty(floatVal)){

            return convertVal;
        }else {
            String stringVal = String.valueOf(floatVal);
            convertVal =stringVal;
        }

        return convertVal;

    }

   /* public float getFloatFromInteger(Float floatVal) {

        Integer convertVal = 0;

        if(floatVal == null || StringUtils.isEmpty(floatVal) ){

            return convertVal;
        }else {


            int value = (int) floatVal;
            int floatVal1= (int) floatVal;
            convertVal =floatVal;

            Integer integerVal = (Integer) floatVal;
            convertVal =floatVal;
        }
        return convertVal;
    }*/

}
