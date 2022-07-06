package nfl.main.dao;

import java.util.List;

import nfl.main.entity.Author;
import nfl.main.entity.Role;

public interface AuthorDAO {

	public List<Author> getAuthors();
	
	public Author getAuthor(int id);
	
	public Author getAuthorPerUsername(String username);
	
	public Author getAuthorPerNameAndLastname(String name, String lastname);
	
	public void saveAuthor(Author author);
	
	public void deleteAuthor(int id);
	
	public void enableAuthor(int id);
	
	public List<Role> getRoles();
	
	public void importUsername(Author author);
	
	public void editRoles(Author author);
	
	
}
