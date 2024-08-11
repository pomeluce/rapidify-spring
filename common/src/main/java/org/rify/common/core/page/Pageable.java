package org.rify.common.core.page;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.rify.common.utils.StringUtils;
import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/11 21:39
 * @className : Pageable
 * @description : 分页对象
 */
// @Builder
@Getter
@Setter
public class Pageable<T extends Comparable<T>> implements Serializable {
    private static final @Serial long serialVersionUID = 1L;
    /* 当前页 */
    private Integer pageNumber;
    /* 叶容量 */
    private Integer pageSize;
    /* 排序方式 */
    private Sort.Direction sort = Sort.Direction.ASC;
    /* 排序列 */
    private String orderByColumn;
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private OrderSpecifier<T> orderSpecifier;

    public Optional<OrderSpecifier<T>> getDefaultOrder() {
        return Optional.ofNullable(this.orderSpecifier);
    }

    public void setDefaultOrder(OrderSpecifier<T> orderSpecifier) {
        this.orderSpecifier = orderSpecifier;
    }

    /**
     * 偏移量
     */
    public int offset() {
        return (this.pageNumber - 1) * pageSize;
    }

    public String getOrderBy() {
        return StringUtils.isBlank(orderByColumn) ? "" : StringUtils.toSnakeCase(orderByColumn) + " " + sort.toString().toLowerCase();
    }

    public Optional<Sort> getJpaOrderBy() {
        if (StringUtils.isEmpty(this.orderByColumn)) return Optional.empty();
        Sort sort = Sort.by(this.orderByColumn);
        if (this.sort.isAscending()) sort.ascending();
        else sort.descending();
        return Optional.of(sort);
    }

    public Optional<OrderSpecifier<String>> getDslOrderBy() {
        if (StringUtils.isEmpty(this.orderByColumn)) return Optional.empty();
        StringPath path = Expressions.stringPath(this.orderByColumn);
        OrderSpecifier<String> orderSpecifier = this.sort.isAscending() ? path.asc() : path.desc();
        return Optional.of(orderSpecifier);
    }

}
