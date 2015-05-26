package com.github.jlgrock.snp.core.domain.lego.classifiers;

import com.github.jlgrock.snp.core.domain.lego.LegoList;
import org.ihtsdo.otf.tcc.api.store.TerminologyStoreDI;

/**
 *
 */
public class LegoListClassifier extends AbstractLegoClassifier {

    private final LegoList legoList;

    LegoListClassifier(final TerminologyStoreDI terminologyStoreDI, final LegoList legoListIn) {
        super(terminologyStoreDI);
        legoList = legoListIn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void classify() {
        parseLegoList(legoList);
    }
}