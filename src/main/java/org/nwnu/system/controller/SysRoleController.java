package org.nwnu.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.nwnu.base.controller.BaseController;
import org.nwnu.pub.util.StringUtil;
import org.nwnu.system.entity.SysPrivilege;
import org.nwnu.system.entity.SysRole;
import org.nwnu.system.entity.SysRolePrivilege;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.SysPrivilegeService;
import org.nwnu.system.service.SysRolePrivilegeService;
import org.nwnu.system.service.SysRoleService;
import org.nwnu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/SysRole")
public class SysRoleController extends BaseController{

	@Autowired
	private SysRoleService sysroleService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysPrivilegeService sysprivilegeService;
	@Autowired
	private SysRolePrivilegeService sysroleprivilegeService;
	
	@RequestMapping(value = "/SysRoleIndex")
	public ModelAndView SysRoleIndex(ModelAndView modelAndView) {
		return modelAndView;
	}

	/***
	 * 列表
	 * 
	 * @param sysrole
	 * @param page
	 *            起始页
	 * @param rows
	 *            页面大小 * @param sort 排序字段 * @param rows 排序顺序
	 * @return
	 */
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> GetList(SysRole sysrole,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int pagesizes,
			@RequestParam(required = false, defaultValue = "id") String sort,
			@RequestParam(required = false, defaultValue = "true") String order) {
		Boolean isAsc=true;
		if(order=="desc") isAsc=false;	
		EntityWrapper<SysRole> wrapper=new EntityWrapper<SysRole>();
		List<SysRole> sysroleList=sysroleService.selectPage(
				new Page<SysRole>(page,pagesizes),
				wrapper.orderBy(sort, isAsc)
				).getRecords();
		for(SysRole sr : sysroleList){
			sr.setSysUser(sysUserService.selectById(sr.getUid()));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		int total=sysroleService.selectList(wrapper).size();	
		result.put("total", total);
		result.put("data", sysroleList);	
		return result;
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param sysrole
	 * @return
	 */
	@RequestMapping(value = "/SysRoleView", method = RequestMethod.GET)
	public ModelAndView SysRoleView(ModelAndView modelAndView,SysRole sysrole, @RequestParam(value = "id", required = false) Integer id) {	
		if (id != null) {
            modelAndView.addObject("sysrole", sysroleService.selectById(id));
        }else
        {
        	sysrole.setRolecode(sysroleService.getCode());
        	modelAndView.addObject("sysrole",sysrole);
        }
	     return modelAndView;
	}

	/***
	 * 保存，如果是现在，调用insert，如果是修改，调用updateByPrimaryKey
	 * 
	 * @param sysrole
	 * @return
	 */
	@RequestMapping(value = "/Save", method = RequestMethod.POST)
	@ResponseBody
	public Object Save(SysRole sysrole,HttpSession session) {	
		if(StringUtil.isEmpty(sysrole.getRolename()))
		{			
			return renderError("角色名称不能为空");			
		}
		if(StringUtil.isEmpty(sysrole.getRolecode()))
		{
			return renderError("角色编码不能为空");			
		}				
		sysrole.setUid(((SysUser)session.getAttribute("sysLoginUser")).getId());
	    sysrole.setUpdate_date(new Date());
	    if (sysrole.getId() == null) {
             return sysroleService.insert(sysrole) ? renderSuccess("添加成功") : renderError("添加失败");
         } else {
             return sysroleService.updateById(sysrole) ? renderSuccess("修改成功") : renderError("修改失败");
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
		return sysroleService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
	}
	
	/***
	 * 根据ids批量删除
	 * 
	 * @param id
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
			return sysroleService.deleteBatchIds(idList) ? renderSuccess("删除成功") : renderError("删除失败");
		}else{
			return renderError("请选择需要删除的数据");
		}
		
	}
	
	
	/***
	 * 根据id停用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/StatusClose")
	@ResponseBody
	public Object StatusClose(@RequestParam(value = "id", required = true) Integer id) {
		SysRole sysRole=sysroleService.selectById(id);
		if (sysRole!=null) {
			sysRole.setStatus("b");
			return sysroleService.updateById(sysRole) ? renderSuccess("成功") : renderError("失败");
		}else
		{
			return renderError("该信息不存在");
		
		}
	}
	/***
	 * 根据id启用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/StatusOpen")
	@ResponseBody
	public Object StatusOpen(@RequestParam(value = "id", required = true) Integer id) {
		SysRole sysRole=sysroleService.selectById(id);
		if (sysRole!=null) {
			sysRole.setStatus("a");
			return sysroleService.updateById(sysRole) ? renderSuccess("成功") : renderError("失败");
		}else
		{
			return renderError("该信息不存在");
		
		}
	}
	
	/**
	 * 根据角色拥有的权限加载权限树
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/RoleGrant",method=RequestMethod.GET)
	 public ModelAndView RoleGrant(Integer id,ModelAndView modelAndView)
     {
		 SysRole sysrole=sysroleService.selectById(id); 
         StringBuilder sb = new StringBuilder();//树数据
         //查找全部权限内容
         List<SysPrivilege> allprivielgelst=sysprivilegeService.selectList(new EntityWrapper<SysPrivilege>());        
         ///查找当前角色的菜单       
         List<SysRolePrivilege> Rplst = sysroleprivilegeService.selectList(new EntityWrapper<SysRolePrivilege>().eq("roleCode",sysrole.getRolecode()));
        
         if (Rplst.size()==0)
         {        	
             ///根据全部权限内容加载菜单树 
             int i = 0;
             for (SysPrivilege pdao : allprivielgelst)
             {
                 if (i == allprivielgelst.size())
                 {                    
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name:\"" + pdao.getPrivilegename() + "\", open: true }");
                    }
                 else
                 {         
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name: \"" + pdao.getPrivilegename() + "\", open: true },");
                    }
                 i++;
             }
         }
         else
         {
        	 String privilegelist="";
        	 int j=0;
        	 for(SysRolePrivilege rprivilege :Rplst)
        	 {
        		 if(j==Rplst.size())
        		 {
        			 privilegelist+=rprivilege.getPrivilegecode();
        		 }else
        		 {
        			 privilegelist+=rprivilege.getPrivilegecode()+",";
        		 }
        	 }        	
        	 modelAndView.addObject("privilegelist", privilegelist);            
             int i = 0;
             for (SysPrivilege pdao : allprivielgelst)
             {
                 if (i == allprivielgelst.size())
                 {
                     if (privilegelist.contains(pdao.getPrivilegecode()))
                     {
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name:\"" + pdao.getPrivilegename() + "\",checked: true, open: true }");
                     }
                     else
                     {
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name:\"" + pdao.getPrivilegename() + "\", open: true }");
                     }
                 }
                 else
                 {                	
                     if (privilegelist.contains(pdao.getPrivilegecode()))
                     {
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name: \"" + pdao.getPrivilegename() + "\",checked:true, open: true },");
                     }
                     else
                     {
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name: \"" + pdao.getPrivilegename() + "\", open: true },");
                     }
                 }
                 i++;
             }
         }
         modelAndView.addObject("MenuStr", sb.toString());       
         modelAndView.addObject("roleCode", sysrole.getRolecode());       
         return modelAndView;
     }
	
	/**
	 * 权限保存
	 *   privilegelist权限列表类似0101,0102,0202
	 *   rolecode角色编码
	 * @return
	 */
	@RequestMapping(value="/GrantSave",method=RequestMethod.POST)
	@ResponseBody
	 public Object GrantSave(String privilegelist, String roleCode,HttpSession session)
     {
		//SysUser sysuser=(SysUser)session.getAttribute("sysLoginUser");
		return sysroleprivilegeService.roletgrant(privilegelist, roleCode,"admin",new Date())? renderSuccess("授权成功") : renderError("授权失败");        
     }
	
	/****
	 * 批量导出
	 * @param
	 * @return
	 */
	  //导出excel
	   @RequestMapping(value = "ExportExcel",method=RequestMethod.GET)
	   @ResponseBody
	   public Object ExportExcel(@RequestParam(value = "ids", required = true) String ids, HttpServletResponse response) throws IOException, ParseException {
		   String fileName = "角色统计";
	       List<SysRole> rolelist = sysroleService.selectList(new EntityWrapper<SysRole>().in("id", ids));
	       
	       List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	       Map<String, Object> map = new HashMap<String, Object>();
	       map.put("sheetName", "角色信息");
	       listMap.add(map);
	       for (SysRole sysrole : rolelist) {	    
	           Map<String, Object> mapValue = new HashMap<String, Object>();
	           mapValue.put("roleCode", sysrole.getRolecode());
	           mapValue.put("roleName", sysrole.getRolename());	           
	           mapValue.put("squence", sysrole.getSequence());
	           String status = "";
	           if (sysrole.getStatus().equals("a")) {
	        	   status = "启用";
	           } else{
	        	   status = "停用";
	           }
	           mapValue.put("status", status);
	           listMap.add(mapValue);
	       }
	       String columnNames[] = {"角色编码", "角色名称", "显示顺序", "状态"};//列名
	       String keys[] = {"roleCode", "roleName", "squence", "status"};//map中的key
	       int line=1;  //标题列宽度重新设置，列数从0开始
	       if(ExcelDownload(response, fileName, columnNames,  keys, listMap, line)){
	           return renderSuccess("导出成功");
	       } else{
	           return renderError("导出失败");
	       }
	   }
}
