package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	//驱动类的地址
	private final static String DRIVER_NAME="com.mysql.jdbc.Driver";
	//数据库连接地址
	private final static String PATH="jdbc:mysql://127.0.0.1:3306/gannan";
	//数据库用户名
	private final static String NAME="root";
	//数据库密码
	private final static String PWD="123456";
	//连接对象
	private Connection con;
	//语句对象
	private PreparedStatement ps;
	//结果集
	private ResultSet rs;
	
	
	/**
	 * 获取连接的方法
	 * @since   2018年4月24日15:58:10
	 * @author  mxt
	 * @return  连接对象
	 */
	public  Connection  getCon(){
		try {
			//加载驱动
			Class.forName(DRIVER_NAME);
			//获取连接对象
			con=DriverManager.getConnection(PATH,NAME,PWD);
		} catch (Exception e) {
			System.out.println("连接数据库时引发异常");
			e.printStackTrace();
		}
		return con;
	}
	
	
	/**
	 * 关闭数据库的方法
	 * @since 2018年4月24日16:01:47
	 * @author mxt
	 * 
	 */
	public void closeAll(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (Exception e) {
			System.out.println("关闭数据库时引发异常");
			e.printStackTrace();
		}
	}
	
	

	/**
	 * 增删改通用方法
	 * @author mxt
	 * @since 2018年4月24日16:06:39
	 * @param sql  增删改语句
	 * @param obj  传入的参数，可以是1个也可以多个也可以不写   类型...表示动态传入该类型的n个参数（n>=0）,JDK1.5之后支持
	 * @return     受影响行数
	 */
	public int upate(String sql,Object...obj){
		//获取连接对象
		con=getCon();
		int result=0;  //受影响行数
		try {
			//获取语句对象
			ps=con.prepareStatement(sql);
			//判断是否有需要置入参数
			if(obj.length>0){
				for (int i = 0; i < obj.length; i++) {
					//将参数设置到对应问号的位子
					ps.setObject(i+1, obj[i]);
				}
			}
			//执行增删改语句
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		
		return result;
	}
	
	
	/**
	 * 查询的通用方法
	 * @param sql  查询语句
	 * @param obj  传入的参数
	 * @return     查询到的数据的结果集
	 * @since 2018年4月24日16:23:15
	 * @author mxt
	 */
	public ResultSet query(String sql,Object...obj){
		        //获取连接对象
				con=getCon();
				try {
					//获取语句对象
					ps=con.prepareStatement(sql);
					//判断是否有需要置入参数
					if(obj.length>0){
						for (int i = 0; i < obj.length; i++) {
							//将参数设置到对应问号的位子
							ps.setObject(i+1, obj[i]);
						}
					}
					//执行增删改语句
					rs=ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//不做关闭操作！！！！
		return rs;
	}
	
	

}
