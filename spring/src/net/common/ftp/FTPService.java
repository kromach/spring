package net.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

// FTP 이용을 위해 pom.xml에 commons-net 추가 

public class FTPService {
    
    public void upload(File file, String newFileNm) throws SocketException, IOException, Exception {
    	
		String serverIp = "ip주소";
		// int serverPort = 21;  // port가 필요한 경우 사용 필요
		String user = "아이디";
		String password = "비밀번호";
		
		FileInputStream fis = null;
		FTPClient ftpClient = new FTPClient();
        
		try {
			ftpClient.connect(serverIp);  //ftp 연결
			// ftpClient.connect(serverIp, serverPort);  //ftp 연결
			ftpClient.setControlEncoding("utf-8");    //ftp 인코딩설정
			int reply = ftpClient.getReplyCode();     //응답코드받기
			
			//System.out.println(reply);
			
			if (!FTPReply.isPositiveCompletion(reply)) {  //응답이 false 라면 연결 해제 exception 발생
			    ftpClient.disconnect();
			    throw new Exception(serverIp+" FTP 서버 연결 실패");
			}
			
			ftpClient.setSoTimeout(1000 * 10);   //timeout 설정
			boolean loginchk = ftpClient.login(user, password);     //ftp 로그인
			
			System.out.println("로그인 성공 여부 : " + loginchk);
			
			//ftpClient.changeWorkingDirectory("/html");
			
			// 경로 존재 확인 후 없으면 신규 폴더 생성
			if(!ftpClient.changeWorkingDirectory("/html/upload")) {
				ftpClient.makeDirectory("/html/upload"); // 만들려고하는 폴더의 전체 경로 
			}
			// 경로 생성 또는 확인 후 이동
			ftpClient.changeWorkingDirectory("/html/upload");
			
			//File uploadFile = new File("d:\\context.txt");
			
			fis = new FileInputStream(file);
			
			boolean isSuccess = ftpClient.storeFile(newFileNm, fis); // 파일 업로드 처리
			
			if (isSuccess) {
				System.out.println("업로드 성공");
			}
			
			ftpClient.logout(); // 로그아웃
			
		} catch (SocketException e) {
		    System.out.println("Socket:"+e.getMessage());
		} catch (IOException e) {
		    System.out.println("IO:"+e.getMessage());
		} finally {
		    if (ftpClient != null && ftpClient.isConnected()) {
		        try { ftpClient.disconnect(); } catch (IOException e) {}
		    }
		    if (fis != null) try { fis.close(); } catch(IOException ex) {}
		}
    }
}
