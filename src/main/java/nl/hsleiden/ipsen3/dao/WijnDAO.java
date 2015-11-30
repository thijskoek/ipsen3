package nl.hsleiden.ipsen3.dao;

import nl.hsleiden.ipsen3.mappers.WijnMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by Daan on 30-Nov-15.
 */
@RegisterMapper(WijnMapper.class)
public interface WijnDAO {
    @SqlQuery("select * from product where id = :id")
    String findWijnById(@Bind("id") int id);

    @SqlQuery("select * from product")
    List<String> all();
}
