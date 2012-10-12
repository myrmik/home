package com.asevastyanov.torrentEater.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Методы для работы с рефлексией
 *
 * @author Alien
 */
public final class ReflectUtils {
    private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * Возвращает HashMap с полями объекта. Для работы нужны валидные геттеры для поля.
     *
     * @param object объект, поля которого нужно переложить в HashMap
     * @return HashMap, содержащую поля объекта в формате Map&lt;имя_поля_в_апперкейс, значение_поля&gt;
     */
    public static HashMap getHMData(Object object) {
        HashMap result = new HashMap();

        Class clazz = object.getClass();
        for (Field field : collectFieldsInfo(clazz, true)) {
            try {
                String name = generateGetterName(field);
                Method method = clazz.getMethod(name);
                Object res = method.invoke(object);
                if ("boolean".equals(field.getType().toString())) {
                    result.put(field.getName().toUpperCase(), res.toString());
                } else {
                    result.put(field.getName().toUpperCase(), res);
                }
            } catch (Exception e) {
                logger.debug(e.toString(), e);
            }
        }

        return result;
    }

    /**
     * Заполняет поля объекта данными из HashMap. Для работы требуются валидные сеттеры для полей
     *
     * @param object объект, поля которого нужно заполнить
     * @param data   данные
     */
    public static void setHMData(Object object, HashMap data) {
        Class clazz = object.getClass();
        for (Field field : collectFieldsInfo(clazz, true)) {
            String name = null;
            Method method = null;
            Object param = null;
            try {
                name = generateSetterName(field.getName());
                method = clazz.getMethod(name, field.getType());
                param = data.get(field.getName().toUpperCase());
                if ("boolean".equals(field.getType().toString()) && param == null) {
                    method.invoke(object, new Object[]{false});
                } else {
                    method.invoke(object, new Object[]{param});
                }
            } catch (Exception e) {
                logger.debug("WARN: can't set field NAME=" + field.getName() + " in CLASS=" + clazz.getName() + " [setter=" + name + ", method=" + method + ", param=" + param + "]: " + e.toString(), e);
            }
        }
    }

    private static String generateGetterName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private static String generateGetterName(Field field) {
        String method;
        String fieldName = field.getName();
        if ("boolean".equals(field.getType().toString())) {
            return "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        } else {
            return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
    }

    private static String generateSetterName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private static List<Field> collectFieldsInfo(Class clazz, boolean recursive) {
        List<Field> fields;
        if (clazz == null || clazz.equals(Object.class)) {
            return new ArrayList<Field>();
        } else if (recursive) {
            fields = collectFieldsInfo(clazz.getSuperclass(), recursive);
        } else {
            fields = new ArrayList<Field>();
        }

        Collections.addAll(fields, clazz.getDeclaredFields());

        return fields;
    }
}
