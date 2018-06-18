package org.nwnu.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.nwnu.system.entity.FoodsType;
import org.nwnu.system.entity.ShopUser;
import org.nwnu.system.service.FoodsTypeService;

import org.nwnu.system.service.SysUserService;
import org.nwnu.base.controller.BaseController;//基础包
import org.nwnu.pub.util.Const;
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
 * 菜品类目 前端控制器
 * </p>
 *
 * @author Answer
 * @since 2017-09-19
 */
@Controller
@RequestMapping("/FoodsType")
public class FoodsTypeController extends BaseController {
	@Autowired
	private FoodsTypeService this_FoodsTypeService;	
	@Autowired
	private SysUserService this_SysUserService;
	
	/***
	 * 每个controller的首页
	 * 	 
	 */
	@RequestMapping(value = "/FoodsTypeIndex")
	public ModelAndView FoodsTypeIndex(ModelAndView modelAndView) {
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
	public Map<String, Object> GetList(HttpSession session,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int pagesizes) {
		EntityWrapper<FoodsType> wrapper=new EntityWrapper<FoodsType>();	
		wrapper.eq("resid",((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER)).getResid());
		List<FoodsType> FoodsTypeList=this_FoodsTypeService.selectPage(
				new Page<FoodsType>(page,pagesizes),
				wrapper.orderBy("sequence", true)
				//.orderBy("id", false)//根据id倒序输出
				).getRecords();	
		for (FoodsType ft : FoodsTypeList) {
			if(((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER))!=null){
				ft.setTpName(((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER)).getName());
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();		
		int total=this_FoodsTypeService.selectList(wrapper).size();		
		result.put("total", total);
		result.put("data", FoodsTypeList);		
		return result;
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/FoodsTypeView", method = RequestMethod.GET)
	public ModelAndView view(ModelAndView modelAndView, FoodsType this_FoodsType, @RequestParam(value = "id", required = false) Integer id) {	
		if (id != null) {
            modelAndView.addObject("FoodsType", this_FoodsTypeService.selectById(id));
        }else
        {        	
        	modelAndView.addObject("FoodsType",this_FoodsType);
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
	public Object Save(FoodsType this_FoodsType,HttpSession session) {
	  		 	  		 	 	 
			  		 	 	 if(StringUtil.isEmpty(this_FoodsType.getName())){
		 	return renderError("类目名称不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_FoodsType.getSequence())){
		 	return renderError("显示顺序不能为空");		}
			this_FoodsType.setResid(((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER)).getResid());
			this_FoodsType.setUid(((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER)).getUid());			
			this_FoodsType.setUptime(new Date()); 	 	
	   if (this_FoodsType.getId() == null) {
            return this_FoodsTypeService.insert(this_FoodsType) ? renderSuccess("添加成功") : renderError("添加失败");
        } else {
            return this_FoodsTypeService.updateById(this_FoodsType) ? renderSuccess("修改成功") : renderError("修改失败");
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
		return this_FoodsTypeService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
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
			return this_FoodsTypeService.deleteBatchIds(idList) ? renderSuccess("删除成功") : renderError("删除失败");
		}else{
			return renderError("请选择需要删除的数据");
		}
		
	}
	
	
	
}
