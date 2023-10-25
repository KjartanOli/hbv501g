package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation implements AdminService{

    @Autowired
    private AdminRepository repository;

    public List<Admin> getAllAdmins() {
        return repository.findAll();
    }

    public Admin getAdmin(long id) {
        return repository.findById(id);
    }

    public Admin create(String username, String password) throws IllegalArgumentException  {
        Admin existingAdmin = repository.findByUsername(username);
		if (existingAdmin != null) {
			throw new IllegalArgumentException("Username already exists");
    	}

		var ad = new Admin();
        ad.setUsername(username);
        ad.setPassword(password);

        return repository.save(ad);
    }

    public Admin update(long id, String username, String password) {
        var ad = getAdmin(id);

        // spurning með if-klásur til að geta uppfært
        // bara annaðhvort
        ad.setUsername(username);
        ad.setPassword(password);

        return repository.save(ad);
    }

    public void delete(long id) {
        var ad = repository.findById(id);
        if (ad != null)
            repository.delete(ad);
    }

    // return type is PLACEHOLDER, implementation pending
    public void authenticate(String username, String password) {
    }

}
