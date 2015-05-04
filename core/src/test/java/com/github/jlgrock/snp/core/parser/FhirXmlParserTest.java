package com.github.jlgrock.snp.core.parser;

import static org.testng.Assert.assertEquals;

import java.io.InputStream;

import org.testng.annotations.Test;

import com.github.jlgrock.snp.core.model.xml.fhir.Bundle;


public class FhirXmlParserTest extends AbstractXmlParserTest {
		
	@Test
	public void testParserConditionBundle() throws Exception {
		InputStream xmlInput = getClass().getClassLoader().getResourceAsStream("ConditionBundle.xml");
		Bundle bundle = new FhirXmlParser().parseDocument(xmlInput);
		
		String expected = readFile("ConditionBundle.xml");
		String formatted = bundle.toXml();
		assertEquals(formatted.trim(), expected.trim());
	}
	
}
