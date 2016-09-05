/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveradmin.proxy;

import dto.AdminDTO;
import dto.Request;
import dto.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import serveradmin.service.AdminService;

/**
 *
 * @author Mihaela Tudose
 */
public class ServerAdminProxy extends Thread{
    private Socket socket;
    private ObjectInputStream in; 
    private ObjectOutputStream out; 

    public ServerAdminProxy(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }
    
    @Override
    public void run(){
        try {
            Request request = (Request) in.readObject();
            while (true) {  
                //Register
                if(request.getCod() == Request.REGISTER){                
                    AdminDTO admin = (AdminDTO) in.readObject();
                    boolean rez = AdminService.getInstance().registerAdmin(admin);
                    if(rez){
                        out.writeObject(new Response(Response.REGISTER_OK));
                    }else{out.writeObject(new Response(Response.REGISTER_FAILED));}
                }
                
                //login
                if(request.getCod() == Request.LOGIN){                
                    AdminDTO admin = (AdminDTO) in.readObject();
                    admin = AdminService.getInstance().login(admin);
                    out.writeObject(admin);                    
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
