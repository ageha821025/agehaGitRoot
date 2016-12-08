package quics.eng.csc;
/*******************************************************************************
 * Project      : Quics Project Engine
 * FileName     : ClickStreamThread.java
 * Author       : ������
 * Organization : (��) ���ͳ�Ŀ�ӽ��ڸ���
 * Version      :
 * Date Created : 2002�� 9�� 13��
 * -----------------------------------------------------------------------------
 * Revison History
 * who          when         	what
 *******************************************************************************/

import java.io.*;
import java.util.*;

import com.daulsoft.util.log.Syslog;


/**

**/
//��Ž - 10032	Thread ��� ����
public class ClickStreamThread extends Thread
{


	private Object [] m_arFileList;
	//��Ž - 11883	������ Private �ʵ� ���� ���� 
	private boolean   m_notify;

	//��Ž - 11764	���� �ʵ�
	private String    m_poolName;
	//��Ž - 11764	���� �ʵ�
	private String    m_query;

	private String    m_savePath;

	/**
	* Constructor
	*/
	public ClickStreamThread( ) {

		this.m_savePath  = "/oradata3/qslog";
		this.m_poolName  = "jdbc:weblogic:pool:CSPool";
		this.m_query     = "insert into quics_log(���ϸ�,�ð�) values(?,sysdate)";
	}

	public void setFileList( Object [] arList ) {
		//��Ž - 45110496	[SP] private �迭-���� �ʵ忡 ���� ������ �Ҵ�
		this.m_arFileList = arList;
	}

	/**
	* thread start
	*/
	public void run()
	{
		try {
			File             f;
			DataInputStream  bin = null;
			String           remotePath;
			LinkedList       lst = new LinkedList();

			for(int i=0;i<m_arFileList.length;i++) {

				try {
					//��Ž - 45110367	[SP] ���� ����: �˻������ ������
					// new File �ϱ��� ���� ���¿� ���� ������ ���� 
					//��Ž - 11835	�������� ��ü�� �ν��Ͻ� ���� ����
					f            = new File( (String)m_arFileList[i] );

					//��Ž - 45110367	[SP] ���� ����: �˻������ ������
					// new File �ϱ��� ���� ���¿� ���� ������ ���� 
					//��Ž - 45110023	[SP] ��� ���丮 ��� ����
					//��Ž - 11835	�������� ��ü�� �ν��Ͻ� ���� ����
					remotePath   = ( new File( m_savePath,f.getName()+".dat") ).getPath();
					//��Ž - 45110190	[SP] ���� �����÷ο�
					//��Ž - 11835	�������� ��ü�� �ν��Ͻ� ���� ����
					byte [] data = new byte[ (int)f.length() ];
					//��Ž - 11835	�������� ��ü�� �ν��Ͻ� ���� ����
					bin          = new DataInputStream(
						new BufferedInputStream(
							//��Ž - 45110367	[SP] ���� ����: �˻������ ������
							// new FileInputStream �ϱ��� ���� ���¿� ���� ������ ���� 
							//��Ž - 45110023	[SP] ��� ���丮 ��� ����	
							new FileInputStream( f.getPath() )
						)
					);
					bin.readFully(data);

					System.out.println("####CLICKSTREAM_THREAD ["+(String)m_arFileList[i]+"]Moving File####");

					//��Ž - 45110367	[SP] ���� ����: �˻������ ������
					//File�� delete�ϱ��� ���� ���¿� ���� ������ ���� 
					f.delete();

					lst.add( f.getName()+".dat" );
					//��Ž - 45110754	[SP]  ������������ ����ó��
				} catch (IOException ignore) {
					//��Ž - 45110497	[SP] �ý��� ������ ���� ����
					//getMessage�� ���� �ý��� �����Ͱ� ���� �� �� ���� 
					System.out.println(ignore.getMessage());
					break;
				} finally {
					//��Ž - 11716	If ���� �߰�ȣ ��� ����  
					//��Ž - 45110754	[SP]  ������������ ����ó��
					if(bin!=null) 
						try { bin.close(); } 
					    catch(Exception ignore){
					    	Syslog.error(ignore);
					    }

					//��Ž - 11729	Null�� �Ҵ�
					//�ʱ�ȭ �κ��� �ƴѵ� null�� �Ҵ��ϰ� �����Ƿ� 
					bin = null;
				}
			}
			//��Ž - 45110571	[SP] �׻� ���� ����
			//��Ž - 11700	�� (Empty) If �� ��� ����
			if( true ) {
			}
		} finally {
			System.out.println("####ClickStreamThread END...####");
		}
	}
}