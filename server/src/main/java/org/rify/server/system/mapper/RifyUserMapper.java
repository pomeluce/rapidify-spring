package org.rify.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.rify.server.system.domain.entity.RifyUser;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27下午11:19
 * @className : RifyUserMapper
 * @description : RifyUser 数据持久层
 */
@Mapper
public interface RifyUserMapper extends BaseMapper<RifyUser> {
}
