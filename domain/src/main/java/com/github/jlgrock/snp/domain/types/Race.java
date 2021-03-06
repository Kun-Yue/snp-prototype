package com.github.jlgrock.snp.domain.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * The Race of the Patient.
 */
public enum Race {
    CAUCASIAN(1), ASIAN(2), HISPANIC(3), BLACK_AFRICAN_AMERICAN(4), AMERICAN_INDIAN(5), OTHER(6);

    private static final Map<Integer, Race> VALUES_BY_ID;

    static {
        VALUES_BY_ID = new HashMap<>();
        Arrays.asList(Race.values()).forEach(race -> VALUES_BY_ID.put(race.getId(), race));
    }

    private final Integer id;

    private Race(final Integer i) {
        id = i;
    }
    /**
     * 
     * @return the Integer representing the Race.
     */
    public Integer getId() {
        return id;
    }
    /**
     * 
     * @param id the integer representing the Race.
     * @return the Race enumeration that matches the integer.
     */
    public static Race getValueById(final Integer id) {
        return VALUES_BY_ID.get(id);
    }
}

