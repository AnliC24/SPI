/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : sjtc

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 01/08/2022 10:02:49
*/

CREATE DATABASE IF NOT EXISTS sjtc CHARACTER SET utf8 COLLATE utf8_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_device_info
-- ----------------------------
DROP TABLE IF EXISTS `base_device_info`;
CREATE TABLE `base_device_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `device_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `login_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录状态',
  `network_connect` double(10, 0) NULL DEFAULT NULL COMMENT '当前设备与服务器的网络连接延迟',
  `opt_process` int(10) NULL DEFAULT NULL COMMENT '当前设备所处工序-必填',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_device_info
-- ----------------------------
INSERT INTO `base_device_info` VALUES (1, '123', 'hk_camera_1#', '08', '01', 23, 1, '2022-07-26 14:53:45', '2022-07-26 14:53:48');

-- ----------------------------
-- Table structure for base_warn_info
-- ----------------------------
DROP TABLE IF EXISTS `base_warn_info`;
CREATE TABLE `base_warn_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `warn_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `warn_level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警级别',
  `warn_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警状态',
  `warn_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警类型',
  `warn_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '告警内容',
  `warn_log` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '告警日志',
  `send_count` int(11) NULL DEFAULT NULL COMMENT '推送条数',
  `success_send_count` int(11) NULL DEFAULT NULL COMMENT '成功推送条数',
  `warn_time` datetime(0) NULL DEFAULT NULL COMMENT '告警日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for detect_config_info
-- ----------------------------
DROP TABLE IF EXISTS `detect_config_info`;
CREATE TABLE `detect_config_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `detect_config_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测配置编号',
  `detect_config_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测配置名称',
  `detect_check_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测属性',
  `detect_check_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测值',
  `detect_check_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device_detect_map
-- ----------------------------
DROP TABLE IF EXISTS `device_detect_map`;
CREATE TABLE `device_detect_map`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `detect_config_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测配置编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device_group_info
-- ----------------------------
DROP TABLE IF EXISTS `device_group_info`;
CREATE TABLE `device_group_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_group_id` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备组编号',
  `device_group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备组名称',
  `device_group_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备组描述',
  `group_address` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_group_info
-- ----------------------------
INSERT INTO `device_group_info` VALUES (1, '7bb39f94648745df857ea8c1c6b9866a', 'left-visual-1#', '左侧视觉1号组,用于检测台车编号', '01', '2022-07-27 09:38:10', '2022-07-27 09:38:13');
INSERT INTO `device_group_info` VALUES (2, '1234', 'right-visual-1#', '右侧视觉1号组,用于检测台车编号', '02', '2022-07-27 09:38:10', '2022-07-27 09:38:13');

