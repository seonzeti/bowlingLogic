package exam03;


public class Game {
	
	
	// Test 03. 한사람이 플레이하는 볼링 로직을 작성하시오.
	//TDD방식으로 개발하되, JUnit은 사용하지않는 방법으로 연습한다.

	private int score = 0;
	// 점수

	// 투구 점수
	public int[] scoreList = new int[21];

	public int[] bonusList = new int[3];

	// 총 21번의 투구가 있으므로
	public int currentRoll = 0;

	public int frameNum = 1;
	public int turn = 1;
	public int tenTurn = 1;

	public void roll(int pins) {
		// 몇개의 핀이 쓰러졌는지 알 수 있음
		scoreList[currentRoll++] = pins;

	}

	public Boolean isBonus(int frame, int turn) {
		// 10프레임 판별을 위한 bonus 메소드

//		만약 10-1이 스트라이크라면 10-2+10-3의 합이 10이 넘어선 안된다
//		만약 10-1이 스트라이크가 아니라면, 10-1+10-2의 합이 10이 넘어선 안된다
//		만약 10-1 , 10-2 모두 스트라이크라면 10-3은 10이 넘어선 안된다. 

		if (frame == 10) {

			if (turn == 1) {
				bonusList[0] = scoreList[currentRoll - 1];
				// 10-1값은 무조건 받는다.

			} else if (turn == 2) {
				bonusList[1] = scoreList[currentRoll - 1];

				// 10-1
				// 스트라이크라면? 10-2 + 10-3을 판단해야하니까 일단 넘기기
				if (bonusList[0] == 10) {
					return true;
				} else {
					// 10-1이 스트라이크가 아닌데, 두개의 합이 10이 안된다면? 스페어, 스트라이크 처리에 실패한거니까 false
					if (bonusList[0] + bonusList[1] < 10) {
						return false;
					} else {
						// 스트라이크가 아니다.
						return true;
					}
				}

			} else if (turn == 3) {
				bonusList[2] = scoreList[currentRoll - 1];

			}

		}
		return true;

		// 만약 false를 출력하면

	}

	public void getFrame() {
		// g.frameNum이 증가되어야 하는 두가지 경우 : 2투구 or 1투구때 스트라이크
		// 단, 10프레임에서는 더이상 프레임이 증가해서는 안된다.

		if (frameNum < 10) {

			if (turn == 2 && scoreList[currentRoll - 1] == 10) {
				frameNum++;
			} else {
				if (turn == 1) {
					frameNum++;
				}
			}

		}

	}

	public void getTurn() {// turn 설정
		if (frameNum == 10) {
			tenTurn++;

		} else {
			// 10프레임이 아닌경우
			// 10프레임이 아닌데 스트라이크인 경우 무조건 turn을 1로

			if (currentRoll > 0) {
				if (scoreList[currentRoll - 1] == 10) {
					turn = 1;
				} else {
					// 10프레임이 아닌데 스트라이크가 아닌 경우
					if (turn == 1) {
						turn = 2;
					} else if (turn == 2) {
						turn = 1;
					}
				}
			}

		}
	}

	// 점수계산
	public int getscore() {
		// return할 점수
		int score = 0;
		int firstRollinFrame = 0;

		for (int frame = 0; frame < 10; frame++) {
			if (isSpare(firstRollinFrame)) {
				score += 10 + nextBallForSpare(firstRollinFrame);
				firstRollinFrame += 2;

			} else if (isStrike(firstRollinFrame)) {
				score += 10 + nextBallsForStrike(firstRollinFrame);
				firstRollinFrame += 1;
			} else {
				score += nextBallsforOften(firstRollinFrame);
				firstRollinFrame += 2;
			}

		}
		return score;
	}

	private int nextBallsforOften(int firstRollinFrame) {
		return scoreList[firstRollinFrame] + scoreList[firstRollinFrame + 1];
	}

	private int nextBallsForStrike(int firstRollinFrame) {
		return scoreList[firstRollinFrame + 1] + scoreList[firstRollinFrame + 2];
	}

	private int nextBallForSpare(int firstRollinFrame) {
		return scoreList[firstRollinFrame + 2];
	}

	private boolean isStrike(int firstRollinFrame) {
		return scoreList[firstRollinFrame] == 10;
	}

	private boolean isSpare(int firstRollinFrame) {
		return nextBallsforOften(firstRollinFrame) == 10;
	}

}
