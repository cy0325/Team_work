package org.nwnu.pub.inter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Ehcache;

import org.nwnu.pub.util.Const;
import org.nwnu.pub.util.CookiesUtil;
import org.nwnu.system.entity.ShopUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor {

	public AdminInterceptor() {
		// TODO Auto-generated constructor stub
	}

	//全部完成后处理
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}
   //拦截后处理
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
	}
	//拦截前处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// TODO Auto-generated method stub
		//获取请求的URL  
		String url = request.getRequestURI();
		Cookie cookie = CookiesUtil.getCookieByName(request, Const.COOKIES_SHOP_OPENID);
		Cookie userCookie = CookiesUtil.getCookieByName(request, Const.COOKIES_SHOP_USER_ID);

		// 开放后台登录
		if (url.indexOf("SysLogin") >= 0||url.indexOf("ShopUser")>=0||url.indexOf("StuLogin")>=0){
			return true;
		}
		// TODO debug后删除
		if (url.contains("/App")) {
			return true;
		}
		// TODO debug注释 允许微信首页登录
		if (url.contains("/App/index")) {
			return true;
		}
		
		// TODO debug注释
		if((url.contains("/person") || url.contains("/list") || url.contains("/sort")
				|| url.contains("/activity") || url.contains("/getGoods") || url.contains("/search-list")
				|| url.contains("/sendCode") || url.contains("/register")|| url.contains("/login_sendCode")
				|| url.contains("/login") || url.contains("/detail")) && cookie != null){
			return true;
		}
		
		
		if (userCookie != null) {
			return true;
		}
		// 后台session
		if(request.getSession().getAttribute(Const.lOGIN_SYSUSER) != null){
			return true;
		} 
		if(request.getSession().getAttribute(Const.lOGIN_SHOPUSER) != null){
			return true;
		}
		
		if(request.getSession().getAttribute(Const.lOGIN_StuUSER)!=null){
			return true;
		}
		else{
			response.sendRedirect("/Login/SysLogin.do");
		}
		
		request.getSession().invalidate();
		return false;

	}
}
