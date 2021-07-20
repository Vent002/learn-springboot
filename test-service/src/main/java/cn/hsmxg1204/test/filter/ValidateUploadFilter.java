package cn.hsmxg1204.test.filter;

import cn.hsmxg1204.core.exception.MyException;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-10 9:55
 */
@WebFilter("/upload/*")
public class ValidateUploadFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String disgest = req.getHeader("Signature-Method");
        String signature = req.getHeader("Signature");

        if(StringUtils.isEmpty(disgest) || StringUtils.isEmpty(signature)){
            throw new MyException("Missing signature");
        }

        MessageDigest md = getMessageDigest(disgest);

        InputStream input = new DigestInputStream(request.getInputStream(),md);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (;;){
            int len = input.read(buffer);
            if(len == -1){
                break;
            }
            output.write(buffer,0,len);
        }
        String hexString = toHexString(md.digest());
        if(hexString.equals(signature)){
            chain.doFilter(new ReReadableHttpServletRequest(req,output.toByteArray()),response);
        }else{

            throw new MyException("Invalid signature");
        }
    }

    private String toHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest){
            // x 表示十六进制输出
            // 02 表示不足两位，前面补0 输出，如果超过两位，则以实际输出。
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }

    private MessageDigest getMessageDigest(String disgest) {
        try {
            return MessageDigest.getInstance(disgest);
        } catch (NoSuchAlgorithmException e) {
            throw new MyException(e.getMessage());
        }
    }
}
