package com.example.boot11.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot11.dto.UserDto;
import com.example.boot11.exception.PasswordException;
import com.example.boot11.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired private UserDao dao;
	//SecurityConfig 클래스에서 bean 으로 등록된 객체 주입받기 
	@Autowired private PasswordEncoder encoder;
	
	//업로드된 이미지를 저장할 파일시스템 상의 위치 
	@Value("${file.location}")
	private String fileLocation;
	
	@Override
	public void addUser(UserDto dto) {
		//role 은 일반 USER 로 넣어준다.
		dto.setRole("USER");
		//비밀번호 암호화 해서 dto 에 다시 넣기
		String encodedPwd=encoder.encode(dto.getPassword());
		dto.setPassword(encodedPwd);
		//dao 객체를 이용해서 DB 에 저장하기
		dao.insert(dto);
	}

	@Override
	public void getInfo(Model model) {
		//개인 정보를 본다는 것은 이미 로그인을 한 상태인데 로그인된 userName 은 어떻게 얻어내지???
		//Spring Security 의 기능을 통해서 얻어낸다 
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//로그인된 userName 을 이용해서 UserDto 객체를 dao 를 통해서 얻어낸다.
		UserDto dto=dao.getData(userName);
		//Model 객체에 담아주기
		model.addAttribute("dto", dto);
	}

	@Override
	public void updateUser(UserDto dto) {
		MultipartFile image=dto.getImage();
		//만일 선택한 프로필 이미지가 있다면 
		if(image.getSize() != 0) {
			//파일을 원하는 위치로 이동시켜 놓고 
			String saveFileName=UUID.randomUUID().toString();
			//저장할 파일의 전체 경로 구성하기
			String filePath=fileLocation+File.separator+saveFileName;
			try {
				//업로드된 파일을 이동시킬 목적지 File 객체
				File f=new File(filePath);
				//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
				dto.getImage().transferTo(f);
			}catch(Exception e) {
				e.printStackTrace();
			}
			//UserDto 에 저장된 이미지의 이름을 넣어준다.
			dto.setProfile(saveFileName);
		}
		//로그인된 userName 도 dto 에 담아준다 
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUserName(userName);
		
		//dao 를 이용해서 수정반영한다
		dao.update(dto);	
	}

	@Override
	public void updatePassword(UserDto dto) {
		//1. 로그인된 userName 을 얻어낸다.
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//2. 기존의 비밀번호를 DB 에서 읽어와서 (암호화된 비밀번호)
		String encodedPwd=dao.getData(userName).getPassword();
		//3. 입력한(암호화 되지 않은 구비밀번호) 와 일치하는지 비교 해서
		// .checkpw( 암호화되지않은 비밀번호, 암호화된 비밀번호)
		boolean isValid = BCrypt.checkpw(dto.getPassword(), encodedPwd);
		//4. 만일 일치하지 않으면 Exception 을 발생 시킨다.
		if(!isValid) {
			//커스텀 예외를 발생 시킨다 ExceptionController 로 실행의 흐름이 넘어간다 
			throw new PasswordException("비밀번호가 일치하지 않아요!");
		}
		//5. 일치하면 새비밀번호를 암호화해서 dto 에 담은 다음
		dto.setNewPassword(encoder.encode(dto.getNewPassword()));
		//6. userName 도 dto 에담고 
		dto.setUserName(userName);
		//7. DB 에 비밀번호 수정반영를 한다.
		dao.updatePwd(dto);
	}

}






































