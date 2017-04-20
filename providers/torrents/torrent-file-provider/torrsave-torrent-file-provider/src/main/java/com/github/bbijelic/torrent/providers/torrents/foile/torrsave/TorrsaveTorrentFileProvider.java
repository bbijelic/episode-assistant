package com.github.bbijelic.torrent.providers.torrents.foile.torrsave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.torrents.file.TorrentFileProvider;

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
     */
    public TorrsaveTorrentFileProvider(File torrentTargetDirectory){
        this.torrentTargetDirectory = torrentTargetDirectory;
        
        trustAllCerts = new TrustManager[] {new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {}
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {}
        }};
        
        try {
            
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            
        } 
        catch(NoSuchAlgorithmException nsae){}
        catch (KeyManagementException kme) {}
        
    }
    
    @Override
    public File getTorrentFile(String infoHash) {
        LOGGER.debug("ENTER: getTorrentFile(); infoHash={}", infoHash);
        
        // Apply the info hash to the url template
        String url = TORRSAVE_URL_TEMPLATE.replace("{INFOHASH}", infoHash);
        LOGGER.debug("Downloading torrent file from: {}", url);
        
        // Destination torrent file
        String destinationTorrentFile = this.torrentTargetDirectory.getAbsolutePath() + "/" + infoHash + ".torrent";
        
        try {
            
            URL torrentDownloadLink = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) torrentDownloadLink.openConnection();
            ReadableByteChannel rbc = Channels.newChannel(con.getInputStream());
            
            FileOutputStream fos = new FileOutputStream(destinationTorrentFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
                            
        } catch(MalformedURLException mue){
            LOGGER.error(mue.getMessage(), mue);
        } catch(IOException ioe){
            LOGGER.error(ioe.getMessage(), ioe);
        }
        
        // Torrent file instance
        File torrentFile = new File(destinationTorrentFile);
        LOGGER.debug("Downloaded torrent file: {}", torrentFile.toString());
        
        LOGGER.debug("LEAVING: getTorrentFile(); file={}", torrentFile.toString());
        return torrentFile;
    }
}
