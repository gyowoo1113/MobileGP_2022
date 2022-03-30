
## GameView Specifications


* padding (l/t/r/b) 을 적용한다
* View 에서 padding 을 제외하고 폭과 높이 중 작은 값을 size 라 한다
* 배경 색은 검정이다.
* 화면 정 중앙을 기준으로 좌표평면을 가정한다
    * 제3사분면의 정 중앙에 pacman을 그린다
* '임의의 size'에 대하여 원을 8개 그린다
    * pacman과 동일한 y값을 가진다
    * pacman과 겹칠 수 있다
    * '임의의 width값'만큼 규칙적으로 그린다
    * 색상은 RGB(225,113,113)이다.
    * 원의 크기는 size의 1/16이다.
* 화면 정 중앙에 "GAME START"라고 적는다.
    * 색상은 흰색이다.
    * 크기는 size의 1/20이다.
    * 두께는 10dp이다.

