package cn.hsmxg1204.test.entity.dto;

import cn.hsmxg1204.test.entity.JwtCacheContent;
import lombok.Data;

@Data
public class VerifyJwtResultDto {

    private Boolean valid;

    private Throwable throwable;

    private long jwtId;

    private JwtCacheContent content;
}