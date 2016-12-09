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
	School school;
	Religion religion;
	Skill skill;
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
		staff.setName(request.getParameter("name"));
		staff.setSn(request.getParameter("snOne")+"-"+request.getParameter("snTwo"));
		staff.setGraduateday(request.getParameter("graduateDay"));

		school.setNo(Integer.parseInt(request.getParameter("school")));
		religion.setNo(Integer.parseInt(request.getParameter("religion")));
		skill.setNo(0);
		String[] getSkill =request.getParameterValues("skill");
		

		
		response.sendRedirect(request.getContextPath()+"/StaffAddController");
	}

}
