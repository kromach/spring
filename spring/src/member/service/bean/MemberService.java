package member.service.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import member.model.dto.MemberDTO;

public interface MemberService {

	
	// 회원가입
	public void insertMember(MemberDTO dto) throws SQLException;
	
	// 로그인 체크 (id, pw) 
	public int loginCheck(MemberDTO dto) throws SQLException;
	
	// 회원 정보 조회
	public MemberDTO selectMemberInfo() throws SQLException;
	
	// 전체 회원 목록 조회
	public List selectMemberList() throws SQLException;
	
	// 회원 정보 수정
	public int updateMember(MemberDTO dto) throws SQLException;
	
	// 회원 탈퇴
	public int deleteMember(MemberDTO dto) throws SQLException;
	
	// 아이디 중복 체크
	public int idAvailCheck(String id) throws SQLException;
	
	// 세션 삭제
	public void removeSessionAttr(String sessionName);
	
	// 이미지 저장 & 파일명 만들기
	public String imageUpload(MultipartHttpServletRequest request) throws Exception;
	
}
