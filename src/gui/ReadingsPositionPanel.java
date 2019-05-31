package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* POSITIONS PANEL IN READINGS TAB*/

public class ReadingsPositionPanel extends JPanel{
	
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
	
	private JLabel currentPosition;
	private JLabel targetPosition;
	
	private static JLabel[][] positions = new JLabel[2][6];
	
	public ReadingsPositionPanel(){
		initComponents();
	}
	
	/*
	 * Initiation of class components
	 */
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Position Readings [rad]"));
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;
		base = new JLabel("Base");
		shoulder = new JLabel("Shoulder");
		elbow = new JLabel("Elbow");
		wrist1 = new JLabel("Wrist1");
		wrist2 = new JLabel("Wrist2");
		wrist3 = new JLabel("Wrist3");

		currentPosition = new JLabel("Actual");
		targetPosition = new JLabel("Target");
		
		for (int i=0;i<2;i++){
			for (int j=0;j<6;j++){
				positions[i][j] = new JLabel("0");
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
		add(currentPosition, gridBagConstraints);
        
        /* 
         * ADDING TARGET LABEL
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
		add(targetPosition, gridBagConstraints);
		
        /* 
         * ADDING POSITIONS VALUES
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
		        add(positions[i][j], gridBagConstraints);
			}
		}
	}
	
	public static String[] getActualPositionReadings(){
		String[] position = new String[6];
		for (int i=0;i<6;i++){
			position[i] = positions[0][i].getText();
		}
		return position;
	}
	
	public static String[] getTargetPositionReadings(){
		String[] position = new String[6];
		for (int i=0;i<6;i++){
			position[i] = positions[1][i].getText();
		}
		return position;
	}
	
	public static void setTargetPositionReadings(String[] targetPositions){
		for (int i=0;i<6;i++){
			positions[1][i].setText(targetPositions[i]);
		}
	}
	
	public static void setActualPositionReadings(String[] actualPositions){
		for (int i=0;i<6;i++){
			positions[0][i].setText(actualPositions[i]);
		}
	}
}