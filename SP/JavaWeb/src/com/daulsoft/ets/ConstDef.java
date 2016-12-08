/**
 * @(#)ConstDef.java
 *
 * @version 1.0    2008. 03. 08.  
 * @author Dennis
 * 
 * Copyright 1998-2008 by Daul Soft, Inc. All rights reserved.
 */
package com.daulsoft.ets;

import kr.daulsoft.neotest5.data.CLeftTimeType;
import kr.daulsoft.neotest5.webext.NTInitServlet;

import com.daulsoft.core.env.Config;
import com.daulsoft.core.env.ServerContext;

/**
 * �� ���� ��� class
 */
//��Ž - 162	[����] ���α׷� �ּ��� ������(@author)�� �ݵ�� ���
public class ConstDef{
	
	/* *************************** Project�� �°� �����ؼ� ��� START *****************/

	/* �α��� ���� (COOKIE or SESSION) START */
	
	/** ����� �̸� */
	public static final String LOGIN_KEY_USERNAME = "com.daulsoft.fpexam.username";

	/** ����� ID. */
	public static final String LOGIN_KEY_USERID = "com.daulsoft.fpexam.userid";

	/** ����� Ÿ�� */
	public static final String LOGIN_KEY_USERTP = "com.daulsoft.fpexam.usertp";

	/** NeoTest ����� Ÿ�� */
	public static final String LOGIN_KEY_NT5USRID = "com.daulsoft.fpexam.nt5usrid";
	
	/* ���ǽ� ���� ��Ű/���� ���� �޴����� LMS���� ȣ��Ǵ� ��쿡�� �α��ν� ���� ó�� �� */

	/** ���ǽ� Ű */
	public static final String LOGIN_KEY_LECKEY = "com.daulsoft.fpexam.leckey";

	/** ���ǽ� �� */
	public static final String LOGIN_KEY_LECNM = "com.daulsoft.fpexam.lecnm";

	/** ���� �⵵ */
	public static final String LOGIN_KEY_LECYEAR = "com.daulsoft.fpexam.lecyear";

	/** ���� �б� */
	public static final String LOGIN_KEY_LECTERM = "com.daulsoft.fpexam.lecterm";

	/** ���� �ڵ� */
	public static final String LOGIN_KEY_LECCD = "com.daulsoft.fpexam.leccd";

	/** ���ǽǳ� ����� ���� */
	public static final String LOGIN_KEY_LECMEMTP = "com.daulsoft.fpexam.lecmemtp";
	
	/* �ý��� 2�� �̻� ���� ������ �ϴ� ��� ȣ�� ���� */
	/** ȣ���� ���� �� */
	public static final String LOGIN_KEY_CALLER = "com.daulsoft.fpexam.caller";
	/** ����� APP���� ȣ��� APP URN��. */
	public static final String LOGIN_KEY_MOBILE_APPURN = "com.daulsoft.fpexam.urn";
	
	/* ��ȭ���� */
	/** ���ٿ��� */
	public static final String LOGIN_KEY_DESKJOB_YN = "com.daulsoft.fpexam.deskjobyn";
	/** CM���� */
	public static final String LOGIN_KEY_CM_YN = "com.daulsoft.fpexam.cmyn";
	
	/** �������Ͽ� �����з� �ڵ� [v_lec_cls.cd1] */
	public static final String LEC_CLS_SANGSANG = "4";
	
	/** ����Ʈ ���� **/
	public static final String USER_TP_ADMIN = "M"; // ������
	public static final String USER_TP_OPERATOR = "O"; // ���
	public static final String USER_TP_TEACHER = "T"; // ������
	public static final String USER_TP_ASSIST = "A"; // ����
	public static final String USER_TP_TUTOR = "B"; // Ʃ��
	public static final String USER_TP_STU = "S"; // �л�
	public static final String USER_TP_PARENT = "P"; // �кθ�
	public static final String USER_TP_USER = "U"; // �Ϲ�
	
