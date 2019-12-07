import java.io.Serializable;

/**
 * This class will hold the any data that we need it to hold from the website  
 * it has a toString that will format the data 
 * 
 * */

public class Model implements Serializable {
	/*
	 *rank will hold the number pos in the list that it s in
	 *site i s the name of website 
	 *people is the number of people that visit the website per month  
	 * */
	private String rank, site, people;
	
	//getters and setter 
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank.trim();
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {////might need to just need say hidden profile
		this.site = site.trim();
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {////might need to just need say hidden profile
		this.people = people.trim();
	}

	
	//The constructor methods 
	public Model() {
		rank = "";
		site = "";
		people = "";
		
	}
	//overload
	public Model(String r, String s, String p) {
		setRank(r);
		setSite(s);
		setPeople(p);		
	}
	
	
	@Override
	public String toString() {
		String str = "";
		
		//str = str + String.format("%.10s   \t%s....................  %s", getRank(), getSite(), getPeople() );
		
		str = str + String.format("%s%25s %s", getRank(), getSite(), getPeople() );
		
		return str;
	}	
	
}//end of class
