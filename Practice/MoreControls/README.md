
## GameView Specifications


* padding (l/t/r/b) 을 적용한다
* View 에서 padding 을 제외하고 폭과 높이 중 작은 값을 size 라 한다
* Content 영역을 배경으로 칭한다.
* 배경 색은 검정이다.
* 배경은 10dp의 파란색 테두리가 있다.
* 화면 정 중앙을 기준으로 좌표평면을 가정한다
    * 제3사분면의 정 중앙에 pacman을 그린다
        * pacman은 +x 축을 기준으로 +- 45'씩 입을 벌리고 있는 원이다.
        * pacman의 색상은 노란색, 크기는 size의 1/8이다.
        
  
* '임의의 size'에 대하여 원을 그린다
    * pacman과 동일한 centerY 값을 가진다
    * (pacman의 centerX - 원의 반지름)값을 시작으로, 오른쪽으로 size/4 간격으로 그린다.
    * '임의의 width값'만큼 규칙적으로 그린다
    * 색상은 RGB(225,113,113)이다.
    * 원의 크기는 size의 1/20이다.
    

* pacman의 centerY를 기준으로 사각형을 그린다.
    * 사각형을 통해 영역을 3개로 분할한다.
    * 세로폭의 크기는 size/5 이다.
    
* 화면분할한 영역중 맨위의 영역 중앙에 "GAME START"라고 적는다.
    * 색상은 흰색이다.
    * 크기는 size의 1/20이다.
    * 두께는 10dp이다.
 
 
