package com.bbva.ccol.batch;

import com.bbva.ccol.lib.r007.CCOLR007;
import com.sun.org.apache.bcel.internal.generic.LOOKUPSWITCH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.ResourceSuffixCreator;

import java.util.HashMap;
import java.util.Map;

public class SuffixCreator implements ResourceSuffixCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuffixCreator.class);
    private CCOLR007 ccolR007;

    public CCOLR007 getCcolR007() {
        return ccolR007;
    }

    public void setCcolR007(CCOLR007 ccolR007) {
        this.ccolR007 = ccolR007;
    }
    @Override
    public String getSuffix(int i) {
        Map<Integer, String> departmentList = ccolR007.executeDistinctDepartment_2();
//        LOGGER.info("departmentList : {}",departmentList);
//departmentList = new HashMap<>();
//        Map<Integer, String> departmentList = new HashMap<>();
//        departmentList.put(1,"RRHH");
//        departmentList.put(2,"FINANZAS");
//        departmentList.put(3,"SISTEMAS");
//        departmentList.put(4,"ADMINISTRACION");
        return departmentList.get(i) + ".txt";

        /**
         *  if(i==1){
         *             return "RRHH" + i + ".txt";
         *         }else if(i==2){
         *             return "FINANZAS" + i + ".txt";
         *         }else if(i==3){
         *             return "SISTEMAS" + i + ".txt";
         *         }else{
         *             return "ADMINISTRACION" + i + ".txt";
         *         }
         *
         * */
    }
}
