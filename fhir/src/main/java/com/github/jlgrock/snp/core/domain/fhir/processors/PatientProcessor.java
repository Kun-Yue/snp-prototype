package com.github.jlgrock.snp.core.domain.fhir.processors;

import com.github.jlgrock.snp.core.domain.fhir.converters.PatientWriteConverter;
import com.github.jlgrock.snp.core.domain.fhir.converters.PatientWriteConverterImpl;
import com.github.jlgrock.snp.core.domain.fhir.model.Patient;
import com.github.jlgrock.snp.domain.data.PatientRepository;

import gov.vha.isaac.logic.LogicGraph;

import org.ihtsdo.otf.tcc.api.store.TerminologyStoreDI;

/**
 *
 */
public class PatientProcessor extends AbstractFhirProcessor {

    private final Patient patient;
    private final PatientWriteConverter pwc;
    private final PatientRepository patientRepo;

    public PatientProcessor(final TerminologyStoreDI terminologyStoreDIIn, final Patient patientIn,
    		final PatientWriteConverter pwcIn, final PatientRepository patientRepositoryIn) {
        super(terminologyStoreDIIn);
        patient = patientIn;
        pwc = pwcIn;
        patientRepo = patientRepositoryIn;
    }

    @Override
    protected LogicGraph getLogicGraph() {
        throw new UnsupportedOperationException();
    }

	@Override
	public void process() {
		com.github.jlgrock.snp.domain.types.Patient patientOut = pwc.convert(patient);
		patientRepo.save(patientOut);
	}
}
