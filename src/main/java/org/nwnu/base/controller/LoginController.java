package org.nwnu.base.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.nwnu.pub.util.PasswordUtil;
import org.nwnu.pub.util.RandomValidateCodeUtil;
import org.nwnu.system.entity.ShopUser;
import org.nwnu.system.entity.StudentUser;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.ShopUserService;
import org.nwnu.system.service.StudentUserService;
import org.nwnu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/Login")
public class LoginController extends BaseController {
    @Autowired
    private SysUserService sysuserService;
    
    @Autowired
    private ShopUserService shopUserService;
    
    @Autowired
    private StudentUserService studentUserService;
    
    @Autowired
    private HttpServletRequest request;


    //系统用户登陆界面
    @RequestMapping(value = "SysLogin")
    public String SysLogin() {
        return "Login/SysLogin";
    }  
    //商铺管理员登陆界面
    @RequestMapping(value = "ShopLogin")
    public String ShopLogin() {
        return "Login/shopLogin";
    }
  //学生登陆界面
    @RequestMapping(value = "StuLogin")
    public String StuLogin() {
        return "Login/StuLogin";
    }
    /***
     *
     * 系统用户登陆
     */
    @RequestMapping(value = "/SysLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> SysLogin(SysUser sysuser, HttpSession session) {
    	delSession(session);
    	HttpSession httpsession = request.getSession();
        Map<String, Object> result = new HashMap<String, Object>();
        if (sysuser.getTel() == null || sysuser.getTel().equals("")) {
            result.put("IsSuccess", false);
            result.put("info", "用户名不能为空");
            return result;
        }
        if (sysuser.getPwd() == null || sysuser.getPwd().equals("")) {
            result.put("IsSuccess", false);
            result.put("info", "密码不能为空");
            return result;
        }
        String validateCode = request.getParameter("validecode");
        String code = null;
        //1:获取cookie里面的验证码信息
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("imagecode".equals(cookie.getName())) {
                code = cookie.getValue();
                break;
            }
        }
        if (StringUtils.isEmpty(validateCode) || !validateCode.equals(code)){
        	 result.put("IsSuccess", false);
             result.put("info", "验证码错误");
             return result;
        }
        SysUser sysLoginUsers = sysuserService.selectOne(new EntityWrapper<SysUser>().eq("tel", sysuser.getTel()));
        if (sysLoginUsers != null) {
            byte[] salt = PasswordUtil.getStaticSalt();
            String cipherText = PasswordUtil.encrypt(sysLoginUsers.getRolecode(), sysuser.getPwd(), salt);
            if (sysLoginUsers.getPwd().equals(cipherText)) {
                httpsession.setAttribute("sysLoginUser", sysLoginUsers);
                result.put("IsSuccess", true);
                result.put("info", "登录成功");
            } else {
                result.put("IsSuccess", false);
                result.put("info", "用户名或密码错误");
            }
        } else {
            result.put("IsSuccess", false);
            result.put("info", "用户名或密码错误");
        }
        return result;
    }

    /***
    *
    * 商铺用户用户登陆
    */
   @RequestMapping(value = "/ShopLogin", method = RequestMethod.POST)
   @ResponseBody
   public Map<String, Object> ShopLogin(ShopUser shopUser, HttpSession session) {
	   System.out.println("9999999999999999999999进入商铺登录");
   		delSession(session);
   		HttpSession httpsession = request.getSession();
       Map<String, Object> result = new HashMap<String, Object>();
       if (shopUser.getPhone() == null || shopUser.getPhone().equals("")) {
           result.put("IsSuccess", false);
           result.put("info", "用户名不能为空");
           return result;
       }
       if (shopUser.getPwd() == null || shopUser.getPwd().equals("")) {
           result.put("IsSuccess", false);
           result.put("info", "密码不能为空");
           return result;
       }
       String validateCode = request.getParameter("validecode");
       String code = null;
       //1:获取cookie里面的验证码信息
       Cookie[] cookies = request.getCookies();
       for (Cookie cookie : cookies) {
           if ("imagecode".equals(cookie.getName())) {
               code = cookie.getValue();
               break;
           }
       }
       if (StringUtils.isEmpty(validateCode) || !validateCode.equals(code)){
       	 result.put("IsSuccess", false);
            result.put("info", "验证码错误");
            return result;
       }
      ShopUser ru=shopUserService.selectOne(new EntityWrapper<ShopUser>().eq("phone",shopUser.getPhone()));
       if (ru != null) {//.eq("status", "a")
     	  if(ru.getStatus().equals("a")){
               byte[] salt = PasswordUtil.getStaticSalt();
               String cipherText = PasswordUtil.encrypt(ru.getRolecode(), shopUser.getPwd(), salt);
               if (ru.getPwd().equals(cipherText)) {
                   httpsession.setAttribute("shopLoginUser", ru);
                   result.put("IsSuccess", true);
                   result.put("info", "登录成功");
               } else {
                   result.put("IsSuccess", false);
                   result.put("info", "用户名或密码错误");
               } 
     	  }
     	  else{ 
               result.put("IsSuccess", false);
               result.put("info", "该账号已停用");
     	  }
       } else {
           result.put("IsSuccess", false);
           result.put("info", "用户名或密码错误");
       }
       return result;
   }  
    
   /***
   *
   * 学生用户登陆
   */
  @RequestMapping(value = "/StuLogin", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> StuLogin(StudentUser studentUser, HttpSession session) {
  		delSession(session);
  		HttpSession httpsession = request.getSession();
      Map<String, Object> result = new HashMap<String, Object>();
      if (studentUser.getPhone() == null || studentUser.getPhone().equals("")) {
          result.put("IsSuccess", false);
          result.put("info", "用户名不能为空");
          return result;
      }
      if (studentUser.getPwd() == null || studentUser.getPwd().equals("")) {
          result.put("IsSuccess", false);
          result.put("info", "密码不能为空");
          return result;
      }
      String validateCode = request.getParameter("validecode");
      String code = null;
      //1:获取cookie里面的验证码信息
      Cookie[] cookies = request.getCookies();
      for (Cookie cookie : cookies) {
          if ("imagecode".equals(cookie.getName())) {
              code = cookie.getValue();
              break;
          }
      }
      if (StringUtils.isEmpty(validateCode) || !validateCode.equals(code)){
      	 result.put("IsSuccess", false);
           result.put("info", "验证码错误");
           return result;
      }
     StudentUser ru=studentUserService.selectOne(new EntityWrapper<StudentUser>().eq("phone",studentUser.getPhone()));
      if (ru != null) {//.eq("status", "a")
    	  if(ru.getStatus().equals("a")){
              byte[] salt = PasswordUtil.getStaticSalt();
              String cipherText = PasswordUtil.encrypt(ru.getRolecode(), studentUser.getPwd(), salt);
              if (ru.getPwd().equals(cipherText)) {
                  httpsession.setAttribute("stuLoginUser", ru);
                  result.put("IsSuccess", true);
                  result.put("info", "登录成功");
              } else {
                  result.put("IsSuccess", false);
                  result.put("info", "用户名或密码错误");
              } 
    	  }
    	  else{
              result.put("IsSuccess", false);
              result.put("info", "该账号已停用");
    	  }
      } else {
          result.put("IsSuccess", false);
          result.put("info", "用户名或密码错误");
      }
      return result;
  }  
   
    
    
    //登录获取验证码
    @RequestMapping("/getCode")
    @ResponseBody
    public String getSysManageLoginCode(HttpServletResponse response,
                                        HttpServletRequest request) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        try {
            randomValidateCode.getRandcode(request, response, "imagecode");// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //验证码验证
    @RequestMapping(value = "/checkimagecode")
    @ResponseBody
    public boolean checkTcode(HttpServletRequest request, HttpServletResponse response) {
        String validateCode = request.getParameter("validecode");
        String code = null;
        //1:获取cookie里面的验证码信息
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("imagecode".equals(cookie.getName())) {
                code = cookie.getValue();
                break;
            }
        }
        System.out.println("*******************code***********"+code+"***************"+validateCode);
        if (!StringUtils.isEmpty(validateCode) && validateCode.equals(code))
            return true;
        return false;
    } 
    
    //在登陆前清除session
    private void delSession(HttpSession session) {
    	if (session.getAttribute("sysLoginUser") != null) {
            session.invalidate();
        } 
    	else if(session.getAttribute("shopLoginUser")!=null){
    		session.invalidate();
    	}
    	else if(session.getAttribute("stuLoginUser")!=null){
    		session.invalidate();
    	}
	}
    
  
}
