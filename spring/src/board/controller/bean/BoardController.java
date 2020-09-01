package board.controller.bean;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import board.controller.bean.service.BoardService;
import board.model.dto.BoardDTO;

@Controller
@RequestMapping("/board/")
public class BoardController {

	// @Autowired
	private BoardService boardService;
	
	@Autowired
	BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("list.git")
	public String list(String pageNum, Model model) throws SQLException {
		
		// 비즈니스 로직 처리 -> Service로 이관 되야 함.
		// mvc actionBean() 메서드 있는 bean 클래스 들
		// Controller -> XxxService -> XxxServiceImpl -> XxxDAO -> XxxDAOImpl
		
		if(pageNum == null || pageNum.length() < 1) {
			pageNum = "1";
		}
		
		// 보여줄 페이지 세팅
		int pageSize = 5; //한페이지에 보여줄 게시글 수 
				
		// 현재 페이지에 보여줄 게시글의 시작과 끝 등등 정보 세팅
		int currPage = Integer.parseInt(pageNum); // 계산을 위해 형변환
		int startRow = (currPage - 1) * pageSize + 1; // 가져올 첫번째 글 번호
		int endRow = currPage * pageSize; // 가져올 마지막 글 번호
		int count = 0; // DB의 총 게시글 개수를 담을 변수. 
		int number = 0; // 게시판 상의 글 번호 뿌려줄 변수.
		
		//전체 게시판 글 개수
		
		List aticleList = null;
		count = boardService.selectAticleCount();

		int pageCount = 0;
		int pageBlock = 5;
		int startPage = 1;
		int endPage = 1;
		
		if(count > 0) {
			aticleList = boardService.selectAticleList(startRow, endRow);

			//articleList
			//게시판용 글번호 세팅
			number = count - (currPage - 1) * pageSize;

			// 게시판 목록 페이지 번호 뷰어 설정
			
			// 10 페이지씩 끊어서 목록 표시
			// 총 페이지 수 계산 (count / pageSize) + 페이지사이즈로 나눈 나머지가 있으면 페이지 1 추가
			pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			
			// 현재 위치한 페이지에서 페이지뷰어 첫번째 숫자가 무엇인지 찾기  
			startPage = (int)((currPage - 1) / pageBlock) * pageBlock + 1;
			
			// 현재 페이지에서 보여줄 마지막 페이지 번호 
			endPage = startPage + pageBlock - 1;
			//out.println("<script>console.log('startPage : "+startPage+", endPage : "+ endPage + ", currPage : "+ currPage + "')</script>");
			
			// 마지막에 보여지는 페이지뷰어에 페이지 개수가 pageBlock 보다 적으면
			if ( pageCount < endPage ) endPage = pageCount;
		} 
		
		//model에 담아 전달
		model.addAttribute("pageNum", pageNum);
		// model.addAttribute("pageSize", new Integer(pageSize)); //숫자로 보내고 싶은 경우 new Integer()로 감싸준다.
		model.addAttribute("currPage", new Integer(currPage));
		//model.addAttribute("startRow", new Integer(startRow));
		//model.addAttribute("endRow", new Integer(endRow));
		model.addAttribute("count", new Integer(count));
		model.addAttribute("number", new Integer(number));
		model.addAttribute("aticleList", aticleList);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/list";
	}
	
	// 글 보기
	@RequestMapping("content.git")
	public String content(int num, String pageNum, Model model) throws SQLException {
		
		BoardDTO aticle = boardService.selectAticleInfo(num, "Y");
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("aticle", aticle);
		
		return "board/content";
	}
	
	// 새글, 답글 쓰기
	@RequestMapping("writeForm.git")
	public String writeForm(String num, String pageNum, Model model) throws SQLException {

		// 답글인 경우 기존 글의 데이터 가져오기
		if (num != null && Integer.parseInt(num) > 0) {
			BoardDTO aticle = boardService.selectAticleInfo(Integer.parseInt(num), "N");
			model.addAttribute("aticle", aticle);
		}
		
		model.addAttribute("pageNum", pageNum);
		
		return "board/writeForm";
	}
	
	@RequestMapping("writePro.git")
	public String writePro(String pageNum, BoardDTO dto, Model model) throws SQLException {
		
		System.out.println("num " + dto.getNum());
		boardService.insertAticle(dto);
		
		model.addAttribute("pageNum", pageNum);

		return "board/writePro";
	}
	
	@RequestMapping("modifyForm.git")
	public String modifyForm(int num, String pageNum, Model model) throws SQLException {
		
		BoardDTO article = boardService.selectAticleInfo(num, "N");
		model.addAttribute("article", article);
		model.addAttribute("pageNum", pageNum);
		
		return "board/modifyForm";
	}
	
	@RequestMapping("modifyPro.git")
	public String modifyPro(BoardDTO dto, String pageNum, Model model) throws SQLException {
		
		int result = boardService.updateAticle(dto);
		
		model.addAttribute("num", dto.getNum());
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("result", result);
		
		return "board/modifyPro";
	}

	@RequestMapping("deleteForm.git")
	public String deleteForm(int num, String pageNum, Model model) throws SQLException {
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("num", num);
		
		return "board/deleteForm";
	}
	
	@RequestMapping("deletePro.git")
	public String deletePro(BoardDTO dto, String pageNum, Model model) throws SQLException {
		
		int result = boardService.deleteAticle(dto.getNum(), dto.getPw());
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("result", result);
		
		return "board/deletePro";
	}
	
	
}
