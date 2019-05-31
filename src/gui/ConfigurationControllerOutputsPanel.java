package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ConfigurationControllerOutputsPanel extends JPanel{
	
private static String previousDelayText;
	
	private JLabel delay;
	private static JTextField fieldDelay;
	private JLabel ms;	
	
	private JLabel digitalOutputs;
	private JLabel highLow;
	
	private JLabel zero;
	private JLabel one;
	private JLabel two;
	private JLabel three;
	private JLabel four;
	private JLabel five;
	private JLabel six;
	private JLabel seven;
	
	private static JCheckBox zeroCB;
	private static JCheckBox oneCB;
	private static JCheckBox twoCB;
	private static JCheckBox threeCB;
	private static JCheckBox fourCB;
	private static JCheckBox fiveCB;
	private static JCheckBox sixCB;
	private static JCheckBox sevenCB;
	
	public ConfigurationControllerOutputsPanel(){
		initComponents();
	}
	
	private void initComponents(){
		delay = new JLabel("Delay");
		fieldDelay = new JTextField("10", 1);
		ms = new JLabel("ms");

		setBorder(BorderFactory.createTitledBorder("Controller Output"));
		setLayout(new GridBagLayout());
		
		digitalOutputs = new JLabel("Digital Outputs");
		highLow = new JLabel("H/L");
		zero = new JLabel("0");
		one = new JLabel("1");
		two = new JLabel("2");
		three = new JLabel("3");
		four = new JLabel("4");
		five = new JLabel("5");
		six = new JLabel("6");
		seven = new JLabel("7");
		zeroCB = new JCheckBox();
		oneCB = new JCheckBox();
		twoCB = new JCheckBox();
		threeCB = new JCheckBox();
		fourCB = new JCheckBox();
		fiveCB = new JCheckBox();
		sixCB = new JCheckBox();
		sevenCB = new JCheckBox();

        previousDelayText = fieldDelay.getText();
		
		GridBagConstraints gridBagConstraints;
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.5;
		add(digitalOutputs, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets.bottom = 20;
		add(highLow, gridBagConstraints);
		
		/* 
		 * ADDING 0-7 LABELS
		 */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(zero, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(one, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(two, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(three, gridBagConstraints);
		 
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(four, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(five, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(six, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(seven, gridBagConstraints);
		
        /* 
         * ADDING CHECKBOXES
         */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(zeroCB, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(oneCB, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(twoCB, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(threeCB, gridBagConstraints);
		 
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(fourCB, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(fiveCB, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(sixCB, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(sevenCB, gridBagConstraints);
        
		/* 
		 * ADDING DELAY TO CONTROLLER OUTPUTS PANEL
		 */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(delay, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(fieldDelay, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(ms, gridBagConstraints);
        
        fieldDelayListener(); // Listener for Delay Field
	}
	
	public static String getDelayValue(){
		return fieldDelay.getText().toString();
	}
	
	private void fieldDelayListener(){
		final String pattern = "^([0-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9])$";
	    final Pattern pattern2 = Pattern.compile(pattern);
        fieldDelay.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldDelay.getText());
				if (!matcher.matches()){
					fieldDelay.setText(previousDelayText);
				}
				else {
					previousDelayText = fieldDelay.getText();
				}
			
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldDelay.getText());
				if (!matcher.matches()){
					fieldDelay.setText(previousDelayText);
				}
				else {
					previousDelayText = fieldDelay.getText();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldDelay.getText());
				if (!matcher.matches()){
					fieldDelay.setText(previousDelayText);
				}
				else {
					previousDelayText = fieldDelay.getText();
				}
		}
        });
	}
	
	public static ArrayList<Integer> getDigitalOutputsValuesON(){
		ArrayList<Integer> outputs = new ArrayList<Integer>();
			if (zeroCB.isSelected())
				outputs.add(0);
			if (oneCB.isSelected())
				outputs.add(1);
			if (twoCB.isSelected())
				outputs.add(2);
			if (threeCB.isSelected())
				outputs.add(3);
			if (fourCB.isSelected())
				outputs.add(4);
			if (fiveCB.isSelected())
				outputs.add(5);
			if (sixCB.isSelected())
				outputs.add(6);
			if (sevenCB.isSelected())
				outputs.add(7);
			return outputs;
	}
	
	public static ArrayList<Integer> getDigitalOutputsValuesOFF(){
		ArrayList<Integer> outputs = new ArrayList<Integer>();
			if (!zeroCB.isSelected())
				outputs.add(0);
			if (!oneCB.isSelected())
				outputs.add(1);
			if (!twoCB.isSelected())
				outputs.add(2);
			if (!threeCB.isSelected())
				outputs.add(3);
			if (!fourCB.isSelected())
				outputs.add(4);
			if (!fiveCB.isSelected())
				outputs.add(5);
			if (!sixCB.isSelected())
				outputs.add(6);
			if (!sevenCB.isSelected())
				outputs.add(7);
			return outputs;
	}
	
	public static void setDelayFieldText(String text){
		fieldDelay.setText(text);
	}
	
	public static void setValuesON(Integer i){
		switch(i){
		case 0: zeroCB.setSelected(true); break;
		case 1: oneCB.setSelected(true); break;
		case 2: twoCB.setSelected(true); break;
		case 3: threeCB.setSelected(true); break;
		case 4: fourCB.setSelected(true); break;
		case 5: fiveCB.setSelected(true); break;
		case 6: sixCB.setSelected(true); break;
		case 7: sevenCB.setSelected(true); break;
		}
	}
	
	public static void setValuesOFF(Integer i){
		switch(i){
		case 0: zeroCB.setSelected(false); break;
		case 1: oneCB.setSelected(false); break;
		case 2: twoCB.setSelected(false); break;
		case 3: threeCB.setSelected(false); break;
		case 4: fourCB.setSelected(false); break;
		case 5: fiveCB.setSelected(false); break;
		case 6: sixCB.setSelected(false); break;
		case 7: sevenCB.setSelected(false); break;
		}
	}
	
	public static String getPreviousDelayText(){
		return previousDelayText;
	}
	
	public static String getFieldDelayText(){
		return fieldDelay.getText();
	}
}