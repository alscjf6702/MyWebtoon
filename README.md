# MyWebtoon (웹툰 플랫폼 프로젝트)

## 📌 프로젝트 소개
- 사용자가 직접 웹툰을 업로드하고, 웹툰 굿즈를 판매할 수 있는 Spring Boot 기반 웹 플랫폼입니다.
- 로그인, 회원가입, 게시판, 굿즈 판매 기능, 포트원(PG) 결제 연동을 구현했습니다.

## 🛠 사용 기술
- Backend: Java, Spring Boot, Spring Security, JPA, MySQL
- Frontend: Thymeleaf, HTML5, CSS3, Bootstrap
- DevOps: AWS EC2, S3

## 🧩 주요 기능
- 회원가입 및 로그인 (Spring Security 적용)
- 웹툰 등록, 수정, 삭제
- 굿즈 상품 등록 및 관리
- 장바구니 및 결제 기능
- 관리자 페이지

## 🗂 프로젝트 구조
com.mywebtoon ├── config ├── controller ├── domain ├── repository ├── service └── templates / static

## 📈 개발 후기
- Spring Security를 통한 사용자 인증/인가를 처음 적용하며 보안에 대한 이해도를 높였습니다.
- AWS 환경에서 서버 배포를 경험하며 클라우드 인프라 운영에 대한 기본기를 다졌습니다.


<details>
  <summary>
    📝 회원가입
  </summary>
   
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/3c3b7841-96f2-40ce-bc90-16308e1737f9)
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/b8129c41-5a20-4da7-87d9-47be5ace8650)
</details>

<details>
  <summary>
    📝 회원정보
  </summary>
   
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/00748192-6ecc-4e34-9c1a-833cb94edd59)
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/93a95ba2-08b1-49b8-b2e0-986837c6dc19)
</details>


<details>
  <summary>
    📝 로그인 (로그인,로그아웃 기능(로그아웃 시 세션 삭제))
  </summary>
   
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/2bb0c1e1-1456-47ee-9102-1db074ca202b)
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/7571efff-16e4-4ce9-a45e-3c2bfd9db2da)
</details> 



<details>
  <summary>
    📝 비밀번호 재설정( 가입시 입력한 email과 전화번호를 입력하여 비밀번호 재설정)
  </summary>
   
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/330e1f09-2805-4860-a0d9-28a879d38402)
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/cdfd91c6-6a6e-4ee9-a801-e9490e1c615e)
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/caa099e1-6e68-4022-bf77-334ac3e01c44)
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/8181fa77-cfc6-43d4-8bbe-a0327fa506a8)
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/86671373-2cd2-45b7-aeec-9cda441718c3)
   ![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/8b934203-67b1-4e74-b4ab-f22941b7a98a)
</details>


<details>
  <summary>
    📝 버튼 별 권한수정, 일반회원 정보수정
  </summary>

![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/37b65402-1f1a-44c9-8605-1c26c704f2e1)
![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/8915076b-eece-47c9-9bd1-3a08dae13b35)
![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/e4b5cb3e-5c90-482d-b145-680e66523e4e)
</details>



<details>
  <summary>
    📝 웹툰 게시판 목록( 그리드형 게시판, 웹툰 업로드, 웹툰 파일 없는 경우 X이미지 출력 )
  </summary>

![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/7335e289-44db-41bc-8bb3-c8ba4fdd2152)
</details>


<details>
  <summary>
    📝 웹툰 보기 ( 파일형태로 올려서 볼 수 있음 ) 
  </summary>

![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/8b737018-675f-419f-bfe7-c9873dfd0362)
</details>



<details>
  <summary>
    📝 굿즈 판매 게시판 (굿즈 판매 등록, javascript통해 사진 미리보기)
  </summary>
   
![image](https://github.com/alscjf6702/MyWebtoon/assets/143998544/9cbf75c9-c916-4e8e-812a-fc1711985880)

</details>



<details>
  <summary>
    📝 굿즈 판매 게시글 (굿즈 상품 판매글 detail)
  </summary>

![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/327316c8-566a-485a-b906-fb2940ea72a2)
</details>


<details>
  <summary>
    📝 굿즈 판매 게시글 리스트 
  </summary>

![image](https://github.com/alscjf6702/MyWebtoonProject/assets/143998544/b265bfc6-1a57-4cbd-bc7b-39b94b45c894)
</details>


<details>
  <summary>
    📝 굿즈 결제 (다날portOne API 사용)
  </summary>

![image](https://github.com/alscjf6702/MyWebtoon/assets/143998544/57b5c786-fa32-488d-a415-2764b30f1037)
![image](https://github.com/alscjf6702/MyWebtoon/assets/143998544/10ded425-ee5b-4ade-90e9-7f6b202b6694)
![image](https://github.com/alscjf6702/MyWebtoon/assets/143998544/673fff5b-5229-406c-8cf3-4dfdb7b7f519)
![image](https://github.com/alscjf6702/MyWebtoon/assets/143998544/06851972-1c29-45a0-b8b7-7e2da20de650)
![image](https://github.com/alscjf6702/MyWebtoon/assets/143998544/4525ca70-9156-42b3-9650-21753d4ec162)
![image](https://github.com/alscjf6702/MyWebtoon/assets/143998544/d2f1acf5-0d69-4ff4-a8c7-564ba67250b1)
</details>






