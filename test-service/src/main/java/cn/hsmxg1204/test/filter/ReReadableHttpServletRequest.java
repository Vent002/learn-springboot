package cn.hsmxg1204.test.filter;

import cn.hsmxg1204.core.exception.MyException;
import org.springframework.stereotype.Component;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 * HttpServletRequest 只能进行一次读取，
 * 如果Filter 调用getInputStream()读取了一次数据，后续Servlet处理时，再次读取，将无法读到任何数据。
 *
 * @author gxming
 * @description 伪造 HttpServletRequest ；使用代理模式，返回一个新的流。
 * @date 2021-07-10 10:14
 */
public class ReReadableHttpServletRequest extends HttpServletRequestWrapper {
    private byte[] body;
    private boolean open =false;

    public ReReadableHttpServletRequest(HttpServletRequest request,byte[] body) {
        super(request);
        this.body = body;
    }

    public ServletInputStream getInputStream(){
        if(open){
            throw new MyException("Cannot re-open input stream");
        }
        open = true;
        return new ServletInputStream() {
            private int offset = 0;

            @Override
            public boolean isFinished() {
                return offset >= body.length;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                if(offset >= body.length){
                    return -1;
                }
                int n = body[offset] & 0xff;
                offset++;
                return n;
            }
        };
    }

    public BufferedReader getReader(){
        if(open){
            throw new  MyException("Cannot re-open reader");
        }
        open = true;
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body), StandardCharsets.UTF_8));
    }
}
