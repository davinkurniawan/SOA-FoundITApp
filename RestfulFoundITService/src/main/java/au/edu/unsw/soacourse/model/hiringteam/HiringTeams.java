package au.edu.unsw.soacourse.model.hiringteam;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Reviewers")
@XmlAccessorType (XmlAccessType.FIELD)
public class HiringTeams {

	@XmlElement(name="Team")
	private ArrayList<Team> teams;
	
	public HiringTeams() {
		this.setTeams(new ArrayList<Team>());
	}

	public Team getTeam(String id) {
		for (Team team : teams) {
			if(team.getHtid().equalsIgnoreCase(id))
				return team;
		}
		
		return null;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}
	
	public boolean contains(String id) {
		for (Team t: this.teams) {
			if (t.getHtid().equals(id))
				return true;
		}
		return false;
	}
	
	public void addTeam(Team t) {
		this.teams.add(t);
	}
	

}
