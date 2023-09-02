package com.example.todolist.util.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Objects;

@Slf4j
public class EnsureDataQuality {

    /**
     * Verifica la calidad de los datos de un objeto.
     *
     * @param object El objeto cuya calidad de datos se va a verificar.
     * @return true si los datos del objeto son de calidad, false en caso contrario.
     */
    public static Boolean check(Object object) {

        log.info(object.getClass().getName() + " checking data quality");
        if (ObjectUtils.isEmpty(object)) {
            log.error("Object is Empty: " + object);
            return false;
        }

        log.info("Checking is Object has null fields");
        var fieldsAsNull = new LinkedList<>();
        for (Field f : object.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (Objects.isNull(f.get(object)) || (f.getType().getName().equals("int")
                        && (Integer) f.get(object) == 0)) {
                    fieldsAsNull.add(f.getName());
                    log.warn(f.getName() + " IS NULL");
                }
            } catch (Exception e) {
                log.error("Error on check data quality" + e);
                return false;
            }
        }

        if (!fieldsAsNull.isEmpty()) {
            log.warn("field as null: " + fieldsAsNull);
            log.info(object.getClass().getName() + " end checked data quality");
            return false;
        }

        log.info(object.getClass().getName() + " end checked data quality");
        return true;
    }

}
