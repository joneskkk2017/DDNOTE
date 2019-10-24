package com.jones.service.impl;

import com.jones.dao.BaseDao;
import com.jones.service.BaseService;
import com.jones.utils.MapToEntityUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    //获取BaseDao对象的解决方案
    public abstract BaseDao getBaseDao();

    //需要获取到父类的泛型 T 到底是什么Class
    public Class<?> clazz;
    public String tableName;

    public BaseServiceImpl(){
        clazz = (Class<?>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments())[0];
        //获取数据表的名字
        tableName = "t_" + clazz.getSimpleName().toLowerCase();
    }

    @Override
    public void add(T t) {
        //数据表中的字段
        List<Object> list = new ArrayList<>();
        //把字段放入数组中
        for (Field field:t.getClass().getDeclaredFields()){
            //打开获取private修饰的属性的权限
            field.setAccessible(true);
            try {
                //获取字段的名字并放入list中
                list.add(field.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //调用BaseDao的add方法
        getBaseDao().add(tableName,list.toArray());
    }

    @Override
    public void addForNotMatch(Object[] fieldNames,Object[] fieldValues){
        getBaseDao().addForNotMatch(tableName,fieldNames,fieldValues);
    }

    @Override
    public void delete(int id) {

        getBaseDao().delete(tableName,id);

    }

    @Override
    public void update(T t) {
        int id = 0;
        //数据表中的字段
        List<Object> list = new ArrayList<>();
        //把字段放入数组中
        for (Field field:t.getClass().getDeclaredFields()){
            //打开获取private修饰的属性的权限
            field.setAccessible(true);
            try{
                if(field.get(t)==null){
                    continue;
                }
                // id不需要放到list集合中去
                if(("id").equals(field.getName())){
                    id =(int)field.get(t);
                    continue;
                }
                //剩下的字段才是要修改到数据库中的字段值,构造update中set后面的语句  拼接成: 变量名 = '值' 的形式
                list.add(field.getName()+"="+"'"+field.get(t)+"'");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        getBaseDao().update(tableName,id,list.toArray());
    }


    @Override
    public T selectOne(int id) {
        //结果是Map类型,但是该方法返回的是 T 类型,所以要把 Map 转换为 T
        Map<Object,Object> rsMap = getBaseDao().selectOne(tableName, id);
        T t = (T)MapToEntityUtil.mapToEntity(rsMap, clazz);
        return t;
    }

    @Override
    public List<T> selectAll() {
        List<Map<Object,Object>> rslist = getBaseDao().selectAll(tableName);
        List<T> list = new ArrayList<>();
        T t = null;
        for(Map<Object,Object> map:rslist){
            //把rslist集合中 Map类型 转换为 T类型
            t = (T) MapToEntityUtil.mapToEntity(map,clazz);
            list.add(t);
        }
        return list;
    }
}

