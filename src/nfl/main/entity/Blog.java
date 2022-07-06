package nfl.main.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.ocpsoft.prettytime.PrettyTime;


@Table
@Entity
public class Blog {

	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	@Size(min = 20, max = 255, message = "min 20, max 255 characters")
	private String title;
	@Column
	private String titleURL;
	@Column
	private String date;
	@Column
	@Size(min = 50, max = 500, message = "min 50, max 500 characters")
	private String description;
	@Column
	@Size(min = 20, max = 200, message = "min 20, max 200 characters")
	private String subheading;
	@Column
	@Size(min = 100, max = 1000, message = "min 100, max 1000 characters")
	private String text;
	@Column
	private int numberViews;
	@Column
	private boolean enabled;
	@Column
	private boolean isImportant;

	@JoinColumn(name = "idCategory")
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Category category;
	@JoinColumn(name = "idMainImage")
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Image image;
	@JoinColumn(name = "idAuthor")
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Author author;
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	private List<Image> images;
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	private List<Comment> comments;
	@JoinTable(name = "Blog_Team", joinColumns = @JoinColumn(name = "idBlog"), inverseJoinColumns = @JoinColumn(name = "idTeam"))
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Team> teams;

	public Blog() {
	}

	public Blog(String title, String date, String description, String subheading, String text, int numberViews,
			boolean enabled) {
		super();
		this.title = title;
		this.date = date;
		this.description = description;
		this.subheading = subheading;
		this.text = text;
		this.numberViews = numberViews;
		this.enabled = enabled;
	}

	public boolean getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubheading() {
		return subheading;
	}

	public void setSubheading(String subheading) {
		this.subheading = subheading;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getNumberViews() {
		return numberViews;
	}

	public void setNumberViews(int numberViews) {
		this.numberViews = numberViews;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public String getTitleURL() {
		return titleURL;
	}
	public void setTitleURL(String titleURL) {
		this.titleURL = titleURL;
	}

	public String getBlogDescriptionShort() {
		String text = description.substring(0, 50);
		return text + "...";
	}
	
	public String getBlogTextShort() {   
		String result;
		
		try {
			result = text.substring(text.indexOf("[") + 1, text.indexOf("]"));
		}catch(StringIndexOutOfBoundsException ex){
			result = "";
		}
		
		return result;
	}
	
	public String getBlogTextBefore() {   
		int posA = text.indexOf("*");
        if (posA == -1) {
            return text;
        }
        return text.substring(0, posA).replace("[", "").replace("]", "");
	}
	
	public String getBlogTextAfter() {   
		int posA = text.lastIndexOf("*");
        if (posA == -1) {
            return "";
        }
        int adjustedPosA = posA + "*".length();
        if (adjustedPosA >= text.length()) {
            return "";
        }
        text.replace("[", "");
        text.replace("]", "");
        return text.substring(adjustedPosA);
	}

	public String getDateFormat() {
		PrettyTime p = new PrettyTime();
		String date = this.date;
		try {
			Date dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
			return p.format(dateFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this.date;
	}

	public String getDateFormatTwelve() {
		String date = this.date;
		try {
			Date dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
			String pattern = "dd MMMM | yyyy ";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(dateFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this.date;
	}

	public String getDateFormatFooter() {
		String date = this.date;
		try {
			Date dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
			String pattern = "MMMM dd, yyyy ";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(dateFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this.date;
	}

	@Override
	public String toString() {
		return id + " " + title;
	}

}
