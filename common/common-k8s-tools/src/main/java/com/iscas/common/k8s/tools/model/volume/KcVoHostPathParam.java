package com.iscas.common.k8s.tools.model.volume;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 14:51
 * @since jdk1.8
 */
@SuppressWarnings("Lombok")
@Data
@Accessors(chain = true)
public class KcVoHostPathParam extends KcVolumeParam {
   /**
    * path
    * */
   private String path;

   /**
    * 类型 EmptyString / DirectoryOrCreate / Directory / FileOrCreate / File / Socket / CharDevice / BlockDevice
    * */
   private KcVoHostPathType type;

   @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
   public enum KcVoHostPathType {
      EmptyString, DirectoryOrCreate, Directory, FileOrCreate, File, Socket, CharDevice, BlockDevice
   }
}
