package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 留言返回对象
 */
@Data
public class LeaveMsgResVO {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 留言的上级关联id
     */
    private Integer parentId;
    /**
     * 留言的人
     */
    private String wordAuthorId;
    private String wordAuthorName;
    /**
     * 留言的目标
     */
    private String wordMasterId;
    private String wordMasterName;
    /**
     * 留言内容
     */
    private String msgContent;
    /**
     * 留言层级
     */
    private Integer msgLevel;
    /**
     * 留言时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 子留言
     */
    private LeaveMsgResVO child;
}
