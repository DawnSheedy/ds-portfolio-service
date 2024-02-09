package com.dawnsheedy.model.site;

import jakarta.annotation.Nullable;

import java.util.Date;

public class WorkHistoryEntry {
    public String company;
    public String title;
    public String companyUrl;
    public Date startDate;
    @Nullable
    public Date endDate;
    public String description;
}
