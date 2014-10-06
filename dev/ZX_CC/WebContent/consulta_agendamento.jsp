<!--
ZIRIX CONTROL CENTER - CONSULTA AGENDAMENTO
DESENVOLVIDO POR RAPHAEL B. MARQUES

CLIENTE: ZIRIX SOLUÇÕES EM RASTREAMENTO
TECNOLOGIAS UTILIZADAS: HTML5, JAVASCRIPT E JSP
-->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="zirix.zxcc.server.*,zirix.zxcc.server.dao.*,java.sql.SQLException,java.util.Vector,zirix.zxcc.server.mock.*,zirix.zxcc.server.mock.dao.*" %>
<%
	String[] PK_OBJ = {request.getParameter("PK_OBJ")};
	String WORK_ID = request.getParameter("WORK_ID");
	String[] COD_USUARIO = {request.getParameter("COD_USUARIO")};
	String AREA = request.getParameter("AREA");
	AgendamentoServiceBean beanAgendamento = new AgendamentoServiceBean(PK_OBJ);
	String[] pkCodCliente = {beanAgendamento.getCodCliente()};
	ClienteServiceBean beanCliente = new ClienteServiceBean(pkCodCliente);
	MockScheduleBean bean = new MockScheduleBean(COD_USUARIO);
	bean.setStartTimestamp(WORK_ID);
%>

