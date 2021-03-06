
# blog
个人博客
=======
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![GitHub Release](https://img.shields.io/github/release/lerry903/spring-boot-api-project-seed.svg)](https://github.com/lerry903/spring-boot-api-project-seed/releases)
[![SpringBoot](https://img.shields.io/badge/SpringBoot-2.1.2.RELEASE-brightgreen.svg)](https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/reference/htmlsingle/)
## 简介
Spring Boot API Project Seed 是一个基于Spring Boot & MyBatis的种子项目，用于快速构建中小型API、RESTful API项目，该种子项目已经有过多个真实项目的实践，稳定、简单、快速，使我们摆脱那些重复劳动，专注于业务代码的编写，减少加班。

## 源码托管
> **[Github](https://github.com/lerry903/spring-boot-api-project-seed)** | **[Gitee](https://gitee.com/lerry903/spring-boot-api-project-seed)**

## 特征&提供
- 最佳实践的项目结构、配置文件、精简的POM
- 统一响应结果封装
- 统一异常处理
- 统一日志打印
- 开源的Java工具包Hutool
- 简单的接口签名认证
- 常用基础方法抽象封装
- 使用Druid Spring Boot Starter 集成Druid数据库连接池与监控
- 使用FastJsonHttpMessageConverter，提高JSON序列化速度
- 集成MyBatis、通用Mapper插件、PageHelper分页插件，实现单表业务零SQL
- 提供代码生成器根据表名生成对应的Model、Mapper、MapperXML、Service、ServiceImpl、Controller等基础代码，其中Controller模板默认提供POST和RESTful两套，根据需求在```CodeGenerator.genController(tableName)```方法中自己选择，默认使用POST模板。代码模板可根据实际项目的需求来扩展，由于每个公司业务都不太一样，所以只提供了一些比较基础、通用的模板，**主要是提供一个思路**来减少重复代码的编写，我在实际项目的使用中，其实根据公司业务的抽象编写了大量的模板。另外，使用模板也有助于保持团队代码风格的统一
- 另有彩蛋，待你探索
 
 
## 开发技术
- JDK 1.8，Tomcat 8.5以上（强烈建议）
- 表名，建议使用小写，多个单词使用下划线拼接
- Model内成员变量建议与表字段数量对应，如需扩展成员变量（比如连表查询）建议创建DTO，否则需在扩展的成员变量上加```@Transient```注解，详情见[通用Mapper插件文档说明](https://mapperhelper.github.io/docs/2.use/)
- 建议业务失败使用```BusinessException```子类抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new DataNotFoundException(ResultCode.RESULE_DATA_NONE);```，会直接被封装为```{"code":50001,"message":"数据未找到"}```返回，无需自己处理，尽情抛出
- 需要工具类的话建议先从```Hutool```中找，实在没有再继承```Hutool```中相应的工具类造轮子或引入类库，尽量精简项目
- 开发规范建议遵循阿里巴巴Java开发手册（[最新版下载](https://github.com/alibaba/p3c))
 
## 技术选型&文档
- Spring Boot（[查看Spring Boot学习&使用指南](https://blog.csdn.net/lsy0903/article/category/6413992)）
- MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatisb通用Mapper插件（[查看官方中文文档](https://mapperhelper.github.io/docs/)）
- MyBatis PageHelper分页插件（[查看官方中文文档](https://pagehelper.github.io/)）
- Druid Spring Boot Starter（[查看官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/)）
- Fastjson（[查看官方中文文档](https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5)）
- Hutool工具包（[查看官方文档](https://hutool.cn/docs/)）
- 其他略

## 展示效果图
页面效果图
![image](https://user-images.githubusercontent.com/95741711/167565400-20482744-efa4-4214-8a23-788ec6dc5d4a.png)
![image](https://user-images.githubusercontent.com/95741711/167565548-0ad5b4cd-210d-47d7-a72d-0f492e9fff5c.png)
账号修改
![image](https://user-images.githubusercontent.com/95741711/167565711-11734afe-b81f-4ddd-9fec-c69e7b61abca.png)
博客评论
![image](https://user-images.githubusercontent.com/95741711/167565773-d6d4f9d4-f77f-4d0e-9496-86252841d041.png)
后台管理
![image](https://user-images.githubusercontent.com/95741711/167565903-b61c6fd5-a8cd-4053-b9ff-487cc5c02025.png)

## License

用户在遵循本项目协议的同时，如果用户下载、安装、使用本项目中所提供的软件，软件作者对任何原因在使用本项目中提供的软件时可能对用户自己或他人造成的任何形式的损失和伤害不承担任何责任。作者有权根据有关法律、法规的变化修改本项目协议。修改后的协议会随附于本项目的新版本中。当发生有关争议时，以最新的协议文本为准。如果用户不同意改动的内容，用户可以自行删除本项目。如果用户继续使用本项目，则视为您接受本协议的变动。


