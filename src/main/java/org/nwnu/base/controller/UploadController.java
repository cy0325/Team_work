package org.nwnu.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Upload")
public class UploadController extends BaseController{

	@RequestMapping(value = "/UploadExample")
	public ModelAndView UploadExample(ModelAndView modelAndView) {
	 return modelAndView;
	}


}
