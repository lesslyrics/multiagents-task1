package multiAgentAverage.average;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class MainController {
    public static final int AGENTS_TOTAL = 7;

    public void initAgents() {

        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "8085");

        profile.setParameter(Profile.CONTAINER_NAME, "CurrentContainer");
        profile.setParameter(Profile.GUI, "true");

        Runtime runtime = Runtime.instance();
        ContainerController cc = runtime.createMainContainer(profile);

        // construct multiagent system
        try {
            for (int i = 1; i <= AGENTS_TOTAL; i++) {
                AgentController agent = cc.createNewAgent(
                        String.valueOf(i),
                        "multiAgentAverage.pack.SingleAgent",
                        null);
                agent.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
