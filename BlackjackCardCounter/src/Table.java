public class Table {
	
	public static void main(String[] args) {
		Game g = new Game(1, 1, 5000, 10);
		for(int i = 0; i < 50; i++){
			g.playPrintedRound();
		}
		g.printTotals();
	}
}
