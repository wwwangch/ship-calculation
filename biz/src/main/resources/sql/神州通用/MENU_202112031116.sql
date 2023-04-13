INSERT INTO SYSDBA.MENU (MENU_ID,MENU_PID,MENU_PAGE,MENU_CREATE_TIME,MENU_UPDATE_TIME,MENU_NAME) VALUES 
(27,NULL,'/pages/index','2021-11-29','2021-03-11','首页')
,(28,NULL,'/pages/systemmanage','2021-11-29','2021-03-11','系统管理')
,(29,NULL,'/pages/systemmanagegateway','2021-03-10','2021-03-10','原系统管理')
,(30,28,'/pages/systemmanage/usermanage','2021-11-29','2021-03-11','用户管理')
,(31,28,'/pages/systemmanage/organizationmanage','2021-11-29','2021-03-11','组织机构管理')
,(32,28,'/pages/systemmanage/menumanage','2021-11-29','2021-03-11','菜单管理')
,(33,28,'/pages/systemmanage/rolemanage','2021-11-29','2021-03-11','角色管理')
,(34,28,'/pages/systemmanage/paramset','2021-11-29','2021-03-11','参数设置')
,(35,28,'/pages/systemmanage/dictionarymanage','2021-11-29','2021-03-11','字典管理')
,(36,28,'/pages/systemmanage/systemMonitor','2021-11-29','2021-03-11','系统监控')
;
INSERT INTO SYSDBA.MENU (MENU_ID,MENU_PID,MENU_PAGE,MENU_CREATE_TIME,MENU_UPDATE_TIME,MENU_NAME) VALUES 
(37,36,'/pages/systemmanage/systemMonitor/physicalsource','2021-11-29','2021-03-11','物理资源监控')
,(38,36,'/pages/systemmanage/systemMonitor/jvmmonitor','2021-03-10','2021-03-10','虚拟机监控')
,(39,36,'/pages/systemmanage/systemMonitor/portDocument','2021-11-29','2021-03-11','接口文档')
,(40,36,'/pages/systemmanage/systemMonitor/DataMonitoring','2021-03-10','2021-03-10','数据监控')
,(41,36,'/pages/systemmanage/systemMonitor/systemLog','2021-03-10','2021-03-10','系统日志')
,(42,36,'/pages/systemmanage/systemMonitor/logManagement','2021-03-10','2021-03-10','访问日志')
,(43,36,'/pages/systemmanage/systemMonitor/WSController','2021-03-10','2021-03-10','消息管理')
,(44,29,'/pages/systemmanagegateway/usermanagegateway','2021-11-29','2021-03-10','用户管理-网关')
,(45,29,'/pages/systemmanagegateway/serviceconfiggateway','2021-03-10','2021-03-10','服务配置-网关')
,(46,29,'/pages/systemmanagegateway/menumanagegateway','2021-03-10','2021-03-10','菜单管理-网关')
;
INSERT INTO SYSDBA.MENU (MENU_ID,MENU_PID,MENU_PAGE,MENU_CREATE_TIME,MENU_UPDATE_TIME,MENU_NAME) VALUES 
(47,29,'/pages/systemmanagegateway/rolemanagegateway','2021-03-10','2021-03-10','角色管理-网关')
,(48,28,'/pages/systemmanage/monitoring/1','2021-11-29','2021-03-12','纤维生产企业监测')
;