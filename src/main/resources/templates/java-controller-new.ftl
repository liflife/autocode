package ${packagename}.dao.${entry};

import java.util.List;
import ${packagename}.model.${entry?cap_first};
import ${packagename}.service.${entry?cap_first};
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
*
* @author  <#if author??>${author}</#if>
*
*/
@Controller
@RequestMapping(${entry?cap_first}Controller.PATH)
public class ${entry?cap_first}Controller {
    public final static String PATH = "/login";
    private static Logger logger = LogManager.getLogger(${entry?cap_first}Controller.class);
    @Resource
    private ${entry?cap_first}Service ${entry}Service;

    @RequestMapping(value = {"","/","/index"}, method = RequestMethod.GET)
    public String index() {
    return PATH + "/login";
    }

    @RequestMapping(value = {"/insert"})
    public void insert${entry?cap_first}(${entry?cap_first} ${entry}) {
        ${entry?cap_first} ${entry}=new  ${entry?cap_first}();
        ${entry}Service.insert${entry?cap_first}(${entry});
    }
    @RequestMapping(value = {"/update"})
    public void update${entry?cap_first}(${entry?cap_first} ${entry}) {
        ${entry?cap_first} ${entry}=new  ${entry?cap_first}();
        ${entry}Service.update${entry?cap_first}(${entry});
    }
    @ResponseBody
    @RequestMapping(value = {"/delete"})
    public void delete${entry?cap_first}(Integer ${entry}Id) {
        ${entry}Service.delete${entry?cap_first}(${entry}Id);
    }
    @ResponseBody
    @RequestMapping(value = {"/query${entry?cap_first}ById"})
    public ${entry?cap_first} query${entry?cap_first}ById(Integer ${entry}Id) {
        ${entry?cap_first} ${entry}= ${entry}Service.query${entry?cap_first}ById(${entry}Id);
        String s = JSONObject.toJSON(${entry}).toString();
        return s;
    }
    @ResponseBody
    @RequestMapping(value = {"/queryAll${entry?cap_first}"})
    public String queryAll${entry?cap_first}() {
    List<${entry?cap_first}> ${entry}List=${entry}Service.queryAll${entry?cap_first}();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("data", ${entry}List);
    String s = JSONObject.toJSON(jsonObject).toString();
    return s;
    }
}


package com.jjyc.web.controller.login;

import com.jjyc.domain.model.User;
import com.jjyc.service.login.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
* Created by Administrator on 2017/12/19.
*/
@Controller
@RequestMapping(LoginController.PATH)
public class LoginController {
public final static String PATH = "/login";
private static Logger logger = LogManager.getLogger(LoginController.class);
@Resource
private LoginService loginService;

@RequestMapping(value = {"","/","/index"}, method = RequestMethod.GET)
public String index() {
return PATH + "/login";
}

@RequestMapping(value = {"/login"})
public ModelAndView login(String userName,String password) {
ModelAndView mv=new ModelAndView();
User user=loginService.login(userName,password);
if(user!=null){
mv.setViewName("redirect:index/index");

}else{
mv.setViewName(PATH + "/login");
mv.addObject("errorMsg","用户密码错误！");
}
return mv;
}

}

