<%--
 - Copyright : SPECTRA INC.
 - @author : yshwang
 - @create date : 2012-05-23
 - @description : keyword Search List page
--%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page contentType="text/html; charset=euc-kr" %>
<%@ include file="../include/CommonHeader.jsp" %>
<%@ include file="../login/LoginCheck.jsp" %>
<%@page import="java.util.*" %>
<%@page import="spectra.base.web.ParamMap" %>
<%@page import="spectra.ee.web.template.BoardNavigatorByCount"%>
<%@page import="net.sf.json.*"%>
<%@page import="java.util.logging.*"%>
<jsp:useBean id="kbManager" class="spectra.ee.web.kb.service.KbManager" />
<jsp:useBean id="nodeManager" class="spectra.ee.web.node.service.NodeManager" />
<jsp:useBean id="replyManager" class="spectra.ee.web.common.service.ReplyManager" />

<%
	ParamMap req = new ParamMap(request);

	int pageNo = req.getInt("pageNo", 1);  															// ������ ��ȣ
	String paramParentNodeId = req.getString("parentNodeId", "");										// 1�ܰ� ī�װ�
	String paramNodeId = req.getString("nodeId", "");													// 2�ܰ� ī�װ�
	String paramNodeIdPath = req.getString("nodeIdPath", "");											// 2�ܰ� ī�װ� (���� �˻� ����)
	String paramQuestion = req.getString("question", "");												// �˻���
	String paramIsSearchLog = req.getString("isSearchLog", "true");										// �˻� �α׸� ���� ������ ����
	String paramIsKeywordLog = req.getString("isKeywordLog", "true");									// Ű���� �α׸� ���� ������ ����
	String paramLogId = req.getString("log_id", Util.getGuid());										// �α�ID
	int rowsPerPage = Util.str2i(WebConfig.getEnvValue("FAQ_PAGE_ROW_COUNT"), 10);						// �� ������ �� ����Ʈ ����

	// �ʿ�� ��� ����
    //int searchResultCount = Util.str2i(WebConfig.getEnvValue("FAQ_SEARCH_RESULT_COUNT"), 10);			// �ִ� �˻���
    //String serviceType = req.getParam("serviceType", "SVFAQ");										// ���� Ÿ�� , FAQ or RAR
    //String surveyType = req.getParam("surveyType", "SCHAF");											// ���� Ÿ��

    if (paramQuestion == null || paramQuestion.trim().length() == 0)
    {
        WebUtil.sendRedirect(out, "�˻�� �����ϴ�.", "../faqList.jsp");
      //��Ž -	11711	���ʿ��� Return �� ��� ����
      //break�� �ǹ̷� ��� 
        return;
    }

    // ī�װ� �˻��� �� ���, �ش� ī�װ� ���̵��� �̸��� ������ �´�.
    String parentNodeName = "���հ˻�";

    if (paramParentNodeId != null && paramParentNodeId.length() > 0)
    {
    	ParamMap map = nodeManager.selectByPK(paramParentNodeId);
    	parentNodeName = map.getString("node_name");
    }

    String question = null;
    try
    {
        question = java.net.URLDecoder.decode(paramQuestion, "UTF-8");
    }
    catch(UnsupportedEncodingException e)
    {
    	Logger.getLogger(e.toString());
    }

    // *** �˻��� ���� �Ķ���� �� ���� start
    ParamMap searchMap = new ParamMap();
    /** �˻� ����
    	0, �˻������� ����� ����, �±�(����Ű����)�� �����ؼ� ��� �˻��Ѵ�.
		1, �˻������� ����� ���� ��� �˻��Ѵ�.
 		2, �˻������� ������ �˻��Ѵ�.
		3, �˻������� ���븸�� �˻��Ѵ�.
		4, �˻������� ����� ����, �±�(����Ű����), ÷�������� �����ؼ� ��� �˻��Ѵ�.
	*/
	searchMap.put("searchRange", "0");
    /** �˻���� Ÿ��
    	1, SearchKb, SearchRawKb �� ��� �ʵ忡 ���� �����͸� ��Ƽ� �����Ѵ�.
  		2, SearchKb, SearchRawKb �� ��� �ʵ忡 ���� �����͸� ��Ƽ� ����������<br>
    		����� ������ ������ ��Ģ�� ���� �Ϻ� �����ͱ����� �����Ѵ�.
    	11, SearchKb(kb_id, node_id, title, contents, node_path) SearchRawKb(qna_id,
    		node_id, question_title, question_contents, answer_title, answer_contents,
    		node_path) ���� ���� �迭�� �����Ѵ�.
    	12, SearchKb(kb_id, node_id, title, contents, node_path) SearchRawKb(qna_id,
    		node_id, question_title, question_contents, answer_title, answer_contents,
    		node_path) ���� ���� �迭�� �����Ѵ�. ����� ������ ������ ��Ģ�� ���� �Ϻ� �����ͱ����� �����Ѵ�.
    	21, SearchKb(kb_id, node_id, title, node_path) SearchRawKb(qna_id, node_id,
    		question_title, answer_title, node_path) ���� ���� �迭�� �����Ѵ�.
  	*/
  	searchMap.put("searchReturnType", "1");
	// �˻� ī�װ�
	if( StringUtil.isNotBlank(paramNodeId) )
	{
		searchMap.put("nodeId", paramNodeId);
	}
	else
	{
		searchMap.put("nodeIdPath", paramNodeIdPath);
	}
	// FAQ ��  ���Ŀ� ���� ���� Ÿ�� �÷���
	searchMap.put("serviceType", "SVFAQ");
	// �˻� �α� ���� flag
	searchMap.put("isSearchLog", paramIsSearchLog);
	// Ű���� �α� ���� flag
	searchMap.put("isKeywordLog", paramIsKeywordLog);
	// �˻� ����� �ִ� ��쿡�� ��ȸ���� ��������
	searchMap.put("isSearchHitCount", "true");
	// �˻����� Volume (�⺻������ v01)
	searchMap.put("volume", "v01");
	// �ڵ��ϼ���ɿ��� �Է��� �ܾ��� ��ġ�� ��Ÿ����. 0 : �պκ�, 1 : �޺κ� : 2 : �� �Ǵ� �޺κ�.
	searchMap.put("flag", "2");
	// �˻��� ���� Ű����
	searchMap.put("question", paramQuestion);
	// �˻� ������ NO
	searchMap.put("pageNo", Integer.toString(pageNo));
	// �˻� ���� ���ڵ� (�ѱ۰���)
	// "a2k" ascii(8859_1) -> ksc(KSC5601);
	// "a2u" ascii(8859_1) -> UTF-8;
	// "k2a", "u2a", "u2k", "k2u"
    // ������ ���� ������ �ٽ� ���־�� ��.
	searchMap.put("langFlag", "u2u");
	// �˻� �α� (ex: -4ce3768f:137723d1032:-7ff9133765151)
	searchMap.put("searchLogId", paramLogId);
	//*** �˻��� ���� �Ķ���� �� ���� end

	// �˻� ����
	int startNo = 0;
	int totalCount = 0;
	String searchLogId = "";
	String keyword = "";
	String keywordLogId = "";
	ArrayList rarList = null;

	ParamMap resultMap = null;

	try
	{
		resultMap = kbManager.searchKb(searchMap);
	}
	//��Ž -	45110754	[SP]  ������������ ����ó��
	catch(Exception e)
	{
		Logger.getLogger(e.toString());
	}

	if(resultMap != null && "SUCCESS".equals((String)resultMap.get("RESULT")))
	{
 		startNo = resultMap.getInt("START_NO");
		totalCount = resultMap.getInt("TOTAL_COUNT");

		searchLogId = resultMap.getString("SEARCH_LOG_ID");
		keyword = resultMap.getString("KEYWORD");
		keywordLogId = resultMap.getString("KEYWORD_LOG_ID");

		rarList = (ArrayList)resultMap.get("LIST");
	}

