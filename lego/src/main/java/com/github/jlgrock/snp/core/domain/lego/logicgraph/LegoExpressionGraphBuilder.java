package com.github.jlgrock.snp.core.domain.lego.logicgraph;

import com.github.jlgrock.snp.apis.classifier.LogicGraphClassifier;
import com.github.jlgrock.snp.core.domain.lego.model.Expression;
import gov.vha.isaac.logic.node.AbstractNode;
import gov.vha.isaac.logic.node.RootNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Logic Graph Builder specific to Lego documents.  This should only be used to
 */
public class LegoExpressionGraphBuilder extends AbstractLegoLogicGraphBuilder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LegoExpressionGraphBuilder.class);

    private Expression expression;

    public LegoExpressionGraphBuilder(final LogicGraphClassifier logicGraphClassifierIn, final Expression expressionIn) {
        super(logicGraphClassifierIn);
        expression = expressionIn;
    }

    @Override
    public void create() {
        LOGGER.trace("Creating LogicExpressionGraph");

        // TODO
        // Determine if it is SufficientSet or NecessarySet.  In most cases in Lego, this will be a SufficientSet because
        // all of the primitives have been defined in snomed.

        //Create root node first
        RootNode root = getRoot();

        AbstractNode node = buildExpression(expression);
        root.addChildren(SufficientSet(node));
    }


}
