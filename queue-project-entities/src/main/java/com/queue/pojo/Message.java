package com.queue.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value="留言对象模型")
public class Message {
    @ApiModelProperty(value="ID",name = "id",notes = "Primary Key")
    private Integer id;
    @ApiModelProperty(value="用户ID")
    private Integer userId;
    @ApiModelProperty(value="留言内容")
    private String messageContent;
    @ApiModelProperty(value="员工ID")
    private Integer staffId;
    @ApiModelProperty(value="留言等级")
    private Integer messageType;
    @ApiModelProperty(value="队列ID")
    private Integer queueId;
    private String queueNo;
    private String staffName;
    private String staffNo;
    private String userNo;
}
