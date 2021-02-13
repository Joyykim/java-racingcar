# java-racingcar

자동차 경주 게임 미션 저장소

## 우아한테크코스 코드리뷰

* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 🚀 1단계 - 자동차 경주 구현

## 개요

- 정해진 턴 동안 n대의 자동차는 전진 또는 멈출 수 있다.
- 차들의 이름과 개수, 몇 번의 턴을 진행할지는 사용자가 입력하여 결정한다.
- 차는 Random 값에 따라 해당 턴의 전진/정지 여부가 결정된다.
- 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.

## 기능 요구사항

### 경주

- [x] 주어진 턴 수 동안 차들은 전진/정지할 수 있다.
- [x] 전진하는 조건은 0에서 9 사이에서 random 값을 구한 후 random 값이 4 이상일 경우 전진하고, 3 이하의 값이면 멈춘다.
- [x] 자동차는 한 턴 당 전진/정지 중 하나의 동작만 한다.
    - 전진한 차는 한 칸 앞으로 이동한다.
    - 정지한 차는 이동하지 않고 그대로 있는다.
- [x] 전진하는 자동차를 출력할 때 자동차 이름과 현재까지 나아간 거리를 같이 출력한다.

### 사용자 입력

- [x] 경주할 자동차들의 이름을 입력한다.
    - [x] 쉼표(,) 기준으로 구분한다.
    - [x] 1자 이상 5자 이하만 가능하다.
    - [x] 이름 앞 뒤의 공백이 있다면 제거된다. `ex) "car " or " car "`
- [x] 진행할 턴 수를 입력한다.
    - [x] 턴 수는 1 이상의 자연수만 가능하다.
- **예외 상황 검증**
    - [x] 이름이 빈 문자열이거나, 공백만 존재 - Car책임 `ex) "" or " "`
    - [x] 이름에 형식에 맞지 않은 `","` 존재 `ex) "joy,,hey" or ",,joy"`
    - [x] 차들의 이름에 중복 존재 `ex) "joy,joy"`
    - [x] 턴 수 입력 시 문자열, 실수, 0 이하의 정수

### 게임 진행

- [x] 전진하는 조건은 0에서 9 사이에서 random 값을 구한 후 random 값이 4 이상일 경우 전진하고, 3 이하의 값이면 멈춘다.
- [x] 전진하는 자동차를 출력할 때 자동차 이름과 현재까지 나아간 거리를 같이 출력한다.
- [x] 한 턴이 끝날 때 마다 공백 라인을 출력해 턴을 구분한다.

### 게임 결과

- [x] 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.

**실행 결과**

- 위 요구사항에 따라 3대의 자동차가 5번 움직였을 경우 프로그램을 실행한 결과는 다음과 같다.

```
경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).
pobi,crong,honux
시도할 회수는 몇회인가요?
5

실행 결과
pobi : -
crong : -
honux : -

pobi : --
crong : -
honux : --

pobi : ---
crong : --
honux : ---

pobi : ----
crong : ---
honux : ----

pobi : -----
crong : ----
honux : -----

pobi : -----
crong : ----
honux : -----

pobi, honux가 최종 우승했습니다.
```

# 🚀 2단계 - 자동차 경주 리팩터링

## 리팩터링 요구사항

- 핵심 비지니스 로직을 가지는 객체를 domain 패키지, UI 관련한 객체를 view 패키지에 구현한다.
- MVC 패턴 기반으로 리팩토링해 view 패키지의 객체가 domain 패키지 객체에 의존할 수 있지만, domain 패키지의 객체는 view 패키지 객체에 의존하지 않도록
  구현한다.
  ![](https://firebasestorage.googleapis.com/v0/b/nextstep-real.appspot.com/o/lesson-attachments%2F-L9D5L4Xj_6xjTfOTRks%2Fdomain.PNG?alt=media&token=5083069f-e922-4f0e-95a4-d3fb9fb95224)

- 테스트 가능한 부분과 테스트하기 힘든 부분을 분리해 테스트 가능한 부분에 대해서만 단위 테스트를 진행한다.
  ![](https://firebasestorage.googleapis.com/v0/b/nextstep-real.appspot.com/o/lesson-attachments%2F-L9D5L4Xj_6xjTfOTRks%2Fdomain2.PNG?alt=media&token=f8d287eb-e747-410c-979c-369eb3b26826)

## MVC 패턴으로 리팩터링 후의 main 메소드 예시

```java
import view.InputView;
import view.ResultView;
import domain.RacingGame;

public class RacingMain {

    public static void main(String[] args) {
        String carNames = InputView.getCarNames();
        int tryNo = InputView.getTryNo();

        RacingGame racingGame = new RacingGame(carNames, tryNo);
        while (!racingGame.isEnd()) {
            racingGame.race();
            ResultView.printCars(racingGame.getCars());
        }
        ResultView.printWinners(racingGame.getWinners());
    }
}
```

## 프로그래밍 요구사항

- 모든 로직에 단위 테스트를 구현한다. 단, UI(System.out, System.in) 로직은 제외
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 참고문서: https://google.github.io/styleguide/javaguide.html 또는 https://myeonguni.tistory.com/1596
- 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기(indent)만 한다.를 지키며 구현한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메소드)를 분리하면 된다.
- 규칙 2: else 예약어를 쓰지 않는다.를 지키며 구현한다.
    - 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
    - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
- 함수(또는 메소드)의 길이가 10라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.

## 기능 목록 및 commit 로그 요구사항

- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.
