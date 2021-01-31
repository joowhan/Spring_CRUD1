package com.spring.crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import com.spring.bean.Emp;
import com.spring.dao.EmpDao;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class EmpController {
	@Autowired
	EmpDao dao;// will inject dao from XML file

	/*
	 * It displays a form to input data, here "command" is a reserved request
	 * attribute which is used to display object data into form
	 */
	@RequestMapping("/")
	public String show(Model m) {
		return "index";
	}

	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("command", new Emp());
		return "empform";
	}

	/*
	 * It saves object into database. The @ModelAttribute puts request data into
	 * model object. You need to mention RequestMethod.POST method because default
	 * request is GET
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest servletRequest, @ModelAttribute("emp") Emp emp, Model model) throws IOException {
		String files = "";
        
        if (null != files) {
        	String realPath = servletRequest.getSession().getServletContext().getRealPath("/resources/upload");
        	File dir = new File(realPath);
        	if (!dir.exists());

    		int sizeLimit = 15 * 1024 * 1024;
    		MultipartRequest multpartRequest = null;
    		multpartRequest = new MultipartRequest(servletRequest, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

    		String name = multpartRequest.getParameter("name");
    		String salary = multpartRequest.getParameter("salary");
    		String designation = multpartRequest.getParameter("designation");
    		files = multpartRequest.getFilesystemName("image");

    		float num = Float.parseFloat(salary);
    		
    		Emp emps = new Emp();
    		emps.setName(name);
    		emps.setSalary(num);
    		emps.setDesignation(designation);
    		emps.setImage(files);
    		dao.save(emps);
        }
    	model.addAttribute("emp", emp);
    	return "redirect:/viewemp";// will redirect to viewemp request mapping
	}

	/* It provides list of employees in model object */
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Emp> list = dao.getEmployees();
		m.addAttribute("list", list);
		return "viewemp";
	}

	/*
	 * It displays object data into form for the given id. The @PathVariable puts
	 * URL data into variable.
	 */
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable int id, Model m) {
		Emp emp = dao.getEmpById(id);
		m.addAttribute("command", emp);
		return "empeditform";
	}

	/* It updates model object. */
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(HttpServletRequest servletRequest, @ModelAttribute("emp") Emp emp, Model model) throws IOException {
String files = "";
        
        if (null != files) {
        	String realPath = servletRequest.getSession().getServletContext().getRealPath("/resources/upload");
        	File dir = new File(realPath);
        	if (!dir.exists());

    		int sizeLimit = 15 * 1024 * 1024;
    		MultipartRequest multpartRequest = null;
    		multpartRequest = new MultipartRequest(servletRequest, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

    		String name = multpartRequest.getParameter("name");
    		String salary = multpartRequest.getParameter("salary");
    		String designation = multpartRequest.getParameter("designation");
    		files = multpartRequest.getFilesystemName("image");
    		String id = multpartRequest.getParameter("id");

    		int num = Integer.parseInt(id);
    		float num2 = Float.parseFloat(salary);
    		
    		Emp emps = new Emp();
    		emps.setName(name);
    		emps.setSalary(num2);
    		emps.setDesignation(designation);
    		emps.setImage(files);
    		emps.setId(num);
    		dao.update(emps);
        }
        model.addAttribute("emp", emp);
		return "redirect:/viewemp";
	}

	/* It deletes record for the given id in URL and redirects to /viewemp */
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewemp";
	}
}
