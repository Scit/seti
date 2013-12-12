package ru.scit.seti;

import jade.gui.GuiEvent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame implements ActionListener {
	//UI components
	JPanel inputPanel;
	JPanel outputPanel;
	JTextField inputUrl;
	JTextField outputUrl;
	JCheckBox withImages;
	JCheckBox withTables;
	JLabel inputUrlLabel;
	JLabel withImagesLabel;
	JLabel withTablesLabel;
	JLabel infoLabel;
	JButton submit;
	
	Process proc;
    MainAgent agent;
	
	public MainFrame(MainAgent agent) {
        super();
        this.agent = agent;
		//Setting up the frame
		setTitle("Main");
		setSize(500, 200);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
   
	    //Creating "input" UI-components
	    inputUrl = new JTextField();
	    withImages = new JCheckBox();
	    withTables = new JCheckBox();
	    inputUrlLabel = new JLabel("Ссылка");
	    withImagesLabel = new JLabel("Конвертировать изображения");
	    withTablesLabel = new JLabel("Конвертировать таблицы");
	    
	    submit = new JButton("Конвертировать");
	    submit.addActionListener(this);
   
	    //Creating "output" UI-components
	    infoLabel = new JLabel("Файл успешно сконвертирован:");
	    outputUrl = new JTextField();
	    outputUrl.setEditable(false);
   
	    //Initializing panels
	    inputPanel = new JPanel();
	    inputPanel.setLayout(new GridLayout(4,2));	       
	    outputPanel = new JPanel();
	    outputPanel.setLayout(new GridLayout(2,1));
   
	    //Adding components into inputPanel
	    inputPanel.add(inputUrlLabel);
	    inputPanel.add(inputUrl);
	    inputPanel.add(withImagesLabel);
	    inputPanel.add(withImages);
	    inputPanel.add(withTablesLabel);
	    inputPanel.add(withTables);
	    inputPanel.add(submit);
   
	    //Adding components into outputPanel
	    outputPanel.add(infoLabel);
	    outputPanel.add(outputUrl);
   	
	    //Seting the inputPanel active
	    setContentPane(inputPanel);
	    invalidate();
	    validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
        ConvertParamsHolder cph = new ConvertParamsHolder(
                inputUrl.getText(),
                withTables.isSelected(),
                withImages.isSelected());

        GuiEvent ge = new GuiEvent(null, MainAgent.CONVERT);
        ge.addParameter(cph);
        agent.postGuiEvent(ge);
	}

    public void onAgentResponse(String response) {
        setContentPane(outputPanel);
        invalidate();
        validate();

        if(response != null) {
            outputUrl.setText(response);
        } else {
            infoLabel.setText("Ошибка конвертации");
        }
    }
}
