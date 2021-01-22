import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public abstract class Helper {
	public String S_start_state = "";
	public String A_final_state = "";
	public String[] E;
	public String[] Q;
	public ArrayList<String> path=new ArrayList<>();
	

	public void readFile() {
		try {
			File myObj = new File("DFA");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine().trim();
				String[] input = data.split("=");
				if (data.startsWith("S")) {
					S_start_state = input[1];
				} else if (data.startsWith("A")) {
					A_final_state = input[1];
				} else if (data.startsWith("E")) {
					E = input[1].split(",");
				}else if (data.startsWith("Q")) {
					Q = input[1].split(",");
				}else {
						path.add(data);
				}
				
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		}
	}
}
