---------
## 프로젝트 제목

    1. 제목: mini Cuphead
    2. 원본게임 제목: Cuphead
    3. 원본게임 설명: 여러 런앤건·보스 스테이지를 클리어하는 게임
                  보스 스테이지는 일반/비행기 2종류가 존재

## 게임 컨셉

    1. 게임 '컵헤드(Cuphead)'의 모작
    2. '우당탕탕 새장' 스테이지(비행기) 1페이즈를 구현
        → 장르: 횡스크롤 액션 슈팅
    3. 스테이지 보스를 쓰러트리는 게임
    
## 개발 범위
![screensh](/TermProject/Resource/develop.png)
---

##### 플레이어 컨트롤

    1. 캐릭터를 터치다운, 드래그로 조작
    2. 이동범위엔 제한이 있다.
        - 터치다운한 지점은 중점이다.
        - 반지름이 radius(임시)인 원 내부에서만 이동가능
        - 범위 밖으로 드래그하면 캐릭터는 원(범위)을 벗어나지 않는다.
        
##### 몬스터

    1. 일반 몬스터: 플레이어 공격으로 사망
    2. 특수 몬스터: 화면 터치다운으로 사망
        - 일정 횟수이상 터치 시 사망
        - 터치 시 몬스터 색상의 변화로 체력을 표현
    
   
##### 화면구성
    
    시작화면 - Android Activity
    메인화면 - Custom View
    종료화면 - 개발 상황에 따라 변경 예정
                Custom View: 클리어 등급 개발
                Android Activity: 클리어 등급 미개발
---------

## 예상 게임 실행 흐름
![screensh](/TermProject/Resource/flow.png)

![screensh](/TermProject/Resource/control.png)

----------
## 개발 일정
![screensh](/TermProject/Resource/chart1.png)
