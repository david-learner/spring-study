package hardlearner.springStudy.user.sqlservice;

import hardlearner.springStudy.user.dao.UserDao;
import hardlearner.springStudy.user.service.SqlRetrievalFailureException;
import hardlearner.springStudy.user.service.SqlService;
import hardlearner.springStudy.user.sqlservice.jaxb.SqlType;
import hardlearner.springStudy.user.sqlservice.jaxb.Sqlmap;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlSqlService implements SqlService {
    private Map<String, String> sqlMap = new HashMap<>();
    private String sqlmapFile;

    public XmlSqlService() {

    }

    @PostConstruct
    public void loadSql() {
        System.out.println("load sql called");
        String contextPath = Sqlmap.class.getPackage().getName();
        try {
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream is = UserDao.class.getResourceAsStream(this.sqlmapFile); // 같은 패키지에 있는 sqlmap.xml 파일
            Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(is);

            for (SqlType sql : sqlmap.getSql()) {
                sqlMap.put(sql.getKey(), sql.getValue());
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        String sql = sqlMap.get(key);
        if (sql == null) {
            throw new SqlRetrievalFailureException(key + "를 이용해서 SQL을 찾을 수 없습니다");
        }
        return sql;
    }

    public void setSqlmapFile(String sqlmapFile) {
        this.sqlmapFile = sqlmapFile;
    }
}
