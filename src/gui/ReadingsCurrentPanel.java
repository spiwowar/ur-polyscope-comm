package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* CURRENT PANEL IN READINGS TAB*/

public class ReadingsCurrentPanel extends JPanel{
	
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
	private JLabel currentCurrent;
	private JLabel targetCurrent;
	
	private static JLabel[][] currents = new JLabel[2][6];
	
	public ReadingsCurrentPanel(){
		initComponents();
	}
	
	/*
	 * Initiation of class components
	 */
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Current Readings [A]"));
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;
		base = new JLabel("Base");
		shoulder = new JLabel("Shoulder");
		elbow = new JLabel("Elbow");
		wrist1 = new JLabel("Wrist1");
		wrist2 = new JLabel("Wrist2");
		wrist3 = new JLabel("Wrist3");

		currentCurrent = new JLabel("Actual");
		targetCurrent = new JLabel("Target");
		
		for (int i=0;i<2;i++){
			for (int j=0;j<6;j++){
				currents[i][j] = new JLabel("0");
			}
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
	      * ADD CURRENT LABEL 
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
		add(currentCurrent, gridBagConstraints);
        
        /* 
         * ADD TARGET LABEL
         */
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets.bottom = 20;
		add(targetCurrent, gridBagConstraints);
		
        /* 
         * ADDING VALUES
         */
		
		for (int i=0;i<2;i++){
			for (int j=0;j<6;j++){
				gridBagConstraints = new GridBagConstraints();
		        gridBagConstraints.gridx = j+1;
		        gridBagConstraints.gridy = i+1;
		        gridBagConstraints.ipadx = 25;
		        gridBagConstraints.ipady = 0;
		        gridBagConstraints.anchor = GridBagConstraints.CENTER;
		        gridBagConstraints.weightx = 0.5;
		        gridBagConstraints.weighty = 0.5;
		        add(currents[i][j], gridBagConstraints);
			}
		}
	}
	
	public static void setTargetCurrentReadings(String[] targetCurrents){
		for (int i=0;i<6;i++){
			currents[1][i].setText(targetCurrents[i]);
		}
	}
	
	public static void setActualCurrentReadings(String[] actualCurrents){
		for (int i=0;i<6;i++){
			currents[0][i].setText(actualCurrents[i]);
		}
	}
}