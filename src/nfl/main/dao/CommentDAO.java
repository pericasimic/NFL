package nfl.main.dao;

import java.util.List;

import nfl.main.entity.Comment;

public interface CommentDAO {

	public List<Comment> getComments();
	
	public List<Comment> getCommentsPerBlogID(int id);
	
	public List<Comment> getCommentsPerBlogIDEnabled(int id);
	
	public void enableComment(int id);
	
	public Comment getComment(int id);
	
	public void saveComment(Comment comment);
}
