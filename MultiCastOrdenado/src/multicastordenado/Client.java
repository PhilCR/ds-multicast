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
public class Client implements Runnable{
    private int pid;
    private int clock;
    
    public Client(int pid, int clock){
        this.pid = pid;
        this.clock = clock;
    }
   

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 52000);
            DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());  
            DataInputStream istream = new DataInputStream(socket.getInputStream());

            
            ostream.writeUTF(pid + "-" + clock + "-" + "SEND");  
            ostream.flush();  

            System.out.println(pid + ": Enviando");

        } catch(Exception e){ 
            System.err.println(e);  
        }         
    }
   
}
