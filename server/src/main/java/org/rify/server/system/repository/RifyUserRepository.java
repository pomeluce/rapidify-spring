package org.rify.server.system.repository;

import org.rify.common.core.repository.BaseRepository;
import org.rify.server.system.domain.entity.RifyUser;
import org.rify.server.system.domain.enums.RifyUserStatus;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/10 00:23
 * @className : RifyUserRepository
 * @description : RifyUser 数据持久层
 */
@NoRepositoryBean
public interface RifyUserRepository extends BaseRepository<RifyUser, Long> {

    Optional<RifyUser> findByAccount(String account);

    Optional<List<RifyUser>> findByStatus(RifyUserStatus status);
}
