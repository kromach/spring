package test.spring.model;

import java.util.Date;

public class TestDTO {
	
	private String id;
	private String pw;
	private Date reg; 
	
	TestDTO() {};
	TestDTO(String id) {
		this.id = id;
	};
	TestDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	};
	TestDTO(String id, String pw, Date reg) {
		this.id = id;
		this.pw = pw;
		this.reg = reg;
	};
	
	
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getPw() {
		return pw;
	}
	public final void setPw(String pw) {
		this.pw = pw;
	}
	public final Date getReg() {
		return reg;
	}
	public final void setReg(Date reg) {
		this.reg = reg;
	}
	
}
