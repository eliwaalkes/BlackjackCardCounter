import java.util.ArrayList;

public class Dealer implements Player{
	ArrayList<String> cards = new ArrayList<>();
	String choice;
	public int runningCount = 0;
	
	public void printHand(){
		System.out.print("Dealer: ");
		for(String i : cards)
			System.out.print(i + ", ");
		System.out.print("Total: " + Integer.toString(sumCards()));
		System.out.print("\n");
	}
	
	public int dealerUp(){
		String c = cards.get(1);
		return translate(c.substring(0, c.length()-1));
	}
	
	public String choice(){
		return choice;
	}
	
	public void newRound(){
		cards.clear();
	}
	
	public void addCard(String card){
		cards.add(card);
	}
	
	public int sumCards(){
		int sum = 0;
		boolean ace = false;
		for(String c : cards){
			String i = c.substring(0,c.length()-1);
			if (i.equals("A"))
				ace = true;
			Integer x = translate(i);
			sum += x;
		}
		if (sum > 21 && ace)
			sum -= 10;
		return sum;
	}
	
	public String play(int deal){
		int sum = sumCards();
		if(sum > 21)
			choice = "Bust";
		else if(sum >= 17)
			choice = "Stay";
		else
			choice = "Hit";
		return choice;
	}

	public void changeChips(int chips) {
	}

	public void winHand() {
	}

	public void loseHand() {
	}
	
	public String playerIndex(){
		return "";
	}
	
	public int chips(){
		return 0;
	}

	public void bet() {
	}

	public void newShuffle() {
	}

	public void printPlayer(int chips) {
	}
	public String secondCard(){
		return cards.get(1);
	}

	@Override
	public void printBet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRunningCount(ArrayList<String> countList) {
		// TODO Auto-generated method stub
		
	}
	
}
