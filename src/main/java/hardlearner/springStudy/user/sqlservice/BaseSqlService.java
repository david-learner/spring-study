package hardlearner.springStudy.user.sqlservice;

import hardlearner.springStudy.user.service.SqlRetrievalFailureException;
import hardlearner.springStudy.user.service.SqlService;

import javax.annotation.PostConstruct;

public class BaseSqlService implements SqlService {
    protected SqlReader sqlReader;
    protected SqlRegistry sqlRegistry;

    public void setSqlReader(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
    }

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    @PostConstruct
    public void loadSql() {
        this.sqlReader.read(this.sqlRegistry);
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        try {
            return this.sqlRegistry.findSql(key);
        }catch (SqlNotFoundException e) {
            throw new SqlRetrievalFailureException(e);
        }
    }
}