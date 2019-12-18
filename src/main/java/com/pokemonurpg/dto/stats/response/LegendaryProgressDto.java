package com.pokemonurpg.dto.stats.response;

import com.pokemonurpg.object.LegendaryProgress;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LegendaryProgressDto {
    private String section;
    private double progress;
    private int claimedTier1 = 0;
    private int claimedTier2 = 0;
    private double requirementTier1;
    private double requirementTier2;
    private List<LegendLog> logs = new ArrayList<>();

    private static final DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public LegendaryProgressDto() {
    }

    public LegendaryProgressDto(String section) {
        setSection(section);
    }

    public void addClaimed(int tier) {
        if (tier == 1)
            claimedTier1++;
        else if (tier == 2)
            claimedTier2++;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public double getProgress() {
        return progress - getTotalClaimedCost();
    }

    public void addProgress(LegendaryProgress progressRecord) {
        if (progressRecord != null) {
            progress += progressRecord.getValue();
            Date progressDate = progressRecord.getDate();
            if (progressDate != null) {
                logs.add(new LegendLog(progressRecord.getLogUrl(), YYYY_MM_DD.format(progressDate), progressRecord.getValue()));
            }
        }
    }

    public double getTotalClaimedCost() {
        double total = 0;
        double baseTier1 = -1;
        double baseTier2 = -1;
        if (section.equalsIgnoreCase("Reffing") || section.equalsIgnoreCase("Judging")) {
            baseTier1 = 1000000;
            baseTier2 = 500000;
        }
        if (section.equalsIgnoreCase("Art") || section.equalsIgnoreCase("Writing") || section.equalsIgnoreCase("Curating") || section.equalsIgnoreCase("Grading") || section.equalsIgnoreCase("Ranger") || section.equalsIgnoreCase("National Park")) {
            baseTier1 = 500000;
            baseTier2 = 250000;
        }
        if (section.equalsIgnoreCase("Contests")) {
            baseTier1 = 35;
            baseTier2 = 15;
        }
        if (section.equalsIgnoreCase("Legend Defender")) {
            return 0;
        }
        if (section.equalsIgnoreCase("Morphic")) {
            baseTier1 = 1500;
            baseTier2 = 750;
        }

        if (claimedTier1 >= 1) {
            total += baseTier1;
        }
        if (claimedTier1 >= 2){
            total += Math.floor(baseTier1 * 1.5);
        }
        if (claimedTier1 >= 3){
            total += baseTier1 * 2 * (claimedTier1 - 2);
        }

        if (claimedTier2 >= 1) {
            total += baseTier2;
        }
        if (claimedTier2 >= 2){
            total += Math.floor(baseTier2 * 1.5);
        }
        if (claimedTier2 >= 3){
            total += baseTier2 * 2 * (claimedTier2 - 2);
        }

        return total;
    }

    public double getRequirementTier1() {
        requirementTier1 = -1;
        if (section.equalsIgnoreCase("Reffing") || section.equalsIgnoreCase("Judging")) {
            if (claimedTier1 == 0) {
                requirementTier1 = 1000000;
            }
            else if (claimedTier1 == 1) {
                requirementTier1 = 1500000;
            }
            else if (claimedTier1 > 1){
                requirementTier1 = 2000000;
            }
        }
        if (section.equalsIgnoreCase("Art") || section.equalsIgnoreCase("Writing") || section.equalsIgnoreCase("Curating") || section.equalsIgnoreCase("Grading") || section.equalsIgnoreCase("Ranger") || section.equalsIgnoreCase("National Park")) {
            if (claimedTier1 == 0) {
                requirementTier1 = 500000;
            }
            else if (claimedTier1 == 1) {
                requirementTier1 = 750000;
            }
            else if (claimedTier1 > 1){
                requirementTier1 = 1000000;
            }
        }
        if (section.equalsIgnoreCase("Contests")) {
            if (claimedTier1 == 0) {
                requirementTier1 = 35;
            }
            else if (claimedTier1 == 1) {
                requirementTier1 = 52;
            }
            else if (claimedTier1 > 1){
                requirementTier1 = 70;
            }
        }
        if (section.equalsIgnoreCase("Legend Defender")) {
            if (claimedTier1 == 0) {
                requirementTier1 = 10;
            }
            else if (claimedTier1 >= 1) {
                requirementTier1 = -1;
            }
        }
        if (section.equalsIgnoreCase("Morphic")) {
            if (claimedTier1 == 0) {
                requirementTier1 = 1500;
            }
            else if (claimedTier1 == 1) {
                requirementTier1 = 2250;
            }
            else if (claimedTier1 > 1){
                requirementTier1 = 3000;
            }
        }
        return requirementTier1;
    }

    public double getRequirementTier2() {
        requirementTier2 = -1;
        if (section.equalsIgnoreCase("Reffing") || section.equalsIgnoreCase("Judging")) {
            if (claimedTier2 == 0) {
                requirementTier2 = 500000;
            }
            else if (claimedTier2 == 1) {
                requirementTier2 = 750000;
            }
            else if (claimedTier2 > 1){
                requirementTier2 = 1000000;
            }
        }
        if (section.equalsIgnoreCase("Art") || section.equalsIgnoreCase("Writing") || section.equalsIgnoreCase("Curating") || section.equalsIgnoreCase("Grading") || section.equalsIgnoreCase("Ranger") || section.equalsIgnoreCase("National Park")) {
            if (claimedTier2 == 0) {
                requirementTier2 = 250000;
            }
            else if (claimedTier2 == 1) {
                requirementTier2 = 375000;
            }
            else if (claimedTier2 > 1){
                requirementTier2 = 500000;
            }
        }
        if (section.equalsIgnoreCase("Contests")) {
            if (claimedTier1 == 0) {
                requirementTier2 = 15;
            }
            else if (claimedTier1 == 1) {
                requirementTier1 = 22;
            }
            else if (claimedTier1 > 1){
                requirementTier1 = 30;
            }
        }
        if (section.equalsIgnoreCase("Morphic")) {
            if (claimedTier1 == 0) {
                requirementTier1 = 750;
            }
            else if (claimedTier1 == 1) {
                requirementTier1 = 1125;
            }
            else if (claimedTier1 > 1){
                requirementTier1 = 1500;
            }
        }
        return requirementTier2;
    }

    public List<LegendLog> getLogs() {
        return logs;
    }

    public void setLogs(List<LegendLog> logs) {
        this.logs = logs;
    }

    public static class LegendLog {
        private String url;
        private String date;
        private int value;

        public LegendLog(String url, String date, int value) {
            this.url = url;
            this.date = date;
            this.value = value;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
