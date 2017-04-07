package com.github.bbijelic.torrent;

import com.github.bbijelic.torrent.config.ServiceConfiguration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Service extends Application<ServiceConfiguration> {
    
    @Override
    public String getName() {
        return "torrent-assistant";
    }
    
    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }
    
    @Override
    public void run(ServiceConfiguration config, Environment env) throws Exception {
        
    }
    
    public static void main(String[] args) throws Exception {
        new Service().run(new String[]{"server", System.getProperty("service.config")});
    }
    
}
