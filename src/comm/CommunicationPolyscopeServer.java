package comm;

import gui.BorderedPositionFromFilePanel;
import gui.BottomPanelGUI;
import gui.ConfigurationAccelerationPanel;
import gui.ConfigurationControllerOutputsPanel;
import gui.ConfigurationCoordinatePanel;
import gui.ConfigurationMoveTypePanel;
import gui.ConfigurationVelocityPanel;
import gui.TrajectoryFromFileGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class CommunicationPolyscopeServer extends Thread{
	
	private static int portNumber = 30000;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static PrintWriter out;
	private static BufferedReader in;
	public static boolean stop = false;
	private static boolean serverDisconnected = false;
	public static boolean connected = false;
	private static boolean nextStep = false;
	public static boolean beforeConnecting = false;
	
	public void run() {
		try  {
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Created serverSocket");
			stop = false;

			
			while (true){ // listening for socket continuously
				try {
					beforeConnecting = true;
					clientSocket = serverSocket.accept();
				
				System.out.println("Accepted the client");
				if (clientSocket.isConnected()){
					if (!nextStep)
						connectedToClient();
					connected = true;
					serverDisconnected = false;
					beforeConnecting = false;
				}
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.US_ASCII));
				}
				catch(Exception e){
				}
				if (connected){
				//	Thread t = new Thread(new Runnable() {
					//	@Override
					//	public void run() {
							while(connected){
								nextStep = false;
								System.out.println("Connected to client: " + connected);
								String readingFromClient = null;
								try {
									readingFromClient = in.readLine();
									System.out.println(readingFromClient);
									if (BottomPanelGUI.getClickedStartTrajectoryButtonStatus()){
										sendPositions(in, out);
										BottomPanelGUI.saving = false;
										BottomPanelGUI.isClickedStartTrajectoryButton(false);
										TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(true);
										if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
											BottomPanelGUI.enableTrajectoryFromFileButton(true);
											BottomPanelGUI.enableStartTrajectoryAndSavingButton(true);
											BottomPanelGUI.enableStartSavingPositionsButton(true);
										}
									}
									else if (BottomPanelGUI.getClickedStartTrajectoryAndSavingButtonStatus()){
										BottomPanelGUI.startSavingPositions();
										sendPositions(in, out);
										BottomPanelGUI.stopSavingPositions();
										BottomPanelGUI.isClickedStartTrajectoryAndSavingButton(false);
										TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(true);
										if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
											BottomPanelGUI.enableTrajectoryFromFileButton(true);
											BottomPanelGUI.enableStartTrajectoryAndSavingButton(true);
											BottomPanelGUI.enableStartSavingPositionsButton(true);
										}
									}
									else if (readingFromClient != null && readingFromClient.equals("Control_MSG")){
										System.out.println("Get: Control_MSG");
										out.println("(1)");
										System.out.println("Sent: (1)");
										sendDigitalsON(in, out);
										sendDigitalsOFF(in, out);
									} else if (readingFromClient == null && connected && serverDisconnected){
										closedConnection();
									} else if (readingFromClient == null && connected){
										nextStep = true;
										break;
									}
								} catch (IOException e1) {
									System.out.println("Connection reset");
									if (!serverDisconnected)
									closedConnection();
								}
							}
						}
				//	});
				//	t.start();
			//	}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendDigitalsON(BufferedReader in, PrintWriter out){
		ArrayList<Integer> digitalON = ConfigurationControllerOutputsPanel.getDigitalOutputsValuesON();
		String on = new String();
		String reading = null;
		try {
			reading = in.readLine();
			if (reading == null && connected){
				if (!serverDisconnected)
					closedConnection();
			}
			System.out.println("Inside sendDigitalsON: reading -> " + reading);
		} catch (IOException e) {
			System.out.println("Connection reset");
			if (!serverDisconnected)
				closedConnection();
		}
		for (int i=0;i<digitalON.size();i++){
			if (i==0){
				on = "(";
			}
			if (i==digitalON.size()-1){
				on = on + digitalON.get(i).toString() + ")";
			}
			else{
				on = on + digitalON.get(i).toString() + ",";
			}
		}
		if (digitalON.size()==0)
			on = "(-2)";
		if ((reading != null) && reading.equals("GET_DIGITAL_OUTPUTS_ON")){
			System.out.println("Get: GET_DIGITAL_OUTPUTS_ON");
			out.println(on.toString());
			System.out.println("Sent: Digital Outputs ON");
		}
	}
	
	private void sendDigitalsOFF(BufferedReader in, PrintWriter out){
		ArrayList<Integer> digitalOFF = ConfigurationControllerOutputsPanel.getDigitalOutputsValuesOFF();
		String off = new String();
		String reading = null;
		try {
			reading = in.readLine();
			if (reading == null && connected){
				if (!serverDisconnected)
				closedConnection();
			}
			System.out.println("Inside sendDigitalsOFF: reading -> " + reading);
		} catch (IOException e) {
			System.out.println("Connection reset");
			if (!serverDisconnected)
				closedConnection();
		}
		for (int i=0;i<digitalOFF.size();i++){
			if (i==0){
				off = "(";
			}
			if (i==digitalOFF.size()-1){
				off = off + digitalOFF.get(i).toString() + ")";
			}
			else{
				off = off + digitalOFF.get(i).toString() + ",";
			}
		}
		if (digitalOFF.size()==0)
			off = "(-2)";
		if ((reading != null) && reading.equals("GET_DIGITAL_OUTPUTS_OFF")){
			System.out.println("Get: GET_DIGITAL_OUTPUTS_OFF");
			out.println(off.toString());
			System.out.println("Sent: Digital Outputs OFF");
		}

	}
	
	static void sendPositions(final BufferedReader in, final PrintWriter out){
			out.println("(2)");
			Integer lines = BorderedPositionFromFilePanel.getLinesCount();
			String moveType = ConfigurationMoveTypePanel.getMoveTypeValue().toLowerCase();
			Double accInt = Double.parseDouble(ConfigurationAccelerationPanel.getAccelerationValue());
			Double speedInt = Double.parseDouble(ConfigurationVelocityPanel.getVelocityValue());
			String speed = speedInt.toString();
			String acc = accInt.toString();
			String reading = null;
			Double delay = Double.parseDouble(ConfigurationControllerOutputsPanel.getDelayValue())/1000;
			String coordinate = null;
			String incOrAbs = null;
			if (ConfigurationCoordinatePanel.getCarthesianSelection())
				coordinate = "(1)";
			else if (ConfigurationCoordinatePanel.getJointSelection())
				coordinate = "(2)";
			if (TrajectoryFromFileGUI.getAbsoluteSelection())
				incOrAbs = "(1)";
			else if (TrajectoryFromFileGUI.getIncrementalSelection())
				incOrAbs = "(2)";
			Integer linesCount=0;
			while (true && connected){
				try {
					reading = in.readLine();
					if (linesCount>lines || stop){
						linesCount=0;
						break;
					}
					if ((reading != null) && reading.equals("GET_POS")){
						System.out.println("(" + lines.toString() + ")");
						out.println("(" + lines.toString() + ")");
					}
					if ((reading != null) && reading.equals("GET_NEXT")){
						String[] position = TrajectoryFromFileGUI.getPositions(linesCount);
						System.out.println("(" + position[0] + "," + position[1] + "," + position[2] + "," + position[3] + "," + position[4] + "," + position[5] + ")");
						out.println("(" + position[0] + "," + position[1] + "," + position[2] + "," + position[3] + "," + position[4] + "," + position[5] + ")");
						linesCount++;
					}
					if ((reading != null) && reading.equals("GET_ACC_VALUE")){
						System.out.println("(" + acc + ")");
						out.println("(" + acc + ")");
					}
					if ((reading != null) && reading.equals("GET_SPEED_VALUE")){
						System.out.println("(" + speed + ")");
						out.println("(" + speed + ")");
					}
					if ((reading != null) && reading.equals("GET_INC_OR_ABS")){
						out.println(incOrAbs);
					}
					if ((reading != null) && reading.equals("GET_DELAY")){
						System.out.println("(" + delay + ")");
						out.println("(" + delay + ")");
					}
					if ((reading != null) && reading.equals("GET_CARTH_OR_JOINT")){
						out.println(coordinate);
						System.out.println(coordinate.toString());
					}
					if ((reading != null) && reading.equals("GET_MOVE_TYPE")){
						System.out.println(moveType);
						switch (moveType){
						case "movel": out.println("(1)"); break;
						case "movej": out.println("(2)"); break;
						case "movep": out.println("(3)"); break;
						}
					}	
					if (reading == null){
					//	if (!serverDisconnected)
					//		closedConnection();
							connected = false;
							break;
					}
				} catch (IOException e) {
					System.out.println("Connection reset");
					if (!serverDisconnected)
						closedConnection();
				}
			}
		}

	public static void closedConnection(){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				JOptionPane.showMessageDialog(new JFrame(), "Client disconnected!");
			}
		});
		t.start();
		if (BottomPanelGUI.getFileWriter()!=null)
			BottomPanelGUI.flushFile();
		if (TrajectoryFromFileGUI.getReadButtonFirstOption()){
			BottomPanelGUI.enableStartTrajectoryAndSavingButton(true);
			BottomPanelGUI.enableTrajectoryFromFileButton(true);
		}
			TrajectoryFromFileGUI.setReadTrajectoryFromFileButtonStatus(true);
			connected = false;
			serverDisconnected = true;
		}
	
	static void connectedToClient(){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(new JFrame(), "Connected to client");
			}
		});
		t.start();
	}
	
	public static void closeConnection(){
		try {
			if (connected){
				/*while (connected){
					@SuppressWarnings("unused")
					String reading = in.readLine();
					out.println("(-1)");
				}*/
				stop = true;
				connected = false;
			}
			if (serverSocket.isBound()){
				serverSocket.close();
		//		connected = false;
			//	stop = true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}