package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



public class ConfigurationCoordinatePanel extends JPanel{
	
	private static JRadioButton carthesianRB;
	private static JRadioButton jointRB;
	private ButtonGroup coordinationGroup;
	
	public ConfigurationCoordinatePanel(){
		initComponents();
	}
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Coordinate"));
		setLayout(new GridBagLayout());
		
		carthesianRB = new JRadioButton("Carthesian");
		jointRB = new JRadioButton("Joint");
		coordinationGroup = new ButtonGroup();
		
		carthesianRB.setEnabled(false);
		jointRB.setEnabled(false);
		
		carthesianRB.setMnemonic(KeyEvent.VK_K);
		jointRB.setMnemonic(KeyEvent.VK_Z);
		
		carthesianRB.setSelected(true);
		coordinationGroup.add(carthesianRB);
		coordinationGroup.add(jointRB);
		
		GridBagConstraints gridBagConstraints;
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
		add(carthesianRB, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
		add(jointRB, gridBagConstraints);
	}
	
	public static String getCoordinateValue(){
		if (carthesianRB.isSelected())
			return "Carthesian";
		else 
			return "Joint";	
	}
	
	public static boolean getCarthesianSelection(){
		return carthesianRB.isSelected();
	}
	
	public static boolean getJointSelection(){
		return jointRB.isSelected();
	}
	
	public static void setCarthesianSelection(boolean enable){
		carthesianRB.setSelected(enable);
	}
	
	public static void setJointSelection(boolean enable){
		jointRB.setSelected(enable);
	}
	
	public static void setCoordinateValue(String coordinateType){
		if (coordinateType.equals("Carthesian"))
			carthesianRB.setSelected(true);
		else if (coordinateType.equals("Joint"))
			jointRB.setSelected(true);	
	}
}