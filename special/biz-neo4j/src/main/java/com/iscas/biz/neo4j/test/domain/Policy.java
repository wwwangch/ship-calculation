package com.iscas.biz.neo4j.test.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;


@Data
@Accessors(chain = true)
@Node("policy")
public class Policy {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "title")
    private String title;

    //TODO 其他属性定义....

    @Relationship(type = "DEPENDENCE", direction = Relationship.Direction.INCOMING)
    private List<Policy> policys = new ArrayList<>();

}