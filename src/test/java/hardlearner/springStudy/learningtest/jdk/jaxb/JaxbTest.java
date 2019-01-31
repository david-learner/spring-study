package hardlearner.springStudy.learningtest.jdk.jaxb;

import hardlearner.springStudy.user.sqlservice.jaxb.SqlType;
import hardlearner.springStudy.user.sqlservice.jaxb.Sqlmap;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JaxbTest {
    @Test
    public void readSqlmap() throws JAXBException {
        String contextPath = Sqlmap.class.getPackage().getName();
        JAXBContext context = JAXBContext.newInstance(contextPath);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("hardlearner/springStudy/user/sqlservice/sqlmap.xml");
        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(in);
        List<SqlType> sqlList = sqlmap.getSql();

        assertThat(sqlList.size(), is(3));
        assertThat(sqlList.get(0).getKey(), is("add"));
        assertThat(sqlList.get(0).getValue(), is("insert"));
        assertThat(sqlList.get(1).getKey(), is("get"));
        assertThat(sqlList.get(1).getValue(), is("select"));
        assertThat(sqlList.get(2).getKey(), is("delete"));
        assertThat(sqlList.get(2).getValue(), is("delete"));
    }
}
