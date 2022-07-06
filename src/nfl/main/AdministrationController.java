package nfl.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import nfl.main.dao.AuthorDAO;
import nfl.main.dao.BlogDAO;
import nfl.main.dao.CategoryDAO;
import nfl.main.dao.CommentDAO;
import nfl.main.dao.ContactDAO;
import nfl.main.dao.DivisionDAO;
import nfl.main.dao.ImageDAO;
import nfl.main.dao.TeamDAO;
import nfl.main.entity.Author;
import nfl.main.entity.Blog;
import nfl.main.entity.Category;
import nfl.main.entity.ChangePassword;
import nfl.main.entity.Contact;
import nfl.main.entity.Division;
import nfl.main.entity.Image;
import nfl.main.entity.Team;
import nfl.main.service.ServiceInterface;

@Controller
@RequestMapping("/admin")
public class AdministrationController {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private AuthorDAO authorDAO;
	@Autowired
	private DivisionDAO divisionDAO;
	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private TeamDAO teamDAO;
	@Autowired
	private ImageDAO imageDAO;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private ServiceInterface serviceInterface;

	/************* Blog *******************/

	@RequestMapping("/blog-list")
	public String getBlogList(Principal principal, Model model) {
		model.addAttribute("blogList", blogDAO.getBlogsWithComments());
		model.addAttribute("authorList", authorDAO.getAuthors());
		model.addAttribute("categoryList", categoryDAO.getCategories());
		model.addAttribute("blog", new Blog());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "blog-list";
	}

