package com.pokemonurpg.stats.input;

import com.pokemonurpg.lib.input.ChildInputDto;
import com.pokemonurpg.lib.validation.annotation.ExistsInDb;
import com.pokemonurpg.entities.Section;
import com.pokemonurpg.lib.strings.GeneralConstants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class LegendaryProgressInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Section.class)
    private String section;

    @NotNull
    @Size(max = 2083)
    @Pattern(
        regexp = GeneralConstants.ALLOWED_URL_PATTERN,
        message = "The provided log URL must come from forum.pokemonurpg.com or one of the URPG forum archives."
    )
    private String logUrl;

    private Date date;

    @Min(1)
    private Integer value;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