	/* �з� */	

	/* �����ڵ� */
	/* �׿� ������ ���̼���Ű */
	public static final String NEO_WEBOARD_LICENSE_KEY = (String)((Config)ServerContext.getInstance().get(ServerContext.CTX_CONFIG))
															.getString("/properties/site/WEBOARD_LICENSE");
	
	/* ezSet Key */
		/** jsp/common/proc_i.jsp ȣ��� ���Ǵ� EzSet key�� */
	public static final String EZSET_KEY_PROC = "RESULT_PROC";

		/** jsp/common/excel.jsp ȣ��� ���Ǵ� EzSet key�� */
	public static final String EZSET_KEY_EXCEL = "RESULT_EXCEL";
	
		/** �з�ü�輱�� �˾� ȣ��� ���Ǵ� EzSet key�� */
	public static final String EZSET_KEY_CLSCD = "POPUP_CLSCD";
	
		/** �����ڵ弱�� �˾� ȣ��� ���Ǵ� EzSet key�� */
	public static final String EZSET_KEY_CODE = "POPUP_CODE";
	
	/* LMS ���� ���� */
		/** ������ ���� ��� Dummy ���ǽ� Ű */
	public static final String DUMMY_LEC_KEY = "LecKey";
		/** ������ ���� ��� Dummy ���ǽ� �ڵ� */
	public static final String DUMMY_LEC_CD = "LecCd";
		/** ������ ���� ��� Dummy ���ǽ� �⵵ */
	public static final String DUMMY_LEC_YEAR = "9999";
		/** ������ ���� ��� Dummy ���ǽ� �б�/��� */
	public static final String DUMMY_LEC_TERM = "9";
		/** ������ ���� ��� Dummy ���ǽ� ���� ���� */
	public static final String DUMMY_LEC_EXAM_WEEK = "9";

	public static final String CURR_LEC_KEY = "currLecKey";
	public static final String CURR_LEC_CD = "currLecCd";
	public static final String CURR_LEC_YEAR = "currLecYear";
	public static final String CURR_LEC_TERM = "currLecTerm";
	public static final String CURR_LEC_EXAM_WEEK = "currLecExamWeek";

		/** ���ǽ� ���� �ڵ� - ��� */
	public static final String LEC_STATUS_ON = "1";
		/** ���ǽ� ��� ���� �ڵ� - ���� */
	public static final String LEC_MEM_STATUS_ON = "1";
		/** ����� ���� �ڵ� - ���� */
	public static final String LEC_USR_STATUS_ON = "1";

	/* *************************** Project�� �°� �����ؼ� ��� END *****************/
	
