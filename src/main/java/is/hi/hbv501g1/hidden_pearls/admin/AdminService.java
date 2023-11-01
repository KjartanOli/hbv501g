package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

public interface AdminService {

	public List<Admin> getAllAdmins();

	public Admin getAdmin(long id);

	public Admin create(String username, String password);

	public Admin update(long id, String username, String password);

	public void delete(long id);

	public List<Admin> searchByName(String name);

	public Admin authenticate(String username, String password);

}
