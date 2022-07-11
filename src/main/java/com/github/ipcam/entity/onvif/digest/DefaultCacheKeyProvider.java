package com.github.ipcam.entity.onvif.digest;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * DefaultCacheKeyProvider
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public final class DefaultCacheKeyProvider implements CacheKeyProvider {
    /**
     * Provides the caching key for the given request. Can be used to share passwords accross multiple subdomains.
     *
     * @param request the http request.
     * @return the cache key.
     */
    @Override
    public String getCachingKey(Request request) {
        final HttpUrl url = request.url();
        if (url == null)
            return null;
        return url.scheme() + ":" + url.host() + ":" + url.port();
    }
}
