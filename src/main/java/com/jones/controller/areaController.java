package com.jones.controller;

import com.jones.model.Area;
import com.jones.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * areaController
 *
 * @author JoNeS
 * @date
 */
@Controller
public class areaController {

    @Autowired
    private AreaService areaService;

    /**
     * description  添加区域
     *
     * @param area
     * @return java.lang.String
     */
    @RequestMapping("/admin/addArea.html")
    public String addDept(Area area){
        areaService.addForNotMatch(new Object[]{"areaname"},new Object[]{area.getAreaname()});

        return "redirect:/admin/areaManager.html";
    }

    /**
     * description 弹出编辑区域框并加载对应信息
     *
     * @param id
     * @param request
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/admin/updateArea.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String updateArea(Integer id, HttpServletRequest request){
        Area area = areaService.selectOne(id);
        String path = request.getContextPath();
        return  "<div class=\"modal-header\">\n" +
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span>&times;</span></button>\n" +
                "                <h4 class=\"modal-title\" id=\"myModal2\">编辑区域</h4>\n" +
                "            </div>\n" +
                "            <div class=\"modal-body\">\n" +
                "                <form id=\"updateAreaForm\" action=\""+path+"/admin/updateArea.html\" method=\"post\">\n" +
                "                    <input type='hidden' name='id' value='"+area.getId()+"' />"+
                "                    <div class=\"form-group\" >\n" +
                "                        <label>区域名称:</label>\n" +
                "                        <input id=\"inp2\" type=\"text\" name=\"areaname\" class=\"form-control\" value=\""+area.getAreaname()+"\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\" >\n" +
                "                        <label>区域排序字段(升序):</label>\n" +
                "                        <input id=\"inp2\" type=\"text\" name=\"areasort\" class=\"form-control\" value=\""+area.getAreasort()+"\" />\n" +
                "                    </div>\n" +
                "                </form>\n" +
                "            </div> " +
                "            <div class=\"modal-footer\">\n" +
                "                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n" +
                "                <button onclick=\"updateAreaFormSubmit()\" type=\"button\" class=\"btn btn-primary\">编辑区域</button>\n" +
                "            </div>";
    }

    /**
     * description 提交更新区域
     *
     * @param area
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/updateArea.html",method = RequestMethod.POST)
    public String updateArea(Area area){
        areaService.update(area);
        return "redirect:/admin/areaManager.html";
    }

    /**
     * description 删除区域及其关联信息
     *
     * @param id
     * @return java.lang.String
     */
    @RequestMapping(value = "/admin/delArea.html",method = RequestMethod.GET)
    public String delArea(Integer id){
        areaService.delAreaUserRes(id);
        areaService.delete(id);
        return "redirect:/admin/areaManager.html";
    }
}
