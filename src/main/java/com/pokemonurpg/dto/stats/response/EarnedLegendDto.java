package com.pokemonurpg.dto.stats.response;

import com.pokemonurpg.object.EarnedLegendary;
import com.pokemonurpg.object.OwnedPokemon;
import com.pokemonurpg.object.Section;
import com.pokemonurpg.object.Species;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EarnedLegendDto {
    private String section;
    private int tier;
    private String name;
    private String displayName;
    private int dexno;
    private String nickname;
    private String date;

    private static final DateFormat MM_DD_YYYY = new SimpleDateFormat("MM-dd-yyyy");

    public EarnedLegendDto() {
    }

    public EarnedLegendDto(EarnedLegendary legend) {
        if (legend != null) {
            Section legendSection = legend.getSection();
            if (legendSection != null) {
                setSection(legendSection.getName());
            }

            OwnedPokemon ownedPokemon = legend.getPokemon();
            if (ownedPokemon != null) {
                Species species = ownedPokemon.getSpecies();

                if (species != null) {
                    setTier(species.getLegendaryTier());
                    setName(species.getName());
                    setDisplayName(species.getDisplayName());
                    setDexno(species.getDexno());
                }

                setNickname(ownedPokemon.getNickname());
            }

            Date earnDate = legend.getDate();
            if (earnDate != null) {
                setDate(MM_DD_YYYY.format(earnDate));
            }
        }
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getDexno() {
        return dexno;
    }

    public void setDexno(int dexno) {
        this.dexno = dexno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
