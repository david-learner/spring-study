package hardlearner.springStudy.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {

    private DataSource dataSource;

    public void setDatasource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void executeSql(final String query, String ...args) throws SQLException {
        workWithStatementStrategy(
            new StatementStrategy() {
                @Override
                public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                    PreparedStatement ps = c.prepareStatement(query);
                    if (args.length > 0) {
                        for (int sequnce = 1; sequnce <= args.length; sequnce++) {
                            ps.setString(sequnce, args[sequnce - 1]);
                        }
                    }
                    return ps;
                }
            }
        );
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

}
