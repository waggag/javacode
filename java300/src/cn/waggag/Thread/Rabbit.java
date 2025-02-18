package cn.waggag.Thread;
/**
 * 模拟龟兔赛跑
 * 1.创建多线程,继承Thread类 + 重写run
 * 2.使用线程 创建子类对象,对象调用.start()方法,线程启动
 * @author waggag
 * 2018年4月18日 下午2:28:55
 */
public class Rabbit extends Thread {
	
	@Override
	public void run() 
	{
		//线程体
		for(int i=0; i<100; i++)
		{
			System.out.println("兔子跑了"+i+"步!");
		}
	}
} 

class Tortoise extends Thread {
	@Override
	public void run() 
	{
		//线程体
		for(int i=0; i<100; i++)
		{
			System.out.println("乌龟跑了"+i+"步!");
		}
	}
}