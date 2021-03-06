package id.ac.its.kelompok;

import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class SerialScoreZen{
    private static ObjectOutputStream output;

    public static void openFile()
    {
        try {
            output = new ObjectOutputStream(
                    Files.newOutputStream(Paths.get("ScoreZen.ser")));
        }
        catch (IOException ioException){
            System.err.println("Error opening file. Termianting");
            System.exit(1);
        }
    }

    public static void addRecords(String nama,int score)
    {
        try
        {
            Score record = new Score(nama,score);
            System.out.print(record.getScore());
            System.out.println("Masuk add records %n");
            output.writeObject(record);
            System.out.println("Add records success %n");
        }
        
        catch(IOException ioException){
            System.err.println("Error writing file. Terminating");
        }
    }

    public static void closeFile()
    {
        try {
            if(output != null)
                output.close();
        }
        catch(IOException ioException){
            System.err.println("Error closing file. Terminating");
        }
    }
}
