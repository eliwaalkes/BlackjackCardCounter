import java.util.ArrayList;

public class SimplePlayer implements Player{
	public ArrayList<String> cards = new ArrayList<>();
	public ArrayList<String> split = new ArrayList<>();
	public int playerIndex;
	public int chips;
	public int bet;
	public String choice;
	public boolean dd = false;
	
	
	public SimplePlayer(int chips, int playerIndex, int bet){
		this.chips = chips;
		this.playerIndex = playerIndex;
		this.bet = bet;
	}
	
	public String choice(){
		return choice;
	}
	
	public void printHand(){
		System.out.print("Player " + Integer.toString(playerIndex) + ": ");
		for(String i : cards)
			System.out.print(i + ", ");
		System.out.print("Total: " + Integer.toString(sumCards()));
	}
	
	public void newRound(){
		cards.clear();
		dd = false;
	}
	
	public void addCard(String card){
		cards.add(card);
	}

	public int sumCards(){
		int sum = 0;
		int aceCount = 0;
		for(String i : cards){
			if (i.equals("A"))
				aceCount++;
			Integer x = translate(i);
			sum += x;
		}
		while(sum > 21 && aceCount > 0){
			sum -= 10;
			aceCount--;
		}
		return sum;
	}
	
	public String play(int deal){
		int sum = sumCards();
		
		if (sum > 21)
			choice = "Bust";
		
		else if (dd==true)
			choice = "Stay";
		
		else if(sum >= 17)
			choice = "Stay";
		
		else if(sum <= 16 && sum >= 13){
			if(deal < 7)
				choice = "Stay";
			else
				choice = "Hit";
		}
		
		else if(sum == 12){
			if(deal >= 4 && deal <= 6)
				choice = "Stay";
			else
				choice = "Hit";
		}
		else if(sum == 11){
			if(deal == 11)
				choice = "Hit";
			else{
				dd = true;
				choice = "Hit";
			}
		}
		
		else if(sum == 10){
			if(deal == 10 || deal == 11)
				choice = "Hit";
			else{
				dd = true;
				choice = "Hit";
			}
		}
		
		else if(sum == 9 && (deal >= 3 && deal <= 6)){
			dd = true;
			choice = "Hit";
		}
		else
			choice = "Hit";
		return choice;
	}

	public void winHand() {
		if (dd)
			chips += 2*bet;
		else {
			String one = cards.get(0);
			String two = cards.get(1);
			if (one.equals("A") && (two.equals("J")||two.equals("Q")||two.equals("K")))
				chips += bet + (bet/2);
			else if  (two.equals("A") && (one.equals("J")||one.equals("Q")||one.equals("K")))
				chips += bet + (bet/2);
			else
				chips += bet;
		}
	}

	public void loseHand() {
		if (dd)
			chips -= 2*bet;
		else
			chips -= bet;
	}
	
	public String playerIndex(){
		return Integer.toString(playerIndex);
	}
	
	public int chips(){
		return chips;
	}
	
	public int dealerUp(){
		return 0;
	}

	public void bet() {
	}

	public void newShuffle() {
	}

	@Override
	public void printPlayer(int chips) {
		int net = chips;
		if (chips() > net)
			net = chips() - chips;
		else if (chips() < net && chips() > 0)
			net = chips() - net;
		else
			net = net + chips();
		System.out.println("Simple Player " + playerIndex() + "\n\tTotal Chips: " + Integer.toString(chips()) + "\n\tNet: " + Integer.toString(net));
	}
	
	public void printBet() {
		System.out.println("Simple Player " + playerIndex() + " Bet = " + Integer.toString(bet));
	}

	@Override
	public void updateRunningCount(ArrayList<String> countList) {
		// TODO Auto-generated method stub
		
	}
	
	
}
