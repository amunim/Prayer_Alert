package com.amfk.prayer_alert;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IPStack {

    @SerializedName("ip")
    @Expose
    public String ip;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("continent_code")
    @Expose
    public String continentCode;
    @SerializedName("continent_name")
    @Expose
    public String continentName;
    @SerializedName("country_code")
    @Expose
    public String countryCode;
    @SerializedName("country_name")
    @Expose
    public String countryName;
    @SerializedName("region_code")
    @Expose
    public String regionCode;
    @SerializedName("region_name")
    @Expose
    public String regionName;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("zip")
    @Expose
    public String zip;
    @SerializedName("latitude")
    @Expose
    public Float latitude;
    @SerializedName("longitude")
    @Expose
    public Float longitude;
    @SerializedName("location")
    @Expose
    public com.amfk.prayer_alert.Location location;

}