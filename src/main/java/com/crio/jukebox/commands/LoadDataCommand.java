package com.crio.jukebox.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.crio.jukebox.services.ISongService;  
public class LoadDataCommand implements ICommand {

    private final ISongService songService;

    public LoadDataCommand(ISongService songService) {
        this.songService = songService;
    }

    private static final List<String> getRecordsFromLine(String line){
        List<String> record = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	            record.add(rowScanner.next());
	        }
	    }
        return record;
    }

    @Override
    public void execute(List<String> tokens) throws FileNotFoundException {
        // TODO Auto-generated method stub

        String csvFileName = tokens.get(1);
        Scanner scanner = new Scanner(new File(csvFileName));

        List<List<String>> records = new ArrayList<>();
        
        while(scanner.hasNextLine()){
            records.add(getRecordsFromLine(scanner.nextLine()));
        }
        // System.out.println(records);
        for(int i=0; i<records.size(); i++){
            List<String> record = records.get(i);
            
            // if(record.size()==5){
                List<String> featuredArtists = Arrays.asList(record.get(5).split("#"));
                songService.createSong(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4),featuredArtists);
            // }
            // else if(record.size()==6){
            //     List<String> featuredArtists = Arrays.asList(record.get(5).split("#"));
            //     songService.createSong(record.get(1), record.get(2), record.get(3), record.get(4), featuredArtists);
            // }
        }

        System.out.println("Songs Loaded successfully");

    }


}
