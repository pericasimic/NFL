package nfl.main.dao;

import java.util.List;

import nfl.main.entity.Division;

public interface DivisionDAO {

	public List<Division> getDivisions();
	
	public List<Division> getDivisionsPerNumOrder();

	public Division getDivision(int id);

	public void saveDivision(Division division);

	public void deleteDivision(int id);
	
	public void enableDivision(int id); 

}
