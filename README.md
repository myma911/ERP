# erp
采用目前主流的快速开发框架springboot为基础


项目包括后端接口服务、移动端接口服务，网站前台、网站管理后台。
功能模块包括客户管理、工作流程、产品管理、方案设计、信息共享、公司管理、财务管理、车辆管理、行业知识管理、考试管理及第三方登陆等。
项目融合了Docker容器化部署、第三方登陆 、人工智能、爬虫、RabbitMQ等技术。（部分功能暂未实现）


# 技术架构
后端架构：SpringBoot+StringMVC+MybatisPlus 
前端架构：Node.js+Vue.js+ElementUI+NUXT 




# 项目结构
|         模块           |           名称         |
| ---------------------  | ---------------------- |
| `erp-api`             |         后台接口       |
| `erp-common-base`    | 基础通用公共工具类组件 |
| `erp-common-dao`     | 通用dao组件            |
| `erp-common-pojo`    | 通用实体对象           |
| `erp-common-service` | 通用服务类             |
| `erp-common-util`    | 通用公共工具类组件     |
| `mobile-api`          | 移动端接口             |



# 后端

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [Spring boot] | 应用开发框架 |   2.2.9.RELEASE |
| [POSTGRESQL] | 数据库服务器 | 9.x |
| [Mybatis-plus]| 数据持久层框架 |  |
| [Apache-shiro]| 权限管理框架 |  |
| [Redis](https://redis.io/) | key-value 数据库 |  |
| [RedisTemplate] | Redis 客户端 |  |
| [Elasticsearch](https://www.elastic.co/cn/) | 分布式搜索引擎 | 7.4.2 |
| [mongodb] | mongodb数据库 | |
| [logstash] | 日志搜索处理框架 | |
| [rabbitmq] | 消息中间件 | |




# 其他

## Token 登录流程
用户进行接口请求时，需要携带token，而此时进行接口请求时通常有以下几种状态：
未携带token传入：返回token为空，权限不足；
错误token传入：返回token错误，权限不足；
正常token传入：通过 ；
正常token但已过期：查询redis中该token是否存在，如果缓存中存在则刷新token，并将刷新后的token重新放入redis，使用刷新后的token请求资源； 缓存中不存在，则token刷新期限已过，用户需重新登录。


## Mock框架的集成
提前接入测试，提供测试效率，当接口定义好后，测试人员就可以创建Mock，把接口添加到自动化测试环境，提前开始测试，起到测试驱动开发效果；可以有效的增加覆盖，接口涉及入参，或者业务逻辑复杂的情况，某些场景无法通过正常手段进行操作，可以通过mock虚拟模拟；



## 实现接口幂等性校验，防止数据重复提交
通过redis + token机制实现接口幂等性校验，为需要保证幂等性的每一次请求创建一个唯一标识token, 先获取token, 并将此token存入redis, 请求接口时, 将此token放到header或者作为请求参数请求接口, 后端接口判断redis中是否存在此token:如果存在, 正常处理业务逻辑, 并从redis中删除此token, 那么, 如果是重复请求, 由于token已被删除, 则不能通过校验, 返回请勿重复操作提示，如果不存在, 说明参数不合法或者是重复请求, 返回提示即可。@ApiIdempotent注解 + 拦截器对请求进行拦截  @ControllerAdvice全局异常处理



## 密码加密与服务鉴权
使用 JWT + BCrypt 实现，用户数据更加安全可靠

## 集成极光短信
主要场景 用户注册；登录验证；关键信息修改；支付确认；人员身份有效性确认。


##
友盟推送


## 服务容器部署与持续集成
服务容器部署与版本控制使用Git+Gogs ,服务持续集成使用Gogs Docker私有仓库与Jenkins实现，服务发布更加高效快捷



## 容器管理与弹性扩容
容器管理，弹性扩容，Rancher实现容器部署管理， Grafana实时监控， 服务更加安全稳定


## lombok
消除模板代码：getter、setter、构造器、toString()、equals()便捷的生成比较复杂的代码，例如一个POJO要转化成构建器模式的形式，只需要一个注解



## Swagger 
一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。总体目标是使客户端和文件系统作为服务器以同样的速度来更新。作用：1.接口的文档在线自动生成；2.接口功能测试。



## 基于websocket实现实时通讯
管理员可向在线的用户发送实时消息



## 用户行为分析
解析用户终端信息，pc/移动，浏览器类型、版本等



## 集成redisson分布式锁
可以保证在分布式部署的应用集群中互斥性，即同一个方法在同一操作只能被一台机器上的一个线程执行。程序出现异常锁能自动释放，避免死锁，锁超时，使项目具有快速扩容的能力



## 多套环境配置方案
快速开发、测试、部署


## 接口流量控制
灵活配置限流方案，系统更加稳定。 服务接口的流量控制策略：分流、降级、限流等，保证服务接口和业务应用系统的高可用。



## 集成多套文件上传方式
包括支出本地文件上传，阿里云OSS、七牛云、MinIO


## XSS SQL注入
全站预防XSS注入，html标签注入，js脚本注入，及SQL注入


## 集成第三方登录
实现第三方登录，包括QQ登录、GitHub登录、微信登录、谷歌登录、微软登录、小米登录、企业微信登录等


## 接口数据加密
在项目中，为了保证数据的安全，对传递的数据进行加密。使用RSA加密方式对API接口返回的数据加密，让API数据更加安全。


## 数据接口日志访问记录
对接口名称，访问用户，入参、出参进行日志记录


# API前后端约定的返回码列表：

状态描述	    返回码
成功	        20000
失败	        20001
用户名密码错误	20002
权限不足	    20003
远程调用失败	20004
重复操作	    20005
验证码错误      20006
token不能为空   20007