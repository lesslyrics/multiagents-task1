package multiAgentAverage.average;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import jade.core.Agent;


public class SingleAgent extends Agent {

    private String[] agentsGraph;
    private float value;
    private final String graphLocation = "src/main/resources/inputs/graph.txt";

    @Override
    protected void setup() {

        String name = getAID().getLocalName();
        int id = Integer.parseInt(name) - 1;

        // set initial agents' values
        switch (name) {
            case "1" -> value = 7f;
            case "2" -> value = 3f;
            case "3" -> value = 16f;
            case "4" -> value = 5f;
            case "5" -> value = 1f;
            case "6" -> value = 18f;
            case "7" -> value = 20f;
        }

        // set agents' connections (graph edges)
        String[][] graph = new String[0][];
        try {
            graph = setAgentsGraph();
        } catch (Exception e) {
            e.printStackTrace();
        }

        agentsGraph = graph[id];
        addBehaviour(new FindAverageValue(this, TimeUnit.SECONDS.toMillis(1)));
    }

    private String[][] setAgentsGraph() throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(graphLocation)));
        String[] size = sc.nextLine().trim().split(" ");

        int rows = Integer.parseInt(size[0]);
        int columns = Integer.parseInt(size[1]);

        String[][] graph = new String[rows][columns];
        while(sc.hasNextLine()) {
            for (String[] strings : graph) {
                String[] line = sc.nextLine().trim().split(" ");
                System.arraycopy(line, 0, strings, 0, line.length);
            }
        }
        return graph;
    }


    public String[] getAgentsGraph() {
        return agentsGraph;
    }

    public void setValue(float value) {
        this.value = value;
    }
    public float getValue() {
        return value;
    }

}
