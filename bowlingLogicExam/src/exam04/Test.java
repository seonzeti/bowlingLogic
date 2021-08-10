package exam04;

public class Test {

	public static void main(String[] args) {

		twoPlayerGame();
		// 2명이서 올스페어인 조건
		threePlayerGame();
		// 1명은 올스페어, 1명은 올스트라이크, 1명은 거터게임인 조건

	}

	private static void twoPlayerGame() {		

		// 필요한 만큼 선언
		Game[] g = new Game[2];
		Multi[] m = new Multi[2];

		Boolean isOK = true;

		// 배열을 할당
		for (int i = 0; i < 2; i++) {

			g[i] = new Game();
			m[i] = new Multi();

			isOK = true;

			// player이름 설정
			m[i].setName("p" + (i + 1));

			// 프레임 반복
			while (isOK) {
				g[i].roll(5);
				if (g[i].currentRoll == 21) {
					isOK = false;
				}
				m[i].setTotalScore(g[i].getscore());
			}

		}

		if (m[0].getTotalScore() == 150 && m[1].getTotalScore() == 150) {
			System.out.println("twoPlayerGame Success!");
		} else {

			System.out.println("twoPlayerGame Failed...");
		}

	}

	private static void threePlayerGame() {

		

		// 필요한 만큼 선언
		Game[] g = new Game[3];
		Multi[] m = new Multi[3];

		Boolean isOK = true;

		// 배열을 할당
		for (int i = 0; i < 3; i++) {

			g[i] = new Game();
			m[i] = new Multi();

			isOK = true;

			// player이름 설정
			m[i].setName("p" + (i + 1));

			// 프레임 반복
			while (isOK) {

				if (i == 0) {
					g[i].roll(5);
					if (g[i].currentRoll == 21) {
						isOK = false;
					}
				} else if (i == 1) {
					g[i].roll(10);
					if (g[i].currentRoll == 13) {
						isOK = false;
					}
				} else if (i == 2) {
					g[i].roll(0);
					if (g[i].currentRoll == 20) {
						isOK = false;
					}
				}

				// 최종 점수 세팅
				m[i].setTotalScore(g[i].getscore());
			}

		}

		if (m[0].getTotalScore() == 150 && m[1].getTotalScore() == 300 && m[2].getTotalScore() == 0) {
			System.out.println("threePlayerGame Success!");
		} else {
			System.out.println("threePlayerGame Failed...");
		}

	}

}
