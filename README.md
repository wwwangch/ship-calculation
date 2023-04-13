# 工程使用说明 
---



工程使用说明可参见码云的wiki(coding的wiki不支持导出PDF和HTML)：[https://gitee.com/quanwenz/newframe/wikis](https://gitee.com/quanwenz/newframe/wikis)



版本更新日志
===================================================

- 3.0.0-20221130-1

  - 1、继承2.1.3-20221116-1版本的大部分功能
  - 2、Springboot版本升级至3.0.0并做相应适配
  - 3、将使用的第三方依赖未提供对@AutoConfiguration支持的starter配置到META/INF下的org.springframework.boot.autoconfigure.AutoConfiguration.imports中
  - 4、添加Tess4J工具类模块，提供图片识别支持
  - 5、添加aspose.pdf的包，新增word行复制等工具
  - 6、调整一些包的日志级别为info
-----------------------------------------------

- 2.1.3-20221116-1

  - 1、添加SpringDoc配置
  - 2、解决ErrorPageController误引用了jakarta导致异常信息无法正确提示的BUG
  - 3、修改CsvUtils中的BUG
  - 4、修改TableDefinitionService中的BUG
  - 5、修复MybatisGeneralUtils中的BUG
  - 6、解决引入了多个datasong-client-0.2.4.jar导致无法争取打jar包的BUG
  - 7、升级springboot版本至2.7.5
  - 8、添加DatasourceRegistryUtils工具类实现数据源的动态添加与移除
-----------------------------------------------

- 2.1.3-20220929-1

  - 1、添加Exceptions工具类，简化一些抛异常的写法
  - 2、优化common-docker-tools、common-etcd-tools、common-k8s-tools、common-redis-tools、common-rpc-tools模块代码
  - 3、添加common-aspose-tools 模块丰富word操作
  - 4、移动admin-server、biz-ssh-server到special下
  - 5、添加datasong-connect-java模块
  - 6、修复showdjar依赖每次都要重新下载的问题
  - 7、升级springboot版本至2.7.4
  - 8、修改@EnableAuth不开启但InitUserRoleFilter还会执行的BUG
  - 9、使用springdoc替换springfox，解决swagger不更新导致与springboot不兼容的问题，删除knife4j的依赖
-----------------------------------------------

- 2.1.2-20220704-1

  - 1、添加TreeHelper、ComboboxHelper工具类
  - 2、修复Websocket不可用的BUG
  - 3、修改MinioService的实现
  - 4、JWTUtils中添加解析HMAC256生成的token的函数
  - 5、升级了部分第三方依赖的版本
  - 6、spring boot版本升级至2.7.1
  - 7、修复刷新gradle时卡在build model很久的问题
  - 8、重新实现ReflectUtils工具类
  - 9、去除ice-blue的maven依赖，spire.doc修改为直接jar包引入方式
-----------------------------------------------

- 2.1.1-20220530-1

  - 1、添加启动后显示进程号、端口号日志功能，添加进程号保存至newframe.pid功能
  - 2、修改MyExclusionFilter的名字为AutoConfigurationExclustionFilter
  - 3、优化@EnableFlowable开关不生效的BUG
-----------------------------------------------

- 2.1.1-20220525-1

  - 1、数据源配置时添加类型配置(例如：spring.datasource.druid.mysql2.type=mysql)
  - 2、ScannerUtils扫描类工具中添加内部类的扫描
  - 3、修改几个事务使用不当的BUG
  - 4、Spring Boot版本升级至2.7.0
  - 5、遵循spring boot 2.7.x的改动,将@Configuration修改为@AutoConfiguration,自动配置类从spring.factories移动到AutoConfiguration.imports文件中
  - 6、优化OkHttp工具类代码
  - 7、JsonUtils工具添加时区和时间格式化配置
  - 8、Jsonutils工具通过ThreadLocal实现自定义ObjectMapper
  - 9、fastjson版本升级至1.2.83,解决1.2.80以下版本的反序列化漏洞
  - 10、优化TableResponse的泛型并修改使用处的代码
  
-----------------------------------------------

- 2.1.0-20220426-2

  - 1、springboot升级至2.6.7
  
-----------------------------------------------
- 2.1.0-20220426-1

  - 1、优化登录和权限验证用到的缓存
  - 2、SpringBoot版本升级至2.6.6
  - 3、添加接口签名验证工具类
  - 4、暂时关闭common-docker-tools、common-etcd-tools、common-jgit-tools、common-minio-tools
    、biz-neo4j、common-lua-tools、common-zeromq-tools、log-expansion模块，如果还想使用在setting.xml中打开对应模块即可
  - 5、删除workflow、webflow-app半成品模块
  - 6、添加flowable工作流(目前不能与Atomikos共用)
  - 7、规范了autoconfig配置类命名并在spring.factories中作配置
  - 8、修改了java版本配置方式(1.11改为11)
  - 9、跨域origin配置改为originPattern
  - 10、修改Mybatis-Plus分页插件的问题
  - 11、xxtable插件中插入数据自动返回主键ID
  - 12、修复druid配置的若干BUG
  - 13、添加quartz定时任务引擎
  - 14、添加LocalDateTimeUtils工作
  - 15、mybatis-plus中添加动态表名切换功能
  - 16、优化多个模块的源码

-----------------------------------------------
- 2.0.2-20211206-1

    - 1、反射工具类中添加函数句柄
    - 2、更新etcd-tools模块功能
    - 3、更新docker-tools模块功能
    - 4、harbor-tools丰富工具函数
    - 5、添加common-jgit-tools模块功能
    - 6、添加phantomjs生成echarts图表功能
    - 7、添加文本比对功能
    - 8、添加通过配置文件和注解跳过权限认证的功能
    - 9、修改xxtable下拉菜单类型的BUG
    - 10、springboot升级至2.6.1
    - 11、修改xxtable的sql调用方式，动态适配多种数据库，当前支持mysql、orcale、神通通用
    - 12、添加了一些请求参数校验
    - 13、更新了k8s-tools模块功能
    - 14、修改了已知的一些BUG
    - 15、整体优化了代码
-----------------------------------------------
- 2.0.2-20211213-1
    - 1、添加了一些请求参数校验
    - 2、修复了K8s-tools中的BUG
    - 3、修复了代码中存在的一些安全漏洞
    - 4、升级log4j2版本至2.15.0

-----------------------------------------------
- 2.0.2-20211221-1
  - 1、log4j2升级至2.17.0
  - 2、修复了一部分能改的安全漏洞
  - 3、AES加密模式由ECD改为CBC
  - 4、配置文件内数据库密码加密
  - 5、默认gradle版本修改为7.3.2

-----------------------------------------------
- 2.0.2-20211221-2
  - 1、优化mybatis配置

-----------------------------------------------
- 2.0.2-20211231-1
  - 1、添加OpenAuth客户端
  - 2、在@ComponentScan注解中排除了测试包的扫描
  - 3、移动了DynamicMapper类的位置，删除了DynamicMapper中的弃用方法
  - 4、DynamicMapper中修改了insert、delete、select等直接运行SQL的方法，为了避免冲突，添加了BySql后缀
  - 5、扩展了Mybatis-Plus的通用方法，添加了fetchByStream和truncate方法，可在Mapper层继承DynamicMapper使用(替换继承BaseMapper)
  - 6、修改配置文件内mapper.xml路径配置多个只有一个生效的BUG
  - 7、添加执行初始化schema脚本的功能，在数据源处spring.datasource.druid.xxx.schema配置脚本和开关
  - 8、修复了一些配置文件内Mybatis-Plus的配置项不生效的BUG，Mybatis-Plus配置名称做了统一
  - 9、JWT生成Token方式添加了rsa的实现(为了以后兼容Istio网关)，通过配置文件内的配置选项切换生成方式
  - 10、升级了Mybatis-Plus等一些第三方依赖的版本
  - 11、添加了RedisJson操作依赖和测试
  - 12、将权限相关的Mapper.XML实现改为了Mybatis-Plus方式
  - 13、添加数据配置Validation等配置，防止mysql8小时断开连接
  - 14、修改数据源默认切面表达式支持切到配置的子包
  - 15、去除了CustomCorsFilter中无用的配置
  - 16、修改的数据源切面，使第一个数据源的包也注册到aop中

-----------------------------------------------
- 2.0.2-20220126-1
  - 1、添加Mybatis-Plus的MetaObjectHandler
  - 2、添加p6spy，在使用atomikos时会有冲突，在不使用atomikos时可以使用，默认不启用
  - 3、修复了StatFilter和数据库相关的一些布尔值配置不生效的BUG
  - 4、修改了CaffineUtils默认容量到10000
  - 5、添加了jredisearch依赖并添加了其全文检索的测试
  - 6、修改了一些gradle参数，替换了一些过时的gradle配置
  - 7、修改登陆时密码错误提示不准确的BUG
  - 8、调整sqlSessionFactoryCustomizers扩展接口的调用位置，保证扩展配置不被覆盖
  - 9、添加大文件断点续传功能(需前端配置，resources下有前端demo代码)
  - 10、添加prometheus监控依赖
  - 11、升级oshi-core版本至5.8.7，兼容windows11
  - 12、修改构建docker并推送至镜像私服的配置
  - 13、springboot版本升级至2.6.3
  - 14、修复了其他已知的BUG
