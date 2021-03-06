//package com.howtodoinjava.jersey;
//
///**
// * Created by Katz on 13.12.2016.
// */
//import com.codahale.metrics.MetricRegistry;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.dropwizard.db.DataSourceFactory;
//import io.dropwizard.jdbi.DBIFactory;
//import io.dropwizard.setup.Environment;
//import io.dropwizard.util.Duration;
//import org.skife.jdbi.v2.DBI;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import java.util.Collections;
//
//public class TestJdbiDynamicAttributes
//{
//    private JdbiDynamicAttributes dynamicAttributes;
//
//    @BeforeMethod
//    public void setup() throws Exception
//    {
//        DBIFactory factory = new DBIFactory();
//        Environment environment = new Environment("test", new ObjectMapper(), null, new MetricRegistry(), ClassLoader.getSystemClassLoader());
//        DataSourceFactory dataSourceFactory = new DataSourceFactory();
//        dataSourceFactory.setUrl("jdbc:hsqldb:mem:soa-jdbi;shutdown=true");
//        dataSourceFactory.setDriverClass("org.hsqldb.jdbc.JDBCDriver");
//        dataSourceFactory.setLogValidationErrors(true);
//        dataSourceFactory.setUser("SA");
//        dataSourceFactory.setValidationQuery("SELECT * FROM INFORMATION_SCHEMA.SYSTEM_TABLES");
//        DBI jdbi = factory.build(environment, dataSourceFactory, "test");
//        dynamicAttributes = new JdbiDynamicAttributes(jdbi, Collections.singletonList("test"));
//
//        dynamicAttributes.getDao().createTable();
//        dynamicAttributes.start();
//    }

//    @AfterMethod
//    public void tearDown() throws Exception
//    {
//        dynamicAttributes.stop();
//    }

//    @Test
//    public void testBasic() throws Exception
//    {
//        Assert.assertEquals(dynamicAttributes.getAttribute("file.separator"), System.getProperty("file.separator"));
//        Assert.assertEquals(dynamicAttributes.getAttribute("os.version"), System.getProperty("os.version"));
//
//        Assert.assertEquals(dynamicAttributes.getAttribute("test.foo.bar", ""), "");
//
//        AttributeEntity attribute = new AttributeEntity("test.foo.bar", "test");
//        dynamicAttributes.getDao().insert(attribute.getfKEY(), attribute.getfSCOPE(), attribute.getfVALUE(), attribute.getfTIMESTAMP());
//        dynamicAttributes.update();
//        Assert.assertEquals(dynamicAttributes.getAttribute("test.foo.bar", ""), "test");
//
//        attribute = new AttributeEntity("test.foo.bar", "bad", "scoped-value");
//        dynamicAttributes.getDao().insert(attribute.getfKEY(), attribute.getfSCOPE(), attribute.getfVALUE(), attribute.getfTIMESTAMP());
//        dynamicAttributes.update();
//        Assert.assertEquals(dynamicAttributes.getAttribute("test.foo.bar", ""), "test");
//
//        attribute = new AttributeEntity("test.foo.bar", "test", "new-value");
//        dynamicAttributes.getDao().insert(attribute.getfKEY(), attribute.getfSCOPE(), attribute.getfVALUE(), attribute.getfTIMESTAMP());
//        dynamicAttributes.update();
//        Assert.assertEquals(dynamicAttributes.getAttribute("test.foo.bar", ""), "new-value");
//
//        dynamicAttributes.getDao().delete("test.foo.bar", "test");
//        dynamicAttributes.update();
//        Assert.assertEquals(dynamicAttributes.getAttribute("test.foo.bar", ""), "test");
//    }
//}
