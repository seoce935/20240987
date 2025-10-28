package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller // 컨트롤러 어노테이션 명시
Public class DemoController {
@GetMapping(＂/hello＂) // 전송 방식 GET
public String hello(Model model) {
model.addAttribute("data", " 방갑습니다."); // model 설정
return "hello"; // hello.html 연결
}
}

import com.example.demo.model.service.Testservice; // 최상단 서비스 클래스 연동 추가
// 클래스 하단 작성
@Autowired
Testservice testService; // DemoController 클래스 아래 객체 생성
// 하단에 맵핑 이어서 추가
@GetMapping("/testdb")
public String getAllTestDBs(Model model) {
TestDB test = testService.findByName("홍길동");
model.addAttribute("data4", test);
System.out.println("데이터 출력 디버그 : " + test);
return "testdb";
@GetMapping("/article_list")
public String article_list() {
return "article_list";
}