import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	public ArrayList<String> deck = new ArrayList<>();
	public ArrayList<String> deck2 = new ArrayList<>();
	public ArrayList<String> allCards = new ArrayList<>();
	int cur;
	int size;
	
	public Deck(int num){
		cur = 0;
		size = 52*num;
		for(int i = 0; i < num; i++){
			for(int j = 0; j < 4; j++){
				for(int k = 1; k < 14; k++){
					if(k==1){
						deck.add("A");
						deck2.add("A");
					}
					else if(k <= 10){
						deck.add(Integer.toString(k));
						deck2.add(Integer.toString(k));
					}
					else if(k == 11){
						deck.add("J");
						deck2.add("J");
					}
					else if(k == 12){
						deck.add("Q");
						deck2.add("Q");
					}
					else if(k == 13){
						deck.add("K");
						deck2.add("K");
					}
				}
			}
		}
		Collections.shuffle(deck);
	}
	
	public String deal(){
		String d = "";
		if (size*4/7 < cur){
			deck.clear();
			deck = new ArrayList<>(deck2);
			Collections.shuffle(deck);
			cur = 0;
			d += "X";
			System.out.println("\n\n\n\n\n-------------------------\n\n\n\n\n" );
		}
		d += deck.remove(0);
		cur++;
		allCards.add(d);
		return d;
	}
	
	public void print(){
		System.out.println("Deck: ");
		for(String i : deck){
			System.out.println(i);
		}
		System.out.print("\n");
	}
	
	public void printAllCards(){
		System.out.println(allCards);
	}
}
