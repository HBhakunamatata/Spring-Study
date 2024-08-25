package cloud.popples.databaseandjdbc.repository.impl;

import cloud.popples.databaseandjdbc.pojo.Spitter;
import cloud.popples.databaseandjdbc.repository.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    private static final String SQL_UPDATE_SPITTER = "update spitter set username = ?, password=?, fullname=? where id = ?";

    private static final String SQL_SEARCH_SPITTER = "select id, username, fullName from spitter where id = ?";

    private static final String SQL_INSERT_SPITTER = "insert spitter (username, password, fullName, email) values (?, ?, ?, ?)";

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcSpitterRepository (JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void addSpitter(Spitter spitter) {
        jdbcOperations.update(SQL_INSERT_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName(),
                spitter.getEmail()
        );
    }

    @Override
    public Spitter findOne(long id) {
        Spitter spitter = jdbcOperations.queryForObject(
                SQL_SEARCH_SPITTER,
                (resultSet, rowNum) -> {
                    Spitter temp = new Spitter();
                    String fullName = resultSet.getString("fullName");
                    temp.setId(resultSet.getLong("id"));
                    temp.setUsername(resultSet.getString("username"));
                    temp.setFirstName(fullName);
                    return temp;
                },
                id);
        return spitter;
    }

    @Override
    public void updateSpitter(Spitter spitter) {
        jdbcOperations.update(SQL_UPDATE_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName(),
                spitter.getId()
        );
    }

//    private static final class SpitterRowMapper implements RowMapper<Spitter> {
//        @Override
//        public Spitter mapRow(ResultSet resultSet, int i) throws SQLException {
//            Spitter spitter = new Spitter();
//            String fullName = resultSet.getString("fullName");
//            spitter.setId(resultSet.getLong("id"));
//            spitter.setUsername(resultSet.getString("username"));
//            spitter.setFirstName(fullName);
//            return spitter;
//
//        }
//    }
}
