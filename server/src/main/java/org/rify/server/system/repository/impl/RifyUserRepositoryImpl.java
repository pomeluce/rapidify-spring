package org.rify.server.system.repository.impl;

import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.rify.common.core.page.Pageable;
import org.rify.common.core.repository.BaseRepositoryImpl;
import org.rify.server.system.domain.entity.QRifyUser;
import org.rify.server.system.domain.entity.RifyUser;
import org.rify.server.system.domain.enums.RifyUserStatus;
import org.rify.server.system.repository.RifyUserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/12 00:00
 * @className : RifyUserRepositoryImpl
 * @description : RifyUser 数据持久层实现
 */
@Repository
public class RifyUserRepositoryImpl extends BaseRepositoryImpl<RifyUser, Long> implements RifyUserRepository {

    final QRifyUser user = QRifyUser.rifyUser;

    public RifyUserRepositoryImpl(EntityManager entityManager, BlazeJPAQueryFactory factory) {
        super(RifyUser.class, entityManager, factory);
    }

    public Optional<RifyUser> findByAccount(String account) {
        return Optional.ofNullable(factory.selectFrom(user).where(user.account.eq(account)).fetchOne());
    }

    @Override
    public Optional<List<RifyUser>> findByStatus(RifyUserStatus status) {
        Pageable<String> page = new Pageable<>();
        page.setPageNumber(1);
        page.setPageSize(2);
        page.setSort(Sort.Direction.DESC);
        page.setOrderByColumn("id");
        page.setDefaultOrder(user.account.asc());
        return this.fetchPage(factory.selectFrom(user).where(user.status.eq(status)).orderBy(user.createTime.desc()), page);
    }

}
