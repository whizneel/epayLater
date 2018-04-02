package in.epaylater.testApp.dao;

import in.epaylater.testApp.entity.TokenData;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

@RegisterMapper(TokenDataMapper.class)
public interface TokenDataDao {

    @SqlUpdate("insert into token_data (phone,token) values (:phone,:token) on duplicate key update token = :token")
    @GetGeneratedKeys
    Long insertOrUpdate(@BindBean TokenData tokenData);

    @SqlQuery("Select * from token_data where phone = :phone")
    //@SingleValueResult
    TokenData findTokenDataByPhoneNumber(@Bind("phone") String phone);
}