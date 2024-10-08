package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String main() {
		return "index";
	}
	
	@GetMapping("stdpage")
	public ModelAndView stdpage()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stdpage");
		return mv;
	}
	
	@GetMapping("stdreg")
	public ModelAndView stdreg()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stdreg");
		return mv;
	}
	
	@PostMapping("insertstd")
	public ModelAndView insert(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String msg = null;
		try
		{
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String contact = request.getParameter("contact");
			
			Student std = new Student();
			
			
			std.setName(name);
			std.setGender(gender);
			std.setDateofbirth(dob);
			std.setEmail(email);
			std.setPassword(pwd);
			std.setContact(contact);
		
			
			
		msg = studentService.addstudent(std);
		mv.setViewName("displaymsg");
		mv.addObject("message",msg);
		
		}
		catch (Exception s) {
			msg = s.getMessage();
			
			mv.setViewName("displaymsg");
			mv.addObject("message",msg);
		}
		return mv;
	}
	
	
	@GetMapping("stdlogin")
	public ModelAndView stdlogin()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stdlogin");
		return mv;
	}
	
	@GetMapping("stdhome")
	public ModelAndView cushome(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		int sid = (int)session.getAttribute("sid");
		String sname = (String) session.getAttribute("sname");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stdhome");
		
		mv.addObject("sid",sid);
		mv.addObject("sname",sname);
		
		return mv;
	}
	
	@PostMapping("checkstdlogin")
	   public ModelAndView checkstdlogin(HttpServletRequest request)
	   {
	     ModelAndView mv = new ModelAndView();
	     
	     String email = request.getParameter("email");
	     String pwd = request.getParameter("pwd");
	     
	     
	       Student std = studentService.checkstdlogin(email, pwd);
	       
	       if(std!=null)
	       {
	    	   HttpSession session = request.getSession();
	           
	           session.setAttribute("sid", std.getId());
	           session.setAttribute("sname", std.getName());
	           //sid is a session varible
	         mv.setViewName("stdhome");
	       }
	       else
	       {
	         mv.setViewName("stdlogin");
	         mv.addObject("message", "Login Failed");
	       }
	       
	       return mv;
	   }

}
