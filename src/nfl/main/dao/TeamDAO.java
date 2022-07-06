package nfl.main.dao;

import java.util.List;

import nfl.main.entity.Team;

public interface TeamDAO {

	public List<Team> getTeams();

	public Team getTeam(int id);
	
	public Team getTeamWithBlogs(int id);

	public void saveTeam(Team team);

	public void deleteTeam(int id);
	
	public List<Team> getTeamsById(List<Integer> ids);
	
	public Team getTeamByName(String name);
	
	public List<Team> getTeamsOrderByBlog();

}
