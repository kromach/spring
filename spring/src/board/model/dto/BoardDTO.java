package board.model.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class BoardDTO {

	private Integer num;
	
	@NotBlank(message = "작성자를 입력하세요.")
	private String writer;
	private String subject;
	
	@Email(message = "올바른 이메일 형식으로 입력하세요.")
	private String email;
	private String contents;
	
	@Size(min = 4, max = 10, message = "비밀번호는 4~10자리로 설정해주세요.")
	private String pw;
	
	private Timestamp reg;
	private Integer readCount;
	private Integer ref;
	private Integer reStep;
	private Integer reLevel;
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public Integer getReadCount() {
		return readCount;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	public Integer getRef() {
		return ref;
	}
	public void setRef(Integer ref) {
		this.ref = ref;
	}
	public Integer getReStep() {
		return reStep;
	}
	public void setReStep(Integer reStep) {
		this.reStep = reStep;
	}
	public Integer getReLevel() {
		return reLevel;
	}
	public void setReLevel(Integer reLevel) {
		this.reLevel = reLevel;
	}
		
}
