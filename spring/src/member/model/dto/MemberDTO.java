package member.model.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class MemberDTO {
	
	@NotEmpty(message = "아이디를 입력해주세요.")
	private String id;
	
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String pw;
	
	@Size(min=1, message = "이름을 입력해주세요.")
	private String name;
	
	@Range(min=1, max=150, message = "나이가 올바르지 않습니다.")
	private Integer age;
	
	private String email;
	private Timestamp reg;
	private String image;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getReg() {
		return reg;
	}

	public void setReg(Timestamp reg) {
		this.reg = reg;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
