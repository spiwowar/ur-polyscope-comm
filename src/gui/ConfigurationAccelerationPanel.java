package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ConfigurationAccelerationPanel extends JPanel{
	
	private static String previousAccelerationText;
	private static JSlider sliderAcceleration;
	private static JTextField fieldAcceleration;
	private JLabel percentage;
	
	public ConfigurationAccelerationPanel(){
		initComponents();
	}
	
	private void initComponents(){

		setBorder(BorderFactory.createTitledBorder("Acceleration"));
		setLayout(new GridBagLayout());
		
		percentage = new JLabel("%");
		fieldAcceleration = new JTextField("0", 1);
		sliderAcceleration = new JSlider(SwingConstants.HORIZONTAL, 1, 100, 50);
		previousAccelerationText = fieldAcceleration.getText();
		
		GridBagConstraints gridBagConstraints;
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 25;
		add(sliderAcceleration, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 0;
		add(fieldAcceleration, gridBagConstraints);
		
		gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
		add(percentage, gridBagConstraints);
		
        fieldAccelerationListener(); // Listener for Acceleration Field
        sliderAccelerationListener(); // Listener for Acceleration Slider
	}
	
	public static String getAccelerationValue(){
		return fieldAcceleration.getText().toString();
	}
	
	private void fieldAccelerationListener(){
		final String pattern = "^([1-9]|[1-9][0-9]|100)$";
	    final Pattern pattern2 = Pattern.compile(pattern);
        fieldAcceleration.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldAcceleration.getText());
			     if (!matcher.matches()){
			    	 fieldAcceleration.setText(previousAccelerationText);
			     }
			     else {
			    	Integer accelerationValue = Integer.parseInt(fieldAcceleration.getText());
					sliderAcceleration.setValue(accelerationValue);
					previousAccelerationText = fieldAcceleration.getText();
			     }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldAcceleration.getText());
			     if (!matcher.matches()){
			    	 fieldAcceleration.setText(previousAccelerationText);
			     }
			     else {
			    	Integer accelerationValue = Integer.parseInt(fieldAcceleration.getText());
					sliderAcceleration.setValue(accelerationValue);
					previousAccelerationText = fieldAcceleration.getText();
			     }
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldAcceleration.getText());
			     if (!matcher.matches()){
			    	 fieldAcceleration.setText(previousAccelerationText);
			     }
			     else {
			    	Integer accelerationValue = Integer.parseInt(fieldAcceleration.getText());
					sliderAcceleration.setValue(accelerationValue);
					previousAccelerationText = fieldAcceleration.getText();
			     }
			}
        	
        });
	}
	
	private void sliderAccelerationListener(){
        sliderAcceleration.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				fieldAcceleration.setText(new Integer(sliderAcceleration.getValue()).toString());
			}	
        });
	}
	
	public static void setSliderValue(Integer value){
		sliderAcceleration.setValue(value);
	}
	
	public static void setFieldText(String text){
		fieldAcceleration.setText(text);
	}
	
	public static String getFieldText(){
		return fieldAcceleration.getText();
	}
	
	public static String getPreviousAccelerationText(){
		return previousAccelerationText;
	}
}