package com.pokemonurpg.entities.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LegendaryProgressKey implements Serializable {

    @Column(name = "log_url")
    private String logUrl;

    @Column(name = "trainer_dbid")
    private Integer trainerDbid;
    
    @Column(name = "section_dbid")
    private Integer sectionDbid;

    public LegendaryProgressKey() {    }

    public LegendaryProgressKey(String logUrl, Integer trainerDbid, Integer sectionDbid) {
        this.logUrl = logUrl;
        this.trainerDbid = trainerDbid;
        this.sectionDbid = sectionDbid;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public Integer getTrainerDbid() {
        return trainerDbid;
    }

    public void setTrainerDbid(Integer trainerDbid) {
        this.trainerDbid = trainerDbid;
    }

    public Integer getSectionDbid() {
        return sectionDbid;
    }

    public void setSectionDbid(Integer sectionDbid) {
        this.sectionDbid = sectionDbid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((logUrl == null) ? 0 : logUrl.hashCode());
        result = prime * result + ((sectionDbid == null) ? 0 : sectionDbid.hashCode());
        result = prime * result + ((trainerDbid == null) ? 0 : trainerDbid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LegendaryProgressKey other = (LegendaryProgressKey) obj;
        if (logUrl == null) {
            if (other.logUrl != null)
                return false;
        } else if (!logUrl.equals(other.logUrl))
            return false;
        if (sectionDbid == null) {
            if (other.sectionDbid != null)
                return false;
        } else if (!sectionDbid.equals(other.sectionDbid))
            return false;
        if (trainerDbid == null) {
            if (other.trainerDbid != null)
                return false;
        } else if (!trainerDbid.equals(other.trainerDbid))
            return false;
        return true;
    }

    
    
}
