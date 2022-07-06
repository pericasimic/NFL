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

@Table
@Entity
public class Division {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String image;
	@Column
	@Size(min = 3, max = 100, message = "min 3, max 100 characters")
	private String title;
	@Column
	@Size(min = 3, max = 45, message = "min 3, max 45 characters")
	private String titleButton;
	@Column
	@Size(min = 3, max = 500, message = "min 3, max 500 characters")
	private String urlButton;
	@Column
	private Integer numOrder;
	@Column
	private boolean enabled;
	
	@OneToMany(mappedBy = "division", cascade = CascadeType.ALL)
	private List<Team> teams;
	
	public Division() {
		
	}
	
	public Division(String image, String title, String titleButton, String urlButton, boolean enabled) {
		super();
		this.image = image;
		this.title = title;
		this.titleButton = titleButton;
		this.urlButton = urlButton;
		this.enabled = enabled;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleButton() {
		return titleButton;
	}
	public void setTitleButton(String titleButton) {
		this.titleButton = titleButton;
	}
	
	public String getUrlButton() {
		return urlButton;
	}
	
	public void setUrlButton(String urlButton) {
		this.urlButton = urlButton;
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Integer getNumOrder() {
		return numOrder;
	}
	
	public void setNumOrder(Integer numOrder) {
		this.numOrder = numOrder;
	}
	
	public List<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	@Override
	public String toString() {
		return title;
	}
}
