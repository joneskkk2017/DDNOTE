package com.jones.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Diary
 *
 * @author JoNeS
 * @date
 */
@Getter
@Setter
@ToString
public class Diary {

    /*
        日志编号
     */
    private Integer id;

    /*
        日志标题
     */
    private String diarytitle;

    /*
        日志内容
     */
    private String diarycontent;

    /*
        0,未阅;1,已阅
     */
    private Integer readable;

    /*
        点击数
     */
    private Integer clickcount;

    /*
        日期
     */
    private Date addDate;

    private Integer uuuid;

    private String ipaddress;

    private String lookman;
}

