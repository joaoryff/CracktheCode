package corbos.crackthecode.data;

import corbos.crackthecode.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Profile("database")
public class GameDataBaseDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDataBaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game createGame(Game game) {

        final String sql = "INSERT INTO game(answer, status) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);


            statement.setString(1, game.getAnswer());
            statement.setString(2, game.getStatus());
            return statement;

        }, keyHolder);

       game.setGameId(keyHolder.getKey().intValue());

        return game;
    }


    @Override
    public List<Game> getAll() {
        final String sql = "select gameId,  coalesce(nullif(status, 'exact'),answer) answer, status  from game;";
        return jdbcTemplate.query(sql, new ToDoMapper());
    }

    @Override
    public Game findById(int id) {
        final String sql = "select gameId, coalesce(nullif(status, 'exact'),answer) answer, status "
                + "FROM game WHERE gameId = ?;";

        return jdbcTemplate.queryForObject(sql, new ToDoMapper(), id);
    }

    private static final class ToDoMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(rs.getString("status"));

            return game;
        }
    }


}
