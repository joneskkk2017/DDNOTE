package com.jones.controller;

import com.github.pagehelper.PageInfo;
import com.jones.model.*;
import com.jones.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * adminController
 *
 * @author JoNeS
 * @date
 */
@Controller
public class adminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartService departService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;


    /**
     * description 跳转到admin页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/admin.html",method = RequestMethod.GET)
    public String admin(){
        return "admin/admin";
    }

    /**
     * description 跳转到用户管理页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/userManager.html",method = RequestMethod.GET)
    public String userM(Model model,Integer pageNum,Integer pageSize){
        //获取到所有的角色的记录信息,注入到User.jsp视图页面
        List<Role> roles = roleService.selectAll();
        model.addAttribute("allRoles",roles);

        //获取所有的部门信息
        List<Depart> departments = departService.selectAll();
        model.addAttribute("allDepartments",departments);

        //获取所有的区域信息
        List<Area> Areas = areaService.selectAll();
        model.addAttribute("allAreas",Areas);

        //注入用户信息
        //关联查询

        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        PageInfo<User> users = userService.selectUsersByPager(pageNum,pageSize);
        model.addAttribute("allUsers", users);
        return "admin/user";
    }

    /**
     * description 跳转到角色管理页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/roleManager.html",method = RequestMethod.GET)
    public String roleM(Model model,Integer pageNum,Integer pageSize){
        List<Resource> resourceList = resourceService.selectAll();
        model.addAttribute("resourceList",resourceList);
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        PageInfo<Role> allRoles = roleService.selectAllByPage(pageNum,pageSize);
        model.addAttribute("allRoles",allRoles);

        return "admin/role";
    }

    /**
     * description 跳转到资源管理页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/resourceManager.html",method = RequestMethod.GET)
    public String resourceM(Model model,Integer pageNum,Integer pageSize){
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        PageInfo<Resource> allReses = resourceService.selectAllByPage(pageNum,pageSize);
        model.addAttribute("allReses",allReses);
        return "admin/resource";
    }

    /**
     * description 跳转到部门管理页面
     *
     * @param model
     * @param pageNum
     * @param pageSize
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/deptManager.html",method = RequestMethod.GET)
    public String deptM(Model model,Integer pageNum,Integer pageSize){
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        PageInfo<Depart> allDept = departService.selectAllByPage(pageNum,pageSize);
        model.addAttribute("allDept",allDept);
        return "admin/dept";
    }

    @RequestMapping(value = "/admin/areaManager.html",method = RequestMethod.GET)
    public String areaM(Model model,Integer pageNum,Integer pageSize){
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        PageInfo<Area> allArea = areaService.selectAllByPage(pageNum,pageSize);
        model.addAttribute("allArea",allArea);

        return "admin/area";
    }

    /**
     * description 跳转到diaryManager页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/admin/diaryManager.html")
    public String  diaryManager(){


        return "admin/diary";
    }
}

