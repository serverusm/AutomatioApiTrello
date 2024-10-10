package com.trello.utils;

import org.apache.logging.log4j.LogManager;

import java.util.Properties;
import java.util.logging.Logger;

public final class PropertiesInfo {
    private static final Logger LOGGER= LogManager.getLogger(PropertiesInfo.class.getSimpleName());
    private static final String CONFIG_PROPERTIES=PathUtils.buildPath("gradle.properties");
    private static final String BASE_API="apiUrl";
    private static final String PARTICULAR_PATH = PathUtils.buildPath("trello-core/src/main/resources/propertiesEnv/");
    private static PropertiesInfo instance;
    private Properties properties;

    /**
     * Constructor method
     */
    private PropertiesInfo(){
        loadProperties();
    }
    /**
     * this method is going to retrieve just one instance of the PropertiesInfo class.
     *
     * @return Properties Info instance.
     */
    private static PropertiesInfo getInstance(){
        if (instance==null){
            instance=new PropertiesInfo();
        }
        return instance;
    }
    /**
     * Method to load properties with general and particular information from properties files.
     */
    private coid loadProperties(){}


}
