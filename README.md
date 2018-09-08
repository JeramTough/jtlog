# JtLog

---

&emsp;&emsp;日志框架，同一套api适用于JavaWeb项目以及Android项目.  
&emsp;&emsp;A logging library for javaweb project or android project. 

---
### ==环境要求==
JDK1.8及以上

---


### ==添加依赖==
jar包位置 : [jtlog/build/libs/jtlog-*.jar](https://github.com/JeramTough/jtlog/tree/master/build/libs)

---
### ==简单使用==
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
*效果：*

```
D:{time}=20:09:08.882 , {thread}=main , {context}=TestMain , {location}=TestMain.<init>().18 , {caller}=(TestMain.java:18)
131313

I:{time}=20:09:08.903 , {thread}=main , {context}=TestMain , {tag}=tag
false
```
---

--


### ==配置==

##### 配置属性：

属性名 | 描述 | 默认值
---|---|---
contextName | 日志环境名，{context}=的标识，日志框架根据环境名区分不同的日志环境 |传入Class对象的类名或者实现WithJtLogger接口的类名
isEnabled | 是否允许输出日志 |true
maxLengthOfRow | 日志框架输出内容时，每一行的最大长度，超过这个长度就会换行 |130
minVisibleLevel | 最低可见日志等级，默认为DEBUG等级，日志等级优先级为：PRINTLN > ERROR > WARN > INFO > DEBUG > ARRIVE > VERBOSE |DEBUG
isUsedJtloggerApi | 是否使用JtLogger框架的Api进行日志输出，false的话会自适应使用Logback或者Log4j2的Api进行输出 |true



##### 1. 注释配置

```
@JtLoggerConfig(isUsedJtloggerApi = false, isEnabled = false,
        maxLengthOfRow = 0,contextName = "MyLogger",
        minVisibleLevel = LogLevel.VERBOSE)
public class TestMain implements WithJtLogger {
    public static void main(String[] args) {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);
        jtLogger.info("information");
        new TestMain();
    }
}
```
##### 2. 编程式配置

```
public class TestMain {
    public static void main(String[] args) {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);
        jtLogger.getLogContext().getLogConfig().setMaxLengthOfRow(0);
        jtLogger.getLogContext().getLogConfig().setEnabled(false);
        jtLogger.info("information");
    }
```

--
### ==过滤器==
- 使用标签过滤器,过滤掉指定tag的日志输出

```
//过滤掉标签标记为“aaa”的日志
TagLogFilter tagLogFilter = new TagLogFilter("aaa");
jtLogger.getLogContext().getLogConfig().addLogFilter(tagLogFilter);

jtLogger.info("aaa","这句日志信息将会被过滤掉");
```
- 自定义过滤器

```
//实现LogFilter接口
public class CustomLogFilter implements LogFilter {
    @Override
    public boolean isPrinted(LogInformation logInformation) {
        if (logInformation.getLogLevel() == LogLevel.DEBUG) {
            return false;
        }
        return true;
    }
}

//使用
jtLogger.getLogContext().getLogConfig().addLogFilter(customLogFilter);
```

---
--
### ==日志持久化==
当需要把日志信息做持久化处理，比如写入到某文件或者存入数据库时，可以使用**Recoder**接口，框架里有一个Recoder的实现类**FileRecoder**，用于日志信息文件保存方法。
````
/**
 * 
 * 创建RecorderHandler的实现类，并重写handleRecorders()方法，返回一个Recorder接口的数组
 * ，数据里的Recorder接口实现类就是你的日志信息保存逻辑实现类
 */
public class MyRecordHandler implements RecorderHandler {
    @Override
    public Recorder[] handleRecorders() {
        return new Recorder[]{new MyRecorder()};
    }
}


/**
 * 实现自己的持久化逻辑
 */
public class MyRecorder implements Recorder {
    @Override
    public void record(LogInformation logInformation, String stylizedText) throws IOException {
        //saved log to where you want.
        File file=new File("/someWhere/info.log");
        IOUtil.write(stylizedText.getBytes(),new FileOutputStream(file));
    }
}


/**
 * 使用注释配置RecorderHandler类
 */
@JtLoggerConfig(recorderHandleClass = MyRecordHandler.class,
isEnabled = true)
public class FileTst implements WithJtLogger {
    @Test
    public void test() {
        getJtLogger().info("saving log information in somewhere");
    }
}

```





