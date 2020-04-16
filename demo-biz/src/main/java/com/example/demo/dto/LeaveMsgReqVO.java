package com.example.demo.dto;

import lombok.Data;
/**
 * 留言请求对象
 */
@Data
public class LeaveMsgReqVO {
    @Override
    public String toString() {
        return "LeaveMsgReqVO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", wordAuthorId='" + wordAuthorId + '\'' +
                ", wordMasterId='" + wordMasterId + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", msgLevel=" + msgLevel +
                ", cityCode='" + cityCode + '\'' +
                '}';
    }

    private Integer id;
    /**
     * 留言的上级关联id
     */
    private Integer parentId;
    /**
     * 留言的人
     */
    private String wordAuthorId;
    /**
     * 留言的目标
     */
    private String wordMasterId;
    /**
     * 留言内容
     */
    private String msgContent;
    /**
     * 留言层级
     */
    private Integer msgLevel;
    /**
     * 城市区县编码
     */
    private String cityCode;
}
