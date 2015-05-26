package com.github.jlgrock.snp.core.domain.lego.classifiers;

import com.github.jlgrock.snp.core.domain.lego.Concept;
import org.ihtsdo.otf.tcc.api.store.TerminologyStoreDI;

/**
 *
 */
public class ConceptClassifier extends AbstractLegoClassifier {

    private final Concept concept;

    ConceptClassifier(final TerminologyStoreDI terminologyStoreDI, final Concept conceptIn) {
        super(terminologyStoreDI);
        concept = conceptIn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void classify() {
        throw new UnsupportedOperationException();
    }
}