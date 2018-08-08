package com.simbiose.service;

import okhttp3.Interceptor;
import okhttp3.Response;
import okio.Buffer;

import java.io.IOException;
import java.nio.charset.Charset;

public class CallInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Buffer buffer = new Buffer();
        chain.request().body().writeTo(buffer);
        String result = buffer.readUtf8();


        return null;
    }
}
