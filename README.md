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
##### 1. 使用Logger接口

```
//Logger logger = LoggerManager.getLogger(TestMain.class);
Logger logger=LoggerManager.getLogger("MyLogger");
logger.arrive();
logger.info("11111");
logger.warn("22222");
logger.debug("tag", "3333");
logger.debug("44444");
logger.error("tag", "55555");
logger.verbose("tag", "66666");

//不带任何格式输出
logger.p("77777"); 

//使用占位符
logger.info("%d and %s",12,"字符型");

```
*效果：*

```
Arrive:{time}=13:12:34:618 .{context}=MyLogger .{thread}=main .{trace}=at test.TestMain.test7(TestMain.java:93) .

I:{time}=13:12:34:667 .{context}=MyLogger .{thread}=main .{trace}=at test.TestMain.test7(TestMain.java:94) .
11111

W:{time}=13:12:34:669 .{context}=MyLogger .{thread}=main .{trace}=at test.TestMain.test7(TestMain.java:95) .
22222

D:{time}=13:12:34:671 .{context}=MyLogger .{tag}=tag .{thread}=main .{trace}=at test.TestMain.test7(TestMain.java:96) .
3333

D:{time}=13:12:34:672 .{context}=MyLogger .{thread}=main .{trace}=at test.TestMain.test7(TestMain.java:97) .
44444

E:{time}=13:12:34:672 .{context}=MyLogger .{tag}=tag .{thread}=main .{trace}=at test.TestMain.test7(TestMain.java:98) .
55555

V:{time}=13:12:34:673 .{context}=MyLogger .{tag}=tag .{thread}=main .{trace}=at test.TestMain.test7(TestMain.java:99) .
66666

77777

I:{time}=15:43:24:206 .{context}=MyLogger .{thread}=main .{trace}=at test.TestMain.test(TestMain.java:43) .
12 and 字符型
```

——————————————————————
##### 2. 使用全局日志工具类 

```
L.debug(null, null);
L.error("tag","gggggggggg");
L.info(88888.f);
L.verbose("99999");
L.arrive();
L.debugs(null, 111111, 121212);
```
*效果：*

```
D:{time}=13:15:34:042 .{context}=L .{thread}=main .{trace}=at test.TestMain.test1(TestMain.java:39) .
[null]

E:{time}=13:15:34:072 .{context}=L .{tag}=tag .{thread}=main .{trace}=at test.TestMain.test1(TestMain.java:40) .
gggggggggg

I:{time}=13:15:34:073 .{context}=L .{thread}=main .{trace}=at test.TestMain.test1(TestMain.java:41) .
88888.0

V:{time}=13:15:34:074 .{context}=L .{thread}=main .{trace}=at test.TestMain.test1(TestMain.java:42) .
99999

Arrive:{time}=13:15:34:076 .{context}=L .{thread}=main .{trace}=at test.TestMain.test1(TestMain.java:43) .

D:{time}=13:15:34:077 .{context}=L .{thread}=main .{trace}=at test.TestMain.test1(TestMain.java:44) .
[null] , 111111 , 121212
```
——————————————————————
##### 3. 实现WithLogger接口

```
public class TestMain implements WithLogger {
    public static void main(String[] args) {
        new TestMain();
    }

    public TestMain() {
         //使用接口的默认方法getLogger()得到Logger
        getLogger().debug("with.www");
        getLogger().debugs("Strinds", 1, 12.1f, false);
    }
}
```
*效果：*

```
D:{time}=2019:02:13:18:53 .{context}=MyLogger .{thread}=main .{trace}=at test.TestMain.test2(TestMain.java:49) .
with.www

D:{time}=2019:02:13:18:53 .{context}=MyLogger .{thread}=main .{trace}=at test.TestMain.test2(TestMain.java:50) .
Strinds ，1 ，12.1 ，false
```
---

--


### ==四、配置==

##### 配置的属性：

