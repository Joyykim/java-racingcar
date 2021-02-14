package racingcar.view;

import java.util.Scanner;

public class ConsoleInputView {

    public static final String ASK_CAR_NAMES =
            "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).은 쉼표(,)를 기준으로 구분).";
    public static final String ASK_TURNS = "시도할 회수는 몇회인가요?";

    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleInputView() {
    }

    public static String getInputCarsName() {
        printAskCarNames();
        return scanner.nextLine().trim();
    }

    public static int getInputTurns() {
        printAskTurns();
        return scanner.nextInt();
    }

    public static void printAskCarNames() {
        System.out.println(ASK_CAR_NAMES);
    }

    public static void printAskTurns() {
        System.out.println(ASK_TURNS);
    }


}
