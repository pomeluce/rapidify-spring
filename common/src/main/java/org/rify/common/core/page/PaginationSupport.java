package org.rify.common.core.page;

import org.rify.common.utils.spring.ServletClient;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/12 23:51
 * @className : PaginationSupport
 * @description : 数据分页支持器
 */
public class PaginationSupport {
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGE_SIZE = "pageSize";
    private static final String ORDER_BY_COLUMN = "orderByColumn";


    public static Pageable pageable() {
        return Pageable.builder()
                .pageNumber(ServletClient.getIntegerParameter(PAGE_NUMBER, 1))
                .pageSize(ServletClient.getIntegerParameter(PAGE_SIZE, 10))
                .orderByColumn(ServletClient.getParameter(ORDER_BY_COLUMN))
                .build();
    }
}