	@RequestMapping("/blog-form")
	public String getBlogForm(Principal principal, Model model) {
		Blog blog = new Blog();
		List<Team> teamList = teamDAO.getTeams();
		List<Category> categoryList = categoryDAO.getCategories();
		List<Author> authorList = authorDAO.getAuthors();
		model.addAttribute("authorList", authorList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("teamList", teamList);
		model.addAttribute("blog", blog);
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "blog-form";
	}

	@RequestMapping("/blog-form-update")
	public String getBlogUpdateForm(@RequestParam int id, Principal principal, Model model) {
		Blog blog = blogDAO.getBlogWithInitialize(id);
		List<Team> teamList = teamDAO.getTeams();
		List<Category> categoryList = categoryDAO.getCategories();
		List<Author> authorList = authorDAO.getAuthors();
		model.addAttribute("authorList", authorList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("teamList", teamList);
		model.addAttribute("imageList", blog.getImages());
		model.addAttribute("blog", blog);
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "blog-form";
	}

	@RequestMapping("/blog-save")
	public String saveBlog(@RequestParam("files") MultipartFile[] files, @Valid @ModelAttribute Blog blog,
			BindingResult result, Model model, Principal principal) {

		if (blog.getId() == 0) {

			for (int i = 0; i < files.length; i++) {
				try {
					byte[] bytes = files[i].getBytes();
					if (files[i].getBytes().length != 0 && result.hasErrors()) {
						List<Team> teamList = teamDAO.getTeams();
						List<Category> categoryList = categoryDAO.getCategories();
						List<Author> authorList = authorDAO.getAuthors();
						model.addAttribute("authorList", authorList);
						model.addAttribute("categoryList", categoryList);
						model.addAttribute("teamList", teamList);
						model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
						return "blog-form";
					}
					if (files[i].getBytes().length == 0 && result.hasErrors()) {
						List<Team> teamList = teamDAO.getTeams();
						List<Category> categoryList = categoryDAO.getCategories();
						List<Author> authorList = authorDAO.getAuthors();
						model.addAttribute("authorList", authorList);
						model.addAttribute("categoryList", categoryList);
						model.addAttribute("teamList", teamList);
						model.addAttribute("message", "required field");
						model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
						return "blog-form";
					}
					if (files[i].getBytes().length == 0) {
						List<Team> teamList = teamDAO.getTeams();
						List<Category> categoryList = categoryDAO.getCategories();
						List<Author> authorList = authorDAO.getAuthors();
						model.addAttribute("authorList", authorList);
						model.addAttribute("categoryList", categoryList);
						model.addAttribute("teamList", teamList);
						model.addAttribute("message", "required field");
						model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
						return "blog-form";
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (result.hasErrors()) {
			List<Team> teamList = teamDAO.getTeams();
			List<Category> categoryList = categoryDAO.getCategories();
			List<Author> authorList = authorDAO.getAuthors();
			model.addAttribute("authorList", authorList);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("teamList", teamList);
			model.addAttribute("imageList", blogDAO.getBlogWithInitialize(blog.getId()).getImages());
			model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
			return "blog-form";
		}

		int numImage = 0;

		if (blog.getId() != 0) {
			numImage = imageDAO.getImagesPerBlogId(blog.getId()).size();
		}

		Category category = categoryDAO.getCategory(blog.getCategory().getId());
		Author author = authorDAO.getAuthor(blog.getAuthor().getId());

		List<Integer> ids = new ArrayList<>();
		for (Team team : blog.getTeams()) {
			ids.add(Integer.parseInt(team.getName()));
		}

		List<Team> list = teamDAO.getTeamsById(ids);
		blog.setCategory(category);
		blog.setAuthor(author);
		blog.setTeams(list);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		blog.setDate(dtf.format(now));
		blog.setTitleURL(serviceInterface.getTitleURL(blog.getTitle()));
		blogDAO.saveBlog(blog);

		for (int i = 0; i < files.length; i++) {
			try {
				byte[] bytes = files[i].getBytes();
				if (files[i].getBytes().length != 0) {
					String rootPath = "C:\\Users\\perica.simic\\Documents\\workspace-spring\\NFL\\WebContent\\resources";
					File dir = new File(rootPath + File.separator + "img");
					if (!dir.exists())
						dir.mkdirs();
					File serverFile;
					if (numImage == 0) {
						serverFile = new File(
								dir.getAbsolutePath() + File.separator + "image" + blog.getId() + i + ".jpg");
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						Image image = new Image("img/" + "image" + blog.getId() + i + ".jpg", false, blog);
						imageDAO.saveImage(image);
						blog.setImage(image);
						blogDAO.saveBlog(blog);
					} else {
						serverFile = new File(
								dir.getAbsolutePath() + File.separator + "image" + blog.getId() + numImage + ".jpg");
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						Image image = new Image("img/" + "image" + blog.getId() + numImage + ".jpg", false, blog);
						imageDAO.saveImage(image);
						numImage++;
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return "redirect:/admin/blog-list";
	}

	@RequestMapping("/blog-enable")
	public String blogEnable(@RequestParam int id) {
		blogDAO.enableBlog(id);
		return "redirect:/admin/blog-list";
	}

	@RequestMapping("/blog-important")
	public String blogImportant(@RequestParam int id) {
		blogDAO.importantBlog(id);
		return "redirect:/admin/blog-list";
	}

	@RequestMapping(value = "/search-blog")
	public String blogSearch(@ModelAttribute Blog blog, Principal principal, Model model) {

		if (blog.getTitle() != null || blog.getTitle() != "") {
			model.addAttribute("blogList", blogDAO.getBlogsPerSearchTitle(blog.getTitle()));
		}

		if (blog.getAuthor() != null) {
			model.addAttribute("blogList", blogDAO.getBlogsPerSearchAuthor(blog.getAuthor().getId()));
		}

		if (blog.getCategory() != null) {
			model.addAttribute("blogList", blogDAO.getBlogsPerSearchCategory(blog.getCategory().getId()));
		}

		model.addAttribute("authorList", authorDAO.getAuthors());
		model.addAttribute("categoryList", categoryDAO.getCategories());
		model.addAttribute("blog", new Blog());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "blog-list";
	}

	@RequestMapping(value = "/search-blog-status")
	public String blogSearchPerStatus(@ModelAttribute Blog blog, Principal principal, Model model) {
		model.addAttribute("blogList", blogDAO.getBlogsPerStatus(blog.getEnabled()));
		model.addAttribute("authorList", authorDAO.getAuthors());
		model.addAttribute("categoryList", categoryDAO.getCategories());
		model.addAttribute("blog", new Blog());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "blog-list";
	}

	@RequestMapping("/blog-delete")
	public String deleteBlog(@RequestParam int id) {
		blogDAO.deleteBlog(id);
		return "redirect:/admin/blog-list";
	}

	/************* Author ****************/

	@RequestMapping("/author-list")
	public String getAuthorList(Model model, Principal principal) {

		model.addAttribute("authorList", authorDAO.getAuthors());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		model.addAttribute("authorSearch", new Author());
		model.addAttribute("blog", new Blog());
		return "author-list";
	}

	@RequestMapping("/author-form")
	public String getAuthorForm(Principal principal, Model model) {

		Author author = new Author();
		model.addAttribute("author", author);
		model.addAttribute("roles", authorDAO.getRoles());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "author-form";
	}

	@RequestMapping("/author-edit-roles")
	public String getAuthorUpdateForm(@RequestParam int id,Principal principal, Model model) {
		Author a = authorDAO.getAuthor(id);
		a.getAuthorities().clear();
		model.addAttribute("author", a);
		Author au = authorDAO.getAuthor(id);
		model.addAttribute("authorR", au);
		model.addAttribute("roles", authorDAO.getRoles());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "author-edit-roles";
	}
	
	@RequestMapping("/author-edit-role-action")
	public String getAuthorEditRoleAction(@Valid @ModelAttribute Author author, BindingResult result, Model model, Principal principal) {
		
		if(result.hasErrors()) {
			Author a = authorDAO.getAuthor(author.getId());
			a.getAuthorities().clear();
			model.addAttribute("author", a);
			Author au = authorDAO.getAuthor(author.getId());
			model.addAttribute("authorR", au);
			model.addAttribute("roles", authorDAO.getRoles());
			model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
			model.addAttribute("error", "required one role");
			return "author-edit-roles";
		}
		
		authorDAO.editRoles(author);
		
		return "redirect:/admin/author-list";
	}

	@RequestMapping("/author-save")
	public String saveAuthor(@RequestParam("file") MultipartFile file, Principal principal, Model model,
			@Valid @ModelAttribute Author author, BindingResult result) {

		if (file.isEmpty() && result.hasErrors()) {
			model.addAttribute("message", "required field");
			model.addAttribute("roles", authorDAO.getRoles());
			model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
			return "author-form";
		}

		if (result.hasErrors()) {
			model.addAttribute("roles", authorDAO.getRoles());
			model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
			return "author-form";
		}

		if (file.isEmpty()) {
			model.addAttribute("roles", authorDAO.getRoles());
			model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
			model.addAttribute("message", "required field");
			return "author-form";
		}
		
		if(authorDAO.getAuthorPerUsername(author.getUsername()) != null) {
			model.addAttribute("roles", authorDAO.getRoles());
			model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
			model.addAttribute("userMessage", "*username is used");
			return "author-form";
		}

		try {

			byte[] bytes = file.getBytes();
			String rootPath = "C:\\Users\\perica.simic\\Documents\\workspace-spring\\NFL\\WebContent\\resources";
			File dir = new File(rootPath + File.separator + "img");
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + author.getUsername() + ".jpg");
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			author.setImage("img/" + author.getUsername() + ".jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String passEncode = new BCryptPasswordEncoder().encode(author.getPassword());
		author.setPassword("{bcrypt}" + passEncode);
		author.setEnabled(true);
		authorDAO.saveAuthor(author);
		authorDAO.importUsername(author);
		return "redirect:/admin/author-list";
	}

	@RequestMapping("/user-edit-profile")
	public String getUserEditProfile(Principal principal, Model model) {
		Author author = authorDAO.getAuthorPerUsername(principal.getName());
		model.addAttribute("author", author);
		return "user-edit-profile";
	}

	@RequestMapping("/author-edit")
	public String getAuthorEdit(@RequestParam("file") MultipartFile file, Model model,
			@Valid @ModelAttribute Author author, BindingResult result) {

		if (result.hasErrors()) {
			return "user-edit-profile";
		}
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String rootPath = "C:\\Users\\perica.simic\\Documents\\workspace-spring\\NFL\\WebContent\\resources";
				File dir = new File(rootPath + File.separator + "img");
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath() + File.separator + author.getUsername() + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				author.setImage("img/" + author.getUsername() + ".jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		authorDAO.saveAuthor(author);
		authorDAO.importUsername(author);
		return "redirect:/admin/author-list";
	}
	
	@RequestMapping(value = "/search-author")
	public String authorSearch(@ModelAttribute Blog blog, Principal principal, Model model) {
		model.addAttribute("blogList", blogDAO.getBlogsPerStatus(blog.getEnabled()));
		model.addAttribute("authorList", authorDAO.getAuthors());
		model.addAttribute("categoryList", categoryDAO.getCategories());
		model.addAttribute("blog", new Blog());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "blog-list";
	}

	@RequestMapping("/author-enable")
	public String authorEnable(@RequestParam int id) {
		authorDAO.enableAuthor(id);
		return "redirect:/admin/author-list";
	}

	@RequestMapping("/author-delete")
	public String deleteAuthor(@RequestParam int id) {
		authorDAO.deleteAuthor(id);
		return "redirect:/admin/author-list";
	}

	@RequestMapping("/author-photo-delete")
	public String deleteAuthorPhoto(@RequestParam int id, Model model) {
		Author author = authorDAO.getAuthor(id);
		author.setImage(null);
		authorDAO.saveAuthor(author);
		model.addAttribute("author", author);
		return "user-edit-profile";
	}

	@RequestMapping("/author-change-password")
	public String authorChangePassword(Principal principal, Model model) {

		Author author = authorDAO.getAuthorPerUsername(principal.getName());
		model.addAttribute("changePassword", new ChangePassword());
		model.addAttribute("loginAuthor", author);
		return "author-change-password";
	}

	@RequestMapping("/author-change-password-action")
	public String authorChangePasswordAction(@ModelAttribute ChangePassword changePassword, Principal principal,
			Model model) {

		Author author = authorDAO.getAuthorPerUsername(principal.getName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (encoder.matches(changePassword.getOldPassword(), author.getPassword().replace("{bcrypt}", ""))) {
			author.setPassword("{bcrypt}" + encoder.encode(changePassword.getNewPassword()));
			authorDAO.saveAuthor(author);
			authorDAO.importUsername(author);
		} else {
			model.addAttribute("message", "**The old password is not good");
			model.addAttribute("changePassword", new ChangePassword());
			model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
			return "author-change-password";
		}

		return "redirect:/admin/author-list";
	}

	/************* Category ****************/

	@RequestMapping("/category-list")
	public String getCategoryList(Principal principal, Model model) {
		List<Category> categoryList = categoryDAO.getCategoriesPerNumOrder();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "category-list";
	}

	@RequestMapping("/category-form")
	public String getCategoryForm(Model model, Principal principal) {

		Category category = new Category();
		model.addAttribute("category", category);
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));

		return "category-form";
	}

	@RequestMapping("/category-form-update")
	public String getCategoryUpdateForm(@RequestParam int id, Model model, Principal principal) {
		Category category = categoryDAO.getCategory(id);
		model.addAttribute("category", category);
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "category-form";
	}

	@RequestMapping("/category-save")
	public String saveCategory(@Valid @ModelAttribute Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "category-form";
		}
		categoryDAO.saveCategory(category);
		return "redirect:/admin/category-list";
	}

	@RequestMapping("/category-delete")
	public String deleteCategory(@RequestParam int id) {
		categoryDAO.deleteCategory(id);
		return "redirect:/admin/category-list";
	}

	/************* Division ****************/

	@RequestMapping("/division-list")
	public String getDivisionList(Model model, Principal principal) {
		model.addAttribute("divisionList", divisionDAO.getDivisions());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "division-list";
	}

	@RequestMapping("/division-form")
	public String getDivisionForm(Model model, Principal principal) {

		Division division = new Division();
		model.addAttribute("division", division);
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));

		return "division-form";
	}

	@RequestMapping("/division-form-update")
	public String getDivisionUpdateForm(@RequestParam int id, Model model, Principal principal) {
		model.addAttribute("division", divisionDAO.getDivision(id));
		model.addAttribute("teamList", divisionDAO.getDivision(id).getTeams());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "division-form";
	}

	@RequestMapping("/division-save")
	public String saveDivision(@RequestParam("file") MultipartFile file, Model model,
			@Valid @ModelAttribute Division division, BindingResult result) {

		if (division.getId() != 0 && result.hasErrors()) {
			return "division-form";
		}

		if (file.isEmpty() && result.hasErrors()) {
			model.addAttribute("message", "required field");
			return "division-form";
		}

		if (result.hasErrors()) {
			return "division-form";
		}

		if (division.getId() == 0 && file.isEmpty()) {
			model.addAttribute("message", "required field");
			return "division-form";
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String rootPath = "C:\\Users\\perica.simic\\Documents\\workspace-spring\\NFL\\WebContent\\resources";
				File dir = new File(rootPath + File.separator + "img");
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath() + File.separator
						+ division.getTitleButton().replaceAll("\\s+", "") + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				division.setImage("img/" + division.getTitleButton().replaceAll("\\s+", "") + ".jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		divisionDAO.saveDivision(division);
		return "redirect:/admin/division-list";
	}

	@RequestMapping("/division-enable")
	public String divisionEnable(@RequestParam int id) {
		divisionDAO.enableDivision(id);
		return "redirect:/admin/division-list";
	}

	@RequestMapping("/division-delete")
	public String deleteDivision(@RequestParam int id) {
		divisionDAO.deleteDivision(id);
		return "redirect:/admin/division-list";
	}

	/************* Team ****************/

	@RequestMapping("/team-list")
	public String getTeamList(Model model, Principal principal) {
		model.addAttribute("teamList", teamDAO.getTeams());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "team-list";
	}

	@RequestMapping("/team-form")
	public String getTeamForm(Model model, Principal principal) {
		Team team = new Team();
		model.addAttribute("team", team);
		model.addAttribute("divisionList", divisionDAO.getDivisions());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "team-form";
	}

	@RequestMapping("/team-form-update")
	public String getTeamUpdateForm(@RequestParam int id, Model model, Principal principal) {
		model.addAttribute("team", teamDAO.getTeam(id));
		model.addAttribute("divisionList", divisionDAO.getDivisions());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "team-form";
	}

	@RequestMapping("/team-save")
	public String saveTeam(@RequestParam("file") MultipartFile file, @Valid @ModelAttribute Team team,
			BindingResult result, Model model) {

		if (team.getId() != 0 && result.hasErrors()) {
			model.addAttribute("divisionList", divisionDAO.getDivisions());
			return "team-form";
		}

		if (file.isEmpty() && result.hasErrors()) {
			model.addAttribute("divisionList", divisionDAO.getDivisions());
			model.addAttribute("message", "required field");
			return "team-form";
		}

		if (result.hasErrors()) {
			model.addAttribute("divisionList", divisionDAO.getDivisions());
			return "team-form";
		}

		if (team.getId() == 0 && file.isEmpty()) {
			model.addAttribute("divisionList", divisionDAO.getDivisions());
			model.addAttribute("message", "required field");
			return "division-form";
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String rootPath = "C:\\Users\\perica.simic\\Documents\\workspace-spring\\NFL\\WebContent\\resources";
				File dir = new File(rootPath + File.separator + "img");
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + team.getName().replaceAll("\\s+", "") + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				team.setImage("img/" + team.getName().replaceAll("\\s+", "") + ".jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Division division = divisionDAO.getDivision(team.getDivision().getId());
		team.setDivision(division);
		teamDAO.saveTeam(team);
		return "redirect:/admin/team-list";
	}

	@RequestMapping("/team-delete")
	public String deleteTeam(@RequestParam int id) {
		teamDAO.deleteTeam(id);
		return "redirect:/admin/team-list";
	}

	/************* Contact *******************/

	@RequestMapping("/contact-list")
	public String getContactList(Model model, Principal principal) {
		model.addAttribute("contactList", contactDAO.getContacts());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "contact-list";
	}

	@RequestMapping("/contact-delete")
	public String deleteContact(@RequestParam int id) {
		contactDAO.deleteContact(id);
		return "redirect:/admin/contact-list";
	}

	@RequestMapping("/contact-details")
	public String getContactDetails(@RequestParam int id, Model model, Principal principal) {
		Contact contact = contactDAO.getContact(id);
		if (!contact.isSeen()) {
			contactDAO.seenContact(id);
		}
		model.addAttribute("contact", contact);
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "contact-details";
	}

	/************* Comment *******************/

	@RequestMapping("/comment-list")
	public String getCommentList(Model model, Principal principal) {
		model.addAttribute("commentList", commentDAO.getComments());
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "comment-list";
	}

	@RequestMapping("/comment-search")
	public String getCommentSearch(@RequestParam int id, Model model, Principal principal) {
		model.addAttribute("commentList", commentDAO.getCommentsPerBlogID(id));
		model.addAttribute("loginAuthor", authorDAO.getAuthorPerUsername(principal.getName()));
		return "comment-list";
	}

	@RequestMapping("/comment-blogId")
	public String getCommentBlogID(@RequestParam int id, Model model) {
		Blog blog = blogDAO.getBlog(id);
		return "redirect:/post/" + blog.getTitleURL();
	}

	@RequestMapping("/comment-enable")
	public String commentEnable(@RequestParam int id, Model model) {
		commentDAO.enableComment(id);
		return "redirect:/admin/comment-list";
	}

}
