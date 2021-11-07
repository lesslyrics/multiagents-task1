package multiAgentAverage.average;

import java.util.concurrent.TimeUnit;
import jade.core.Agent;


public class SingleAgent extends Agent {

    private String[] agentsGraph;
    private float value;

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
        String[][] graph = {
                {"3","2"},
                {"1","5"},
                {"2","4"},
                {"3","5"},
                {"4","1"},
                {"2","7"},
                {"7","3"},
                {"4","2"},
                {"2","5"},
                {"6","1"},
                {"7","6"}
        };

        agentsGraph = graph[id];
        addBehaviour(new FindAverageValue(this, TimeUnit.SECONDS.toMillis(1)));
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