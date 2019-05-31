package comm;

import gui.ConnectionStatus;
import gui.ReadingsAccelerationPanel;
import gui.ReadingsCurrentPanel;
import gui.ReadingsPositionPanel;
import gui.ReadingsTAVPanel;
import gui.ReadingsTCPPanel;
import gui.ReadingsVelocityPanel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

public class RealTimeClientInterface extends Thread{
	
	private static Double timestamp;
	private static String timestampString;
	
	private static Double[] positionsTarget = new Double[6];
	private static String[] positionsTargetString = new String[6];
	private static Double[] speedsTarget = new Double[6];
	private static String[] speedsTargetString = new String[6];
	private static Double[] accTarget = new Double[6];
	private static String[] accTargetString = new String[6];
	
	private static Double[] positionsActual = new Double[6];
	private static String[] positionsActualString = new String[6];
	private static Double[] speedsActual = new Double[6];
	private static String[] speedsActualString = new String[6];
	
	private static Double[] currentTarget = new Double[6];
	private static String[] currentTargetString = new String[6];
	private static Double[] currentActual = new Double[6];
	private static String[] currentActualString = new String[6];
	
	private static Double[] tavs = new Double[3];
	private static String[] tavsString = new String[3];
	
	private static Double[] tcpPose = new Double[6];
	private static String[] tcpPoseString = new String[6];
	private static Double[] tcpSpeed = new Double[6];
	private static String[] tcpSpeedString = new String[6];
	
	private static boolean isConnected = false;
	
	private static String addr;
	
