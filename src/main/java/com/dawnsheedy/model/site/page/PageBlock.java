package com.dawnsheedy.model.site.page;

import jakarta.annotation.Nullable;

import java.util.List;

public class PageBlock {
    /**
     * Type of the page
     */
    public PageBlockType type;
    /**
     * Meta field
     * For images, used for aria-label
     */
    @Nullable
    public String meta;

    /**
     * Detail field
     * Used for content, paragraph text, image ID, etc.
     */
    @Nullable
    public String detail;

    /**
     * Sub blocks
     */
    @Nullable
    public List<PageBlock> children;
}
