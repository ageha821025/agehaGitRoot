<%--
 - Copyright : SPECTRA INC.
 - @author : sylee
 - @create date : 2012-05-24
 - @description : QNA Write ������
--%>
<%@page contentType="text/html; charset=euc-kr" %>
<%@ include file="../include/CommonHeader.jsp" %>
<%@ include file="../login/LoginCheck.jsp" %>
<%@page import="java.util.*" %>
<%@page import="spectra.base.web.ParamMap" %>
<%@ page import="spectra.ee.commons.common.model.Attach" %>
<jsp:useBean id="qnaManager" class="spectra.ee.web.qna.service.QnaManager" />
<jsp:useBean id="nodeManager" class="spectra.ee.web.node.service.NodeManager" />
<jsp:useBean id="attachManager" class="spectra.ee.web.common.service.AttachManager" />
<%
	// �α��� check
	if (loginInfo == null)
	{
    	WebUtil.sendRedirect(out, "�α����� �ʿ��մϴ�.", "../login/Login.jsp");
        //��Ž -	11711	���ʿ��� Return �� ��� ����
        //break�� �ǹ̷� ��� 
        return;
    }

	//-------------------------------------------------------------------------

    ParamMap req = new ParamMap(request);

    String strDomainId = req.getString("domainId", WEB_DOMAIN_ID);
    String strNodeId = req.getString("nodeId", WEB_DOMAIN_ID);

    // ÷������ ����
    String strAttachUseFlag = WebConfig.getEnvValue("QNA_CUSTOMER_ATTACH_USE_FLAG");
    // ÷�� ���� ���� Ȯ����
	String strAttachExtList = StringUtil.defaultString(WebConfig.getConfigValue(WebPublic.CFG_SVC_COMMON, "ATTACH_EXT_LIST_WEB"));
	strAttachExtList = strAttachExtList.toUpperCase();
    // ÷�������� ��ü �뷮�� �����ϴ� ������ �ʱ�ȭ�Ѵ�.
    //��Ž -	11816	Long �ʱ�ȭ
    Long lFileSize = new Long(0);
    session.setAttribute("filesize", lFileSize);

    // category nodes
    List topLevelNodeList = nodeManager.selectNodeList(WEB_ROOT_ID,WEB_ROOT_ID,WebPublic.SERVICE_TYPE_QNA);

    // qna info
    ParamMap qna = null;
    List selectedNodePath = null;
    List uploadAttachList = null;

    String nodeNamePath = "";

    String pTxMode = req.getString("txMode", "");
    if ("UPDATE".equals(pTxMode)) {
	    // get Info.
	    ParamMap param = new ParamMap();
	    param.put("domainId", req.getString("domainId", WebConfig.getDomainId()));
	    param.put("qnaId",    req.getString("qnaId"));

	    qna = qnaManager.selectQna(param);
	    
	    String returnPage = "javascript:history.go(-1);";

	    if (qna == null) {
	    	WebUtil.sendRedirect(out, "�������� �ʰų�, �ùٸ��� ���� �����Դϴ�.", returnPage);
	    }
	    
	    if (!strUserId.equals(qna.getString("customer_id"))) {
	    	WebUtil.sendRedirect(out, "���� ������ �����ϴ�.", returnPage);
	    }
	    
	    if (!"ANNOT".equals(qna.getString("answer_status"))) {
	    	WebUtil.sendRedirect(out, "���� �亯 ���̹Ƿ� ���� �� �� �����ϴ�.", returnPage);
	    }

	    // ������ ī�װ� ���
	    nodeNamePath = nodeManager.selectNodeNamePath(qna.getString("qna_node_id"), " > ");
	    strNodeId = qna.getString("qna_node_id");

	    // ���ε� ÷�� ���
		if ("Y".equals(strAttachUseFlag)) {
		    uploadAttachList = attachManager.selectAttachByKbId(qna.getString("qna_id"));
		}

	    // �� �� ó���� ���� �Ϻε����� ����

	    // email
	    String customerEmail = qna.getString("customer_email");
	    if (customerEmail != null) {
	    	String[] emailparts = customerEmail.split("@");
	    	qna.put("customer_email_local",  emailparts[0]);
	    	if (emailparts.length > 1) {
		    	qna.put("customer_email_domain", emailparts[1]);
	    	}
	    }
    }
    else
    {
    	qna = new ParamMap();
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>eNomix Enterprise</title>
		<link rel="stylesheet" type="text/css" href="../css/style.css" media="all" >
		<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="../js/jquery.validate.js"></script>
		<script type="text/javascript" src="../js/json2.js"></script>
		<script type="text/javascript" src="../js/jquery.form.js"></script>
		<script type="text/javascript" src="../js/qnaWrite.js"></script>

		<script type=""text/javascript">
		function setQnaInfo()
		{
<%
		// ī�װ� ��� ���� ����
		if (selectedNodePath != null) {
			StringBuffer sb = new StringBuffer(100);
			for (int i = 0; i < selectedNodePath.size(); i++) {
				ParamMap nodeInfo = (ParamMap)selectedNodePath.get(i);
				sb.append("selectCategory('");
				//��Ž -	11864	��ȿ������ StringBuffer ��� ����
				//+������ ���� 
				sb.append(nodeInfo.getString("node_id"));
				sb.append("',");
				//��Ž -	11864	��ȿ������ StringBuffer ��� ����
				//+������ ����
				sb.append(nodeInfo.getInt("depth"));
				sb.append(");");
			}
		}

		// ÷����������
        if (uploadAttachList != null) {
	        for (int i = 0; i < uploadAttachList.size(); i++) {
				ParamMap attachInfo = (ParamMap)uploadAttachList.get(i);
		        String attachJson = attachInfo.toJsonObj();
		      //��Ž -	76	4ȸ �̻� String Concat ��� ����
				out.println("qnaInfo.attachList["+i+"] = " + attachJson + ";");
	        }
        }
%>
		}
		</script>
		<style type="text/css">
		.uploadProgress { position:relative; width:400px; border: 1px solid #ddd; padding: 1px; border-radius: 3px; }
		.uploadBar { background-color: #B4F5B4; width:0%; height:20px; border-radius: 3px; }
		.uploadPercent { position:absolute; display:inline-block; top:3px; left:48%; }
		</style>
    </head>

	<body>
		<div id="warp">

			<div class="subTitle">
				<h2 class="title">�̸��� ��� <%="UPDATE".equals( pTxMode) ? " ����" : "" %></h2>
				<p class="stl"><font class="font_blue">|</font> 1:1 �¶��� ������� ������ �亯�� �帳�ϴ�.</p>
			</div>

			<div class="category">
				<p class="tt">1:1 ���� ������ �ۼ��� �ּ���.</p>
			</div>

			<!--- �̸��� ��� �Է� : ���� --->
			<p class="height20"></p>

			<div class="form_type">
              <form id="actionForm" name="actionForm" method="post" action="" style="margin:0px;" >
                <input type="hidden" id="txMode" name="txMode" value="<%= pTxMode %>"/>
                <input type="hidden" id="qnaId" name="qnaId" value="<%= qna.getString("qna_id") %>"/>
                <input type="hidden" id="domainId" name="domainId" value="<%=strDomainId%>" />
                <input type="hidden" id="nodeId" name="nodeId" value="<%=strNodeId%>"/>
                <input type="hidden" id="customerNo" name="customerNo"/>
                <input type="hidden" id="customerEmail" name="customerEmail"/>
                <input type="hidden" id="customerId" name="customerId" value="<%= strUserId %>" />
                <input type="hidden" id="questionChannelId" name="questionChannelId" value="CHNL0000000011" /> <!-- ������ �÷��� -->
                <input type="hidden" id="attachCount" name="attachCount" />
                <input type="hidden" id="attach" name="attach" />
                <input type="hidden" id="password" name="password" />
                <input type="hidden" id="logId" name="logId" />
                <input type="hidden" id="surveyDataType" name="surveyDataType" value="JSON"/>
                <input type="hidden" id="attachDataType" name="attachDataType" value="JSON"/>
				<input type="hidden" id="attachListJSON" name="attachListJSON"/>

				<table cellspacing="0" cellpadding="0" class="form_type1" summary="������������" id="inputform">
				<colgroup>
					<col width="150"><col><col width="150"><col>
				</colgroup>
				<tr>
					<th scope="row">�̸�</th>
					<td class="end"><input type="text" name="customerName" id="customerName" style="width:190px;" value="<%= qna.getString("customer_name") %>" class="required" rangelength="[2, 10]"/></td>
				</tr>
				<tr>
					<th scope="row" valign="middle">�������</th>
					<td class="end">
                     <div id="categoryBox">
                        <div id="categoryLayer1">
                        <%
                        	if ("UPDATE".equals(pTxMode)) {
                        %>
                          	<%= nodeNamePath %>
                        <%
                          	}
                        	else {
                            	for (int i=0; i < topLevelNodeList.size(); i++ ) {
                                	ParamMap node = (ParamMap)topLevelNodeList.get(i);
                        %>
                            <input type="radio" class="radio" id="category1_<%=(i+1)%>" name="category1" value="<%=node.getString("node_id")%>"
                                    onclick="selectCategory('<%=node.getString("node_id")%>',<%=node.getString("depth")%>);" /> <%=node.getString("node_name")%>
                        <%
                                }  // end of for loop
                           	}
                        %>
                        </div>
                     </div>
					</td>
				</tr>
				<tr>
					<th scope="row">�̸���</th>
					<td class="end">
						<input type="text" name="customerEmailLocal" style="width:150px;" class="input02" value="<%= qna.getString("customer_email_local") %>"> -
						<select name="emailprovidor" class="select01" style="width:100px;" onchange="this.form.customerEmailDomain.value=this[this.selectedIndex].value;">
							<option value="" selected>�����Է�</option>
							<option value="spectra.co.kr">spectra.co.kr</option>
							<option value="gmail.com">gmail.com</option>
							<option value="naver.com">naver.com</option>
							<option value="daum.net">daum.net</option>
							<option value="nate.com">nate.com</option>
						</select> -
						<input type="text" name="customerEmailDomain" style="width:120px;" class="input02" value="<%= qna.getString("customer_email_domain") %>">
					</td>
				</tr>
				<tr>
					<th scope="row">�޴���ȭ��ȣ</th>
					<td class="end">
						<input type="text" name="customerTel" id="customerTel" style="width:200px;" class="input02" value="<%= qna.getString("customer_tel") %>">
					</td>
				</tr>
				<tr>
					<th scope="row">�ֹι�ȣ</th>
					<td class="end">
						<input type="text" name="customerNoFront" style="width:90px;" class="input02" value="<%= qna.getString("customer_no").length() == 13 ? qna.getString("customer_no").substring(0,6) : "" %>"/> -
						<input type="text" name="customerNoRear"  style="width:90px;" class="input02" value="<%= qna.getString("customer_no").length() == 13 ? qna.getString("customer_no").substring(7) : "" %>"/>
					</td>
				</tr>
				<tr>
					<th scope="row" valign="middle">��������</th>
					<td class="end">
<!-- 					//��Ž -	11759	���ͷ��� �׻� �񱳹��� ���ʿ� ��ġ -->
<!--                    //��Ž - 11748	ȥ�������� 3�� ����                       -->
						<input type="radio" class="radio" name="publicFlag" value="Y"<%= qna.getString("public_flag").equals("Y") ? " checked" : "" %>/> ����
<!--                    //��Ž - 11748	ȥ�������� 3�� ����                       -->
						<input type="radio" class="radio" name="publicFlag" value="N"<%= !"Y".equals(qna.getString("public_flag")) ? " checked" : "" %>/> �����
						<br>
					</td>
				</tr>
				<!-- ========== Survey  ========== -->
				<tbody id="additionalSurveyInfo">
				</tbody>
				<!-- ========== /Survey  ========== -->
				<tr>
					<th scope="row">����</th>
					<td class="end">
						<input type="text" name="questionTitle" id="questionTitle" style="width:600px;" class="input02" value="<%= qna.getString("question_title") %>">

					</td>
				</tr>
				<tr>
					<th scope="row">����</th>
					<td class="end"><textarea name="questionContents" id="questionContents" style="width:607px;height:200px;" class="comment"><%
						List contentsList = (List) qna.get("question_contents");
						if (contentsList != null) {
							for (int i=0; i<contentsList.size(); i++)
		                    {
		                        CaseInsensitiveMap contentsInfo = (CaseInsensitiveMap)contentsList.get(i);
		                        out.print(contentsInfo.get("contents"));
		                    }
						}
					%></textarea>
					</td>
				</tr>
              </form>
<% if ("Y".equals(strAttachUseFlag)) { %>
                <tr>
					<th scope="row">÷������</th>
					<td class="end"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tbody id="attachArea">
                        <tr>
                            <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
							<form name="attachForm" id="attachForm" method="post" enctype="multipart/form-data">
                                <tr>
                                    <td width="80" class="info" style="padding-top:5px;padding-bottom:5px;">
<!--                                     //��Ž -	45110434	[SP] ������ Ÿ���� ������ ������� ���ε� : Upload�޼ҵ带 �� ã�ڴµ�, �� ������ �ƴ��� �ʳ�?? -->
                                        <input type="file" name="attach" id="attach" style="width:400px;height:20px;" value="���ϼ���">
                                        <input type="button" name="btnAddAttach" id="btnAddAttach" value="�����߰�"/>
                                        <div class="uploadProgress" id="uploadProcess"> <div class="uploadBar" id="uploadBar"></div > <div class="uploadPercent" id="uploadPercent">0%</div > </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="info" style="padding-top:5px;padding-bottom:5px;">
                                        <input type="hidden" name="attachText" id="attachText" class="input" style="width:100%" readonly>
                                        <select name="attachList" id="attachList" size="3" style="width:400px;">
                                        <%
                                        	if (uploadAttachList != null) {
                                        		StringBuffer sb = new StringBuffer(100);
                                        		for (int i = 0; i < uploadAttachList.size(); i++) {
							                        ParamMap attachInfo = (ParamMap)uploadAttachList.get(i);
							                        sb.append("<option value='");
							        				//��Ž -	11864	��ȿ������ StringBuffer ��� ����
							        				//+������ ����
							                        sb.append(attachInfo.getString("attachSeq"));
							                        sb.append("'>");
							                        //��Ž -	11864	��ȿ������ StringBuffer ��� ����
							        				//+������ ����
							                        sb.append(attachInfo.getString("attachName"));
							                        sb.append("</option>");
                                        		}
                                        	}
                                        %>
                                        </select>
                                        <input type="button" name="btnDeleteAttach" id="btnDeleteAttach" value="���û���"/>
                                    </td>
                                </tr>
							</form>
                            </table>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
<% } %>
				</table>

				</div>
				<div class="bbsBtn2">
					<ul>
						<li><input type="button" id="writeButton" name="writeButton" value="<%= "UPDATE".equals(pTxMode) ? " �����ϱ�" : "����ϱ�" %>" class="btn_bt02"></li>
						<li><input type="button" id="cancelButton" name="cancelButton" value="<%= "UPDATE".equals(pTxMode) ? " �������" : "������" %>" class="btn_bt02"></li>
					</ul>
				</div>

			</div>
			<!--- : �� --->
		</div>

    </body>
</html>
