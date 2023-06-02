import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Scoreboard  
{
    // instance variables - replace the example below with your own
    private int x;

    private String scorePath;
    private Map<String, Integer>scoreList = new TreeMap<>();
    
    Scoreboard(String scorePath) {
        this.scorePath = scorePath;
        setUp();
    }
    
    private void setUp() {
        try(BufferedReader br = new BufferedReader(new FileReader(scorePath))) {
            for(int i = 0; i < 5; i++) {
                String[] line = br.readLine().split(",");
                scoreList.put(line[0], Integer.parseInt(line[1]));
            }
        } catch(java.io.IOException e) {
            System.out.println("Error reading score file");
        }
    }
    
    public void addScore(String playerName, int score) {
        scoreList.put(playerName, score);
    }
    
    public List<Map.Entry<String, Integer>> getScoreboard() {
        List<Map.Entry<String, Integer>> elements = new LinkedList<>(scoreList.entrySet());
        Collections.sort(elements, new Comparator<Map.Entry<String, Integer>>() {
    
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 ) {
                return o2.getValue().compareTo(o1.getValue());
            }
    
        });
        return elements;
    }
    
    public void close() {
        List<Map.Entry<String, Integer>> scores = getScoreboard();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(scorePath))) {
            for(int i = 0; i < 5; i++) {
                bw.write(scores.get(i).getKey() + "," + scores.get(i).getValue()+"\n");
            }
        } catch(java.io.IOException e) {
            System.out.println("Error reading score file");
        }
    }
    
}
