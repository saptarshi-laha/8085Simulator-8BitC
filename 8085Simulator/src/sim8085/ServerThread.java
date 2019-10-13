package sim8085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{
	
	Socket socket;
	
	ServerThread(Socket socket){
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			String message = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			message = br.readLine();
			int indexOfColon = message.indexOf(":");
			GUIData.port_data[Integer.parseInt(message.substring(0,indexOfColon))] = (byte) Integer.parseInt(message.substring(indexOfColon+1));
			socket.close();
		} catch (IOException e) {
			GUIData.error_area.setText("Error Reading Stream");
		}
		
	}

}
