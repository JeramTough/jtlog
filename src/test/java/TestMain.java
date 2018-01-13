import com.jeramtough.jtlog3.JtLogConfig;
import com.jeramtough.jtlog3.LogInformation;
import com.jeramtough.jtlog3.P;
import com.jeramtough.jtlog3.filter.TagJtLogFilter;
import com.jeramtough.jtlog3.level.JtLogLevel;
import com.jeramtough.jtlog3.logger.JtLogger;
import com.jeramtough.jtlog3.printer.JavaJtPrinterImpl;
import com.jeramtough.jtlog3.printer.JtPrinter;
import com.jeramtough.jtlog3.style.DebugPrintStyle;
import com.jeramtough.jtlog3.style.PrintStyle;

public class TestMain
{
	public static void main(String[] args)
	{
		new TestMain();
	}
	
	public TestMain()
	{
		//		test1();
		//		test2();
		test3();
	}
	
	private void test1()
	{
		//每次都是新对象
		LogInformation logInformation = new LogInformation("dsafsafd");
		logInformation.setJtLogLevel(JtLogLevel.DEBUG);
		logInformation.setTag("debug");
		
		//输入适配器,存在两个实例就得
		JtPrinter jtPrinter = new JavaJtPrinterImpl();
		
		PrintStyle printStyle = new DebugPrintStyle();
		
		JtLogger jtLogger = JtLogger.getJtLogger();
		jtLogger.setJtPrinter(jtPrinter);
		jtLogger.setPrintStyle(printStyle);
		
		jtLogger.log(logInformation);
	}
	
	private void test2()
	{
		P.arrive();
		P.p("primary information");
		P.debug(new Object());
		P.info("test", 1);
		Object o = null;
		P.warn("test", o);
		P.error("test", "error message");
	}
	
	private void test3()
	{
		P.info("test", "aaaaa");
		TagJtLogFilter tagJtLogFilter = new TagJtLogFilter("abc");
		JtLogConfig.getJtLogConfig().addFilter(tagJtLogFilter);
		P.info("abc", "bbbb");
	}
}
