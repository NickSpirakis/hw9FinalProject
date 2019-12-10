

//this will wrtie the data to the screen the cconsle most likey
//wtrie out a text file .txt
//write out a jason file .json

import org.json.simple.*;
import java.io.*;
import java.util.ArrayList;

public class Writer {
	
	//this function writes to a text file
	public static boolean toTextFile(File fname, ArrayList<Model> mod) {
		PrintWriter pw;
		try {//checking if everything is OK
			pw = new PrintWriter(new BufferedWriter(new FileWriter(fname)));			
			
		}catch(Exception ex) {
			return false;
		}		
		
		String line = "test";
		for(int i = 0; i<mod.size(); i++) {
			
			line = String.format("%s%25s %s", mod.get(i).getRank(), mod.get(i).getSite(), mod.get(i).getPeople() );
			
			pw.print(line);	
		}// end of for loop
		pw.close();
		return true;		
	}//end of toTextFile()
	
	
	public static boolean writeMembersToJSON(File fname, ArrayList<Model> mod) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fname)));
			JSONObject modObj;
			JSONArray array = new JSONArray();
			String line = "";
			for (Model m : mod) {
				modObj = new JSONObject();
				modObj.put("rank", m.getRank());
				modObj.put("name", m.getSite());
				modObj.put("number", m.getPeople());
			
			}
			JSONObject outer = new JSONObject();
			outer.put("Model", array);
			pw.println(outer.toJSONString());
			pw.close();
			return true;
			
		}catch (Exception ex){
			return false;
		}
	}
	
	
	
	
}//end of class
