package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import comm.Configuration;


public class TrajectoryFromFileGUI extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BorderedPositionFromFilePanel positionPanel;
	private static JButton readFileButton;
	
	private JPanel wayOfReadingPanel;
	private JLabel loadingTrajectory;
	
	private static JPanel valuesToReadPanel;
	private static JRadioButton jointsRB;
	private static JRadioButton tcpRB;
	
	private static JRadioButton incrementalRB;
	private static JRadioButton absoluteRB;
	private static JScrollPane scrollPane;
	private static JCheckBox showOnPanel;
	
	private static ArrayList<String> values;
	
	private ButtonGroup buttonGroup;
	private ButtonGroup buttonGroup2;
	private boolean wrongCSVFile = false;
	
	private static boolean isClickedReadFileButtonFirst = false;
	
	public TrajectoryFromFileGUI() {
		initComponents();
	}
	
	private void initComponents(){
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints;

		positionPanel = new BorderedPositionFromFilePanel();
		loadingTrajectory = new JLabel();
		showOnPanel = new JCheckBox("Show on panel");
		showOnPanel.setSelected(true);

		scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createTitledBorder("TCP Pose readings [m], [rad]"));
		scrollPane.setMinimumSize(new Dimension(650,250));
		scrollPane.setViewportView(positionPanel);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.fill = GridBagConstraints.SOUTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 0.5;
		add(scrollPane, gridBagConstraints);
		
		
		readFileButton = new JButton("Read trajectory from file");
		
		valuesToReadPanel = new JPanel();
		valuesToReadPanel.setBorder(BorderFactory.createTitledBorder("Values to read"));
		valuesToReadPanel.setLayout(new GridBagLayout());
		tcpRB = new JRadioButton("TCP Pose");
		jointsRB = new JRadioButton("Joints positions");
		buttonGroup2 = new ButtonGroup();
		tcpRB.setMnemonic(KeyEvent.VK_T);
		jointsRB.setMnemonic(KeyEvent.VK_J);
		if (ConfigurationCoordinatePanel.getCarthesianSelection()){
			tcpRB.setSelected(true);
		}
		else if (ConfigurationCoordinatePanel.getJointSelection()){
			jointsRB.setSelected(true);
		}
		buttonGroup2.add(tcpRB);
		buttonGroup2.add(jointsRB);
		
		wayOfReadingPanel = new JPanel();
		wayOfReadingPanel.setBorder(BorderFactory.createTitledBorder("Way of reading file"));
		wayOfReadingPanel.setLayout(new GridBagLayout());
		incrementalRB = new JRadioButton("Incremental");
		absoluteRB = new JRadioButton("Absolute");
		buttonGroup = new ButtonGroup();
		incrementalRB.setMnemonic(KeyEvent.VK_I);
		absoluteRB.setMnemonic(KeyEvent.VK_A);
		
		incrementalRB.setSelected(true);
		buttonGroup.add(incrementalRB);
		buttonGroup.add(absoluteRB);
		
		// WAY OF READING FILE
        gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
		wayOfReadingPanel.add(incrementalRB, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
		wayOfReadingPanel.add(absoluteRB, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(wayOfReadingPanel, gridBagConstraints);
        
        // VALUES TO READ
        
        gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
        valuesToReadPanel.add(tcpRB, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weighty = 0.5;
		valuesToReadPanel.add(jointsRB, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(valuesToReadPanel, gridBagConstraints);
        
        // buttons
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(readFileButton, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(showOnPanel, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(loadingTrajectory, gridBagConstraints);
        
        readFileButtonListener(); // Read file button listener
	}
	
	
	private void readFileButtonListener(){
		readFileButton.addActionListener(new ActionListener(){
		private JFileChooser fileChooser;
		private FileFilter filter = new FileNameExtensionFilter("CSV Files", new String ("csv"));
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Configuration.getDirectoryText().equals("user.home"))
					 fileChooser = new JFileChooser(System.getProperty("user.home"));
				else 
					fileChooser = new JFileChooser(Configuration.getDirectoryText());
				fileChooser.setApproveButtonText("Read trajectory from file");
				fileChooser.setDialogTitle("Read CSV file");
				fileChooser.setSize(JPanel.WIDTH, JPanel.HEIGHT);
				fileChooser.setBorder(BorderFactory.createEtchedBorder());
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.setFileFilter(filter);
				
				int status = fileChooser.showOpenDialog(null);

			    if (status == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					setPositionReadingsFromCSVFile(selectedFile);
					setReadButtonFirstOption(true);
			    }
				revalidate();
			}
		});
	}
	
	private void setPositionReadingsFromCSVFile(final File selectedFile){
		loadingTrajectory.setText("Loading file...");
		BottomPanelGUI.enableStartTrajectoryAndSavingButton(false);
		BottomPanelGUI.enableTrajectoryFromFileButton(false);
		TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(false);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Integer linesCount = 0;
				Path path = selectedFile.toPath();
				String[] split = null;
				ArrayList<String> splitAll = new ArrayList<String>();
				final String pattern = "^[-+]?[0-9]*.?[0-9]+([eE][-+]?[0-9]+)?$";
			    final Pattern pattern2 = Pattern.compile(pattern);
			    Matcher matcher = null;
			    Integer firstValue = null;
			    Integer secondValue = null;
			    Integer thirdValue = null;
			    Integer fourthValue = null;
			    Integer fifthValue = null;
			    Integer sixthValue = null;
			    String[] allValues = new String[6];
			    wrongCSVFile = false;

				try {
					List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
					Iterator<String> it = lines.iterator();
					split = it.next().toString().split(";");
					List<String> firstLine = Arrays.asList(split);
					try{
						if (getTCPSelection()){
							scrollPane.setBorder(BorderFactory.createTitledBorder("TCP Pose readings [m], [rad]"));
							BorderedPositionFromFilePanel.setLabelsValues(new String[]{
									"X", "Y", "Z", "RX", "RY", "RZ"
							});
							ConfigurationCoordinatePanel.setCarthesianSelection(true);
							firstValue = firstLine.indexOf("x");
							secondValue = firstLine.indexOf("y");
							thirdValue = firstLine.indexOf("z");
							fourthValue = firstLine.indexOf("rx");				
							fifthValue = firstLine.indexOf("ry");
							sixthValue = firstLine.indexOf("rz");
							if (firstValue == -1 || secondValue == -1 || thirdValue == -1 || fourthValue == -1 || fifthValue == -1 || sixthValue == -1)
								wrongCSVFile = true;
						}
						else if (getJointsSelection()){
							scrollPane.setBorder(BorderFactory.createTitledBorder("Joints readings [rad]"));
							BorderedPositionFromFilePanel.setLabelsValues(new String[]{
									"Q1", "Q2", "Q3", "Q4", "Q5", "Q6"
							});
							ConfigurationCoordinatePanel.setJointSelection(true);
							firstValue = firstLine.indexOf("qa_1");
							secondValue = firstLine.indexOf("qa_2");
							thirdValue = firstLine.indexOf("qa_3");
							fourthValue = firstLine.indexOf("qa_4");				
							fifthValue = firstLine.indexOf("qa_5");
							sixthValue = firstLine.indexOf("qa_6");
							if (firstValue == -1 || secondValue == -1 || thirdValue == -1 || fourthValue == -1 || fifthValue == -1 || sixthValue == -1)
								wrongCSVFile = true;
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
					if (wrongCSVFile){
						wrongCSVFile();
					}
					else {
						wrongCSVFile = false;
						while (it.hasNext()){
							split = it.next().toString().split(";");
							List<String> nextLine = Arrays.asList(split);
							allValues[0] = nextLine.get(firstValue).trim();
							allValues[1] = nextLine.get(secondValue).trim();
							allValues[2] = nextLine.get(thirdValue).trim();
							allValues[3] = nextLine.get(fourthValue).trim();
							allValues[4] = nextLine.get(fifthValue).trim();
							allValues[5] = nextLine.get(sixthValue).trim();
							for (int i=0;i<6;i++){
								matcher = pattern2.matcher(allValues[i]);
								if (!matcher.matches()){
									wrongCSVFile();
									return;
								}
								if (allValues[i].equals("")){
									wrongCSVFile();
									return;
								}
							}
							Collections.addAll(splitAll, allValues);
						}
						linesCount = lines.size()-1;
						positionPanel.setActualPositionValues(splitAll, linesCount, loadingTrajectory);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				values = splitAll;
				if (!getShowOnPanelSelection()){
					if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
						BottomPanelGUI.enableStartTrajectoryAndSavingButton(true);
						BottomPanelGUI.enableTrajectoryFromFileButton(true);
					}
					TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(true);
				}
				if (linesCount==1)
					loadingTrajectory.setText("Loaded " + linesCount + " position.");
				else
					loadingTrajectory.setText("Loaded " + linesCount + " positions.");
			}
		});
	}
	
	public static boolean getAbsoluteSelection(){
		return absoluteRB.isSelected();
	}
	
	public static boolean getIncrementalSelection(){
		return incrementalRB.isSelected();
	}
	
	public static boolean getTCPSelection(){
		return tcpRB.isSelected();
	}
	
	public static boolean getJointsSelection(){
		return jointsRB.isSelected();
	}
	
	public static boolean getReadTrajectoryFromFileButtonStatus(){
		return readFileButton.isEnabled();
	}
	
	public static void setReadTrajectoryFromFileButtonStatus(boolean b){
		readFileButton.setEnabled(b);
	}
	
	public void wrongCSVFile(){
		JOptionPane.showMessageDialog(this, (String)"Invalid CSV File", "Invalid CSV File", JOptionPane.ERROR_MESSAGE);
		TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(true);
		loadingTrajectory.setText("Invalid CSV File.");
	}
	
	public static boolean getShowOnPanelSelection(){
		return showOnPanel.isSelected();
	}
	
	public static String[] getPositions(Integer lines){
		String[] positionsLine = new String[6];
		for (int i=0;i<6;i++){
			positionsLine[i] = values.get((lines)*6+i);
		}
		return positionsLine;
	}
	
	public static void setReadButtonFirstOption(boolean read){
		isClickedReadFileButtonFirst = true;
	}
	
	public static boolean getReadButtonFirstOption(){
		return isClickedReadFileButtonFirst;
	}
	
}