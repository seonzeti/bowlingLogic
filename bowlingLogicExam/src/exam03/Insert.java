package exam03;

import java.util.Scanner;

public class Insert {
	public static void main(String[] args) {

		while (true) {

			Game g = new Game();
			Scanner sc = new Scanner(System.in);

			int pin = 0; // 핀

			int num = 0; // n번째 입력값
			int prevPin = 0; // 전 핀

			Boolean isBonus = true;

			for (int i = 0; i < 21; i++) {
				System.out.print((num + 1) + "번째 입력 값  : ");

				// 입력받고, 문자인지 판별 후 형변환
				String strPin = sc.nextLine();
				if (isNumber(strPin)) {
					pin = Integer.parseInt(strPin);

					if (prevPin + pin < 11) {
						// 첫번째 투구에 스트라이크인 경우는 한번에 두번씩 넘어간다.
						if ((i % 2 == 0) && (pin == 10)) {
							if (g.frameNum < 10) {
								i++;
							}

						}

						// pin을 굴려 Game클래스의 scoreList에 쌓아준다.
						g.roll(pin);

						// 만약, 1프레임에 10이 아니라면 prevPin에 현재 핀을 넣어준다
						// 2프레임에서 전 핀을 판별하기 위해서

						if ((i % 2 == 0) && (g.frameNum != 10)) {
							prevPin += pin;
						} else if (g.frameNum == 10) {
							if (g.tenTurn == 1 && pin == 10) {
								prevPin = 0;
							} else if (pin + prevPin == 10) {
								// 10-1, 10-2가 스페어가 됐다면?
								prevPin = 0;
							} else {
								prevPin += pin;
							}
						} else {
							// 2프레임이거나, 1프레임에서 10이 나왔다면 prevPin 에 0을 넣어준다.
							prevPin = 0;
						}

						// 내가 원하는 출력 형태
						// n번째 입력 값 : m (앞에서 이미 출력)
						// 출력 = pin [프레임, 투구, 현재 스코어]

						System.out.print("출력 = " + pin + " [" + (g.frameNum) + ",");

						// 투구는 10프레임은 10-1, 10-2, 10-3
						// 다른 프레임은 g.frameNum-1, g.frameNum -2

						if (g.frameNum == 10) {
							isBonus = g.isBonus(g.frameNum, g.tenTurn);

							if (isBonus == false) {
								// 즉, 10프레임에서 스트라이크나 스페어 처리를 하지 못한 경우
								i = 21;
							}

							System.out.print(g.tenTurn + ",");

						} else {
							System.out.print(g.turn + ",");
						}

						if (pin == 10) {
							// 현재 스트라이크
							System.out.print(g.getscore() + "+");
						} else if ((i > 1) && (g.scoreList[i - 2] == 10)) {
							// 이전 값이 스트라이크
							System.out.print(prevScore(g, pin) + "+" + pin + "+");
						} else {

							System.out.print(g.getscore());
						}

						System.out.print("]");
						System.out.println();

						g.getTurn();
						g.getFrame();

					} else {
						// 전 핀수 + 현 핀수를 더한 값이 10보다 큰 경우
						System.out.println((11 - prevPin) + "보다 더 작은수를 입력해주세요.");

						num--;
						i--;

						if (g.currentRoll == -1) {
							g.turn = 1;
						} else {
							if (g.frameNum != 10) {
								g.getTurn();

							}
						}

					}

				} else {
					// 입력한 값이 숫자가 아닌 경우
					System.out.println("잘못입력하셨습니다.");
					i--;
					num--;
				}
				num++;
			}
			System.out.println("당신의 최종 점수는 " + g.getscore() + "입니다.");
			System.out.print("게임을 계속 하시겠습니까? [y/n] ");
			String check = sc.nextLine();
			if (check.equals("y") || check.equals("Y")) {
				continue;
			} else if (check.equals("n") || check.equals("N")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 프로그램을 종료합니다.");
				break;
			}

		}

	}

	private static int prevScore(Game g, int pin) {
		return g.getscore() - (pin * 2);
	}

	private static boolean isNumber(String input) {
		boolean flag = true;
		if (!input.matches("[0-9]+")) {
			flag = false;
		}
		return flag;
	}

}
