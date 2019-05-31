package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* TAV PANEL IN READINGS TAB */

public class ReadingsTAVPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel x;
	private JLabel y;
	private JLabel z;
	private JLabel TAV;
	
	private static JLabel[] tavs = new JLabel[3];
	
	public ReadingsTAVPanel(){
		initComponents();
	}
	
	/*
	 * Initiation of class components
	 */
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Tool Acc. Values Readings [m/s2]"));
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;
		x = new JLabel("X");
		y = new JLabel("Y");
		z = new JLabel("Z");

		TAV = new JLabel("TAV Values");
		
		for (int j=0;j<3;j++){
			tavs[j] = new JLabel("0");
		}
		
		/* 
		 * ADDING X, Y AND Z LABELS
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
		add(TAV, gridBagConstraints);
		
        /* 
         * TOOL ACCELEROMETER VALUES
         */

		for (int j=0;j<3;j++){
			gridBagConstraints = new GridBagConstraints();
		    gridBagConstraints.gridx = j+1;
		    gridBagConstraints.gridy = 1;
		    gridBagConstraints.ipadx = 25;
		    gridBagConstraints.ipady = 0;
		    gridBagConstraints.anchor = GridBagConstraints.CENTER;
		    gridBagConstraints.weightx = 0.5;
		    gridBagConstraints.weighty = 0.5;
		    add(tavs[j], gridBagConstraints);
		    
		}
	}
	
	public static String[] getTAV(){
		String[] acc = new String[3];
		for (int i=0;i<3;i++){
			acc[i] = tavs[i].getText();
		}
		return acc;
	}
	
	public static void setTAV(String[] tavsActual){
		for (int i=0;i<3;i++){
			tavs[i].setText(tavsActual[i]);
		}
	}
}