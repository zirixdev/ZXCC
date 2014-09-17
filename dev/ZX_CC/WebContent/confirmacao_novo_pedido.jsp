<!--
ZIRIX CONTROL CENTER - CADASTRO DE NOVO PEDIDO
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: HTML5, JAVASCRIPT E JSP
-->

<%@ page import="zirix.zxcc.server.*,zirix.zxcc.server.dao.*,java.sql.SQLException,java.util.Vector" %>

<%
	String[] pkVal = {request.getParameter("COD_PEDIDO")};
	NovoPedidoServiceBean beanPedido = new NovoPedidoServiceBean(pkVal);
	ClienteServiceBean beanCliente = new ClienteServiceBean(beanPedido.getCodCliente());
%>
<!--Comercial -> Novo Pedido-->
<div id="comercial-novo-pedido-content">
    <form class="outer_form" >
        <ul class="nav nav-tabs">
            <li class="active"><a href="#aba_cliente" data-toggle="tab">Cliente</a></li>
            <li><a href="#aba_unidade" data-toggle="tab">Unidades</a></li>
            <li><a href="#aba_servicos" data-toggle="tab">Serviços</a></li>
            <li><a href="#aba_observacoes" data-toggle="tab">Observações</a></li>
            <li><a href="#aba_anexos" data-toggle="tab" hidden="hidden">Anexos</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="aba_cliente">
                <fieldset class="field">
	                <%if(beanCliente.getTipo() == 0){%>
	                	Pessoa Física
	                <%}else{%>
	                    Pessoa Jurídica
	                <%}%>
                    <fieldset class="fild_vendedor">
                    	Vendedor:
                    		<%try {
								Vector<VendedorDAO> list = VendedorDAOService.loadAll();
								for (int i=0;i < list.size();i++) {
									VendedorDAO dao = list.elementAt(i);
									String str = String.valueOf(dao.getAttValueFor("NOME_FANTASIA")).trim();
									int codVendedor = beanCliente.getCodVendedor();
									if(dao.getPkValueFor("COD_VENDEDOR") == codVendedor){%> <%=str%><%}%>
					       		<%}%>
							<%} catch (Exception e) {
								out.println("Error... " + e.getMessage());
							}%>
                    </fieldset>
                    <br>Razão Social / Nome: <%=beanCliente.getNome().trim()%>
                    <br>Nome Fantasia / Apelido: <%=beanCliente.getNomeFantasia().trim()%>
                    <br>Data de Nascimento: <%=beanCliente.getDtNascimento().trim()%>
                    <br>Site: <%=beanCliente.getSite().trim()%>
                </fieldset>
                <fieldset class="field">
                <legend>Documentos:</legend>
                	<div id="documentos"></div>
                	<div id="docs_inserido">
						<%Vector<String[]> docList = beanCliente.getDocumento();
						for (int i=0;i < docList.size();i++) {%>
	                        <div id="doc_oculta_<%=i%>" class="class_oculta">
								<div id="num_doc_oculta_<%=i%>"><%=docList.elementAt(i)[0].trim()%></div>
								<div id="tipo_doc_oculta_<%=i%>"><%=docList.elementAt(i)[1].trim()%></div>
								<div id="dt_emiss_doc_oculta_<%=i%>"><%=docList.elementAt(i)[2].trim()%></div>
								<div id="org_emiss_doc_oculta_<%=i%>"><%=docList.elementAt(i)[3].trim()%></div>
							</div>
						<%}%>
					</div>
                </fieldset>
                <fieldset class="field">
				<legend>Endereço:</legend>
                	<div id="endereco"></div>
                	<div id="endereco_inserido">
						<%Vector<String[]> endList = beanCliente.getEnd();
							for (int i=0;i < endList.size();i++) {%>
		                        <div id="end_oculta_<%=i%>" class="class_oculta">
									<div id="endereco_oculta_<%=i%>"><%=endList.elementAt(i)[0].trim()%></div>
									<div id="bairro_oculta_<%=i%>"><%=endList.elementAt(i)[1].trim()%></div>
									<div id="cidade_oculta_<%=i%>"><%=endList.elementAt(i)[2].trim()%></div>
									<div id="uf_oculta_<%=i%>"><%=endList.elementAt(i)[3].trim()%></div>
									<div id="pais_oculta_<%=i%>"><%=endList.elementAt(i)[4].trim()%></div>
									<div id="complemento_oculta_<%=i%>"><%=endList.elementAt(i)[5].trim()%></div>
									<div id="cep_oculta_<%=i%>"><%=endList.elementAt(i)[6].trim()%></div>
									<div id="tipo_end_oculta_<%=i%>"><%=endList.elementAt(i)[7].trim()%></div>
									<br>
								</div>
							<%}%>
					</div>
                </fieldset>
                <fieldset class="field">
				<legend>Contato:</legend>
                    <div id="contato"></div>
                	<div id="contato_inserido">
						<%Vector<String[]> contatoList = beanCliente.getContato();
							for (int i=0;i < contatoList.size();i++) {%>
		                        <div id="contato_oculta_<%=i%>" class="class_oculta">
									<div id="tipo_contato_oculta_<%=i%>"><%=contatoList.elementAt(i)[0].trim()%></div>
									<div id="ddd_contato_oculta_<%=i%>"><%=contatoList.elementAt(i)[1].trim()%></div>
									<div id="numero_contato_oculta_<%=i%>"><%=contatoList.elementAt(i)[2].trim()%></div>
									<div id="cod_pais_oculta_<%=i%>"><%=contatoList.elementAt(i)[3].trim()%></div>
									<div id="nome_contato_oculta_<%=i%>"><%=contatoList.elementAt(i)[4].trim()%></div>
									<div id="grau_contato_oculta_<%=i%>"><%=contatoList.elementAt(i)[5].trim()%></div>
									<br>
								</div>
							<%}%>
					</div>
                </fieldset>
                <fieldset class="field">
				<legend>E-mail:</legend>
                    <div id="email"></div>
					<div id="emails_inserido">
						<%Vector<String[]> emailList = beanCliente.getEmail();
						for (int i=0;i < emailList.size();i++) {%>
							<div id="email_oculta_<%=i%>" class="class_oculta">
								<div id="nome_email_oculta_<%=i%>"><%=emailList.elementAt(i)[0].trim()%></div>
								<br>
							</div>
						<%}%>
					</div>
                </fieldset>
            </div>
            <div class="tab-pane" id="aba_unidade">
                <fieldset class="field">
                <legend>Dados para Instalação:</legend>
					<div id="div_end">
	                    Endereço: <input type="text" class="size_100" id="endereco_inst" maxlength="99">
	                    <br>
	                    Complemento:<input type="text" class="size_24" id="complemento_inst" maxlength="29">
	                    Bairro:<input type="text" class="size_22" id="bairro_inst" maxlength="50">
	                    Cidade:<input type="text" class="size_22" id="cidade_inst" maxlength="50">
	                    <br>UF:&nbsp;
	                    <select id="uf_list_inst" class="size_8">
	                        <option value="1" name="option_endereco_uf">AC</option>
	                        <option value="2" name="option_endereco_uf">AL</option>
	                        <option value="3" name="option_endereco_uf">AP</option>
	                        <option value="4" name="option_endereco_uf">AM</option>
	                        <option value="5" name="option_endereco_uf">BA</option>
	                        <option value="6" name="option_endereco_uf">CE</option>
	                        <option value="7" name="option_endereco_uf">DF</option>
	                        <option value="8" name="option_endereco_uf">ES</option>
	                        <option value="9" name="option_endereco_uf">GO</option>
	                        <option value="10" name="option_endereco_uf">MA</option>
	                        <option value="11" name="option_endereco_uf">MT</option>
	                        <option value="12" name="option_endereco_uf">MS</option>
	                        <option value="13" name="option_endereco_uf">MG</option>
	                        <option value="14" name="option_endereco_uf">PA</option>
	                        <option value="15" name="option_endereco_uf">PB</option>
	                        <option value="16" name="option_endereco_uf">PR</option>
	                        <option value="17" name="option_endereco_uf">PE</option>
	                        <option value="18" name="option_endereco_uf">PI</option>
	                        <option value="19" name="option_endereco_uf">RJ</option>
	                        <option value="20" name="option_endereco_uf">RN</option>
	                        <option value="21" name="option_endereco_uf">RS</option>
	                        <option value="22" name="option_endereco_uf">RO</option>
	                        <option value="23" name="option_endereco_uf">RR</option>
	                        <option value="24" name="option_endereco_uf">SC</option>
	                        <option value="25" name="option_endereco_uf">SP</option>
	                        <option value="26" name="option_endereco_uf">SE</option>
	                        <option value="27" name="option_endereco_uf">TO</option>
	                        <option value="28" name="option_endereco_uf">OUTRO</option>
	                    </select>
	                    &nbsp;País:
	                    <select id="pais_list_inst" class="size_16" onchange="">
							<%try{
								Vector<PaisDAO> list = PaisDAOService.loadAll();
								for (int i=0;i < list.size();i++) {
									PaisDAO dao = list.elementAt(i);
									String str = String.valueOf(dao.getAttValueFor("NOME_PAIS")).trim();%>				   	
									<option value="<%=dao.getPkValueFor("COD_PAIS")%>" name="option_endereco_pais_inst"<% 
									if(str.compareTo("Brasil") == 0){%> selected<%}%>><%=str%></option>
								<%}%>
							<%}catch (Exception e){
								out.println("Error... " + e.getMessage());
				   			}%>
	                    </select>
	                    CEP:<input type="text" class="size_11" id="cep_inst" maxlength="8" onkeypress="javascript: return EntradaNumerico(event);">
						Referência:<input type="text" class="size_35" id="referencia_inst" maxlength="249">
						<br>DDD:<input type="text" class="size_5" id="ddd_inst" maxlength="2" onkeypress="javascript: return EntradaNumerico(event);">
						Número:<input type="text" class="size_17" id="numero_inst" maxlength="10" onkeypress="javascript: return EntradaNumerico(event);">
						Contato Responsável:<input type="text" class="size_40" id="contato_inst" maxlength="50">
					</div>
                </fieldset>
                <fieldset class="field">
                    <legend>Procedimentos em Caso de Emergencia e Alertas:</legend>
                    <div id="unidade_procedimento">
	                    <div id="div_contato_procedimento">
	                        DDD:<input type="text" class="size_5" id="ddd_procedimento" maxlength="2" onkeypress="javascript: return EntradaNumerico(event);">
	                        Número:<input type="text" class="size_19" id="numero_contato_procedimento" maxlength="10" onkeypress="javascript: return EntradaNumerico(event);">
	                        Tipo do Contato:
	                        <select id="tipocont_procedimento_list" class="size_21">
	                            <%        	
									try {
												
										Vector<TipoContatoDAO> list = TipoContatoDAOService.loadAll();
										for (int i=0;i < list.size();i++) {
											TipoContatoDAO dao = list.elementAt(i);
											String str = String.valueOf(dao.getAttValueFor("NOME_TIPO")).trim();%>					   	
							       			   <option value="<%=dao.getPkValueFor("COD_CONTATO")%>" name="option_contato_tipo"><%=str%></option>
							       		<%}%>
								<%
									   } catch (Exception e) {
										   out.println("Error... " + e.getMessage());
									   }	
								%>
	                        </select>
	                        Cod. País: <input type="text" class="size_5" id="cod_pais_procedimento" maxlength="3" onkeypress="javascript: return EntradaNumerico(event);">
	                        <br>Nome:<input type="text" class="size_32" id="nome_contato_procedimento">
	                        Parentesco / Cargo:
	                        <select id="info_contato_procedimento_list" class="size_35">
	                            <%        	
									try {
												
										Vector<InfoContatoDAO> list = InfoContatoDAOService.loadAll();
										for (int i=0;i < list.size();i++) {
											InfoContatoDAO dao = list.elementAt(i);
											String str = String.valueOf(dao.getAttValueFor("NOME_GRAU")).trim();%>
							       			   <option value="<%=dao.getPkValueFor("COD_GRAU")%>"  name="option_parentesco_cargo"><%=str%></option>
							       		<%}%>
								<%
									   } catch (Exception e) {
										   out.println("Error... " + e.getMessage());
									   }	
								%>
	                        </select>
	                    </div>
	                    <fieldset class="fieldinner">
	                        <legend>Inseridos:</legend>
	                        <div id="contato_procedimento_inserido">
	
	                        </div>
	                    </fieldset>
	                    <div id="div_contato_procedimento_bt">
	                        <button type="button" id="incluir_contato_procedimento" onclick="insert_contatos_procedimento_function()">Incluir</button>
	                    </div>
						Senha de Atendimento:<input type="text" class="size_15" id="senha_atendimento_procedimento" maxlength="4" onkeypress="javascript: return EntradaNumerico(event);">
						<br>
						Tipo de Unidade:<select onchange="unit_function()" id="tipo_unidade_list" class="size_21">
							<option value="0"></option>
							<%try {
								Vector<TipoUnidadeDAO> list = TipoUnidadeDAOService.loadAll();
								for (int i=0;i < list.size();i++) {
									TipoUnidadeDAO dao = list.elementAt(i);
									String str = String.valueOf(dao.getAttValueFor("NOME")).trim();
									if(str.compareTo("ESTOQUE") != 0){%>
										<option value="<%=dao.getPkValueFor("COD_UNIDADE")%>"><%=str%></option>
									<%}
								}
							}catch (Exception e){
								out.println("Error... " + e.getMessage());
							}%>
						</select>
						<div id="tipo_unidade">
	
						</div>
					</div>
                    <fieldset class="fieldinner">
                        <legend>Inseridos:</legend>
                        <div id="unidades_inseridas">

                        </div>
                    </fieldset>
                   	<div id="div_unidade_bt">
                    	<button type="button" id="incluir_unidade" onclick="insert_unidades_function()">Incluir</button>
                    </div>
                </fieldset>
            </div>
            <div class="tab-pane" id="aba_servicos">
                <fieldset class="field">
                    <legend>Tipo do Pedido:</legend>
                    <%try {
                    	int count = 0;
						Vector<TipoPedidoDAO> list = TipoPedidoDAOService.loadAll();
						for (int i=0;i < list.size();i++) {
							TipoPedidoDAO dao = list.elementAt(i);
							int codTipo = dao.getPkValueFor("COD_TIPO");
							int codTipoInserted = beanPedido.getCodTipo();
							String str = String.valueOf(dao.getAttValueFor("NOME_TIPO")).trim();%>
							<input type="radio" name="servico" value="<%=codTipo%>" onchange="libera_div('<%=str%>')"<%if(codTipo == codTipoInserted){%> checked="checked"<%}%>><%=str%>
							<%if(str.compareTo("Teste") == 0){%>
								<div style="display: inline-block;" id="div_teste_dias">
								</div>
							<%}else if(str.compareTo("Outro") == 0){%>
								<div style="display: inline-block;" id="div_outro_servico">
								</div>
							<%}
							count ++;
							if(count == 3){
								count = 0;
								%><br><%
							}
						}
                    }catch (Exception e) {
						out.println("Error... " + e.getMessage());
					}%>
                </fieldset>
                <fieldset class="field">
                <legend>Equipamentos / Acessórios:</legend>
	                <div id="div_equipamentos_acessorios">
	                    Item:<select id="equip_acess_list" class="size_20">
							<%try {
								Vector<TipoEquipAcessorioDAO> list = TipoEquipAcessorioDAOService.loadAll();
								for (int i=0;i < list.size();i++) {
									TipoEquipAcessorioDAO dao = list.elementAt(i);
									int codEquipAcessorio = dao.getPkValueFor("COD_EQUIP_ACESSORIO");
									String str = String.valueOf(dao.getAttValueFor("NOME_EQUIP_ACESSORIO")).trim();%>
									<option value="<%=codEquipAcessorio%>"
									<%if(codEquipAcessorio == 1){%> selected<%}%>><%=str%></option>
								<%}
							}catch (Exception e){
								out.println("Error... " + e.getMessage());
							}%>
	                    </select>Quantidade:
	                    <input class="size_5" type="text" id="quant_equip_acess" onkeypress="javascript: return EntradaNumerico(event);" onchange="valor_total_function('equip_acessorio')" placeholder="0">Valor Unitário:
	                    <input class="size_11" type="text" id="valor_unit_equip_acess" maxlength="8" onchange="valor_total_function('equip_acessorio')" onkeypress="javascript: return EntradaNumerico(event);" placeholder="0.00">
	                    Valor Total:<div id="valor_total_equip_acessorio" style="display: inline-block; height:24px; width:75px;"><input class="size_100" type="text" id="valor_total_equip_acess" disabled="disabled" placeholder="0.00"></div>
					</div>
                   <fieldset class="fieldinner">
                   <legend>Inseridos:</legend>
                       <div id="equip_acessorio_inserido">

                       </div>
                   </fieldset>
                  	<div id="div_equip_acessorio_bt">
                   		<button type="button" id="incluir_equip_acess" onclick="insert_equip_acessorio_function()">Incluir</button>
					</div>
                </fieldset>
                <fieldset class="field">
              	<legend>Data de Vencimento:</legend>
                    <input type="radio" name="vencimento" value="03"<%if(beanPedido.getDataVencimento() == 3){%> checked="checked"<%}%>>03
                    <input type="radio" name="vencimento" value="05"<%if(beanPedido.getDataVencimento() == 5){%> checked="checked"<%}%>>05
                    <input type="radio" name="vencimento" value="10"<%if(beanPedido.getDataVencimento() == 10){%> checked="checked"<%}%>>10
                </fieldset>
                <fieldset class="field">
				<legend>Serviços:</legend>
	                <div id="div_servico">
	                    Item:<select id="serv_monit_list" class="size_20">
							<%try {
								Vector<TipoServicoDAO> list = TipoServicoDAOService.loadAll();
								for (int i=0;i < list.size();i++) {
									TipoServicoDAO dao = list.elementAt(i);
									int codServico = dao.getPkValueFor("COD_SERVICO");
									String str = String.valueOf(dao.getAttValueFor("NOME_SERVICO")).trim();%>
									<option value="<%=codServico%>"
									<%if(codServico == 1){%> selected<%}%>><%=str%></option>
								<%}
							}catch (Exception e){
								out.println("Error... " + e.getMessage());
							}%>
	                    </select>Quantidade:
	                    <input class="size_5" type="text" id="quant_serv_monit" onkeypress="javascript: return EntradaNumerico(event);" onchange="valor_total_function('servico')" placeholder="0">Valor Unitário:
	                    <input class="size_11" type="text" id="valor_assin_serv_monit" maxlength="8" onchange="valor_total_function('servico')" onkeypress="javascript: return EntradaNumerico(event);" placeholder="0.00">
	                    Valor Total:<div id="valor_total_servico" style="display: inline-block; height:24px; width:75px;"><input class="size_100" type="text" id="valor_total_serv_monit" disabled="disabled" placeholder="0.00"></div>
                    </div>
                    <fieldset class="fieldinner">
                    <legend>Inseridos:</legend>
                        <div id="serv_monit_inserido">
                            
                        </div>
                    </fieldset>
                  	<div id="div_servico_bt">
	                    <button type="button" id="incluir_servico" onclick="insert_servico_function()">Incluir</button>
					</div>
                </fieldset>
            </div>
            <div class="tab-pane" id="aba_observacoes">
                <fieldset class="field">
                    <textarea placeholder="Observações e Pendências" cols="80" rows="8" id="observacoes" maxlength="796"></textarea>
                </fieldset>
            </div>
            <div class="tab-pane" id="aba_anexos">
                <fieldset class="field">
                    <input type="file" name="anexos" id="upload_arquivo" multiple="multiple">
                    <button type="button" id="enviar_arquivo_anexo">Enviar</button>
                    <br>
                    <fieldset class="fieldinner">
                    <legend>Arquivos:</legend>
                        <div id="arquivos_upload">
                        </div>
                    </fieldset>
                </fieldset>
            </div>
            <br>
            <button type="button" id="incluir_modal" onclick="comercial_cadastrar_novo_pedido_function()">Incluir</button>
            <button type="button" id="cancel_modal">Cancelar</button>      
        </div>
    </form>
</div>