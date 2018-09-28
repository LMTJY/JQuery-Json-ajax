package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	//������ĵ�ַ
	private final static String DRIVER_NAME="com.mysql.jdbc.Driver";
	//���ݿ����ӵ�ַ
	private final static String PATH="jdbc:mysql://127.0.0.1:3306/gannan";
	//���ݿ��û���
	private final static String NAME="root";
	//���ݿ�����
	private final static String PWD="123456";
	//���Ӷ���
	private Connection con;
	//������
	private PreparedStatement ps;
	//�����
	private ResultSet rs;
	
	
	/**
	 * ��ȡ���ӵķ���
	 * @since   2018��4��24��15:58:10
	 * @author  mxt
	 * @return  ���Ӷ���
	 */
	public  Connection  getCon(){
		try {
			//��������
			Class.forName(DRIVER_NAME);
			//��ȡ���Ӷ���
			con=DriverManager.getConnection(PATH,NAME,PWD);
		} catch (Exception e) {
			System.out.println("�������ݿ�ʱ�����쳣");
			e.printStackTrace();
		}
		return con;
	}
	
	
	/**
	 * �ر����ݿ�ķ���
	 * @since 2018��4��24��16:01:47
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
			System.out.println("�ر����ݿ�ʱ�����쳣");
			e.printStackTrace();
		}
	}
	
	

	/**
	 * ��ɾ��ͨ�÷���
	 * @author mxt
	 * @since 2018��4��24��16:06:39
	 * @param sql  ��ɾ�����
	 * @param obj  ����Ĳ�����������1��Ҳ���Զ��Ҳ���Բ�д   ����...��ʾ��̬��������͵�n��������n>=0��,JDK1.5֮��֧��
	 * @return     ��Ӱ������
	 */
	public int upate(String sql,Object...obj){
		//��ȡ���Ӷ���
		con=getCon();
		int result=0;  //��Ӱ������
		try {
			//��ȡ������
			ps=con.prepareStatement(sql);
			//�ж��Ƿ�����Ҫ�������
			if(obj.length>0){
				for (int i = 0; i < obj.length; i++) {
					//���������õ���Ӧ�ʺŵ�λ��
					ps.setObject(i+1, obj[i]);
				}
			}
			//ִ����ɾ�����
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
	 * ��ѯ��ͨ�÷���
	 * @param sql  ��ѯ���
	 * @param obj  ����Ĳ���
	 * @return     ��ѯ�������ݵĽ����
	 * @since 2018��4��24��16:23:15
	 * @author mxt
	 */
	public ResultSet query(String sql,Object...obj){
		        //��ȡ���Ӷ���
				con=getCon();
				try {
					//��ȡ������
					ps=con.prepareStatement(sql);
					//�ж��Ƿ�����Ҫ�������
					if(obj.length>0){
						for (int i = 0; i < obj.length; i++) {
							//���������õ���Ӧ�ʺŵ�λ��
							ps.setObject(i+1, obj[i]);
						}
					}
					//ִ����ɾ�����
					rs=ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//�����رղ�����������
		return rs;
	}
	
	

}