	/* �� LAYOUT ���� START */
    /** �ܹ��� ���� ��ü Layout */
	public static final String PRNT_LAYOUT_QST = "<LayoutItem bVisibleBody=\"Y\" iSpacingPiece=\"56\" Type=\"LayoutPaperOneQuestion\"><Border bDraw=\"N\" color=\"16777215\" /><BackGround bDraw=\"N\" color=\"16777215\" /><Padding l=\"566\" r=\"566\" t=\"566\" b=\"566\" /><pt x=\"0\" y=\"0\" /><sz w=\"10773\" h=\"8471\" /><SizeInfo iColumnCount=\"1\" iSpacingPage=\"283\" iSpacingPiece=\"56\" iWidthQuestionNumber=\"396\" iWidthQuestionAnswerNumber=\"340\"><SizePage w=\"11905\" h=\"16837\" /><PaddingPage l=\"566\" r=\"566\" t=\"566\" b=\"566\" /><PaddingColumn l=\"283\" r=\"283\" t=\"283\" b=\"283\" /></SizeInfo><LayoutPaperItemSet>@QST_LAYOUT</LayoutPaperItemSet></LayoutItem>";
    /** �ܹ��� ���� - ���� Layout */
    public static final String PRNT_APY_ITEM_QST = "<LayoutItem bVisibleBody=\"Y\" iSpacingPiece=\"56\" iWidthQuestionNumber=\"396\" iWidthQuestionAnswerNumber=\"340\" bVisibleHint=\"N\" bVisibleComment=\"N\" bVisibleUserAnswer=\"Y\" bVisibleCorrectAnswer=\"N\" bVisibleScore=\"N\" bVisiblePoint=\"Y\"><Border bDraw=\"N\" color=\"16777215\" /><BackGround bDraw=\"N\" color=\"16777215\" /><Padding l=\"56\" r=\"56\" t=\"56\" b=\"283\" /><pt x=\"0\" y=\"0\" /><sz w=\"10095\" h=\"2358\" /></LayoutItem>";
    /** �ܹ��� ��� - ���� Layout */
    public static final String PRNT_RET_ITEM_QST = "<LayoutItem bVisibleBody=\"Y\" iSpacingPiece=\"56\" iWidthQuestionNumber=\"396\" iWidthQuestionAnswerNumber=\"340\" bVisibleHint=\"N\" bVisibleComment=\"Y\" bVisibleUserAnswer=\"Y\" bVisibleCorrectAnswer=\"Y\" bVisibleScore=\"Y\" bVisiblePoint=\"Y\"><Border bDraw=\"N\" color=\"16777215\" /><BackGround bDraw=\"N\" color=\"16777215\" /><Padding l=\"56\" r=\"56\" t=\"56\" b=\"283\" /><pt x=\"0\" y=\"0\" /><sz w=\"10095\" h=\"2358\" /></LayoutItem>";
    /** �ܹ��� ���Ǹ� */
    public static final String PRNT_TP_NM_QST = "�ܹ���";
    /** �ܹ��� ���� ���Ǹ� */
    public static final String PRNT_TP_NM_QST_APY = "�ܹ�������";
    /** �ܹ��� ��� ���Ǹ� */
    public static final String PRNT_TP_NM_QST_RET = "�ܹ��װ��";
    
