/*******************************************************************************
 * Project      : Quics Project Engine
 * FileName     : ClickStreamControler.java
 * Author       : ������
 * Organization : (��) ���ͳ�Ŀ�ӽ��ڸ���
 * Version      :
 * Date Created : 2002�� 4�� 12��
 * -----------------------------------------------------------------------------
 * Revison History
 * who          when         	what
 * syk 		2003.06.10	finalize() �߰�, rename�ϴ� ���� write�Ǵ� ��(����� 0�� ��)�� ���ǵǴ� ���� �ذ�
*******************************************************************************/

package quics.eng.csc;

import java.io.*;
import java.util.*;
import java.net.*;

import spectra.base.util.DateUtil;

/**
 <CODE>ClickStreamControler</CODE>�� ������� ���¸� �м��ϱ� ���� CRM������ Click Stream Engine�� �����ϰ� ������ �����ϱ� ���� Controler�̴�.

 ������ �Ǽ��� �� ���Ͽ� ����ϰ� ���� �ٸ� ������ �����ؼ� ����մϴ�.
 �ٸ� ������ ������ ��� Agent�� �ٸ� ������ �̸��� �����մϴ�.

 ������ property Handler�� ���ǵ� ���ϸ� ����_�ð�����(DD_yyyyMMddHHmmss)�� ���Ͽ� �����մϴ�.

 file �� property �� ������ rootpath �Ʒ��� ���� �׷캰�� ������ ���� �����մϴ�.

 �����׷��� BOK�̰� �ν��Ͻ� ���̵� BOK01 �� ���

 rootpath/BOK/BOK01_yyyyMMddHHmmss

**/

public class ClickStreamControler
{
	// ���ϸ��� �����ϴ� ��������
	// ��Ž - 11782	Final �ʵ�� �ݵ�� Static���� �����ؾ� �� 
	public final String m_DateFormat = "_yyyyMMddHHmmss";

	// ���Ͽ� �� Ŭ�� �Ǽ�
	private int m_clickCount = 0;

	// �� ���Ͽ� �� �� �ִ� �ִ� Ŭ�� �Ǽ�
	private int m_clickLimitCount;

	private long m_changeTerm;
	//��Ž - 11764	���� �ʵ�
	private long m_checkTime;

	private PrintWriter  m_writer;
	//��Ž - 11883	������ Private �ʵ� ���� ���� 
	//��Ž - 11866	�ʵ忡 StringBuffer ��� ����
	private StringBuffer m_sb;

	// �ν��Ͻ� Ÿ��
	//��Ž - 11764	���� �ʵ�
	private String m_instanceType  = "";

	// �ν��Ͻ� ���̵�
	private String m_instanceCode  = "";

	// ���� �����ִ� ����
	private File m_currentFile = null;

	// ������ ����� ���
	// ��Ž - 11764	���� �ʵ�
	private String m_rootpath  = "";

	// ����� ���� ���
	private String m_filepath  = "";

	/**
	* ClickStreamControler�� �������Դϴ�.
	*/
	public ClickStreamControler()
	{

		this.m_checkTime       = 10000l;

		this.m_filepath        = initDirectory(m_rootpath , m_instanceType+File.separator+m_instanceCode);


		Runnable run = new Runnable() {
			public void run() {
				long startTime = System.currentTimeMillis();
				//��Ž - 12	True Loop���� No break ����
				//��Ž - 45110571	[SP] �׻� ���� ����
				while(true) {
					if( System.currentTimeMillis()-startTime>=m_changeTerm || m_clickCount>=m_clickLimitCount )
					{
						//log.inf.println("###############CHECK###############");
						startTime = renameFile();
					}
					//��Ž - 4306	Catch �� ���� ��ó�� Exception ����
					//InterruptedException�� ó�������� �ʾ����� Exception�� �� ���� �̹Ƿ� 
					//��Ž - 45110754	[SP]  ������������ ����ó��
					//��Ž - 45110390	[SP] �׼� ���� ���� ���� Ž�� (���� ��Ȳ�� ���� ó�� ����)
					try{Thread.sleep(m_checkTime);}catch(Exception e){}
					
						
				}
			}
		};

		(new Thread(run, "File Check Thread")).start();
	}

