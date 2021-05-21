package corbos.crackthecode.data;

import corbos.crackthecode.models.Round;

import java.util.List;

public interface RoundDao {
    Round createRound(Round round);

    Round makeGuess(String guess, int gameId);

    List<Round> getAll();

    Round findById(int id);
}




