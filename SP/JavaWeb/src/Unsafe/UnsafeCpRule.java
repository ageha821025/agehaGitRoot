
package Unsafe;

import java.net.Socket;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import org.exist.util.serializer.IndentingXMLWriter;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.security.PrivilegedAction;




/**
 * @author ageha
 *
 */

public class UnsafeCpRule extends Throwable{
	

	
	public UnsafeCpRule(){
		String iden_num = u103(5);
	}
	//���� - 11824	Ŭ������ ���� �̸��� ����ϴ� �޼ҵ�
	public void UnsafeCpRule(){
		
	}
    public static String url = "jdbc:mysql://172.16.2.74:3306/testlink";

    //���� -11883	������ Private �ʵ� ���� ���� 
    //���� -11725	Volatile ��� ����
    //���� - 11823	�޼ҵ� ��� ��Ģ
    private volatile static String Property = "?characterEncoding=EUC_KR";

    //���� - 11782	Final �ʵ�� �ݵ�� Static���� �����ؾ� �� 
    public final String user = "testlink";
    public static String password = "testlink";
    public static String q;

	private StringBuffer queryBuff = new StringBuffer("");
    //���� - 11874	Char�� ����� StringBuffer�� �ν��Ͻ� ����
	private StringBuffer queryBuff2 = new StringBuffer('a');
    private static final SimpleDateFormat sdf = new SimpleDateFormat();
    private String date1 = "2013-04-02";
    
	public boolean compareClass(Class a, Class aa) throws ParseException{
		java.util.Date timedates = sdf.parse(date1);
		//���� - 11769	����ȭ���� ���� Static ��¥ ������(Formatter)
		String datestr = sdf.format(timedates);
		
		//���� - 11683	ThreadGroup ��� ����
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		boolean b = true;
		Double db = null;
		//���� - 14	getName()�� ����� Class �� ����
		if(a.getName().equals(aa)){
			b = true;
		}else{
			b = false;
		}
		//���� - 11703	�� (Empty) Synchronize Block ��� ���� 
		synchronized (UnsafeCpRule.class){
			
		}
		//���� - 11746	�������� ��
		if(Double.NaN !=db){
			b = true;
		}
		return b;
	}
	//���� - 48	���� �Ķ���� ��� ����
    public String U104(int ... flag){
        //���� - �ֹε�Ϲ�ȣ �ϵ��ڵ� ����
    	String idenNumber = "8402182813239"; //����
    	String idenNumber2 = "840218-2813239"; //����
    	
    	//���� - 11867	String ������ String �ν��Ͻ� ���� ���� 
    	String returnValue = new String("init");
    	Short st = Short.valueOf(idenNumber);

    	//���� - 11811	Short �ν��Ͻ� ����
    	Short st1 = new Short(st);

    	//���� - 11814	Byte �ʱ�ȭ
    	//���� - 11877	���ʿ��� String.valueOf �޼ҵ� ȣ�� (String�� ���� �� ���� ) 
    	Byte b = new Byte(String.valueOf(flag[0])+idenNumber2);
    	
    	//���� - 11815	Integer �ʱ�ȭ
    	Integer i = new Integer(idenNumber);

    	//���� - 11684	8���� ���� ��� ����
    	int ii = 073;
    	
    	switch (flag[0]) {
		case 1:
			returnValue = idenNumber;
		default:
			returnValue = idenNumber2;
		}
    	//���� - 11692	For ���� While ������ ��ü ���� 
    	for(;1==1;){
    		if(flag[0]==0){
    			break;
    		}
    	}
    	//���� - 11868	String ��ü�� ���� toString() ��� ����
    	return returnValue.toUpperCase().toString();
    }
    
