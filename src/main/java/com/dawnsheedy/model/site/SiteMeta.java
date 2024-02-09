package com.dawnsheedy.model.site;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class SiteMeta {
    public String title;
    public String subTitle;
    public List<String> pronouns;
    public String linkedInUrl;
    public String githubUrl;

    public SiteMeta() {
        this.title = "New Site";
        this.subTitle = "New site created with ds-portfolio-ws";
        this.pronouns = new ArrayList<>();
        this.linkedInUrl = "";
        this.githubUrl = "";
    }
}
