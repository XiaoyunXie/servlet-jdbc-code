package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//JDBC驱动名及数据库URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/bookstore_java?serverTimezone=UTC&amp";
	
	//数据库的用户名与密码，需要根据自己的配置
	static final String USER = "root";
	static final String PASS = "luolikong";
       
    public ListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("tableName");
		System.out.println(tableName);
		Connection conn = null;
		Statement stmt = null;
		try {
			//注册JDBC驱动器
			Class.forName("com.mysql.cj.jdbc.Driver");
			//打开一个连接
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			//执行SQL查询
			stmt = conn.createStatement();
			String sql = "select * from "+tableName;
			ResultSet rs = stmt.executeQuery(sql);
			
			List list = new ArrayList();
			ResultSetMetaData md = rs.getMetaData();//获取键名
			int columnCount = md.getColumnCount();//获取列的数量
			//展开结果集
			while(rs.next()) {
				Map rowData = new HashMap();
				for(int i = 1;i <= columnCount;i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
			request.setAttribute("length", columnCount);
			request.setAttribute("list", list);
			System.out.println(list.toString());
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
				}
			}catch(Exception e) {
				
			}
			
			try {
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				
			}
			request.getRequestDispatcher("../../index.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
