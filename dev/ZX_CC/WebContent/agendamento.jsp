<!--
ZIRIX CONTROL CENTER - CADASTRO DE SIM CARD
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: HTML5, JAVASCRIPT E JSP
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="zirix.zxcc.server.*,zirix.zxcc.server.dao.*,java.sql.SQLException,java.util.Vector" %>

<%
	String[] PK_OBJ = {request.getParameter("PK_OBJ")};
	String WORK_ID = request.getParameter("WORK_ID");
	String[] COD_USUARIO = {request.getParameter("COD_USUARIO")};
	String AREA = request.getParameter("AREA");
	NovoPedidoServiceBean beanPedido = new NovoPedidoServiceBean(PK_OBJ);
	String[] pkCodCliente = {beanPedido.getCodCliente()};
	ClienteServiceBean beanCliente = new ClienteServiceBean(pkCodCliente);
%>

<!--Operacional -> Agendamento-->
<div id="operacional-agendamento-content">
    <form class="outer_form">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#aba_agendamento" data-toggle="tab">Agendamento</a></li>
            <li><a href="#aba_observacoes" data-toggle="tab">Observações</a></li>
        </ul>   
        <div class="tab-content"> 
            <div class="tab-pane active" id="aba_agendamento">
	            <fieldset class="field">
				<legend><b>Agendamento:</b></legend>
					<b>Cliente:</b> <%=beanCliente.getNome().trim()%>
					<br><b>Endereço de instalação igual do pedido?</b><select id="end_inst" class="size_15" onchange="muda_endereco_function()">
						<option value="sim" selected="selected"><b>Sim</b></option>
						<option value="nao"><b>Não</b></option>
					</select>
					<fieldset class="fieldinner">
						<div id="endereco_instalacao">
							<%Vector<String[]> instalacaoList = beanPedido.getDadosInstalacao();
							for (int i=0;i < instalacaoList.size();i++) {%>
								<b>Endereço:</b>&nbsp;<%=instalacaoList.elementAt(i)[0].trim()%>
								<%if(instalacaoList.elementAt(i)[5].trim().compareTo("") !=0 ){%>
									<br><b>Complemento:</b>&nbsp;<%=instalacaoList.elementAt(i)[5].trim()%>
								<%}%>
								<br><b>Bairro:</b>&nbsp;<%=instalacaoList.elementAt(i)[1].trim()%>&nbsp;&nbsp;&nbsp;<b>Cidade:</b>&nbsp;<%=instalacaoList.elementAt(i)[2].trim()%>&nbsp;&nbsp;&nbsp;<b>UF.:</b>&nbsp;<%=instalacaoList.elementAt(i)[3].trim()%>
								<br><b>País:</b>&nbsp;<%=instalacaoList.elementAt(i)[4].trim()%>&nbsp;&nbsp;&nbsp;<b>CEP.:</b>&nbsp;<%=instalacaoList.elementAt(i)[6].trim()%>
								<br><b>Ponto de Referência:</b>&nbsp;<%=instalacaoList.elementAt(i)[7].trim()%>
								<br><b>Contato Responsável:</b>&nbsp;<%=instalacaoList.elementAt(i)[10].trim()%>&nbsp;-&nbsp;(<%=instalacaoList.elementAt(i)[8].trim()%>)&nbsp;<%=instalacaoList.elementAt(i)[9].trim()%>
							<%} %>
						</div>
					</fieldset>
	                <b>Data do Agendamento:</b> <input type="date" id="data_agendamento">
	                <b>Hora:</b> <input type="time" name="usr_time">
	                <fieldset class="fieldinner">
	                    <div id="agendamento_inserido">
	                        
	                    </div>
	                </fieldset>
				</fieldset>
            </div>
            <div class="tab-pane" id="aba_observacoes">
                <fieldset class="field">
                    <textarea placeholder="Observações e Pendências" cols="80" rows="8" id="observacoes" maxlength="796"></textarea>
                </fieldset>
            </div>
            <div class="div_modal_bt">
            	<button type="button" id="incluir_modal" onclick="operacional_agendamento_function()">Incluir</button>
            	<button type="button" id="cancel_modal">Cancelar</button>
           	</div>	
        </div>
    </form>
</div>