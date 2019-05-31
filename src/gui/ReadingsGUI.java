package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;


public class ReadingsGUI extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadingsPositionPanel borderPositionPanel;
	private JPanel borderVelocityPanel;
	private JPanel borderAccelerationPanel;
	private JPanel borderCurrentPanel;
	private JPanel borderTAVPanel;
	private JPanel borderTCPPanel;

	public ReadingsGUI() {
		initComponents();
	}
	
	private void initComponents(){
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;
		
		borderPositionPanel = new ReadingsPositionPanel();
		borderVelocityPanel = new ReadingsVelocityPanel();
		borderAccelerationPanel = new ReadingsAccelerationPanel();
		borderCurrentPanel = new ReadingsCurrentPanel();
		borderTAVPanel = new ReadingsTAVPanel();
		borderTCPPanel = new ReadingsTCPPanel();

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 0.5;
		add(borderPositionPanel, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 0.5;
		add(borderVelocityPanel, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 0.5;
		add(borderAccelerationPanel, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 0.5;
		add(borderCurrentPanel, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 0.5;
		add(borderTCPPanel, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 0.5;
		add(borderTAVPanel, gridBagConstraints);
	}
}