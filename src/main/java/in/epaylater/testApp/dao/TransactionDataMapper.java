package in.epaylater.testApp.dao;

import in.epaylater.testApp.entity.TransactionData;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDataMapper implements ResultSetMapper<TransactionData> {
    public TransactionData map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new TransactionData(r.getLong("id"),
                r.getString("phone"),
                r.getDate("date"),
                r.getString("description"),
                r.getDouble("amount"));
    }
}