    /** ������ ��ü ���� Layout */
	public static final String PRNT_LAYOUT_PAP = "<LayoutItem bVisibleBody=\"Y\" iSpacingPiece=\"56\" ePageNumberPosition=\"PNP_BOTTOM_CENTER\" Type=\"LayoutPaperPages\" iHeader1Height=\"1020\" iHeader2Height=\"340\" iFooterHeight=\"680\"><Border bDraw=\"N\" color=\"16777215\" /><BackGround bDraw=\"N\" color=\"16777215\" /><Padding l=\"0\" r=\"0\" t=\"0\" b=\"0\" /><pt x=\"0\" y=\"0\" /><sz w=\"11905\" h=\"16893\" /><SizeInfo iColumnCount=\"2\" iSpacingPage=\"283\" iSpacingPiece=\"56\" iWidthQuestionNumber=\"396\" iWidthQuestionAnswerNumber=\"340\"><SizePage w=\"11905\" h=\"16837\" /><PaddingPage l=\"564\" r=\"564\" t=\"564\" b=\"564\" /><PaddingColumn l=\"283\" r=\"283\" t=\"283\" b=\"283\" /></SizeInfo><LayoutPaperItemSet Type=\"LayoutPaperItemSet\"><Border bDraw=\"N\" color=\"16777215\" /><BackGround bDraw=\"N\" color=\"16777215\" /><Padding l=\"0\" r=\"0\" t=\"0\" b=\"0\" /><pt x=\"0\" y=\"0\" /><sz w=\"4822\" h=\"23841\" />@QST_LAYOUT</LayoutPaperItemSet></LayoutItem>";
    /** ������ ���� - ���� Layout */
    public static final String PRNT_APY_ITEM_PAP = "<LayoutItem bVisibleBody=\"Y\" iSpacingPiece=\"56\" iWidthQuestionNumber=\"396\" iWidthQuestionAnswerNumber=\"340\" bVisibleHint=\"N\" bVisibleComment=\"N\" bVisibleUserAnswer=\"Y\" bVisibleCorrectAnswer=\"N\" bVisibleScore=\"N\" bVisiblePoint=\"N\"><Border bDraw=\"N\" color=\"16777215\" /><BackGround bDraw=\"N\" color=\"16777215\" /><Padding l=\"56\" r=\"56\" t=\"56\" b=\"283\" /><pt x=\"0\" y=\"1237\" /><sz w=\"4710\" h=\"898\" /></LayoutItem>";
    /** ������ ��� - ���� Layout */
    public static final String PRNT_RET_ITEM_PAP = "<LayoutItem bVisibleBody=\"Y\" iSpacingPiece=\"56\" iWidthQuestionNumber=\"396\" iWidthQuestionAnswerNumber=\"340\" bVisibleHint=\"Y\" bVisibleComment=\"Y\" bVisibleUserAnswer=\"Y\" bVisibleCorrectAnswer=\"Y\" bVisibleScore=\"Y\" bVisiblePoint=\"Y\"><Border bDraw=\"N\" color=\"16777215\" /><BackGround bDraw=\"N\" color=\"16777215\" /><Padding l=\"56\" r=\"56\" t=\"56\" b=\"222\" /><pt x=\"0\" y=\"0\" /><sz w=\"4708\" h=\"2942\" /></LayoutItem>";
    /** ������ ���Ǹ� */
    public static final String PRNT_TP_NM_PAP = "������";
    /** ������ ���� ���Ǹ� */
    public static final String PRNT_TP_NM_PAP_APY = "����������";
    /** ������ ��� ���Ǹ� */
    public static final String PRNT_TP_NM_PAP_RET = "���������";
	/* �� LAYOUT ���� END */

	public static final int POPUP_LINE_COUNT = 10;
	public static final int POPUP_PAGE_COUNT = 10;
	public static final int DEFAULT_LINE_COUNT = 10;
	public static final int DEFAULT_PAGE_COUNT = 10;

	public static final String DEFAULT_SCR_ROW = "20";	// �Խù��� ����Ʈ �ڽ� �⺻��
	
	/* Blowfish ��ȣȭ key */
	public static final String XML_ENCRYPT_KEY = "neotest5";

	/** ���� relay(message) ������ <br> value : /jsp/common/relay.jsp */
	public static final String RELAY_PAGE = "/daul/jsp/common/relay.jsp";

	/** Layout ������ */
	public static final String FORWARD_PAGE_USER = "/daul/jsp/layoutPopup.jsp";
	public static final String FORWARD_PAGE_ADMIN = "/daul/jsp/layoutAdmin.jsp";
	public static final String FORWARD_PAGE_POPUP = "/daul/jsp/layoutPopup.jsp";

	public static final String FORWARD_DEMO = "/daul/jsp/demo/layout.jsp";
	public static final String FORWARD_DEMO_POPUP = "/daul/jsp/demo/popup/layoutPopup.jsp";

	/* �ڵ�Ŵ��� �׷� ��. SystemSQL.xml�� ���ǵǾ� �ִ� pattern name�Ӽ���. */
	public static final String CODE_GROUP_NAME = "code";
	public static final String COMM_CD = "comm_cd";

	/** ���� Ÿ�Ӿƿ� <br> value : 60 [��] */
	public static final int SESSION_TIMEOUT = 60;

	/** request �⺻ parameters. input�ʵ�� �ѱ��� �ʾƵ� request�� �⺻������ �����Ǵ� parameter key��. */
	//���� - 156	[����] ��� ������ �̸��� �빮�ڷ� �����ؾ� ��
	public static final String defaultReqData = ",screen,javax.servlet.forward.request_uri,javax.servlet.forward.context_path,javax.servlet.forward.servlet_path,";
	
