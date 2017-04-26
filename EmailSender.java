/**
   Tiffany Bui (tvb4sh)
   Kenny Nguyen (kn9rc)
**/

import java.io.*;
import java.net.*;

public class EmailSender {

   public static void main(String[] args) throws Exception {

      // Establish a TCP connection with the mail server.
      Socket socket = new Socket("mail.eservices.virginia.edu", 25);
      
      // Create a BufferedReader to read a line at a time.
      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);


      // Read greeting from the server.
      String response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("220")) {
         throw new Exception("220 reply not received from server.");
      }


      // Get a reference to the socket's output stream.
      OutputStream os = socket.getOutputStream(); 

      // Send HELO command and get server response.
      String command = "HELO 137.54.32.36\r\n";
      System.out.print(command);
      os.write(command.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
         throw new Exception("250 reply not received from server.");
      }

      // Send MAIL FROM command.
      String command2 = "MAIL FROM: buinguyen@gmail.com\r\n";
      System.out.print(command2);
      os.write(command2.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
         throw new Exception("250 reply not received from server.");
      }
      
     
      // Send RCPT TO command.
      String command3 = "RCPT TO: sks6bu@virginia.edu\r\n";
      System.out.print(command3);
      os.write(command3.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
         throw new Exception("250 reply not received from server.");
      }

 
      // Send DATA command.
      String command4 = "DATA\r\n";
      System.out.print(command4);
      os.write(command4.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("354")) {
         throw new Exception("354 reply not received from server.");
      }

     

      // Send message data.
      String message = "\r\nTiffany Bui (50%)\nKenny Nguyen (50%)";
      System.out.print(message);
      os.write(message.getBytes("US-ASCII"));
   

      // End with line with a single period.

      String period = "\r\n.\r\n";
      System.out.print(period);
      os.write(period.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
         throw new Exception("250 reply not received from server.");
      }
     
       // Send QUIT command.
      String command5 = "quit\r\n";
      System.out.print(command5);
      os.write(command5.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("221")) {
         throw new Exception("221 reply not received from server.");
      }

   }
}