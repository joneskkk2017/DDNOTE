package com.jones.controller;

import com.github.pagehelper.PageInfo;
import com.jones.model.*;
import com.jones.service.AreaService;
import com.jones.service.DiaryService;
import com.jones.service.UserService;
import com.jones.utils.IpUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * indexController
 *
 * @author JoNeS
 * @date
 */
@Controller
public class indexController {

    @Autowired
    private UserService userService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private DiaryService diaryService;

    @RequestMapping("/index.html")
    public String index(Model model){
        //获取区域列表
        List<Area> areaList = areaService.selectAllBysort();

        model.addAttribute("areaList",areaList);
        //获取登录的用户信息
        User principal = (User)SecurityUtils.getSubject().getPrincipal();

        //获取登录用户的角色信息
        Role role = userService.getLogRoleByUid(principal.getId());

        //获取了所有的用户
        List<UserResult> users = userService.getUsersByRidpath(role.getIdpath()+"%");
        model.addAttribute("users",users);
        return "index";
    }

    /**
     * description 无权限时跳转的页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/unauthorizedUrl.html")
    public String unauthorizedUrl(){
        return "unauthorizedUrl";
    }

    /**
     * description 跳转到
     *
     * @param id
     * @return java.lang.String
     */
    @RequestMapping("/index/diaryList.html")
    public String getDiaryList(Integer id,Model model,Integer pageNum,Integer pageSize){
        System.out.println(id+":8888888888888888888888888888888888888");
        User user = userService.getUserByid(id);
        model.addAttribute("user", user);
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 31;
        }
        //通过uuuid查询日志记录
        PageInfo<Diary> diaryList = diaryService.selectDiaryByUid(id,pageNum,pageSize);
        model.addAttribute("diaryList",diaryList);
        return "index/diaryList";
    }

    /**
     * description 跳转回主页面
     *
     * @param
     * @return java.lang.String
     */
    @GetMapping("/index/index1.html")
    public String index1(){

        return "redirect:/index.html";
    }

    /**
     * description 修改密码
     *
     * @param password
     * @return java.lang.String
     */
    @PostMapping("/index/changeNewP.html")
    public String changeNewP(String password){
        //获取登录的用户信息
        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        userService.updateUserPassword(password.trim(),principal.getId());
        return "redirect:/index.html";
    }

    @PostMapping("/index/writeDiary.html")
    public String writeDiary(Diary diary, HttpServletRequest request){
        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        //时间转化
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        diary.setAddDate(date);
        diary.setClickcount(0);
        diary.setReadable(0);
        diary.setUuuid(principal.getId());
        diary.setIpaddress(IpUtil.getIpAddress(request));
        diary.setLookman("");
        System.out.println(diary+"555555555555555555555555555555555");
        diaryService.add(diary);

        return "redirect:/index/diaryList.html";
    }

    @ResponseBody
    @RequestMapping(value = "/index/checkContent.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String CheckContent(Integer id,HttpServletRequest request,String uname){
        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        String uuname="";
        Diary diary = diaryService.selectOne(id);
        User u = userService.selectOne(diary.getUuuid());
        if(diary.getLookman()!=null) {
            System.out.println(u.getUsername()+":"+principal.getUsername());
            //如果查看的人不在数据库里或者不等于登录的名字
            if (u.getUsername().equals(principal.getUsername()) == false && diary.getLookman().contains(uname) == false) {
                uuname = diary.getLookman() + "&nbsp;&nbsp;" + uname;
                Integer click = diary.getClickcount() + 1;
                diaryService.updateLookmanClick(uuname, click, id);
            } else {
                Integer click = diary.getClickcount() + 1;
                diaryService.updateClick(click, id);
            }
        }
        if("徐卫民".contains(uname)){
            if(diary.getReadable()==0){
                diaryService.updateReadalbe(diary.getId());
            }
        }
        //保存查看的人员
        return "<div class=\"modal-header\">\n" +
                "<button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span>&times;</span></button>\n" +
                "<h4 class=\"modal-title\" id=\"myModal3\">日志内容</h4>\n" +
                "</div>\n" +
                "<div class=\"modal-body\">\n" +
                diary.getDiarycontent()+
                "</div>\n" +
                " <div class=\"modal-footer\">\n" +
                "<div><strong>查看人员</strong>:"+diary.getLookman()+"</div>"+
                "<button id=\"closeCheckBtn\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n" +
                "</div>";
    }



    @RequestMapping("/index/searchdiary.html")
    public String searchdiary(String diaryDate,Integer pageNum,Integer pageSize,Model model){


        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        User user = userService.selectOne(principal.getId());
        model.addAttribute("user",user);
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 15;
        }
        if(diaryDate == "" || diaryDate == null){
            PageInfo<Diary> diaryList = diaryService.selectDiaryByUid(principal.getId(),pageNum,pageSize);
            model.addAttribute("diaryList",diaryList);
        }else{
            //通过uuuid和日期查询日志记录
            PageInfo<Diary> diaryList = diaryService.selectDiaryByDate(principal.getId(),diaryDate,pageNum,pageSize);
            model.addAttribute("diaryList",diaryList);
        }

        return "index/diaryList";
    }

    @RequestMapping("/index/diaryCount.html")
    public String DiaryCoun(Model model,String diaryDate,Integer pageNum,Integer pageSize){

//        model.addAttribute("diaryForDate",diaryDate);
//
//        System.out.println(diaryDate+"999999999999999999999999999999999999999");
//        if(pageNum == null || pageNum == 0){
//            pageNum = 1;
//        }
//        if(pageSize == null || pageSize == 0){
//            pageSize = 20;
//        }
//        System.out.println("pageNum:"+pageNum+",pageSize:"+pageSize);
//
//        PageInfo<DiaryCount> diaryCount = diaryService.selectCountDiary(diaryDate,pageNum,pageSize);
//
//        model.addAttribute("diaryCount",diaryCount);
        return "index/diaryCount";
    }


