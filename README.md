# JtLog

---

&emsp;&emsp;日志框架，同一套api适用于JavaWeb项目以及Android项目.  
&emsp;&emsp;A logging library for javaweb project or android project. 

---
### 环境要求
JDK1.8及以上

---


### 添加依赖
jtlog/build/libs/jtlog-*.jar

---
### 简单使用
##### 1. 使用JtLogger接口

```
JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class); 
jtLogger.arrive();
jtLogger.info("11111");
jtLogger.warn("22222");
jtLogger.debug("tag", "3333");
jtLogger.debug("44444");
jtLogger.error("tag", "55555");
jtLogger.verbose("tag", "66666");
jtLogger.p("77777"); //不带任何格式输出

```
*效果：*

```
Arrive:{time}=19:36:35.332 , {thread}=main , {context}=TestMain , {location}=TestMain.test().29 , {caller}=(TestMain.java:29)
I:{time}=19:36:35.354 , {thread}=main , {context}=TestMain
11111

W:{time}=19:36:35.355 , {thread}=main , {context}=TestMain
22222

D:{time}=19:36:35.355 , {thread}=main , {context}=TestMain , {tag}=tag , {location}=TestMain.test().32 , {caller}=(TestMain.java:32)
3333

D:{time}=19:36:35.355 , {thread}=main , {context}=TestMain , {location}=TestMain.test().33 , {caller}=(TestMain.java:33)
44444

E:{time}=19:36:35.356 , {thread}=main , {context}=TestMain , {tag}=tag
55555

V:{time}=19:36:35.357 , {thread}=main , {context}=TestMain , {tag}=tag
66666

77777
```

——————————————————————
##### 2. 使用全局日志工具类 

```
L.debug(null);
L.info(88888.f);
L.verbose("99999");
L.debugs(null,111111,121212);`

```
*效果：*

```
D:{time}=19:58:16.479 , {thread}=main , {location}=TestMain.test1().40 , {caller}=(TestMain.java:40)
[null]

I:{time}=19:58:16.480 , {thread}=main
88888.0

V:{time}=19:58:16.480 , {thread}=main
99999

D:{time}=19:58:16.480 , {thread}=main , {location}=TestMain.test1().43 , {caller}=(TestMain.java:43)
[null] ，111111 ，121212
```
——————————————————————
##### 3. 实现WithJtLogger接口 

```
public class TestMain implements WithJtLogger {
    public static void main(String[] args) {
        new TestMain();
    }

    public TestMain() {
        getJtLogger().debug(131313);
        getJtLogger().info("tag",false);
    }
}
```
*效果*

```
D:{time}=20:09:08.882 , {thread}=main , {context}=TestMain , {location}=TestMain.<init>().18 , {caller}=(TestMain.java:18)
131313

I:{time}=20:09:08.903 , {thread}=main , {context}=TestMain , {tag}=tag
false
```


---


