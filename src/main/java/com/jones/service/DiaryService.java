package com.jones.service;

import com.github.pagehelper.PageInfo;
import com.jones.model.Diary;
import com.jones.model.DiaryCount;

/**
 * DiaryService
 *
 * @author JoNeS
 * @date
 */
public interface DiaryService extends BaseService<Diary>{

    /**
     * description 添加日志
     *
     * @param id
     * @param title
     * @param content
     * @return void
     */
    void addDiary(Integer id, String title, String content);

    /**
     * description 通过uuuid查询工作日志信息
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.jones.model.Diary>
     */
    PageInfo<Diary> selectDiaryByUid(Integer id, Integer pageNum, Integer pageSize);

    /**
     * description 通过id更新readable
     *
     * @param id
     * @return void
     */
    void updateReadalbe(Integer id);

    /**
     * description 通过uuuid和日期查询数据
     *
     * @param id
     * @param diaryDate
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.jones.model.Diary>
     */
    PageInfo<Diary> selectDiaryByDate(Integer id, String diaryDate, Integer pageNum, Integer pageSize);

    /**
     * description 统计日志
     *
     * @param
     * @return com.jones.model.DiaryCount
     */
    PageInfo<DiaryCount> selectCountDiary(String diaryDate,Integer pageNum, Integer pageSize);


    PageInfo<DiaryCount> selectCountDiaryByPage(Integer pageNum, Integer pageSize);

    void Datesave(String diaryDate);

    String selectNewDate();

    /**
     * description 点击数+1
     *
     * @param id
     * @return void
     */
    void updateClick(Integer click,Integer id);

    /**
     * description 保存查看人员
     *
      * @param s
     * @return void
     */
    void updateLookman(String s,Integer id);

    /**
     * description 更新点击数和查看人员
     *
      * @param uuname
     * @param id
     * @return void
     */
    void updateLookmanClick(String uuname, Integer click,Integer id);
}

