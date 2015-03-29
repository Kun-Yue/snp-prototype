package com.github.jlgrock.snp.core.converters;

import com.github.jlgrock.snp.apis.converters.ReadConverter;
import com.github.jlgrock.snp.core.data.AssertionTags;
import com.github.jlgrock.snp.core.domain.ClassifiedAssertion;
import com.mongodb.DBObject;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;

/**
 * A Conversion class to convert between a MongoDB DBObject to an PCE object.
 */
@Service
@Named
public class ClassifiedAssertionReadConverter implements ReadConverter<DBObject, ClassifiedAssertion> {
    @Override
    public ClassifiedAssertion convert(final DBObject source) {
        ClassifiedAssertion assertion = new ClassifiedAssertion();
        assertion.setId(((Number) source.get(AssertionTags.ID_TAG)).longValue());
        assertion.setDesc((String) source.get(AssertionTags.DESCRIPTION_TAG));
        return assertion;
    }
}