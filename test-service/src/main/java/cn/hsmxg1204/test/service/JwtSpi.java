package cn.hsmxg1204.test.service;

import cn.hsmxg1204.test.entity.dto.CreateJwtDto;
import cn.hsmxg1204.test.entity.dto.VerifyJwtResultDto;

public interface JwtSpi {

    /**
     * 生成JWT
     *
     * @param dto dto
     * @return String
     */
    String generate(CreateJwtDto dto);

    /**
     * 校验JWT
     *
     * @param jwt jwt
     * @return VerifyJwtResultDto
     */
    VerifyJwtResultDto verify(String jwt);

    /**
     * 把JWT添加到封禁名单中
     *
     * @param jwtId jwtId
     */
    void blockJwt(long jwtId);

    /**
     * 判断JWT是否在封禁名单中
     *
     * @param jwtId jwtId
     * @return boolean
     */
    boolean isInBlockList(long jwtId);
}