<!--Operacional -> Agendamento-->
<div id="operacional-agendamento-content">
    <form class="outer_form">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#aba_agendamento" data-toggle="tab">Agendamento</a></li>
            <li><a href="#aba_unidades" data-toggle="tab">Unidades</a></li>
            <li><a href="#aba_observacoes" data-toggle="tab">Observações</a></li>
        </ul>   
        <div class="tab-content"> 
            <div class="tab-pane active" id="aba_agendamento">
	            <fieldset class="field">
				<legend><b>Agendamento do Pedido Nº. <%=beanAgendamento.getNumeroPedido()%>:</b></legend>
					<b>Cliente:</b> <%=beanCliente.getNome().trim()%>
					<fieldset class="fieldinner">
					<legend><b>Local de Instalação:</b></legend>
						<%Vector<String[]> instalacaoList = beanAgendamento.getEnderecoInstalacaoAgendamento();%>
							<b>Endereço:</b>&nbsp;<%=instalacaoList.elementAt(0)[0].trim()%>
							<%if(instalacaoList.elementAt(0)[5].trim().compareTo("") !=0 ){%>
								<br><b>Complemento:</b>&nbsp;<%=instalacaoList.elementAt(0)[5].trim()%>
							<%}%>
							<br><b>Bairro:</b>&nbsp;<%=instalacaoList.elementAt(0)[1].trim()%>&nbsp;&nbsp;&nbsp;<b>Cidade:</b>&nbsp;<%=instalacaoList.elementAt(0)[2].trim()%>&nbsp;&nbsp;&nbsp;<b>UF.:</b>&nbsp;<div id="uf_agend_div" style="display: inline-block;"><%=instalacaoList.elementAt(0)[3].trim()%></div>
							<br><b>País:</b>&nbsp;<%=instalacaoList.elementAt(0)[4].trim()%>&nbsp;&nbsp;&nbsp;<b>CEP.:</b>&nbsp;<%=instalacaoList.elementAt(0)[6].trim()%>
							<br><b>Ponto de Referência:</b>&nbsp;<%=instalacaoList.elementAt(0)[7].trim()%>
							<br><b>Contato Responsável:</b>&nbsp;<%=instalacaoList.elementAt(0)[10].trim()%>&nbsp;-&nbsp;(<%=instalacaoList.elementAt(0)[8].trim()%>)&nbsp;<%=instalacaoList.elementAt(0)[9].trim()%>
					</fieldset>
					<b>Data do Agendamento: </b><%=beanAgendamento.getDataAgendamento()%>&nbsp;&nbsp;&nbsp;<b>Hora do Agendamento: </b><%=beanAgendamento.getHoraAgendamento()%>
				</fieldset>
            </div>
            <div class="tab-pane" id="aba_unidades">
                <fieldset class="field">
				<legend><b>Unidades Agendadas:</b></legend>
					<b>Técnico:</b><input list="nome_list" name="nome_tecnico" id="item_nome_tecnico" class="size_100">
			        <datalist id="nome_list">      	
					<%try{
						Vector<TecnicoDAO> list = TecnicoDAOService.loadAll();
						for (int i=0;i < list.size();i++) {
							TecnicoDAO dao = list.elementAt(i);
							String str = String.valueOf(dao.getAttValueFor("NOME")).trim();%>				   	
							<option value="<%=str%>" data-label="<%=dao.getPkValueFor("COD_CLIENTE")%>">
						<%}%>
					<%}catch(Exception e){
						out.println("Error... " + e.getMessage());
					}%>
			        </datalist>
					<br><b>Horario de Chegada do Técnico: </b><input type="time" id="chegada_tecnico">
					<b>Horario de Saída do Técnico: </b><input type="time" id="saida_tecnico">
					<br><b>Visita Frustrada? </b><select id="frustrada" class="size_15" onchange="desbloqueia_unidades_function()">
						<option value="sim" selected="selected">Sim</option>
						<option value="nao">Não</option>	
					</select>
					<br><div id="obs_frust"><textarea placeholder="Observações" cols="30" rows="1" id="obs_frustrada" maxlength="796"></textarea></div>
					<div id="unidades">
						
					</div>
                </fieldset>
            </div>
            <div class="tab-pane" id="aba_observacoes">
                <fieldset class="field">
                <legend><b>Observações</b></legend>
                <%try{
					Vector<String[]> listObservacoes = beanAgendamento.getObsAgendamento();
					for(int i=0; i<listObservacoes.size(); i++){%>
						<%=listObservacoes.elementAt(i)[0].trim()%>
					<%}
				}catch(Exception e){
					out.println("Error ao preencher observações... " + e.getMessage());
				}%>
                </fieldset>
            </div>
           	<div class="tab-pane" id="aba_oculta">
           		<div id="dados_unidades">
					<ul class="nav nav-tabs">
						<%Vector<String[]> UnidadesAgendadas = beanAgendamento.getUnidadesAgendadas();
						for (int i=0;i < UnidadesAgendadas.size();i++) {%>
						<li<%if(i==0){%> class="active"<%}%>><a href="#aba_unidade_<%=i+1%>" data-toggle="tab">Unidade <%=i+1%></a></li>
						<%}%>
					</ul>
					<div class="tab-content" style="background: #eeeeee;">
						<%for (int i=0;i < UnidadesAgendadas.size();i++) {%>
							<div class="tab-pane<%if(i==0){%> active<%}%>" id="aba_unidade_<%=i+1%>">
								<b><%=UnidadesAgendadas.elementAt(i)[4].trim()%>:</b>&nbsp;
								<select id="resposta_unidade_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>" class="size_25">
									<option value="instalado_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">Serviço Executado</option>
									<option value="inprodutivo_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">Visita Improdutiva</option>
								</select>
								<br><b>Placa: </b><%=UnidadesAgendadas.elementAt(i)[1].trim()%>&nbsp;&nbsp;&nbsp;<b>Marca: </b><%=UnidadesAgendadas.elementAt(i)[2].trim()%>&nbsp;&nbsp;&nbsp;<b>Modelo: </b><%=UnidadesAgendadas.elementAt(i)[3].trim()%>
								<br><b>O.S.: <%=UnidadesAgendadas.elementAt(i)[5].trim()%></b>
								<br>
								<fieldset class="fieldinner">
									<textarea placeholder="Observações" cols="70" rows="4" id="observacoes" maxlength="796"></textarea>
								</fieldset>
								<b>Marque apenas os testes realizados que obtiveram sucesso:</b>
								<br><input type="checkbox" id="ignicao_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">&nbsp;<b>Ignição</b>
								<input type="checkbox" id="bloqueio_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">&nbsp;<b>Bloqueio</b>
								<input type="checkbox" id="gps_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">&nbsp;<b>GPS</b>
								<input type="checkbox" id="gprs_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">&nbsp;<b>GPRS</b>
								<br><input type="checkbox" id="sirene_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">&nbsp;<b>Sirene</b>&nbsp;&nbsp;
								<input type="checkbox" id="panico_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">&nbsp;<b>Alerta de Pânico</b>&nbsp;
								<input type="checkbox" id="rpm_<%=UnidadesAgendadas.elementAt(i)[0].trim()%>">&nbsp;<b>RPM</b>
							</div>
						<%}%>
					</div>
				</div>
				<select id="uf_list_inst" class="size_8">
					<option value="1" name="option_endereco_uf">AC</option><option value="2" name="option_endereco_uf">AL</option><option value="3" name="option_endereco_uf">AP</option><option value="4" name="option_endereco_uf">AM</option><option value="5" name="option_endereco_uf">BA</option><option value="6" name="option_endereco_uf">CE</option><option value="7" name="option_endereco_uf">DF</option><option value="8" name="option_endereco_uf">ES</option><option value="9" name="option_endereco_uf">GO</option><option value="10" name="option_endereco_uf">MA</option><option value="11" name="option_endereco_uf">MT</option><option value="12" name="option_endereco_uf">MS</option><option value="13" name="option_endereco_uf">MG</option><option value="14" name="option_endereco_uf">PA</option><option value="15" name="option_endereco_uf">PB</option><option value="16" name="option_endereco_uf">PR</option><option value="17" name="option_endereco_uf">PE</option><option value="18" name="option_endereco_uf">PI</option><option value="19" name="option_endereco_uf">RJ</option><option value="20" name="option_endereco_uf">RN</option><option value="21" name="option_endereco_uf">RS</option><option value="22" name="option_endereco_uf">RO</option><option value="23" name="option_endereco_uf">RR</option><option value="24" name="option_endereco_uf">SC</option><option value="25" name="option_endereco_uf">SP</option><option value="26" name="option_endereco_uf">SE</option><option value="27" name="option_endereco_uf">TO</option><option value="28" name="option_endereco_uf">OUTRO</option>
				</select>
		    </div>
            <div class="div_modal_bt">
            	<button type="button" id="incluir_modal" onclick="operacional_processar_agendamento_function('<%=WORK_ID%>','<%=PK_OBJ[0]%>')">Incluir</button>
            	<button type="button" id="cancel_modal">Cancelar</button>
           	</div>
        </div>
    </form>
</div>