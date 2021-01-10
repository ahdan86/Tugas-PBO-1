package id.ac.its.kelompok;

import java.io.Serializable;

public class ScoreClassic implements Serializable 
{
    private static String nama;
    private static int score;

    public ScoreClassic() {
        this("", 0);
    }

    public ScoreClassic(String nama, int score) {
        this.nama = nama;
        this.score = score;
    }

    public static String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public static int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
