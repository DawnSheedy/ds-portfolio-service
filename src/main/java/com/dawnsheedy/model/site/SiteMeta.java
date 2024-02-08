package com.dawnsheedy.model.site;

import com.dawnsheedy.model.site.page.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SiteMeta {
    public String title;
    public String subTitle;

    public SiteMeta() {
        this.title = "New Site";
        this.subTitle = "New site created with ds-portfolio-ws";
    }
}
