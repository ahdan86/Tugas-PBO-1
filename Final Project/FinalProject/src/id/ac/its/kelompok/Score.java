package id.ac.its.kelompok;

import java.io.Serializable;

public class Score implements Serializable 
{
    private String nama;
    private int score;

    public Score() {
        this("", 0);
    }

    public Score(String nama, int score) {
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
