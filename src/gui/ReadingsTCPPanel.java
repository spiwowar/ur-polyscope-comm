package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* TCP PANEL IN READINGS TAB */

public class ReadingsTCPPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel x;
	private JLabel y;
	private JLabel z;
	private JLabel rx;
	private JLabel ry;
	private JLabel rz;
	
	private JLabel currentTCPSpeed;
	private JLabel currentTCPPose;
	
	private static JLabel[] tcpPose = new JLabel[6];
	private static JLabel[] tcpSpeed = new JLabel[6];
	
	public ReadingsTCPPanel(){
		initComponents();
	}
	
	/*
	 * Initiation of class components
	 */
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("TCP Readings [m], [rad] | [m/s], [rad/s]"));
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;
		x = new JLabel("X");
		y = new JLabel("Y");
		z = new JLabel("Z");
		rx = new JLabel("RX");
		ry = new JLabel("RY");
		rz = new JLabel("RZ");
		
		currentTCPSpeed = new JLabel("Speed");
		currentTCPPose = new JLabel("Actual TCP Pose");
		
		for (int j=0;j<6;j++){
			tcpPose[j] = new JLabel("0");
			tcpSpeed[j] = new JLabel("0");
		}
		
		/* 
		 * ADDING POSES LABELS
		 */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(x, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(y, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(z, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(rx, gridBagConstraints);
		 
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(ry, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(rz, gridBagConstraints);
        
        
        /* 
         * ADDING CURRENT POSE LABEL
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
		add(currentTCPPose, gridBagConstraints);
		
        /* 
         * ADDING VALUES VALUES
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
		    add(tcpPose[j], gridBagConstraints);
		}

		/* 
		 * ADDING SPEED LABEL
		 */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(currentTCPSpeed, gridBagConstraints);
        
        /* 
         * ADDING SPEED VALUES
         */
        
		for (int j=0;j<6;j++){
			gridBagConstraints = new GridBagConstraints();
		    gridBagConstraints.gridx = j+1;
		    gridBagConstraints.gridy = 2;
		    gridBagConstraints.ipadx = 25;
		    gridBagConstraints.ipady = 0;
		    gridBagConstraints.anchor = GridBagConstraints.CENTER;
		    gridBagConstraints.weightx = 0.5;
		    gridBagConstraints.weighty = 0.5;
		    add(tcpSpeed[j], gridBagConstraints);
		}
	}

	public static String[] getTcpPose(){
		String[] position = new String[6];
		for (int i=0;i<6;i++){
			position[i] = tcpPose[i].getText();
		}
		return position;
	}
	
	public static String[] getTcpSpeed(){
		String[] speed = new String[6];
		for (int i=0;i<6;i++){
			speed[i] = tcpSpeed[i].getText();
		}
		return speed;
	}
	
	public static void setActualTCPPose(String[] actualTCPPose){
		for (int i=0;i<6;i++){
			tcpPose[i].setText(actualTCPPose[i]);
		}
	}
	
	public static void setActualTCPSpeed(String[] actualTCPSpeed){
		for (int i=0;i<6;i++){
			tcpSpeed[i].setText(actualTCPSpeed[i]);
		}
	}
}