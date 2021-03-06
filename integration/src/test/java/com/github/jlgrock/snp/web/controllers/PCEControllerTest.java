package com.github.jlgrock.snp.web.controllers;

import com.github.jlgrock.snp.web.ApplicationConfig;
import com.github.jlgrock.snp.web.ApplicationObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.test.JerseyTestNg;
import org.glassfish.jersey.test.TestProperties;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.ws.rs.core.Application;

import static org.testng.Assert.assertEquals;

/**
 *
 */
public class PCEControllerTest extends JerseyTestNg.ContainerPerClassTest {
    @BeforeMethod
    public void setUp() throws Exception {
        // Required to make this work on TestNG
        MockitoAnnotations.initMocks(this);
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(new JacksonFeature()).register(ApplicationObjectMapper.class);
    }

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        //return all of the rest endpoints
        return ApplicationConfig.createApp();
    }

    @Test
    public void testFindById() {
        final Long id = 1l;
//        final PCE p = new PCE();
//        p.setId(id);
//        p.setDesc("bla");
        final String response = target("pce/" + id).request().get(String.class);
//        final String converted = new PCEWriteConverter().convert(p).toString();
        final String converted = "{\r\n  \"id\" : 1,\r\n  \"desc\" : \"bla\"\r\n}";
        assertEquals(response, converted);
    }
}
