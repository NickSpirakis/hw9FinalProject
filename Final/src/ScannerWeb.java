import java.net.URL;
import java.util.ArrayList; 
import java.util.Scanner;
//us this function openStream()  which will funnel the web data into the applcaiotn 
//need a try catch block


/* The website that we are going to use is 
 * https://www.quantcast.com/top-sites/US?userView=Public 
 * a list of the most visited website
 */
public class ScannerWeb {
	private static ArrayList<Model> webList = new ArrayList<Model>();
	private static String rank, name, number;
	private static Model temp;
	
	
	/////////////using this main for test then remove it 
	public static void main(String[] args) {
		ScannerWeb.scraper("https://www.quantcast.com/top-sites/US?userView=Public");
		for(int i =0; i<webList.size(); i++) {
			System.out.println(webList.get(i));
			}
		
	}//end of main/// fir testing 
	
	
	public static void scraper(String webName) {
		try {
			URL link = new URL(webName);//later put in webName
			Scanner in = new Scanner(link.openStream());
			//t meaning testing
			String t1,t2,t3,nextLine;
			
			
			t1 = "<td class=\"rank\"  id=\"rank-";
			t2 = "<img class=\"favicon\"";
			t3 = "<td class=\"digit ";
			
			
			while(in.hasNextLine()) {
				rank = "-1" ;
				name = "-1" ;
				number = "-1" ;
				temp = new Model();
				
				
				nextLine = in.nextLine();//temp String
				
				
				/* This part is going to filter and parse out the line to get one word and will save it to String rank */
				
						//try to get the same line 				putting a Lower bound					puting a upper bound
				if(   (t1.compareTo(nextLine.trim()) <= 0) && (nextLine.trim().length()>=30) && (nextLine.trim().length()<=32) ) {
					//System.out.println(nextLine.trim()); //testing
					
					//trying to just get "rank-###"
					if(nextLine.trim().substring(26,27).equals("-") ){//some more filtering
						//System.out.println(nextLine.trim().substring(22,30));//remove the \" //testing
						rank = nextLine.trim().substring(22,30);
						rank  = rank.replaceAll("\"","").replaceAll(">", "");
						//System.out.println(rank);//testing
						temp.setRank(rank);
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
							
						} else {name = "Hidden";}
						//System.out.println(name);//testing
						temp.setSite(name);
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
							number = in.nextLine().trim();
							
						} else {number = "---";}						
						//System.out.println(number);//testing
						temp.setPeople(number);

						
					}
					
				}//end of number filter
				/////////////fix ad re move to a differnt plaec
				if(  !(rank.equals("-1") && name.equals("-1") && number.equals("-1"))  ) {
					webList.add(temp);
				}
				
				
			}//end if while loop
			
			in.close();
		}catch (Exception ex) {
			//ex.printStackTrace(); //testing
			System.out.println("Something went wrong when trying to scan in the link.");
		}
	}//end of function
	

}//end of class




















