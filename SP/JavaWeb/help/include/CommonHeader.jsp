<%@ page import="java.util.Locale" %>

<%@ page import="java.util.*" %>
<%@ page import="spectra.base.*" %>
<%@ page import="spectra.base.util.*" %>
<%@ page import="spectra.base.web.*" %>
<%@ page import="spectra.base.sql.ibatis.*" %>
<%@ page import="spectra.base.web.template.*" %>
<%@ page import="spectra.base.i18n.*" %>
<%@ page import="spectra.ee.*" %>
<%@ page import="spectra.ee.web.*" %>
<%@ page import="spectra.ee.web.common.service.*" %>
<%@ page import="spectra.ee.web.common.model.*" %>
<%@ page import="spectra.ee.web.kb.model.*" %>
<%@ page import="spectra.ee.web.template.*" %>
<%@ page import="spectra.ee.commons.search.model.*" %>
<%@ page import="spectra.base.licensemanager.Encrypt" %>
<%@ page import="spectra.ee.commons.qna.model.*" %>
<%@ page import="org.apache.commons.collections.map.CaseInsensitiveMap" %>

<%
    //��Ž -	4306	Catch �� ���� ��ó�� Exception ���� 
    request.setCharacterEncoding("euc-kr");
    
	/* �������� �ƴ� ī�װ�ID�� �ֻ��� ī�װ��� �� ��츦 ����� WEB_ROOT_ID �߰�
	       ����Ʈ�� ������ ���̵�������, ī�װ��� ������ �� ���� ī�װ� ID�� �־��־�� �� */

	//��Ž -	11734	���� ������ ���� Final ��� ���� 
	final String WEB_ROOT_ID = WebConfig.getDomainId(); //"NODE0000000028";
    String WEB_DOMAIN_ID = WebConfig.getDomainId();
    String FAQ_PAGE_SORT_TYPE = WebConfig.getEnvValue("FAQ_PAGE_SORT_TYPE");
    String URL_WEBAPPS = WebConfig.getConfigValue("COMMON", "URL_WEBAPPS");
    String WEB_SEARCH_VOLUME = WebConfig.getEnvValue("COM_SEARCH_VOLUME");

	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires",0);   
	if ("HTTP/1.1".equals(request.getProtocol()))  
	{
		response.setHeader("Cache-Control", "no-cache"); 
	}
%>

<%!
    protected String getPageNavigator(int totalCount, int rowsPerPage, int pageNo) 
    {
        BoardNavigatorBase nav = new BoardNavigatorBase(totalCount, rowsPerPage, pageNo);
        nav.setToFirstSymbol("<img src=\"../images/btn_pg1_l.gif\" alt=\"ó��\">");
        nav.setPreviousSymbol("<img src=\"../images/btn_pg2_l.gif\" alt=\"����\">");
        nav.setCurrHighlight("<b>", "</b>");                        // Ȱ��ȭ ��ȣ ���� �±� 
      	nav.setForwardSymbol( "<img src=\"../images/btn_pg2_r.gif\" alt=\"����\">");
      	nav.setToEndSymbol( "<img src=\"../images/btn_pg1_r.gif\" alt=\"������\">");
        nav.setUnitCount(10);
        //��Ž -	11841	String �߰� �� StringBuffer ��� (2�� �ɸ���, �ּ��������� �̵���)
        nav.setStyle("style=\"padding:3px;\"");                     // [ó��,����,����,��] �� ���� ��Ÿ�� 

        
        String pageNavigator = "<div class=\"paginate\">";
        pageNavigator+=nav.getLinkHtmlByTable(true, true, true, null);
        pageNavigator+="</div>";
        return pageNavigator;
    }
    
    protected int getPageCount(int totalCount, int rowsPerPage)  
    {
        int pageCount = totalCount / rowsPerPage + 1 ;
        return pageCount;
    }
    
%>

