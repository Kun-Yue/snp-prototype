package com.github.jlgrock.snp.domain.data;

import com.github.jlgrock.snp.apis.connection.MongoDbFactory;

import com.github.jlgrock.snp.domain.converters.EncounterReadConverter;
import com.github.jlgrock.snp.domain.converters.EncounterWriteConverter;
import com.github.jlgrock.snp.domain.types.Encounter;
import org.bson.Document;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class executes queries against the Encounter
 * Collection within MongoDB.
 */
@Service(name="encounterRepository")
public class EncounterRepositoryImpl extends
        AbstractRepositoryImpl<Encounter, Long> implements EncounterRepository {

    private final EncounterReadConverter encounterReadConverter;

    private final EncounterWriteConverter encounterWriteConverter;
    
    private List<Encounter> encounterShell = new ArrayList<>(6);
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(EncounterRepositoryImpl.class);

    /**
     * 
     * @param mongoDbFactoryIn MongoDbFactory
     * @param encounterReadConverterIn EncounterReadConverter
     * @param encounterWriteConverterIn EncounterWriteConverter
     */
    @Inject
    protected EncounterRepositoryImpl(final MongoDbFactory mongoDbFactoryIn,
                                      final EncounterReadConverter encounterReadConverterIn,
                                      final EncounterWriteConverter encounterWriteConverterIn) {
        super(mongoDbFactoryIn);
        encounterReadConverter = encounterReadConverterIn;
        encounterWriteConverter = encounterWriteConverterIn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getCollectionName() {
    	LOGGER.trace("getCollectionName()");
        return "encounters";
    }

    /**
     * {@inheritDoc}
     */
    public List<Encounter> findByDate(final LocalDate date) {
    	if (date == null){
    		return encounterShell;
    	}
    	LOGGER.trace("findByDate(LocalDate date=" + date + ")");
        Document query = new Document() {{
            put("date", date);
        }};
        return executeQueryAndTransformResults(query);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Encounter convertToDomainObject(final Document dbObjectin) {
    	LOGGER.trace("convertToDomainObject(Document dbObjectin=" + dbObjectin + ")");
        if (dbObjectin == null) {
            return null;
        }
        return encounterReadConverter.convert(dbObjectin);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Document convertToDBObject(final Encounter s) {
    	LOGGER.trace("convertToDBObject(Encounter s=" + s + ")");
        if (s == null) {
            return null;
        }
        return encounterWriteConverter.convert(s);
    }

}
