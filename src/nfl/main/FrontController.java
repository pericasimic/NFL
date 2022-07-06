package nfl.main;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nfl.main.dao.AuthorDAO;
import nfl.main.dao.BlogDAO;
import nfl.main.dao.CategoryDAO;
import nfl.main.dao.CommentDAO;
import nfl.main.dao.ContactDAO;
import nfl.main.dao.DivisionDAO;
import nfl.main.dao.TeamDAO;
import nfl.main.entity.Author;
import nfl.main.entity.Blog;
import nfl.main.entity.Category;
import nfl.main.entity.Comment;
import nfl.main.entity.Contact;
import nfl.main.entity.Search;
import nfl.main.entity.Team;
import nfl.main.service.ServiceInterface;

@Controller
@RequestMapping("/")
public class FrontController {

	@Autowired
	private DivisionDAO divisionDAO;
	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ServiceInterface serviceInterface;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private TeamDAO teamDAO;
	@Autowired
	private AuthorDAO authorDAO;
	@Autowired
	private CommentDAO commentDAO;

	/************* Blog Index *******************/

	@RequestMapping({ "/", "/index-page" })
	public String getIndexPage(Model model) {
		List<Blog> twelveBlogs = blogDAO.getLastTwelveBlogs();
		int loopNumber = 0;
		if (twelveBlogs.size() % 3 != 0) {
			loopNumber = twelveBlogs.size() / 3;
		} else {
			loopNumber = twelveBlogs.size() / 3 - 1;
		}
		model.addAttribute("loopNumber", loopNumber);
		model.addAttribute("divisionList", divisionDAO.getDivisionsPerNumOrder());
		model.addAttribute("importantBlogList", blogDAO.getThreeImportantBlogs());
		model.addAttribute("twelveBlogList", twelveBlogs);
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("search", new Search());
		return "front/index-page";
	}

	/************* Blog Category *******************/
	@RequestMapping("/category/{name}")
	public String getBlogCategory(@PathVariable String name, Model model) {
		Category category = categoryDAO.getCategoryByName(serviceInterface.getURLNameWithSpace(name));
		int size = blogDAO.getBlogsSizePerCategory(category.getId());
		long pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}

