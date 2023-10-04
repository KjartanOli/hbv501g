package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository {
    public List<Admin> findAll();
    
    public Admin findByUsername(long id);

    public Admin create(Admin admin);
    
    public Admin save(Admin admin);

    public void delete(Admin admin);

}
