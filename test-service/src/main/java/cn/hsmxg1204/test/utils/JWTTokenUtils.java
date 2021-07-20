package cn.hsmxg1204.test.utils;


import cn.hsmxg1204.test.constant.Part;
import cn.hsmxg1204.test.entity.PartContent;
import com.alibaba.nacos.common.codec.Base64;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import javax.crypto.Mac;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-23 14:13
 */
public class JWTTokenUtils {
    private static final String KEY = "HSMXG1204llllllll0000123";
    private static final String DOT=".";
    private static final Map<String,String> HEADERS = new HashMap<>(8);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        HEADERS.put("alg","HS256");
        HEADERS.put("typ","JWT");
    }

    public String generateHeaderPart() throws JsonProcessingException {
        byte[] headerBytes = OBJECT_MAPPER.writeValueAsBytes(HEADERS);
        String headerPart = new String(Base64.encodeBase64(headerBytes,false,true,128), StandardCharsets.UTF_8);
        System.out.println("generate HEADER value is :\t"+headerPart);
        return headerPart;
    }

    public String generatePayloadPart(Map<String,Object> claims) throws JsonProcessingException {
        byte[] payloadBytes = OBJECT_MAPPER.writeValueAsBytes(claims);
        String payloadPart = new String(Base64.encodeBase64(payloadBytes, false, true, 128), StandardCharsets.UTF_8);
        System.out.println("generate Payload value is :\t"+payloadPart);
        return payloadPart;
    }

    public String generateSignaturePart(String headerPart,String payloadPart){
        String content = headerPart + DOT + payloadPart;
        Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256,KEY.getBytes(StandardCharsets.UTF_8));
        byte[] output = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
        String signaturePart = new String(Base64.encodeBase64(output,false,true,128),StandardCharsets.UTF_8);
        System.out.println("generate SignaturePart value is :\t"+signaturePart);
        return signaturePart;
    }

    /**
     * 生成JWT
     * @param claims
     * @return
     * @throws JsonProcessingException
     */
    public String generate(Map<String,Object> claims) throws JsonProcessingException {
        String headerPart = generateHeaderPart();
        String payloadPart = generatePayloadPart(claims);
        String signaturePart = generateSignaturePart(headerPart, payloadPart);
        String jws = headerPart + DOT + payloadPart + DOT +signaturePart;
        System.out.println("JWT result is " + jws);
        return jws;
    }


    /**
     * 解析JWT
     * @param jwt
     * @return
     * @throws Exception
     */
    public Map<Part, PartContent> parse(String jwt) throws Exception {
        System.out.println("current parse jwt is " + jwt);
        Map<Part,PartContent> result = new HashMap<>(8);
        StringTokenizer tokenizer = new StringTokenizer(jwt,DOT);
        String[] jwtParts = new String[3];
        int idx = 0;
        while (tokenizer.hasMoreElements()){
            jwtParts[idx] = tokenizer.nextToken();
            idx++;
        }
        String headerPart = jwtParts[0];
        PartContent headerContent = new PartContent();
        headerContent.setRawContent(headerPart);
        headerContent.setPart(Part.HEADER);
        headerPart = new String(Base64.decodeBase64(headerPart.getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8);
        headerContent.setPairs(OBJECT_MAPPER.readValue(headerPart, new TypeReference<Map<String, Object>>() {
        }));
        result.put(Part.HEADER, headerContent);

        String payloadPart = jwtParts[1];
        PartContent payloadContent = new PartContent();
        payloadContent.setRawContent(payloadPart);
        payloadContent.setPart(Part.PAYLOAD);
        payloadPart = new String(Base64.decodeBase64(payloadPart.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        payloadContent.setPairs(OBJECT_MAPPER.readValue(payloadPart, new TypeReference<Map<String, Object>>() {
        }));
        result.put(Part.PAYLOAD, payloadContent);

        String signaturePart = jwtParts[2];
        PartContent signatureContent = new PartContent();
        signatureContent.setRawContent(signaturePart);
        signatureContent.setPart(Part.SIGNATURE);
        result.put(Part.SIGNATURE, signatureContent);
        return result;
    }

    /**
     * 验证JWT
     * @param jwt
     * @throws Exception
     */
    public void verify(String jwt) throws Exception {
        Map<Part,PartContent> parseResult = parse(jwt);
        PartContent headerContent = parseResult.get(Part.HEADER);
        PartContent payloadContent = parseResult.get(Part.PAYLOAD);
        PartContent signatureContent = parseResult.get(Part.SIGNATURE);
        String signaturePart = generateSignaturePart(headerContent.getRawContent(), payloadContent.getRawContent());
        if(!Objects.equals(signaturePart,signatureContent.getRawContent())){
            throw new IllegalStateException("签名校验失败");
        }
        String iss = payloadContent.getPairs().get("iss").toString();
        // iss校验
        if (!Objects.equals(iss, "throwx")) {
            throw new IllegalStateException("ISS校验异常");
        }
        long exp = Long.parseLong(payloadContent.getPairs().get("exp").toString());
        // exp校验,有效期14天
        if (System.currentTimeMillis() - exp > 24 * 3600 * 1000 * 14) {
            throw new IllegalStateException("exp校验异常,JWT已经过期");
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put("iss", "throwx");
        claims.put("jid", 10087L);
        claims.put("exp", 1613227468168L);
        JWTTokenUtils jwtTokenUtils = new JWTTokenUtils();
        System.out.println("自行生成的JWT:" + jwtTokenUtils.generate(claims));
    }

}

