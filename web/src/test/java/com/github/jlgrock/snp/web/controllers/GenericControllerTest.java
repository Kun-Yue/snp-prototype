package com.github.jlgrock.snp.web.controllers;

import com.github.jlgrock.snp.web.configuration.ApplicationConfig;
import com.github.jlgrock.snp.web.configuration.ApplicationObjectMapper;
import com.github.jlgrock.snp.web.configuration.JacksonConfig;
import io.dropwizard.jersey.jackson.JacksonMessageBodyProvider;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.glassfish.jersey.test.TestProperties;
import org.jvnet.testing.hk2testng.HK2;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

import javax.validation.Validation;
import javax.ws.rs.core.Application;

@HK2(populate = false)
public abstract class GenericControllerTest extends JerseyTestNg.ContainerPerClassTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericControllerTest.class);
    
    // Value used to override the default application bindings with test versions
    public static final int DEFAULT_HK2_TEST_BIND_RANK = 10;

    @BeforeClass
    public void setUpTests() throws Exception {
        // Required to make this work on TestNG
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void configureClient(final ClientConfig config) {
        LOGGER.debug("Registering client configurations...");
        config.register(MultiPartFeature.class);
        config.register(
                new JacksonMessageBodyProvider(JacksonConfig.newObjectMapper(),
                        Validation.buildDefaultValidatorFactory().getValidator()));
        config.register(ApplicationObjectMapper.class);
    }

    @Override
    protected Application configure() {
        LOGGER.debug("Registering web application configurations...");
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ResourceConfig application = ApplicationConfig.createApp(serviceLocator, null, true);
        registerInjectionPoints(application);
        return application;
    }

    /**
     * register the injection points in HK2
     *
     * example:
     * application.registerInstances(new SimpleBinder<>(webconfiguration, WebConfiguration.class));
     *
     * @param application the application to register with
     */
    protected abstract void registerInjectionPoints(final ResourceConfig application);

}
