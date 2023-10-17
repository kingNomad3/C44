package com.example.tpfinal;

public class Score {
    // Classe créée pour faciliter l'entrée de scores dans la base de données

    private int score; // Variable pour stocker le score

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
