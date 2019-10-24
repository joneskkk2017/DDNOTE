package com.jones.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jones.dao.BaseDao;
import com.jones.dao.DiaryDao;
import com.jones.model.Diary;
import com.jones.model.DiaryCount;
import com.jones.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * DiaryServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("diaryService")
public class DiaryServiceImpl extends BaseServiceImpl<Diary> implements DiaryService {

    @Autowired
    private DiaryDao diaryDao;

    @Override
    public BaseDao getBaseDao() {
        return diaryDao;
    }

    /**
     * description 添加日志
     *
     * @param id
     * @param title
     * @param content
     * @return void
     */
    @Override
    public void addDiary(Integer id, String title, String content) {
        //时间转化
        Date date = new Date();
        long l = date.getTime();
        this.addForNotMatch(new Object[]{"diarytitle","diarycontent","readable","clickcount","add_date","uuuid"},
                new Object[]{title,content,0,0,new java.sql.Date(l),id});

    }

    @Override
    public PageInfo<Diary> selectDiaryByUid(Integer id, Integer pageNum, Integer pageSize) {

        Page<Diary> pager= PageHelper.startPage(pageNum,pageSize);

        List<Diary> diaryList = diaryDao.selectDiaryByUid(id);

        PageInfo<Diary> info = new PageInfo<>(diaryList);

        return info;
    }

    /**
     * description 通过id更新readable
     *
     * @param id
     * @return void
     */
    @Override
    public void updateReadalbe(Integer id) {

        diaryDao.updateReadalbe(id);
    }

    @Override
    public PageInfo<Diary> selectDiaryByDate(Integer id, String diaryDate, Integer pageNum, Integer pageSize) {

        Page<Diary> pager= PageHelper.startPage(pageNum,pageSize);

        List<Diary> diaryList = diaryDao.selectDiaryByDate(id,diaryDate);

        PageInfo<Diary> info = new PageInfo<>(diaryList);

        return info;
    }

    @Override
    public PageInfo<DiaryCount> selectCountDiary(String diaryDate,Integer pageNum,Integer pageSize) {

        Page<DiaryCount> pager = PageHelper.startPage(pageNum,pageSize);

        List<DiaryCount> diaryCount = diaryDao.selectCountDiary(diaryDate);

        PageInfo<DiaryCount> info = new PageInfo<>(diaryCount);

        return info;
    }

    @Override
    public PageInfo<DiaryCount> selectCountDiaryByPage(Integer pageNum, Integer pageSize) {

        Page<DiaryCount> pager = PageHelper.startPage(pageNum,pageSize);

        List<DiaryCount> diaryCount = diaryDao.selectCountDiaryByPage();

        PageInfo<DiaryCount> info = new PageInfo<>(diaryCount);
        return info;
    }

    @Override
    public void Datesave(String diaryDate) {
        diaryDao.Datesave(diaryDate);
    }

    @Override
    public String selectNewDate() {
        return diaryDao.selectNewDate();
    }

    @Override
    public void updateClick(Integer click,Integer id) {
        diaryDao.updateClick(click,id);
    }

    @Override
    public void updateLookman(String s,Integer id) {
        diaryDao.updateLookman(s,id);
    }

    /**
     * description 更新查看人员,点击数
     *
      * @param uuname
     * @param click
     * @param id
     * @return void
     */
    @Override
    public void updateLookmanClick(String uuname, Integer click,Integer id) {
        diaryDao.updateLookmanClick(uuname,click,id);
    }
}
