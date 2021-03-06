package com.github.jlgrock.snp.core.domain.fhir.converters;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.testng.annotations.Test;

import com.github.jlgrock.snp.core.domain.fhir.model.Patient;
import com.github.jlgrock.snp.domain.types.Gender;

public class PatientWriteConverterImplTest extends AbstractConverterTest {
	
	public final static String xmlFile = "Patient-example-f001-pieter.xml";

	@Test
	public void testConvert() throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(path);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<Patient> je = (JAXBElement<Patient>) jaxbUnmarshaller.unmarshal(
				readFile(xmlFile));
		Patient patientIn = je.getValue();
		
		PatientWriteConverter patientConverter = new PatientWriteConverterImpl();
		
		com.github.jlgrock.snp.domain.types.Patient patient = patientConverter.convert(patientIn);
		
		assertEquals(patient.getFirstName(), "Pieter");
		assertEquals(patient.getMiddleName(), null);
		assertEquals(patient.getLastName(), "van de Heuvel");
		assertEquals(patient.getDateOfBirth(), LocalDate.of(1944, 11, 17));
		assertEquals(patient.getGender(), Gender.MALE);
		assertEquals(patient.isDeceased(), Boolean.FALSE);
		assertEquals(patient.getDateDeceased(), null);
	}
}
