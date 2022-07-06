package nfl.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table
public class Team {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	@Size(min = 3, max = 45, message = "min 3, max 45 characters")
	private String name;
	@Column
	private String image;
	@JoinColumn(name = "idDivision")
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Division division;
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name = "Blog_Team",joinColumns = @JoinColumn(name="idTeam"),inverseJoinColumns = @JoinColumn(name="idBlog"))
	private List<Blog> blogs;
	
	public Team() {
	}
	
	public Team(String name) {
		super();
		this.name = name;
	}

	public Team(String name, String image, Division division) {
		super();
		this.name = name;
		this.image = image;
		this.division = division;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	
	public String getURLNameWithoutSpace(String name) {
		String after = name.trim().replaceAll(" ", "-").toLowerCase();
		return after;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
