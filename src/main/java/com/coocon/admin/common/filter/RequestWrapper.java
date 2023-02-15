package com.coocon.admin.common.filter;

import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

public class RequestWrapper extends HttpServletRequestWrapper {
    private final Charset encoding = Charset.forName("UTF-8");
    private byte[] rawData;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        request.getParameterMap();
        try (InputStream inputStream = request.getInputStream()) {
            this.rawData = StreamUtils.copyToByteArray(inputStream);
        }
    }

    @Override
    public ServletInputStream getInputStream(){
        return new CachedServletInputStream(this.rawData);
    }
    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }

    private static class CachedServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream buffer;

        public CachedServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        @Override
        public int read() throws IOException {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException("not support");
        }
    }


}