	public void run(){
		int portNo = 30003;
		SocketChannel socketChannel = null;
		while(true){
			try {
				ByteBuffer buf = ByteBuffer.allocate(812);
				buf.order(ByteOrder.BIG_ENDIAN);
					socketChannel = SocketChannel.open();
					addr = Configuration.getIPText();
					socketChannel.connect(new InetSocketAddress(addr, portNo));
					System.out.println("Connected with server on port 30003.");
					ConnectionStatus.setWaitingLabelText("");
					isConnected = true;
					int index = 0;
					int countZeros = 0;

					while(true){
						if (!addr.equals(Configuration.getIPText().trim())){
							isConnected = false;
							System.out.println("No server connection on port 30003!");
							ReadingsAccelerationPanel.setTargetAccelerationsReadings(new String[]{"0", "0", "0", "0", "0", "0"});
							ReadingsCurrentPanel.setActualCurrentReadings(new String[]{"0", "0", "0", "0", "0", "0"});
							ReadingsCurrentPanel.setTargetCurrentReadings(new String[]{"0", "0", "0", "0", "0", "0"});
							ReadingsPositionPanel.setActualPositionReadings(new String[]{"0", "0", "0", "0", "0", "0"});
							ReadingsPositionPanel.setTargetPositionReadings(new String[]{"0", "0", "0", "0", "0", "0"});
							ReadingsTAVPanel.setTAV(new String[]{"0", "0", "0"});
							ReadingsTCPPanel.setActualTCPPose(new String[]{"0", "0", "0", "0", "0", "0"});
							ReadingsTCPPanel.setActualTCPSpeed(new String[]{"0", "0", "0", "0", "0", "0"});
							ReadingsVelocityPanel.setActualVelocityReadings(new String[]{"0", "0", "0", "0", "0", "0"});
							ReadingsVelocityPanel.setTargetVelocityReadings(new String[]{"0", "0", "0", "0", "0", "0"});
							buf.clear();
							socketChannel.finishConnect();
							socketChannel.close();
							socketChannel.socket().close();
							break;
						}
					buf.clear();
					socketChannel.read(buf);
					if (buf.getInt(0)!=812){
						if (++countZeros==10){
							break;
						}
					}
					else {
						countZeros = 0;
					}
					if (buf.position()==812 && buf.getInt(0)==812){
						index++;

							timestamp = (double)Math.round(buf.getDouble(4));
							timestampString = timestamp.toString();
					for (int i=0;i<6;i++){
						
						positionsTarget[i] = (double)Math.round(buf.getDouble(i*8+12)*10000)/10000;
						positionsTargetString[i] = positionsTarget[i].toString();
						speedsTarget[i] = (double)Math.round(buf.getDouble(i*8+60)*10000)/10000;;
						speedsTargetString[i] = speedsTarget[i].toString();
						accTarget[i] = (double)Math.round(buf.getDouble(i*8+108)*10000)/10000;;
						accTargetString[i] = accTarget[i].toString();
						
						currentTarget[i] = (double)Math.round(buf.getDouble(i*8+156)*10000)/10000;;
						currentTargetString[i] = currentTarget[i].toString();
						currentActual[i] = (double)Math.round(buf.getDouble(i*8+348)*10000)/10000;;
						currentActualString[i] = currentActual[i].toString();
					
						positionsActual[i] = (double)Math.round(buf.getDouble(i*8+252)*10000)/10000;;
						positionsActualString[i] = positionsActual[i].toString();
						speedsActual[i] = (double)Math.round(buf.getDouble(i*8+300)*10000)/10000;;
						speedsActualString[i] = speedsActual[i].toString();
					
						tcpPose[i] = (double)Math.round(buf.getDouble(i*8+588)*10000)/10000;;
						tcpPoseString[i] = tcpPose[i].toString();
						tcpSpeed[i] = (double)Math.round(buf.getDouble(i*8+636)*10000)/10000;;
						tcpSpeedString[i] = tcpSpeed[i].toString();
						if (i<3){
							tavs[i] = (double)Math.round(buf.getDouble(i*8+396)*10000)/10000;;
							tavsString[i] = tavs[i].toString();
						}
					}
					if (index%25==0){
					ReadingsPositionPanel.setTargetPositionReadings(positionsTargetString);
					ReadingsPositionPanel.setActualPositionReadings(positionsActualString);
					ReadingsVelocityPanel.setActualVelocityReadings(speedsActualString);
					ReadingsVelocityPanel.setTargetVelocityReadings(speedsTargetString);
					ReadingsAccelerationPanel.setTargetAccelerationsReadings(accTargetString);
					ReadingsCurrentPanel.setActualCurrentReadings(currentActualString);
					ReadingsCurrentPanel.setTargetCurrentReadings(currentTargetString);
					ReadingsTAVPanel.setTAV(tavsString);
					ReadingsTCPPanel.setActualTCPPose(tcpPoseString);
					ReadingsTCPPanel.setActualTCPSpeed(tcpSpeedString);
						}
					}
				}
			
					
			//		socketChannel.close();
			} 
			catch (IOException e) {
				isConnected = false;
				System.out.println("No connection with server on port 30003!");
				ConnectionStatus.setWaitingLabelText("");
			}
		}	
	}
	
	public static boolean getConnectionStatus(){
		return isConnected;
	}
	
	public static String getIPAddress(){
		return addr;
	}
	
	public static void setIPAddress(String ipAddress){
		addr = ipAddress;
	}

	public static String getTimestamp(){
		return timestampString;
	}
	
	public static String[] getPositionsTarget(){
		return positionsTargetString;
	}
	
	public static String[] getSpeedsTarget(){
		return speedsTargetString;
	}
	
	public static String[] getAccTarget(){
		return accTargetString;
	}
	
	public static String[] getPositionsActual(){
		return positionsActualString;
	}
	
	public static String[] getSpeedsActual(){
		return speedsActualString;
	}
	
	public static String[] getCurrentTarget(){
		return currentTargetString;
	}
	
	public static String[] getCurrentActual(){
		return currentActualString;
	}
	
	public static String[] getTavs(){
		return tavsString;
	}
	
	public static String[] getTcpPose(){
		return tcpPoseString;
	}
	
	public static String[] getTcpSpeed(){
		return tcpSpeedString;
	}
}