package com.crio.jukebox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.crio.jukebox.appConfig.ApplicationConfig;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.exceptions.CommandNotFoundException;

class Abc{
	List<String> list;

	public Abc(List<String> list){
		this.list = list;
	}

	void addData(String data){
		this.list.add(data);
	}
	
	void removeData(String data){
		this.list.remove(data);
	}

	@Override
	public String toString() {
		return "Abc [list=" + list + "]";
	}

}

public class App {

	// private static final String COMMA_DELIMITER = ",";

    // private static List<String> getRecordFromLine(String line) {
	//     List<String> values = new ArrayList<String>();
	//     try (Scanner rowScanner = new Scanner(line)) {
	//         rowScanner.useDelimiter(COMMA_DELIMITER);
	//         while (rowScanner.hasNext()) {
	//             values.add(rowScanner.next());
	//         }
	//     }
	//     return values;
	// }
	
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
	public static void main(String[] args) throws FileNotFoundException {

		// List<String> list = new ArrayList<String>(){
		// 	{
		// 		add("1");
		// 		add("2");
		// 	}
		// };

		// Abc abc = new Abc(list);
		// abc.addData("3");
		// System.out.println(abc);
		// abc.removeData("3");
		// System.out.println(abc);
    //     List<List<String>> records = new ArrayList<>();
	// 	try (Scanner scanner = new Scanner(new File("songs.csv"))) {
	// 	    while (scanner.hasNextLine()) {
	// 	        records.add(getRecordFromLine(scanner.nextLine()));
	// 	    }
	// 	}

    //     System.out.println(records);
	// }

		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT_FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
        
	}

    public static void run(List<String> commandLineArgs) {
    // Complete the final logic to run the complete program.
	ApplicationConfig applicationConfig = new ApplicationConfig();
	CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
	BufferedReader reader;
	String inputFile = commandLineArgs.get(0).split("=")[1];
	commandLineArgs.remove(0);
	try {
		reader = new BufferedReader(new FileReader(inputFile));
		String line = reader.readLine();
		while (line != null) {
			// System.out.println(line);
			List<String> tokens = Arrays.asList(line.split(" "));
			// System.out.println(tokens);
			commandInvoker.executeCommad(tokens.get(0),tokens);
			// read next line
			line = reader.readLine();
		}
		reader.close();
	} catch (IOException | CommandNotFoundException e) {
		e.printStackTrace();
	}

}
    }
