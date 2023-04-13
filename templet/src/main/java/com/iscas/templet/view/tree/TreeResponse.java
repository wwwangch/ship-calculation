package com.iscas.templet.view.tree;

import com.iscas.templet.common.ResponseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhuquanwen
 * @date 2017/12/25 17:19
 **/
@SuppressWarnings("rawtypes")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class TreeResponse extends ResponseEntity<TreeResponseData> implements Serializable{

    /**重写*/
    @Override
    public TreeResponse setValue(TreeResponseData treeResponseData) {
        this.value = treeResponseData;
        return this;
    }

}
