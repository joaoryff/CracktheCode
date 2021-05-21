package corbos.crackthecode.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Round {
    private int roundId;
    private int gameId;
    private String guess;
    private Timestamp time;
    private String result;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Round(int roundId, int gameId, String guess, Timestamp time, String result) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guess = guess;
        this.time = time;
        this.result = result;
    }

    public Round() {
        this.time = time;
        this.result = "Wrong";
    }

    public Round(int gameId, String guess, String result) {
        this.gameId = gameId;
        this.guess = guess;
        this.result = result;
    }


}
