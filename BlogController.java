@GetMapping("/article_list") // 게시판 링크 지정
public String article_list(Model model) {
List<Article> list = blogService.findAll(); // 게시판 리스트
model.addAttribute("articles", list); // 모델에 추가
return "article_list"; // .HTML 연결
}
package com.example.demo.controller;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.domain.Article;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController // @Controller + @ResponseBody
public class BlogRestController {
private final BlogService blogService;
@PostMapping("/api/articles") // post 요청
public ResponseEntity<Article> addArticle(@ModelAttribute AddArticleRequest request) { // 아직 없음(에러)
Article saveArticle = blogService.save(request); // 게시글 저장
return ResponseEntity.status(HttpStatus.CREATED) // 상태 코드 및 게시글 정보 반환
.body(saveArticle);
}
}
@GetMapping("/favicon.ico")
public void favicon() {
// 아무 작업도 하지 않음
}

@DeleteMapping("/api/article_delete/{id}")
public String deleteArticle(@PathVariable Long id) {
blogService.delete(id);
return "redirect:/article_list";
}

