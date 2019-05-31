package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


/* ACCELERATION PANEL IN READINGS TAB*/

public class ReadingsAccelerationPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel base;
	private JLabel shoulder;
	private JLabel elbow;
	private JLabel wrist1;
	private JLabel wrist2;
	private JLabel wrist3;
	private JLabel targetAcceleration;
	
	private static JLabel[] accelerations = new JLabel[6];
	
	public ReadingsAccelerationPanel(){
		initComponents();
	}
	
	/*
	 * Initiation of class components
	 */
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Acceleration Readings [rad/s2]"));
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;
		base = new JLabel("Base");
		shoulder = new JLabel("Shoulder");
		elbow = new JLabel("Elbow");
		wrist1 = new JLabel("Wrist1");
		wrist2 = new JLabel("Wrist2");
		wrist3 = new JLabel("Wrist3");

		targetAcceleration = new JLabel("Target");
		
		for (int j=0;j<6;j++){
			accelerations[j] = new JLabel("0");
		}
		
		/* 
		 * ADDING JOINTS LABELS 
		 */
		 
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(base, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(shoulder, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(elbow, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(wrist1, gridBagConstraints);
		 
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(wrist2, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(wrist3, gridBagConstraints);
        
        
        /* 
         * ADDING TARGET LABEL
         */
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets.bottom = 20;
		add(targetAcceleration, gridBagConstraints);
		
        /* 
         * ADDING ACCELERATION TARGET VALUES
         */
		
		for (int j=0;j<6;j++){
			gridBagConstraints = new GridBagConstraints();
		    gridBagConstraints.gridx = j+1;
		    gridBagConstraints.gridy = 1;
		    gridBagConstraints.ipadx = 25;
		    gridBagConstraints.ipady = 0;
		    gridBagConstraints.anchor = GridBagConstraints.CENTER;
		    gridBagConstraints.weightx = 0.5;
		    gridBagConstraints.weighty = 0.5;
		    add(accelerations[j], gridBagConstraints);
		}
	}
	
	public static String[] getTargetAccelerationsReadings(){
		String[] acc = new String[6];
		for (int i=0;i<6;i++){
			acc[i] = accelerations[i].getText();
		}
		return acc;
	}
	
	public static void setTargetAccelerationsReadings(String[] actualAcceleration){
		for (int i=0;i<6;i++){
			accelerations[i].setText(actualAcceleration[i]);
		}
	}
}