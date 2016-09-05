/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveradmin;

import java.net.ServerSocket;
import serveradmin.proxy.ServerAdminProxy;

/**
 *
 * @author Mihaela Tudose
 */
public class Server {    
    private static ServerSocket ss;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ss = new ServerSocket(3232);
            while (true) {                
                new ServerAdminProxy(ss.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
