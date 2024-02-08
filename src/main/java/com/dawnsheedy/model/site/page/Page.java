package com.dawnsheedy.model.site.page;

import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Page {
    public String title;
    @Nullable
    public String subTitle;
    public String slug;
    public List<PageBlock> content;
    public boolean showInMenu;

    public Page() {
        title = "New Page";
        slug = "new-page";
        content = new ArrayList<>();
        showInMenu = false;
    }
}
