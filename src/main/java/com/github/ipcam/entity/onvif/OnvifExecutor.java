package com.github.ipcam.entity.onvif;

import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.exception.CameraConnectionException;
import com.github.ipcam.entity.onvif.digest.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static com.google.common.net.HttpHeaders.CONTENT_TYPE;

/**
 * OnvifExecutor
 *
 * @author echils
 */
@Slf4j
public class OnvifExecutor {


    /**
     * The default media type of the soap protocol
     */
    private MediaType mediaType;


    /**
     * The http client
     */
    private OkHttpClient httpClient;


    /**
     * The camera
     */
    private NetworkCamera networkCamera;


    /**
     * The http protocol
     */
    private static final String HTTP = "http://";


    /**
     * The media type
     */
    private static final String MEDIA_TYPE_VALUE = "application/soap+xml; charset=utf-8;";


    /**
     * The content type
     */
    private static final String CONTENT_TYPE_VALUE = "text/xml; charset=utf-8";


    /**
     * The head of the soap protocol
     */
    private static final String SOAP_ENVELOPE_HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
            "<soap:Envelope " +
            "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
            "xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" >" +
            "<soap:Body>";


    /**
     * The tail of the soap protocol
     */
    private static final String SOAP_ENVELOPE_TAIL = "</soap:Body></soap:Envelope>";



    public OnvifExecutor(NetworkCamera camera) {
        if (camera == null || camera.isIllegal()) {
            log.error("The camera is illegal：{}", camera);
            throw new CameraConnectionException("The camera is illegal");
        }
        CredentialInfo credentials =
                new CredentialInfo(camera.getUsername(), camera.getPassword());
        DigestAuthenticator authenticator = new DigestAuthenticator(credentials);
        Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
                .addInterceptor(new AuthenticationCacheInterceptor(authCache))
                .build();
        this.networkCamera = camera;
        this.mediaType = MediaType.parse(MEDIA_TYPE_VALUE);

    }


    /**
     * execute the onvif command
     *
     * @param command The command
     * @param <T>     the return value
     */
    public <T> OnvifResultData<T> execute(OnvifCommand<T> command) {
        if (command == null) {
            log.warn("The onvif request command is null");
            return null;
        }
        try {
            RequestBody requestBody = RequestBody
                    .create(xmlBuild(command.content()), this.mediaType);
            Request request = new Request.Builder().url(HTTP + this.networkCamera.getIp() + ":" +
                    this.networkCamera.getPort() + command.uri())
                    .addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                    .post(requestBody).build();
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (response.code() == 200 && responseBody != null) {
                return OnvifResultData.success(command.parse(responseBody.string()));
            }
            return OnvifResultData.failed(response.message());
        } catch (Exception e) {
            return OnvifResultData.failed(e.toString());
        }
    }


    /**
     * Destroy the executor
     */
    public void destroy() {
        if (this.httpClient != null) httpClient.dispatcher().cancelAll();
    }


    /**
     * Build the request content of the soap protocol
     *
     * @param xmlContent the content
     */
    private String xmlBuild(String xmlContent) {
        Assert.notNull(xmlContent, "Xml content is null");
        return SOAP_ENVELOPE_HEAD + xmlContent + SOAP_ENVELOPE_TAIL;
    }

}
