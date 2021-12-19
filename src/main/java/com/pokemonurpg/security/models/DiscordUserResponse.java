package com.pokemonurpg.security.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscordUserResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("mfa_enabled")
    private Boolean mfaEnabled;
    @JsonProperty("flags")
    private String flags;
    @JsonProperty("public_flags")
    private String publicFlags;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("discriminator")
    private String discriminator;
    @JsonProperty("error")
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
    @JsonProperty("premium_type")
    private String premiumType;
    @JsonProperty("banner")
    private String banner;
    @JsonProperty("banner_color")
    private String bannerColor;
    @JsonProperty("accent_color")
    private String accentColor;

    public DiscordUserResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean getMfaEnabled() {
        return mfaEnabled;
    }

    public void setMfaEnabled(Boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getPublicFlags() {
        return publicFlags;
    }

    public void setPublicFlags(String publicFlags) {
        this.publicFlags = publicFlags;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(String premiumType) {
        this.premiumType = premiumType;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBannerColor() {
        return bannerColor;
    }

    public void setBannerColor(String bannerColor) {
        this.bannerColor = bannerColor;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    public boolean isValid() {
        if (error != null) return false;
        if (errorDescription != null) return false;
        if (id == null || id.isEmpty()) return false;
        return true;
    }
}
