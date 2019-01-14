public class Game {
	//This class manages a guessing game.
	private int number; //The number the user is trying the guess
	private int score; //The total number of guesses
	
	public Game() {
		newGame(); //A new game is created upon construction
	}
	
	public void newGame() {
		number = (int)(Math.random() * 100) + 1; //A new game requires a random number to guess.
		score = 0;
	}
	
	public void scoreUp1() {score++;} //Score is increased by 1.

	//Getters
	public int getNumber() {return number;}
	public int getScore() {return score;}
	
	public static boolean isNumeral(String s) {
		//Class determines whether or not input is a number
		try {
			int n = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
