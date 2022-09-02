# 烧结台车项目

开发时间线
=========

2022-07-25 -- 2022-08-03
==============
    已完成:
        1.开发设备组管理模块（增删改查，绑定，解绑）
        2.开发设备管理模块（增删改查,绑定，解绑）
        3.迁移设备管理插件(多类型设备映射转移关系)
        4.开发海康摄像机调用模块（初始化，设备登录,设备注销）
    未完成:
        1.设备管理基础业务逻辑
        2.海康设备的摄像功能调用

2022-07-19 -- 2022-07-25
===============
    计划:
        已完成:
        1.搭建基础开发框架 （spring boot + shiro + mybatis）
        2.搭建 hadoop + hbase + phoenix 单机分布环境 
        3.集成开发基础框架（多数据源 Mysql + phoenix）
        4.调测集成多数据源后，开发环境是否正常，基础业务功能是否正常
        5.设计基础业务模块pdm原型
        未完成:
        1.迁移hbase环境到免费的地方
        2.测试hbase如何存储图片内容
        3.设计hbase如何存储批次作业内容
        4.开发设备管理 -- 基础设备信息管理模块


2022-07-19
===============

    计划: 
        1.搭建开发基础框架
        2.完成用户管理模块基本 增删改查 功能
        3.迁移字典管理模块


基本业务功能
==========

    1.用户管理
    2.系统维表管理
    3.操作日志管理
    4.设备管理（多类型设备）
    5.设备组管理
    6.作业统计分析  车轮作业结果分析聚合等

代码结构
=====

    core - 核心模型包 用于提高系统的整体抽象
    device - 设备模型包 用于各类设备不同的集成方式（列入tcp直连(协议自解析)，mqtt连接，第三方云平台消息下发(json格式)等）
    server(单体启动包) - 主程序内核包 主要提供基础业务功能
    starter - SPI 插件包，一般做内核包和多类型关系模型的解耦，像device-spring-boot-starter 即是用来集成多类型设备管理  
                也可用来提取业务框架（未实现）


基础开发框架
=====

    spring boot 2.6.4 生态 (包含spring-fox,aop等)
    mybatis 5.7
    hbase 2.2.*
    hadoop 2.8.*
    shiro 安全登录框架


整体设计亮点
====
    系统的整体结构是采用了单体多模块化的设计思想，主要是静态链接库方式（Springboot 提供的SPI 方式），动态链接的方式可以采用OSGI框架进行集成，这边不提供


