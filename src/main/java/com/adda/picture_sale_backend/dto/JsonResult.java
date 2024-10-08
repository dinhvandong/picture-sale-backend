package com.adda.picture_sale_backend.dto;

import java.util.List;

public class JsonResult {
    private List<Data> data;
    private String actionResult;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getActionResult() {
        return actionResult;
    }

    public void setActionResult(String actionResult) {
        this.actionResult = actionResult;
    }
}
