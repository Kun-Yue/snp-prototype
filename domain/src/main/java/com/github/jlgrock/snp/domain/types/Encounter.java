package com.github.jlgrock.snp.domain.types;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

/**
 * The domain object that represents a single encounter in a patient visit.  Each encounter can consist
 * of multiple {@link com.github.jlgrock.snp.domain.types.Observation Observation} objects.
 */
public class Encounter extends AbstractMongoDomainObject {

    private String fhirId;
    
    private String subject;
    
    private String patientClass;

    private String status;
    
    private String participant;
    
    private List<Observation> observations;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("fhirId", fhirId)
                .add("subject", subject)
                .add("patientClass", patientClass)
                .add("status", status)
                .add("participant", participant)
                .add("observations", observations)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), fhirId, patientClass, status, observations);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Encounter other = (Encounter) obj;
        return Objects.equals(this.getId(), other.getId())
                && Objects.equals(this.fhirId, other.fhirId)
                && Objects.equals(this.subject, other.subject)
                && Objects.equals(this.patientClass, other.patientClass)
                && Objects.equals(this.status, other.status)
                && Objects.equals(this.participant, other.participant)
                && Objects.equals(this.observations, other.observations);
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(final List<Observation> pObservations) {
        observations = pObservations;
    }

    public String getFhirId() {
        return fhirId;
    }

	public void setFhirId(final String fhirIdIn) {
        fhirId = fhirIdIn;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(final String statusIn) {
		status = statusIn;
	}

	public String getPatientClass() {
		return patientClass;
	}

	public void setPatientClass(final String patientClassIn) {
		patientClass = patientClassIn;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(final String participantIn) {
		participant = participantIn;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subjectIn) {
		subject = subjectIn;
	}
}

