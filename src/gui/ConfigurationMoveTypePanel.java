package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class ConfigurationMoveTypePanel extends JPanel {
	
	private static JRadioButton movel;
	private static JRadioButton movej;
	private static JRadioButton movep;
	private ButtonGroup moveGroup;
	
	public ConfigurationMoveTypePanel(){
		initComponents();
	}
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Move type"));
		setLayout(new GridBagLayout());
		
		movel = new JRadioButton("MOVEL");
		movej = new JRadioButton("MOVEJ");
		movep = new JRadioButton("MOVEP");
		moveGroup = new ButtonGroup();
		
		movel.setMnemonic(KeyEvent.VK_L);
		movej.setMnemonic(KeyEvent.VK_J);
		movep.setMnemonic(KeyEvent.VK_P);
		
		movel.setSelected(true);
		moveGroup.add(movel);
		moveGroup.add(movej);
		moveGroup.add(movep);
		
		GridBagConstraints gridBagConstraints;
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
		add(movel, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
		add(movej, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
		add(movep, gridBagConstraints);
	}
	
	public static String getMoveTypeValue(){
		if (movel.isSelected())
			return "MOVEL";
		else if (movep.isSelected())
			return "MOVEP";		
		else
			return "MOVEJ";
	}
	
	public static void setMoveTypeValue(String moveType){
		if (moveType.equals("MOVEL"))
			movel.setSelected(true);
		else if (moveType.equals("MOVEP"))
			movep.setSelected(true);	
		else if (moveType.equals("MOVEJ"))
			movej.setSelected(true);
	}
}