    //[����]IP �ּ��� �ϵ��ڵ� ����
	public String u103(int flag){
		 String ip = "127.0.0.1";
		 String  ipv6 ="fe80::f8e6:d014:56c3:4ebf%11";
		 
		 //���� - 11749	Switch ������ Default ������ �������� �������� ���� 
		 switch (flag) {
			default:
			System.out.println("error");
		//�̰��� - 11783	Switch�� Case ������ Break�� ���� ���� 	
			case 2 : 
			System.out.println(ipv6);
		}
		 //���� - 11765	Switch ���� �ʹ� ���� �б� ���
		 //���� - 11702	�� (Empty) Switch �� ��� ���� 
		switch (flag) {
//		case 1:
//			System.out.println(ip);
//			break;
//		case 2 : 
//			System.out.println(ipv6);
//			break;
//		default:
//			System.out.println("error");
//			break;
		}
		//���� - 11755	Switch ���� Case ����
		switch (flag) {
		case 1:
			mylabel: 
			break;
		default:
			break;
		}
		return ip;
	}
	//INSERT ������ �÷��� �Է°��� ���� ��ġ 
	public void u115(double doubleIData){
		PreparedStatement psmt = null;
		//���� - 11682	���� ���� ���� ������ ��� ����
		int rB = +-+1;
		String qry = "insert into cm_src(src_id, sname, spath) values (1,2)"; //���� - �÷����� 3�� �Է°� ���� 2��
		
		DecimalFormat df = new DecimalFormat("0.0");
		double resultD2 = -9999.0d;
        String d2Str = df.format(doubleIData);
        resultD2 = Double.parseDouble(d2Str);
        
        //���� 11681	BigDecimal �����ڿ��� Decimal ���ͷ� ��� ����
        resultD2 = new java.math.BigDecimal(doubleIData).setScale(2, java.math.BigDecimal.ROUND_UP).doubleValue();
		Connection con  = s4418();
		
		try{
			psmt = con.prepareStatement(qry);
		    for (int k = 0;k<1; k++) {
		    	//�̰��� 100	Loop�� ������ ���� ������ ����(addBatch ������)
				rB = psmt.executeUpdate(qry);
				if(rB==0){
					//�̰��� - 11706	ȥ���� Loop ���� ���� ���� ���� 
					k=1;
				}
		    }
		}catch(SQLException sqle){
			Logger.getLogger(sqle.toString());
		}finally{
			if(con !=null && rB>0 && psmt !=null){
				try{
					psmt.close();
					con.close();
				}catch(SQLException e){
					Logger.getLogger(e.toString());
				}
			}else{
				//���� - 11853	NullPointerException�� Throw ����
				 throw new NullPointerException();
			}
			
		}
	}

	public void u114(int flag){
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		//���� - 114	INSERT ������ ���̺� �÷� �̸� ��� ����
		//���� - 108	Join ���̺� ���� �ٸ��ƽ� ��� ����
		//���� - 106	���길 �����ϴ� SQL �� ����	
		String iQry = "insert into cm_src values ";
				iQry += "(SELECT C_PKG.PKG_NAME, c_pkg_menu_map.MENU_ACT_ID  FROM C_PKG , C_PKG_MENU_MAP "; 
				iQry += " WHERE c_pkg.PKG_ID = 1" ;
				iQry += " AND c_pkg.PKG_ID = c_pkg_menu_map.PKG_ID";
				iQry += " UNION";
				iQry += " SELECT C_PKG.PKG_NAME, c_pkg_menu_map.MENU_ACT_ID  FROM C_PKG , C_PKG_MENU_MAP "; 
				iQry += " WHERE c_pkg.PKG_ID = 3 ";
				iQry += " AND c_pkg.PKG_ID = (SELECT 1+100 FROM dual)) ";

		//���� - 190	WHERE ���� ���� DELETE �Ǵ� UPDATE ��� ����				
		String uQry = "update cm_src set sname = 'a'";
		String dQry = "delete from cm_src";
		String qry = "";
		
		Connection con  = s4418();
		try{
			
			//if else �� ��� �̰��� 
			if(flag == 1){
				qry = iQry;
			}else if(flag ==2){
				qry = uQry;
			}else{
				qry = dQry;
			}
			stmt = con.prepareStatement(qry);
			
			if( flag >1){
				for(int i=0;i<flag;i++){
					//���� - 100	Loop�� ������ ���� ������ ����(addBatch ������)
					int a = stmt.executeUpdate();
					//���� - 11706	ȥ���� Loop ���� ���� ���� ���� / ���� for�������� ���� 
					//���� - 22	Loop�� �� (Empty) ���� ��� ����
					for(a=0;;i++){
						System.out.println("update Result : "+a);
					}
					
				}
			}else{
				rs = stmt.executeQuery();
			}
		}catch(SQLException e){
			Logger.getLogger(e.toString());
		}finally{
			if(con !=null && rs !=null){
				try{
					rs.close();
					con.close();
				}catch(SQLException e){
					Logger.getLogger(e.toString());
				}
			}
			
		}
	}



