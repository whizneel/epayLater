package in.epaylater.testApp.dao;

import in.epaylater.testApp.entity.CreditLimitData;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditDataMapper implements ResultSetMapper<CreditLimitData> {
    public CreditLimitData map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new CreditLimitData(r.getLong("id"),
                r.getString("phone"),
                r.getDouble("credit_limit_original"),
                r.getDouble("credit_limit_remaining"));
    }
}
