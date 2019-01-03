package com.amfk.prayer_alert;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("geoname_id")
    @Expose
    public Integer geonameId;
    @SerializedName("capital")
    @Expose
    public String capital;
    @SerializedName("languages")
    @Expose
    public List<Language> languages = null;
    @SerializedName("country_flag")
    @Expose
    public String countryFlag;
    @SerializedName("country_flag_emoji")
    @Expose
    public String countryFlagEmoji;
    @SerializedName("country_flag_emoji_unicode")
    @Expose
    public String countryFlagEmojiUnicode;
    @SerializedName("calling_code")
    @Expose
    public String callingCode;
    @SerializedName("is_eu")
    @Expose
    public Boolean isEu;

}