package Unsafe;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Logger;
import javax.naming.*;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xquery.*;
import java.security.MessageDigest;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;




public class UnsafeSPRule extends HttpServlet {	
	// ���� - 31000489	[SP] ���ŵ��� �ʰ� ���� ����� �ڵ�  -- main�� ������ �� �� 
	public static void main(String args[]) throws SQLException{
		UnsafeSPRule u = new UnsafeSPRule();
		DAO dao = new DAO();
		
		dao.getName(args[0]);
		u.getBoolean(true);
		
        String data = null;
		getHashCodeUsingMD5(data);
        useMathrandom();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
        String param = request.getParameter("name");
        String param2 = "sky"+param;
        
        PrintWriter out = response.getWriter();
        if ( param != null ){
        // ���� - 31000080	[SP] �������� ����Ʈ �������� ��ũ��Ʈ ���� ��༺ -- ���� ������ ���� �� parameter���� < > &�� ġȯ���� ���� 
        	out.print("Hello " + param2 + " !");
        }
        out.print("<html><body>");
        // �̰��� - 31000352	[SP] ����Ʈ �� ��û ���� -- sky �̰� java ���̾�?? 
        out.print("<form action='/index.jsp' method='get'");
        String fname = request.getParameter("FileName");
        out.print("<input type='text' name='file' value='fname'>");
        String script = "a "+ fname;
        // ���� - 31000078	[SP] �ü�� ��ɾ� ���� -- run time �� exec �޼ҵ��� parameter�� ���� �˻� ���� 
        Runtime.getRuntime().exec(script);
        // ���� - 31000113	[SP] HTTP ���� ���� -- param2�� CR, LF ���� ���� ���� 
        response.addHeader("author",param2);
        out.print("</form>");
        out.print("</body></html>");
    }
	
	public void ldap() throws IOException {
		ServerSocket socket = null;

		try{
        	Properties pro = new Properties();
        	Hashtable env = new Hashtable();
        	SearchControls searchCtls = new SearchControls();
        	
        	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        	env.put(Context.PROVIDER_URL, "ldap://127.0.0.1:386");
        	// ��Ž - 31000285	[SP] �������� �ΰ� : anonymous �� ���� 
        	if (env == null) env.put(Context.SECURITY_AUTHENTICATION, "none");
        	if (env != null) env.put(Context.SECURITY_AUTHENTICATION, "simple");
        	env.put(Context.SECURITY_PRINCIPAL, "cn=user, dc=maxcrc,dc=com");
        	// ��Ž - 31000259	[SP] �ϵ��ڵ�� �н����� -- sky LDAP ������ ���� ��й�ȣ�� qa�� �ϵ��ڵ� 
        	String passwd = "qa";
        	if (passwd == null || "".equals(passwd)) return;
        	if (!passwd.matches("") && passwd.indexOf("@!#") > 4 && passwd.length() > 8) {
        		env.put(Context.SECURITY_CREDENTIALS, passwd);
        	}

        	javax.naming.directory.DirContext ctx = new InitialDirContext(pro);
            String url = pro.getProperty("id");
            String fname = pro.getProperty("name");
            // ���� - 31000023	[SP] ��� ���丮 ��� ���� -- �ܺ� �Է°� ��� ��  / \ �� ġȯ���� ����, �ܺ� �Է°��� string�� �߰��ϸ� ���  
            File rFile = new File("/qa/APPG/"+fname);
            // ���� - 31000036	[SP] ���� ���丮 ��� ���� -- �ܺ� �Է°� ��� ��  / \ �� ġȯ���� ����, �ܺ� �Է°��� �״�� ����ϸ� ���� 
            File aFile = new File(fname);
            rFile.delete();
            aFile.delete();
            // ���� - 31000090	[SP] LDAP ���� -- search ���ڿ�����  = + < > # ; \ �� ġȯ���� ����  
            NamingEnumeration result = ctx.search("url", url, searchCtls);    
            
            int port = Integer.parseInt(pro.getProperty("port"));
            // ���� - 31000099	[SP] �ڿ� ���� -- �ܺ� �Է°� ��� �� ����Ʈ���� �������� ���� 
            socket = new ServerSocket(port);
            pro.put(Context.OBJECT_FACTORIES, result);
        } catch(NamingException e){
        	Logger.getLogger(e.toString());
        }
        catch(IOException e){
        	Logger.getLogger(e.toString());
        }
        finally {
        	if (socket != null) socket.close();
        }
	}
	
