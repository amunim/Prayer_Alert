package com.amfk.prayer_alert;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Namaaz {
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("data")
    @Expose
    public Data data;

    public class Data {

        @SerializedName("timings")
        @Expose
        public Timings timings;
        @SerializedName("date")
        @Expose
        public Date date;
        @SerializedName("meta")
        @Expose
        public Meta meta;

    }

    public class Date {

        @SerializedName("readable")
        @Expose
        public String readable;
        @SerializedName("timestamp")
        @Expose
        public String timestamp;
        @SerializedName("hijri")
        @Expose
        public Hijri hijri;
        @SerializedName("gregorian")
        @Expose
        public Gregorian gregorian;

    }

    public class Designation {

        @SerializedName("abbreviated")
        @Expose
        public String abbreviated;
        @SerializedName("expanded")
        @Expose
        public String expanded;

    }

    public class Designation_ {

        @SerializedName("abbreviated")
        @Expose
        public String abbreviated;
        @SerializedName("expanded")
        @Expose
        public String expanded;

    }

    public class Gregorian {

        @SerializedName("date")
        @Expose
        public String date;
        @SerializedName("format")
        @Expose
        public String format;
        @SerializedName("day")
        @Expose
        public String day;
        @SerializedName("weekday")
        @Expose
        public Weekday_ weekday;
        @SerializedName("month")
        @Expose
        public Month_ month;
        @SerializedName("year")
        @Expose
        public String year;
        @SerializedName("designation")
        @Expose
        public Designation_ designation;

    }

    public class Hijri {

        @SerializedName("date")
        @Expose
        public String date;
        @SerializedName("format")
        @Expose
        public String format;
        @SerializedName("day")
        @Expose
        public String day;
        @SerializedName("weekday")
        @Expose
        public Weekday weekday;
        @SerializedName("month")
        @Expose
        public Month month;
        @SerializedName("year")
        @Expose
        public String year;
        @SerializedName("designation")
        @Expose
        public Designation designation;
        @SerializedName("holidays")
        @Expose
        public List<Object> holidays = null;

    }

    public class Meta {

        @SerializedName("latitude")
        @Expose
        public Float latitude;
        @SerializedName("longitude")
        @Expose
        public Float longitude;
        @SerializedName("timezone")
        @Expose
        public String timezone;
        @SerializedName("method")
        @Expose
        public Method method;
        @SerializedName("latitudeAdjustmentMethod")
        @Expose
        public String latitudeAdjustmentMethod;
        @SerializedName("midnightMode")
        @Expose
        public String midnightMode;
        @SerializedName("school")
        @Expose
        public String school;
        @SerializedName("offset")
        @Expose
        public Offset offset;

    }

    public class Method {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("params")
        @Expose
        public Params params;

    }

    public class Month {

        @SerializedName("number")
        @Expose
        public Integer number;
        @SerializedName("en")
        @Expose
        public String en;
        @SerializedName("ar")
        @Expose
        public String ar;

    }

    public class Month_ {

        @SerializedName("number")
        @Expose
        public Integer number;
        @SerializedName("en")
        @Expose
        public String en;

    }

    public class Params {

        @SerializedName("Fajr")
        @Expose
        public Integer fajr;
        @SerializedName("Isha")
        @Expose
        public Integer isha;

    }

    public class Offset {

        @SerializedName("Imsak")
        @Expose
        public Integer imsak;
        @SerializedName("Fajr")
        @Expose
        public Integer fajr;
        @SerializedName("Sunrise")
        @Expose
        public Integer sunrise;
        @SerializedName("Dhuhr")
        @Expose
        public Integer dhuhr;
        @SerializedName("Asr")
        @Expose
        public Integer asr;
        @SerializedName("Maghrib")
        @Expose
        public Integer maghrib;
        @SerializedName("Sunset")
        @Expose
        public Integer sunset;
        @SerializedName("Isha")
        @Expose
        public Integer isha;
        @SerializedName("Midnight")
        @Expose
        public Integer midnight;

    }

    public class Timings {

        @SerializedName("Fajr")
        @Expose
        public String fajr;
        @SerializedName("Sunrise")
        @Expose
        public String sunrise;
        @SerializedName("Dhuhr")
        @Expose
        public String dhuhr;
        @SerializedName("Asr")
        @Expose
        public String asr;
        @SerializedName("Sunset")
        @Expose
        public String sunset;
        @SerializedName("Maghrib")
        @Expose
        public String maghrib;
        @SerializedName("Isha")
        @Expose
        public String isha;
        @SerializedName("Imsak")
        @Expose
        public String imsak;
        @SerializedName("Midnight")
        @Expose
        public String midnight;

    }

    public class Weekday {

        @SerializedName("en")
        @Expose
        public String en;
        @SerializedName("ar")
        @Expose
        public String ar;

    }

    public class Weekday_ {

        @SerializedName("en")
        @Expose
        public String en;

    }
}
