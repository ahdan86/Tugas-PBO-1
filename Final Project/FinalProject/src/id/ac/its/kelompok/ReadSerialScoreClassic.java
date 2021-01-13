package id.ac.its.kelompok;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadSerialScoreClassic {
	private static ObjectInputStream input;
	public static int highscore;
	public static String nama;
    
	public static boolean openFile()
    {
        try {
			input = new ObjectInputStream(
					Files.newInputStream(Paths.get("ScoreClassic.ser")));
			return true;
		} catch (IOException ioException) {
			System.err.println("Error opening file");
			return false;
		}
    }

    public static void readRecords()
    {
        try
        {
        	ScoreClassic record = (ScoreClassic) input.readObject();
			highscore = record.getScore();
			nama = record.getNama();
			System.out.print(highscore);
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
	
	public static String getNama()
	{
		return nama;
	}
}
