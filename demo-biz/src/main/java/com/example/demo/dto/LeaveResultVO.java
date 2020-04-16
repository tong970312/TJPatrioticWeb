package com.example.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * 留言最终响应对象
 */
@Data
public class LeaveResultVO {
    private Integer count;

    private List<LeaveMsgResVO> result;
}
