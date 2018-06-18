package org.nwnu.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.nwnu.system.entity.ShopList;
import org.nwnu.system.entity.SysDict;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.ShopListService;
import org.nwnu.system.service.SysDictService;
import org.nwnu.system.service.SysUserService;
import org.nwnu.base.controller.BaseController;//基础包
import org.nwnu.pub.util.StringUtil;//自定义字符串处理类，如果没有就取掉
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 餐厅 前端控制器
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */
@Controller
@RequestMapping("/ShopList")
public class ShopListController extends BaseController {
	@Autowired
	private ShopListService this_ShopListService;	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDictService sysDictService;
	/***
	 * 每个controller的首页
	 * 	 
	 */
	@RequestMapping(value = "/ShopListIndex")
	public ModelAndView ShopListIndex(ModelAndView modelAndView) {
		return modelAndView;
	}

	/***
	 * 列表
	 * 
	 * @param 
	 * @param page
	 *            起始页
	 * @param rows
	 *            页面大小 * @param sort 排序字段 * @param rows 排序顺序
	 * @return
	 */
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> GetList(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int pagesizes) {
		EntityWrapper<ShopList> wrapper=new EntityWrapper<ShopList>();	
		//如果有查询条件，此处需要构造查询warpper
		//例如wrapper.eq();	
		
		List<ShopList> ShopListList=this_ShopListService.selectPage(
				new Page<ShopList>(page,pagesizes),
				wrapper.orderBy("id", false)//根据id倒序输出
				).getRecords();	
		
		for(ShopList sp : ShopListList){
			SysUser su = sysUserService.selectById(sp.getUid());
			if(su!=null){
		        sp.setSysUserName(su.getName());
			}else{
				sp.setSysUserName("该用户被改变");
			}
			SysDict sysDict = sysDictService.selectById(sp.getFname());
			if(sysDict!=null){
				sp.setFoodsnameStr(sysDict.getDictvalue());
			}else{
				sp.setFoodsnameStr("系统字典中没有此类型");
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();		
		int total=this_ShopListService.selectList(wrapper).size();		
		result.put("total", total);
		result.put("data", ShopListList);		
		return result;
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/ShopListView", method = RequestMethod.GET)
	public ModelAndView view(ModelAndView modelAndView, ShopList this_ShopList, @RequestParam(value = "id", required = false) Integer id) {	
		if (id != null) {
            modelAndView.addObject("ShopList", this_ShopListService.selectById(id));
        }else
        {        	
        	modelAndView.addObject("ShopList",this_ShopList);
        }
	     return modelAndView;
	}

	/***
	 * 保存，如果是新增，调用insert，如果是修改，调用updateById
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/Save", method = RequestMethod.POST)
	@ResponseBody
	public Object Save(ShopList this_ShopList,HttpSession session) {
	  		 	  		 	 	 if(StringUtil.isEmpty(this_ShopList.getName())){
		 	return renderError("商铺名称不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_ShopList.getAddress())){
		 	return renderError("地址不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_ShopList.getLinkman())){
		 	return renderError("联系人不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_ShopList.getPhone())){
		 	return renderError("联系电话不能为空");		}
			  		 	 	
			  		 	 	 if(StringUtil.isEmpty(this_ShopList.getFname())){
		 	return renderError("菜品类型不能为空");		}
			  		 	 	
			  		 	 	 if(StringUtil.isEmpty(this_ShopList.getIntro())){
		 	return renderError("商铺介绍不能为空");		}
			  	  // this_ShopList.setOperator(((SysUser)session.getAttribute("loginedUser")).getName());
	   this_ShopList.setUpdateDate(new Date());
	   if (this_ShopList.getId() == null) {
            return this_ShopListService.insert(this_ShopList) ? renderSuccess("添加成功") : renderError("添加失败");
        } else {
            return this_ShopListService.updateById(this_ShopList) ? renderSuccess("修改成功") : renderError("修改失败");
        }		
	}

	/***
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/Delete")
	@ResponseBody
	public Object Delete(@RequestParam(value = "id", required = true) Integer id) {
		return this_ShopListService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
	}

	/***
	 * 根据ids批量删除，此方法根据前端需要
	 * 
	 * @param ids 逗号拼接项
	 * @return
	 */
	@RequestMapping("/BatchDelete")
	@ResponseBody
	public Object BatchDelete(@RequestParam(value = "ids", required = true) String ids) {
		List<Integer> idList=new ArrayList<Integer>();
		if(StringUtil.isNotEmpty(ids))
		{
			if (ids.contains("all,")) {
				ids=ids.replace("all,", "");//checkbox全选的时候带入，要去掉
			}			
			String[] lsStrings=ids.split(",");			
			for (String id : lsStrings) {
				idList.add(Integer.parseInt(id));
			}
			return this_ShopListService.deleteBatchIds(idList) ? renderSuccess("删除成功") : renderError("删除失败");
		}else{
			return renderError("请选择需要删除的数据");
		}
		
	}
	
	
	
}