	//DB �ڿ� ���� �˻� 
	public Connection s4418(){
	    Connection con = null;	
	    try{
	    	con = DriverManager.getConnection(url, user, password); //���� 
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return con;
	}
	//File �ڿ� ���� �˻�
	public void u4402(String fileName){
		FileInputStream fs = null;
		try{
			 fs= new FileInputStream(fileName); //���� new �ѵ� Close�� ���� �ʾ��� 
		//���� - 11855	Catch ������ Throwable ��ü ��� ���� 
		}catch(Throwable th){
			Logger.getLogger(th.toString());
		}
	}
	//Socket �ڿ� ���� �˻�
	public void u4412(){
		Socket so = null;
		try{
			so = new Socket(); //���� finally ���� ���� ���� �ʾ��� 
		}catch(Exception e){
			
		}finally{
			
		}
	}

	//���� ResultSet 0����
	//���� - 11745	�޼ҵ� Synchronized ���� 
	public synchronized void u9(String biz_nm){
		Statement stmt = null;
		ResultSet rs = null;
		//���� - 11734	���� ������ ���� Final ��� ����
		
		final String biz_nm_chk = biz_nm.replace('%', '/');
		short system_id  = 4;
		
		
		//�̰��� - 182	ALIAS ��Ī �ߺ� ��� ����		
		String  qry = "select a.SRC_ID, a.SPATH, a.SNAME ";
				qry+="from cm_src a ";
				qry+="WHERE a.SRC_ID IN "; 
				qry+="( ";
				qry+="SELECT b.SRC_ID FROM C_BIZ a, CM_BIZ_SRC_MAP b ";
				qry+="WHERE a.BIZ_NM='SP_JAVA_WEB' ";
				qry+="AND a.BIZ_id = b.BIZ_ID ";
				qry+=")";

		//���� -182	ALIAS ��Ī �ߺ� ��� ����
		//���� -186	NULL �� ���� ����� ����
		//���� -187	���ʿ��� DISTINCT ����� ����
		//���� -11873 StringBuffer ���𺸴� �� ū �뷮�� ����ϴ� ��� 		
		//���� -11864	 ��ȿ������ StringBuffer ��� ���� 
		//���� -11842	 �� (Empty) String ���� ���ϱ� ���� ����
		//���� -113	SELECT ������ ���̺� �÷� �̸� ��� ���� (SELECT * ��� ����)
		//���� -119	������ �˻� ���ǿ� ���� 'OR' ���� ���� ����		
		//���� - 124	WHERE ������ LIKE ��� �� ù���ڷ� '%', '_' ��� ����
		//���� - 4324	OR ��� UNION ALL ��� ����		
		//���� - 4328	Sub-query �� ���� ������ ���� ���� Ȯ�� �� IN ���ٴ� EXISTS �� ��� ����	
		//���� - 11724	Short Ÿ�� ��� ����		
		//���� - 107	Select ���� �÷� ���� ���� �÷��� �ƴ� ���(���깮 ��) �˸��ƽ� ��� ����	
		//���� - 	109	Join �÷��� ���� �ٸ��ƽ� ��� ����
        queryBuff.append(" select rownum, allTb.* from(");    
		queryBuff.append(" SELECT distinct SRC_ID,       concat(sPATH,SNAME) "); 
		queryBuff.append(" FROM cm_src a,  ");
		queryBuff.append("      (SELECT b.SRC_ID "); 
		queryBuff.append("          FROM C_BIZ a,  ");
		queryBuff.append("               CM_BIZ_SRC_MAP b "); 
		queryBuff.append("         WHERE (a.BIZ_NM='"+biz_nm+"' or a.biz_nm like '%SP_JAVA_WEB') "+""); 
		queryBuff.append("           AND a.BIZ_id = b.BIZ_ID AND a.UPD_DT IS NULL");
		queryBuff.append("    ) B  ");
		queryBuff.append(" WHERE a.SRC_ID = B.src_id AND ");
		queryBuff.append(" A.SPATH IN ( ");
		queryBuff.append(" SELECT REMOTE_FILE_PATH FROM CM_COLLECT_INFO WHERE SYSTEM_ID = "+system_id+" ");
		queryBuff.append(" OR SYSTEM_ID = (SELECT hdw_id+2 FROM C_HDW_INFO WHERE hdw_nm = 'QARULE')))allTb ");
			  
	    
		Connection con  = s4418();
		
		int a = 0;
		
		synchronized (UnsafeCpRule.class) {
			try{
				//11940	DB Transaction ���� ���Ἲ ����
				con.setAutoCommit(false);
				stmt = con.createStatement();

				//���� - 11716	If ���� �߰�ȣ ��� ����  
				//���� - 11875	���ʿ��� ��ҹ��� ����
				if(!biz_nm.toUpperCase().equals("ALL"))
					
				//���� - 83	Nested synchronized ��� ����	
				synchronized (UnsafeCpRule.class) {
					//�̰��� - 11878	StringBuffer.length() ���
					if(queryBuff.toString().length()>10){
						rs = stmt.executeQuery(queryBuff.toString());
					}
					//���� - 11878	StringBuffer.length() ���
					if(!queryBuff.toString().equals("")){
						rs = stmt.executeQuery(queryBuff.toString());	
					}
					//���� - 11705	�� (Empty) While �� ��� ���� 
					while(a>0){
						
					}
					
				}
				//���� - 11687	ResultSet Ȯ�� 
				rs.next();
				
				//���� a�� 0�̹Ƿ� 
				System.out.println(rs.getString(a));
				
				//���� 11714	For ���� �߰�ȣ ��� ���� 
				//���� 11869	String Ÿ�� �񱳽� equlas() ��� 
				for(int i=0; i<rs.getRow()+1;i++)
					//����
					System.out.println(rs.getString(i));
				
					//����
				System.out.println(rs.getString(0));
			}catch(Exception e){
				System.out.println("Exception!!");
			}finally{
				if(con !=null && rs !=null){
					try{
						rs.close();
						con.close();
					}catch(Exception e){
						
					}
				}
				
			}
		}
		
	}
	static String t() {
		return null;
	}
	//Map/List�� Static �ʵ�� ���� ����
	private static final List<String> list= new ArrayList<String>(); //���� - List�� Static�� ����
	private static Map map;	//���� - Map�� Static�� ����
	static Map map2; //���� - Map�� Static�� ����
	
	//��ü �񱳽� == ������ ��� ���� 
	public void u11780(Boolean a, Boolean b){
		Boolean c = true;
		Integer iia = 1;
		Integer iib = 2;
		
		String sa="";
		String sb="";
		 
		if (a==b) {//����  Boolean ��ü ��
			if (b == c) {//����  Boolean ��ü �� - �Ķ���Ϳ� ����� ��ü ��
				if (sa==sb) {//���� String ��ü ��
					if(iia==iib){//���� Integer ��ü ��
						
						}
					}
				}
			}	
		}
	
	//equals()���� Null �� ���� 
	public void u11781(String a){
		String sa = null;
		
		Object o = "U11781";
		if (o.equals(null)) { // ���� 
			
		}
		if(a.equals(sa)){ // ���� sa �� null�̹Ƿ� 
			if(a.equals(null)){ //���� 
			}
		}
	}
	//�߸��� Null�˻� 
	public void u11693(String a){
		String sa = null;
		//���� a�� null �ϰ�� NullPointException �߻� , �ƴҰ�� �ι� ° �񱳰�  �ǹ� ����
		//�ΰ����� ��� ���� �� ���� �� ���ݿ� �ɸ� (�Ķ���ͺ�)
		if(a.getClass()==null && a==null){  
			
		}
		//���� sa�� null�̹Ƿ� NullPointException �߻�, �ƴҰ�� �ι�° null �񱳰� �ǹ� ����
		//����� ��ü�� �� 
		if(sa.getClass()==null && sa==null){
			
		}
	}
	
	//�ҿ����� Null �˻�
	//���� - 11845	����Ŭ�θ�ƽ ���⵵( �⺻���� 11��)  
	void u11686(int un) {
		String a=null,b=null;
		// ����- a.length()���� NullPointException �߻�
		if (a == null && a.length() > 0) { 
			System.out.println("cyclo 1");
		}else if (a != null || a.length() > 0) { // ����- a.length()���� NullPointException �߻�
			System.out.println("cyclo 2");
		}else if (t() != null || t().length() > 0) { // ����- t().length()���� NullPointException �߻�
			System.out.println("cyclo 3");
		}else if (q != null || t().length() > 0) {// ����- t().length()���� NullPointException �߻�
			System.out.println("cyclo 4");
		}else if (q != null || q.length() > 0) { // ����- q.length()���� NullPointException �߻�
			System.out.println("cyclo 5");
		}else if (q != null || q.toLowerCase().length() > 0) { // ����- q.toLowerCase().length()���� NullPointException �߻�
			System.out.println("cyclo 6");
		}else if (a.intern() == null && a.intern().getBytes() == null) { //���� - a.intern()���� NullPointException �߻�
			System.out.println("cyclo 7");
		}else if (a.substring(1) == null && a.substring(2) == null) { //���� - a.subString(1)���� NullPointException �߻�
			System.out.println("cyclo 8");
		}else if (a.substring(1) == null && a.substring(1).toCharArray() == null) { //���� -a.subString(1)���� NullPointException �߻�
			System.out.println("cyclo 9");
		}else {
			System.out.println("cyclo 10");
		}
		switch (un) {
		case 1:
			System.out.println("cyclo: 11");
			break;
		case 2:
			System.out.println("cyclo : 12");
			break;
		default:
			break;
		}
		
	}
	//���� - 11718	Clone �޼ҵ�� Cloneable�� Implement �Ͽ��� ��
	//���� - 11720	Clone�� CloneNotSupportedException�� Throw ��
    public Object clone()
    {
    	UnsafeCpRule ucr = null;
       try{
         ucr = (UnsafeCpRule)super.clone();
       }catch(CloneNotSupportedException e){
    		Logger.getLogger(e.toString());
       }finally{
        return ucr;
       }
    }
    public void exeThread(String flag){
    	Thread tr = new Thread();
    	if(flag != null){
	    	if("run".equals(flag)){
	    		tr.run();
	    	}else if("stop".equals(flag)){
	    		//���� - 1	Thread.stop(), Thread.suspend() ��� ����
	    		tr.stop();
	    	}else{
	    		//���� - 1	Thread.stop(), Thread.suspend() ��� ����
	    		tr.suspend();
	    	}
    	}
    }
    //���� - 11707	equals() ���� ��ü�� hashCode() ���� 
    public boolean equals(Object o){
    	
    	UnsafeCpRule ucr = new UnsafeCpRule();
    	
    	if(ucr.equals(o)){
    		return true;
    	}else{
    		//���� - 11791	Object.finallize ���  ���� 
    		ucr.finalize();
    		return false;
    	}
    	
    }
//    protected void finalize() �Լ��� gc�� �� �ڵ������� ȣ���� �̷���. 
//    ���� �� �޼���� �ƹ��͵� �����Ǿ� ���� ����
//    ���� - 11790	Finalize�� Proected �̾�� ��
    public void finalize(){
    	
    }
    public void toArrayTest(){
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("t1");
    	list.add("t2");
    	//�̰��� - 11688	toArray() �޼ҵ��� ClassCaseException
    	String[] arr  = (String[])list.toArray();
    	
    }

    public void invalidCallsInMethod() throws SecurityException, NoSuchMethodException {
        // Possible call to forbidden getDeclaredConstructors
        Class[] arrayOfClass = new Class[1];
        this.getClass().getDeclaredConstructors();
        this.getClass().getDeclaredConstructor(arrayOfClass);
        Class clazz = this.getClass();
        clazz.getDeclaredConstructor(arrayOfClass);
        clazz.getDeclaredConstructors();

        //���� - 11722	���ټ� ���� ����
        clazz.getMethod("", arrayOfClass).setAccessible(false);
        AccessibleObject.setAccessible(null, false);
        Method.setAccessible(null, false);
        Method[] methodsArray = clazz.getMethods();
        int nbMethod;
        for ( nbMethod = 0; nbMethod < methodsArray.length; nbMethod++ ) {
        //���� - 11722	���ټ� ���� ����
        	methodsArray[nbMethod].setAccessible(false);
        }

        PrivilegedAction priv = (PrivilegedAction) new Object(); priv.run();
       }

}

class UnSafeInterClass{
	String ip = "127.0.0.1";
	String ipv6 ="fe80::f8e6:d014:56c3:4ebf%11";
	static Map map;
	
	private UnSafeInterClass() {
		
	}
	
	//������ ���� �ʾ� ���� 
	public void u4412(){
		Socket so = null;
		try{
			so = new Socket();
		}catch(Exception e){
			
		}finally{
			
		}
		//���� - 11704	�� (Empty) Try �� ��� ���� 
		try{
            //String log_file = "D:\\workspace\\smoac_"+crs_name+"_str.sql";
            //ps = new PrintStream(new FileOutputStream(log_file, false));
            //ps.print(queryBuff);
		}catch(Exception e){
			//���� - 11705	�� (Empty) While �� ��� ����
			//���� - 11717	While ���� �߰�ȣ ��� ����
			while(so.isConnected())
			Logger.getLogger(e.toString());
			//���� - 11711	���ʿ��� Return �� ��� ���� 
			return;
		}
	}

}
