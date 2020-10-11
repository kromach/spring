package board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import board.model.dto.BoardDTO;

public interface BoardDAO {
	
	//글 저장
	public void insertAticle(BoardDTO dto) throws SQLException;
	
	//전체글 개수 검색
	public int selectAticleCount() throws SQLException;
	
	//게시글 범위 지정 가져오기
	public List selectAticleList(int start, int end) throws SQLException;
	
	//게시글 한개 정보 가져오기 (조회수 업)
	public Map selectAticleInfo(int num, String readCountUp) throws SQLException;

	// 게시글 수정 
	public int updateAticle(BoardDTO dto) throws SQLException;
	
	// 글 삭제
	public int deleteAticle(int num, String pw) throws SQLException;
}
