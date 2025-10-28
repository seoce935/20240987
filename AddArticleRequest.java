package com.example.demo.model.service;
import lombok.*; // 어노테이션 자동 생성
import com.example.demo.model.domain.Article;
@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Data // getter, setter, toString, equals 등 자동 생성
public class AddArticleRequest {
데이터만! 불필요한 데이터 노출을 방지
private String title;
private String content;
public Article toEntity(){ // Article 객체 생성
return Article.builder()
.title(title)
.content(content)
.build();
}
}
public Article save(AddArticleRequest request){
    // DTO가 없는 경우 이곳에 직접 구현 가능
    // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
    // Article article = Article.builder()
    // .title(title)
    // .content(content)
    // .build();
    return blogRepository.save(request.toEntity());
    }