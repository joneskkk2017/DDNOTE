package com.jones.controller;

import com.jones.model.Depart;
import com.jones.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * deptController
 *
 * @author JoNeS
 * @date
 */
@Controller
public class deptController {

    @Autowired
    private DepartService departService;

    /**
     * description  添加部门
     *
     * @param depart
     * @return java.lang.String
     */
    @RequestMapping("/admin/addDept.html")
    public String addDept(Depart depart){
        departService.addForNotMatch(new Object[]{"departname"},new Object[]{depart.getDepartname()});

        return "redirect:/admin/deptManager.html";
    }

    /**
     * description 弹出编辑部门框并加载对应信息
     *
     * @param id
     * @param request
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/admin/updateDept.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String updateResource(Integer id, HttpServletRequest request){
        Depart depart = departService.selectOne(id);
        String path = request.getContextPath();
        return  "<div class=\"modal-header\">\n" +
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span>&times;</span></button>\n" +
                "                <h4 class=\"modal-title\" id=\"myModal2\">编辑部门</h4>\n" +
                "            </div>\n" +
                "            <div class=\"modal-body\">\n" +
                "                <form id=\"updateDeptForm\" action=\""+path+"/admin/updateDept.html\" method=\"post\">\n" +
                "                    <input type='hidden' name='id' value='"+depart.getId()+"' />"+
                "                    <div class=\"form-group\" >\n" +
                "                        <label>资源描述:</label>\n" +
                "                        <input id=\"inp2\" type=\"text\" name=\"departname\" class=\"form-control\" value=\""+depart.getDepartname()+"\" />\n" +
                "                    </div>\n" +
                "                </form>\n" +
                "            </div> " +
                "            <div class=\"modal-footer\">\n" +
                "                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n" +
                "                <button onclick=\"updateDeptFormSubmit()\" type=\"button\" class=\"btn btn-primary\">编辑部门</button>\n" +
                "            </div>";
    }

    /**
     * description 提交更新部门
     *
     * @param depart
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/updateDept.html",method = RequestMethod.POST)
    public String updateDept(Depart depart){
        departService.update(depart);
        return "redirect:/admin/deptManager.html";
    }

    /**
     * description 删除部门及其关联信息
     *
     * @param id
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/delDept.html",method = RequestMethod.GET)
    public String delDept(Integer id){
        departService.delDeptUserRes(id);
        departService.delete(id);
        return "redirect:/admin/deptManager.html";
    }
}

