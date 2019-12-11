import java.net.URL;
import java.util.ArrayList; 
import java.util.Scanner;


/**
 * This class will go to the website and start scraping it for the HTML
 * the website that we are going to be using is 
 * https://www.quantcast.com/top-sites/US?userView=Public
 * 
 * The web-page will show a list of 100 website and rank them based on monthly traffic 
 * 
 * This class uses a ArralyList<Model> to hold the 100 different website
 * and has three String data type show it can make a new Model object to put into the list 
 * 
 */
public class ScannerWeb {
	private static ArrayList<Model> webList = new ArrayList<Model>();
	private static String rank="-1", name="-1", number="-1";
	
	/*
	/////////////using this main for testing /////////////////
	public static void main(String[] args) {
		ScannerWeb.scraper("https://www.quantcast.com/top-sites/US?userView=Public");
		for(int i =0; i<webList.size(); i++) {
			System.out.println(webList.get(i));
			}		
	}
	*/
	
	
	/**
	 * This is the function that needs to called to start the whole process of the scanning 
	 * this function will only need input 
	 * @param URL of the website 
	 * 
	 * */
	public static void scraper(String webName) {
		try {
			URL link = new URL(webName);
			Scanner in = new Scanner(link.openStream());
			//t meaning testing
			String t1,t2,t3,nextLine;
			
			/* These are the conditions string that will help me filter out most of the ode that I wont need */
			t1 = "<td class=\"rank\"  id=\"rank-";
			t2 = "<img class=\"favicon\"";
			t3 = "<td class=\"digit ";
			
			
			while(in.hasNextLine()) {				
				nextLine = in.nextLine();//temp String
				//System.out.println(nextLine);//testing				

				
				/* This part is going to filter and parse out the line to get one word and will save it to String rank */
				
						//try to get the same line 				putting a Lower bound					putting a upper bound
				if(   (t1.compareTo(nextLine.trim()) <= 0) && (nextLine.trim().length()>=30) && (nextLine.trim().length()<=32) ) {
					//System.out.println(nextLine.trim()); //testing
					
					//trying to just get "rank-###"
					if(nextLine.trim().substring(26,27).equals("-") ){//some more filtering
						//System.out.println(nextLine.trim().substring(22,30)); //testing
						rank = nextLine.trim().substring(22,30);
						rank  = rank.replaceAll("\"","").replaceAll(">", "");
						//System.out.println(rank);//testing
						
					}					
				}//end of rank filter
				
				
				
				/* going to get the name of the website by filtering and parsing*/
				if(  (t2.compareTo(nextLine.trim()) <= 0) && (nextLine.trim().length()>20 )    ) {
					//trying to get <img class="favicon" 
					
					//System.out.println(nextLine.trim().substring(0,20));//testing
					if( nextLine.trim().substring(0,20).equals(t2))  {//filtering out the other lines that we don't need
						//System.out.println(nextLine.trim());//test
						String [] parts = nextLine.trim().split(" ");
						
						if(parts.length == 6) {
							name = parts[2].replaceAll("\"","").replaceAll("name=","");							
							
						} else {//some of the data does not show the URL so putting it as 'Hidden'
							name = "Hidden";}
						//System.out.println(name);//testing
						
					}					
				}//end of name filter
				
				
				
				/*This will get the number of people that visit per month*/				
				if( (t3.compareTo(nextLine.trim())<=0) && (nextLine.trim().length()>=17)   ) {
					//System.out.println(nextLine.trim());//testing
					
					if(nextLine.trim().substring(11,16).equals("digit")) {
						//System.out.println(nextLine.trim());//testing //just getting number
						
						if(nextLine.trim().equals("<td class=\"digit \" >")) {
							in.nextLine();//skipping the blank lines with in the HTML
							in.nextLine();
							in.nextLine();
							number = in.nextLine().trim();	//getting the number 						
						} else {
							number = "---";}						
						//System.out.println(number);//testing											
					}					
				}//end of number filter
				
				
				
				/* This part will check to see if there is something in all of the three var's
				 * if soe the add it to the ArrayList  and then reset back the values to do the process over again */				
				if(  !(rank.equals("-1")) && !(name.equals("-1")) && !(number.equals("-1"))   )   {
					webList.add(new Model(rank,name,number));
					////reseting the values 
					rank = "-1" ;
					name = "-1" ;
					number = "-1" ;				
				}//end of checker
				
				
			}//end if while loop
			in.close();
		}catch (Exception ex) {
			//ex.printStackTrace(); //testing
			System.out.println("Something went wrong when trying to scan in the link.");
		}
	}//end of function

	
	/**
	 * This is to get the data and return it 
	 * @return a Array list that is in the class 
	 * */
	public static ArrayList<Model> getList(){
		return webList;
	}
	

}//end of class




















