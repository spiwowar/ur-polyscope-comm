package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;

/* POSITION READINGS PANEL IN TRAJECTORY FROM FILE TAB */

public class BorderedPositionFromFilePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel x;
	private static JLabel y;
	private static JLabel z;
	private static JLabel rx;
	private static JLabel ry;
	private static JLabel rz;
	private JLabel no;
	private JLabel[] numbers;
	private static JLabel[][] positions;
	
	private static boolean isLoading = false;
	
	public BorderedPositionFromFilePanel(){
		initComponents();
	}
	
	/*
	 * Initiation of class components
	 */

	private void initComponents(){
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(500,250));
		GridBagConstraints gridBagConstraints;
		x = new JLabel("X");
		y = new JLabel("Y");
		z = new JLabel("Z");
		rx = new JLabel("RX");
		ry = new JLabel("RY");
		rz = new JLabel("RZ");
		no = new JLabel("No.");

		/* 
		 * ADDING JOINT LABELS
		 */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(no, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(x, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(y, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(z, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(rx, gridBagConstraints);
		 
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(ry, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(rz, gridBagConstraints);
        
   /*     ArrayList<String> list = new ArrayList<String>();
        for (int i=0;i<6;i++)
        	list.add("0");
		/* Adding the default position values - equal 0 */
    /*	addNewPositionLines(list, 1, new JLabel(), new JScrollPane());*/
		
	}
	
	public void setActualPositionValues(ArrayList<String> actualPositions, Integer lines, JLabel loading){
		BottomPanelGUI.enableStartTrajectoryAndSavingButton(false);
		BottomPanelGUI.enableTrajectoryFromFileButton(false);
		TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(false);
		addNewPositionLines(actualPositions, lines, loading);
	}
	
	/* Method that changes the view of values in panel regarding how many values there are */
	private void addNewPositionLines(final ArrayList<String> actualPositions, final Integer lines, final JLabel loading){
		isLoading = true;
		if (positions != null){
			for (int i=0;i<positions.length;i++){
				this.remove(numbers[i]);
				for (int j=0;j<positions[0].length;j++){
					this.remove(positions[i][j]);
				}
			}
		}
		if (TrajectoryFromFileGUI.getShowOnPanelSelection()){
		positions = new JLabel[lines][6];
		numbers = new JLabel[lines];
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int count=0;
				for (int i=0;i<lines;i++){
					numbers[i] = new JLabel(((Integer)(i+1)).toString());
					numbers[i].setVisible(false);
					numbers[i].setOpaque(false);
					GridBagConstraints gridBagConstraints = new GridBagConstraints();
					gridBagConstraints.gridx = 1;
					gridBagConstraints.gridy = i+1;
			        gridBagConstraints.ipadx = 50;
			        gridBagConstraints.ipady = 0;
			        gridBagConstraints.anchor = GridBagConstraints.NORTH;
			        gridBagConstraints.weightx = 0.5;
			        gridBagConstraints.weighty = 0.5;
			        add(numbers[i], gridBagConstraints);
					for (int j=0;j<6;j++){

						positions[i][j] = new JLabel(actualPositions.get(count++));
	
						positions[i][j].setVisible(false);
						positions[i][j].setOpaque(false);

				        
						gridBagConstraints.gridx = j+2;
						gridBagConstraints.gridy = i+1;
				        gridBagConstraints.ipadx = 50;
				        gridBagConstraints.ipady = 0;
				        gridBagConstraints.anchor = GridBagConstraints.NORTH;
				        gridBagConstraints.weightx = 0.5;
				        gridBagConstraints.weighty = 0.5;
				        add(positions[i][j], gridBagConstraints);

				        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
				        df.setMaximumFractionDigits(1);
				        if (i%150==0 || i==lines.SIZE-1)
				        	loading.setText("Loading: " + df.format((100.0/(((float)lines)/(i+1)))) + "%");

					}
				}

				loading.setText("Wait for refresh!");
				for (int i=0;i<lines;i++){
					numbers[i].setVisible(true);
					for (int j=0;j<6;j++){
						positions[i][j].setVisible(true);
					}
					if (i==20){
						revalidate();
					repaint();
					doLayout();
					}
				}
				revalidate();
				repaint();
				doLayout();
				loading.setText("Loaded " + lines + " positions.");
				BorderedPositionFromFilePanel.setLoadingStatus(false);
				if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
					BottomPanelGUI.enableStartTrajectoryAndSavingButton(true);
					BottomPanelGUI.enableTrajectoryFromFileButton(true);
				}
				TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(true);
			}
		});
		t.start();
		}
	}
	
	public static Integer getLinesCount(){
		return positions.length;
	}
	
	public static String[] getPositionsLine(Integer lineNo){
		String[] positionsLine = new String[6];
		for (int i=0;i<6;i++){
			positionsLine[i] = positions[lineNo][i].getText();
		}
		return positionsLine;
	}
	
	
	public static boolean getLoadingStatus(){
		return isLoading;
	}
	
	public static void setLoadingStatus(boolean isLoadingStatus){
		isLoading = isLoadingStatus;
	}
	
	public static void setLabelsValues(String[] labels){
		x.setText(labels[0]);
		y.setText(labels[1]);
		z.setText(labels[2]);
		rx.setText(labels[3]);
		ry.setText(labels[4]);
		rz.setText(labels[5]);
	}
}