package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import comm.CommunicationPolyscopeServer;
import comm.RealTimeClientInterface;


/**
 * @author Szymon Piwowar
 */

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanelGUI;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuConnection;
    private JMenuItem menuItemConnectionStatus;
    private JMenuItem fileMenuItem;
    private static CommunicationPolyscopeServer cps;
    private static RealTimeClientInterface rtci;
    private ConnectionStatus connectionStatus;
    private FileMenu fileMenu;

	public MainGUI() throws IOException, InstantiationException, IllegalAccessException{
		initComponents();
	}

    public static void main(String[] args){
    	try {
    		
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new MainGUI().setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            }
        });
    	
		cps = new CommunicationPolyscopeServer();
		cps.start();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rtci = new RealTimeClientInterface();
		rtci.start();
    }
    
    private void initComponents() {
    	mainPanelGUI = new MainPanelGUI();
        menuBar = new JMenuBar();
        menuFile = new JMenu();
        menuConnection = new JMenu();
        menuItemConnectionStatus = new JMenuItem();
        connectionStatus = new ConnectionStatus();
        fileMenuItem = new JMenuItem();
        fileMenu = new FileMenu();
        
    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("UR GUI");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setPreferredSize(new Dimension(1100, 700));
        setMinimumSize(new Dimension(1100, 650));
        setResizable(true);
        
		getContentPane().add(mainPanelGUI, BorderLayout.CENTER);
		getContentPane().add(mainPanelGUI, BorderLayout.CENTER);

        menuFile.setText("File");
        fileMenuItem.setText("Saving directory");
        menuFile.add(fileMenuItem);
        menuBar.add(menuFile);
        
        /* Menu Connection */
        menuConnection.setText("Connection");
        menuItemConnectionStatus.setText("Connection Status");
        menuConnection.add(menuItemConnectionStatus);
        menuBar.add(menuConnection);
        
        menuItemConnectionStatus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				connectionStatus.setVisibility(true);
				
			}
		});
        
        fileMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fileMenu.setVisibility(true);
			}
        	
        });

        setJMenuBar(menuBar);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width/2, screenSize.height/2);
        Point newLocation = new Point(middle.x-(getWidth()/2), middle.y-(getHeight()/2));
        setLocation(newLocation);
    }
    
    public static CommunicationPolyscopeServer getCommunicationPolyscopeServer(){
    	return cps;
    }
}