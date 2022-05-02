package com.rota.freemarker;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
public class FreeMarkerConfig {
    

    public Configuration cfg;
    
    public FreeMarkerConfig() {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        try {
            cfg.setDirectoryForTemplateLoading(new File("/home/greg/java_prjects/rota/rota/src/main/resources/templates"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}