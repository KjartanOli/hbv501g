package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

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

	public Admin create(String username, String password) {
		Admin existingAdmin = repository.findByUsername(username);
		if (existingAdmin != null) {
			throw new IllegalArgumentException("Username already exists");
    	}

		var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var ad = new Admin();

		var hash = passwordEncoder.encode(password);

        ad.setUsername(username);
        ad.setPassword(hash);

        return repository.save(ad);
    }

    public Admin update(long id, String username, String password) {
        var ad = getAdmin(id);

        ad.setPassword(password);

        return repository.save(ad);
    }

    public void delete(long id) {
        var ad = repository.findById(id);
        if (ad != null)
            repository.delete(ad);
    }

	@Override
    public List<Admin> searchByName(String name) {
        return repository.findByUsernameLike(name);
    }

    public Admin authenticate(String username, String password) {
		var admin = repository.findByUsername(username);

		if (admin == null)
			return null;

		var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		if (!passwordEncoder.matches(password, admin.getPassword()))
			return null;

		return admin;
    }

}
