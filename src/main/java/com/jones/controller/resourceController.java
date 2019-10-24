package com.jones.controller;

import com.jones.model.Resource;
import com.jones.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ResourceController
 *
 * @author JoNeS
 * @date
 */
@Controller
public class resourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * description  添加角色
     *
     * @param resource
     * @return java.lang.String
     */
    @RequestMapping("/admin/addResource.html")
    public String addResource(Resource resource){
        resourceService.addForNotMatch(new Object[]{"pathname","path"},new Object[]{resource.getPathname(),resource.getPath()});

        return "redirect:/admin/resourceManager.html";
    }

    /**
     * description 弹出编辑角色框并加载对应信息
     *
     * @param id
     * @param request
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/admin/updateResource.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String updateResource(Integer id, HttpServletRequest request){
        Resource resource = resourceService.selectOne(id);
        String path = request.getContextPath();
        return  "<div class=\"modal-header\">\n" +
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span>&times;</span></button>\n" +
                "                <h4 class=\"modal-title\" id=\"myModal2\">编辑资源</h4>\n" +
                "            </div>\n" +
                "            <div class=\"modal-body\">\n" +
                "                <form id=\"updateResourceForm\" action=\""+path+"/admin/updateResource.html\" method=\"post\">\n" +
                "                    <input type='hidden' name='id' value='"+resource.getId()+"' />"+
                "                    <div class=\"form-group\" >\n" +
                "                        <label>资源描述:</label>\n" +
                "                        <input id=\"inp2\" type=\"text\" name=\"pathname\" disabled class=\"form-control\" value=\""+resource.getPathname()+"\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label>Code:</label>\n" +
                "                        <input type=\"text\" name=\"path\" class=\"form-control\" value=\""+resource.getPath()+"\" />\n" +
                "                    </div>\n" +
                "                </form>\n" +
                "            </div> " +
                "            <div class=\"modal-footer\">\n" +
                "                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n" +
                "                <button onclick=\"updateResourceFormSubmit()\" type=\"button\" class=\"btn btn-primary\">编辑资源</button>\n" +
                "            </div>";
    }

    /**
     * description 提交更新角色
     *
     * @param resource
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/updateResource.html",method = RequestMethod.POST)
    public String updateResource(Resource resource){
        System.out.println(resource+":::::::::::::::::::::::::::");
        resourceService.update(resource);
        return "redirect:/admin/resourceManager.html";
    }

    /**
     * description 删除角色及其关联信息
     *
     * @param id
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/delResource.html",method = RequestMethod.GET)
    public String delResource(Integer id){
        resourceService.delResourceRes(id);
        resourceService.delete(id);
        return "redirect:/admin/resourceManager.html";
    }
}

