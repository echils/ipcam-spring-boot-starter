package com.github.ipcam.onvif.digest;

import okhttp3.Request;

/**
 * CacheKeyProvider
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public interface CacheKeyProvider {

    /**
     * Provides the caching key for the given request. Can be used to share passwords accross multiple subdomains.
     *
     * @param request the http request.
     * @return the cache key.
     */
    String getCachingKey(Request request);

}
