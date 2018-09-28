package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JDBCUtil;

import com.google.gson.Gson;

import entity.News;
import entity.Type;

public class JsonServlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int id = Integer.valueOf(request.getParameter("tid"));
		JDBCUtil util = new JDBCUtil();
		ResultSet rs = util.query("SELECT * FROM t_news WHERE tid = ?",id);
		List<News> list = new ArrayList<News>();
		try {
			while(rs.next()){
				News n = new News(rs.getInt("nid"), rs.getString("ntitle"), rs.getInt("tid"));
				list.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			util.closeAll();
		}
		Gson gson = new Gson();
		String str = gson.toJson(list);
		out.print(str);
		out.flush();
		out.close();
	}

}
