package exam04;

import java.util.Scanner;

public class Insert {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 필요한 변수들
		Boolean isOK = true;
		Boolean isFrame = true;
		Boolean isTurn = true;
		Boolean isBonus = true;

		int playerNum = 0;
		int pin = 0;

		// 게임 전체를 도는 while문
		while (isOK) {
			System.out.print("플레이어 수는 몇명입니까? >> ");
			String strPlayerNum = sc.nextLine();
			if (isNumber(strPlayerNum)) {
				playerNum = Integer.parseInt(strPlayerNum);

				// 필요한 배열들을 선언
				Game[] g = new Game[playerNum];
				Multi[] m = new Multi[playerNum];

				// player수만큼 객체 생성하고 이름 선언, 객체 초기화
				for (int i = 0; i < playerNum; i++) {
					MultiFirstSetting(g, m, i);
				}

				// 게임의 '계산식'을 도는 while문
				while (isFrame) {

					// 유저의 수는 정해져있으므로 for문으로 돌려준다.
					for (int i = 0; i < playerNum; i++) {

						// player의 게임이 끝나지 않았다면 isTurn도 계속 반복시켜준다.
						if (m[i].isGameStatus() == true) {
							isTurn = true;
						} else {
							isFrame = false;
						}

						// 한 투구를 담당하는 while문, isturn으로 설정
						while (isTurn) {

							// 출력 
							// ex) p1의 1-1투구 : 
							System.out.print(m[i].getName() + "의 " + g[i].frameNum + "-");

							if (g[i].frameNum == 10) {
								System.out.print(g[i].tenTurn + " 투구 : ");
							} else {
								System.out.print(g[i].turn + " 투구 : ");
							}

							//투구입력
							String str_Pin = sc.nextLine();
							if (isNumber(str_Pin)) {
								pin = Integer.parseInt(str_Pin);

								// 전 투구 + 현 투구가 11보다 작다면
								if (g[i].prevRoll + pin < 11) {

									// roll!
									g[i].roll(pin);

									// prevRollCheck() : 전 핀을 구분해서 조건에 맞게 prevRoll에 넣어주는 역할
									g[i].prevRollCheck();

									// 10프레임으로 전체 게임 종료 판별
									if (g[i].frameNum == 10) {

										// 10프레임 1,2,3투구 조건식 확인
										if (g[i].tenTurn == 3) {
											// 10-3이라면, 점수와 관계 없이 무조건 게임 종료
											System.out.println(m[i].getName() + "의 게임이 종료되었습니다.");

											//반복문 전부 종료 / GameStatus도 false로 세팅 
											m[i].setGameStatus(false);
											isTurn = false;
											isFrame = false;

										} else {
											// 10-1 / 10-2의 경우 점수에 따라 달라진다.
											isBonus = g[i].isBonus(g[i].frameNum, g[i].tenTurn);
											if (isBonus == false) {
												System.out.println(m[i].getName() + "의 게임이 종료되었습니다.");
												
												//한 유저의 게임만 종료되었을 수도 있으니 isFrame은 제외하고 종료 
												m[i].setGameStatus(false);
												isTurn = false;
											}
										}
										
										//10프레임의 Frame과 Turn을 set 해준다. 
										setFrameTurn(g, i);

									} else {

										// 10프레임이 아닌 평소에는?
										if ((g[i].turn == 1 && pin == 10) || g[i].turn == 2) {
											// 1프레임에서 스트라이크를 쳤거나, 2프레임을 친 경우는 넘어간다.
											setFrameTurn(g, i);
											isTurn = false;
										} else {
											setFrameTurn(g, i);
											// 그 외의 경우는 2투구에 들어와야하므로 continue
											continue;
										}

									}

								} else {
									// 전 투구 + 현 투구가 11보다 큰 경우
									System.out.println("더 작은 수를 입력해주세요.");
								}

							} else {
								//숫자가 아닌 값을 입력했을 경우 
								System.out.println("숫자로 입력해주세요.");
							}

						}

					}

				}

				// 계산식 종료 이후 최종 점수를 세팅해준다.
				for (int i = 0; i < playerNum; i++) {

					MultiLastSetting(g, m, i);

					System.out.print(m[i].getName() + "의 총점 : ");
					System.out.println(m[i].getTotalScore());
				}

			} else {
				//숫자가 아닌 값을 입력했을 경우 
				System.out.println("숫자를 입력해주세요.");
				continue;
			}

			//게임의 재시작 여부 
			System.out.print("게임을 계속 하시겠습니까? [y/n] ");
			String check = sc.nextLine();
			if (check.equals("y") || check.equals("Y")) {

				// 게임을 다시 진행하기 때문에 while문을 전부 초기화시켜준다.
				isOK = true;
				isBonus = true;
				isFrame = true;
				isTurn = true;

				playerNum = 0;
				pin = 0;

			} else if (check.equals("n") || check.equals("N")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 프로그램을 종료합니다.");
				break;
			}

		}

	}

	static void setFrameTurn(Game[] g, int i) {
		g[i].getFrame();
		g[i].getTurn();
	}

	static void MultiLastSetting(Game[] g, Multi[] m, int i) {
		m[i].setScoreList(g[i].scoreList);
		m[i].setTotalScore(g[i].getscore());
	}

	static void MultiFirstSetting(Game[] g, Multi[] m, int i) {
		g[i] = new Game();
		m[i] = new Multi();

		// player이름 설정
		m[i].setName("p" + (i + 1));

		// Game설정
		m[i].setGameStatus(true);
	}

	private static boolean isNumber(String input) {
		boolean flag = true;
		if (!input.matches("[0-9]+")) {
			flag = false;
		}
		return flag;
	}
}
