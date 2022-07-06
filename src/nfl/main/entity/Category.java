package nfl.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table
public class Category {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	@Size(min = 3, max = 45, message = "min 3, max 45 characters")
	private String name;
	@Column
	@Size(min = 10, max = 1000, message = "min 10, max 1000 characters")
	private String description;
	@Column
	private Integer numOrder;
	
	@OneToMany(mappedBy = "category", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Blog> blogs;
	
	public Category() {
	}
	
	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNumOrder() {
		return numOrder;
	}
	public void setNumOrder(Integer numOrder) {
		this.numOrder = numOrder;
	}
	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public String getNameURL() {
		return name.toLowerCase();
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
