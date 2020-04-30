package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 留言返回对象
 */
@Data
public class LeaveMsgResVO {
    @Override
    public String toString() {
        return "LeaveMsgResVO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", wordAuthorId='" + wordAuthorId + '\'' +
                ", wordAuthorName='" + wordAuthorName + '\'' +
                ", wordMasterId='" + wordMasterId + '\'' +
                ", wordMasterName='" + wordMasterName + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", createDate=" + createDate +
                ", cityCode='" + cityCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }

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
//    private Integer msgLevel;
    /**
     * 留言时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String cityCode;

    private String areaCode;

    private String areaName;

    private List<LeaveMsgResVO> list;

}
