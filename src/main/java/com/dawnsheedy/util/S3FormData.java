package com.dawnsheedy.util;

import org.jboss.resteasy.reactive.RestForm;

import java.io.File;

public class S3FormData {
    @RestForm("file")
    public File file;
}