		model.addAttribute("category", category);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("activeNumber", 1);
		model.addAttribute("blogs", blogDAO.getBlogsPerCategory(category.getId(), 1));
		model.addAttribute("search", new Search());
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		return "front/blog-category";
	}

	@RequestMapping(value = "/category/category-page", params = { "id", "idCategory" })
	public String getBlogCategoryPage(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "idCategory", required = true) int idCategory, Model model) {
		int size = blogDAO.getBlogsSizePerCategory(idCategory);
		int pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}

		model.addAttribute("category", categoryDAO.getCategory(idCategory));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("activeNumber", id);
		model.addAttribute("blogs", blogDAO.getBlogsPerCategory(idCategory, id));
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("search", new Search());
		return "front/blog-category";
	}

	/************* Blog Post *******************/

	@RequestMapping("/blog-page")
	public String getBlogPage(Model model) {
		long size = blogDAO.getBlogsSize();
		long pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}
		model.addAttribute("activeNumber", 1);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("lastTwelveBlogs", blogDAO.getLastTwelveBlogs());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("search", new Search());
		return "front/blog-page";
	}

	@RequestMapping("/blog-page-item")
	public String getBlogItemPage(@RequestParam int id, Model model) {
		List<Blog> blogs = blogDAO.getBlogsPerPage(id);
		long size = blogDAO.getBlogsSize();
		long pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}
		model.addAttribute("activeNumber", id);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("lastTwelveBlogs", blogs);
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("search", new Search());

		return "front/blog-page";
	}

	@RequestMapping("/post/{title}")
	public String getBlogPost(@PathVariable String title, Model model) {
		List<Blog> blogs = blogDAO.getBlogsPerTitle(title);
		blogs.get(1).setNumberViews(blogs.get(1).getNumberViews() + 1);
		blogDAO.saveBlog(blogs.get(1));
		blogs.get(1).setComments(commentDAO.getCommentsPerBlogIDEnabled(blogs.get(1).getId()));
		model.addAttribute("blogs", blogs);
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("search", new Search());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		Comment comment = new Comment();
		comment.setBlog(blogs.get(1));
		model.addAttribute("comment", comment);
		return "front/blog-post";
	}

	@RequestMapping("/post/comment-save")
	public String saveComment(@Valid @ModelAttribute Comment comment) {
		comment.setBlog(blogDAO.getBlog(comment.getBlog().getId()));
		commentDAO.saveComment(comment);
		return "redirect:/post/" + comment.getBlog().getTitleURL();

	}

	/************* Blog Author *******************/

	@RequestMapping("/blog/{name}")
	public String getBlogAuthor(@PathVariable String name, Model model) {
		String firstName = serviceInterface.before(name, "-");
		String lastName = serviceInterface.after(name, "-");
		Author author = authorDAO.getAuthorPerNameAndLastname(firstName, lastName);

		int size = blogDAO.getBlogsSizePerAuthor(author.getId());
		long pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}
		model.addAttribute("activeNumber", 1);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("blogs", blogDAO.getBlogsPerAuthor(author.getId(), 1));
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("author", author);
		model.addAttribute("search", new Search());
		return "front/blog-author";
	}

	@RequestMapping(value = "/author/author-page", params = { "id", "idAuthor" })
	public String getBlogAuthorPage(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "idAuthor", required = true) int idAuthor, Model model) {
		int size = blogDAO.getBlogsSizePerAuthor(idAuthor);
		int pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}

		model.addAttribute("author", authorDAO.getAuthor(idAuthor));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("activeNumber", id);
		model.addAttribute("blogs", blogDAO.getBlogsPerAuthor(idAuthor, id));
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("search", new Search());
		return "front/blog-author";
	}

	/************* Blog Team *******************/

	@RequestMapping("/team/{name}")
	public String getTeam(@PathVariable String name, Model model) {

		Team team = teamDAO.getTeamByName(serviceInterface.getURLNameWithSpace(name));
		long size = team.getBlogs().size();
		long pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}
		model.addAttribute("activeNumber", 1);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("team", team);
		model.addAttribute("blogs", blogDAO.getBlogsPerTeam(team.getId(), 1));
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("search", new Search());

		return "front/blog-team";
	}

	@RequestMapping(value = "/team/blog-page-item", params = { "id", "idTeam" })
	public String getTeamBlogPageItem(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "idTeam", required = true) int idTeam, Model model) {
		Team team = teamDAO.getTeamWithBlogs(idTeam);
		long size = team.getBlogs().size();
		long pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}
		model.addAttribute("activeNumber", id);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("team", team);
		model.addAttribute("blogs", blogDAO.getBlogsPerTeam(idTeam, id));
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("search", new Search());

		return "front/blog-team";
	}

	/************* Blog Search *******************/

	@RequestMapping("/blog-search")
	public String getSearch(@ModelAttribute Search search, Model model) {
		int size = blogDAO.getBlogsSizePerTerm(search.getTerm());
		int pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}

		model.addAttribute("activeNumber", 1);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("blogItems", blogDAO.getBlogsPerWord(search.getTerm(), 1));
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("search", search);
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		return "front/blog-search";
	}

	@RequestMapping(value = "/blog-search-page", params = { "id", "word" })
	public String getSearchPage(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "word", required = true) String word, Model model) {

		int size = blogDAO.getBlogsSizePerTerm(word);
		int pageNumber = 1;
		if ((size % 12) != 0 && size > 12) {
			pageNumber = size / 12 + 1;
		}
		if ((size % 12) == 0 && size > 12) {
			pageNumber = size / 12;
		}

		model.addAttribute("activeNumber", id);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("blogItems", blogDAO.getBlogsPerWord(word, id));
		model.addAttribute("search", new Search(word));
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("listCategories", categoryDAO.getCategoriesWithBlogs());
		model.addAttribute("listTeams", teamDAO.getTeamsOrderByBlog());
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		return "front/blog-search";
	}

	/************* Blog Contact *******************/

	@RequestMapping("/contact-page")
	public String getContactPage(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("search", new Search());
		return "front/contact-page";
	}

	@RequestMapping("/contact-send")
	public String sendMessage(Model model, @Valid @ModelAttribute Contact contact, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
			model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
			model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
			model.addAttribute("search", new Search());
			return "front/contact-page";
		}
		contactDAO.saveContact(contact);
		String message = "Successfully sent";
		model.addAttribute("message", message);
		Contact newContact = new Contact();
		model.addAttribute("contact", newContact);
		model.addAttribute("lastThreeBlogs", blogDAO.getLastThreeBlogs());
		model.addAttribute("threeViewsBlogs", blogDAO.getThreeBlogsViews());
		model.addAttribute("categories", categoryDAO.getFourCategoriesPerNumOrder());
		model.addAttribute("search", new Search());
		return "front/contact-page";
	}

}
