package com.antyron.services;

import java.io.IOException;
import java.util.Map;

/**
 * @author atyron
 */
public interface HTTPService {
    void sendGet(Map<String, String> data, String url) throws IOException;

    void sendPost(Map<String, String> data, String url) throws IOException;
}
