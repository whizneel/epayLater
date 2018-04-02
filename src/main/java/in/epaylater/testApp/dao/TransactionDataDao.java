package in.epaylater.testApp.dao;

import in.epaylater.testApp.entity.TransactionData;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(TransactionDataMapper.class)
public interface TransactionDataDao {
    @SqlUpdate("INSERT INTO epaylater.transaction_data (phone, date, description, amount) VALUES ( :phone, :date, :description, :amount)")
    @GetGeneratedKeys
    Long create(@BindBean TransactionData transactionData);
}
