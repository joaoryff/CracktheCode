package corbos.crackthecode.service;

import corbos.crackthecode.models.Game;

public class CrackRules {


    public String BeginMsg (int id) {
       return  "The game is created with the id:" + id;
    }

    public boolean HasNumber(Game game, String guess, int position) {
        int index = game.getAnswer().indexOf(guess);
        return index != -1;
    }

    public boolean PositionOK(int guessPosition, int position) {
        return guessPosition == position;
    }

    public String CrackStatus(boolean PositionOK, boolean HasNumber, int e, int p) {
        if (HasNumber == true) {
            if (PositionOK == true) {
                e++;
                if (p > 1) {
                    p--;
                }
            } else {
                p++;
            }
        }
        return "e:" + e + "p:" + p;
    }


}
