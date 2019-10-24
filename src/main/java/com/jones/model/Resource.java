package com.jones.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Resource
 *
 * @author JoNeS
 * @date
 */
@Getter
@Setter
@ToString
public class Resource {
    private Integer id;
    private String pathname;
    private String path;

    public Resource(){

    }

    public Resource(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object obj) {
        //判断两个role对象是不是相等的规则重写,只要id值一样就认为一样
        if(this == obj) {
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Resource)){
            return  false;
        }
        Resource other = (Resource) obj;
        if(!id.equals(other.id)){
            return  false;
        }
        return true;
    }
}

