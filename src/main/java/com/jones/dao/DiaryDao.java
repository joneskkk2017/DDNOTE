package com.jones.dao;

import com.jones.model.Diary;
import com.jones.model.DiaryCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DiaryDao
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface DiaryDao  extends BaseDao{


    List<Diary> selectDiaryByUid(@Param("uuuid") Integer uuuid);

    /**
     * description 通过id更新readable
     *
     * @param id
     * @return void
     */
    void updateReadalbe(@Param("id") Integer id);


    List<Diary> selectDiaryByDate(@Param("id") Integer id, @Param("diaryDate") String diaryDate);


    List<DiaryCount> selectCountDiary(@Param("diaryDate") String diaryDate);


    List<DiaryCount> selectCountDiaryByPage();

    void Datesave(@Param("diaryDate") String diaryDate);

    String selectNewDate();

    void updateClick(@Param("click") Integer click,@Param("id") Integer id);

    void updateLookman(@Param("uname") String uname,@Param("id") Integer id);

    void updateLookmanClick(@Param("uuname") String uuname, @Param("click") Integer click, @Param("id") Integer id);
}

