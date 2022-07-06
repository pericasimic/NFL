package nfl.main.dao;

import java.util.List;

import nfl.main.entity.Image;

public interface ImageDAO {

	public void saveImage(Image image);
	
	public List<Image> getImagesPerBlogId(int id);
	
}
