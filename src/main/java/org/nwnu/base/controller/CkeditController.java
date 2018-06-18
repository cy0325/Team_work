package org.nwnu.base.controller;

import org.nwnu.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/Ckedit")
public class CkeditController extends BaseController{


	@RequestMapping(value = "/CkeditExample")
	public ModelAndView CkeditExample(ModelAndView modelAndView) {
		return modelAndView;
	}
	
	@RequestMapping(value = "/Ckeditor")
	public ModelAndView Ckeditor(ModelAndView modelAndView) {
	 return modelAndView;
	}

	
}
