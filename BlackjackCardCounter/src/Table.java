import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Table {
	
	public static void main(String[] args) throws IOException {
		int [] numRounds = {10,50,100,500,1000};
		int [] sPlayers = {5,3,2,3,1};
		int [] kPlayers = {2,1,4,3,5};
		int [] wPlayers = {1,3,3,2,2};
		
		for (int j = 0; j<5; j++){
			Game g = new Game(sPlayers[j], kPlayers[j], wPlayers[j], 0, 5000, 10);
			for(int i = 0; i < numRounds[j]; i++){
				g.playRound();
			}
			String columns = "Player Kind, Net Chips, Total Chips";
			List<String> gameResults = new ArrayList<String>();
			gameResults.add(columns);
			for(String i : g.listTotals())
				gameResults.add(i);
			writeGame(g, gameResults, numRounds[j]);
		}
		
		
	}
	
	public static void writeGame(Game g, List<String> gameResults, int numRounds) throws IOException{
		String name = Integer.toString(g.simplePlayers) + " Simple Players, ";
		name += Integer.toString(g.KOCounters) + " KO Counters, ";
		name += Integer.toString(g.WongHalvesCounter) + " Wong Halves Counters, ";
		name += Integer.toString(g.HumanPlayers) + " Human Players\n";
		name += "Initial Chips: " + Integer.toString(g.chips) + " Minimum Bet: " + Integer.toString(g.minBet) + "\n";
		name += "Number of Hands: " + Integer.toString(numRounds) + "\n";
		List<String> e = new ArrayList<>();
		e.add(name);
		Path file = Paths.get("Results.txt");
		Files.write(file, e, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		for (String res : gameResults){
			List<String> f = new ArrayList<>();
			f.add(res);
			Files.write(file, f, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		}
		List<String> g1 = new ArrayList<>();
		g1.add("\n\n\n");
		Files.write(file, g1, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
	}
}
