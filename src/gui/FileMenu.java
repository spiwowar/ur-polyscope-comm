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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import comm.Configuration;


public class FileMenu extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel fileMenuPanel;
	private JTextField chosenDirectory;
	private JButton fileChooserButton;
	private JLabel savingDirectoryLabel;

	public FileMenu(){
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("File configuration");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setPreferredSize(new Dimension(500, 100));
        setResizable(false);
                
        fileMenuPanel = new JPanel();
        if (Configuration.getDirectoryText().equals("user.home"))
        	chosenDirectory = new JTextField(System.getProperty(Configuration.getDirectoryText()), 30);
        else 
        	chosenDirectory = new JTextField(Configuration.getDirectoryText());
       
        chosenDirectory.setColumns(30);
        savingDirectoryLabel = new JLabel("Saving directory:");
        chosenDirectory.setEditable(false);
        fileChooserButton = new JButton("Change directory");
        fileMenuPanel.setLayout(new GridBagLayout());
        
        fileChooserButton.addActionListener(new ActionListener() {
    		private JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
		
					@Override
					public void actionPerformed(ActionEvent arg0) {
						fileChooser.setApproveButtonText("Change directory");
						fileChooser.setDialogTitle("Change directory");
						fileChooser.setBorder(BorderFactory.createEtchedBorder());
						fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int status = fileChooser.showOpenDialog(null);

					    if (status == JFileChooser.APPROVE_OPTION) {
					    	chosenDirectory.setText(fileChooser.getSelectedFile().toString().trim());
					    	Configuration.setDirectoryText(fileChooser.getSelectedFile().toString().trim());
					    	System.out.println("Directory has been changed.");
					    }
						revalidate();
					}
		});
        
        GridBagConstraints gridBagConstraints;
		
    	gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        fileMenuPanel.add(savingDirectoryLabel, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        fileMenuPanel.add(chosenDirectory, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        fileMenuPanel.add(fileChooserButton, gridBagConstraints);
 
        
        getContentPane().add(fileMenuPanel, BorderLayout.CENTER);
        setVisible(false);
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width/2, screenSize.height/2);
        Point newLocation = new Point(middle.x-(getWidth()/2), middle.y-(getHeight()/2));
        setLocation(newLocation);
	}
	
	public void setVisibility(boolean visibility){
		setVisible(visibility);
	}
	
}