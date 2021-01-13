package id.ac.its.kelompok;

import java.io.Serializable;

public class ScoreClassic implements Serializable 
{
    private String nama;
    private int score;

    public ScoreClassic() {
        this("", 0);
    }

    public ScoreClassic(String nama, int score) {
        this.nama = nama;
        this.score = score;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    
}
