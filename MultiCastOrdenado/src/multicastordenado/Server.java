/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multicastordenado;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
           serverSocket = new ServerSocket(52000);
            while(true)  
            { 
                socket = serverSocket.accept();
                new EchoThread(socket).start();
            } 
        } 
        catch (IOException e)  
        { System.err.println("Fechando conexão") ;
           System.err.println(e.toString());
          if  (socket != null)  
          try  
          { socket.close();  
          } catch (IOException ex) {}  
        }  
    

    }
    
    public class EchoThread extends Thread {

        protected Socket socket;

        public EchoThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try{
                DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());
                DataInputStream istream = new DataInputStream(socket.getInputStream());

                
                String message = istream.readUTF();
                String[] tuples = message.split("-");

                // Confirms if its a ACK
                if ("ACK".equals(tuples[2])) {
                    System.out.println("Confirmando ACK");
                } else {
                    queue.add(tuples);
                    System.out.println(tuples[0] + "-" + tuples[1] + "-" + tuples[2]);
                    //Lamport's timestamp algorithm
                    clock = Integer.max(Integer.parseInt(tuples[0]), clock) + 1;
                    ostream.writeUTF(pid + "-" + clock + "-" + "ACK");
                    ostream.flush();
                    System.out.println(pid + "-" + clock + "-" + "ACK");
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }

    }
}
