package nfl.main.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Table
@Entity
public class Comment {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String alias;
	@Column
	private String email;
	@Column
	private String date;
	@Column
	private String text;
	@Column
	private boolean enabled;
	@Valid
	@JoinColumn(name = "idBlog")
	@ManyToOne(cascade = CascadeType.ALL)
	private Blog blog;
	
	
	public Comment() {
	}
	
	public Comment(String alias, String date, String text, Blog blog) {
		super();
		this.alias = alias;
		this.date = date;
		this.text = text;
		this.blog = blog;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDateFormat() {
		String date = this.date;
		try {
			Date dateFormat = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			String pattern = "MMMM yyyy ";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(dateFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this.date;
	}
	
	@Override
	public String toString() {
		return alias + id + email +date+ text + blog.getId();
	}
	
}
