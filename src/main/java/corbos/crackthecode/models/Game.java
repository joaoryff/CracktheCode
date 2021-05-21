package corbos.crackthecode.models;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class Game {

    private int gameId;
    private String answer;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Game(int gameId, String answer, boolean status) {
        this.gameId = gameId;
        this.answer = CreateAnswer();
        this.status = "progress";
    }
    public Game() {
        this.answer = CreateAnswer();
        this.status = "progress";
    }

    public String CreateAnswer() {

        Random randNum = new Random();
        Set<Integer> set = new LinkedHashSet<Integer>();
        while (set.size() < 4) {
            set.add(randNum.nextInt(9)+1);
        }
        String result = set.stream().map(String::valueOf).collect(joining());

        return result;
    }


}
