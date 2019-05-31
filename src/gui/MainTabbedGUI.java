package gui;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class MainTabbedGUI extends JTabbedPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel tabReadings;
	private JPanel tabConfiguration;
	private JPanel tabTrajectoryFromFile;

	
	public MainTabbedGUI(){
		initComponents();
	}
	
	private void initComponents(){
		
		tabReadings = new ReadingsGUI();
		tabConfiguration = new ConfigurationGUI();
		tabTrajectoryFromFile = new TrajectoryFromFileGUI();

		setTabPlacement(JTabbedPane.TOP);
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		setOpaque(true);
		
		addTab("<html><body leftmargin=15 topmargin=2 marginwidth=80 marginheight=15>Readings</body></html>", tabReadings);
		addTab("<html><body leftmargin=15 topmargin=2 marginwidth=80 marginheight=15>Configuration</body></html>", tabConfiguration);
		addTab("<html><body leftmargin=15 topmargin=2 marginwidth=80 marginheight=15>Trajectory from file</body></html>", tabTrajectoryFromFile);
		
	}
}