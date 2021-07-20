package cn.hsmxg1204.test.entity.dto;

import lombok.Data;

@Data
public class CreateJwtDto {

    private Long customerId;

    private String customerName;

    private String customerPhone;
}