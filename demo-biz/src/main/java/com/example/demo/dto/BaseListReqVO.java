package com.example.demo.dto;

import com.example.demo.dao.entity.PageModel;
import lombok.Data;

@Data
public class BaseListReqVO extends PageModel {

    private String parentId;

}
