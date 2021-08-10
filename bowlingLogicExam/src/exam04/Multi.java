package exam04;

public class Multi {

	//game.java는 단순히 게임 로직이였지만 multi는 player의 정보가 들어가있다.
	//그러므로 private으로 선언해주고, 외부에서 처리할 수 있도록 Getter / Setter 선언을 해준다.

	private String name ;  // 이름 
	private int[] scoreList = new int[21]; // 점수를 넣을 배열
	private int totalScore ; // 총점수 
	
	//게임이 끝났는지 확인하기 위함 
	private boolean gameStatus;
	
	
	public boolean isGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(boolean gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int[] getScoreList() {
		return scoreList;
	}
	public void setScoreList(int[] scoreList) {
		this.scoreList = scoreList;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	

	
	
	
	
	

}
