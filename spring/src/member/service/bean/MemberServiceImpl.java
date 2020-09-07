package member.service.bean;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import net.common.ftp.FTPService;

/*
	@Component
 	@Controller, @Service, @Repository
 */

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;
	
	@Autowired
	MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void insertMember(MemberDTO dto) throws SQLException {

		memberDAO.insertMember(dto);
	}

	@Override
	public int loginCheck(MemberDTO dto) throws SQLException {
		int check = memberDAO.loginCheck(dto);
		
		if(check == 1) {
			
			RequestContextHolder.getRequestAttributes().setAttribute("memId", dto.getId(), RequestAttributes.SCOPE_SESSION);
		}
		
		return check;
	}

	@Override
	public MemberDTO selectMemberInfo() throws SQLException {

		// session.getAttribute("memId")와 동일
		String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("memId", RequestAttributes.SCOPE_SESSION);
		
		return memberDAO.selectMemberInfo(id);
	}

	@Override
	public List selectMemberList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberDTO dto) throws SQLException {
		
		String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("memId", RequestAttributes.SCOPE_SESSION);
		dto.setId(id);
		
		// 비밀번호 일치 여부 확인 한다면
		//int check = memberDAO.loginCheck(dto);
		
		int result = 0;
		//if(check == 1) {
			result = memberDAO.updateMember(dto);
		//}
		//model.addAttribute("check", check);
		
		return result;
	}

	@Override
	public int deleteMember(MemberDTO dto) throws SQLException {

		String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("memId", RequestAttributes.SCOPE_SESSION);
		dto.setId(id);
		
		int check = memberDAO.loginCheck(dto);

		if(check == 1) {
			memberDAO.deleteMember(id);
			removeSessionAttr("memId");
		}
		
		return check;
	}

	@Override
	public int idAvailCheck(String id) throws SQLException {
		
		int check = memberDAO.idAvailCheck(id);
		
		return check;
	}

	@Override
	public void removeSessionAttr(String sessionName) {
		// session.removeAttribute("memId");
		RequestContextHolder.getRequestAttributes().removeAttribute(sessionName, RequestAttributes.SCOPE_SESSION);
	}

	@Override
	public String imageUpload(MultipartHttpServletRequest request) throws Exception {
		
		// 파일정보 꺼내기
		MultipartFile mf = null;
		String imgPath = null;
		String newName = null;

        // input type="file"의 name 값으로 업로드한 파일 정보 받아오기
		mf = request.getFile("img");
		
		// 파일 저장 폴더 지정
		String path = request.getRealPath("resources/upload");
		
		String orgName = mf.getOriginalFilename();

		if(orgName != null && orgName.length() > 0 ) { // 전송된 파일이 있는지 체크 
	
			// -----------------------------------------------------------
			// 이미지 이름 중복처리 시작 

			// 파일의 이름만 추출 (처음부터, . 이전까지)
			String imgName = orgName.substring(0, orgName.lastIndexOf("."));
			
			// 파일의 확장자만 추출 (.부터 끝까지)
			String ext = orgName.substring(orgName.lastIndexOf("."));
			
			// 파일명 중복을 방지하기 위해 지금 시간을 밀리초로 받아와 파일명에 추가
			long cur = System.currentTimeMillis();
			newName = imgName + cur + ext;  //원본 이름 + 현재시각(millis) + 확장자
			
			imgPath = path + "\\" + newName; // 경로 + 중복처리 된 파일명
			// ------------------------------------------------------------
	
			// File copyFile = new File(imgPath); // 새로운 이미지 경로로 업로드 한 파일 복사 생성
	
			// mf.transferTo(copyFile); // 지정된 경로로 파일 저장
			
			File file = new File(mf.getOriginalFilename());
			mf.transferTo(file); // 새로운 이미지 경로로 업로드 한 파일 복사 생성
			
			FTPService ftp = new FTPService();
			
			ftp.upload(file, newName);
		}
		
		return newName;
	}
	
}
