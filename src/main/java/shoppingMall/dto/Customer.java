package shoppingMall.dto;

public class Customer {
	private int cusno;
	private String passno;
	private String cusname;
	private String birth;
	private String callno;
	private int sex;

	public Customer() {}


	public Customer(int cusno, String passno) {
		this.cusno = cusno;
		this.passno = passno;
	}


	public Customer(int cusno, String cusname, String birth, String callno, int sex) {
		this.cusno = cusno;
		this.cusname = cusname;
		this.birth = birth;
		this.callno = callno;
		this.sex = sex;
	}

	public Customer(int cusno, String passno, String cusname, String birth, String callno, int sex) {
		this.cusno = cusno;
		this.passno = passno;
		this.cusname = cusname;
		this.birth = birth;
		this.callno = callno;
		this.sex = sex;
	}


	public Customer(int cusno) {
		this.cusno = cusno;
	}

	public int getCusno() {
		return cusno;
	}

	public void setCusno(int cusno) {
		this.cusno = cusno;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPassno() {
		return passno;
	}


	public void setPassno(String passno) {
		this.passno = passno;
	}
	
	public String getCallno() {
		return callno;
	}

	public void setCallno(String callno) {
		this.callno = callno;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return String.format("%d %s %s %s %s", cusno, cusname, birth == null ? "" : birth,
				callno == null ? "" : callno, sex == 0 ? "" : sex);
	}

}
