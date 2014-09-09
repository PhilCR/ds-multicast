/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multicastordenado;


/**
 *
 * @author Rodrigo
 */
public class MultiCastOrdenado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        try {
            
            Runnable r = new Server(1,2);
            new Thread(r).start();
            
            Runnable r2 = new Client(1,2);
            new Thread(r2).start();
            
            Runnable p = new Server(2,3);
            new Thread().start();
            
            Runnable p2 = new Client(2,3);
            new Thread(p2).start();
            
            Runnable q = new Server(3,5);
            new Thread(q).start();
            
            Runnable q2 = new Client(3,5);
            new Thread(q2).start();
        }catch(Exception ex){
            
        }        
    }
    
}
