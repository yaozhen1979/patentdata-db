package com.uxb2b.anz02.helper;

import java.util.List;

import com.uxb2b.anz02.model.FunctionData;
import com.uxb2b.hibernate.session.QueryHelper;

public class FunctionDataHelper {

    public static List<FunctionData> queryAll() {
        QueryHelper helper = new QueryHelper("FunctionData a");
        helper.order("a.orderIndex");
        return helper.query();
    }
    
    public static FunctionData findByFunctionId(String functionId) {
        QueryHelper helper = new QueryHelper("FunctionData a");
        helper.eq("a.functionId", functionId);
        List<FunctionData> func = helper.query();
        if (func.size() > 0) {
            return func.get(0);
        } else {
            return null;
        }
    }
}