%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" >
<title>eNomix Enterprise</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" media="all" >
<script language="JavaScript" type="text/JavaScript" src="../js/jquery-1.7.2.min.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/rarList.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/rar.js"></script>
<script language="javascript">
/**
 * jquery, init
 */
$(document).ready(function(){
	$("#textQuestion").val("<%=question%>").focus();
});
</script>
</head>

<body>
	<div id="warp">
		<div class="subTitle">
			<h2 class="title">FAQ</h2>
			<p class="stl"><font class="font_blue">|</font> ���� �����Ͻô� ������ �亯�Դϴ�.</p>
		</div>

		<%@ include file="./rarForm.jsp" %>

		<div class="category">
			<p class="st"><font class="font_blue">Total</font> : <strong><%=totalCount%></strong> ��</p>
		</div>

		<!--- RAR ����Ʈ : ���� --->
		<p class="mgb20"></p>
			<div class="boardtype_list02">
				<table cellspacing="0" class="boardtype2" summary="�Խ����� ������ ����Ʈ">
				<caption>�Խ��� ����Ʈ</caption>
				<colgroup>
					<col width="5%">
					<col width="15%">
					<col width="5%">
				</colgroup>
				<thead>
				<tr>
					<th scope="col">��ȣ</th>
					<th scope="col">����</th>
					<th scope="col">��ȸ��</th>
				</tr>
				</thead>
				<tbody>
<%


