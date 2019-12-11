import org.json.simple.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class will output two different file a .txt and a .json file 
 * it will save the data in a formated way as display in the UI and that is the data that it will be saving 
 * */
public class Writer {
	
	/**
	 * This function will save the data and output a text file
	 * it will only only need two inout to make this function work
	 * @param the desired name for the .txt file, in the ArrayList<Model>  
	 * @return true if everything work out fine 
	 * false if a error has been catch 
	 * */
	public static boolean toTextFile(File fname, ArrayList<Model> mod) {
		PrintWriter pw;
		try {//checking if everything is OK
			pw = new PrintWriter(new BufferedWriter(new FileWriter(fname)));			
			
		}catch(Exception ex) {
			return false;
		}		
		
		String line = "test";
		for(int i = 0; i<mod.size(); i++) {
			
			line = String.format("%s\t%25s\t%10s\n", mod.get(i).getRank(), mod.get(i).getSite(), mod.get(i).getPeople() );
			
			pw.print(line);	
		}// end of for loop
		pw.close();
		return true;		
	}//end of toTextFile()
	
	
	/**
	 * This class will make make a .json file and save the <Model> objects  as a list 
	 * to make this function work just enter a FIle and a ArrayList  in to the pararmter 
	 * @param name of File, ArrayList of Model 
	 * @return  will returns true if everything went well
	 * return false when there is a error 
	 * */
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
				array.add(modObj);
			
			}
			JSONObject outer = new JSONObject();
			outer.put("Model", array);
			pw.println(outer.toJSONString());
			pw.close();
			return true;
			
		}catch (Exception ex){
			return false;
		}
	}//end of JSON()
	
	
}//end of class
