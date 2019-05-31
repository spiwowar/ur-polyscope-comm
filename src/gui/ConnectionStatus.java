package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import comm.CommunicationPolyscopeServer;
import comm.Configuration;
import comm.RealTimeClientInterface;


public class ConnectionStatus extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel connectionPanel;
	private JLabel connectionServerStatusLabel;
	private JLabel connectionClientStatusLabel;
	private JLabel ipConnection;
	private JTextField ipField;
	private JButton changeButton;
	private static JLabel waitingLabel;
	private static boolean isClickedChangeIpButton = false;
	
	public ConnectionStatus(){
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Connection Status");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setPreferredSize(new Dimension(400, 200));
        setResizable(false);
                
        connectionPanel = new JPanel();
        connectionServerStatusLabel = new JLabel("Server: Checking for status...");
        connectionClientStatusLabel = new JLabel("Client: Checking for status...");
        ipConnection = new JLabel("Client connection IP: ");
		ipField = new JTextField(Configuration.getIPText(), 1);
		changeButton = new JButton("Change IP");
		waitingLabel = new JLabel();
        
        Thread serverStatusThread = new Thread(new Runnable() {	
			@Override
			public void run() {
				while (true){
					if (!CommunicationPolyscopeServer.beforeConnecting)
						connectionServerStatusLabel.setText("Server: Connected with client on port 30000");
					else 
						connectionServerStatusLabel.setText("Server: No client connection");
					connectionServerStatusLabel.revalidate();
				}
			}
		});
        serverStatusThread.start();
        
        Thread clientStatusThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					if (RealTimeClientInterface.getConnectionStatus())
						connectionClientStatusLabel.setText("Client: Connected on port 30003");
					else 
						connectionClientStatusLabel.setText("Client: Not connected");
					connectionClientStatusLabel.revalidate();
				}
			}
		});
        clientStatusThread.start();
        
        
        changeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
		        String pattern = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
			    Pattern pattern2 = Pattern.compile(pattern);
				Matcher matcher = pattern2.matcher(ipField.getText().trim());
			    if (!matcher.matches()){
			    	JOptionPane.showMessageDialog(new JFrame(), "Wrong IPv4 Input", "Wrong IPv4", JOptionPane.WARNING_MESSAGE);;
			    }
			    else if (ipField.getText().trim().equals(Configuration.getIPText().trim())){
			    	waitingLabel.setText("");
			    }
			    else{
			    	connectionClientStatusLabel.setText("Client: Checking for status...");
			    	Configuration.setIPText(ipField.getText().trim());
			    	System.out.println("IP has been changed to: " + ipField.getText().trim());
			    	waitingLabel.setText("Waiting for connection...");
			    	isClickedChangeIpButton = true;
			    }
			}
		});
        
        connectionPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gridBagConstraints;
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.weighty = 1;
        connectionPanel.add(connectionServerStatusLabel, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.weighty = 1;
        connectionPanel.add(connectionClientStatusLabel, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 3;
        gridBagConstraints.weighty = 1;
        connectionPanel.add(ipConnection, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        connectionPanel.add(ipField, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        connectionPanel.add(waitingLabel, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        connectionPanel.add(changeButton, gridBagConstraints);
        
        getContentPane().add(connectionPanel, BorderLayout.CENTER);
        setVisible(false);
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width/2, screenSize.height/2);
        Point newLocation = new Point(middle.x-(getWidth()/2), middle.y-(getHeight()/2));
        setLocation(newLocation);
	}
	
	public void setVisibility(boolean visibility){
		setVisible(visibility);
		if (visibility){
			ipField.setText(Configuration.getIPText());
		}
	}
	
	public static void setWaitingLabelText(String text){
		waitingLabel.setText(text);
	}
	
	public static void setClickedButtonStatus(boolean status){
		isClickedChangeIpButton = status;
	}
	
	public static boolean getClickedButtonStatus(){
		return isClickedChangeIpButton;
	}
	
}