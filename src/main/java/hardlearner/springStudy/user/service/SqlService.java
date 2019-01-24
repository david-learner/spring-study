package hardlearner.springStudy.user.service;

public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
