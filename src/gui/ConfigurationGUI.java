package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comm.CommunicationPolyscopeServer;
import comm.Configuration;

/* CONFIGURATION PANEL IN CONFIGURATION TAB */

public class ConfigurationGUI extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel moveTypePanel;
	private JPanel velocityPanel;
	private JPanel accelerationPanel;
	private JPanel coordinationPanel;
	private JPanel probesPanel;
	private JPanel controlOutputs;

	private JButton serverResetButton;
	private JButton saveConfigurationButton;
	
	private CommunicationPolyscopeServer cps = MainGUI.getCommunicationPolyscopeServer();
	
	public ConfigurationGUI(){
		initComponents();
	}
	
	/* Initializing the class components */
	private void initComponents(){
		
		moveTypePanel = new ConfigurationMoveTypePanel();
		velocityPanel = new ConfigurationVelocityPanel();
		accelerationPanel = new ConfigurationAccelerationPanel();
		coordinationPanel = new ConfigurationCoordinatePanel();
		probesPanel = new ConfigurationProbesPanel();
		controlOutputs = new ConfigurationControllerOutputsPanel();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;
		
		// reset server button and coordination panel
		saveConfigurationButton = new JButton("Save configuration");
		serverResetButton = new JButton("Reset Server");
		
		/* 
		 * ADDING MOVE TYPE PANEL
		 */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(moveTypePanel, gridBagConstraints);
		
		/* 
		 * ADDING VELOCITY PANEL
		 */

		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(velocityPanel, gridBagConstraints);
		
		/* 
		 * ADDING ACCELERATION PANEL
		 */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(accelerationPanel, gridBagConstraints);
            
		/* 
		 * ADDING CONTROLLER OUTPUTS PANEL
		 */
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.5;
		add(controlOutputs, gridBagConstraints);

        /* 
         * ADDING COORDINATE SYSTEM PANEL 
         */
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(coordinationPanel, gridBagConstraints);

        /*
         *  ADDING SERVER RESET BUTTON  
         */
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.2;
        add(serverResetButton, gridBagConstraints);
        
        /* 
         * ADDING SAVE BUTTON 
         */
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.2;
        add(saveConfigurationButton, gridBagConstraints);
        
        /*
         *  PROBES
         */
		
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.fill = GridBagConstraints.SOUTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 0.5;
		add(probesPanel, gridBagConstraints);
        
        updateConfigurationFromXMLFile(); // Updating panels from config.xml file
        saveConfigurationButtonListener(); // Saving configuration to config.xml file
        resetServerButtonListener();
	}

	private void saveConfigurationButtonListener(){
        saveConfigurationButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isWrongConfiguration()){
					wrongConfigurationPopup();
				}
				else {
					Configuration conf;
					ArrayList<Integer> listOutputsON = ConfigurationControllerOutputsPanel.getDigitalOutputsValuesON();
					Iterator<Integer> itOutputsON = listOutputsON.iterator();
					ArrayList<Integer> listOutputsOFF = ConfigurationControllerOutputsPanel.getDigitalOutputsValuesOFF();
					Iterator<Integer> itOutputsOFF = listOutputsOFF.iterator();
					conf = new Configuration();
					conf.setMoveTypeText(ConfigurationMoveTypePanel.getMoveTypeValue());
					conf.setMoveVelocityText(Integer.parseInt(ConfigurationVelocityPanel.getVelocityValue()));
					conf.setMoveAccelerationText(Integer.parseInt(ConfigurationAccelerationPanel.getAccelerationValue()));
					conf.setMoveCoordinateText(ConfigurationCoordinatePanel.getCoordinateValue());
					conf.setControllerDelayText(Integer.parseInt(ConfigurationControllerOutputsPanel.getDelayValue()));
					conf.setProbesCountText(ConfigurationProbesPanel.getProbesCountText());
					conf.setProbesFreqText(ConfigurationProbesPanel.getProbesFreqText());
					while (itOutputsON.hasNext()){
						conf.setOutputON(itOutputsON.next());
					}
					while (itOutputsOFF.hasNext()){
						conf.setOutputOFF(itOutputsOFF.next());
					}
					System.out.println("Configuration has been saved.");
				}
			}
		});
	}

	public static void updateConfigurationFromXMLFile(){
		Configuration conf = new Configuration();
		String moveType = Configuration.getMoveTypeText();
		
		switch(moveType){
			case "MOVEL": ConfigurationMoveTypePanel.setMoveTypeValue("MOVEL"); break;
			case "MOVEJ": ConfigurationMoveTypePanel.setMoveTypeValue("MOVEJ"); break;
			case "MOVEP": ConfigurationMoveTypePanel.setMoveTypeValue("MOVEP"); break;
		}
		
		String moveVelocity = Configuration.getMoveVelocityText();
		ConfigurationVelocityPanel.setFieldText(moveVelocity);
		ConfigurationVelocityPanel.setSliderValue(Integer.parseInt(moveVelocity));
		
		String moveAcceleration = Configuration.getMoveAccelerationText();
		ConfigurationAccelerationPanel.setFieldText(moveAcceleration);
		ConfigurationAccelerationPanel.setSliderValue(Integer.parseInt(moveAcceleration));
		
		String coordinateSystem = Configuration.getMoveCoordinateText();
		switch(coordinateSystem){
			case "Carthesian": ConfigurationCoordinatePanel.setCoordinateValue("Carthesian");; break;
			case "Joint": ConfigurationCoordinatePanel.setCoordinateValue("Joint");; break;
		}
		
		String delay = Configuration.getControllerDelayText();
		ConfigurationControllerOutputsPanel.setDelayFieldText(delay);
		
		ArrayList<Integer> outputsON = conf.getOutputsON();
		Iterator<Integer> itOutputsON = outputsON.iterator();
		while(itOutputsON.hasNext()){
			switch ((Integer)itOutputsON.next()){
			case 0: ConfigurationControllerOutputsPanel.setValuesON(0); break;
			case 1: ConfigurationControllerOutputsPanel.setValuesON(1); break;
			case 2: ConfigurationControllerOutputsPanel.setValuesON(2); break;
			case 3: ConfigurationControllerOutputsPanel.setValuesON(3); break;
			case 4: ConfigurationControllerOutputsPanel.setValuesON(4); break;
			case 5: ConfigurationControllerOutputsPanel.setValuesON(5); break;
			case 6: ConfigurationControllerOutputsPanel.setValuesON(6); break;
			case 7: ConfigurationControllerOutputsPanel.setValuesON(7); break;
			}
		}
		
		ArrayList<Integer> outputsOFF = conf.getOutputsOFF();
		Iterator<Integer> itOutputsOFF = outputsOFF.iterator();
		while(itOutputsOFF.hasNext()){
			switch ((Integer)itOutputsOFF.next()){
			case 0: ConfigurationControllerOutputsPanel.setValuesOFF(0); break;
			case 1: ConfigurationControllerOutputsPanel.setValuesOFF(1); break;
			case 2: ConfigurationControllerOutputsPanel.setValuesOFF(2); break;
			case 3: ConfigurationControllerOutputsPanel.setValuesOFF(3); break;
			case 4: ConfigurationControllerOutputsPanel.setValuesOFF(4); break;
			case 5: ConfigurationControllerOutputsPanel.setValuesOFF(5); break;
			case 6: ConfigurationControllerOutputsPanel.setValuesOFF(6); break;
			case 7: ConfigurationControllerOutputsPanel.setValuesOFF(7); break;
			}
		}
		
		String probesCount = Configuration.getProbesCountText();
		ConfigurationProbesPanel.setFieldProbesCountText(probesCount);
		String probesFreq = Configuration.getProbesFreqText();
		ConfigurationProbesPanel.setFieldProbesFreqText(probesFreq);
		ConfigurationProbesPanel.setSliderProbesFreqValue(Integer.parseInt(probesFreq));
		
		ConfigurationProbesPanel.setLabelProbesCount();
		ConfigurationProbesPanel.setLabelProbesFreq();
	}

	private void resetServerButtonListener(){
		serverResetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CommunicationPolyscopeServer.closeConnection();
				cps.interrupt();
				cps = new CommunicationPolyscopeServer();
			//	CommunicationPolyscopeServer.stop = true;
				CommunicationPolyscopeServer.connected = false;
				cps.start();
				//JOptionPane.showMessageDialog(new JFrame(), "Resetting the server.");
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						do {
							serverResetButton.setEnabled(false);
							serverResetButton.setText("Resetting...");
							repaint();
						} while(!CommunicationPolyscopeServer.connected);
						serverResetButton.setEnabled(true);
						serverResetButton.setText("Reset server");
						System.out.println("The server is reset.");
						
					}
				});
				t.start();
			}
		});
	}
	
	static void wrongConfigurationPopup(){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(new JFrame(), "Wrong configuration values!");
			}
		});
		t.start();
	}
	
	public static boolean isWrongConfiguration(){
		String pattern = "^([0-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9])$";
		Pattern pattern2 = Pattern.compile(pattern);
		Matcher matcher = pattern2.matcher(ConfigurationControllerOutputsPanel.getFieldDelayText());
		if (!matcher.matches()){
			ConfigurationControllerOutputsPanel.setDelayFieldText(ConfigurationControllerOutputsPanel.getPreviousDelayText());
	    	 return true;
		}
		
		pattern = "^([0-9]|[1-9][0-9]|100)$";
		pattern2 = Pattern.compile(pattern);
		matcher = pattern2.matcher(ConfigurationVelocityPanel.getFieldText());
		if (!matcher.matches()){
	    	 ConfigurationVelocityPanel.setFieldText(ConfigurationVelocityPanel.getPreviousVelocityText());
	    	 return true;
		}
		
		pattern = "^([0-9]|[1-9][0-9]|100)$";
		pattern2 = Pattern.compile(pattern);
		matcher = pattern2.matcher(ConfigurationAccelerationPanel.getFieldText());
		if (!matcher.matches()){
			ConfigurationAccelerationPanel.setFieldText(ConfigurationAccelerationPanel.getPreviousAccelerationText());
	    	 return true;
		}
		return false;
	}
}