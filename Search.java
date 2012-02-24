import java.util.ArrayList;
import java.util.regex.*;
import java.io.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
public class Search {
	static String input;
	static File fname;
	public String[] rStr;
	public double[] rDbl;
	private ArrayList<String> sar = new ArrayList<String>();
	private ArrayList<Double> iar = new ArrayList<Double>();
	public Search(String in, File name){
		input = new String(in);
		fname = name;
	}
	public void doSearch() throws IOException{	
		if(fname == null){
			fname = new File("o.txt");
		}
		FileReader nRead = new FileReader(fname);
		BufferedReader mRead = new BufferedReader(nRead);
		boolean runs = true;
		try{
			Pattern.compile(input);
		}catch(PatternSyntaxException d){
			JOptionPane.showMessageDialog(Front.frame,  "Invalid Search Term");
			runs = false;
		}
		
		while(mRead.ready() && runs){
			boolean matched = false;
			String str = mRead.readLine();
			int i = 0;
			String temp = new String();
			String temp2 = new String();
			while(str.charAt(i)!=' '){
				temp+=str.charAt(i);
				++i;
			}
			for(int j = i+1; j < str.length(); j++){
				temp2 += str.charAt(j);
			}
			
			if(Pattern.matches(input, (CharSequence)temp2)){
				sar.add(temp2);
				matched = true;
			}
			if(matched == true){
				iar.add(Double.parseDouble(temp));
			}
			}
		
	}
	public String[] getStringResults(){
		if(rStr == null){
			rStr = new String[sar.size()];
			System.out.println(sar.size());
			for(int i = 0; i < rStr.length; i++){
				rStr[i] = sar.get(i);
			}
		}
		return rStr;
	}
	public double[] getTimeStampResults(){
		if(rDbl == null){
			rDbl = new double[iar.size()];
			for(int i = 0; i < rDbl.length; i++){
				rDbl[i] = iar.get(i);
			}
		}
		return rDbl;
	}
	public void clear(){
			rStr = new String[0];
			rDbl = new double[0];
			iar = new ArrayList<Double>();
			sar = new ArrayList<String>();
	}
}