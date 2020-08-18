package com.haoyongsys.erp.common.pojo;

import java.util.List;

import lombok.Data;

/**
 * 封装分页结果返回
 */
@Data
public class PageResult<T> {

    private long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
