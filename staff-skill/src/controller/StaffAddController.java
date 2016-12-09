package controller;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/StaffAddController")
public class StaffAddController extends HttpServlet {
	MiniDao dao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new MiniDao();
		List<Religion> ReligionList = dao.mSelectForReligion();
		List<School> SchoolList = dao.mSelectForSchool();
		List<Skill> SkillList = dao.mSelectForSkill();
		
		request.setAttribute("SkillList", SkillList);
		request.setAttribute("ReligionList", ReligionList);
		request.setAttribute("SchoolList", SchoolList);
		request.getRequestDispatcher("/WEB-INF/jsp/staffAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StaffAddController doPost진입성공");
		request.setCharacterEncoding("euc-kr");
		
		Staff staff= new Staff();
		//이름 받고 staff에 셋팅
		staff.setName(request.getParameter("name"));
		//Sn 받아서 합치고 staff에 셋팅
		staff.setSn((request.getParameter("snOne")+"-"+request.getParameter("snTwo")));
		//graduate 받고 staff에 셋팅
		staff.setGraduateday(request.getParameter("graduateDay"));
		//school 받아서 int로 변경 후 School 객체에 셋팅
		int getschool = Integer.parseInt(request.getParameter("school"));
		School school = new School(getschool);
		staff.setSchool(school);
		//religion받아서 int로 변경 후 Religion객체에 셋팅
		int getreligion = Integer.parseInt(request.getParameter("religion"));
		Religion religion = new Religion(getreligion);
		staff.setReligion(religion);
		
		//String 배열을 int배열로 바꾸기
		String[] Stringskill =request.getParameterValues("skillNo");
		int[] skill = new int [Stringskill.length];
		for(int i=0;i<Stringskill.length; i++){
			skill[i] = Integer.parseInt(Stringskill[i]);
		}
		
		dao.mInsertStaff(staff, skill);
		
		
		response.sendRedirect(request.getContextPath()+"/StaffAddController");
	}

}
