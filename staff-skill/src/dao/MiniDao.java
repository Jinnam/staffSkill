package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Religion;
import dto.School;
import dto.Skill;
import dto.Staff;
import dto.StaffAll;
import dto.SearchStaff;

public class MiniDao {
	private final String driver = "oracle.jdbc.OracleDriver";
	private final String url="jdbc:oracle:thin:@localhost:1521:xe";
	private final String id="crud";
	private final String pw="java0000";
			
	
	public void mSearch(Staff staff, String[] skill,String[] gender,String[] scghool){
		if(staff.getName().equals("")){
			
		}
	}
	
	//모든 회원 정보 가져오기
	public List<StaffAll> mSelectAll(){
		List<StaffAll> list = new ArrayList<StaffAll>();
		SearchStaff staffAll = null;
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql="SELECT * FROM STAFF";
		try {
			conn=this.connection();
			stmt = conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()){
				staffAll=new SearchStaff();
				staffAll.setNo(rs.getInt("no"));
				staffAll.setName(rs.getString("name"));
				staffAll.setGraduateday(rs.getString("graduateday"));
				staffAll.setReligion(rs.getInt("religionno"));
				staffAll.setSchool(rs.getInt("schoolno"));
				for(int i=0 ;i<(rs.getString("skillno")).length();i++){
					staffAll.setSkill(rs.getString("skillno"));
				}
				
				staffAll.setGender(rs.getString("sn"));
				list.add(staffAll);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn, stmt, rs);
		}
		return list;

	}
/*	//테이블에서 주민번호 가져와서 성별 검색?
	public Staff mSelectForSn(){
		Staff staff = null;
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql="SELECT NAME,SN,GRADUATEDAY,SCHOOLNO,RELIGIONNO FROM STAFF WHERE SN=?";
		try {
			conn=this.connection();
			stmt = conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			if(rs.next()){
				staff=new Staff();
				staff.setNo(rs.getInt("no"));
				staff.setName(rs.getString("name"));
				staff.setGraduateday(rs.getString("graduateday"));
				staff.setReligion(rs.getInt("religionno"));
				staff.setSchool(rs.getInt("schoolno"));
				staff.setSn(rs.getString("sn"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn, stmt, rs);
		}
		return staff;

	}*/
	
	//한명의 정보 STAFF 테이블에서 가져오기(컬럼별)
	public StaffAll mSelectForOne(String colum, String value){
		StaffAll staffAll = null;
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql="SELECT * FROM STAFF WHERE "+colum+"=?";
		try {
			conn=this.connection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, value);
			rs= stmt.executeQuery();
			if(rs.next()){
				staffAll=new StaffAll();
				staffAll.setNo(rs.getInt("no"));
				staffAll.setName(rs.getString("name"));
				staffAll.setGraduateday(rs.getString("graduateday"));
				staffAll.setReligion(rs.getInt("religionno"));
				staffAll.setSchool(rs.getInt("schoolno"));
				staffAll.setSn(rs.getString("sn"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn, stmt, rs);
		}
		return staffAll;

	}
	
	//Staff와 skill에 입력
	public int mInsertStaff(Staff staff, int[] skill){
		int rowCount = 0;
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		int staffNo = 0;
		String[] keyCol = {"NO"};


		
		String staffSql="INSERT INTO staff(NO,NAME,SN,GRADUATEDAY,SCHOOLNO,RELIGIONNO) "
				+ "VALUES (STAFF_SEQ.nextval,?,?,?,?,?)";
		System.out.println(staffSql+" : staffSql");
		try {
			conn=this.connection();
			stmt = conn.prepareStatement(staffSql, keyCol);
			stmt.setString(1, staff.getName());
			stmt.setString(2, staff.getSn());
			stmt.setString(3, staff.getGraduateday());
			stmt.setInt(4, staff.getSchool().getNo());
			stmt.setInt(5, staff.getReligion().getNo());
			rowCount = stmt.executeUpdate();
			System.out.println("STAFF 입력성공");
			rs = stmt.getGeneratedKeys();
			if(rs.next()){
				staffNo = rs.getInt(1);
				System.out.println(staffNo+" : <<<getGeneratedKeys");
			}
			System.out.println(skill.length+" : skill.length");
			for(int i=0;i<skill.length;i++){
				String skillSql="INSERT INTO STAFFSKILL(NO,STAFFNO,SKILLNO) VALUES (STAFFSKILL_SEQ.nextval,?,?)";
				System.out.println(skillSql+" : skillSql");
				try{
					stmt=conn.prepareStatement(skillSql);
					stmt.setInt(1, staffNo);
					stmt.setInt(2, skill[i]);
					System.out.println(i+" : for반복 횟수");
					stmt.executeUpdate();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}System.out.println("STAFF SKILL 입력성공");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(conn, stmt, null);
		}return rowCount;	
	}
	
	//Skill 목록 가져오기
		public List<Skill> mSelectForSkill(){
			List<Skill> list = new ArrayList<Skill>();
			Skill skill=null;
			Connection conn = null;
			PreparedStatement stmt=null;
			ResultSet rs = null;
			String sql="SELECT * FROM SKILL";
			try{
				conn=this.connection();
				stmt=conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()){
					skill = new Skill();
					skill.setNo(rs.getInt("no"));
					skill.setName(rs.getString("name"));
					list.add(skill);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.close(conn, stmt, rs);
			}
			
			return list;	
		}
		
	//최종 학력 출력
	public List<School> mSelectForSchool(){
		List<School> list = new ArrayList<School>();
		School school=null;
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql="SELECT * FROM school";
		try{
			conn=this.connection();
			stmt=conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				school = new School();
				school.setNo(rs.getInt("no"));
				school.setGraduate(rs.getString("graduate"));
				list.add(school);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn, stmt, rs);
		}
		return list;	
	}
	
	//종교 목록 가져오기
	public List<Religion> mSelectForReligion(){
		List<Religion> list = new ArrayList<Religion>();
		Religion religion=null;
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql="SELECT * FROM RELIGION";
		try{
			conn=this.connection();
			stmt=conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				religion = new Religion();
				religion.setNo(rs.getInt("no"));
				religion.setName(rs.getString("name"));
				list.add(religion);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn, stmt, rs);
		}
		
		return list;	
	}
	
	public Connection connection(){
		Connection conn=null;
		 try{
			 System.out.println("connection 진입");
			 //드라이버 로딩  
			 Class.forName(this.driver);
			 conn=DriverManager.getConnection(this.url, this.id, this.pw);
			 System.out.print("DB 연결성공");        
		 }catch(Exception e){
			 e.printStackTrace();
			 System.out.print("DB 연결실패");
		 }
		return conn;
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
        if(rs != null) {
            try {
            	rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null) {
            try {
            	stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
            	conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
