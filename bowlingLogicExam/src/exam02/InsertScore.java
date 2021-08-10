package exam02;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class InsertScore {


	public static void main(String[] args) {
		
		// Test 02. 랜덤게임 / 퍼펙트게임 / 거터게임 / 올스페어게임 / 랜덤게임 조건에 맞는 스코어판을 작성하시오.

		// 필요한 변수 선언
		Boolean isOK = true; // 반복문
		Scanner sc = new Scanner(System.in); // 입력받을 스캐너
		Random rd = new Random(); // 랜덤값

		int firstRollinFrame = 0; // 첫 투구 값
		int SecondRollinFrame = 0; // 두번째 투구 값
		int lastRollinFrame = 0; // 10-3 보너스 투구 값
		int score = 0; // 점수

		ArrayList<Integer> scoreList = new ArrayList<>(); // 점수를 쌓을 arrayList

		while (isOK) { // 반복문 시작
			System.out.println("볼링게임에 오신걸 환영합니다.");
			System.out.println("[1]랜덤게임 [2]퍼펙트게임 [3]거터게임 [4]올스페어 [5]직접 입력");
			System.out.print("원하시는 게임세팅을 선택하세요. >> ");

			String Str_choice = sc.nextLine();
			// 공백값 처리를 위해 sc.nextLine()을 사용
			int choice = 0;

			if (isNumber(Str_choice)) {
				choice = Integer.parseInt(Str_choice);
				if (choice == 1) {
					// 랜덤게임

					for (int i = 0; i < 10; i++) {
						// 1~10 frame을 for문으로 반복

						firstRollinFrame = rd.nextInt(11);
						// 0부터 10까지의 랜덤한 수가 나와야 하니까 next(11)
						SecondRollinFrame = rd.nextInt(11 - firstRollinFrame);
						// first + second 값이 10이 넘으면 안되니까 11-first값으로 랜덤한 수를 받아온다.

						scoreList.add(firstRollinFrame);
						scoreList.add(SecondRollinFrame);
						// scoreList.add로 list에 각각 값을 쌓아준다

						// 두개 합쳐서 10인경우 (스트라이크, 스페어) 10점을 추가해준다
						// 이 이외의 보너스 점수는 밑에서 한번 더 계산하니까 점수만 더해서 넘겨준다
						if (isSpare(firstRollinFrame, SecondRollinFrame)) {
							score += 10;
						} else {
							score += firstRollinFrame + SecondRollinFrame;
						}

					}

				} else if (choice == 2) {
					// 퍼펙트게임
					// 모든 값이 10이고, 결과가 300이 나와야하는 게임

					for (int i = 0; i < 10; i++) {

						firstRollinFrame = 10;
						SecondRollinFrame = 0;

						scoreList.add(firstRollinFrame);
						scoreList.add(SecondRollinFrame);

						score += firstRollinFrame + SecondRollinFrame;

					}

				} else if (choice == 3) {
					// 거터게임
					// 모든 값이 0이고, 결과가 0이 나와야하는 게임

					for (int i = 0; i < 10; i++) {
						firstRollinFrame = 0;
						SecondRollinFrame = 0;
						scoreList.add(firstRollinFrame);
						scoreList.add(SecondRollinFrame);

						score += firstRollinFrame + SecondRollinFrame;

					}

				} else if (choice == 4) {
					// 올스페어
					// 모든값이 5고, 결과가 150이 나오는 게임

					for (int i = 0; i < 10; i++) {
						firstRollinFrame = 5;
						SecondRollinFrame = 5;
						scoreList.add(firstRollinFrame);
						scoreList.add(SecondRollinFrame);

						score += firstRollinFrame + SecondRollinFrame;

					}

				} else if (choice == 5) {
					// 직접 입력

					for (int frame = 0; frame < 10; frame++) {
						System.out.print((frame + 1) + "프레임의 첫번째 투구 >> ");

						// 공백처리
						String Str_first = sc.nextLine();
						String emptyFirst = Empty(Str_first);

						// 숫자 판별
						if (isNumber(Str_first)) {
							firstRollinFrame = Integer.parseInt(emptyFirst);
							if (firstRollinFrame > 10) {
								System.out.println("입력한 값이 10보다 큽니다.");

							} else {
								if (isStrike(firstRollinFrame)) {
									SecondRollinFrame = 0;
									score += 10;
								} else {
									System.out.print((frame + 1) + "프레임의 두번째 투구>> ");
									String Str_second = sc.nextLine();

									if (isNumber(Str_second)) {
										SecondRollinFrame = Integer.parseInt(Str_second);

									} else {
										// 두번째 입력받은 값이 숫자가 아닐 때
										System.out.println("잘못 입력하셨습니다.");
										frame--;
									}

								}

								

							}

						} else {
							// 첫번째 입력받은 값이 숫자가 아닐 때
							System.out.println("잘못입력하셨습니다.");
							frame--;

						}

						if (firstRollinFrame + SecondRollinFrame > 10) {
							System.out.println("잘못 입력하셨습니다.");
							frame--;

							if (scoreList.size() > 2) {
								//2프레임 이상인 경우
								scoreList.remove(scoreList.size() - 2);
								scoreList.remove(scoreList.size() - 1);
							} else {
								//1프레임일 경우 그냥 clear 해준다.
								scoreList.clear();
							}
							
							//점수도 원래대로
							score -= firstRollinFrame + SecondRollinFrame;

						}

								scoreList.add(firstRollinFrame);
								scoreList.add(SecondRollinFrame);
					}

				} else {
					System.out.println("잘못입력하셨습니다.");
					continue;

				}
			} else {
				System.out.println("잘못입력하셨습니다.");
			}

			// 10-3프레임 투구 계산

			try {
				if ((scoreList.get(18) + scoreList.get(19)) == 10 || scoreList.get(18) == 10) {
					System.out.print("10프레임의 보너스 투구!! ");
					if (choice == 1) {
						// 랜덤게임
						lastRollinFrame = rd.nextInt(11);
					} else if (choice == 2) {
						// 퍼펙트게임
						lastRollinFrame = 10;

					} else if (choice == 3) {
						// 거터게임

						lastRollinFrame = 0;

					} else if (choice == 4) {
						// 올스페어

						lastRollinFrame = 5;

					} else if (choice == 5) {
						// 직접 입력
						String Str_last = sc.nextLine();
						String emptyLast = Empty(Str_last);
						lastRollinFrame = Integer.parseInt(emptyLast);

						if (isNumber(Str_last) == false) {
							System.out.println("숫자로 입력해주세요");
							continue;
						}
					} else {

						System.out.println("보기에 있는 숫자를 입력해주세요.");
						continue;

					}

				}
			} catch (Exception e) {
				// 두개 exception 처리는 따로 두번 적을 것 (명시할 것) 
				// 프로젝트를 할 때 유지보수가 어렵다 
				// ex ~ | ex~ 
				continue;
			}

			scoreList.add(lastRollinFrame);
			
			
			//lastRollinFrame 스트라이크, 스페어의 보너스 점수 계산
			if (scoreList.get(18) == 10) {
				score += 20 + lastRollinFrame;
			} else {
				score += lastRollinFrame;
			}

			// 1~10프레임까지 추가로 받았던 점수 합산
			for (int i = 0; i < 18; i = i + 2) {
				if (scoreList.get(i) == 10) {
					// 스트라이크일때
					score += nextFrameTotalRollin(scoreList, i);

					//2프레임부터 
					if (i > 1) {
						if (scoreList.get(i - 2) == 10) {
							score += nextFrameFirstRollin(scoreList, i);
						}

					}

				} else if (scoreList.get(i) + scoreList.get(i + 1) == 10) {
					// 스페어일때
					score += nextFrameFirstRollin(scoreList, i);
				}
			}

			//점수 보여주기
			scoreView(score, scoreList);
			
			//반복을 위한 값 초기화
			scoreList.clear();
			score = 0;
			firstRollinFrame = 0;
			SecondRollinFrame = 0;
			lastRollinFrame = 0;

			System.out.print("게임을 계속 하시겠습니까? [y/n] ");
			String check = sc.nextLine();
			if (check.equals("y") || check.equals("Y")) {
				continue;
			} else if (check.equals("n") || check.equals("N")) {
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 프로그램을 종료합니다.");
				break;
			}

		}

	}

	private static void scoreView(int score, ArrayList<Integer> scoreList) {
		// 볼링 점수판출력 
		
		System.out.println();
		for (int i = 0; i < scoreList.size(); i++) {
			System.out.print(scoreList.get(i) + "|");
		}
		System.out.println();
		System.out.println(score + "점");
	}

	private static Integer nextFrameFirstRollin(ArrayList<Integer> scoreList, int i) {
		return scoreList.get(i + 2);
	}

	private static int nextFrameTotalRollin(ArrayList<Integer> scoreList, int i) {
		return nextFrameFirstRollin(scoreList, i) + scoreList.get(i + 3);
	}

	private static boolean isSpare(int firstRollinFrame, int SecondRollinFrame) {
		return firstRollinFrame + SecondRollinFrame == 10;
	}

	private static boolean isStrike(int firstRollinFrame) {
		return firstRollinFrame == 10;
	}

	private static String Empty(String input) {
		String emptyStr = input.replace(" ", "");
		return emptyStr;
	}

	private static boolean isNumber(String input) {
		boolean flag = true;
		if (!input.matches("[0-9]+")) {
			flag = false;
		}
		return flag;
	}

}

