package uk.hotten.tkmpuller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Data {
    public static class ResponseWrapper {
        @JsonProperty("response")
        public Response response;

        @JsonProperty("facet_counts")
        public FacetCounts facetCounts;

        @JsonProperty("stats")
        public Stats stats;

        @JsonProperty("metadata")
        public Metadata metadata;

        @JsonProperty("category_map")
        public Object categoryMap;

        @JsonProperty("did_you_mean")
        public Object didYouMean;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Response {
        @JsonProperty("numFound")
        public int numFound;

        @JsonProperty("start")
        public int start;

        @JsonProperty("docs")
        public List<Doc> docs;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Doc {
        @JsonProperty("pid")
        public String pid;

        @JsonProperty("title")
        public String title;

        @JsonProperty("brand")
        public String brand;

        @JsonProperty("thumb_image")
        public String thumbImage;

        @JsonProperty("url")
        public String url;

        @JsonProperty("description")
        public String description;

        @JsonProperty("price")
        public double price;

        @JsonProperty("published_date")
        public double publishedDate;

        @JsonProperty("is_bundle")
        public boolean isBundle;

        @JsonProperty("style_name")
        public String styleName;

        @JsonProperty("fmt_price")
        public String fmtPrice;

        @JsonProperty("fmt_rrp")
        public String fmtRRP;

        @JsonProperty("badge_image")
        public String badgeImage;

        @JsonProperty("save_price")
        public double savePrice;

        @JsonProperty("percent_saving")
        public double percentSaving;

        @JsonProperty("mh_class")
        public String mhClass;

        @JsonProperty("mh_dept_name")
        public String mhDeptName;

        @JsonProperty("mh_dept")
        public String mhDept;

        @JsonProperty("department")
        public String department;

        @JsonProperty("is_shy_brand")
        public boolean isShyBrand;

        @JsonProperty("primary_category_name")
        public String primaryCategoryName;

        @JsonProperty("badge_name")
        public String badgeName;

        @JsonProperty("primary_category_code")
        public String primaryCategoryCode;

        @JsonProperty("fmt_was_price")
        public String fmtWasPrice;

        @JsonProperty("primary_category_path")
        public String primaryCategoryPath;

        @JsonProperty("stock")
        public double stock;

        @JsonProperty("mh_class_name")
        public String mhClassName;

        @JsonProperty("published_days")
        public double publishedDays;

        @JsonProperty("badge_aa_text")
        public String badgeAaText;

        @JsonProperty("fmt_save_price")
        public String fmtSavePrice;

        @JsonProperty("bundle_skuid")
        public String bundleSkuid;

        @JsonProperty("rrp")
        public double rrp;

        @JsonProperty("is_low_stock")
        public boolean isLowStock;

        @JsonProperty("code")
        public String code;

        @JsonProperty("was_price")
        public double wasPrice;

        @JsonProperty("variants")
        public List<Variant> variants;

        @JsonProperty("material")
        public String material;

        @JsonProperty("mat_badge_image")
        public String matBadgeImage;

        @JsonProperty("mat_badge_name")
        public String matBadgeName;

        @JsonProperty("mat_badge_aa_text")
        public String matBadgeAAText;

        @JsonProperty("bundle_rrp")
        public String bundleRrp;

        @JsonProperty("bundle_name")
        public String bundleName;

        @JsonProperty("bundle_price")
        public String bundlePrice;

        @JsonProperty("bundle_was_price")
        public String bundleWasPrice;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Variant {
        @JsonProperty("skuid")
        public String skuid;

        // Getters and setters
        // toString() method for debugging
    }

    public static class FacetCounts {
        @JsonProperty("facet_fields")
        public Map<String, List<FacetField>> facetFields;

        @JsonProperty("facet_queries")
        public Map<String, Object> facetQueries;

        @JsonProperty("facet_ranges")
        public Map<String, List<FacetRange>> facetRanges;

        // Getters and setters
        // toString() method for debugging
    }

    public static class FacetField {
        @JsonProperty("name")
        public String name;

        @JsonProperty("count")
        public int count;

        // Getters and setters
        // toString() method for debugging
    }

    public static class FacetRange {
        @JsonProperty("start")
        public String start;

        @JsonProperty("end")
        public String end;

        @JsonProperty("count")
        public int count;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Stats {
        @JsonProperty("stats_fields")
        public Map<String, StatsField> statsFields;

        // Getters and setters
        // toString() method for debugging
    }

    public static class StatsField {
        @JsonProperty("min")
        public double min;

        @JsonProperty("max")
        public double max;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Metadata {
        @JsonProperty("query")
        public Query query;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Query {
        @JsonProperty("precision")
        public Precision precision;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Precision {
        @JsonProperty("configured")
        public Configured configured;

        @JsonProperty("applied")
        public Applied applied;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Configured {
        @JsonProperty("value")
        public String value;

        // Getters and setters
        // toString() method for debugging
    }

    public static class Applied {
        @JsonProperty("value")
        public String value;

        // Getters and setters
        // toString() method for debugging
    }
}