package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* VELOCITY PANEL IN READINGS TAB */

public class ReadingsVelocityPanel extends JPanel{
	
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
	private JLabel currentVelocity;
	private JLabel targetVelocity;
	
	private static JLabel[][] velocities = new JLabel[2][6];
	
	public ReadingsVelocityPanel(){
		initComponents();
	}
	
	/*
	 * Initiation of class components
	 */
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Velocity Readings [rad/s]"));
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;
		base = new JLabel("Base");
		shoulder = new JLabel("Shoulder");
		elbow = new JLabel("Elbow");
		wrist1 = new JLabel("Wrist1");
		wrist2 = new JLabel("Wrist2");
		wrist3 = new JLabel("Wrist3");

		currentVelocity = new JLabel("Actual");
		targetVelocity = new JLabel("Target");
		
		for (int i=0;i<2;i++){
			for (int j=0;j<6;j++){
				velocities[i][j] = new JLabel("0");
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
	      *  ADDING CURRENT LABEL
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
		add(currentVelocity, gridBagConstraints);
        
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
		add(targetVelocity, gridBagConstraints);
		
        /* 
         * VELOCITIES VALUES
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
		        add(velocities[i][j], gridBagConstraints);
			}
		}
	}
	
	public static String[] getActualVelocityReadings(){
		String[] velocity = new String[6];
		for (int i=0;i<6;i++){
			velocity[i] = velocities[0][i].getText();
		}
		return velocity;
	}
	
	public static String[] getTargetVelocityReadings(){
		String[] velocity = new String[6];
		for (int i=0;i<6;i++){
			velocity[i] = velocities[1][i].getText();
		}
		return velocity;
	}
	
	public static void setTargetVelocityReadings(String[] targetSpeed){
		for (int i=0;i<6;i++){
			velocities[1][i].setText(targetSpeed[i]);
		}
	}
	
	public static void setActualVelocityReadings(String[] actualSpeed){
		for (int i=0;i<6;i++){
			velocities[0][i].setText(actualSpeed[i]);
		}
	}
	
}