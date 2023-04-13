package com.iscas.base.biz.test.datasongplus.repository;

import com.iscas.base.biz.config.datasongplus.ConditionalDatasongPlus;
import com.iscas.base.biz.test.datasongplus.domain.Achievements;
import com.iscas.datasong.client.plus.repository.DetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/2 10:53
 * @since jdk1.8
 */
@Repository
@ConditionalDatasongPlus
public interface AchievementsRepository extends DetRepository<Achievements, String> {
    List<Achievements> findByAuthorCNLike(String name);
}
