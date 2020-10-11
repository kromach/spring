package board.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.model.dto.BoardDTO;
import common.bean.spring.LowerHashMap;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	BoardDAOImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}


	@Override
	public void insertAticle(BoardDTO dto) throws SQLException {
		
		// ref에 미리 시퀀스 번호 추적해서 다은 번호로 받아 추가하고 insert
		int number = 0;
		String numb = sqlSession.selectOne("board.maxNum");
		
		//System.out.println("numb" + numb);
		if(numb != null) {
			number = Integer.parseInt(numb);
			//number = Integer.parseInt(numb) + 1;
		} else {
			number = 1;
		}
		
		if(dto.getNum() != null && dto.getNum() > 0) { // 답글 일때
			// 이전 답글 step 미리 올리기
			HashMap map = new HashMap();
			map.put("ref", dto.getRef());
			map.put("re_step", dto.getReStep());
			
			sqlSession.update("board.updateReStep", map);
			
			dto.setReStep(dto.getReStep() + 1);
			dto.setReLevel(dto.getReLevel() + 1);
		} else { // 새글일때
		
			dto.setRef(number);
			dto.setReLevel(0);
			dto.setReStep(0);
			
		}
		
		dto.setNum(number); //num에 새 글 번호 삽입
		
		// 글 저장
		sqlSession.insert("board.insertAticle", dto);
	}

	@Override
	public int selectAticleCount() throws SQLException {
		int count = sqlSession.selectOne("board.selectAticleCount");
		
		return count;
	}

	@Override
	public List selectAticleList(int start, int end) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List list = sqlSession.selectList("board.selectAticleList", map);
		System.out.println(list);
		return list;
	}

	@Override
	public Map selectAticleInfo(int num, String readCountUp) throws SQLException {
		
		if(readCountUp == "Y") {
			// readCount 1 추가
			sqlSession.update("board.updateReadCount", num);
		}
		
		Map aticle = sqlSession.selectOne("board.selectAticleInfo", num);
//		System.out.println(aticle);
		return aticle;
	}

	@Override
	public int updateAticle(BoardDTO dto) throws SQLException {
		
		int result = -1;
		String dbpw = sqlSession.selectOne("board.selectPw", dto.getNum());
		
		if(dbpw != null) {
			if(dbpw.equals(dto.getPw())) {
				sqlSession.update("board.updateAticle", dto);
				result = 1;
			}
			else {
				result = 0;
			}
		}
		
		return result;
	}

	@Override
	public int deleteAticle(int num, String pw) throws SQLException {
		
		int result = -1;
		String dbpw = sqlSession.selectOne("board.selectPw", num);
		
		if(dbpw != null) {
			if(dbpw.equals(pw)) {
				sqlSession.delete("board.deleteAticle", num);
				result = 1;
			}
			else {
				result = 0;
			}
		}
		
		return result;
	}

}
