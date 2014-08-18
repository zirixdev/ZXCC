<!--
ZIRIX CONTROL CENTER - INFORMAÇÕES SOBRE INSTALAÇÃO DO MODULO
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: HTML5 E JSP
-->

<%@ page import="zirix.zxcc.server.*,zirix.zxcc.server.dao.*,java.sql.SQLException,java.util.Vector" %>

<!--Instalação Cliente-->
<div id="instalado_cliente">
	Nome / Razão Social:<input list="nome_list" name="nome_razaosocial_cad_modulo" id="item_nome_razao" class="size_61">
	<datalist id="nome_list">      	
		<%        	
		try {		
			Vector<ClienteDAO> list = ClienteDAOService.loadAll();
			for (int i=0;i < list.size();i++) {
				ClienteDAO dao = list.elementAt(i);
				String str = String.valueOf(dao.getAttValueFor("NOME")).trim();%>				   	
				<option value="<%=str%>" data-label="<%=dao.getPkValueFor("COD_CLIENTE")%>">
			<%}%>
		<%}catch (Exception e){
			out.println("Error... " + e.getMessage());
		}%>
	</datalist>
	<div id="div_veiculo_equip">
		Veículo - Placa:<input list="placa_list" name="placa_veiculo_cad_modulo" id="item_placa_veiculo" class="size_71" disabled="disabled">
		<datalist id="placa_list">
			<!-- TODO de acordo com o cliente selecionado -->
		</datalist>
	</div>
</div>