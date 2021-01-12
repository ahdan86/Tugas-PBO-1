package id.ac.its.kelompok;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadSerialScoreClassic {
	private static ObjectInputStream input;
	public static int highscore;
    
	public static void openFile()
    {
        try {
			input = new ObjectInputStream(
					Files.newInputStream(Paths.get("ScoreClassic.ser")));
		} catch (IOException ioException) {
			System.err.println("Error opening file");
			System.exit(1);
		}
    }

    public static void readRecords()
    {
        try
        {
        	ScoreClassic record = (ScoreClassic) input.readObject();
            highscore = record.getScore();
        }
        
        catch (EOFException endOfFileException) {
			System.out.printf("%nNo more records%n");
		}
		catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Invalid object type. Terminating");
		}
		catch (IOException ioException) {
			System.err.println("Error reading from file. Terminating");
		}
    }

    public static void closeFile() {
		try {
			if(input != null)
			{
				input.close();
			}
		} catch (IOException ioException) {
			System.err.println("Error closing file. Terminating");
			System.exit(1);
		}
	}
    
    public static int getScore()
    {
    	return highscore;
    }
}
