import java.util.ArrayList;

public interface Player {
	public void newRound();
	public void addCard(String card);
	public int sumCards();
	public String play(int deal);
	public void printHand();
	public String choice();
	public void winHand();
	public void loseHand();
	public int chips();
	public String playerIndex();
	public int dealerUp();
	public void bet();
	public void newShuffle();
	public void printPlayer(int chips);
	public void printBet();
	public void updateRunningCount(ArrayList<String> countList);
	
	default public int translate(String card){
		char c = card.charAt(0);
		if ("JQK".indexOf(c) > -1)
			return 10;
		else if (c == 'A')
			return 11;
		else
			return Integer.parseInt(card);
	}
}
