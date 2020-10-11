package board.controller.bean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import board.controller.bean.service.BoardService;

@Controller
@RequestMapping("/board/api")
public class BoardApiController {
	
	// @Autowired
	private BoardService boardService;
	
	@Autowired
	BoardApiController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 글 보기
	@RequestMapping(value = "/aticle/{num}", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public Map contentInit(@PathVariable(name="num", required=true) String num) throws Exception {
		
		//Map aticle = boardService.selectAticleInfo(num, "Y");
		
		//System.out.println(aticle);
		
		//return ResponseEntity.ok().body(aticle);
		Map<String, String> list = new HashMap<>();
		
		list.put("id", num);
		list.put("pw", "codevang123");
		list.put("location", "SEOUL");
		
		return list;
	}
}