	/**
	* �Էµ� ����Ÿ�� ���Ͽ� ����.
	* @param  o  an object writing to file
	*/
	public void setStream(String s) throws Exception
	{
		//��Ž - 4322	Synchronized�� this�� lock���� ���̴��� Ȯ��
		synchronized (this) {
			if( null == m_currentFile || null == m_writer || !m_currentFile.exists() ) {
				//��Ž - 45110488	[SP] �߸��� �������� ������ ���� (���� ���� ������ ����)
				//HttpServlet�� ������. 
				m_currentFile = new File(m_filepath,generateFilename(m_instanceCode));

				//��Ž - 4306	Catch �� ���� ��ó�� Exception ����
				//openWriter�� ����ϸ鼭 IOException�� ���� ó���� ����
				//��Ž - 45110488	[SP] �߸��� �������� ������ ���� (���� ���� ������ ����)
				//HttpServlet�� ������. 
				m_writer      = openWriter( m_currentFile.getPath() );
				m_clickCount  =  0;
			}
			m_writer.println(s);
			m_clickCount++;
		}
	}
	protected void finalize() throws Throwable
	{
		renameFile();
	}	
				
	private long renameFile() {
		//log.inf.println("ReName File");
		try
		{
			if(m_writer != null) {
				m_writer.flush();
				m_writer.close();
				//log.dbg.println("writer close");
			}
		
			if( null != m_currentFile && m_currentFile.exists() ) {
				File oldFile = new File(m_currentFile.getPath());
				//��Ž - 11729	Null�� �Ҵ�
				//�ʱ�ȭ �κ��� �ƴѵ� null�� �Ҵ��ϰ� �����Ƿ� 
				m_currentFile = null;

				//��Ž - 11729	Null�� �Ҵ�
				//�ʱ�ȭ �κ��� �ƴѵ� null�� �Ҵ��ϰ� �����Ƿ� 
				m_writer = null;
				//log.dbg.println("currentFile null");
				//��Ž - 45110023	[SP] ��� ���丮 ��� ����
				//oldFile�� �ܺ� �Է��� �ƴ�. 
				oldFile.renameTo( new File(oldFile.getPath()+"_WD") );
			}
			//��Ž - 45110754	[SP]  ������������ ����ó��		
			//��Ž - 45110390	[SP] �׼� ���� ���� ���� Ž�� (���� ��Ȳ�� ���� ó�� ����)
		} catch(Exception e) {
		} finally {
			//��Ž - 45110584	[SP] finally ��� ������ ����
			return System.currentTimeMillis();
		}
	}


	private String initDirectory(String parent , String child)
	{
		File dir = new File(parent , child);

		if( !dir.exists() || !dir.isDirectory() )
		{
			dir.mkdirs();
		}
		return dir.getPath();
	}

	/**
	* open file
	* @param filename open filename
	*/
	private PrintWriter openWriter(String filename) throws IOException
	{
		try {
			//log.dbg.println(filename + " open ..");
			//��Ž - 45110404	[SP] �ڿ��� �������� ��ȯ
			return new PrintWriter(new BufferedWriter(new FileWriter(filename, true)) , false);
		} catch(IOException e) {
			//��Ž - 11856	Exception�� �� Throw ����
			throw e;
		}
	}

	/**
	* ���� �����̸��� �޾Ƽ� ���� ���ڿ� �ð��� �ٿ� �����ش�.
	* @param    filename
	* @return   string     filename_DD_HHmmss
	*/
	private String generateFilename(String filename)
	{
		return filename;
	}
}