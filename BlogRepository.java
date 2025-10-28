package com.example.demo.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.Article;
@Repository
public interface BlogRepository extends JpaRepository<Article, Long>{
}

package com.example.demo.model.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Article;
import com.example.demo.model.repository.BlogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자 자동 생성(부분)
public class BlogService{
    @Autowired // 객체 주입 자동화, 생성자 1개면 생략 가능
    private final BlogRepository blogRepository; // 리포지토리 선언
public List<Article> findAll() { // 게시판 전체 목록 조회
return blogRepository.findAll();
}
}
@GetMapping("/article_edit/{id}") // 게시판 링크 지정
public String article_edit(Model model, @PathVariable Long id) {
Optional<Article> list = blogService.findById(id); // 선택한 게시판 글
if (list.isPresent()) {
model.addAttribute("article", list.get()); // 존재하면 Article 객체를 모델에 추가
} else {
// 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
return "error"; // 오류 처리 페이지로 연결
}
return "article_edit"; // .HTML 연결
}

public Optional<Article> findById(Long id) { // 게시판 특정 글 조회
    return blogRepository.findById(id);
    }
    public void update(Long id, AddArticleRequest request) {
    Optional<Article> optionalArticle = blogRepository.findById(id); // 단일 글 조회
    optionalArticle.ifPresent(article -> { // 값이 있으면
    article.update(request.getTitle(), request.getContent()); // 값을 수정
    blogRepository.save(article); // Article 객체에 저장
    });
    }

@PutMapping("/api/article_edit/{id}")
public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
blogService.update(id, request);
return "redirect:/article_list"; // 글 수정 이후 .html 연결
}