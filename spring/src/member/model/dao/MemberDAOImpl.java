package member.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	private final SqlSessionTemplate sqlSession;
	
	@Autowired
	MemberDAOImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// setter 주입방식
	// public void setMemberDAOImpl(SqlSessionTemplate sqlSession) {
	//	 this.sqlSession = sqlSession;
	// }
	
	// aop를 잘 사용하려면 인터페이스에 선언된 메서드를 사용 해야 한다.
	// 인터페이스에 존재하지 않는 추가 메서드들은 aop에서 잘 작동되지 않는 경우가 많다.
	@Override
	public void insertMember(MemberDTO dto) throws SQLException {
		int result = sqlSession.insert("member.insertMember", dto);
		
		System.out.println(result);
		
	}

	@Override
	public int loginCheck(MemberDTO dto) throws SQLException {
		
		int check = sqlSession.selectOne("member.loginCheck", dto);
		
		return check;
	}

	@Override
	public MemberDTO selectMemberInfo(String id) throws SQLException {
		
		MemberDTO dto =	sqlSession.selectOne("member.selectMemberInfo", id);
		
		return dto;
	}

	@Override
	public List selectMemberList() throws SQLException {
		return null;
	}

	@Override
	public int updateMember(MemberDTO dto) throws SQLException {
		
		int result = sqlSession.update("member.updateMember", dto);
		
		return result;
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		
		int result = sqlSession.delete("member.deleteMember", id);
		
		System.out.println(result);
	}

	@Override
	public int idAvailCheck(String id) throws SQLException {
		
		int result = sqlSession.selectOne("member.idAvailCheck", id);
		
		return result;
	}
	
}
