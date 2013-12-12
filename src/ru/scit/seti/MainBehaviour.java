package ru.scit.seti;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

/**
 * Created with IntelliJ IDEA.
 * User: scit
 * Date: 12/11/13
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainBehaviour extends SimpleBehaviour {
    MainBehaviour(Agent agent) {
        super(agent);
    }

    public void action() {
        MainAgent agent = (MainAgent) myAgent;
        ConvertParamsHolder cph = agent.getConvertParamsHolder();
        String response = HttpConverter.convert(cph);
        agent.onBehaviourPerformed(response);
    }

    public boolean done() {
        return true;
    }


    /*
    try {
        htmlToFb2ConvertRequest();
    } catch (IOException e) {
        e.printStackTrace();
    }

    //Html to fb2 convertation func
    private void htmlToFb2ConvertRequest() throws IOException {
        String args = "";

        //Setting withImage param
        if(withImages.isSelected()) {
            args += " -i 1";
        }
        //Setting withTables param
        if(withTables.isSelected()) {
            args += " -t 1";
        }
        //Setting url param
        args += " -u " + inputUrl.getText();

        //Launching script
        Runtime rt = Runtime.getRuntime();
        proc = rt.exec("./script.sh" + args);

        htmlToFb2ConvertResponce();
    }

    private void htmlToFb2ConvertResponce() throws IOException {
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        String url = stdInput.readLine();
        outputUrl.setText(url);
    }
    */
}
