package com.rengu.util;

import com.rengu.entity.RG_TyperescourceEntity;

/**
 * Created by hanchangming on 2017/5/27.
 */
public class EntityConvertToSQL {
    public static String insertSQL(RG_TyperescourceEntity rg_typerescourceEntity) {
        String SQLString = "INSERT INTO '" + Info.APS_TYPERESOURCE + "' (id,name,ratio) VALUES (" + rg_typerescourceEntity.getId() + "," + rg_typerescourceEntity.getName() + "," + rg_typerescourceEntity.getRatio() + ")";
        return SQLString;
    }

    public static String deleteSQL(RG_TyperescourceEntity rg_typerescourceEntity) {
        String SQLString = "DELETE FROM " + Info.APS_TYPERESOURCE + " WHERE id = '" + rg_typerescourceEntity.getId() + "'";
        return SQLString;
    }
}
