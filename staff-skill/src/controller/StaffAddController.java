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
		System.out.println("StaffAddController doPost���Լ���");
		request.setCharacterEncoding("euc-kr");
		
		Staff staff= new Staff();
		//�̸� �ް� staff�� ����
		staff.setName(request.getParameter("name"));
		//Sn �޾Ƽ� ��ġ�� staff�� ����
		staff.setSn((request.getParameter("snOne")+"-"+request.getParameter("snTwo")));
		//graduate �ް� staff�� ����
		staff.setGraduateday(request.getParameter("graduateDay"));
		//school �޾Ƽ� int�� ���� �� School ��ü�� ����
		int getschool = Integer.parseInt(request.getParameter("school"));
		School school = new School(getschool);
		staff.setSchool(school);
		//religion�޾Ƽ� int�� ���� �� Religion��ü�� ����
		int getreligion = Integer.parseInt(request.getParameter("religion"));
		Religion religion = new Religion(getreligion);
		staff.setReligion(religion);
		
		//String �迭�� int�迭�� �ٲٱ�
		String[] Stringskill =request.getParameterValues("skillNo");
		int[] skill = new int [Stringskill.length];
		for(int i=0;i<Stringskill.length; i++){
			skill[i] = Integer.parseInt(Stringskill[i]);
		}
		
		dao.mInsertStaff(staff, skill);
		
		
		response.sendRedirect(request.getContextPath()+"/StaffAddController");
	}

}
