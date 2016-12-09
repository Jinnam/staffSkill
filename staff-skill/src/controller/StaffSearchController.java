package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MiniDao;
import dto.Religion;
import dto.School;
import dto.Skill;
import dto.Staff;
import dto.StaffAll;

@WebServlet("/StaffSearchController")
public class StaffSearchController extends HttpServlet {
	MiniDao dao;
	Staff staff;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new MiniDao();
		List<Religion> ReligionList = dao.mSelectForReligion();
		List<School> SchoolList = dao.mSelectForSchool();
		List<Skill> SkillList = dao.mSelectForSkill();
		
		request.setAttribute("SkillList", SkillList);
		request.setAttribute("ReligionList", ReligionList);
		request.setAttribute("SchoolList", SchoolList);
		request.getRequestDispatcher("/WEB-INF/jsp/staffSearch.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StaffSearchController doPost진입성공");
		String name =request.getParameter("name");
		String graduateDayOne = request.getParameter("graduateDayOne");
		String graduateDayTwo = request.getParameter("graduateDayTwo");
		int religion = Integer.parseInt(request.getParameter("religion"));
		String[] skill =request.getParameterValues("skill");
		String[] gender =request.getParameterValues("gender");
		String[] school =request.getParameterValues("school");
		
		

		
	//	이름 Staff에 셋팅
		staff = new Staff();
		if(name==null){
			staff.setName("*");
		}else{
			staff.setName(name);
		}
		for(int i=0;i<skill.length;i++){
			
		
	}
		for(int i=0;i<gender.length;i++){
			if(gender.length<1){
				
			}else if(gender.length>1){
		}
	
	
	
	
	
		}
}
}
