package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.biz.domain.common.Opration;
import com.iscas.biz.mapper.common.OprationMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.templet.view.table.ComboboxData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限操作service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/22 18:06
 * @since jdk1.8
 */
@Service
@ConditionalOnMybatis
public class OprationService extends ServiceImpl<OprationMapper, Opration> {

    public List<ComboboxData<Opration>> combobox() {
        List<Opration> oprations = this.list();
        List<ComboboxData<Opration>> comboboxDatas = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(oprations)) {
            comboboxDatas = oprations.stream().map(opration -> new ComboboxData<Opration>()
                    .setLabel(opration.getOpName())
                    .setValue(opration.getOpId())
                    .setData(opration)).toList();
        }
        return comboboxDatas;
    }
}
