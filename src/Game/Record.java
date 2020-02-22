package Game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Record {
	
	int read(String f) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
        int[] array = new int[1];
        array = reader.lines().mapToInt(Integer::parseInt).toArray();
        return array[0];
	}
    
    void write(long stmt, String f) throws IOException {
    	FileWriter fr = new FileWriter(f,false);
    	String s = Long.toString(stmt);
    	fr.write(s);
    	fr.close();
    }
    
}
