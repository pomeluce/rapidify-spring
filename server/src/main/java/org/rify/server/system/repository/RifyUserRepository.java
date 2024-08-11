package org.rify.server.system.repository;

import org.rify.server.system.domain.entity.RifyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/10 00:23
 * @className : RifyUserRepository
 * @description : RifyUser 数据持久层
 */
@Repository
public interface RifyUserRepository extends JpaRepository<RifyUser, Long>, JpaSpecificationExecutor<RifyUser>, QuerydslPredicateExecutor<RifyUser> {
    Optional<RifyUser> findRifyUserByAccount(String account);
}
