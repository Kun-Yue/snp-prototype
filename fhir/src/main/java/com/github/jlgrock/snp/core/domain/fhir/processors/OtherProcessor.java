package com.github.jlgrock.snp.core.domain.fhir.processors;

import com.github.jlgrock.snp.apis.classifier.LogicalExpressionClassifier;
import com.github.jlgrock.snp.core.domain.fhir.model.Other;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class OtherProcessor extends AbstractFhirProcessor {

    @Inject
    public OtherProcessor(final LogicalExpressionClassifier logicalExpressionClassifierIn) {
        super(logicalExpressionClassifierIn);
    }

	@Override
	public void process(final String identifier, final Object unmarshalledObject) {
        Other other = (Other) unmarshalledObject;
		throw new UnsupportedOperationException();
	}

    @Override
    public Class processesType() {
        return Other.class;
    }
}
