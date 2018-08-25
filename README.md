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
1. 使用JtLogger接口

```
JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class); 
jtLogger.arrive();
jtLogger.info("11111");
jtLogger.warn("22222");
jtLogger.debug("tag", "3333");
jtLogger.debug("44444");
jtLogger.error("tag", "55555");
jtLogger.verbose("tag", "66666");
jtLogger.p("77777");

```
*效果：*

```
I:{time}=19:30:31.390 , {thread}=main , {context}=TestMain
11111

W:{time}=19:30:31.409 , {thread}=main , {context}=TestMain
22222

D:{time}=19:30:31.410 , {thread}=main , {context}=TestMain , {tag}=tag , {location}=TestMain.test().31 , {caller}=(TestMain.java:31)
3333

D:{time}=19:30:31.410 , {thread}=main , {context}=TestMain , {location}=TestMain.test().32 , {caller}=(TestMain.java:32)
44444

E:{time}=19:30:31.411 , {thread}=main , {context}=TestMain , {tag}=tag
55555

V:{time}=19:30:31.412 , {thread}=main , {context}=TestMain , {tag}=tag
66666

Arrive:{time}=19:30:31.412 , {thread}=main , {context}=TestMain , {location}=TestMain.test().35 , {caller}=(TestMain.java:35)
77777
```


2. 使用全局日志工具类 

```

```

3. 实现WithJtLogger接口 

```

```


---


