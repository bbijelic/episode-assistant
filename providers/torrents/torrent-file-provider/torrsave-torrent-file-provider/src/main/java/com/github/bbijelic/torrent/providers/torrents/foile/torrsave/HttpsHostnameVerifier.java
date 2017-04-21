package com.github.bbijelic.torrent.providers.torrents.foile.torrsave;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * HTTPS hostname verifier
 * 
 * @author Bojan Bijelić
 */
public class HttpsHostnameVerifier implements HostnameVerifier {
    
    @Override
    public boolean verify(String arg0, SSLSession arg1) {
        return true;
    }
    
}
