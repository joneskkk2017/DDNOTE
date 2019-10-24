package com.jones.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapToEntityUtil {

    //缓存类的属性信息
    private static  Map<String,EntityCacheItem> convertItemCache = new HashMap<>();

    /**
     *  @Description TODO 具体的转换方法实现
     *  @Param  [map, entityClass]
     *  @Return T
     *  @Date 2019/9/2 15:24
     *  @Author  61044
     */
    public static <T> T mapToEntity(Map<Object,Object> map,Class<T> entityClass){
        //尝试从缓存中获取EntityCacheItem对象
        EntityCacheItem entityCacheItem = convertItemCache.get(entityClass.getName());
        if(entityCacheItem == null){
            entityCacheItem = EntityCacheItem.createConvertItem(entityClass);
            convertItemCache.put(entityClass.getName(),entityCacheItem);
        }
        //通过entityClass拿到参数传来的对象的属性名称List集合
        List<String> fieldNameList = entityCacheItem.getFieldNameList();
        //通过entityClass拿到参数传来的对象的set方法Map集合
        Map<String, Method> setMethodMap = entityCacheItem.getSetMethodMap();

        //数据库中带有下划线_的字段,把下划线去了重新拼接
        String key;
        String key1;
        String key2;
        Map<Object,Object> targetMap = new HashMap<>();
        for (Map.Entry<Object,Object> entry:map.entrySet()){
            //add_date
            key = entry.getKey().toString();
            while (key.contains("_")){
                key1 = key.substring(0,key.indexOf("_"));//add
                key2 = key.substring(key.indexOf("_")+1);//date
                key2 = key2.substring(0,1).toUpperCase()+key2.substring(1);//Date
                key = key1 + key2;//addDate
            }
            targetMap.put(key,entry.getValue());
        }

        T entity =null;
        try {
            entity = entityClass.newInstance(); //通过反射的方法,获取这个类型的一个对象
        } catch (Exception e)  {
            e.printStackTrace();
        }

        Object mapFieldValue;
        Method setMethod1;
        Class<?>[] parameterTypes = null;
        for(String fieldName1 :fieldNameList){
            mapFieldValue = targetMap.get(fieldName1);
            if(mapFieldValue == null) { continue; }

            setMethod1 = setMethodMap.get(fieldName1);
            if(setMethod1 == null){ continue; }
            //获取set方法中参数类型的对象,参数只能有一个
            parameterTypes = setMethod1.getParameterTypes();
            if(parameterTypes ==null || parameterTypes.length>1){
                continue;
            }
            // set方法中的参数类型和mapFieldValue中的字段值类型是否一致
            if(parameterTypes[0].isAssignableFrom(mapFieldValue.getClass())){
                //若一致
                try {
                    //调用了对象的set方法,把属性值注入
                    setMethod1.invoke(entity,mapFieldValue);
                } catch (Exception e)  {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return entity;
    }

    static class EntityCacheItem{
        //构造器私有化,只能通过下面的createConvertltem方法
        private EntityCacheItem(){}

        private List<String> fieldNameList = new ArrayList<>();
        private Map<String,Method> setMethodMap = new HashMap<>();

        public List<String> getFieldNameList(){
            return fieldNameList;
        }
        public Map<String,Method> getSetMethodMap(){
            return setMethodMap;
        }
        //定义一个方法
        public void parseEntity(Class<?> entityClass){

            Field[] fields = entityClass.getDeclaredFields();
            String fieldName;
            String setMethodName;
            Method setMethod = null;
            for (Field field:fields){
                field.setAccessible(true);
                fieldName = field.getName(); //拿到属性对象对应的名称
                fieldNameList.add(fieldName);
                //拼接方法名称,把属性名第一个字母大写然后拼接剩下的,如果属性名称为age,则拼接成setAge
                setMethodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                try {
                    setMethod = entityClass.getDeclaredMethod(setMethodName,field.getType());
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                setMethodMap.put(fieldName,setMethod);
            }
        }
        //静态创建内部类EntityCacheItem
        public static EntityCacheItem createConvertItem(Class<?> cls){
            EntityCacheItem ci = new EntityCacheItem();
            ci.parseEntity(cls);
            return ci;
        }
    }
}

