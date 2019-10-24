package com.jones.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UserResult
 *
 * @author JoNeS
 * @date
 */
@Getter
@Setter
@ToString
public class UserResult {
    private Integer userid;
    private String username;
    private Integer roleid;
    private String rolecode;
    private String rolename;
    private String rolepath;
    private Integer areaid;
    private String areaname;
}
