/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multicastordenado;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

/**
 *
 * @author Rodrigo
 */
public class Server implements Runnable{
    private int pid;
    private int clock; 
    private LinkedList queue;
    
    public Server(int pid, int clock){
        this.pid = pid;
        this.clock = clock;
        this.queue = new LinkedList();
    }

    @Override
    public void run() {
        Socket socket = null;
        ServerSocket serverSocket;
        
    
        try {
            serverSocket = new ServerSocket(6000);
            socket = serverSocket.accept();
            DataOutputStream ostream = new   
            DataOutputStream(socket.getOutputStream());  
            DataInputStream istream = new   
            DataInputStream(socket.getInputStream());  
  
            while(true)  
            { 
                String message = istream.readUTF();  
                String[] tuples = message.split("|");
                
                // Confirms if its a ACK
                if ( tuples[2] == "ACK" ){
                    
                }else{
                    queue.add(tuples);
                    //Lamport's timestamp algorithm
                    clock = Integer.max(Integer.parseInt(tuples[0]), clock) +1;
                    ostream.writeUTF(pid + "|" + clock + "|" + "ACK");
                    ostream.flush();
                    System.out.println(pid + "|" + clock + "|" + "ACK");
                }
            } 
        } 
        catch (Exception e)  
        { System.err.println("Fechando conexão") ;
           System.err.println(e.toString());
          if  (socket != null)  
          try  
          { socket.close();  
          } catch (IOException ex) {}  
        }  
    

    }
    
    
}
