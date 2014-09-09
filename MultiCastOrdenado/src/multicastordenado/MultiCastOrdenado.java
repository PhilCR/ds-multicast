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
        if (args.length != 2) {
            System.err.println(
                "Usage: java MultiCastOrdenado <process number> <clock>");
            System.exit(1);
        }
        
        try {
            Runnable r = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            new Thread(r).start();
            
            Runnable r2 = new Client(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            new Thread(r2).start();
        }catch(Exception ex){
            
        }        
    }
    
}
