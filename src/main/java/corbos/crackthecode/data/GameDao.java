package corbos.crackthecode.data;

import corbos.crackthecode.models.Game;

import java.util.List;

public interface GameDao {

    Game createGame(Game game);


    List<Game> getAll();

    Game findById(int id);


}