-- ----------------------------
-- Table structure for device_group_map
-- ----------------------------
DROP TABLE IF EXISTS `device_group_map`;
CREATE TABLE `device_group_map`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_group_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备组编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grease_nipple_info
-- ----------------------------
DROP TABLE IF EXISTS `grease_nipple_info`;
CREATE TABLE `grease_nipple_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hk_camera_info
-- ----------------------------
DROP TABLE IF EXISTS `hk_camera_info`;
CREATE TABLE `hk_camera_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opt_record_info
-- ----------------------------
DROP TABLE IF EXISTS `opt_record_info`;
CREATE TABLE `opt_record_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `opt_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求IP',
  `user_id` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `role_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型',
  `user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `opt_harm_level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求危害级别',
  `opt_url` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `is_success` smallint(4) NULL DEFAULT NULL COMMENT '请求是否成功',
  `opt_result` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求结果',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of opt_record_info
-- ----------------------------
INSERT INTO `opt_record_info` VALUES (3, '127.0.0.1', '37e778b276064690b09596434e73a0fb', '01', 'admin', NULL, '/security/manage/login', 1, '用户: admin 登录成功', '2022-07-28 11:11:43');
INSERT INTO `opt_record_info` VALUES (6, '127.0.0.1', '37e778b276064690b09596434e73a0fb', '01', 'admin', NULL, '/security/manage/login', 1, '用户: admin 登录成功', '2022-07-28 11:52:39');
INSERT INTO `opt_record_info` VALUES (7, '127.0.0.1', '37e778b276064690b09596434e73a0fb', '01', 'admin', NULL, '/security/manage/login', 1, '用户: admin 登录成功', '2022-07-28 11:55:10');
INSERT INTO `opt_record_info` VALUES (8, '127.0.0.1', '37e778b276064690b09596434e73a0fb', '01', 'admin', NULL, '/security/manage/login', 1, '用户: admin 登录成功', '2022-07-28 12:29:11');
INSERT INTO `opt_record_info` VALUES (9, '127.0.0.1', '37e778b276064690b09596434e73a0fb', '01', 'admin', NULL, '/security/manage/login', 1, '用户: admin 登录成功', '2022-08-01 09:45:45');
INSERT INTO `opt_record_info` VALUES (10, '127.0.0.1', '37e778b276064690b09596434e73a0fb', '01', 'admin', NULL, '/device/manage/getDevicePage', 0, 'java.lang.NullPointerException:测试异常抛出是否能被记录\n	com.hitqz.sjtc.controller.DeviceController.getDevicePage(DeviceController.java:48)\ncom.hitqz.sjtc.controller.DeviceController$$FastClassBySpringCGLIB$$388e79b.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:783)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:753)\norg.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:753)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:753)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:698)\ncom.hitqz.sjtc.controller.DeviceController$$EnhancerBySpringCGLIB$$18cd8afc.getDevicePage(<generated>)\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\njava.lang.reflect.Method.invoke(Method.java:498)\norg.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\norg.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\norg.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\norg.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\norg.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1067)\norg.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)\norg.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\norg.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:655)\norg.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:764)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\norg.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\norg.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\norg.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\norg.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\norg.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\norg.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\norg.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\norg.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\norg.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:450)\norg.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365)\norg.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\norg.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\norg.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\norg.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\norg.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\norg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\norg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:540)\norg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\norg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\norg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\norg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:359)\norg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\norg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\norg.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:889)\norg.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1735)\norg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\norg.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\norg.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\norg.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\njava.lang.Thread.run(Thread.java:745)\n', '2022-08-01 09:46:01');
INSERT INTO `opt_record_info` VALUES (11, '127.0.0.1', '37e778b276064690b09596434e73a0fb', '01', 'admin', NULL, '/security/manage/login', 1, '用户: admin 登录成功', '2022-08-01 09:49:46');

-- ----------------------------
-- Table structure for sys_data_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dict`;
CREATE TABLE `sys_data_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `dict_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `dict_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '字典值',
  `dict_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `dict_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '字典描述',
  `dict_pid` int(11) NULL DEFAULT NULL COMMENT '上级字典编号',
  `dict_level` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典级别-系统级/客户级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_data_dict
-- ----------------------------
INSERT INTO `sys_data_dict` VALUES (1, '01', '超级管理员', 'role_type', '用户角色', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (2, '02', '作业人员', 'role_type', '用户角色', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (3, '08', '海康摄像机', 'device_type', '设备类型', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (4, '09', '注油装置', 'device_type', '设备类型', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (5, '10', '轮芯清理', 'device_type', '设备类型', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (6, '11', '涡电流感应', 'device_type', '设备类型', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (7, '1', '检测台车编号', 'opt_process_desc', '工序说明', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (8, '2', '检测车轮串动', 'opt_process_desc', '工序说明', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (9, '01', '在线', 'login_status', '登录状态', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (10, '02', '离线', 'login_status', '登录状态', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (11, '03', '故障', 'login_status', '登录状态', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (12, '01', '左侧', 'group_address', '组地址-台车左侧', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (13, '02', '右侧', 'group_address', '组地址-台车右侧', NULL, 'SYS');

-- ----------------------------
-- Table structure for turbine_inductance_info
-- ----------------------------
DROP TABLE IF EXISTS `turbine_inductance_info`;
CREATE TABLE `turbine_inductance_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_device_map
-- ----------------------------
DROP TABLE IF EXISTS `user_device_map`;
CREATE TABLE `user_device_map`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `user_status` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户状态',
  `role_type` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色状态',
  `user_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `user_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '37e778b276064690b09596434e73a0fb', 'admin', '123654', '02', '01', '111111@gmail.com', '11111111', '2022-07-18 15:08:43', '2022-07-19 09:08:51');
INSERT INTO `user_info` VALUES (8, 'e988838438864ecf8f33d9fd471c3b84', 'opt1', '123456', '02', '02', '123333333@gmail.com', '1234567891', '2022-07-19 09:55:52', '2022-07-19 09:55:52');

-- ----------------------------
-- Table structure for user_relate_map
-- ----------------------------
DROP TABLE IF EXISTS `user_relate_map`;
CREATE TABLE `user_relate_map`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `super_relate_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属上级用户编号',
  `super_relate_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属上级用户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_relate_map
-- ----------------------------
INSERT INTO `user_relate_map` VALUES (1, '37e778b276064690b09596434e73a0fb', 'admin', 'root', 'root');
INSERT INTO `user_relate_map` VALUES (6, 'e988838438864ecf8f33d9fd471c3b84', 'opt1', '37e778b276064690b09596434e73a0fb', 'admin');

-- ----------------------------
-- Table structure for wheel_clean_info
-- ----------------------------
DROP TABLE IF EXISTS `wheel_clean_info`;
CREATE TABLE `wheel_clean_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
