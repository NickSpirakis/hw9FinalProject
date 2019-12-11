import java.io.Serializable;

/**
 * This class will hold the any data that we need it to hold from the website  
 * we will be holding three data type form the website 
 * the tree data type are all String  
 * rank - will hold the number pos in the list that it is in
 * site - can hold the URL of website 
 * people - is going to hold number of people that visit the website per month
 * 
 * This has two constructors a default and one overload. The overload will need three String datatypes 
 * 
 */
public class Model implements Serializable {
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
	
	/**
	 * This is a overload constructor it will three String data types
	 * @param rank number,
	 * website name(URL), 
	 * number of people
	 * 
	 */
	public Model(String r, String s, String p) {
		setRank(r);
		setSite(s);
		setPeople(p);		
	}
	
	
	@Override
	public String toString() {
		String str = "";
		
		//str = str + String.format("%.10s   \t%s....................  %s", getRank(), getSite(), getPeople() );
		
		str = str + String.format("%s\t%25s\t%10s", getRank(), getSite(), getPeople() );
		
		return str;
	}	
	
}//end of class
