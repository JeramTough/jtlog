# JtLog

---

&emsp;&emsp;日志框架，同一套api适用于JavaWeb项目以及Android项目.  
&emsp;&emsp;A logging library for javaweb project or android project. 

---
### ==一、环境要求==
JDK1.8及以上

---


### ==二、添加依赖==
- jar包位置 : [target/jtlog-*.jar](https://github.com/JeramTough/jtlog/tree/master/target)  


- Mave 

```
<dependency>
    <groupId>com.jeramtough</groupId>
    <artifactId>jtlog</artifactId>
    <version>x.x.x</version>
</dependency>

```
- Gradle 

```
compile group: 'com.jeramtough', name: 'jtlog', version: 'x.x.x'

```




---
### ==三、简单使用==
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


### ==四、配置==

##### 注释型配置的属性：

属性名 | 描述 | 默认值
---|---|---
contextName | 日志环境名，{context}=的标识，日志框架根据环境名区分不同的日志环境 |传入Class对象的类名或者实现WithJtLogger接口的类名
isEnabled | 是否允许输出日志 |true
maxLengthOfRow | 日志框架输出内容时，每一行的最大长度，超过这个长度就会换行 |0
minVisibleLevel | 最低可见日志等级，默认为DEBUG等级，日志等级优先级为：PRINTLN > ERROR > WARN > INFO > DEBUG > ARRIVE > VERBOSE |DEBUG
isUsedJtloggerApi | 是否使用JtLogger框架的Api进行日志输出，false的话会自适应使用Logback或者Log4j2的Api进行输出 |true
componentHandleClass | 设置日志系统附加组件的把持类，可以添加一些组件，比如自定义日志过滤器，自定义日志记录器 |DefaultComponentHandler.class



##### 1. 使用注释声明式配置

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
##### 2. 使用编程式配置

```
public class TestMain {
    public static void main(String[] args) {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);
        jtLogger.getLogContext().getLogConfig().setMaxLengthOfRow(0);
        jtLogger.getLogContext().getLogConfig().setEnabled(false);
        jtLogger.info("information");
    }
```


##### 3. 覆盖框架默认配置
在第一次使用Jtlog框架前调用才能生效，推荐在项目初始化代码块里调用，++注意，当使用声明式注释配置时，以声明式注释配置为准++。

 LogConfig.setLogConfigDefaultValues(new **LogConfigDefaultValues**(){...});  
 或者  
LogConfig.setLogConfigDefaultValues(new **SimpleLogConfigDefaultValues**() {...});
```
public class TestMain {
    public static void main(String[] args) {
         LogConfig.setLogConfigDefaultValues(new LogConfigDefaultValues() {
            @Override
            public int loadMaxLengthOfRow() {
                return 0;
            }

            @Override
            public boolean loadIsEnabled() {
                return true;
            }

            @Override
            public boolean loadIsUsedJtloggerApi() {
                return true;
            }

            @Override
            public LogLevel loadMinVisibleLevel() {
                return LogLevel.DEBUG;
            }

            @Override
            public int loadCallerPlus() {
                return 0;
            }
        });
    }
```
---

--
### ==五、过滤器==


**- 实现日志过滤器接口**   

实现LogFilter接口,实现自己的过滤逻辑。  
TageLogFilter为框架已实现的一个标签(tag)过滤功能。

```
//实现LogFilter接口,实现自己的过滤逻辑,返回false则过滤该条日志.
public class CustomLogFilter implements LogFilter {
    @Override
    public boolean isPrinted(LogInformation logInformation) {
        if (logInformation.getLogLevel() == LogLevel.DEBUG) {
            return false;
        }
        return true;
    }
}

```
**- 使用日志过滤器**
1. 使用编程式方法去使用

```
//使用LogConfig方法
jtLogger.getLogContext().getLogConfig().addLogFilter(customLogFilter);
```
2. 使用注释的方法去使用

```
//第一步，实现ComponentHandler接口或者集成DefaultComponentHandler类
public class MyComponentHandler extends DefaultComponentHandler {

    @Override
    public void handleLogFilters(ArrayList<LogFilter> logFilters) {
        //往logFilters集合里添加日志过滤器对象
        TagLogFilter tagLogFilter = new TagLogFilter("bbb");
        logFilters.add(tagLogFilter);
    }

}

-------------------------------------------
//第二步，配置注释
@JtLoggerConfig(componentHandleClass = MyComponentHandler.class,
        contextName = "ComponentHandlerTest")
public class TestMain {
    public static void main(String[] args) {
         JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);
         jtLogger.info("bbb", "因为添加了标签过滤器，这句日志信息将会被过滤掉");
    }
}

```


---
--
### ==六、日志持久化==
当需要把日志信息做持久化处理，比如写入到某文件或者存入数据库时，需要做持久化处理。

**- 实现LogRecoder接口**   
框架里有一个Recoder的实现类**FileRecoder**，用于日志信息文件保存方法。

````
/**
 * 实现自己的持久化逻辑
 */
public class MyLogRecorder implements LogRecorder {
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

````

**- 使用日志记录器**
1. 使用编程式方法去使用

```
//使用LogConfig方法
jtLogger.getLogContext().getLogConfig().addLogRecorder(new MyLogRecorder());
```
2. 使用注释的方法去使用

```
//第一步，实现ComponentHandler接口或者集成DefaultComponentHandler类
public class MyComponentHandler extends DefaultComponentHandler {

    @Override
    public void handleLogRecorders(ArrayList<LogRecorder> logRecorders) {
        File file = new File("E:\\Codes\\IdeaCodes\\JtlogForMaven\\jtlog.log");
        FileLogRecorder fileLogRecorder = new FileLogRecorder(file, 5);
        logRecorders.add(fileLogRecorder);
    }

}

-------------------------------------------
//第二步，配置注释
@JtLoggerConfig(componentHandleClass = MyComponentHandler.class,
        contextName = "ComponentHandlerTest")
public class TestMain {
    public static void main(String[] args) {
         JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);
         jtLogger.info("aaa", "因为使用了FileLogRecorder，这句日志将会被持久化到文件中");
    }
}

```







