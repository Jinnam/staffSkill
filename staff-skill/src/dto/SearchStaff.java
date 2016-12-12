package dto;

public class SearchStaff {
	
	private int no;
	private String name;
	private Religion religion;
	private String graduateDayOne;
	private String graduateDayTwo;
	private int[] skill;
	private int[] gender;
	private int[] school;
	
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}
	public String getGraduateDayOne() {
		return graduateDayOne;
	}
	public void setGraduateDayOne(String graduateDayOne) {
		this.graduateDayOne = graduateDayOne;
	}
	public String getGraduateDayTwo() {
		return graduateDayTwo;
	}
	public void setGraduateDayTwo(String graduateDayTwo) {
		this.graduateDayTwo = graduateDayTwo;
	}
	public int[] getSkill() {
		return skill;
	}
	public void setSkill(int[] skill) {
		this.skill = skill;
	}
	public int[] getGender() {
		return gender;
	}
	public void setGender(int[] gender) {
		this.gender = gender;
	}
	public int[] getSchool() {
		return school;
	}
	public void setSchool(int[] school) {
		this.school = school;
	}
	
	

}
