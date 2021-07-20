package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.test.entity.dto.CreateJwtDto;
import cn.hsmxg1204.test.entity.dto.VerifyJwtResultDto;
import cn.hsmxg1204.test.service.JwtSpi;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-05-17 15:44
 */
@Component
public class HMAC256JwtSpiImpl implements JwtSpi, InitializingBean, EnvironmentAware {

    private SecretKey secretKey;
    private Environment environment;
    private int minSeed;
    private String issuer;
    private int seed;
    private Random random;

    @Override
    public String generate(CreateJwtDto dto) {
        long duration = this.random.nextInt(this.seed) + minSeed;
        Map<String,Object> claims = new HashMap<>(8);
        claims.put("iss",issuer);
        claims.put("jti",dto.getCustomerId());
        claims.put("uid",dto.getCustomerId());
        claims.put("exp", TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) + duration);
        String jwt = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .signWith(this.secretKey, SignatureAlgorithm.HS256)
                .addClaims(claims)
                .compact();
        //可以在这儿缓存uid

        return jwt;
    }

    @Override
    public VerifyJwtResultDto verify(String jwt) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .requireIssuer(this.issuer)
                .setSigningKey(this.secretKey)
                .build();
        VerifyJwtResultDto resultDto = new VerifyJwtResultDto();
        try{
            Jws<Claims> parseResult = jwtParser.parseClaimsJws(jwt);
            Claims claims = parseResult.getBody();
            long jti = Long.parseLong(claims.getId());
            if(isInBlockList(jti)){
                throw new IllegalArgumentException(String.format("jti is in block list,[i:%d]",jti));
            }
            Long uid = claims.get("uid", Long.class);
            resultDto.setValid(Boolean.TRUE);
        }catch (Exception e){
            resultDto.setValid(Boolean.FALSE);
            resultDto.setThrowable(e);
        }
        return resultDto;
    }

    @Override
    public void blockJwt(long jwtId) {

    }

    @Override
    public boolean isInBlockList(long jwtId) {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String secretKey = Objects.requireNonNull(environment.getProperty("jwt.hmac.secretKey"));
        this.minSeed = Objects.requireNonNull(environment.getProperty("jwt.exp.seed.min",Integer.class));
        int maxSeed = Objects.requireNonNull(environment.getProperty("jwt.exp.seed.max", Integer.class));
        this.issuer = Objects.requireNonNull(environment.getProperty("jwt.issuer"));
        this.random = new Random();
        this.seed = (maxSeed - minSeed);
        this.secretKey = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