	public void getBoolean(Boolean result1) {
		PrintWriter out = new PrintWriter(System.out, true);
		if (result1 == true) out.println(result1);
	}
//	��Ž - 45110674 [SP] ����� ������� ���� ��� - ���Ǿ��� ����Լ� ȣ������ 
	public int recursive(int n) {
		return (recursive(n-1));
	  }
	
//	��Ž - 45110652 - [SP] XQuery ����  
	public String useXQuery(String name) throws NamingException, IOException {
		Properties props = new Properties();
		String fileName = "MakeDB.properties";
		FileInputStream in = new FileInputStream(fileName);
		props.load(in);

		//�ܺη� ���� �Է��� ����
		String name2 = props.getProperty("name");
		
		String es = "doc('users.xml')/userlist/user[uname='"+name+"']";
		String es2 = "doc('users.xml')/userlist/user[uname='"+name2+"']";
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=rootDir");
		javax.naming.directory.DirContext ctx = new InitialDirContext(env);

		javax.xml.xquery.XQConnection conn = null;
		XQPreparedExpression expr, expr2 = null;
		XQResultSequence result = null;

		javax.xml.xquery.XQDataSource xqds = (javax.xml.xquery.XQDataSource)ctx.lookup("xqj/personnel");
		try{
			conn = xqds.getConnection();

			// name�� �ܺο��� �޾� ó������ ���
			expr = conn.prepareExpression(es);
			expr2 = conn.prepareExpression(es2);
			
			result = expr.executeQuery();
			while(result.next()){
				String str = result.getAtomicValue();
				if(str.indexOf('>')<0){
					System.out.println(str);
			}
		}
		}catch(XQException xqe){
			Logger.getLogger(xqe.toString());
		}finally{
			in.close();
		}
		return name;
	}
//	��Ž - 45110643	[SP] XPath ����
	public void useXPath(String name, String password) throws IOException{
		Properties props = new Properties();
		String fileName = "External.properties";
		FileInputStream in = new FileInputStream(fileName);
		props.load(in);

		// �ܺη� ���� �Է��� ����
		String prname = props.getProperty("name");
		String passwd = props.getProperty("password");

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr, expr2 = null;
		Object result, result2 = null;
		NodeList nodes = null;
		InputSource doc = null;
		try{
			 expr = xpath.compile("//users/user[login/text()='" + name+ "' and password/text() = '" + password + "']/home_dir/text()");
			 //name�� password�� �ƹ��� ó�� ���� �޾�  ó�� (������������ �α����� ��������)

			 expr2 = xpath.compile("//users/user[login/text()='" + prname+ "' and password/text() = '" + passwd + "']/home_dir/text()");
			 //name�� password�� �ƹ��� ó�� ���� �޾�  ó�� (������������ �α����� ��������)
			 
			 result = expr.evaluate(doc , XPathConstants.NODESET);
			 result2 = expr2.evaluate(doc , XPathConstants.NODESET);
			 
			 nodes = (NodeList) result;
		}catch(XPathExpressionException e){
			Logger.getLogger(e.toString());
		}finally{
			in.close();
		}
	}
// 45110247	[SP] ���� ������ DNS lookup�� ����
	public void useDNSLookup(HttpServletRequest req, HttpServletResponse res) throws SecurityException, IOException{
		boolean trusted = false;
		String ip = req.getRemoteAddr();
		
		InetAddress addr = InetAddress.getByName(ip);
		//DNS �̸��� �����ϰ� ���� 
		if(addr.getCanonicalHostName().endsWith("trustme.com")){
			trusted = true;
		}
	}
//45110732	[SP]  �߿��� �ڿ��� ���� �߸��� ���Ѽ���
	public void useFaltAuth() throws IOException{
		String cmd = "umask 0";
		Runtime.getRuntime().exec(cmd);
		File file  = new File("/home/report/report.txt");
	    }
//45110759	[SP] ��Ʈ ���� �Ϲ��� �ؽ� �Լ� ��� - 196���ο��� ȣ���� �ϱ� ������ �����. 
//������� �ʱ� ���ؼ��� 203���������� algorithm.update(b); �� �ʿ���. 
    private static void getHashCodeUsingMD5(String inData) {
    	MessageDigest algorithm = null;
        try {
        	if(inData !=null && algorithm !=null){
			algorithm.digest(inData.getBytes("UTF-8"));
        	}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(e.toString());
		}
    }

//    45110330	[SP] �������� ���� �������� ���
    private static double useMathrandom(){
    	return Math.random();
    }
//45110306	[SP] ������ ���� ���� �߿��� ���-- ����Ʈ Ŀ���͸���¡ ���� (������ü, ��������) �����Ǵ� ����� ���������� ������ �� ����
    //������ü, �������� Ŭ���� �� �޼ҵ尡 ���ǵǾ�� �� �� ���� . 

//45110494	[SP] ���Ἲ �˻���� �ڵ� �ٿ�ε�
    public void notCheckedDownload() throws ClassNotFoundException {
    	URL[] classURLs;
    	URLClassLoader loader;
		try {
			classURLs = new URL[] {
			    new URL("file:subdir/")
			};
	    	 loader = new URLClassLoader(classURLs);
	    	Class loadedClass = Class.forName("LoadMe", true, loader);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(e.toString());
		}
        }
    
}


class DAO {
	public static Connection conn = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	public static Integer rset = null;
	public static String passwd = "tiger";
	
	public Boolean getName(String mgr_nm) throws SQLException{
		Boolean result = false;
		try {
			// ��Ž - 31000259	[SP] �ϵ��ڵ�� �н����� - public static field�� tiger �ϵ��ڵ�  
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.2.50:1521", "scott", passwd);
			String selectQuery1, selectQuery2;
			String updateQuery;
			selectQuery1 = "SELECT DEPTNO FROM DEPT;";
			selectQuery2 = "SELECT GRADE FROM SALGRADE;";
			pstmt = conn.prepareStatement(selectQuery1);
			// ��Ž - 11939	DB �ڿ� ���� ���� - close���� �ʰ� prepareStatement ��� 
			pstmt = conn.prepareStatement(selectQuery2);
			if (!mgr_nm.equals(null)) {
				updateQuery = "UPDATE EMP SET MGR_NM = '" + mgr_nm + "' WHERE EMPNO = 0;";
		        stmt = conn.createStatement();
		        //���� - 31000089	[SP] SQL ����  -- ������ parameter�� prepareStatement�� set �Լ��� ����ؾ� �� 
		        rset = stmt.executeUpdate(updateQuery);
		        result = (rset!=0);
			}
			// ��Ž - 11940	DB Transaction ���� ���Ἲ ���� - autoCommit�� ������� �ʴ� ��� rollback �޼ҵ� ����ؾ� �� 
			conn.setAutoCommit(false); 
			conn.close();  
	   } catch (SQLException e) {
		   Logger.getLogger(e.toString());
	   } finally {
		   conn.close();
		   pstmt.close();
	   }
	   return result;
	}

}