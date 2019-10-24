package com.jones.controller;

import com.github.pagehelper.PageInfo;
import com.jones.model.*;
import com.jones.service.AreaService;
import com.jones.service.DepartService;
import com.jones.service.RoleService;
import com.jones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * UserController
 *
 * @author JoNeS
 * @date
 */
@Controller
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private DepartService departService;


    /**
     * description 添加用户
     *
     * @param user
     * @param roleIds
     * @param departmentId
     * @param areaId
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/addUser.html",method = RequestMethod.POST)
    public String addUser(User user,Integer[] roleIds,Integer departmentId,Integer areaId){

        //获取表单信息
        userService.addUser(user, roleIds, departmentId, areaId);
        return "redirect:/admin/userManager.html";
    }

    /**
     * description 编辑用户
     *
     * @param id
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/admin/updateUser.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String updateUser(Integer id, HttpServletRequest request) {
        User user = userService.SelectRelUserByUid(id);
        List<Role> roles = user.getRoles();
        Area areas = user.getArea();
        Depart departments = user.getDepartment();
        //获取到所有的role信息
        List<Role> allRoles = roleService.selectAll();
        String str1 = "";
        if(roles.size()!=0) {
            for (Role role : allRoles) {
                if (roles.contains(role)) {
                    str1 = str1 + "<option selected value=\"" + role.getId() + "\">" + role.getName() + "</option>\n";
                } else {
                    str1 = str1 + "<option value=\"" + role.getId() + "\">" + role.getName() + "</option>\n";
                }
            }
        }else{
            for (Role role : allRoles) {
                str1 = str1 + "<option value=\"" + role.getId() + "\">" + role.getName() + "</option>\n";
            }
        }

        //获取到所有部门信息
        List<Depart> allDeparts = departService.selectAll();
        String str3 = "";
        if (departments != null) {
            for (Depart de : allDeparts) {
                if (departments.getId().equals(de.getId())) {
                    str3 = str3 + "<option selected value=\"" + de.getId() + "\">" + de.getDepartname() + "</option>\n";
                } else {
                    str3 = str3 + "<option value=\"" + de.getId() + "\">" + de.getDepartname() + "</option>\n";
                }
            }
        }else{
            for (Depart de : allDeparts) {
                str3 = str3 + "<option value=\"" + de.getId() + "\">" + de.getDepartname() + "</option>\n";
            }
        }
        //获取到所有区域信息
        List<Area> allAreas = areaService.selectAll();
        String str2 = "";
        if (areas != null) {
            for (Area ar : allAreas) {
                if (areas.getId().equals(ar.getId())) {
                    str2 = str2 + "<option selected value=\"" + ar.getId() + "\">" + ar.getAreaname() + "</option>\n";
                } else {
                    str2 = str2 + "<option value=\"" + ar.getId() + "\">" + ar.getAreaname() + "</option>\n";
                }
            }
        }else{
            for (Area ar : allAreas) {
                str2 = str2 + "<option value=\"" + ar.getId() + "\">" + ar.getAreaname() + "</option>\n";
            }
        }

        String path = request.getContextPath();
        return "<div class=\"modal-header\">\n" +
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span>&times;</span></button>\n" +
                "                <h4 class=\"modal-title\" id=\"myModal2\">编辑用户</h4>\n" +
                "            </div>\n" +
                "            <div class=\"modal-body\">\n" +
                "                <form id=\"updateUserForm\" action=\"" + path + "/admin/updateUser.html\" method=\"post\">\n" +
                "                    <input type='hidden' name='id' value='" + user.getId() + "' />" +
                "                    <div class=\"form-group\" >\n" +
                "                        <label>用户名:</label>\n" +
                "                        <input id=\"inp1\" type=\"text\" name=\"username\" disabled class=\"form-control\" value=\"" + user.getUsername() + "\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>密 码:</label>\n" +
                "                        <input type=\"text\" name=\"password\" class=\"form-control\" value=\"" + user.getPassword() + "\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>手 机:</label>\n" +
                "                        <input type=\"text\" name=\"telephone\" class=\"form-control\" value=\"" + user.getTelephone() + "\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>部 门:</label>\n" +
                "                        <select  name=\"departmentId\" class=\"selectpicker form-control\">  //支持多选,后面加multiple  自带搜索功能data-live-search=\"true\"\n" +
                str3 +
                "                        </select>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>区 域:</label>\n" +
                "                        <select name=\"areaId\" class=\"selectpicker form-control\">  //支持多选,后面加multiple  自带搜索功能data-live-search=\"true\"\n" +
                str2 +
                "                        </select>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>角色:</label>\n" +
                "                        <select name=\"roleIds\" class=\"selectpicker form-control\" multiple>  //支持多选,后面加multiple  自带搜索功能data-live-search=\"true\"\n" +
                str1 +
                "                        </select>\n" +
                "                    </div>\n" +
                "                </form>\n" +
                "            </div> " +
                "            <div class=\"modal-footer\">\n" +
                "                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n" +
                "                <button onclick=\"updateUserFormSubmit()\" type=\"button\" class=\"btn btn-primary\">编辑用户</button>\n" +
                "            </div>";

    }


    /**
     * description 更新用户
     *
     * @param user
     * @param roleIds
     * @param areaId
     * @param departmentId
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/updateUser.html",method = RequestMethod.POST)
    public String updateUser(User user,Integer[] roleIds,Integer areaId,Integer departmentId){
        userService.updateUser(user,roleIds,areaId,departmentId);
        return "redirect:/admin/userManager.html";
    }

    /**
     * description 删除用户
     *
     * @param id
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/delUser.html",method = RequestMethod.GET)
    public String delUser(Integer id){
        userService.deleteByUidRelRole(id);
        userService.deleteByUidRelArea(id);
        userService.deleteByUidRelDept(id);
        userService.delete(id);
        return "redirect:/admin/userManager.html";
    }

    /**
     * description 批量删除用户
     *
     * @param uid
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/admin/batchDelUsers.html",method = RequestMethod.POST)
    public String batchDelUsers(String uid){
        //去掉字符串中的[ ]
        uid = uid.substring(1,uid.length()-1);
        uid = uid.replaceAll("\"","");
        //变成字符串数组
        String[] strings = uid.split(",");
        Integer[] uidArr = new Integer[strings.length];
        //变成整形数组
        for(int i=0;i<strings.length;i++){
            uidArr[i] = Integer.parseInt(strings[i]);
        }

        userService.batchDelUsersByIds(uidArr);
        return "success";
    }

    /**
     * description 根据名字查询用户信息
     *
     * @param userInfo
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/userSearch.html",method = RequestMethod.POST)
    public String searchUsers(String userInfo,Model model){

//        //获取到所有的角色的记录信息,注入到User.jsp视图页面
//        List<Role> roles = roleService.selectAll();
//        model.addAttribute("allRoles",roles);
//
//        //获取所有的部门信息
//        List<Depart> departments = departService.selectAll();
//        model.addAttribute("allDepartments",departments);
//
//        //获取所有的区域信息
//        List<Area> Areas = areaService.selectAll();
//        model.addAttribute("allAreas",Areas);
        //注入用户信息
        //关联查询
        int pageNum = 1;
        int pageSize = 10;
        PageInfo<User> users = userService.selectUsersBySearch(pageNum,pageSize,userInfo);
        System.out.println("++++++++++++++++++++++"+users);
        model.addAttribute("allUsers", users);
        return "admin/user";
    }

    @RequestMapping("/admin/userSearchByUidByDate.html")
    public String userSearchByUidByDate(String userInfo,String dateInfo,Integer pageNum,Integer pageSize,Model model){

        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 20;
        }

        System.out.println("userInfo:"+userInfo+",dateInfo:"+dateInfo);
        //通过名字搜索该用户
        User user = userService.selectUserByName(userInfo);
        System.out.println(user);
        PageInfo<Udiary> udiarys = userService.selectDiaryByUidByDate(userInfo,dateInfo,pageNum,pageSize);
        model.addAttribute("udiarys",udiarys);

        return "admin/diary";
    }

    /**
     * description 删除日志
     *
     * @param id
     * @return java.lang.String
     */
    @RequestMapping("/admin/deldiary.html")
    public String deldiary(Integer id){

        userService.deldiaryByid(id);

        return "redirect:/admin/diaryManager.html";
    }
}
























