package com.github.jlgrock.snp.core.domain.fhir.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jlgrock.snp.core.domain.fhir.model.Code;
import com.github.jlgrock.snp.core.domain.fhir.model.DateTime;
import com.github.jlgrock.snp.domain.types.Gender;

@Service
public class PatientWriteConverterImpl implements PatientWriteConverter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientWriteConverterImpl.class);

	@Override
	public com.github.jlgrock.snp.domain.types.Patient convert(
			com.github.jlgrock.snp.core.domain.fhir.model.Patient source) {
		LOGGER.trace("source={}", source);
		
		com.github.jlgrock.snp.domain.types.Patient patientOut = new com.github.jlgrock.snp.domain.types.Patient();
		
		// convert name
		patientOut.setFirstName(source.getName().get(0).getGiven().get(0).getValue());
		// convert middle name
		if (source.getName().size() >= 2) {
			patientOut.setMiddleName(source.getName().get(0).getGiven().get(1).getValue());
		}
		patientOut.setLastName(source.getName().get(0).getFamily().get(0).getValue());
		
		patientOut.setGender(convertGender(source.getGender().getCoding().get(0).getCode()));
		patientOut.setDateOfBirth(convertDateTimeToLocalDate(source.getBirthDate()));
		patientOut.setDeceased(source.getDeceasedBoolean().isValue());
		patientOut.setDateDeceased(convertDateTimeToLocalDate(source.getDeceasedDateTime()));
		return patientOut;
	}
	
	protected Gender convertGender(Code genderCode) {
		switch (genderCode.getValue()) {
		case "M":
			return Gender.MALE;
		case "F":
			return Gender.FEMALE;
		case "UN":
			return Gender.OTHER;
		default:
			return Gender.OTHER;
		}
	}
	
	protected LocalDate convertDateTimeToLocalDate(DateTime dateTime) {
		LOGGER.trace("dateTime={}", dateTime);
		if (dateTime == null) {
			return null;
		}
		return LocalDate.parse(dateTime.getValue(), DateTimeFormatter.ISO_DATE);
	}
}
