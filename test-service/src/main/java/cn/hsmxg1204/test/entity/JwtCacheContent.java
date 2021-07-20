package cn.hsmxg1204.test.entity;

import lombok.Data;

@Data
public class JwtCacheContent {

    private Long customerId;

    private String customerName;

    private String customerPhone;
}