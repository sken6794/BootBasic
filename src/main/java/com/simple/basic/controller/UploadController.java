package com.simple.basic.controller;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simple.basic.command.UploadVO;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Value("${project.upload.path}")
	private String uploadPath;

	//폴더 생성 함수
	public String makeFolder() {
		String path = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		File file = new File(uploadPath+"/"+path);
		if(file.exists()==false) { //존재하면 true, 존재하지 않으면 false
			file.mkdirs();
		}
		return path;
	}





	@GetMapping("/upload")
	public String upload() {
		return "upload/upload";
	}
	//파일데이터는 MultipartFile 객체로 받습니다.
	@PostMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file) {

		//파일이름을 받습니다.
		String originName = file.getOriginalFilename();

		//브라우저 별로 파일의 경로가 다를수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
		String filename = originName.substring(originName.lastIndexOf("\\")+1);

		//파일사이즈
		long size = file.getSize();

		//동일한 파일을 재업로드시 기존파일 덮어버리기 때문에, 난수 이름으로 파일명을 바꿔서 올림
		String uuid = UUID.randomUUID().toString();

		//날짜별로 폴더 생성
		String filepath = makeFolder();


		//세이브할 경로
		String savepath = uploadPath+"/"+filepath+"/"+uuid+"_"+filename;

		//데이터베이스 추후에 저장
		//System.out.println(originName);
		//System.out.println("사이즈 : "+size);
		System.out.println("uuid : "+uuid);
		System.out.println("실제 파일명 : "+filename);
		System.out.println("??"+uploadPath);
		System.out.println("날짜폴더경로 : "+filepath);
		System.out.println("세이브 할 경로 : " + savepath);


		try {
			File saveFile = new File(savepath);
			file.transferTo(saveFile);
		} catch (Exception e) {
			System.out.println("파일업로드 중 error 발생");
			e.printStackTrace();
		}

		return "upload/upload_ok";
	}


	//복수 태그를 사용한 다중파일 업로드 - List(MultipartFile)
	@PostMapping("/upload_ok2")
	public String upload_ok2(@RequestParam("file") List<MultipartFile> list) {
		//빈 file객체는 제외, 파일 받아온것만 리스트로 새로 생성
		list = list.stream().filter(f->f.isEmpty()==false).collect(Collectors.toList());

		for(MultipartFile file : list) {


			//System.out.println(file.isEmpty());
			//파일이름을 받습니다.
			String originName = file.getOriginalFilename();
			//브라우저 별로 파일의 경로가 다를수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
			String filename = originName.substring(originName.lastIndexOf("\\")+1);
			//파일사이즈
			long size = file.getSize();
			//동일한 파일을 재업로드시 기존파일 덮어버리기 때문에, 난수 이름으로 파일명을 바꿔서 올림
			String uuid = UUID.randomUUID().toString();
			//날짜별로 폴더 생성
			String filepath = makeFolder();
			//세이브할 경로
			String savepath = uploadPath+"/"+filepath+"/"+uuid+"_"+filename;
			//데이터베이스 추후에 저장
			//System.out.println(originName);
			//System.out.println("사이즈 : "+size);
			System.out.println("uuid : "+uuid);
			System.out.println("실제 파일명 : "+filename);
			System.out.println("??"+uploadPath);
			System.out.println("날짜폴더경로 : "+filepath);
			System.out.println("세이브 할 경로 : " + savepath);
			System.out.println("-------------------------------------------------");
			try {
				File saveFile = new File(savepath);
				file.transferTo(saveFile);
			} catch (Exception e) {
				System.out.println("파일업로드 중 error 발생");
				e.printStackTrace();
			}
		}
		return "upload/upload_ok";
	}


	@PostMapping("/upload_ok3")
	public String upload_ok3(MultipartHttpServletRequest files) {

		List<MultipartFile> list = files.getFiles("file"); //클라이언트 input name 을 매개변수로
		for(MultipartFile file : list) {


			//System.out.println(file.isEmpty());
			//파일이름을 받습니다.
			String originName = file.getOriginalFilename();
			//브라우저 별로 파일의 경로가 다를수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
			String filename = originName.substring(originName.lastIndexOf("\\")+1);
			//파일사이즈
			long size = file.getSize();
			//동일한 파일을 재업로드시 기존파일 덮어버리기 때문에, 난수 이름으로 파일명을 바꿔서 올림
			String uuid = UUID.randomUUID().toString();
			//날짜별로 폴더 생성
			String filepath = makeFolder();
			//세이브할 경로
			String savepath = uploadPath+"/"+filepath+"/"+uuid+"_"+filename;
			//데이터베이스 추후에 저장
			//System.out.println(originName);
			//System.out.println("사이즈 : "+size);
			System.out.println("uuid : "+uuid);
			System.out.println("실제 파일명 : "+filename);
			System.out.println("??"+uploadPath);
			System.out.println("날짜폴더경로 : "+filepath);
			System.out.println("세이브 할 경로 : " + savepath);
			System.out.println("-------------------------------------------------");
			try {
				File saveFile = new File(savepath);
				file.transferTo(saveFile);
			} catch (Exception e) {
				System.out.println("파일업로드 중 error 발생");
				e.printStackTrace();
			}
		}

		return "upload/upload_ok";
	}


	//일반 컨트롤러에서 rest 방식으로 받으려면 @ResponseBody 어노테이션을 작성
	// 비동기 방식으로 받기 -> 폼데이터로 받는거라 @RequestBody는 필요없다
	@PostMapping("/upload_ok4")
	public @ResponseBody ResponseEntity<String> upload_ok4(UploadVO vo) {

		MultipartFile file = vo.getFile();
		System.out.println("file : "+file);
		//System.out.println(file.isEmpty());
		//파일이름을 받습니다.
		String originName = file.getOriginalFilename();
		//브라우저 별로 파일의 경로가 다를수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
		String filename = originName.substring(originName.lastIndexOf("\\")+1);
		//파일사이즈
		long size = file.getSize();
		//동일한 파일을 재업로드시 기존파일 덮어버리기 때문에, 난수 이름으로 파일명을 바꿔서 올림
		String uuid = UUID.randomUUID().toString();
		//날짜별로 폴더 생성
		String filepath = makeFolder();
		//세이브할 경로
		String savepath = uploadPath+"/"+filepath+"/"+uuid+"_"+filename;
		//데이터베이스 추후에 저장
		//System.out.println(originName);
		//System.out.println("사이즈 : "+size);
		System.out.println("uuid : "+uuid);
		System.out.println("실제 파일명 : "+filename);
		System.out.println("??"+uploadPath);
		System.out.println("날짜폴더경로 : "+filepath);
		System.out.println("세이브 할 경로 : " + savepath);
		System.out.println("-------------------------------------------------");
		try {
			File saveFile = new File(savepath);
			file.transferTo(saveFile);
		} catch (Exception e) {
			System.out.println("파일업로드 중 error 발생");
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	
	//이미지 띄워주기
	@GetMapping("/display")
	public @ResponseBody byte[] display(@RequestParam("filename") String filename,
										@RequestParam("filepath") String filepath,
										@RequestParam("uuid") String uuid) {
		
		byte[] data=null;
		//읽어올 경로
		String path = uploadPath+"/"+filepath+"/"+uuid+"_"+filename;
		try {
			data = FileCopyUtils.copyToByteArray(new File(path));///이미지 경로를 바이트배열로 구함
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}













