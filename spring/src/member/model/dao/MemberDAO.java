package member.model.dao;

import java.sql.SQLException;
import java.util.List;

import member.model.dto.MemberDTO;

// DAO - Data Access Object
// DB 명세 주석을 달아주는 경우가 있음
// 국내는 문서로 별도로 주는 경우가 많고 해외는 주석으로 달아두는 경우가 많다.
public interface MemberDAO {
	
	// SQLException : 쿼리문 잘못 썼거나, 리턴 타입 잘못 받았거나, 입력 데이터가 문제 있거나
	// 개발이 완료되고 테스트가 끝난 이후 SQLException이 발생하는 것은 하드웨어적인 문제인 경우가 많다. 
	// (서버가 죽거나, 인터넷 끊겼거나 등등). 
	// 정사적인 서비스가 이뤄지는 상황에서 SQLException은 복구할 수 있는 예외가 아니기 때문에 해결 방법이 없음
	
	// 회원 가입
	public void insertMember(MemberDTO dto) throws SQLException;
	
	// 로그인 체크 (id, pw) 
	public int loginCheck(MemberDTO dto) throws SQLException;
	
	// 회원 정보 조회
	public MemberDTO selectMemberInfo(String id) throws SQLException;
	
	// 전체 회원 목록 조회
	public List selectMemberList() throws SQLException;
	
	// 회원 정보 수정
	public int updateMember(MemberDTO dto) throws SQLException;
	
	// 회원 탈퇴
	public void deleteMember(String id) throws SQLException;
	
	// 아이디 중복 체크
	public int idAvailCheck(String id) throws SQLException;
	
}
