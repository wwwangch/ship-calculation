package com.iscas.biz.neo4j.test.repository;

import com.iscas.biz.neo4j.test.domain.Policy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/22 15:49
 * @since jdk1.8
 */
public interface PolicyRepository extends CrudRepository<Policy, Long> {
    List<Policy> findAll();

    List<Policy> findByTitleLike(String title);

//    List<ExtendedTestEntity> findAllExtendedEntites();
//
//    List<TestEntityInterfaceProjection> findAllInterfaceProjections();
//
//    List<TestEntityDTOProjection> findAllDTOProjections();
//
//    @Query("MATCH (t:TestEntity) - [r:RELATED_TO] -> () RETURN t, COUNT(r) AS numberOfRelations")
//    List<TestEntityDTOProjection> findAllDTOProjectionsWithCustomQuery();
}
