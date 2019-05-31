package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ConfigurationProbesPanel extends JPanel{
	
	private static JSlider sliderProbesFreq;
	private static JTextField fieldProbesCount;
	private static JTextField fieldProbesFreq;
	private static JLabel labelProbesFreqInfo;
	private static JLabel labelProbesCountInfo;
	private static JLabel labelProbesFreq;
	private static JLabel labelProbesCount;
	private static JLabel seconds;
	private static String previousProbesFreqText;
	private static String previousProbesCountText;

	public ConfigurationProbesPanel(){
		initComponents();
	}
	
	private void initComponents(){
		setBorder(BorderFactory.createTitledBorder("Saving probes to file"));
		setLayout(new GridBagLayout());
		fieldProbesFreq = new JTextField("1", 1);
		fieldProbesCount = new JTextField("12000", 4);
		sliderProbesFreq = new JSlider(SwingConstants.HORIZONTAL, 1, 125, 1);
		labelProbesCountInfo = new JLabel("<html>How many (max)?<br/> 0 = no limit</html>");
		labelProbesFreqInfo = new JLabel("How often?");
		DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(0);
		labelProbesCount = new JLabel("Time: " + df.format((Integer.parseInt(fieldProbesCount.getText())*sliderProbesFreq.getValue())/125) + "s.");
        df.setMaximumFractionDigits(2);
		labelProbesFreq = new JLabel("Freq: " + df.format(125.0/sliderProbesFreq.getValue()) + "Hz.");
		previousProbesFreqText = fieldProbesFreq.getText();
		previousProbesCountText = fieldProbesCount.getText();
		seconds = new JLabel("s.");
		
		GridBagConstraints gridBagConstraints;
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 25;
		add(labelProbesFreqInfo, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 25;
		add(sliderProbesFreq, gridBagConstraints);
		
		gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 0;
		add(fieldProbesFreq, gridBagConstraints);
		
		gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 0;
		add(labelProbesFreq, gridBagConstraints);
		
        gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 25;
		add(labelProbesCountInfo, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 0;
		add(fieldProbesCount, gridBagConstraints);
		
		gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets.right = 0;
		add(labelProbesCount, gridBagConstraints);

		fieldFreqListener();
		sliderProbesFreqListener();
		fieldFreqListener();
		fieldProbesCountListener();
	}
	
	private void fieldFreqListener(){
		final String pattern = "^([1-9]|[1-9][0-9]|[1][0-1][0-9]|[1][2][0-5])$";
	    final Pattern pattern2 = Pattern.compile(pattern);
        fieldProbesFreq.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldProbesFreq.getText());
			     if (!matcher.matches()){
			    	 fieldProbesFreq.setText(previousProbesFreqText);
			     }
			     else {
			    	Integer probesFreqValue = Integer.parseInt(fieldProbesFreq.getText());
					sliderProbesFreq.setValue(probesFreqValue);
					previousProbesFreqText = fieldProbesFreq.getText();
			     }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldProbesFreq.getText());
			     if (!matcher.matches()){
			    	 fieldProbesFreq.setText(previousProbesFreqText);
			     }
			     else {
			    	Integer probesFreqValue = Integer.parseInt(fieldProbesFreq.getText());
					sliderProbesFreq.setValue(probesFreqValue);
					previousProbesFreqText = fieldProbesFreq.getText();
			     }
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				Matcher matcher = pattern2.matcher(fieldProbesFreq.getText());
			     if (!matcher.matches()){
			    	 fieldProbesFreq.setText(previousProbesFreqText);
			     }
			     else {
			    	Integer probesFreqValue = Integer.parseInt(fieldProbesFreq.getText());
					sliderProbesFreq.setValue(probesFreqValue);
					previousProbesFreqText = fieldProbesFreq.getText();
			     }
			}
        	
        });
	}
	
	private void sliderProbesFreqListener(){
		final DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(2);
        sliderProbesFreq.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				fieldProbesFreq.setText(new Integer(sliderProbesFreq.getValue()).toString());
		        df.setMaximumFractionDigits(2);
				labelProbesFreq.setText("Freq: " + df.format(125.0/sliderProbesFreq.getValue()) + "Hz.");
		        df.setMaximumFractionDigits(0);
				labelProbesCount.setText("Time: " + df.format((Integer.parseInt(fieldProbesCount.getText())*sliderProbesFreq.getValue())/125) + "s.");
			}	
        });
	}
	
	private void fieldProbesCountListener(){
		final String pattern = "^[0-9]{1,6}$";
	    final Pattern pattern2 = Pattern.compile(pattern);
		final DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(0);
	    fieldProbesCount.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				Matcher matcher = pattern2.matcher(fieldProbesCount.getText());
			     if (!matcher.matches()){
			    	 fieldProbesCount.setText(previousProbesCountText);
			     }
			     else if (fieldProbesCount.getText().equals("0")){
			    	 labelProbesCount.setText("Time: not specified");
			     }
			     else {
					previousProbesCountText = fieldProbesCount.getText();
					labelProbesCount.setText("Time: " + df.format((Integer.parseInt(fieldProbesCount.getText())*sliderProbesFreq.getValue())/125) + "s.");
			     }
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				Matcher matcher = pattern2.matcher(fieldProbesCount.getText());
			     if (!matcher.matches()){
			    	 fieldProbesCount.setText(previousProbesCountText);
			     }
			     else if (fieldProbesCount.getText().equals("0")){
			    	 labelProbesCount.setText("Time: not specified");
			     }
			     else {
					previousProbesCountText = fieldProbesCount.getText();
					labelProbesCount.setText("Time: " + df.format((Integer.parseInt(fieldProbesCount.getText())*sliderProbesFreq.getValue())/125) + "s.");
			     }
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				Matcher matcher = pattern2.matcher(fieldProbesCount.getText());
			     if (!matcher.matches()){
			    	 fieldProbesCount.setText(previousProbesCountText);
			     }
			     else if (fieldProbesCount.getText().equals("0")){
			    	 labelProbesCount.setText("Time: not specified");
			     }
			     else {
					previousProbesCountText = fieldProbesCount.getText();
					labelProbesCount.setText("Time: " + df.format((Integer.parseInt(fieldProbesCount.getText())*sliderProbesFreq.getValue())/125) + "s.");
			     }
			}
		});
	}
	
	public static void setLabelProbesFreq(){
		final DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(2);
        previousProbesCountText = fieldProbesCount.getText();
    	labelProbesCount.setText("Time: " + df.format((Integer.parseInt(fieldProbesCount.getText())*sliderProbesFreq.getValue())/125) + "s.");
	}
	
	public static void setLabelProbesCount(){
		final DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(0);
        previousProbesCountText = fieldProbesCount.getText();
    	labelProbesCount.setText("Time: " + df.format((Integer.parseInt(fieldProbesCount.getText())*sliderProbesFreq.getValue())/125) + "s.");
	}
	
	public static String getProbesFreqText(){
		return fieldProbesFreq.getText().toString().trim();
	}
	
	public static String getProbesCountText(){
		return fieldProbesCount.getText().toString().trim();
	}
	
	public static void setFieldProbesFreqText(String text){
		fieldProbesFreq.setText(text);
	}
	
	public static void setFieldProbesCountText(String text){
		fieldProbesCount.setText(text);
	}
	
	public static void setSliderProbesFreqValue(Integer value){
		sliderProbesFreq.setValue(value);
	}
}