属性名 | 描述 | 默认值
---|---|---
contextName | 日志环境名，{context}=的标识，日志框架根据环境名区分不同的日志环境 |"default"
isEnabled | 是否允许输出日志 |TRUE
maxLengthOfRow | 日志框架输出内容时，每一行的最大长度，超过这个长度就会换行,0表示不换行 |0
minVisibleLevel | 最低可见日志等级，默认为DEBUG等级，日志等级优先级为：PRINTLN > ERROR > WARN > INFO > DEBUG > ARRIVE > VERBOSE |DEBUG
isUsedJtloggerApi | 是否使用JtLogger框架的Api进行日志输出，false的话会自适应使用Logback或者Log4j2的Api进行输出 |TRUE
logHeaders | 决定要输出的日志信息头及顺序，有则输出，没有则不输出 |TIME,CONTEXT,TAG,HREAD,TRACE
dataFormat | 日志时间信息头的输出格式 |"HH:mm:ss:SSS"
wrapCount | 每条新日志之间的空行数, 0则两条日志间无空行 |1
logFilters | 添加额外的日志过滤器，过滤器类必须有个无参公共的构造函数，过滤器详细见{@link com.jeramtough.jtlog.filter.LogFilter} |无
logRecorders | 加额外的日志记录器，记录器类必须有个无参公共的构造函数，过滤器详细见{@link com.jeramtough.jtlog.recorder.LogRecorder} |无



##### 1. @注释声明式配置使用方法

```
@LogConfiguration(isUsedJtloggerApi = DefaultBoolean.TRUE, isEnabled = DefaultBoolean.TRUE,
        maxLengthOfRow = 130, contextName = "MyLogger",
        minVisibleLevel = LogLevel.VERBOSE, logFilters = {MyTagLogFilter.class},
        dataFormat = "YYYY:MM:HH:mm:ss")
public class TestMain implements WithJtLogger {

    public static void main(String[] args) {
        Logger Logger = LoggerManager.getLogger(TestMain.class);
        Logger.info("information");
    }
}
```
##### 2. 使用编程式配置

```
public class TestMain {
    public static void main(String[] args) {
          //使用LogConfig对象进行设置
          Logger logger = LoggerManager.getLogger("JtloggerInterface");
          logger.getLogContext().getLogConfig().setWrapCount(3);
    }
```


##### 3. 覆盖框架默认配置
在第一次使用Jtlog框架前调用才能生效，推荐在项目初始化代码块里调用。

 LoggerManager.setLogConfigDefaultValues(new **LogConfigDefaultValues**(){...});  
 或者  
 LoggerManager.setLogConfigDefaultValues(new **SimpleLogConfigDefaultValues**() {...});
```
public class TestMain {
    public static void main(String[] args) {
        LoggerManager.setLogConfigDefaultValues(new LogConfigDefaultValues() {
                @Override
                public int decideMaxLengthOfRow() {
                    return 0;
                }
    
                @Override
                public boolean decideIsEnabled() {
                    return true;
                }
    
                @Override
                public boolean decideIsUsedJtloggerApi() {
                    return true;
                }
    
                @Override
                public LogLevel decideMinVisibleLevel() {
                    return LogLevel.DEBUG;
                }
    
                @Override
                public LogHeader[] decideLogHeaders() {
                    return new LogHeader[]{LogHeader.TIME, LogHeader.CONTEXT,
                            LogHeader.TAG, LogHeader.THREAD, LogHeader.TRACE};
                }
    
                @Override
                public int decideWrapCount() {
                    return 0;
                }
    
                @Override
                public String decideDataFormat() {
                    return "HH:mm:ss:SSS";
                }
        });
        
        LoggerManager.setLogConfigDefaultValues(new SimpleLogConfigDefaultValues() {
                @Override
                public boolean decideIsEnabled() {
                    return true;
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
**- 添加日志过滤器**
1. 使用编程式方法去使用

```
//使用LogConfig方法
logger.getLogContext().addLogFilter(customLogFilter);
```
2. 使用注释配置方法添加过滤器，过滤器类必须有个无参公共的构造函数

```
@JtLoggerConfig(logFilters = {MyTagLogFilter.class},
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

````

**- 添加日志记录器**
1. 使用编程式方法去使用

```
//使用LogConfig方法
jtLogger.getLogContext().addLogRecorder(new MyLogRecorder());
```
 
2. 使用注释配置方法添加记录器，记录器类必须有个无参公共的构造函数

```
@JtLoggerConfig(logRecorders = {MyLogRecorder.class},
        contextName = "ComponentHandlerTest")
public class TestMain {
    public static void main(String[] args) {
         JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);
         jtLogger.info("bbb", "因为添加了文件记录器，这句日志信息将会被记录在文件上");
    }
}
```







