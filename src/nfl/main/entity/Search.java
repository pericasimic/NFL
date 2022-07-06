package nfl.main.entity;

public class Search {

	private String term;
	
	public Search() {
		
	}
	
	public Search(String term) {
		super();
		this.term = term;
	}

	public String getTerm() {
		return term;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
}
