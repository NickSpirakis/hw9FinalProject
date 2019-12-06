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
	ArrayList<Model> webList = new ArrayList<Model>();
	/////////////using this main for test then remove it 
	public static void main(String[] args) {
		ScannerWeb.scraper("https://www.quantcast.com/top-sites/US?userView=Public");
		
	}
	
	
	public static void scraper(String webName) {
		try {
			URL link = new URL(webName);//later put in webName
			Scanner in = new Scanner(link.openStream());
			while(in.hasNextLine()) {
				System.out.println(in.nextLine());
				
				
				/* this to pick ut and gettin the data in the website
				 * 
				 * <td class="rank"  id=
				 * 
				 * <img class="favicon" name  //// but this does get all the daat like the hidden 
				 * 
				 * <td class="digit  /// should be able to gett all the data but will need another ifstatmetn in the block
				 * 
				 */
				
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
