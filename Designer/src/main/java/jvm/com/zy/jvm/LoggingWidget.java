package jvm.com.zy.jvm;

public class LoggingWidget extends Widget {

	public synchronized void dothing()
	{
		//同步里面同步
		System.out.println(toString()+"111");
		super.dosomething();
		
	}
}
