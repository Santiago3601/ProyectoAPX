package com.bbva.ccol.lib.r002.impl.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DefVals {
    public static final  String getInvocationDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return  sdf.format(timestamp);
    }


    public static final  Long disableValueStatus(){
        return  0L;
    }
}
