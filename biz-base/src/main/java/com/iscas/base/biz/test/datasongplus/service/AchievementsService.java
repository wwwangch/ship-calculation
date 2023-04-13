package com.iscas.base.biz.test.datasongplus.service;

import com.iscas.base.biz.config.datasongplus.ConditionalDatasongPlus;
import com.iscas.base.biz.test.datasongplus.domain.Achievements;
import com.iscas.base.biz.test.datasongplus.repository.AchievementsRepository;
import com.iscas.datasong.client.plus.model.Page;
import com.iscas.templet.view.table.TableResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/2 10:50
 * @since jdk1.8
 */
@Service
@ConditionalDatasongPlus
public class AchievementsService {
    @Autowired
    AchievementsRepository achievementsRepository;

    public List<Achievements> findByPage() {
        Page<Achievements> page = new Page<>();
        page.setPageNumber(1);
        page.setPageSize(10);
        Page<Achievements> usePage = achievementsRepository.findUsePage(page);
        return usePage.getData();
    }

    public String save(Achievements achievements) {
        String save = achievementsRepository.save(achievements);
        return save;
    }

    public List<String> saveBatch(List<Achievements> achievements) {
        return achievementsRepository.saveBatch(achievements);
    }

    public List<Achievements> findByAuthorCNLike(String name) {
        return achievementsRepository.findByAuthorCNLike(name);
    }

    public boolean delete(String _id) {
        return achievementsRepository.delete(_id);
    }

    public TableResponseData<List> findToTable() {
        return achievementsRepository.findAllToTable();

    }
}
