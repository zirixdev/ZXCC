<!--
ZIRIX CONTROL CENTER - FORMULÁRIO DE CONSULTA
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: HTML5, JAVASCRIPT E JSP
-->
<%@ page import="zirix.zxcc.server.*,zirix.zxcc.server.dao.*,java.sql.SQLException,java.util.Vector" %>
<!--Operacional -> Consulta -> Cliente-->
<div id="operacional-consulta-cliente-content">
    <br>
    <fieldset class="fieldinner">
        Nome / Razão Social: <input list="nome_list" name="nome_razaosocial_consulta" id="item_nome_razao" class="size_65">
        <datalist id="nome_list">      	
		<%        	
			try {
						
				Vector<ClienteDAO> list = ClienteDAOService.loadAll();
				for (int i=0;i < list.size();i++) {
					   ClienteDAO dao = list.elementAt(i);
					   String str = String.valueOf(dao.getAttValueFor("NOME")).trim();%>				   	
	       			   <option value="<%=str%>" data-label="<%=dao.getPkValueFor("COD_CLIENTE")%>">
	       		<%}%>
		<%
			   } catch (Exception e) {
				   out.println("Error... " + e.getMessage());
			   }	
		%>
        </datalist>
        <br>
        Número Documento: <input type="text" class="size_16" name="numero_documento" id="num_doc_cons" disabled="disabled">
        Tipo do Documento:
       	<select id="tipodoc_list" disabled="disabled">
		<%        	
			try {
				Vector<TipoDocumentoDAO> list = TipoDocumentoDAOService.loadAll();
				for (int i=0;i < list.size();i++) {
					TipoDocumentoDAO dao = list.elementAt(i);
					String str = String.valueOf(dao.getAttValueFor("NOME")).trim();%>			   	
					<option value="<%=dao.getPkValueFor("COD_DOCUMENTO")%>" name="option_documento_tipo"><%=str%></option>
				<%}%>
		<%
			} catch (Exception e) {
				out.println("Error... " + e.getMessage());
			}	
		%>
		</select>
    </fieldset>
</div>

<!--Operacional -> Consulta -> Equipamento-->
<div id="operacional-consulta-equipamento-content">
    <br>
    <fieldset class="fieldinner">
        ID Modulo: <input list="id_list" name="id_modulo_consulta" id="item_id_modulo" class="size_65">
        <datalist id="id_list">      	
		<%try{
			Vector<ModuloDAO> list = ModuloDAOService.loadAll();
			for (int i=0;i < list.size();i++) {
				ModuloDAO dao = list.elementAt(i);
				String str = String.valueOf(dao.getAttValueFor("NUMERO_MODULO")).trim();%>				   	
				<option value="<%=str%>" data-label="<%=dao.getPkValueFor("COD_MODULO")%>">
			<%}%>
		<%}catch (Exception e) {
			out.println("Error... " + e.getMessage());
		  }%>
        </datalist>
		</select>
    </fieldset>
</div>

<!--Operacional -> Consulta -> Chip-->
<div id="operacional-consulta-chip-content">
    <br>
    <fieldset class="fieldinner">
        ICC-ID:<input list="iccid_list" name="iccid_consulta" id="item_iccid" class="size_50">
        <datalist id="iccid_list">   	
		<%        	
			try {
						
				Vector<ChipDAO> list = ChipDAOService.loadAll();
				for (int i=0;i < list.size();i++) {
						ChipDAO dao = list.elementAt(i);
						String str = String.valueOf(dao.getAttValueFor("ICCID")).trim();%>				   	
						<option value="<%=str%>" data-label="<%=dao.getPkValueFor("COD_CHIP")%>">
	       		<%}%>
		<%
			   } catch (Exception e) {
				   out.println("Error... " + e.getMessage());
			   }	
		%>
        </datalist>
    </fieldset>
</div>

<!--Comercial -> Consulta -> Cliente-->
<div id="comercial-consulta-cliente-content">
    <br>
    <fieldset class="fieldinner">
        Nome / Razão Social: <input list="nome_list" name="nome_razaosocial_consulta" id="item_nome_razao" class="size_65">
        <datalist id="nome_list">      	
		<%        	
			try {
						
				Vector<ClienteProspeccaoDAO> list = ClienteProspeccaoDAOService.loadAll();
				for (int i=0;i < list.size();i++) {
					   ClienteProspeccaoDAO dao = list.elementAt(i);
					   String str = String.valueOf(dao.getAttValueFor("NOME")).trim();%>				   	
	       			   <option value="<%=str%>" data-label="<%=dao.getPkValueFor("COD_CLIENTE_PROSPECCAO")%>">
	       		<%}%>
		<%
			   } catch (Exception e) {
				   out.println("Error... " + e.getMessage());
			   }	
		%>
        </datalist>
    </fieldset>
</div>