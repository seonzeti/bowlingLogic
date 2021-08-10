package exam01;

import java.util.ArrayList;
import java.util.Scanner;

public class Looptest {
	public static void main(String[] args) {
		
		// Test 01. Enter를 칠 때 까지 계속해서 숫자를 입력받는 looptest를 작성하시오.

		Boolean isOK = true;
		// while문에 들어갈 Boolean

		Scanner sc = new Scanner(System.in);
		// 값을 입력받기 위한 스캐너 선언

		int sum = 0;
		String EmptyNum;
		String num = null;
		String confirm = "";
		boolean check = true;
		ArrayList<Integer> list = new ArrayList<>();
		// sum = 값의 합계, num = 받는 값, confirm = yes or no
		// check = num이 숫자인지 아닌지 판별하는 boolean 값
		// list = num의 값 넣기 위한 Arraylist

		int listnum = 0;

		// 전체 while문 시작 (현재 isOK의 값은 true)
		while (isOK) {
			System.out.print("숫자를 입력해주세요. (단, Enter입력 시 계산이 종료됩니다.) >> ");
			// Enter를 입력시 종료되어야하기 때문에 공백이 있는 문자열을 리턴받을 수 있는 nextLine() 메소드로 값을 받는다.
			num = sc.nextLine();

			// 공백을 제거해주는 메소드
			EmptyNum = Empty(num);

			// 받은 값이 숫자인지 아닌지 판별하기 위한 메소드
			check = isNumber(num);

			if (check == true) {
				try {
					listnum = Integer.parseInt(EmptyNum);
					list.add(listnum);
					System.out.println("현재 입력하신 숫자는 " + listnum + "입니다.");
					sum += listnum;

				} catch (Exception e) {
					System.out.println("잘못 입력 하셨습니다.");

				}

			} else {
				if (num.equals("")) {
					while (true) {
						System.out.print("종료하시겠습니까? [y/n] >> ");
						confirm = sc.nextLine();
						if (confirm.equals("y") || confirm.equals("Y")) {
							isOK = false;
							// list에 쌓인 값이 없을 때 종료를 했다면, 숫자가 없다고 알려준다.
							if (list.size() < 1) {
								System.out.println("입력한 숫자가 없습니다.");
								break;
							} else {
								// list에 쌓인 값이 있다면 연산을 해준다.
								System.out.println("-------------------------");

								// 뒤에 "+"가 붙어있는 반복문을 사용할 것이므로 맨마지막 숫자는 제외하고 출력해준다.
								for (int i = 0; i < list.size() - 1; i++) {
									System.out.print(list.get(i));
									System.out.print(" + ");
								}

								// 맨마지막 숫자를 입력하고 부등호와 결과를 출력해준다.
								System.out.println(list.get(list.size() - 1) + " = " + sum);

								// 사용자가 보기 편하도록 다시한번 안내해준다.
								System.out.println("총 합계는 " + sum + "입니다.");
								System.out.println("-------------------------");

								// Yes or No를 묻는 while문 또한 종료해준다.
								break;
							}
						} else if (confirm.equals("n") || confirm.equals("N")) {
							System.out.println("다시 시작합니다.");
							isOK = true;
							break;
						} else {
							System.out.println("잘못입력하셨습니다.");
						}
					}
				} else {
					System.out.println("숫자가 아닙니다. 다시 입력해주세요.");
				}

			}

		}

	}

	// 공백을 제거해주는 메소드, reaplce를 이용해서 " "를 ""로 수정해준다.
	static String Empty(String num) {
		String emptyStr = num.replace(" ", "");
		return emptyStr;
	}

	// num이 넘버인지 아닌지 확인할 수 있는 메소드
	static boolean isNumber(String num) {

		char tmp = 0;

		for (int i = 0; i < num.length(); i++) {
			tmp = num.charAt(i);
		}

		//숫자를 판단하는 내장함수
		if (Character.isDigit(tmp) == false) {
			return false;
		} else {
			return true;
		}
	}
}
