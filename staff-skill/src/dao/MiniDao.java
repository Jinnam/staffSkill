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

public class MiniDao {
	private final String driver = "oracle.jdbc.OracleDriver";
	private final String url="jdbc:oracle:thin:@localhost:1521:xe";
	private final String id="crud";
	private final String pw="java0000";
			
	
	public void mSearch(Staff staff, String[] skill,String[] gender,String[] scghool){
		if(staff.getName().equals("")){
			
		}
	}
	
	//��� ȸ�� ���� ��������
	public List<StaffAll> mSelectAll(){
		List<StaffAll> list = new ArrayList<StaffAll>();
		StaffAll staffAll = null;
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql="SELECT STAFF.* , STAFFSKILL.* FROM STAFF FULL OUTER JOIN STAFFSKILL ON STAFF.NO = STAFFSKILL.STAFFNO";
		try {
			conn=this.connection();
			stmt = conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()){
				staffAll=new StaffAll();
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
	//���̺����� �ֹι�ȣ �����ͼ� ���� �˻�?
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

	}
	
	//�Ѹ��� ���� STAFF ���̺����� ��������(�÷���)
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
	
	public Skill mSelectSkill(){
	String skillSql="SELECT SKILLNO FROM STAFFSKILL WHERE sn=?";
	stmt2=conn.prepareStatement(skillSql);
	stmt2.setString(1, rs.getString("sn"));
	rs2=stmt2.executeQuery();
	while(rs2.next()){
		skill.s
		staffAll.setSkill("skill");
	}
	
	//Staff�� skill�� �Է�
	public int mInsertStaff(Staff staff, String[] skill){
		int rowCount = 0;
		Connection conn = null;
		PreparedStatement stmt=null;
		
		String staffSql="INSERT INTO staff(NO,NAME,SN,GRADUATEDAY,SCHOOLNO,RELIGIONNO) VALUES (STAFF_SEQ.nextval,?,?,?,?,?)";
		System.out.println(staffSql+" : staffSql");
		try {
			conn=this.connection();
			stmt = conn.prepareStatement(staffSql);
			stmt.setString(1, staff.getName());
			stmt.setString(2, staff.getSn());
			stmt.setString(3, staff.getGraduateday());
			stmt.setInt(4, staff.getSchoolNo());
			stmt.setInt(5, staff.getReligionNo());
			stmt.executeUpdate();
			stmt.getg
			System.out.println("STAFF �Է¼���");
			
			for(int i=0;i<skill.length;i++){
				String skillSql="INSERT INTO STAFFSKILL(NO,STAFFNO,SKILLNO) VALUES (STAFFSKILL_SEQ.nextval,(SELECT no FROM STAFF WHERE sn=?) ,?)";
				System.out.println(skillSql+" : skillSql");
				try{
					stmt=conn.prepareStatement(skillSql);
					stmt.setString(1, staff.getSn());
					stmt.setString(2, skill[i]);
					System.out.println(i+" : for�ݺ� Ƚ��");
					stmt.executeUpdate();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}System.out.println("STAFF SKILL �Է¼���");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(conn, stmt, null);
		}return rowCount;	
	}
	
	//Skill ��� ��������
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
		
	//���� �з� ���
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
	
	//���� ��� ��������
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
			 System.out.println("connection ����");
			 //����̹� �ε�  
			 Class.forName(this.driver);
			 conn=DriverManager.getConnection(this.url, this.id, this.pw);
			 System.out.print("DB ���Ἲ��");        
		 }catch(Exception e){
			 e.printStackTrace();
			 System.out.print("DB �������");
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