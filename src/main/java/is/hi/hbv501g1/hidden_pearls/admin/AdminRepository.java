package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends CrudRepository<Admin, Long>{
    public List<Admin> findAll();

    public Admin findById(long id);
    public Admin findByUsername(String username);

    public Admin save(Admin admin);

    public void delete(Admin admin);

}
