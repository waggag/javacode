package cn.waggag.que;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用队列模拟银行存款业务
 * @author wagga
 * 2018年4月14日上午10:59:27
 */
public class Quest {
	public static void main(String[] args) {
		Queue<Request> que = new ArrayDeque<Request>();
		//模拟排队情况
		for(int i=0;i<10;i++)
		{
			final int num = i;
			que.offer(new Request(){
				@Override
				public void deposit() 
				{
					System.out.println("第"+num+"个人，办理存款业务，存款额度为"+(Math.random()*1000));
				}
				
			});
		}
		dealWith(que);
	}
	
	//处理业务
	public static void dealWith(Queue<Request> que) {
		Request req = null;
		while((req=que.poll()) != null) {
			req.deposit();
		}
	}
	
	
	
}
interface Request{
	//存款
	void deposit();
}
