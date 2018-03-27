import java.io.*;
import java.net.Socket;

public class client {
	
	public static void main(String[] args) {
		String serverName = "";
	    int port = 80;
	    BufferedReader inputStream;
		PrintWriter outputStream;
		
		String input;
		
		try {
	         Socket client = new Socket(serverName, port);
	         
	         outputStream = new PrintWriter(client.getOutputStream());
	         inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
	         
	         outputStream.print("GET //test/test.html HTTP/1.1\r\n");
	         outputStream.print("Host: www." + serverName + "\r\n\r\n");
	         outputStream.flush();
	         
	         while((input = inputStream.readLine()) != null) {
	        	 System.out.println(input);
	         }

	         outputStream.close();
	         inputStream.close();
	         client.close();
	         
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
	}

}
