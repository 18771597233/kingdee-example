package com.kingdee.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import org.junit.Test;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
/**
 * mencached基本操作
 * @author chenghuang
 *
 */
public class MemcacheJava {
	
	
	private static MemcachedClient mcc;
	static {
		try {
			// 连接本地的 Memcached 服务
			mcc = new MemcachedClient(new InetSocketAddress("47.106.215.232", 11211));
			System.out.println("Connection to server sucessful.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**set操作
	 * 
	 */
	@Test
	public void getMencache(){
		try{
	         // 存储数据
	         Future fo = mcc.set("runoob", 900, "Free Education");
	      
	         // 查看存储状态
	         System.out.println("set status:" + fo.get());
	         
	         // 输出值
	         System.out.println("runoob value in cache - " + mcc.get("runoob"));
	 
	         // 关闭连接
	         mcc.shutdown();  
	      }catch(Exception ex){
	         System.out.println( ex.getMessage() );
	      }
	}
	/**
	 * add操作
	 */
	@Test
	public void addMencache(){
		try{
			 // 添加数据
	         Future fo = mcc.set("runoob", 900, "Free Education");
	 
	         // 打印状态
	         System.out.println("set status:" + fo.get());
	 
	         // 输出
	         System.out.println("runoob value in cache - " + mcc.get("runoob"));
	 
	         // 添加
	         fo = mcc.add("runoob", 900, "memcached");
	 
	         // 打印状态
	         System.out.println("add status:" + fo.get());
	 
	         // 添加新key
	         fo = mcc.add("codingground", 900, "All Free Compilers");
	 
	         // 打印状态
	         System.out.println("add status:" + fo.get());
	         
	         // 输出
	         System.out.println("codingground value in cache - " + mcc.get("codingground"));
	 
	         // 关闭连接
	         mcc.shutdown();  
	      }catch(Exception ex){
	         System.out.println( ex.getMessage() );
	      }
	}
	/**
	 * replace操作
	 */
	@Test
	public void replaceMencache(){
		try{
			// 添加第一个 key=》value 对
			 Future fo = mcc.set("runoob", 900, "Free Education");
			 
			         // 输出执行 add 方法后的状态
			 System.out.println("add status:" + fo.get());
			 
			         // 获取键对应的值
			 System.out.println("runoob value in cache - " + mcc.get("runoob"));
			 
			         // 添加新的 key
			 fo = mcc.replace("runoob", 900, "Largest Tutorials' Library");
			 
			         // 输出执行 set 方法后的状态
			 System.out.println("replace status:" + fo.get());
			 
			         // 获取键对应的值
			 System.out.println("runoob value in cache - " + mcc.get("runoob"));
			 mcc.shutdown();  
	      }catch(Exception ex){
	         System.out.println( ex.getMessage() );
	      }
		}
	/**
	 * append后追加操作
	 */
	@Test
	public void appendMencache(){
		try{
			// 添加数据
	         Future fo = mcc.set("runoob", 900, "Free Education");
	 
	         // 输出执行 set 方法后的状态
	         System.out.println("set status:" + fo.get());
	 
	         // 获取键对应的值
	         System.out.println("runoob value in cache - " + mcc.get("runoob"));
	 
	         // 对存在的key进行数据添加操作
	         fo = mcc.append("runoob", " for All");
	 
	         // 输出执行 set 方法后的状态
	         System.out.println("append status:" + fo.get());
	         
	         // 获取键对应的值
	         System.out.println("runoob value in cache - " + mcc.get("codingground"));
			 mcc.shutdown();  
	      }catch(Exception ex){
	         System.out.println( ex.getMessage() );
	      }
		}
	/**
	 * 前追加操作
	 */
	@Test
	public void prependMencache(){
		try{
			// 添加数据
		    Future fo = mcc.set("runoob", 900, "Education for All");
		
		    // 输出执行 set 方法后的状态
		    System.out.println("set status:" + fo.get());
		
		    // 获取键对应的值
		    System.out.println("runoob value in cache - " + mcc.get("runoob"));
		
		    // 对存在的key进行数据添加操作
		    fo = mcc.prepend("runoob", "Free ");
		
		    // 输出执行 set 方法后的状态
		    System.out.println("prepend status:" + fo.get());
		    
		    // 获取键对应的值
		    System.out.println("runoob value in cache - " + mcc.get("codingground"));
		    mcc.shutdown();  
	      }catch(Exception ex){
	         System.out.println( ex.getMessage() );
	      }
	}
	/**
	 * CAS
	 */
	@Test
	public void casMencache(){
		try{
			// 添加数据
	         Future fo = mcc.set("runoob", 900, "Free Education");
	 
	         // 输出执行 set 方法后的状态
	         System.out.println("set status:" + fo.get());
	            
	         // 使用 get 方法获取数据
	         System.out.println("runoob value in cache - " + mcc.get("runoob"));
	 
	         // 通过 gets 方法获取 CAS token（令牌）
	         CASValue casValue = mcc.gets("runoob");
	 
	         // 输出 CAS token（令牌） 值
	         System.out.println("CAS token - " + casValue);
	 
	         // 尝试使用cas方法来更新数据
	         CASResponse casresp = mcc.cas("runoob", casValue.getCas(), 900, "Largest Tutorials-Library");
	         
	         // 输出 CAS 响应信息
	         System.out.println("CAS Response - " + casresp);
	 
	         // 输出值
	         System.out.println("runoob value in cache - " + mcc.get("runoob"));
		    mcc.shutdown();  
	      }catch(Exception ex){
	         System.out.println( ex.getMessage() );
	      }
	}
	
}
