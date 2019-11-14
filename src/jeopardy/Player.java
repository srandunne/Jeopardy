/*
 * @author Sean Randunne, Shalin Mehta
 * @teacher(Ms. Denna)
 * @version(12/17/2017)
 * This is the Player class for the 2 Jeopardy players
 */
package jeopardy;

public class Player {
	
	private String name;
	private int winnings;
	
	//@param(String name - the name of the player)
	public Player(String name) {
		this.name = name;
		this.winnings = 0;
	}
	
	//@param(int hundo - the amount that the player's winnings is going up)
	//This method raises the Player's winnings by however much is passed in through the parameter
	public void incrementWinnings(int hundo)
	{
		winnings+=hundo;
	}
	
	//@return(the Player's name)
	public String getName() {
		return name;
	}
	
	//@return(the Player's current winnings)
	public int getWinnings() {
		return winnings;
	}

}