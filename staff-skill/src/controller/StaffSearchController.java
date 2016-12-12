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
import dto.SearchStaff;
import dto.Skill;
import dto.Staff;
import dto.StaffAll;

@WebServlet("/StaffSearchController")
public class StaffSearchController extends HttpServlet {
	MiniDao dao;
	SearchStaff searchStaff;
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
		
		//이름
		String name =request.getParameter("name");
		searchStaff.setName(name);
		//졸업일
		String graduateDayOne = request.getParameter("graduateDayOne");
		String graduateDayTwo = request.getParameter("graduateDayTwo");
		searchStaff.setGraduateDayOne(graduateDayOne);
		searchStaff.setGraduateDayTwo(graduateDayTwo);
		//종교
		int religion = Integer.parseInt(request.getParameter("religion"));
		Religion rel = new Religion();
		rel.setNo(religion);
		searchStaff.setReligion(rel);
		//기술
		String[] getskill =request.getParameterValues("skill");
		int[] skill={getskill.length};
		for(int i=0;i< getskill.length;i++){
			skill[i] =Integer.parseInt(getskill[i]);
			searchStaff.setSkill(skill);
		}
		//성별
		String[] getgender =request.getParameterValues("gender");	
		int[] gender={getgender.length};
		for(int i=0;i< getgender.length;i++){
			gender[i] =Integer.parseInt(getgender[i]);
			searchStaff.setGender(gender);
		}
		//최종학력
		String[] getschool =request.getParameterValues("school");
		int[] school={getschool.length};
		for(int i=0;i< getschool.length;i++){
			school[i] =Integer.parseInt(getschool[i]);
			searchStaff.setSchool(school);
		}
		
		=dao.mSelectAll();
		
		
	}
}
