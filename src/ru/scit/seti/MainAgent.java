package ru.scit.seti;

import jade.core.behaviours.Behaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: scit
 * Date: 12/11/13
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainAgent extends GuiAgent{
    public static final int CONVERT = 1;
    transient protected MainFrame gui;
    private ConvertParamsHolder cph;

    public void setup() {
        gui = new MainFrame(this);
        gui.setVisible(true);
    }

    protected void onGuiEvent(GuiEvent ge) {
        if(ge.getType() == CONVERT) {
            cph = (ConvertParamsHolder) ge.getParameter(0);
            Behaviour b = new MainBehaviour(this);
            addBehaviour(b);
        }
    }

    public void onBehaviourPerformed(String response) {
        gui.onAgentResponse(response);
    }

    public ConvertParamsHolder getConvertParamsHolder() {
        return cph;
    }
}
