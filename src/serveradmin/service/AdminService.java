/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveradmin.service;

import dto.AdminDTO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import serveradmin.dao.AdminDao;
import serveradmin.model.AdminDB;

/**
 *
 * @author Mihaela Tudose
 */
public class AdminService {
    private EntityManagerFactory emf;
    private AdminDao adminDao;
    
    private AdminService(){
        emf = Persistence.createEntityManagerFactory("serverAdminPU");
        adminDao = new AdminDao(emf); 
    }
    
    private static final class SingletonHolder {
        private static final AdminService SINGLETON = new AdminService();
    }
    
    public static AdminService getInstance(){
        return SingletonHolder.SINGLETON;
    }
    
    public boolean registerAdmin(AdminDTO admin){
        AdminDB rez = adminDao.findAdminByUser(admin.getUser());
        if(rez==null){
            adminDao.create(new AdminDB(0, admin.getUser(), admin.getParola()));
            return true;
        }
        return false;
    }
    
    public AdminDTO login(AdminDTO admin){
        AdminDB rez = adminDao.findAdminByUser(admin.getUser());
        if(rez!=null){
            if(rez.getUser().equals(admin.getUser()) && rez.getParola().equals(admin.getParola())){
                return new AdminDTO(rez.getId(), rez.getUser(), rez.getParola());
            }            
        }
        return null;
    }
    
    
}
