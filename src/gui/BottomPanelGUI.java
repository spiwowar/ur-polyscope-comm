package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comm.CommunicationPolyscopeServer;
import comm.Configuration;
import comm.RealTimeClientInterface;

/* BOTTOM PANEL WITH 4 BUTTONS */

public class BottomPanelGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static JButton savePositionButton;
	private static JButton startSavingPositionsButton;
	private static JButton stopSavingPositionsButton;
	private static JButton startTrajectoryFromFileButton;
	private static JButton startTrajectoryAndSavingButton;
	private static boolean isClickedStartTrajectoryButton = false;
	private static boolean isClickedStartTrajectoryAndSavingButton = false;
	public static boolean saving = true;
	    
	public BottomPanelGUI(){
		initComponents();
	}
	
	/* Initiation of class components */
	private void initComponents(){

		setLayout(new GridBagLayout());
		
		savePositionButton = new JButton("Save actual position");
		startSavingPositionsButton = new JButton("Start saving positions");
		stopSavingPositionsButton = new JButton("Stop saving positions");
		startTrajectoryFromFileButton = new JButton("Start trajectory from file");
		startTrajectoryAndSavingButton = new JButton("Start trajectory with saving");
		
		stopSavingPositionsButton.setEnabled(false);
		startTrajectoryFromFileButton.setEnabled(false);
		startTrajectoryAndSavingButton.setEnabled(false);
		
		GridBagConstraints gridBagConstraints;
		
		/* ADDING FOUR BUTTONS */
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(savePositionButton, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(startSavingPositionsButton, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(stopSavingPositionsButton, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(startTrajectoryFromFileButton, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(startTrajectoryAndSavingButton, gridBagConstraints);
        
        saveCurrentPositionListener(); // Listener for Save current position button
        startSavingPositionsListener(); // Listener for Start saving positions button
        startTrajectoryFromFileListener();
        startTrajectoryAndSavingListener();
	}
	
	public static void enableTrajectoryFromFileButton(boolean enabled){
		if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
			startTrajectoryFromFileButton.setEnabled(enabled);
		}
	}

	public static void enableStopSavingPositionsButton(boolean enabled){
		if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
			stopSavingPositionsButton.setEnabled(enabled);
		}
	}
	
	public static void enableStartSavingPositionsButton(boolean enabled){
		startSavingPositionsButton.setEnabled(enabled);
	}
	
	public static void enableStartTrajectoryAndSavingButton(boolean enabled){
		startTrajectoryAndSavingButton.setEnabled(enabled);
	}
	
	public static boolean getEnabledTrajectoryFromFileButton(){
		return startTrajectoryFromFileButton.isEnabled();
	}

	public static boolean getEnabledStopSavingPositionsButton(){
		return stopSavingPositionsButton.isEnabled();
	}
	
	public static boolean getEnabledStartSavingPositionsButton(){
		return startSavingPositionsButton.isEnabled();
	}
	
	public static boolean getEnabledStartTrajectoryAndSavingButton(){
		return startTrajectoryAndSavingButton.isEnabled();
	}
	
	/* Method that listens for button and save to file the actual position */
	
	private void saveCurrentPositionListener(){
		savePositionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
				Date date = new Date();
				File file;
				if (Configuration.getDirectoryText().equals("user.home"))
					file = new File(System.getProperty("user.home") + "\\actualPosition_" + dateFormat.format(date) + ".csv");
				else
					file = new File(Configuration.getDirectoryText() + "\\actualPosition_" + dateFormat.format(date) + ".csv");
				FileWriter fileWriter;
				try {
					fileWriter = new FileWriter(file);
					fileWriter.write("timestamp;qa_1;qa_2;qa_3;qa_4;qa_5;qa_6;qt_1;qt_2;qt_3;qt_4;qt_5;qt_6;x;y;z;rx;ry;rz;qda_1;qda_2;qda_3;qda_4;qda_5;qda_6;qdt_1;qdt_2;qdt_3;qdt_4;qdt_5;qdt_6;qd_x;qd_y;qd_z;qd_rx;qd_ry;qd_rz;qddt_1;qddt_2;qddt_3;qddt_4;qddt_5;qddt_6;qdd_x;qdd_y;qdd_z;qca_1;qca_2;qca_3;qca_4;qca_5;qca_6;qct_1;qct_2;qct_3;qct_4;qct_5;qct_6\n");
					String timestamp = RealTimeClientInterface.getTimestamp();
					String[] positionsActual = RealTimeClientInterface.getPositionsActual();
					String[] positionsTarget = RealTimeClientInterface.getPositionsTarget();
					String[] tcpPose = RealTimeClientInterface.getTcpPose();
					String[] velocitiesActual = RealTimeClientInterface.getSpeedsActual();
					String[] velocitiesTarget = RealTimeClientInterface.getSpeedsTarget();
					String[] tcpSpeed = RealTimeClientInterface.getTcpSpeed();
					String[] accelerationsTarget = RealTimeClientInterface.getAccTarget();
					String[] tcpAcc = RealTimeClientInterface.getTavs();
					String[] currentsActual = RealTimeClientInterface.getCurrentActual();
					String[] currentsTarget = RealTimeClientInterface.getCurrentTarget();
					if (timestamp == null || positionsActual == null || positionsTarget == null || tcpPose == null || velocitiesActual == null || velocitiesTarget == null || tcpSpeed == null || accelerationsTarget == null || tcpAcc == null || currentsActual == null || currentsTarget == null){
						JOptionPane.showMessageDialog(new JFrame(), "No connection with server");
						fileWriter.flush();
						fileWriter.close();
						if (file.delete())
							System.out.println("File deleted");
						else
							System.out.println("File not deleted");
					}
					else {
					fileWriter.write(timestamp.toString().trim() + ";");
					for (int i=0;i<positionsActual.length;i++){
						fileWriter.write(positionsActual[i].toString().trim() + ";");
					}
					for (int i=0;i<positionsTarget.length;i++){
						fileWriter.write(positionsTarget[i].toString().trim() + ";");
					}
					for (int i=0;i<tcpPose.length;i++){
						fileWriter.write(tcpPose[i].toString().trim() + ";");
					}
					for (int i=0;i<velocitiesActual.length;i++){
						fileWriter.write(velocitiesActual[i].toString().trim() + ";");
					}
					for (int i=0;i<velocitiesTarget.length;i++){
						fileWriter.write(velocitiesTarget[i].toString().trim() + ";");
					}
					for (int i=0;i<tcpSpeed.length;i++){
						fileWriter.write(tcpSpeed[i].toString().trim() + ";");
					}
					for (int i=0;i<accelerationsTarget.length;i++){
						fileWriter.write(accelerationsTarget[i].toString().trim() + ";");
					}
					for (int i=0;i<tcpAcc.length;i++){
						fileWriter.write(tcpAcc[i].toString().trim() + ";");
					}
					for (int i=0;i<currentsActual.length;i++){
						fileWriter.write(currentsActual[i].toString().trim() + ";");
					}
					for (int i=0;i<currentsTarget.length;i++){
						if (i<(currentsTarget.length-1))
							fileWriter.write(currentsTarget[i].toString().trim() + ";");
						else
							fileWriter.write(currentsTarget[i].toString().trim() + "\n");
					}
				fileWriter.flush();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	/* Method that listens for button and keep on saving to file the actual position */
	private void startSavingPositionsListener(){
		startSavingPositionsButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				enableStartSavingPositionsButton(false);
				enableStopSavingPositionsButton(true);
				startSavingPositions();
			}
		});
	}
	
	/* Method that stops saving positions - listener for stop saving positions button */
	private static void stopSavingPositionsListener(){
		stopSavingPositionsButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enableStartSavingPositionsButton(true);
				enableStopSavingPositionsButton(false);
				saving = false;
			}
		});
	}
	
	private void startTrajectoryFromFileListener(){
		startTrajectoryFromFileButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ConfigurationGUI.isWrongConfiguration()){
					ConfigurationGUI.wrongConfigurationPopup();
				}
				else {
					enableTrajectoryFromFileButton(false);
					enableStartTrajectoryAndSavingButton(false);
					TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(false);
					isClickedStartTrajectoryButton(true);
					if (!CommunicationPolyscopeServer.connected){
						CommunicationPolyscopeServer.closedConnection();
						isClickedStartTrajectoryButton(false);
						TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(true);
						if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
							enableTrajectoryFromFileButton(true);
							enableStartTrajectoryAndSavingButton(true);
						}
					}
				}
			}
		});
	}
	
	private void startTrajectoryAndSavingListener(){
		startTrajectoryAndSavingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ConfigurationGUI.isWrongConfiguration()){
					ConfigurationGUI.wrongConfigurationPopup();
				}
				else{
					enableStartSavingPositionsButton(false);
					enableStopSavingPositionsButton(false);
					enableTrajectoryFromFileButton(false);
					enableStartTrajectoryAndSavingButton(false);
					isClickedStartTrajectoryAndSavingButton(true);
					TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(false);
					if (!CommunicationPolyscopeServer.connected){
						CommunicationPolyscopeServer.closedConnection();
						isClickedStartTrajectoryAndSavingButton(false);
						TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(true);
						if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
							enableTrajectoryFromFileButton(true);
							enableStartTrajectoryAndSavingButton(true);
						}
					}
				}
			}
		});
	}
	
	public static void isClickedStartTrajectoryButton(boolean b){
		isClickedStartTrajectoryButton = b;
	}
	
	public static boolean getClickedStartTrajectoryButtonStatus(){
		return isClickedStartTrajectoryButton;
	}
	
	public static void isClickedStartTrajectoryAndSavingButton(boolean b){
		isClickedStartTrajectoryAndSavingButton = b;
	}
	
	public static boolean getClickedStartTrajectoryAndSavingButtonStatus(){
		return isClickedStartTrajectoryAndSavingButton;
	}
	
	static File file;
	static FileWriter fileWriter = null;
	
	public static void flushFile(){
		try {
			fileWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static FileWriter getFileWriter(){
		return fileWriter;
	}
	
	public static void startSavingPositions(){
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				Integer count = 0;
				DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
				Date date = new Date();
				
				if (Configuration.getDirectoryText().equals("user.home"))
					file = new File(System.getProperty("user.home") + "\\actualPositions_" + dateFormat.format(date) + ".csv");
				else
					file = new File(Configuration.getDirectoryText() + "\\actualPositions_" + dateFormat.format(date) + ".csv");
				
				stopSavingPositionsListener();
				try {
					saving=true;
					fileWriter = new FileWriter(file);
					fileWriter.write("timestamp;qa_1;qa_2;qa_3;qa_4;qa_5;qa_6;qt_1;qt_2;qt_3;qt_4;qt_5;qt_6;x;y;z;rx;ry;rz;qda_1;qda_2;qda_3;qda_4;qda_5;qda_6;qdt_1;qdt_2;qdt_3;qdt_4;qdt_5;qdt_6;qd_x;qd_y;qd_z;qd_rx;qd_ry;qd_rz;qddt_1;qddt_2;qddt_3;qddt_4;qddt_5;qddt_6;qdd_x;qdd_y;qdd_z;qca_1;qca_2;qca_3;qca_4;qca_5;qca_6;qct_1;qct_2;qct_3;qct_4;qct_5;qct_6\n");
					String timestamp;
					String[] positionsActual;
					String[] positionsTarget;
					String[] tcpPose;
					String[] velocitiesActual;
					String[] velocitiesTarget;
					String[] tcpSpeed;
					String[] accelerationsTarget;
					String[] tcpAcc;
					String[] currentsActual;
					String[] currentsTarget;
					Integer probesFreq = (1000/(125/(Integer.parseInt(ConfigurationProbesPanel.getProbesFreqText()))));
					long timeInMs;
					while(saving){
						timeInMs = System.currentTimeMillis();
						count++;
						timestamp = RealTimeClientInterface.getTimestamp();
						positionsActual = RealTimeClientInterface.getPositionsActual();
						positionsTarget = RealTimeClientInterface.getPositionsTarget();
						tcpPose = RealTimeClientInterface.getTcpPose();
						velocitiesActual = RealTimeClientInterface.getSpeedsActual();
						velocitiesTarget = RealTimeClientInterface.getSpeedsTarget();
						tcpSpeed = RealTimeClientInterface.getTcpSpeed();
						accelerationsTarget = RealTimeClientInterface.getAccTarget();
						tcpAcc = RealTimeClientInterface.getTavs();
						currentsActual = RealTimeClientInterface.getCurrentActual();
						currentsTarget = RealTimeClientInterface.getCurrentTarget();
						if (timestamp == null || positionsActual == null || positionsTarget == null || tcpPose == null || velocitiesActual == null || velocitiesTarget == null || tcpSpeed == null || accelerationsTarget == null || tcpAcc == null || currentsActual == null || currentsTarget == null){
							JOptionPane.showMessageDialog(new JFrame(), "No connection with server");
							enableStartSavingPositionsButton(true);
							enableStopSavingPositionsButton(false);
							fileWriter.flush();
							fileWriter.close();
							if (file.delete())
								System.out.println("File deleted");
							else
								System.out.println("File not deleted");
							break;
						}
						else {
						fileWriter.write(timestamp.toString().trim() + ";");
						for (int i=0;i<positionsActual.length;i++){
							fileWriter.write(positionsActual[i].toString().trim() + ";");
						}
						for (int i=0;i<positionsTarget.length;i++){
							fileWriter.write(positionsTarget[i].toString().trim() + ";");
						}
						for (int i=0;i<tcpPose.length;i++){
							fileWriter.write(tcpPose[i].toString().trim() + ";");
						}
						for (int i=0;i<velocitiesActual.length;i++){
							fileWriter.write(velocitiesActual[i].toString().trim() + ";");
						}
						for (int i=0;i<velocitiesTarget.length;i++){
							fileWriter.write(velocitiesTarget[i].toString().trim() + ";");
						}
						for (int i=0;i<tcpSpeed.length;i++){
							fileWriter.write(tcpSpeed[i].toString().trim() + ";");
						}
						for (int i=0;i<accelerationsTarget.length;i++){
							fileWriter.write(accelerationsTarget[i].toString().trim() + ";");
						}
						for (int i=0;i<tcpAcc.length;i++){
							fileWriter.write(tcpAcc[i].toString().trim() + ";");
						}
						for (int i=0;i<currentsActual.length;i++){
							fileWriter.write(currentsActual[i].toString().trim() + ";");
						}
						for (int i=0;i<currentsTarget.length;i++){
							if (i<(currentsTarget.length-1))
								fileWriter.write(currentsTarget[i].toString().trim() + ";");
							else
								fileWriter.write(currentsTarget[i].toString().trim() + "\n");
						}
					}
					while ((System.currentTimeMillis()-timeInMs)<probesFreq){
					}
					if (Integer.parseInt(ConfigurationProbesPanel.getProbesCountText())==0){
						fileWriter.flush();
						count=0;
					}
					else if (count==(Integer.parseInt(ConfigurationProbesPanel.getProbesCountText()))){
						enableStartSavingPositionsButton(true);
						enableStopSavingPositionsButton(false);
						saving = false;
						count=0;
						fileWriter.flush();
					}
					}
					fileWriter.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		t.start();
	}
	
	public static void stopSavingPositions(){
		saving = false;
	}
}