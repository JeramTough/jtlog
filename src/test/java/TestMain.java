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
		JtLogConfig.getJtLogConfig().setUsedJavaPrinter(true);
		test1();
		test2();
		test3();
	}
	
	private void test1()
	{
		new A();
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
