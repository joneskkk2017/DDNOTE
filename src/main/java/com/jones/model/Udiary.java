package com.jones.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Udiary
 *
 * @author JoNeS
 * @date
 */
@Getter
@Setter
@ToString
public class Udiary {

    Integer uid;

    String username;

    Integer diaryid;

    String diarytitle;

    String diarycontent;

    Date adddate;

}
