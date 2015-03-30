package com.github.jlgrock.snp.web.controllers;

import com.github.jlgrock.snp.apis.connection.configuration.WebConfiguration;
import com.github.jlgrock.snp.apis.data.MultiPartFileUtils;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * The controller for handling all xml uploads of lego data
 */
@Path("/lego")
public class LegoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LegoController.class);

    private MultiPartFileUtils multipartFileUtils;

    private WebConfiguration webConfiguration;
    
    private AssertionClassifierService assertClssfrSvc;

    /**
     * Default constructor.
     * @param webConfigurationIn the configuration object for the web project
     * @param multipartFileUtilsIn instance of the file helper utilities
     */
    @Inject
    public LegoController(final WebConfiguration webConfigurationIn,
                          final MultiPartFileUtils multipartFileUtilsIn,
                          final AssertionClassifierService assertClssfrSvcIn) {
        webConfiguration = webConfigurationIn;
        multipartFileUtils = multipartFileUtilsIn;
        assertClssfrSvc = assertClssfrSvcIn;
    }

    /**
     * Upload an XML file in Multi-part form to allow for posting from a website form.
     * @param fileInputStream the inputstream of the file
     * @param formDataContentDisposition the metadata associated with the file (which is treated like an attachment)
     * @return the response for the HTML transaction
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") final InputStream fileInputStream,
            @FormDataParam("file") final FormDataContentDisposition formDataContentDisposition) {

        LOGGER.debug("fileInputStream provided?=[{}], formDataContentDisposition provided?=[{}]",
                fileInputStream != null, formDataContentDisposition != null);

        LOGGER.debug("fileInputStream={}, fileName={}", fileInputStream, formDataContentDisposition.getFileName());

        java.nio.file.Path uploadedFileLocation = webConfiguration.fileLocation().resolve(formDataContentDisposition.getFileName());

        // save it
        multipartFileUtils.writeToFile(fileInputStream, uploadedFileLocation);

        LOGGER.debug("File uploaded to : " + uploadedFileLocation);

        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(
				uploadedFileLocation, StandardOpenOption.READ));) {
        	assertClssfrSvc.classifyAssertion(inputStream);
		} catch (IOException e) {
			LOGGER.error(e.toString());
			return Response.serverError().build();
		}

        
        return Response.ok().build();
    }
    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/xml" media type.
     *
     * @param xml the xml content body to ingest
     *
     * @return String that will be returned as a application/xml response.
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
//    @Produces(MediaType.APPLICATION_XML)
    public Response getLego(final String xml) {
    	
    	LOGGER.debug("HTTP XML stream received: " + xml);
    	
    	assertClssfrSvc.classifyAssertion(xml);
    	
    	return Response.ok().build();
    }

}