/*
	//json control source -s-
  	if(!"FAIL".equals(jsResult) && jsResult != null)
	{
			for (Iterator i = ja.iterator(); i.hasNext();)
			{ 	  jsStartNo++;
			      JSONObject ob = (JSONObject) i.next();
			      String strKbId = StringUtil.defaultString(ob.getString("KB_ID"), "");
			      out.println("<tr><td>"+jsStartNo+"</td>");
			      String strTitle = "<td class=\"title\"><a href=\"#\" onclick=\"faqClick('"+StringUtil.defaultString(ob.getString("KB_ID"), "")+"')\">"+StringUtil.defaultString(ob.getString("TITLE"), "")+"</a></td>";
			      out.println(strTitle);
			      out.println("<td>"+StringUtil.defaultString(ob.getString("HIT_COUNT"), "0")+"</td></tr>");

			}
	}
	//json control source -e-
*/

	if (rarList != null && rarList.size() > 0)
	{
		StringBuffer sb = new StringBuffer(100);
		ParamMap replyParam = new ParamMap();
		for(int i=0; i < rarList.size() ; i++)
		{
			Map kbMap = (Map)rarList.get(i);
			startNo++;

			out.println("<tr><td>"+startNo+"</td>");

			/*
			   ��� ī��Ʈ ���
			*/
			replyParam.put("kbId", (String)kbMap.get("KB_ID"));
			replyParam.put("serviceType", "SVFAQ");

			String replyCount = (String)replyManager.selectReplyCount(replyParam);
			if(!"0".equals(replyCount))
			{
				replyCount = " ["+replyCount+"]";
			}
			//��Ž -	11700	�� (Empty) If �� ��� ����
			else
			{
// 				replyCount ="";
			}
			sb.append("<td class=\"title\"><a href=\"javascript:;\" onclick=\"faqClick('");
			//��Ž -	11864	��ȿ������ StringBuffer ��� ����
			//+������ ���� 
			sb.append(StringUtil.defaultString((String)kbMap.get("KB_ID"), ""));
			sb.append("')\">");
			//��Ž -	11864	��ȿ������ StringBuffer ��� ����
			//+������ ���� 
			sb.append(StringUtil.defaultString((String)kbMap.get("TITLE"), ""));
			sb.append(replyCount);
			sb.append("</a></td>");

			out.println(sb.toString());
			out.println("<td>"+kbMap.get("HIT_COUNT")+"</td></tr>");
	    }
	}
	else
    {
          out.println("<tr><td></td><td>�˻� ����� �����ϴ�.</td><td></td></tr>");
    }
%>
				</tbody>
				</table>
				<p class="bline"></p>
			</div>
			<div class="paginate">
<%
    if (rarList != null && rarList.size() > 0)
    {
  		BoardNavigatorByCount nav = new BoardNavigatorByCount(totalCount, rowsPerPage, pageNo);
 	    nav.setToFirstSymbol("<img src=\"../images/btn_pg1_l.gif\" alt=\"ó��\">");
 	    nav.setPreviousSymbol("<img src=\"../images/btn_pg2_l.gif\" alt=\"����\">");
 	    nav.setCurrHighlight("<strong class=\"first-child\">", "</strong>");                        // Ȱ��ȭ ��ȣ ���� �±�
 	    nav.setForwardSymbol("<img src=\"../images/btn_pg2_r.gif\" alt=\"����\">");
 	    nav.setToEndSymbol("<img src=\"../images/btn_pg1_r.gif\" alt=\"��\">");
 	    nav.setUnitCount(10);
   	    out.println(nav.getLinkHtmlByTable(true, true, false, null));
    }
%>
			</div>
			<!--- RAR ����Ʈ : �� --->
		</div>
<!-- 	//��Ž -	45110352	[SP] ����Ʈ �� ��û ����	 -->
	<form name="listForm" id="listForm" method="get" action="rarList.jsp">
	  <input type="hidden" name="pageNo" id="pageNo" value="<%=pageNo%>" />
	  <input type="hidden" name="question" id="question" value="<%=paramQuestion%>" />
	  <input type="hidden" name="parentNodeId" id="parentNodeId" value="<%=paramParentNodeId%>" />
	  <input type="hidden" name="nodeId" id="nodeId" value="<%=paramNodeId%>" />
	  <input type="hidden" name="nodeIdPath" id="nodeIdPath" value="<%=paramNodeIdPath%>" />
	  <input type="hidden" name="kbId" id="kbId"/>
	  <input type="hidden" name="surveyType" id="surveyType" value="SCHAF" />
	  <input type="hidden" name="rarTotalCount" id="rarTotalCount" />
	  <input type="hidden" name="log_id" id="log_id" value="<%=paramLogId%>" id="log_id" />
	  <input type="hidden" name="isSearchLog" id="isSearchLog" value="false" />
	  <input type="hidden" name="isKeywordLog" id="isKeywordLog" value="false" />
	</form>
    </body>
</html>
