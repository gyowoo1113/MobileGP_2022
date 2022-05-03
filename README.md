----------------------------------
## 게임 소개


    1. 게임 '컵헤드(Cuphead)'의 모작
    2. 횡스크롤 액션 슈팅 게임
    
![screensh](/TermProject/Resource/boss.png)
    
----------------------------------

## 진행 상황
|주차|구현항목|세부내용|진행도|
|:------:|:---:|:---:|:---:|
|1주차|리소스|배경·오브젝트·사운드·리소스 수집·편집|100%|
|2주차|화면,플레이어|횡스크롤 화면 구현, 플레이어 이동 구현|100%|
|3주차|플레이어|기본공격(1·2),특수공격, 공격 전환버튼|80%|
|4주차|몬스터,충돌체크|일반·특수 몬스터 구현, 오브젝트 충돌체크|90%|
|5주차|보스|보스 2가지 패턴 구현|0%|

----------------------------------

## Git Commit

![screensh](/TermProject/Resource/git01.png)
![screensh](/TermProject/Resource/git02.png)

-----------------------------------

## 목표 변경(일정 변경)

|주차|구현항목|세부내용|
|:------:|:---:|:---:|
|5주차|중간점검|1~4주차 미구현 항목 보완|
|6주차|보스|보스 2가지 패턴 구현|
|7주차|화면전환|시작·메인·종료 화면 전환, (추가구현)종료 시 랭크 띄우기|


#### 변경사유

    미구현 상태인 플레이어 특수공격·몬스터 HP 구현 후 보스구현 진행할 예정
    -> 7주차 일정이었던 중간 점검을 5주차로 변경
        

---------------------------------------

## MainGame에 등장하는 GameObject 설명

#### 전체 구성도

![screensh](/TermProject/Resource/flow_.png)

#### class 

* Bullet
    * 구성    
        * ![screensh](/TermProject/Resource/bullet.png)
        
    * 상호작용
        1. 플레이어 -> Bullet 발사
        2. 몬스터 -> 공격, 충돌검사

* Cuphead(Player)
    * 구성 (동작 구성)
        1. 터치다운 => Box/Point 충돌검사 성공
            * 이동범위(RangeBox) 갱신
            * 플레이어 좌표 갱신·총알 발사 활성화
        2. 화면 드래그
            * 제한된 범위 내에서 이동
        3. 터치업
            * 이동범위(RangeBox) 갱신
            * 플레이어 좌표 갱신·총알 발사 비활성화
    * 상호작용
        1. 화면터치 -> 이동처리, 총알발사
        
* Enemy
    * 구성
        - level에 따라 충돌처리를 다르게 함
            * level 1 : 총알과 충돌처리
            * level 2 : 화면 터치로 충돌처리
                * 터치다운 상태에서만 충돌검사 진행
    * 상호작용
        1. 총알 -> 충돌검사
        2. 화면터치 -> 충돌검사    
        
        
#### 핵심코드

* 공격 유형(isBomb)에 따라 BombBullet/NormalBullet class를 호출하여 bullet을 생성한다. 

        * Cuphead의 fire() 일부
            Bullet bullet = (isBomb) ? BombBullet.get(x, y) : NormalBullet.get(x, y + val);
            MainGame.getInstance().add(MainGame.Layer.bullet, bullet);
        
        
gravity로 dy값을 점점 감소시킨다. 
dy는 양수값으로 시작하기 때문에, 화면에서 총알은 위로 상승하다가 서서히 추락한다.
    => 포물선 궤도를 표현
        
        public void update() {
            float frameTime = MainGame.getInstance().frameTime;
            x -= dx * frameTime;
            y -= dy * frameTime;
            dy -=gravity*frameTime;    
    
    
터치다운 시, MainGame의 tx,ty를 갱신하고 isTouch를 true로 바꾼다.
CollisionChecker는 isTouch가 true일 경우 tx,ty 값을 받아와 특정 몬스터와 충돌 검사를 한다.
        
        * CollisionChecker의 update() 일부
            if (!MainGame.getInstance().isTouch) return;
            float x = MainGame.getInstance().tx;
            float y = MainGame.getInstance().ty;

            boolean collided = false;
            if (CollisionHelper.isPointInBox(enemy,x,y)) {
                game.remove(enemy);
                collided = true;
                break;
            }