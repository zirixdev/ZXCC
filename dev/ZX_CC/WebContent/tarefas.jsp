<!DOCTYPE html>
<!--
ZIRIX CONTROL CENTER - MAIN PAGE
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: HTML5, JAVASCRIPT E JSP
-->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="zirix.zxcc.server.*,zirix.zxcc.server.dao.*,java.sql.SQLException,java.util.Vector,zirix.zxcc.server.mock.*,zirix.zxcc.server.mock.dao.*" %>

<%
	String[] pkVal = {request.getParameter("COD_USUARIO")};
	MockScheduleBean bean = new MockScheduleBean(pkVal);
%>
<html lang="pt-br">  
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="css/button.css">
		<title>ZX CC</title>
	</head>
	<body>        
        <header>
       		<h3 align="center">TAREFAS</h3>
       		<div id="cod_usuario" style="visibility: hidden;"><%=pkVal[0]%></div>
        </header>
		<%Vector<String[]> workList = bean.getWork();
		for (int i=0;i < workList.size();i++) {%>
			<%Vector<String[]> workServiceList = bean.getWorkService(workList.elementAt(i)[1].trim(),workList.elementAt(i)[2].trim());%> 
			<%=workList.elementAt(i)[2].trim()%><br>
			<div id="work_<%=workList.elementAt(i)[0].trim()%>" onclick="SCHED_WORK_START_FUNCTION('<%=workList.elementAt(i)[0].trim()%>','<%=workServiceList.elementAt(0)[0].trim()%>','<%=workList.elementAt(i)[4].trim()%>')">
				<%=workList.elementAt(i)[1].trim()%>
				<%if(workList.elementAt(i)[3].trim().compareTo("0") != 0) {
					String[] cod_usuario = new String[1];
					cod_usuario[0] = workList.elementAt(i)[3].trim();
					ZxAccessControlBean beanAccess = new ZxAccessControlBean(cod_usuario);
				%> - Iniciado por <%=beanAccess.getNomeUsuario()%><%}%>
			</div>
			<canvas id="myCanvas_<%=workList.elementAt(i)[0].trim()%>" width="495" height="1" style="border:0px;"></canvas>
			<script>var c = document.getElementById("myCanvas_<%=workList.elementAt(i)[0].trim()%>");
			var ctx = c.getContext("2d");
			ctx.moveTo(0,0);
			ctx.lineTo(495,0);
			ctx.stroke();</script>
		<%}%>
        <script src="js/jquery.js"></script>
        <script src="js/modernizr.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/menu_functions.js"></script>
        <script src="js/page_functions.js"></script>
	</body>
</html>