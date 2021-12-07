package multiAgentAverage.average;

import java.util.Objects;
import java.util.Random;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;


public class FindAverageValue extends TickerBehaviour {

    private final SingleAgent agent;
    private int iteration;

    private final int MAX_STEPS = 200;
    private final int CHECK_POINT = 20;

    FindAverageValue(SingleAgent agent, long period) {
        super(agent, period);
        this.agent = agent;
        this.iteration = 1;
        this.setFixedPeriod(true);
    }

    @Override
    public void onTick() {

        int neighborsNumber = agent.getAgentsGraph().length;

        boolean[] isConnectActive = setConnectPresence(neighborsNumber);
        float[] noise = setNoise(neighborsNumber);

        if (iteration <= MAX_STEPS) {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setSender(agent.getAID());
            // send messages to neighbors
            for (int i = 0; i < neighborsNumber; i++) {
                // send message only if connection is active
                if (isConnectActive[i]){
                    msg.addReceiver(new AID(agent.getAgentsGraph()[i], AID.ISLOCALNAME));
                    try {
                        msg.setContent(Objects.toString(agent.getValue() + noise[i])); // added noise to message
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    agent.send(msg);
                }
            }

            int totalNeighbours = 0;
            float sumByNeighbours = 0;

            // due to initial graph structure
            while (totalNeighbours < 2 && agent.getCurQueueSize() > 0){
                ACLMessage messageReceived = agent.receive();

//                 System.out.println("Agent " + agent.getLocalName() + " received " + messageReceived.getContent() +
//                         " from agent " + messageReceived.getSender().getLocalName());

                // find values sum from neighbors
                sumByNeighbours += Float.parseFloat(messageReceived.getContent());

                totalNeighbours++;
            }

            // step size
            float step = 1f / (neighborsNumber + 1f);
            float currentValue = this.agent.getValue();

            // update value for current agent
            this.agent.setValue(currentValue + step * (sumByNeighbours -  totalNeighbours * currentValue));

            // logging
            if (iteration % CHECK_POINT == 0){
                System.out.println("Agent " + this.agent.getLocalName() + " average = "+ this.agent.getValue() +
                        " at iteration " + iteration);
            }
            iteration++;
        } else {
            this.stop();
        }
    }

    private boolean[] setConnectPresence(int neighborsNumber) {
        Random random = new Random();
        // state of the connection channels with neighbors
        boolean[] isConnectActive = new boolean[neighborsNumber];
        for (int i =0; i < isConnectActive.length; i++)
            isConnectActive[i] = random.nextBoolean();
        return isConnectActive;
    }

    private float[] setNoise(int neighborsNumber) {
        Random random = new Random();
        // noise matrix
        float[] noise = new float[neighborsNumber];
        for (int i = 0; i < noise.length; i++)
            noise[i] = random.nextFloat() / 10f;
        return noise;
    }
}
