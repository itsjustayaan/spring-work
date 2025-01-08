package com.working.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.working.app.dao.VisitorDAO;
import com.working.app.model.Visitor;


@Controller
public class VisitorController {
	
	@Autowired
	VisitorDAO visitorDAO;

	@RequestMapping("/saveVisitor")
	public ModelAndView saveVisitor(Visitor visitor) {
		visitorDAO.createVisitor(visitor);
		System.out.println("visitor details :" +visitor);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("guest", visitor.getVisitorName());
		mv.addObject("msg", "Your details were inserted at: "+ new Date());
		return mv;
	}
	
	@RequestMapping("/updateVisitor")
	public ModelAndView updateVisitor(Visitor visitor) {
		visitorDAO.updateVisitor(visitor);
		System.out.println("visitor details :" +visitor);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("guest", visitor.getVisitorName());
		mv.addObject("msg", "Your details were updated at: "+ new Date());
		return mv;
	}
	
	@RequestMapping("/deleteVisitor")
	public ModelAndView deleteVisitor(Visitor visitor) {
		visitorDAO.deleteVisitor(visitor.getVisitorId());
		System.out.println("visitor details :" +visitor.getVisitorId());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("guest", visitor.getVisitorId());
		mv.addObject("msg", "Your details were deleted at: "+ new Date());
		return mv;
	}
	
	@RequestMapping("/checkVisitor")
	public ModelAndView checkVisitor(Visitor visitor) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("check", visitorDAO.isVisitorExists(visitor.getVisitorId()));
		mv.addObject("msg", "Your details were deleted at: "+ new Date());
		return mv;
	}
	
	@RequestMapping("/findAllVisitor")
	public ModelAndView findAllVisitor(Visitor visitor) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("visitors", visitorDAO.findVisitors());
		mv.addObject("msg", "Details Fond At: "+ new Date());
		return mv;
	}
	
	@RequestMapping("/findVisitor")
	public ModelAndView findVisitor(Visitor visitor) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("visitor", visitorDAO.findVisitors(visitor.getVisitorId()).getVisitorName());
		mv.addObject("msg", "Details Fond At: "+ new Date());
		return mv;
	}
}
