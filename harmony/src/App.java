import java.io.BufferedReader;
/* import java.io.FileReader; */
import java.io.InputStreamReader;
public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
       /*  BufferedReader bi = new BufferedReader(new FileReader("data/1.txt")); */
        String line= "";
        try {
            line = bi.readLine();
            Integer numberOfVertices = Integer.parseInt(line.split(" ")[0]);
            Graphi graph = new Graphi(numberOfVertices);
            while ((line = bi.readLine()) != null) {
                var endpoint1 = Integer.parseInt(line.split(" ")[0]);
                var endpoint2 = Integer.parseInt(line.split(" ")[1]);
                var conflict = Integer.parseInt(line.split(" ")[2]);
                graph.addEdge(endpoint1, endpoint2,conflict);
            }
                new DFS(graph, 0);
            bi.close();
        } catch (Exception e) {
        
        }
    }
}