package comm;


public class DefaultConfiguration{
	
	public static String getDefaultConfiguration(){
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
				+ "<configuration>\n"
				+ "	\t<move>\n"
				+ "	\t\t<!-- Move type: only MOVEL, MOVEJ or MOVEP allowed -->\n"
				+ "	\t\t<type>MOVEL</type>\n"
				+ "	\t\t<!-- Move speed: 0-100% -->\n"
				+ "	\t\t<velocity>100</velocity>\n"
				+ "	\t\t<!-- Move acceleration: 0-100% -->\n"
				+ "	\t\t<acceleration>50</acceleration>\n"
				+ "	\t\t<!-- Coordinate system: only Joint or Carthesian allowed -->\n"
				+ "	\t\t<coordinate>Joint</coordinate>\n"
				+ "	\t</move>\n"
				+ "	\t<controller>\n"
				+ "	\t\t<output>\n"
				+ "	\t\t\t<!-- Controller outputs: only ON or OFF allowed -->\n"
				+ "	\t\t\t<zero>on</zero>\n"
				+ "	\t\t\t<one>on</one>\n"
				+ "	\t\t\t<two>on</two>\n"
				+ "	\t\t\t<three>on</three>\n"
				+ "	\t\t\t<four>on</four>\n"
				+ "	\t\t\t<five>on</five>\n"
				+ "	\t\t\t<six>on</six>\n"
				+ "	\t\t\t<seven>on</seven>\n"
				+ "	\t\t</output>\n"
				+ "	\t\t<!-- Move delay: 0-9999 ms -->\n"
				+ "	\t\t<delay>50</delay>\n"
				+ "	\t</controller>\n"
				+ " \t<!-- Probes -->\n"
				+ " \t<probes>\n"
				+ " \t\t<!-- Probes freq: 1-125 -->\n"
				+ " \t\t<freq>1</freq>\n"
				+ " \t\t<!-- Probes count: 0-999999 -->\n"
				+ " \t\t<count>12000</count>\n"
				+ " \t</probes>\n"
				+ "	\t<!-- IP to connect to server -->\n"
				+ "	\t<ip>127.0.0.1</ip>\n"
				+ "	\t<file>\n"
				+ "	\t\t<!-- Directory path to save files -->\n"
				+ "	\t\t<directory>user.home</directory>\n"
				+ "	\t</file>\n"
				+ "</configuration>";
	}
}