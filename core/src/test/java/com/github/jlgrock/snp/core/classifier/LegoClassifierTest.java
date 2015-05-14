package com.github.jlgrock.snp.core.classifier;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.jlgrock.snp.core.domain.ClassifiedPce;
import com.github.jlgrock.snp.core.domain.lego.Assertion;
import com.github.jlgrock.snp.core.domain.lego.Destination;
import com.github.jlgrock.snp.core.domain.lego.Discernible;
import com.github.jlgrock.snp.core.domain.lego.Expression;
import com.github.jlgrock.snp.core.domain.lego.Relation;

public class LegoClassifierTest {
	private LegoClassifierImpl legoClassifier;
	
	@BeforeClass
	public void setUp() {
		legoClassifier = new LegoClassifierImpl();
		legoClassifier.setLogicGraphClassifier(new LegoLogicGraphClassifierImpl());
	}
	
	@Test
	public void testClassifyComplexExpression() {
		Assertion assertion = new Assertion();
		Discernible discernible = new Discernible();
		Expression expression = new Expression();
				
		Relation relation = new Relation();
		Destination destination = new Destination();
		destination.setExpression(new Expression());
		relation.setDestination(destination);		
		List<Relation> relations = new ArrayList<Relation>();
		relations.add(relation);
		
		expression.getRelation().addAll(relations);			
		discernible.setExpression(expression);
		assertion.setDiscernible(discernible);			
		
		ClassifiedPce classifiedAssertion = legoClassifier.classify(assertion);
		assertNotNull(classifiedAssertion.getUuid(), "complex expression is missing uuid");		
	}
	
	@Test
	public void testClassifySimpleExpression() {
		Assertion assertion = new Assertion();
		Discernible discernible = new Discernible();
		Expression expression = new Expression();
		
		discernible.setExpression(expression);
		assertion.setDiscernible(discernible);
		
		ClassifiedPce classifiedAssertion = legoClassifier.classify(assertion);
		assertNull(classifiedAssertion.getUuid(), "simple expression does not need uuid");		
	}
}