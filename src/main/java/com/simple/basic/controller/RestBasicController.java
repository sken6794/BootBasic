package com.simple.basic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.simple.basic.command.SimpleVO;

@RestController //@ResponseBody + @Controller
public class RestBasicController {
	
	@GetMapping("/basic")
	public String basic() {
		return "<h3>hello world</h3>";
	}
	
	//데이터를 보내는 방법 -> @ResponseBody = 자바의 객체를 JSON 형식으로 자동 변환
	@GetMapping("/getObject")
	public SimpleVO getObject() {
		
		SimpleVO vo = new SimpleVO(1, "홍", "길동", LocalDateTime.now());
		
		return vo; 
	}
	
	
	@GetMapping("/getMap")
	public Map<String, Object> getMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("name", "홍길동");
		return map;
	}
	
	@GetMapping("/getList")
	public List<SimpleVO> getList(){
		List<SimpleVO> list = new ArrayList<>();
		list.add(new SimpleVO(1, "홍", "길동", LocalDateTime.now()));
		list.add(new SimpleVO(2, "박", "길동", LocalDateTime.now()));
		list.add(new SimpleVO(3, "김", "길동", LocalDateTime.now()));
		list.add(new SimpleVO(4, "나", "길동", LocalDateTime.now()));
		list.add(new SimpleVO(5, "이", "길동", LocalDateTime.now()));
		return list;
	}
	
	//데이터를 받는 방법
	//get => 쿼리스트링  or 쿼리파라미터 이용해서 주소에 담아서 넘김
	//post => 폼 형식 or json을 이용해서 body에 담아서 넘김
	
	@GetMapping("/getData")
	public SimpleVO getData(SimpleVO vo) {
		
		System.out.println(vo.toString());
		
		return new SimpleVO(4, "나", "길동", LocalDateTime.now());
	}
	
	//sno, first는 필수값이 되어버린다. 
	@GetMapping("/getData2")
	public SimpleVO getData2(@RequestParam("sno") int sno, @RequestParam("first") String first) {
		System.out.println(sno + " : "+ first);
		
		
		return new SimpleVO(4, "나", "길동", LocalDateTime.now());
	}
	
	@GetMapping("/getData3/{sno}/{first}")
	public SimpleVO getData3(@PathVariable("sno") int sno, @PathVariable("first") String first) {
		
		System.out.println(sno + " : " + first);
		
		return new SimpleVO(4, "나", "길동", LocalDateTime.now());
	}
	
	////////////////////////////////////////////////////////////////
	//post방식의 데이터 받기
	
	@PostMapping("/getForm")
	public SimpleVO getForm(SimpleVO vo) {
			//System.out.println(vo.toString());
		return new SimpleVO(4, "나", "길동", LocalDateTime.now());
	}
	
	
	//보내는입장에서는 JSON형식의 데이터를 써줘야함
	// @RequestBody => JSON형식으로 넘어온 데이터를 자동으로 매핑 해서 객체로 변환해줌
	@PostMapping("/getJSON")
	public SimpleVO getJSON(@RequestBody SimpleVO vo) {
		System.out.println(vo.toString());
		
		
		
		return vo;
	}
	
	
	@PostMapping("/getJSON2")
	public SimpleVO getJSON2(@RequestBody Map<String, Object> map) {
		System.out.println(map.toString());
		return new SimpleVO(4, "나", "길동", LocalDateTime.now());
	}
	//////////////////////////////////////////////////////////////////////////////
	// consumer = 반드시 이 타입으로 보내. 명시해주는 거
	// producer = 내가 이 타입으로 줄게. 명시하는거 (기본값 = json), xml을 사용하려면 xml 데이터 바인딩 라이브러리 필요\
	/*
	@PostMapping(value = "/getResult", produces = "text/plain", consumes = "text/plain")
	public String getResult(@RequestBody String vo) {
		
		System.out.println(vo);
		
		return vo.toString();
	}
	*/
	
	//보내는 타입은 text로 너는 json으로 보내라 ?
	@PostMapping(value = "/getResult", produces = "text/plain", consumes = "application/json")
	public String getResult(@RequestBody String vo) {
		
		System.out.println(vo);
		
		return vo.toString();
	}
	
	//응답문서 직접작성하기
	//ResponseEntity<보낼 데이터 타입>
	@PostMapping("/createResponse")
	public ResponseEntity<SimpleVO> createResponse(){
		
		SimpleVO vo = new SimpleVO(4, "나", "길동", LocalDateTime.now());
		
		//1st
		//ResponseEntity<SimpleVO> ent = new ResponseEntity<>(vo,HttpStatus.OK);
		
		//2nd
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "JSON WEB TOKEN~");
		header.add("Content-Type", "application/json");
		header.add("Access-Control-Allow-Origin", "*");
		
		
		ResponseEntity<SimpleVO> ent = new ResponseEntity<>(vo,header,HttpStatus.OK); //데이터, 헤더, 상태코드
		return ent;
	}
	
	///////////////////////////////////////////
	/*
	 * 클라이언트 fetch
	 * 요청 주소 : /api/v1/getData
	 * 메소드 : get
	 * 클라이언트에서 보낼 데이터는 num, name
	 * 서버에서 보낼 데이터는 ??
	 * 받을 수 있는 restAPI 를 생성
	 * 
	 */
	
	/*
	 * 클라이언트 fetch
	 * 요청 주소 : /api/v1/getInfo
	 * 메소드 : post
	 * 보낼 데이터는 num, name
	 * 받을 수 있는 restAPI 를 생성
	 * 
	 */
	@CrossOrigin("*")
	@GetMapping("/api/v1/getData")
	public ResponseEntity<SimpleVO> ex01(@RequestParam("num") int num,@RequestParam("name") String name ) {
		
		SimpleVO vo = new SimpleVO(num, "나", name, LocalDateTime.now());
		System.out.println(num+ " : "+ name);
		
		
		ResponseEntity<SimpleVO> ent = new ResponseEntity<>(vo,HttpStatus.OK); //데이터, 헤더, 상태코드
		
		return ent;
	}
	
	//consumes = "application/json" => 요청보내는 곳이 json이 아니면 요청을 거절하도록 설정할 수 있음
	//@CrossOrigin("http://localhost:3000","~~~~" , 로 나열 가능)
	//@CrossOrigin("*") -> 모든 서버에 대한 요청을 허용
	@CrossOrigin("*")
	@PostMapping(value = "/api/v1/getInfo", consumes = "application/json")
	public ResponseEntity<List<SimpleVO>> ex02(@RequestBody Map<String, Object> map) {
		
		System.out.println(map.toString());
		List<SimpleVO> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			SimpleVO vo = new SimpleVO(i, "나", "길동", LocalDateTime.now());
			list.add(vo);
		}
		
		
		
		ResponseEntity<List<SimpleVO>> ent = new ResponseEntity<>(list,HttpStatus.OK); //데이터, 헤더, 상태코드
		
		return ent;
	}
	
	
	
	
}