//    @PostMapping("/index/diaryCount.html")
//    public String diaryCount( Model model,Integer pageNum,Integer pageSize,String diaryDate){
//        if(pageNum == null || pageNum == 0){
//            pageNum = 1;
//        }
//        if(pageSize == null || pageSize == 0){
//            pageSize = 20;
//        }
//        if(diaryDate == null || diaryDate == ""){
//            PageInfo<DiaryCount> diaryCount = diaryService.selectCountDiaryByPage(pageNum,pageSize);
//            model.addAttribute("diaryCount",diaryCount);
//        }else{
//            PageInfo<DiaryCount> diaryCount = diaryService.selectCountDiary(diaryDate,pageNum,pageSize);
//            model.addAttribute("diaryCount",diaryCount);
//        }
//
//        return "index/diaryCount";
//    }


//    @GetMapping("/index/searchdiaryCount.html")
//    public String SerchDiaryCoun(Model model,Integer pageNum,Integer pageSize,String diaryDate){
//
//        if(pageNum == null || pageNum == 0){
//            pageNum = 1;
//        }
//        if(pageSize == null || pageSize == 0){
//            pageSize = 20;
//        }
//        PageInfo<DiaryCount> diaryCount = diaryService.selectCountDiary(diaryDate,pageNum,pageSize);
//
//        model.addAttribute("diaryCount",diaryCount);
//        return "index/diaryCount";
//    }

    @RequestMapping("/index/searchdiaryCount.html")
    public String searchdiaryCount(Model model,String diaryDate,Integer pageNum,Integer pageSize){


        if(diaryDate == null || diaryDate == ""){
            //当搜索框的日期为空或者为null的时候,搜索数据库最新的一条记录
            String str1 = diaryService.selectNewDate();
            System.out.println(diaryDate + "88888888888888888888888888888888888888888888888");

            if (pageNum == null || pageNum == 0) {
                pageNum = 1;
            }
            if (pageSize == null || pageSize == 0) {
                pageSize = 20;
            }
            System.out.println("pageNum:" + pageNum + ",pageSize:" + pageSize + ",str1:" + diaryDate);

            PageInfo<DiaryCount> diaryCount = diaryService.selectCountDiary(str1, pageNum, pageSize);

            model.addAttribute("diaryCount", diaryCount);

            return "index/diaryCount";
        }else{
            //当搜索框日期有值的时候,把值保存到数据库中,供没有值时候查看
            diaryDate=diaryDate.substring(0,7);

            System.out.println(diaryDate + "999999999999999999999999999999999999999");
            diaryService.Datesave(diaryDate);

            if (pageNum == null || pageNum == 0) {
                pageNum = 1;
            }
            if (pageSize == null || pageSize == 0) {
                pageSize = 20;
            }
            System.out.println("pageNum:" + pageNum + ",pageSize:" + pageSize + ",str1:" + diaryDate);

            PageInfo<DiaryCount> diaryCount = diaryService.selectCountDiary(diaryDate, pageNum, pageSize);

            model.addAttribute("diaryCount", diaryCount);

            return "index/diaryCount";
        }
    }
}

