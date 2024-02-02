package com.dawnsheedy.bean;

import com.dawnsheedy.model.identity.User;
import com.dawnsheedy.model.site.Site;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestContext {
    private Site site;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSiteMeta(Site site) {
        this.site = site;
    }

    public Site getSiteMeta() {
        return site;
    }
}
