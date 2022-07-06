package nfl.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity(name = "users")
@Table
public class Author {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	@Size(min = 3, max = 45, message = "min 3, max 45 characters")
	private String name;
	@Column
	private String lastname;
	@Column
	@NotBlank(message = "required field")
	private String username;
	@Column
	@NotBlank(message = "required field")
	private String password;
	@Column
	private String image;
	@Column
	@NotBlank(message = "required field")
	private String phone;
	@Column
	@Email(message = "bad email format")
	@NotBlank(message = "required field")
	private String email;
	@Column
	private boolean enabled;
	@JoinTable(name = "authorities", joinColumns = @JoinColumn(name="idAuthor"), inverseJoinColumns = @JoinColumn(name = "authority"))
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@NotEmpty(message = "required one role")
	private List<Role> authorities;
	
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Blog> blogs;

	public Author() {
	}

	public Author(String name, String lastname, String username, String password, String image, String phone,
			String email, boolean enabled) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.image = image;
		this.phone = phone;
		this.email = email;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public String getNameURL() {
		return name.toLowerCase() + "-" + lastname.toLowerCase();
	}
	
	public List<Role> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return id + " " + name + " " + lastname + " " + username + " " + password + " " + image + " " + phone + " "
				+ email + authorities;
	}

}
