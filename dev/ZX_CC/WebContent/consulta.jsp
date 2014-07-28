<!--
ZIRIX CONTROL CENTER - MAIN PAGE
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: HTML5 E JAVASCRIPT
-->
<%@ page import="zirix.zxcc.server.dao.*,java.sql.SQLException,java.util.Vector" %>
<!--Operacional -> Consulta -> Cliente-->
<div id="operacional-consulta-cliente-content">
    <br>
    <fieldset class="field">
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
        Número Documento: <input type="text" class="size_25" name="numero_documento" id="num_doc_cons" disabled="disabled">
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
    <fieldset class="field">
        EM CONSTRUÇÃO
    </fieldset>
</div>

<!--Operacional -> Consulta -> Chip-->
<div id="operacional-consulta-chip-content">
    <br>
    <fieldset class="field">
        ICC-ID: <input list="iccid_list" name="iccid_consulta" id="item_iccid" class="size_50">
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