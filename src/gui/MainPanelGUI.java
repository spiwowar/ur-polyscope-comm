package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MainPanelGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTabbedPane mainTabbedGUI;
	private JPanel bottomPanelGUI;
	    
	public MainPanelGUI(){
		initComponents();
	}
	
	private void initComponents(){

		mainTabbedGUI = new MainTabbedGUI();
		bottomPanelGUI = new BottomPanelGUI();
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(JFrame.WIDTH, 50));

		GridBagConstraints gridBagConstraints;
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(mainTabbedGUI, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.2;
        add(bottomPanelGUI, gridBagConstraints);
	}
}