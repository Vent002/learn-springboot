package cn.hsmxg1204.test.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RefundRecordReq {
    @ApiModelProperty(value = "医生id")
    private String doc_id;
    @ApiModelProperty(value = "医生姓名")
    private String doc_name;
    @ApiModelProperty(value = "接口返回的错误数据")
    private String err_msg;
    @ApiModelProperty(value = "总金额（缴费金额）")
    private String total_fee;
    @ApiModelProperty(value = "缴费项目")
    private String projects;
    @ApiModelProperty(value = "缴费项目名称")
    private String projectsName;
    @ApiModelProperty(value = "商户订单id")
    private String merchant_order_id;
    @ApiModelProperty(value = "微信订单id")
    private String wechar_order_id;
    @ApiModelProperty(value = "科室id")
    private String dept_id;
    @ApiModelProperty(value = "科室名称")
    private String dept_name;
    @ApiModelProperty(value = "医院id")
    private String hos_id;
    @ApiModelProperty(value = "用户名")
    private String user_name;
    @ApiModelProperty(value = "用户身份证")
    private String id_card;
    @ApiModelProperty(value = "用户hosuserid")
    private String hos_user_id;
    @ApiModelProperty(value = "用户openid")
    private String openid;
    @ApiModelProperty(value = "用户cicid")
    private String cicid;
    @ApiModelProperty(value = "用户电话")
    private String phone;
    @ApiModelProperty(value = "用户性别")
    private String sex;
    @ApiModelProperty(value = "缴费类别")
    private String type;

    @ApiModelProperty(value = "门诊号")
    private String mzId;
    @ApiModelProperty(value = "挂号预约ID")
    private String appointId;

    @ApiModelProperty(value = "挂号排队序号")
    private String ixh;
}
