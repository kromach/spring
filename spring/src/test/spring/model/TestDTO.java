package test.spring.model;

import java.beans.ConstructorProperties;
import java.util.Date;

public class TestDTO {
	
	private String id;
	private String pw;
	private String reg; 
	
	TestDTO() {};
	TestDTO(String id) {
		this.id = id;
	};

	@ConstructorProperties({"user_id", "user_pw"})
	TestDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
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
	public final String getReg() {
		return reg;
	}
	public final void setReg(String reg) {
		this.reg = reg;
	}
	
}
