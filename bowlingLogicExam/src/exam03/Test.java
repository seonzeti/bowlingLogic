package exam03;

public class Test {

	public static void main(String[] args) {

		Game g;

		randomGame();

		gutterGame();

		perfectGame();

		allspareGame();

	}

	public static void randomGame() {
		Game g = new Game();
		g.roll(7);
		g.roll(2);
		g.roll(3);
		g.roll(5);
		if (g.getscore() == 17) {
			System.out.println("input Game Success!");
		} else {
			System.out.println("input Game Failed");

		}

	}

	public static void gutterGame() {
		Game g = new Game();

		for (int frames = 0; frames < 20; frames++) {
			g.roll(0);
		}
		if (g.getscore() == 0) {
			System.out.println("gutter Game Success!");
		} else {
			System.out.println("gutter Game Failed...");
		}
	}

	public static void perfectGame() {

		Game g = new Game();

		for (int frames = 0; frames < 12; frames++) {
			g.roll(10);
		}

		if (g.getscore() == 300) {
			System.out.println("perfect Game Success!");
		} else {
			System.out.println("perfect Game Failed..");
		}
	}

	public static void allspareGame() {
		Game g = new Game();

		for (int frames = 0; frames < 21; frames++) {
			g.roll(5);
		}

		if (g.getscore() == 150) {
			System.out.println("allSpare Game Success!");
		} else {
			System.out.println("allSpare Game Failed...");
		}

	}

}
