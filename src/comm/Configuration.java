package comm;

import gui.ConfigurationGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Configuration{
	
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	private static File xmlConfigurationFile;
	private static Document xmlConfiguration;
	private static Element rootElement;
	private static NodeList childrenElements;
	
	public Configuration(){
		try{
			/*  Reading configuration from file config.xml */
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {
				@Override
				public void warning(SAXParseException exception) throws SAXException {
					resetConfigFile();
				}
				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					resetConfigFile();
				}
				@Override
				public void error(SAXParseException exception) throws SAXException {
					resetConfigFile();
				}
			});
			
				xmlConfigurationFile = new File("config/config.xml");

			try {
				xmlConfiguration = builder.parse(xmlConfigurationFile);
			}
			catch(Exception e){
				
			}
			try{
				rootElement = xmlConfiguration.getDocumentElement();
				childrenElements = rootElement.getChildNodes();
			}
			catch(Exception e){
				resetConfigFile();
			}
			/* Checking whether the config.xml is valid */
			if (!isConfigFileValid()){
				resetConfigFile();
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/* UPDATE XML FILE */
	private static void updateXML(){
		try{
			xmlConfiguration.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domSource = new DOMSource(xmlConfiguration);
			StreamResult result = new StreamResult(new File("config/config.xml"));
			transformer.transform(domSource, result);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	/* MOVE NODE*/
	public static Node getMoveNode(){
		for (int i=0; i<childrenElements.getLength(); i++){
			Node child = childrenElements.item(i);
			if (child.getNodeName().equals("move")){
				return child;
			}
		}
		return null;
	}
	
	/* MOVE CHILD NODE */
	public static Node getMoveChildNode(String childText){
		Node move = getMoveNode();
		NodeList moveList = move.getChildNodes();
		for (int i=0;i<moveList.getLength();i++){
			Node moveNode = moveList.item(i);
			if (moveNode.getNodeName().equals(childText)){
				return moveNode;
			}
		}
		return null;
	}
	
	/* MOVE TYPE NODE */
	public static String getMoveTypeText(){
		return getMoveChildNode("type").getTextContent().trim();
	}
	
	public void setMoveTypeText(String nodeText){
	    getMoveChildNode("type").setTextContent(nodeText);
	    updateXML();
	}
	
	/* MOVE VELOCITY NODE */
	public static String getMoveVelocityText(){
		return getMoveChildNode("velocity").getTextContent().trim();
	}
	
	public void setMoveVelocityText(Integer velocity){
	    getMoveChildNode("velocity").setTextContent(velocity.toString());
	    updateXML();
	}
	
	/* MOVE ACCELERATION NODE */
	public static String getMoveAccelerationText(){
		return getMoveChildNode("acceleration").getTextContent().trim();
	}
	
	public void setMoveAccelerationText(Integer acc){
	    getMoveChildNode("acceleration").setTextContent(acc.toString());
		updateXML();
	}
	
	/* MOVE COORDINATE NODE */
	public static String getMoveCoordinateText(){
		return getMoveChildNode("coordinate").getTextContent().trim();
	}
	
	public void setMoveCoordinateText(String s){
	    getMoveChildNode("coordinate").setTextContent(s);
		updateXML();
	}
	
	
	/* CONTROLLER NODE*/
	public static Node getControllerNode(){
		for (int i=0; i<childrenElements.getLength(); i++){
			Node child = childrenElements.item(i);
			if (child.getNodeName().equals("controller")){
				return child;
			}
		}
		return null;
	}
	
	/* CONTROLLER CHILD NODE */
	public static Node getControllerChildNode(String childText){
		Node controller = getControllerNode();
		NodeList controllerList = controller.getChildNodes();
		for (int i=0;i<controllerList.getLength();i++){
			Node controllerNode = controllerList.item(i);
			if (controllerNode.getNodeName().equals(childText)){
				return controllerNode;
			}
		}
		return null;
	}
	
	/* CONTROLLER DELAY NODE */
	public static String getControllerDelayText(){
		return getControllerChildNode("delay").getTextContent().trim();
	}
	
	public void setControllerDelayText(Integer delay){
	    getControllerChildNode("delay").setTextContent(delay.toString());
		updateXML();
	}
	
	/* CONTROLLER OUTPUTS CHILD NODE */
	private static Node getControllerOutputsChildNode(String childText){
		Node controllerOutput = getControllerChildNode("output");
		NodeList controllerOutputList = controllerOutput.getChildNodes();
		for (int i=0;i<controllerOutputList.getLength();i++){
			Node controllerOutputNode = controllerOutputList.item(i);
			if (controllerOutputNode.getNodeName().equals(childText)){
				return controllerOutputNode;
			}
		}
		return null;
	}
	
	/* GETTING AND SETTING VALUES OF CONTROLLER DIGITAL OUTPUTS */
	public  static String getOutputText(Integer outputNumber){
		return getControllerOutputsChildNode(outputNumber.toString()).getTextContent().trim();
	}
	
	public void setOutputON(Integer outputNumber){
		String output = null;
		switch (outputNumber){
		case 0: output = "zero"; break;
		case 1: output = "one"; break;
		case 2: output = "two"; break;
		case 3: output = "three"; break;
		case 4: output = "four"; break;
		case 5: output = "five"; break;
		case 6: output = "six"; break;
		case 7: output = "seven"; break;
		}
	    getControllerOutputsChildNode(output).setTextContent("on");
		updateXML();
	}
	
	public void setOutputOFF(Integer outputNumber){
		String output = null;
		switch (outputNumber){
		case 0: output = "zero"; break;
		case 1: output = "one"; break;
		case 2: output = "two"; break;
		case 3: output = "three"; break;
		case 4: output = "four"; break;
		case 5: output = "five"; break;
		case 6: output = "six"; break;
		case 7: output = "seven"; break;
		}
	    getControllerOutputsChildNode(output).setTextContent("off");
		updateXML();
	}
	
	public ArrayList<Integer> getOutputsON(){
		ArrayList<Integer> outputsON = new ArrayList<Integer>();
		String output = null;
		for (int i=0;i<8;i++){
			switch (i){
			case 0: output = "zero"; break;
			case 1: output = "one"; break;
			case 2: output = "two"; break;
			case 3: output = "three"; break;
			case 4: output = "four"; break;
			case 5: output = "five"; break;
			case 6: output = "six"; break;
			case 7: output = "seven"; break;
			}
			if (getControllerOutputsChildNode(output).getTextContent().equals("on"))
				outputsON.add(i);
		}
		return outputsON;
	}
	
	public ArrayList<Integer> getOutputsOFF(){
		ArrayList<Integer> outputsOFF = new ArrayList<Integer>();
		String output = null;
		for (int i=0;i<8;i++){
			switch (i){
			case 0: output = "zero"; break;
			case 1: output = "one"; break;
			case 2: output = "two"; break;
			case 3: output = "three"; break;
			case 4: output = "four"; break;
			case 5: output = "five"; break;
			case 6: output = "six"; break;
			case 7: output = "seven"; break;
			}
			if (getControllerOutputsChildNode(output).getTextContent().equals("off"))
				outputsOFF.add(i);
		}
		return outputsOFF;
	}
	
	/* PROBES NODE*/
	public static Node getProbesNode(){
		for (int i=0; i<childrenElements.getLength(); i++){
			Node child = childrenElements.item(i);
			if (child.getNodeName().equals("probes")){
				return child;
			}
		}
		return null;
	}
	
	/* PROBES CHILD NODE */
	public static Node getProbesChildNode(String childText){
		Node probes = getProbesNode();
		NodeList probesList = probes.getChildNodes();
		for (int i=0;i<probesList.getLength();i++){
			Node probesNode = probesList.item(i);
			if (probesNode.getNodeName().equals(childText)){
				return probesNode;
			}
		}
		return null;
	}
	
	/* PROBES FREQ NODE */
	public static String getProbesFreqText(){
		return getProbesChildNode("freq").getTextContent().trim();
	}
	
	public void setProbesFreqText(String nodeText){
	    getProbesChildNode("freq").setTextContent(nodeText);
	    updateXML();
	}
	
	/* PROBES COUNT NODE */
	public static String getProbesCountText(){
		return getProbesChildNode("count").getTextContent().trim();
	}
	
	public void setProbesCountText(String nodeText){
	    getProbesChildNode("count").setTextContent(nodeText);
	    updateXML();
	}
	
	private static Node getIPNode(){
		for (int i=0; i<childrenElements.getLength(); i++){
			Node child = childrenElements.item(i);
			if (child.getNodeName().equals("ip")){
				return child;
			}
		}
		return null;
	}
	
	public static String getIPText(){
		return getIPNode().getTextContent().trim();
	}
	
	public static void setIPText(String ipText){
		getIPNode().setTextContent(ipText);
		updateXML();
	}
	
	private static Node getFileNode(){
		for (int i=0; i<childrenElements.getLength(); i++){
			Node child = childrenElements.item(i);
			if (child.getNodeName().equals("file")){
				return child;
			}
		}
		return null;
	}
	
	private static Node getFileChildNode(String childText){
		Node fileNode = getFileNode();
		NodeList fileChildNodes = fileNode.getChildNodes();
		for (int i=0;i<fileChildNodes.getLength();i++){
			Node fileChildNode = fileChildNodes.item(i);
			if (fileChildNode.getNodeName().equals(childText)){
				return fileChildNode;
			}
		}
		return null;
	}
	
	public static String getDirectoryText(){
		return getFileChildNode("directory").getTextContent().trim();
	}
	
	public static void setDirectoryText(String directory){
		getFileChildNode("directory").setTextContent(directory);
		updateXML();
	}
	
	
	public static void resetConfigFile(){
		System.out.println("Wrong or lack of configuration file!\nCreating default xml config file.");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(new JFrame(), "Wrong or lack of configuration file!\nCreating default xml config file.");
			}
		});
		t.start();
		
		PrintWriter pw;
		try {
			new File("config").mkdir();
			pw = new PrintWriter(xmlConfigurationFile);
			pw.write(DefaultConfiguration.getDefaultConfiguration());
			pw.flush();
			ConfigurationGUI.updateConfigurationFromXMLFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isConfigFileValid(){
		if (getMoveNode()==null){
			return false;
		}
		if (getMoveChildNode("type")==null){
			return false;				
		}
		if (getMoveChildNode("velocity")==null){
			return false;				
		}
		if (getMoveChildNode("acceleration")==null){
			return false;				
		}
		if (getMoveChildNode("coordinate")==null){
			return false;			
		}
		
		if (!getMoveTypeText().equalsIgnoreCase("movel") && !getMoveTypeText().equalsIgnoreCase("movej") && !getMoveTypeText().equalsIgnoreCase("movep")){
			return false;
		}
		
		String pattern = "^([0-9]|[1-9][0-9]|100)$";
	    Pattern pattern2 = Pattern.compile(pattern);
		Matcher matcher = pattern2.matcher(getMoveVelocityText());
		 if (!matcher.matches()){
			return false;
		 }
		 matcher = pattern2.matcher(getMoveAccelerationText());
		 if (!matcher.matches()){
			return false;
		 }
		 
		 if (!getMoveCoordinateText().equalsIgnoreCase("joint") && !getMoveCoordinateText().equalsIgnoreCase("carthesian")){
			return false;
		 }
		 
		 if (getControllerNode()==null){
			return false;
		 }
		 
			
	    if (getProbesNode()==null){
			return false;
		}
		if (getProbesChildNode("freq")==null){
			return false;				
		}
		if (getProbesChildNode("count")==null){
			return false;				
		}
		 pattern = "^[0-9]{1,6}$";
		 pattern2 = Pattern.compile(pattern);
		 matcher = pattern2.matcher(getProbesCountText());
		 if (!matcher.matches()){
				return false;
		}
		 
		 pattern = "^([1-9]|[1-9][0-9]|[1][0-1][0-9]|[1][2][0-5])$";
		 pattern2 = Pattern.compile(pattern);
		 matcher = pattern2.matcher(getProbesFreqText());
		 if (!matcher.matches()){
				return false;
		}
		 
		 pattern = "^([0-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9])$";
		 pattern2 = Pattern.compile(pattern);
		 matcher = pattern2.matcher(getControllerDelayText());
		 if (!matcher.matches()){
			return false;
		 }
		 if (getControllerChildNode("delay")==null){
			return false;
		 }
		 if (getControllerChildNode("output")==null){
			return false;
		 }
		 
		 if (getControllerOutputsChildNode("zero")==null || getControllerOutputsChildNode("one")==null || getControllerOutputsChildNode("two")==null || getControllerOutputsChildNode("three")==null || getControllerOutputsChildNode("four")==null || getControllerOutputsChildNode("five")==null || getControllerOutputsChildNode("six")==null || getControllerOutputsChildNode("seven")==null){
			return false;
		 }
		 if (!getControllerOutputsChildNode("zero").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("zero").getTextContent().equalsIgnoreCase("on")){
			return false;
		 }
		 if (!getControllerOutputsChildNode("one").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("one").getTextContent().equalsIgnoreCase("on")){
			 return false;
		 }
		 if (!getControllerOutputsChildNode("two").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("two").getTextContent().equalsIgnoreCase("on")){
			return false;
		 }
		 if (!getControllerOutputsChildNode("three").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("three").getTextContent().equalsIgnoreCase("on")){
			return false;
		 }
		 if (!getControllerOutputsChildNode("four").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("four").getTextContent().equalsIgnoreCase("on")){
			return false;
		 }
		 if (!getControllerOutputsChildNode("five").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("five").getTextContent().equalsIgnoreCase("on")){
			return false;
		 }
		 if (!getControllerOutputsChildNode("six").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("six").getTextContent().equalsIgnoreCase("on")){
			return false;
		 }
		 if (!getControllerOutputsChildNode("seven").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("seven").getTextContent().equalsIgnoreCase("on")){
			return false;
		 }
		 if (!getControllerOutputsChildNode("zero").getTextContent().equalsIgnoreCase("off") && !getControllerOutputsChildNode("zero").getTextContent().equalsIgnoreCase("on")){
			return false;
		 }
		 
		 if (getIPNode()==null){
			 return false;
		 }
		 pattern = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
		 pattern2 = Pattern.compile(pattern);
		 matcher = pattern2.matcher(getIPText());
		 if (!matcher.matches()){
			 return false;
		 }
		 if (getFileNode()==null){
			 return false;
		 }
		 if (getFileChildNode("directory")==null){
			 return false;
		 }
		 File file = new File(getDirectoryText());
		 if (!file.isDirectory() && !getDirectoryText().equals("user.home")){
			 return false;
		 }
		 return true;
	}

}