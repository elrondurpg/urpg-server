package com.pokemonurpg.stats.input;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.stats.models.LegendaryProgress;
import com.pokemonurpg.stats.validation.LegendaryProgressInputSequenceProvider;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@GroupSequenceProvider(LegendaryProgressInputSequenceProvider.class)
public class LegendaryProgressInputDto extends ChildInputDto {

    @ExistsInDb(type = LegendaryProgress.class)
    private Integer dbid;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Section.class)
    private String section;

    @Pattern(regexp = "^((https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+(&page=\\d+)?|^(https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+&pid=\\d+#pid\\d+|(https://)?pokemonurpg\\.com/archive/([a-z0-9\\-]+\\.\\d+/)*[a-z0-9\\-]+\\.\\d+(-page-\\d+)?\\.html|(https://)?pokemonurpg\\.com/archive/pxr/(\\d+-[A-Za-z0-9\\-()!]+/)+(page\\d+\\.html)?)$")
    @Size(max = 2083)
    private String logUrl;

    private Date date;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    private Integer value;

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

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
