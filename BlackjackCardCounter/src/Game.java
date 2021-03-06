import java.util.ArrayList;
import java.util.List;

public class Game {
	public int numPlayers;
	public Player[] players;
	public Dealer dealer;
	public Deck deck;
	public int dealerUp;
	public ArrayList<String> countList = new ArrayList<>();
	int chips, simplePlayers, KOCounters, WongHalvesCounter, HumanPlayers, minBet;
	
	public Game(int simplePlayers, int KOCounters, int WongHalvesCounter, int HumanPlayers, int chips, int minBet){
		this.simplePlayers = simplePlayers;
		this.KOCounters = KOCounters;
		this.WongHalvesCounter = WongHalvesCounter;
		this.HumanPlayers = HumanPlayers;
		this.minBet = minBet;
		this.numPlayers = simplePlayers + KOCounters + WongHalvesCounter + HumanPlayers;
		this.players = new Player[numPlayers];
		for (int i = 0; i < simplePlayers; i++){
			Player p = new SimplePlayer(chips, i+1, minBet);
			players[i] = p;
		}
		for (int i = simplePlayers; i < numPlayers; i++){
			Player p = new KOCounterPlayer(chips, i+1, minBet);
			players[i] = p;
		}
		for (int i = simplePlayers + KOCounters; i < numPlayers; i++){
			Player p = new WongHalvesCounter(chips, i+1, minBet);
			players[i] = p;
		}
		for (int i = simplePlayers + KOCounters + WongHalvesCounter; i < numPlayers; i++){
			Player p = new HumanPlayer(chips, i+1, minBet);
			players[i] = p;
		}
		this.dealer = new Dealer();
		this.deck = new Deck(8);
		this.chips = chips;
	}
	
	public void newShuffle(){
		for (Player p : players)
			p.newShuffle();
	}
	
	public String checkCard(String card){
		if (card.contains("X")){
			newShuffle();
			String x = checkCard(deck.deal());
			return x;
		}
		else
			return card;
	}
	
	public void deal(){
		countList.clear();
		dealer.newRound();
		String c;
		for(Player p : players){
			p.newRound();
			c = checkCard(deck.deal());
			p.addCard(c);
			countList.add(c);
		}
		c = checkCard(deck.deal());
		dealer.addCard(c);
		countList.add(c);
		
		for(Player p : players){
			c = checkCard(deck.deal());
			p.addCard(c);
			countList.add(c);
		}
		c = checkCard(deck.deal());
		String dealerSecond = c;
		countList.add(dealerSecond);
		
		dealer.addCard(dealerSecond);
		dealerUp = dealer.dealerUp();
	}
	
	public void printTotals(){
		System.out.print("\n");
		for(Player p : players){
			p.printPlayer(chips);
		}
		System.out.print("\n");
	}
	
	public List<String> listTotals(){
		List<String> ans = new ArrayList<String>();
		for(Player p : players){
			ans.add(p.listTotals(chips));
		}
		return ans;
	}
	
	public void calculateRound(Player p, Player d){
		if(p.choice().equals("Bust"))
			p.loseHand();
		else if(p.choice().equals("Stay")){
			if(d.choice().equals("Bust"))
				p.winHand();
			if (p.sumCards() < d.sumCards())
				p.loseHand();
			if (p.sumCards() > d.sumCards())
				p.winHand();
		}
	}
	
	public void printRound(Player p, Player d){
		p.printHand();
		if(p.choice().equals("Bust")){
			p.loseHand();
			System.out.print(" You Lose! Busted.");
		}
		else if(p.choice().equals("Stay")){
			if(d.choice().equals("Bust")){
				System.out.print(" You Win! Dealer Busted.");
				p.winHand();
			}
			else if (p.sumCards() < d.sumCards()){
				System.out.print(" You Lose! Dealer's cards are greater" );
				p.loseHand();
			}
			else if (p.sumCards() > d.sumCards()){
				System.out.print(" You win! Your cards are greater!");
				p.winHand();
			}
			else
				System.out.print(" Push.");
		}
		System.out.println("\nRemaining Chips: " + Integer.toString(p.chips()));
		System.out.print("\n");
	}
	
	public void playPrintedRound(){
		for(Player p: players){
			p.bet();
			p.printBet();
		}
		deal();
		System.out.println("Dealer Up: " + Integer.toString(dealerUp));
		for(Player p : players){
			String choice = p.play(dealerUp);
			while(choice.equals("Hit")){
				String c = checkCard(deck.deal());
				p.addCard(c);
				countList.add(c);
				choice = p.play(dealerUp);
			}
		}
		
		String dc = dealer.play(0);
		while(dc.equals("Hit")){
			String c = checkCard(deck.deal());
			countList.add(c);
			dealer.addCard(c);
			dc = dealer.play(dealerUp);
		}
		for (Player p : players){
			p.updateRunningCount(countList);
			printRound(p, dealer);
		}
		dealer.printHand();
		System.out.print("\n");
	}
	
	public void playRound(){
		for(Player p: players)
			p.bet();
		deal();
		for(Player p : players){
			String choice = p.play(dealerUp);
			while(choice.equals("Hit")){
				String c = checkCard(deck.deal());
				p.addCard(c);
				countList.add(c);
				choice = p.play(dealerUp);
			}
		}
		
		String dc = dealer.play(0);
		while(dc.equals("Hit")){
			String c = checkCard(deck.deal());
			countList.add(c);
			dealer.addCard(c);
			dc = dealer.play(dealerUp);
		}
		for (Player p : players){
			p.updateRunningCount(countList);
			calculateRound(p, dealer);
			if (p instanceof HumanPlayer){
				dealer.printHand();
				printRound(p, dealer);
			}
		}
	}

}