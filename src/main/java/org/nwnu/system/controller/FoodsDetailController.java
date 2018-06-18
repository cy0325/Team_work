package org.nwnu.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.nwnu.system.entity.FoodsDetail;
import org.nwnu.system.entity.FoodsType;
import org.nwnu.system.entity.ShopUser;
import org.nwnu.system.service.FoodsDetailService;
import org.nwnu.system.service.FoodsTypeService;
import org.nwnu.system.service.ShopUserService;
import org.nwnu.system.service.SysDictService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜品信息 前端控制器
 * </p>
 *
 * @author Answer
 * @since 2017-09-19
 */
@Controller
@RequestMapping("/FoodsDetail")
public class FoodsDetailController extends BaseController {
	@Autowired
	private FoodsDetailService this_FoodsDetailService;	
	@Autowired
	private FoodsTypeService this_FoodsTypeService;	
	@Autowired
	private SysUserService this_SysUserService;
	@Autowired
	private SysDictService this_SysDictService;
	@Autowired
    private HttpServletRequest request;
	@Autowired
    private ShopUserService shopUserService;
	
	
	/***
	 * 每个controller的首页
	 * 	 
	 */
	@RequestMapping(value = "/FoodsDetailIndex")
	public ModelAndView FoodsDetailIndex(ModelAndView modelAndView,HttpSession session) {
		List<FoodsType> foodsTypes =null;
		foodsTypes = this_FoodsTypeService.selectList(new EntityWrapper<FoodsType>().eq("resid", ((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER)).getResid()));
	modelAndView.addObject("foodsTypes",foodsTypes);
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
			@RequestParam(required = false, defaultValue = "10") int pagesizes,
			@RequestParam(required = false, defaultValue = "") String fid,
			@RequestParam(required = false, defaultValue = "") String name) {
		EntityWrapper<FoodsDetail> wrapper=new EntityWrapper<FoodsDetail>();	
		//如果有查询条件，此处需要构造查询warpper
		//例如wrapper.eq();
		
		wrapper.eq("resid",((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER)).getResid());
		if(!StringUtil.isEmpty(name)){
			wrapper.like("foodsname", name);
		}
		if(!StringUtil.isEmpty(fid)){
			wrapper.eq("fid", fid);
		}
		List<FoodsDetail> FoodsDetailList=this_FoodsDetailService.selectPage(
				new Page<FoodsDetail>(page,pagesizes),
				wrapper.orderBy("id", false)//根据id倒序输出
				).getRecords();	
		for (FoodsDetail fd : FoodsDetailList) {
			if(fd.getFid()!=null){
				 //fd.setLbName(this_FoodsTypeService.selectById(fd.getFid()).getName());
				 ShopUser ru=shopUserService.selectById(fd.getUid());
				 if(ru!=null){
					// fd.set(ru.getName());
				 }				
			}
			if(fd.getSellstatus().equals("a")){
				fd.setSellstatus("是");
			}else if(fd.getHotstatus().equals("b")){
				fd.setHotstatus("否");
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();		
		int total=this_FoodsDetailService.selectList(wrapper).size();		
		result.put("total", total);
		result.put("data", FoodsDetailList);		
		return result;
	}
	
	
	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/FoodsDetailView", method = RequestMethod.GET)
	public ModelAndView view(ModelAndView modelAndView, FoodsDetail this_FoodsDetail,HttpSession session,
			@RequestParam(value = "id", required = false) Integer id) {	
		
		if (id != null) {
			FoodsDetail fd = this_FoodsDetailService.selectById(id);
            modelAndView.addObject("FoodsDetail", fd);
        }else
        {        
        	
        	modelAndView.addObject("FoodsDetail",this_FoodsDetail);
        	
        }
		//所属大类
			List<FoodsType> foodsTypes =null;
			foodsTypes = this_FoodsTypeService.selectList(new EntityWrapper<FoodsType>().eq("resid", ((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER)).getResid()));
		modelAndView.addObject("foodsTypes",foodsTypes);
		
	     return modelAndView;
	}
	/**
	 * 获取所属类别
	 * @param fid
	 * @return
	 */
	@RequestMapping(value = "/getSFoodTypes")
	@ResponseBody
	public List<FoodsType> getSFoodTypes(){
		List<FoodsType> toplsp =new ArrayList<FoodsType>();
		List<FoodsType> flst =this_FoodsTypeService.selectList(new EntityWrapper<FoodsType>());
		if (flst.size() > 0) {
			for (FoodsType dao : flst) {
				toplsp.add(dao);
			}
			
		}
		return toplsp;
	}

	
	
	
	/***
	 * 保存，如果是新增，调用insert，如果是修改，调用updateById
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/Save", method = RequestMethod.POST)
	@ResponseBody
	public Object Save(FoodsDetail this_FoodsDetail,HttpSession session,
			@RequestParam(value = "filepic1", required = false) MultipartFile filepic1) {
			  		 	 	 if(StringUtil.isEmpty(this_FoodsDetail.getFoodsname())){
		 	return renderError("菜品名称不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_FoodsDetail.getPrice())){
		 	return renderError("价格不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_FoodsDetail.getDesc())){
		 	return renderError("菜品描述不能为空");		}
			  		 	 
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			List<String> urlList = new ArrayList<String>();
			fileList.add(filepic1);
			for(MultipartFile file : fileList){
				//文件上传
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		        String date = df.format(new Date());
		        String year = date.substring(0, 4);
		        String month = date.substring(5, 7);
		        //文件路径 upload/article/当前年月/
		        String path = request.getSession().getServletContext().getRealPath("upload") + "/foodsDetail/" + year + month + "/";
		        //取扩展名
		        if(file != null){
		        	String extension = getExtensionName(file.getOriginalFilename());
			        //拼接新的文件名
			        String fileName = getCurrTime()+buildRandom(5) + "." + extension;
			     //
			        if (file.getSize() >= 50*1024)
			        {
			           // return new BaseResult(false, "文件不能大于50M");
			            return renderError("上传图片不能大于50k");
			        }
			    
			        if (upload(path, fileName, file)) {
			        	urlList.add("/upload" + "/foodsDetail/" + year + month + "/"+fileName);
			        	continue;
			        } else{
			        	return renderError("保存失败！");
			        }
		        } else{
		        	urlList.add("");
		        }
			}	
			//添加url
			this_FoodsDetail.setPic(urlList.get(0));
			if(filepic1 == null){
				this_FoodsDetail.setPic(this_FoodsDetailService.selectById(this_FoodsDetail.getId()).getPic());
			}
			//this_FoodsDetail.setResid(((ResUser)session.getAttribute(Const.lOGIN_RESUSER)).getResid());
			this_FoodsDetail.setUid(((ShopUser)session.getAttribute(Const.lOGIN_SHOPUSER)).getId());
			//System.out.println(((ResUser)session.getAttribute(Const.lOGIN_RESUSER)).getName());
			this_FoodsDetail.setUpdateDate(new Date());
	   if (this_FoodsDetail.getId() == null) {
            return this_FoodsDetailService.insert(this_FoodsDetail) ? renderSuccess("添加成功") : renderError("添加失败");
        } else {
            return this_FoodsDetailService.updateById(this_FoodsDetail) ? renderSuccess("修改成功") : renderError("修改失败");
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
		return this_FoodsDetailService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
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
			return this_FoodsDetailService.deleteBatchIds(idList) ? renderSuccess("删除成功") : renderError("删除失败");
		}else{
			return renderError("请选择需要删除的数据");
		}
		
	}
	
	
	
}
