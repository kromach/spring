package board.controller.bean.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.model.dao.BoardDAO;
import board.model.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO;
	
	@Autowired
	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public void insertAticle(BoardDTO dto) throws SQLException {
		
		boardDAO.insertAticle(dto);
	}

	@Override
	public int selectAticleCount() throws SQLException {
		
		return boardDAO.selectAticleCount();
	}

	@Override
	public List selectAticleList(int start, int end) throws SQLException {
		
		return boardDAO.selectAticleList(start, end);
	}

	@Override
	public BoardDTO selectAticleInfo(int num, String readCountUp) throws SQLException {
		
		return boardDAO.selectAticleInfo(num, readCountUp);
	}

	@Override
	public int updateAticle(BoardDTO dto) throws SQLException {
		return boardDAO.updateAticle(dto);
	}

	@Override
	public int deleteAticle(int num, String pw) throws SQLException {

		return boardDAO.deleteAticle(num, pw) ;
	}

}
