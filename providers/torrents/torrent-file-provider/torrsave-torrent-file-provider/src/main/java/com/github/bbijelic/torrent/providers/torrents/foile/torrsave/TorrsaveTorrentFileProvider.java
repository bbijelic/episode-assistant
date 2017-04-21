package com.github.bbijelic.torrent.providers.torrents.foile.torrsave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.torrents.file.TorrentFileProvider;
import com.github.bbijelic.torrent.core.torrents.file.TorrentFileProviderException;

/**
 * Torrsave.top torrent file provider
 * 
 * @author Bojan Bijelić
 */
public class TorrsaveTorrentFileProvider implements TorrentFileProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TorrsaveTorrentFileProvider.class);
    
    /**
     * Torrsave.top url template
     */
    private static final String TORRSAVE_URL_TEMPLATE = "https://torrasave.top/torrent/{INFOHASH}.torrent";
    
    /**
     * Trust manager
     */
    TrustManager[] trustAllCerts;
    
    /**
     * Torrent target directory
     */
    File torrentTargetDirectory;
    
    /**
     * Constructor
     * 
     * @param torrentTargetDirectory the torrent target directory
     * @param trustManager the trust manager
     * @param hostnameVerifier the hostname verifier
     * @throws TorrentFileProviderException
     */
    public TorrsaveTorrentFileProvider (
        final File torrentTargetDirectory, 
        final X509TrustManager trustManager, 
        final HostnameVerifier hostnameVerifier) throws TorrentFileProviderException {
            
        this.torrentTargetDirectory = torrentTargetDirectory;
        
        // Add ssl trust manager
        trustAllCerts = new TrustManager[] { trustManager };
        
        try {
            
            // Initialize SSL
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
            
        } catch(NoSuchAlgorithmException | KeyManagementException e){
            LOGGER.error(e.getMessage(), e);
            // Throw torrent file provider exception
            throw new TorrentFileProviderException(e);
        }
        
    }
    
    @Override
    public File getTorrentFile(String infoHash) throws TorrentFileProviderException {
        LOGGER.debug("ENTER: getTorrentFile(); infoHash={}", infoHash);
        
        // Apply the info hash to the url template
        String url = TORRSAVE_URL_TEMPLATE.replace("{INFOHASH}", infoHash);
        LOGGER.debug("Downloading torrent file from: {}", url);
        
        // Destination torrent file string builder
        StringBuilder destinationTorrentFileSB = new StringBuilder();
        destinationTorrentFileSB.append(torrentTargetDirectory.getAbsolutePath());
        destinationTorrentFileSB.append("/");
        destinationTorrentFileSB.append(infoHash);
        destinationTorrentFileSB.append(".torrent");
        
        try {
            
            URL torrentDownloadLink = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) torrentDownloadLink.openConnection();
            ReadableByteChannel rbc = Channels.newChannel(con.getInputStream());
            
            FileOutputStream fos = new FileOutputStream(destinationTorrentFileSB.toString());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
                            
        } catch(IOException e){
            LOGGER.error(e.getMessage(), e);
            // Throw torrent file provider exception
            throw new TorrentFileProviderException(e);
        } 
        
        // Torrent file instance
        File torrentFile = new File(destinationTorrentFileSB.toString());
        LOGGER.debug("Downloaded torrent file: {}", torrentFile.toString());
        
        LOGGER.debug("LEAVING: getTorrentFile(); file={}", torrentFile.toString());
        return torrentFile;
    }
}
