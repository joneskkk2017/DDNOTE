package com.jones.controller;

import com.jones.model.Resource;
import com.jones.model.Role;
import com.jones.service.ResourceService;
import com.jones.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * RoleController
 *
 * @author JoNeS
 * @date
 */
@Controller
public class roleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    /**
     * description  添加角色
     *
     * @param role
     * @return java.lang.String
     */
    @RequestMapping("/admin/addRole.html")
    public String addRole(Role role,Integer[] resourcesIds){

        if(resourcesIds != null){
            roleService.addRole(role,resourcesIds);
        }else{
            roleService.addRole1(role);
        }


        return "redirect:/admin/roleManager.html";
    }

    /**
     * description 弹出编辑角色框并加载对应信息
     *
     * @param id
     * @param request
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/admin/updateRole.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String updateRole(Integer id, HttpServletRequest request){
        System.out.println(id+"---------------------------------");
        //角色信息
        Role role = roleService.selectRoleResById(id);
        List<Resource> resourceList1 = role.getResources();

        //获取到所有资源信息
        List<Resource> resourceList = resourceService.selectAll();
        String str2 = "";
        if(resourceList1.size() != 0) {
            for (Resource res : resourceList) {
                if (resourceList1.contains(res)) {
                    str2 = str2 + "<option selected value=\"" + res.getId() + "\">" + res.getPathname() + "</option>\n";
                } else {
                    str2 = str2 + "<option value=\"" + res.getId() + "\">" + res.getPathname() + "</option>\n";
                }
            }
        }else{
            for (Resource res1 : resourceList) {
                str2 = str2 + "<option value=\"" + res1.getId() + "\">" + res1.getPathname() + "</option>\n";
            }
        }
        String path = request.getContextPath();
        return  "<div class=\"modal-header\">\n" +
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span>&times;</span></button>\n" +
                "                <h4 class=\"modal-title\" id=\"myModal2\">编辑角色</h4>\n" +
                "            </div>\n" +
                "            <div class=\"modal-body\">\n" +
                "                <form id=\"updateRoleForm\" action=\""+path+"/admin/updateRole.html\" method=\"post\">\n" +
                "                    <input type='hidden' name='id' value='"+role.getId()+"' />"+
                "                    <div class=\"form-group\" >\n" +
                "                        <label>角色名:</label>\n" +
                "                        <input id=\"inp2\" type=\"text\" name=\"name\" disabled class=\"form-control\" value=\""+role.getName()+"\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>Code:</label>\n" +
                "                        <input type=\"text\" name=\"code\" class=\"form-control\" value=\""+role.getCode()+"\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>Code:</label>\n" +
                "                        <input type=\"text\" name=\"idpath\" class=\"form-control\" value=\""+role.getIdpath()+"\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>权限描述:</label>\n" +
                "                        <select name=\"resourceIds\" class=\"selectpicker form-control\" multiple>  //支持多选,后面加multiple  自带搜索功能data-live-search=\"true\"\n" +
                str2   +
                "                        </select>\n" +
                "                    </div>\n" +
                "                </form>\n" +
                "            </div> " +
                "            <div class=\"modal-footer\">\n" +
                "                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n" +
                "                <button onclick=\"updateRoleFormSubmit()\" type=\"button\" class=\"btn btn-primary\">编辑角色</button>\n" +
                "            </div>";
    }

    /**
     * description 提交更新角色
     *
     * @param role
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/updateRole.html",method = RequestMethod.POST)
    public String updateRole(Role role,Integer[] resourceIds){
        roleService.updateRoleRes(role,resourceIds);
        return "redirect:/admin/roleManager.html";
    }

    /**
     * description 删除角色及其关联信息
     *
     * @param id
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/delRole.html",method = RequestMethod.GET)
    public String delRole(Integer id){
        roleService.delRoleRes(id);
        roleService.delRoleUser(id);
        roleService.delete(id);
        return "redirect:/admin/roleManager.html";
    }
}

