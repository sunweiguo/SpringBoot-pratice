# SpringBoot-pratice 极速入门SpringBoot一个小小实践
:watermelon::watermelon::watermelon:基于SpringBoot的一个小课题任务，麻雀虽小，五脏俱全，利用聚合工程的方式，将课题每一步实现的功能作为一个版本逐渐迭代，形成最后的课题终极版

Spring的官网：https://spring.io/projects

### 一、学习SpringBoot框架

![image](http://bloghello.oursnail.cn/shixi1-1.png)

- [x] 在网页上显示最简单的`Hello SpringBoot!`前置准备包括
    - [x]  `IDEA`的下载和基本使用（墙裂推荐学习如何使用）
    - [x]  `JDK8`及以上版本的安装
    - [x]  `Maven`了解、安装和构建项目
    - [x]  `Mysql`数据库的安装、了解基本的增删改查操作
    - [x]  `Navicate`数据库可视化软件的使用
    - [x]  了解web开发中的三层架构模式（控制层、逻辑层、持久层）

- [x]  后置就是`SpringBoot`下载启动，如何引入`SpringMVC`的。了解项目的基本结构，每一层目录的作用，以及后续代码如何分层等。
- [x]  学习`SpringMVC`的参数绑定，即url中传递来的参数是如何接受的
- [x]  要对`spring`以及`springmvc`的常用注解要熟悉，包括他们的作用、用法等
- [x]  提醒：要了解`springboot`，最好了解一下`spring`，特别是其核心的`IOC`和`AOP`，因为前者基于后者。

### 二、持久层框架学习

- [x]  在上面的基础上学习整合`mybatis`或者`spring data jpa`，使用其中一个或者两个写几个接口做增删改查最简单的`demo`（学会用`Postman`这样的工具或者写单元测试）
- [x]  注意事务的概念，这很重要

### 三、前端渲染引擎的学习

- [x]  学习一个前端渲染引擎基本语法（可以是`thymeleaf`、`freemarker`等），与`SpringBoot`整合，可以在前端渲染一些数据，比如基本的单个变量显示、列表之类

### 四、UI框架的简单学习

- [x]  学习一个基本的UI框架，比如`bootstrap`框架

### 五、KAKFKA的基本了解和使用

- [x]  了解`kafka`的基本作用、基本概念、基本命令
- [x]  尝试在本地搭建一个`kafka`，用客户端生产消费消息
- [x]  了解`zookeeper`是什么、为什么玩`kafka`要先启动`zookeeper`
- [x]  ...

### 六、用代码来实现第五步

- [ ]  可以先从简单的来，现在非web项目中起一个`main`方法来充当生产者或消费者
- [ ]  `SpringBoot`整合`kafka`的`demo`，用程序来生产和消费消息

### 七、课题的任务

- 在上面的基础上，实现一个简单的功能模块：
- [ ]  第一个页面
	- [ ]  页面上可以输入`kafka`的地址和端口，并且可以输入发送的内容，点击发送按钮可以往`kafka`发送消息，发送成功的话提示成功
	- [ ]  可以监听`kafka`的回调消息，如果本次发送消息有回执的话，就以`json`格式化的形式优美地渲染在页面上
	- [ ]  点击清空按钮可以清空文本框中的内容
- [ ]  第二个页面
    - [ ]  展示所有发送数据列表（分页）
- [ ]  第三个页面
    - [ ]  展示所有接受数据列表（分页）

发送的数据格式为：

```json
{
    "work_id":"990000010015622261926000026",
    "correlate_id":"990000010015622261926000026",
    "data_md5":"4C3019EF11503BFA6A6B9E4AE56454A1",
    "pub_time":"20190704154312",
    "system_id":"99000001",
    "work_type":0，
    "area_no":"00",
    "data":{
        "attrs":{
            "serviceGroupName":"eee"
        },
        "data_type":0,
        "database":"bmsdb",
        "extra":{
            "sql":"UPDATE I_SERVICEGROUP SET serviceGroupName = 'eee' WHERE serviceGroupID = 'SGaj0036'"
        },
        "primaries":{
            "serviceGroupID":"SGaj0036"
        },
        "table":"I_SERVICEGROUP"
    }
}
```

接受的数据格式为：

```json
{
    "work_id":"123456789",
    "correlate_id":"990000010015622261926000026",
    "system_id":"99123456",
    "area_no":"320000",
    "work_type":0,
    "topic":"pub_business_worksheet_01",
    "pub_time":"20190527105700",
    "data":{
        "result":0,
        "result_description":"数据处理成功啦"
    },
    "data_md5":"f40ca72a15e05cc2"
}
```

如何判断是该发送消息的相应消息--`correlate_id`两者一致就属于一个回合内的数据。

`work_id`或者`correlate_id`可以参考：

```java
public static String getWorkId(String systemid,String areano){
    int inc = atomicInteger.getAndIncrement()%10000;
    String incStr = String.format("%04d",inc);
    return systemid + areano + System.currentTimeMillis() + incStr;
}

/**
 * 生成指定长度的纯数字字符串
 * @param length
 * @return
 */
public static String getLengthNumberString(int length){
    // 指定位数字数组
    int[] number = new int[length];
    // 循环变量
    int i = 0;
    //最后返回的数字
    String endNumber = "";
    // 生成指定位随机数算法
    for (i = 0; i < length; i++) {
        if (number[i] == 0) {
            // 产生0-10之间的随机小数，强制转换成正数
            number[i] = (int) (Math.random() * 10);
        }
        // 输出数字
        endNumber += number[i]+"";
    }

    return endNumber;
}
```

### 八、额外说明

- 原则是基于`SpringBoot`（必须用），其他的可以选择自己喜欢或者觉得简单的来实现。总之实现7就是目标。
- 学会用`markdown`来记载笔记，实习结束收获满满。
- 可以考虑尝试用`github`来管理我们的代码


### 九、完成进度

在进行的过程中进行了相应的kafka学习，笔记地址：https://sunweiguo.github.io/tags/kafka/

- demo01：完成聚合工程的创建（可参考我的[博客](https://sunweiguo.github.io/2019/04/17/mama-action/01-%E8%81%9A%E5%90%88%E5%B7%A5%E7%A8%8B%E5%88%9B%E5%BB%BA%E5%92%8C%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83/)）、在页面如何显示最简单的hello world、简单演示restful接口如何接受参数，详见`TestController`、通过查看配置文件，可以看到如何配置端口(server.port)、如何配置项目前缀(server.servlet.context-path: /demo01)、如何配置datasource（还没用到）、如何在配置文件中指定生效的配置文件,引入logbakc的配置文件，不懂如何配可以看我[博客](https://sunweiguo.github.io/2019/01/28/miscellany/11SpringBoot%E4%BD%BF%E7%94%A8logback%E5%AE%9E%E7%8E%B0%E6%97%A5%E5%BF%97%E6%8C%89%E5%A4%A9%E6%BB%9A%E5%8A%A8/)...最终要看到hello world的显示，先启动主类，然后访问：http://localhost:8888/demo01/test/index 即可
- demo02：完成对mysql数据库的增删改查、controller-service-dao三层包各自应该写的代码、对事务生效的测试、引入lombok、增删改查示例
- demo03：整合渲染引擎freemarker以及UI框架bootstrap，完成一个基本的分页列表的展示，以及首页和发送工单的页面，并且为了方便调试，我在云服务器上搭了kafka单机，并且配上了相应的搭建笔记
- demo04：结合页面完成代码版本的kafka生产和消费