	/* *************************************** ���� NT5���� ��� ************************************/

	/* �����ð� üũ ��� */
	public static final String LFT_TIME_TP_USED = CLeftTimeType.ELeftTimeType.Used; // ����� �ð� ��ŭ�� ����.
	public static final String LFT_TIME_TP_ABSOLUTE = CLeftTimeType.ELeftTimeType.Absolute; // ���� �����ϰ� ����ð� �������� ����.

	/* NT5 Config File Path */
	public static final String NT5_CONFIG_XML_PATH = NTInitServlet.getConfigFilePath();
	
	/* ���۵��� �⺻ ������ ���� */
	public static final String SESS_SESSIONKEY = "12_admin_admin";

	/* ITEM ���� */
	public static final String ITEM_QST = "Q";
	public static final String ITEM_QST_MULTI = "QM";
	public static final String ITEM_QST_SHORT = "QS";
	public static final String ITEM_QST_ESSAY = "QE";
	public static final String ITEM_PAPER = "P";
	public static final String ITEM_EXAM = "E";
	public static final String ITEM_EXAMSET = "ES";
	public static final String ITEM_STUDYBOOK = "SB";
	public static final String ITEM_SUMMARY = "S";
	public static final String ITEM_PSG = "PSG";
	
	/* �׷��� ���� ������ ���� ���� �⺻ ���� */
	public static final String FLD_GRP_PERM = "FP0001,FP0002,FP0003,FP0004,FP0005,FP0007,FP0009,FP0010";

	/* ����� ���� */
	public static final String RIGHT_LOGIN = "UP0001";
	public static final String RIGHT_SYSTEM_MGT = "UP0002";
	public static final String RIGHT_CATEGORY_MGT = "UP0003";
	public static final String RIGHT_USER_MGT = "UP0004";
	public static final String RIGHT_AUTHORING = "UP0005";
	public static final String RIGHT_AUTHORING_PLAN = "UP0006";
	public static final String RIGHT_SUPERVISION = "UP0007";
	public static final String RIGHT_SUPERVISION_PLAN = "UP0008";
	public static final String RIGHT_AUTH_EXAM = "UP0009";
	public static final String RIGHT_EXAM_SCORE_MGT = "UP0010";
	public static final String RIGHT_EXAM_STATISTICS = "UP0011";
	public static final String RIGHT_EXAM_APPLY = "UP0012";

	/* ä�� ���ν��� �ι�° ���ڰ� */
	public static final String SCORE_PROC_SHORT = "SHORT";
	public static final String SCORE_PROC_LONG = "LONG";

	/* ��¥ ���� �⺻ ���� ���� */
	public static final String BOARD_DEFAULT_END_DM = "2099-12-31";
	public static final String BOARD_DEFAULT_END_DTM = "2099-12-31 11:59:59";
	
	/* �ڷ����*/
	public static final String PERM_CD_ONE  = "IP0001"; 
	public static final String PERM_CD_TWO  = "IP0002"; 
	
	/* ���� ����. NT5_EXAM_ANS.APY_EXAM_STAT */
	public static final String EXAMINATION_NONE = "N"; 		//������
	public static final String EXAMINATION_ING = "A"; 		//������
	public static final String EXAMINATION_OK = "F"; 		//����
	public static final String EXAMINATION_RESULT_ING = "E";//ä����
	public static final String EXAMINATION_RESULT_OK = "S";	//ä���Ϸ�	
	
	/* ���� CLASSȣ�� ��� �ڵ�, �޽��� Key */
	public static final String	AR_RESULT_OBJECE_KEY		= "RET";
	public static final String	AR_RESPONSE_MESSAGE_KEY		= "RESPONSE_MESSAGE_KEY";
	
	/* �����ʱ�ȭ�� ��ȣ ���� */
	public static final String EXAM_INIT_PWD = "exam";
}

