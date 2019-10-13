package sim8085;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main implements ActionListener{
	
	static boolean running1 = false;
	static boolean running2 = false;
	
	public static void main(String[] args) {
	
		new GUIData();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Bind")) {
			
			running1 = false;
			running2 = false;
			GUIData.system_port[0] = Integer.parseInt(GUIData.port_numberintf.getText());
			GUIData.system_port[1] = Integer.parseInt(GUIData.port_numberouttf.getText());
			runServer(GUIData.system_port[0]);
			
		}
		
		else if(e.getActionCommand().equals("CHANGED_PORT")) {
			
			GUIData.port_valuetf.setText(String.format("%02X", GUIData.port_data[GUIData.port_box.getSelectedIndex()]).toUpperCase());
			
		}
		
	}


	public static void runClient(int outport){
		
		running2 = true;
		
		new Thread( new Runnable() {
            
			@SuppressWarnings("resource")
			public void run()
            {
				Socket socket = null;
				try {
					socket = new Socket("localhost", outport);
				} catch (Exception e) {
					GUIData.error_area.setText("Error Running Client!");
					return;
				}
				
				PrintWriter pw = null;
				
				try {
					 pw = new PrintWriter(socket.getOutputStream(), true);
				} catch (Exception e) {
					GUIData.error_area.setText("Error Running Client!");
					return;
				}
            	
            	while(running2 == true) {
        			pw.println(GUIData.writeTo + ":" + GUIData.chip.getRegA()); 
        			pw.close();
        			running2 = false;
            	
            }}}).start();
		
		
	}

	private void runServer(int inport) {
		
		running1 = true;
		
		new Thread( new Runnable() {
            
			@SuppressWarnings("resource")
			public void run()
            {
            	ServerSocket ss = null;
				try {
					ss = new ServerSocket(inport);
				} catch (Exception e) {
					GUIData.error_area.setText("Error Running Server!");
					return;
				}
        		GUIData.error_area.setText("IN PORT Ready For Connection!");
            	
            	while(running1 == true) {
        			
            		Socket s = null;
					try {
						s = ss.accept();
					} catch (Exception e) {
						GUIData.error_area.setText("Error Running Server!");
						return;
					}
            		new ServerThread(s).start();
                
            }}}).start();

	}

}
