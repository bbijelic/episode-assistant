package com.github.bbijelic.torrent.db;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DbTest.class);
    
    @Test
    public void testDb(){
        DBI dbi = new DBI("jdbc:sqlite:/tmp/data.db");
        Handle h = dbi.open();
        
        h.close();
    }
    
}
