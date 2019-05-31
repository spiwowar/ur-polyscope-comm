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


public class ConfigurationVelocityPanel extends JPanel{
	
	private static JSlider sliderVelocity;
	private static JTextField fieldVelocity;
	private JLabel percentage;
	private static String previousVelocityText;
	
	
	public ConfigurationVelocityPanel(){
		initComponents();
	}
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Velocity"));
		setLayout(new GridBagLayout());
		
		fieldVelocity = new JTextField("50", 1);
		sliderVelocity = new JSlider(SwingConstants.HORIZONTAL, 1, 100, 100);
		percentage = new JLabel("%");
        previousVelocityText = fieldVelocity.getText();
		
		GridBagConstraints gridBagConstraints;
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 25;
		add(sliderVelocity, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 0;
		add(fieldVelocity, gridBagConstraints);
		
		gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
		add(percentage, gridBagConstraints);
		
        fieldVelocityListener(); // Listener for Velocity Field
        sliderVelocityListener(); // Listener for Velocity Slider
	}
	
	public static String getVelocityValue(){
		return ((Integer)sliderVelocity.getValue()).toString();
	}
	
	private void fieldVelocityListener(){
		final String pattern = "^([1-9]|[1-9][0-9]|100)$";
	    final Pattern pattern2 = Pattern.compile(pattern);
        fieldVelocity.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				 Matcher matcher = pattern2.matcher(fieldVelocity.getText());
			     if (!matcher.matches()){
			    	 fieldVelocity.setText(previousVelocityText);
				}
			     else{
					Integer velocityValue = Integer.parseInt(fieldVelocity.getText());
					sliderVelocity.setValue(velocityValue);
					previousVelocityText = fieldVelocity.getText();	 
			     }	 
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				 Matcher matcher = pattern2.matcher(fieldVelocity.getText());
			     if (!matcher.matches()){
			    	 fieldVelocity.setText(previousVelocityText);
				}
			     else{
					Integer velocityValue = Integer.parseInt(fieldVelocity.getText());
					sliderVelocity.setValue(velocityValue);
					previousVelocityText = fieldVelocity.getText();	 
			     }	 
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				 Matcher matcher = pattern2.matcher(fieldVelocity.getText());
			     if (!matcher.matches()){
			    	 fieldVelocity.setText(previousVelocityText);
				}
			     else{
					Integer velocityValue = Integer.parseInt(fieldVelocity.getText());
					sliderVelocity.setValue(velocityValue);
					previousVelocityText = fieldVelocity.getText();	 
			     }	 
			}
			
        });
	}
	
	private void sliderVelocityListener(){
		sliderVelocity.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				fieldVelocity.setText(new Integer(sliderVelocity.getValue()).toString());
			}	
        });
	}
	
	public static void setSliderValue(Integer value){
		sliderVelocity.setValue(value);
	}
	
	public static void setFieldText(String text){
		fieldVelocity.setText(text);
	}
	
	public static String getFieldText(){
		return fieldVelocity.getText();
	}
	
	public static String getPreviousVelocityText(){
		return previousVelocityText;
	}
}