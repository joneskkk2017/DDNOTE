package com.jones.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Department 部门表
 *
 * @author JoNeS
 * @date
 */
@Getter
@Setter
@ToString
public class Depart {

    private Integer id;

    private  String departname;

    @Override
    public boolean equals(Object obj) {
        //判断两个role对象是不是相等的规则重写,只要id值一样就认为一样
        if(this == obj) {
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Depart)){
            return  false;
        }
        Depart other = (Depart) obj;
        if(!id.equals(other.id)){
            return  false;
        }
        return true;
    }
}

