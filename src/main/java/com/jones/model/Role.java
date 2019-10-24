package com.jones.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Role
 *
 * @author JoNeS
 * @date
 */@Getter
@Setter
@ToString
public class Role {
    private Integer id;
    private String name;
    private String code;
    private String idpath;
    private List<Resource> resources;

    @Override
    public boolean equals(Object obj) {
        //判断两个role对象是不是相等的规则重写,只要id值一样就认为一样
        if(this == obj) {
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Role)){
            return  false;
        }
        Role other = (Role) obj;
        if(!id.equals(other.id)){
            return  false;
        }
        return true;
    }
}

