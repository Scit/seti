package ru.scit.seti;

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
	
	public MainFrame() {
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
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	MainFrame mf = new MainFrame();
                mf.setVisible(true);
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Seting the outputPanel active
		setContentPane(outputPanel);
		invalidate();
		validate();
		try {
			htmlToFb2ConvertRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
