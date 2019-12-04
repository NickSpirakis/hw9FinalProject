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
	
	
	public void Scraper(String webName) {
		try {
			URL link = new URL(webName);
			Scanner in = new Scanner(link.openStream());
			while(in.hasNextLine()) {
				///do a systemout.println()??? for texting
				System.out.println(in.nextLine());
				
			}
			in.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
