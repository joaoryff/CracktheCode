package corbos.crackthecode.data;

import corbos.crackthecode.models.Round;
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
public class RoundDataBaseDao implements RoundDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDataBaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round createRound(Round round) {
        final String sql = "INSERT INTO round(gameId,guess,result) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);


            statement.setInt(1, round.getGameId());
            statement.setString(2, round.getGuess());
//          statement.setTimestamp(3, Timestamp.valueOf(round.getTimestamp().toLocalDateTime().now()));

            statement.setString(3, round.getResult());


            return statement;

        }, keyHolder);

        round.setGameId(keyHolder.getKey().intValue());

        return round;
    }


    @Override
    public Round makeGuess(String guess, int gameId) {
        return null;
    }

    @Override
    public List<Round> getAll() {
        return null;
    }

    @Override
    public Round findById(int id) {
        final String sql = "select roundId, gameId,guess"
                + "FROM round WHERE roundId = ?;";

        return jdbcTemplate.queryForObject(sql, new RoundDataBaseDao.RoundMapper(), id);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            round.setGuess(rs.getString("guess"));
            round.setTime(rs.getTimestamp("time"));
            round.setResult(rs.getString("result"));

            return round;
        }
    }
}
