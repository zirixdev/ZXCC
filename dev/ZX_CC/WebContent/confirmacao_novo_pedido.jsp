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
	String[] pkCodCliente = {beanPedido.getCodCliente()};
	ClienteServiceBean beanCliente = new ClienteServiceBean(pkCodCliente);
%>
<!--Comercial -> Novo Pedido-->
<div id="comercial-novo-pedido-content">
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
					<%}catch (Exception e){
						out.println("Error ao retornar vendedor... " + e.getMessage());
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
						</div>
					<%}%>
				</div>
			</fieldset>
		</div>
		<div class="tab-pane" id="aba_unidade">
			<fieldset class="field">
			<legend>Dados para Instalação:</legend>
				<%Vector<String[]> instalacaoList = beanPedido.getDadosInstalacao();
					for (int i=0;i < instalacaoList.size();i++) {%>
						Endereço: <%=instalacaoList.elementAt(i)[0].trim()%>
						<br>Complemento: <%=instalacaoList.elementAt(i)[5].trim()%>
						<br>Bairro: <%=instalacaoList.elementAt(i)[1].trim()%> Cidade: <%=instalacaoList.elementAt(i)[2].trim()%> UF.: <%=instalacaoList.elementAt(i)[3].trim()%>
						<br>País: <%=instalacaoList.elementAt(i)[4].trim()%> CEP.: <%=instalacaoList.elementAt(i)[6].trim()%>
						<br>Ponto de Referência: <%=instalacaoList.elementAt(i)[7].trim()%>
						<br>Contato Responsável: <%=instalacaoList.elementAt(i)[10].trim()%>
						<br>(<%=instalacaoList.elementAt(i)[8].trim()%>) - <%=instalacaoList.elementAt(i)[9].trim()%>
					<%} %>
			</fieldset>
			<fieldset class="field">
			<legend>Unidades Rastreadas:</legend>
				<ul class="nav nav-tabs">
					<%Vector<int[]> CountUnidadesVeículo = beanPedido.getCountUnidadesVeículo();
					for (int i=0;i < CountUnidadesVeículo.size();i++) {%>
						<li class="active"><a href="#aba_unidade_<%=i+1%>" data-toggle="tab">Veículo <%=i+1%></a></li>
					<%}%>
				</ul>
				<%for (int i=0;i < CountUnidadesVeículo.size();i++) {%>
					<div class="tab-pane" id="aba_unidade_<%=i+1%>">
					<%try{
						Vector<VeiculoDAO> listVeiculo = VeiculoDAOService.loadAllPedido(CountUnidadesVeículo.elementAt(i)[0]);
						for(int j=0; j<listVeiculo.size(); j++){
							VeiculoDAO dao = listVeiculo.elementAt(i);%>
							Placa: <%=String.valueOf(dao.getAttValueFor("PLACA")).trim()%>   Chassi: <%=String.valueOf(dao.getAttValueFor("CHASSI")).trim()%>   Renavam: <%=String.valueOf(dao.getAttValueFor("RENAVAN")).trim()%>
							<br>Marca: <%=beanPedido.getNomeMarca(Integer.parseInt(String.valueOf(dao.getAttValueFor("COD_MARCA")).trim()))%>  Modelo: <%=String.valueOf(dao.getAttValueFor("MODELO")).trim()%>
							<br>Ano de Fabricação: <%=String.valueOf(dao.getAttValueFor("ANO_FAB")).trim()%>  Ano do Modelo: <%=String.valueOf(dao.getAttValueFor("ANO_MOD")).trim()%>  Cor: <%=String.valueOf(dao.getAttValueFor("COR")).trim()%>
							<br>Tipo de Combustível: <%=beanPedido.getNomeCombustivel(Integer.parseInt(String.valueOf(dao.getAttValueFor("COD_COMBUSTIVEL")).trim()))%>  Voltagem: <%=String.valueOf(dao.getAttValueFor("VOLT")).trim()%>  KM: <%=String.valueOf(dao.getAttValueFor("KM")).trim()%>
							<br>Data da Última Vistoria: <%=String.valueOf(dao.getAttValueFor("DATA_ULT_VISTORIA")).trim()%>
							<br>Senha para Atendimento: <%=beanPedido.getSenhaAtendimento(CountUnidadesVeículo.elementAt(i)[0])%>
							<fieldset class="fieldinner">
							<legend>Contatos para Procedimento:</legend>
								<%Vector<String[]> contatoProcedimentoList = beanPedido.getContatoProcedimento(CountUnidadesVeículo.elementAt(i)[0]);
								for(int l=0;l<contatoProcedimentoList.size();l++){%>
									Nome: <%=contatoProcedimentoList.elementAt(i)[4].trim()%>  Parentesco/Cargo: <%=contatoProcedimentoList.elementAt(i)[5].trim()%>
									<br><%=contatoProcedimentoList.elementAt(i)[0].trim()%>: +<%=contatoProcedimentoList.elementAt(i)[3].trim()%>(<%=contatoProcedimentoList.elementAt(i)[1].trim()%>) - <%=contatoProcedimentoList.elementAt(i)[2].trim()%>
								<canvas id="myCanvasCtoProced_<%=i%>_<%=l%>" width="650" height="1" style="border:0px;"></canvas>
								<script>var c = document.getElementById("myCanvasCtoProced_<%=i%>_<%=l%>");
								var ctx = c.getContext("2d");
								ctx.moveTo(0,0);
								ctx.lineTo(650,0);
								ctx.stroke();</script><br>
								<%}%>
							</fieldset>
						<%}
					}catch (Exception e){
						out.println("Error ao preencher abas unidades... " + e.getMessage());
					}%>
					</div>
				<%}%>
			</fieldset>
		</div>
		<div class="tab-pane" id="aba_servicos">
			<fieldset class="field">
			<legend>Tipo do Pedido:</legend>
                 Pedido de <%=beanPedido.getTipoPedido()%> - Nº: <%=beanPedido.getNumeroPedido()%>
                 <br>Dia de Vencimento: <%=beanPedido.getDataVencimento()%>
                 <br>Serviços:
                 <%try{
                	 Vector<String[]> listServico = beanPedido.getServicoPedido();
                	 for(int i=0; i<listServico.size();i++){%>
                		 <%=listServico.elementAt(i)[0].trim()%>: <%=listServico.elementAt(i)[1].trim()%> unid. - Valor Unitário: R$<%=listServico.elementAt(i)[2].trim()%> | Total: R$<%=listServico.elementAt(i)[3].trim()%>
		                 <canvas id="myCanvasServico_<%=i%>" width="650" height="1" style="border:0px;"></canvas>
		                 <script>
		                 	var c = document.getElementById("myCanvasServico_<%=i%>");
							var ctx = c.getContext("2d");
							ctx.moveTo(0,0);
							ctx.lineTo(650,0);
							ctx.stroke();
						</script>
                	 <%}
                 }catch (Exception e){
                	 out.println("Error ao preencher serviços... " + e.getMessage());
                 }%>
                 <br>Valor Total dos Serviços: R$<%=beanPedido.getValorTotalServico()%>
                 <br>Equipamentos/Acessórios:
                 <%try{
                	 Vector<String[]> listEquipamento = beanPedido.getEquipAcessorioPedido();
                	 for(int i=0; i<listEquipamento.size();i++){%>
                		 <%=listEquipamento.elementAt(i)[0].trim()%>: <%=listEquipamento.elementAt(i)[1].trim()%> unid. - Valor Unitário: R$<%=listEquipamento.elementAt(i)[2].trim()%> | Total: R$<%=listEquipamento.elementAt(i)[3].trim()%>
		                 <canvas id="myCanvasEquipamento_<%=i%>" width="650" height="1" style="border:0px;"></canvas>
		                 <script>
		                 	var c = document.getElementById("myCanvasEquipamento_<%=i%>");
							var ctx = c.getContext("2d");
							ctx.moveTo(0,0);
							ctx.lineTo(650,0);
							ctx.stroke();
						</script>
                	 <%}
                 }catch (Exception e){
                	 out.println("Error ao preencher equipamentos... " + e.getMessage());
                 }%>
                 <br>Valor Total dos Equipamentos e Acessórios: R$<%=beanPedido.getValorTotalServico()%>
			</fieldset>
		</div>
		<div class="tab-pane" id="aba_observacoes">
			<fieldset class="field">
			<legend>Observações:</legend>
				<%try{
					Vector<String[]> listObservacoes = beanPedido.getObsPedido();
					for(int i=0; i<listObservacoes.size(); i++){%>
						<%=listObservacoes.elementAt(i)[0].trim()%>
					<%}
				}catch(Exception e){
					out.println("Error ao preencher observações... " + e.getMessage());
				}%>
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
		<button type="button" id="incluir_modal" onclick="confirmar_novo_pedido_function()">Incluir</button>
		<button type="button" id="cancel_modal">Cancelar</button>
	</div>
</div>