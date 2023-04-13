package com.iscas.common.tools.core.io.rar;

import com.github.junrar.UnrarCallback;
import com.github.junrar.Volume;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * rar工具测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/14 16:19
 * @since jdk1.8
 */
public class RarUtilsTests {

    @Test
    @Disabled
    public void test() throws Exception {
        RarUtils.unrar("F:\\\u9879\u76ee\\\u5c0f\u536b\u661f\\ProducerConsumer.rar", "F:\\项目\\小卫星\\a", new UnrarCallback() {
            int currentProgress = -1;
            @Override
            public boolean isNextVolumeReady(Volume volume) {
                return true;
            }
            @Override
            public void volumeProgressChanged(long l, long l1) {
                int progress = (int)((double)l/l1*100);
                if(currentProgress != progress){
                    currentProgress = progress;
                    System.out.println("Unrar :" + progress);
                }
            }
        });
    }
}
