package nl.hsleiden.ipsen3.mappers;

import nl.hsleiden.ipsen3.core.Wijn;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Daan on 30-Nov-15.
 */
public class WijnMapper implements ResultSetMapper<Wijn> {
    public Wijn map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Wijn(
            resultSet.getInt("id"),
            resultSet.getInt("productnummer"),
            resultSet.getString("naam"),
            resultSet.getInt("jaar"),
            resultSet.getDouble("prijs"),
            resultSet.getString("type"),
            resultSet.getInt("land_id"),
            resultSet.getInt("rang")
        );
    }
}
