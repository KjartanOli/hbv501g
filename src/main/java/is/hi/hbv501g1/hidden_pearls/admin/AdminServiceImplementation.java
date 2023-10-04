package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation {
    
    @Autowired
    private AdminRepository repository;
    
    public List<Admin> getAllAdmins() {
        return repository.findAll();
    }
    
    public Admin getAdmin(long id) {
        return repository.findByUsername(id);
    }

    public Admin create(String username, String password, long id) {
        var ad = new Admin();
        
        ad.setUsername(username);
        ad.setPassword(password);
        
        return repository.save(ad);

    }

    public Admin update(long id, String username, String password) {
        var ad = getAdmin(id);

        ad.setUsername(username);
        ad.setPassword(password);

        return repository.save(ad);
    }

    public void delete(long id) {
        var ad = repository.findByUsername(id);
        if (ad != null)
            repository.delete(ad);

    }

    /* 
    // return type is PLACEHOLDER, implementation pending
    public void authenticate(String username, String password) {
    } 
    */

}