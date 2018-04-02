package in.epaylater.testApp.dao;

import in.epaylater.testApp.entity.TokenData;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDataMapper implements ResultSetMapper<TokenData> {
    public TokenData map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new TokenData(r.getLong("id"),
                r.getString("phone"),
                r.getString("token"));
    }
}
