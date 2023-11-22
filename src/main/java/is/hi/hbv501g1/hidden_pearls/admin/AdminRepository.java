package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {

	public List<Admin> findAll();

	public List<Admin> findAll(Sort sort);

	public Admin findById(long id);

	public Admin findByUsername(String username);

	public List<Admin> findByUsernameLike(String username);

	public Admin save(Admin admin);

	public void delete(Admin admin);

}
