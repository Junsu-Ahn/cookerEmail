package com.example.cookers;

import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CookersApplicationTests {

	@Autowired
	RecipeService recipeService;

	@Autowired
	MemberService memberService;

	@Test
	void 카테고리별로_레시피출력() {
		for (int i =1; i<= 1; i++){ // 1
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i);
			String categoryValue = String.format("밥요리");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 2; i++){ // 2 ~ 3
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+1);
			String categoryValue = String.format("국 & 찌개");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 3; i++){ // 4 ~ 6
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+3);
			String categoryValue = String.format("면요리");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 4; i++){ // 7 ~ 10
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+6);
			String categoryValue = String.format("튀김요리");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 5; i++){ // 11 ~ 16
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+10);
			String categoryValue = String.format("볶음요리");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 6; i++){ // 17 ~ 23
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+16);
			String categoryValue = String.format("찜 & 조림");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 7; i++){ // 23 ~ 30
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+22);
			String categoryValue = String.format("샐러드");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 8; i++){ // 31 ~ 39
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+30);
			String categoryValue = String.format("밑반찬");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 9; i++){ // 40 ~ 49
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+39);
			String categoryValue = String.format("간식 & 분식");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 10; i++){ // 50 ~ 60
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+49);
			String categoryValue = String.format("디저트");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 11; i++){ // 61 ~ 72
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+60);
			String categoryValue = String.format("명절음식");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}
		for (int i =1; i<= 12; i++){ // 73 ~
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i+72);
			String categoryValue = String.format("기타요리");
			recipeService.create(title, subject, content, nickname, categoryValue);
		}

	}

	@Test
	void 멤버_생성() {
		for (int i = 1; i <= 50; i++) {
			String username = String.format("user%d", i);
			String password = String.format("user%d", i); // 테스트용 비밀번호
			String passwordConfirm = String.format("user%d", i); // 비밀번호 확인
			String nickname = String.format("nickname%d", i);
			String email = String.format("user%d@example.com", i);
			String typeCode = ""; // 예시 타입 코드
			String url = "";
			Long hit = 0L;
			memberService.signup("", username, password, passwordConfirm, nickname, email, hit, url);
			// public Member signup(String providerTypeCode, String username, String password, String passwordConfirm, String nickname, String email, Long hit, String url){
		}
	}

//	@Test
//	void 카테고리_페이징() {
//		for (int i =1; i<= 12; i++){
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i);
//			recipeService.create(title, subject, content, nickname);
//		}
//	}
}
