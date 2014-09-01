/*
ZIRIX CONTROL CENTER - FUNÇÕES DE CONTROLE DE MENU
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVASCRIPT E AJAX
*/

xmlDoc=loadXMLDoc("js/VariaveisZXCC.xml");
var url_adress = xmlDoc.getElementsByTagName("adress")[0].textContent;


Date.prototype.yyyymmdd = function() {
	var yyyy = this.getFullYear().toString();
	var mm = (this.getMonth()+1).toString();
	var dd  = this.getDate().toString();

	return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
};  

function unit_function(){
    var values = $("#tipo_unidade_list").val();
    var fild_content = '';
    switch(values) {
        case "1":
        	fild_content = "div#unid_veiculo";
            break;
        default:
        $('.tipo_unidade').html('');
    }
    $.ajax({
        url: url_adress + "tipo_unidade.html",
        success: function(result) {
            var html = jQuery('<div>').html(result);
            var content = html.find(fild_content).html();
            $('.tipo_unidade').html(content);
        },
        error: function(e){
            alert('error');
        }
    });
}

function change_consulta_operacional_function(){
	var values = $("input[type='radio'][name='busca_op']:checked").val();
    var fild_content = '';
    switch(values) {
        case "cliente":
        	fild_content = "div#operacional-consulta-cliente-content";
            break;
        case "equipamento":
        	fild_content = "div#operacional-consulta-equipamento-content";
            break;
        case "chip":
        	fild_content = "div#operacional-consulta-chip-content";
            break;
        default:
        	$('.consulta_operacional').html('');
        	break;
    }
    $.ajax({
        url: url_adress + "consulta.jsp",
        success: function(result) {
            var html = jQuery('<div>').html(result);
            var content = html.find(fild_content).html();
            $('.consulta_operacional').html(content);
        },
        error: function(e){
            alert('error');
        }
    });
}

var cod_cliente_consulta;
var cod_modulo_consulta;
var cod_chip_consulta;
var cod_vendedor_consulta;
var cod_cliente_prospect_consulta;

function operacional_consulta_function(e){
    var values = $("input[type='radio'][name='busca_op']:checked").val();
    var adress;
    switch(values) {
        case "cliente":
            var val_datalist_nome = $('#item_nome_razao').val();
            var val_doc_selected = $('#item_tipo_doc :selected').val();
            var val_num_doc = $('#num_doc_cons').val();
            if (val_datalist_nome !== "" || ((val_num_doc !== "") && (val_doc_selected !== ""))){
            	cod_cliente_consulta = $('#nome_list option').filter(function() {
                    return this.value == val_datalist_nome;
                }).data('label');
                adress= url_adress + "consulta_cliente.jsp?COD_CLIENTE=";
                adress= adress + cod_cliente_consulta;
                $.ajax({
                    url: adress,
                    success: function(result) {
                        $('.modal-content').html(result);
                        $('.modal').modal({backdrop:'static'});
                        carregar_dados_consulta_cliente_function();
                    },
                    error: function(){
                        alert('Erro ao buscar dados do CLIENTE selecionado!');
                    }
                });
            }
            else{
                alert('Não é possível realizar a busca sem o preenchimento de um dos campos!');
                document.getElementById("item_nome_razao").focus();
                return 0;
            }
            break;
        case "equipamento":
        	var val_datalist_id = $('#item_id_modulo').val();
            if (val_datalist_id !== ""){
            	cod_modulo_consulta = $('#id_list option').filter(function() {
                    return this.value == val_datalist_id;
                }).data('label');
                adress= url_adress + "consulta_equipamento.jsp?COD_MODULO=";
                adress= adress + cod_modulo_consulta;
                $.ajax({
                    url: adress,
                    success: function(result) {
                        $('.modal-content').html(result);
                        $('.modal').modal({backdrop:'static'});
                    },
                    error: function(){
                        alert('Erro ao buscar dados do MODULO selecionado!');
                    }
                });
            }
        	else{
        		alert('Não é possível realizar a busca sem o preenchimento do campo ID!');
                document.getElementById("iccid_list").focus();
                return 0;
        	}
        	break;
        case "chip":
        	var val_iccid_selected = $('#item_iccid').val();
        	if(val_iccid_selected !== ""){
        		cod_chip_consulta = $('#iccid_list option').filter(function() {
        			return this.value == val_iccid_selected;
            }).data('label');
            adress= url_adress + "consulta_chip.jsp?COD_CHIP=";
            adress= adress + cod_chip_consulta;
            $.ajax({
                url: adress,
                success: function(result) {
                    $('.modal-content').html(result);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(){
                    alert('Erro ao buscar dados do SIM CARD selecionado!');
                }
            });
        		
        	}
        	else{
        		alert('Não é possível realizar a busca sem o preenchimento do campo ICC-ID!');
                document.getElementById("iccid_list").focus();
                return 0;
        	}
        	break;
        default:
            $('.consulta_operacional').html('');
    }
}

function comercial_consulta_function(e){
    var values = $("input[type='radio'][name='busca_com']:checked").val();
    var adress;
    switch(values) {
        case "cliente":
            var val_datalist_nome = $('#item_nome_razao').val();
            if (val_datalist_nome !== ""){
            	cod_cliente_prospect_consulta = $('#nome_list option').filter(function() {
                    return this.value == val_datalist_nome;
                }).data('label');
                adress= url_adress + "consulta_cli_prospect.jsp?COD_CLIENTE_PROSPECCAO=";
                adress= adress + cod_cliente_prospect_consulta;
                $.ajax({
                    url: adress,
                    success: function(result) {
                        $('.modal-content').html(result);
                        $('.modal').modal({backdrop:'static'});
                        carregar_dados_consulta_cliente_prospect_function();
                    },
                    error: function(){
                        alert('Erro ao buscar dados do CLIENTE selecionado!');
                    }
                });
            }
            else{
                alert('Não é possível realizar a busca sem o preenchimento do campo Nome / Razão Social!');
                document.getElementById("item_nome_razao").focus();
                return 0;
            }
            break;
        case "pedido":
        	break;
        default:
            $('.consulta_comercial').html('');
    }
}

function change_consulta_comercial_function(){
	var values = $("input[type='radio'][name='busca_com']:checked").val();
    var fild_content = '';
    switch(values) {
        case "cliente":
        	fild_content = "div#comercial-consulta-cliente-content";
            break;
        case "pedido":
        	fild_content = "div#comercial-consulta-pedido-content";
            break;
        default:
        	$('.consulta_comercial').html('');
        	break;
    }
    $.ajax({
        url: url_adress + "consulta.jsp",
        success: function(result) {
            var html = jQuery('<div>').html(result);
            var content = html.find(fild_content).html();
            $('.consulta_comercial').html(content);
        },
        error: function(e){
            alert('error');
        }
    });
}

function mod_int_function() {
    var values = $("input[type='radio'][name='modulo_instalado']:checked");
    var txt ="";
    if (values.length > 0) {
        txt = values.val();
    }
    switch(txt) {
    	case "1": //Estoque
	        $('.instalacao').html('');
	        break;
        case "2": //Veículo
        	$.ajax({
    	        url: url_adress + "modulo_inst_cad.jsp",
    	        success: function(result) {
                    $('.instalacao').html(result);
                },
                error: function(e){
                    alert('Error to find descriptions of installed clients!');
                }
            });
            break;
        default:
        	$('.instalacao').html('');
    }
}

var myDate = new Date();
var year = myDate.getFullYear();
$('#ano_fab').attr("max",year);
$('#ano_mod').attr("max",year+1);

var h = window.innerHeight;
$('.page-background').height(h - 235);
$('.conteudo').height(h - 301);

var clean_doc;
var control_div_doc = new Array();
function div_doc_inseridas (cod, excluida){
    this.cod = cod;
    this.excluida = excluida;
}

var clean_end;
var control_div_end = new Array();
function div_end_inseridas (cod, excluida){
    this.cod = cod;
    this.excluida = excluida;
}

var clean_contato;
var control_div_contato = new Array();
function div_contato_inseridas (cod, excluida){
    this.cod = cod;
    this.excluida = excluida;
}

var clean_email;
var control_div_email = new Array();
function div_email_inseridas (cod, excluida){
    this.cod = cod;
    this.excluida = excluida;
}

function vetor_doc_inserido(numero, tipo_doc, dt_emiss, org_emiss){
    this.numero = numero;
    this.tipo_doc = tipo_doc;
    this.dt_emiss = dt_emiss;
    this.org_emiss = org_emiss;
}

var control_vetor_doc = new Array();
control_vetor_doc[0] = 0;
var control_vetor_doc_tipo = new Array();

function insert_documentos_function() {
    clean_doc = $('#div_doc').html();
    var doc_list = $('#tipodoc_list').val();
    var doc_name = $('#tipodoc_list :selected').text();
    var num_doc = document.getElementById("numero_documento").value;
    var dt_emis = document.getElementById("data_emissao").value;
    if(dt_emis === null){
        dt_emis = "";
    }
    var org_ems = document.getElementById("orgao_emissor").value;
    if(org_ems === null){
        org_ems = "";
    }
    var insert = "X";

    var tipo_doc_tipo_obj = $('#tipodoc_list');
    var tipo_doc_tamanho_tipo = tipo_doc_tipo_obj[0].length;
    for (var i=0; i<tipo_doc_tamanho_tipo;i++){
        control_vetor_doc_tipo[i] = document.getElementsByName("option_documento_tipo")[i].text;
    }

    if (num_doc === ""){
        alert('Necessário ingressar número do documento.');
        document.getElementById("numero_documento").focus();
        return 0;
    }

    if (doc_name === ""){
        alert('Necessário escolher o tipo do documento.');
        document.getElementById("doc_list").focus();
        return 0;
    }
    switch(doc_list){
        case "1"://RG
            if (dt_emis === "" || org_ems === ""){
                alert('Esse tipo de documento necessita que seja preenchido data de emissão e órgão emissor.');
                if (dt_emis === ""){
                    document.getElementById("data_emissao").focus();
                }
                else {
                    document.getElementById("orgao_emissor").focus();
                }
                return 0;
            }
            break;
        case "2"://CPF
            if(!validarCPF(num_doc)){
                alert('CPF inválido.\n\
Favor digitar o número do documento corretamente.');
                document.getElementById("numero_documento").focus();
                return 0;
            }
            break;
        case "3"://CNPJ
            if(!validarCNPJ(num_doc)){
                alert('CNPJ inválido.\n\
Favor digitar o número do documento corretamente.');
                document.getElementById("numero_documento").focus();
                return 0;
            }
            break;
    }

    if(dt_emis === "")
    	dt_emis = "5000-12-31";
    if(org_ems === "")
    	org_ems = "VAZIO";

    i = control_div_doc.length;
    var j = 0;

    if (i !== 0){
        while (j < i && insert === "X"){
            if (control_div_doc[j].excluida === 1){
                insert = j;
            }
            else{
                j++;
            }
        }
        if (insert === "X"){
            control_div_doc[control_div_doc.length] = new div_doc_inseridas(i,0);
            control_vetor_doc[i] = new vetor_doc_inserido(num_doc, doc_list, dt_emis, org_ems);
        }
    }
    else{
        control_div_doc[0] = new div_doc_inseridas(0,0);
        control_vetor_doc[0] = new vetor_doc_inserido(num_doc, doc_list, dt_emis, org_ems);
    }

    if (insert === "X"){
        var content_div_doc = $('#docs_inserido').html();
        content_div_doc = content_div_doc + '<div id="doc_' + control_div_doc[i].cod + '" class="div_inseridos">\n\
                                                  <input type="radio" name="doc_inserido" value="doc_' + control_div_doc[i].cod + '" onclick="change_doc_button_function()">\n\
                                                  <div id="num_doc_' + control_div_doc[i].cod + '">' + num_doc + '</div>&nbsp;&#45;\n\
                                                  <div id="tipo_doc_' + control_div_doc[i].cod + '">' + doc_name + '</div>';
        if (dt_emis !== "5000-12-31"){
            content_div_doc = content_div_doc + '&nbsp;&#45;<div id="dt_emiss_doc_' + control_div_doc[i].cod + '">' + dt_emis + '</div>';
        }else{
            content_div_doc = content_div_doc + '<div id="dt_emiss_doc_' + control_div_doc[i].cod + '"></div>';
        }
        if (org_ems !== "VAZIO"){
            content_div_doc = content_div_doc + '&nbsp;&#45;<div id="org_emiss_doc_' + control_div_doc[i].cod + '">' + org_ems + '</div>';
        }
        else{
            content_div_doc = content_div_doc + '<div id="org_emiss_doc_' + control_div_doc[i].cod + '"></div>';
        }

        content_div_doc = content_div_doc + '<br> </div>';

        $('#docs_inserido').html(content_div_doc);
    }
    else {
        var content_div_doc = '<input type="radio" name="doc_inserido" value="doc_' + control_div_doc[j].cod + '" onclick="change_doc_button_function()">\n\
                                                  <div id="num_doc_' + control_div_doc[j].cod + '">' + num_doc + '</div>&nbsp;&#45;\n\
                                                  <div id="tipo_doc_' + control_div_doc[j].cod + '">' + doc_name + '</div>';
        if (dt_emis !== "5000-12-31"){
            content_div_doc = content_div_doc + '&nbsp;&#45;<div id="dt_emiss_doc_' + control_div_doc[j].cod + '">' + dt_emis + '</div>';
        }
        else{
            content_div_doc = content_div_doc + '<div id="dt_emiss_doc_' + control_div_doc[j].cod + '"></div>';
        }
        if (org_ems !== "VAZIO"){
            content_div_doc = content_div_doc + '&nbsp;&#45;<div id="org_emiss_doc_' + control_div_doc[j].cod + '">' + org_ems + '</div> <br>';
        }
        else{
            content_div_doc = content_div_doc + '<div id="org_emiss_doc_' + control_div_doc[j].cod + '"></div> <br>';
        }

        $('#doc_' + control_div_doc[j].cod).html(content_div_doc);
        control_div_doc[j].excluida = 0;
        control_vetor_doc[j] = new vetor_doc_inserido(num_doc, doc_list, dt_emis, org_ems);
    }
    limpa_campos_doc_function();
    var content_div_bt = '<button type="button" id="incluir_documento" onclick="insert_documentos_function()">Incluir</button>';
    $('#div_doc_bt').html(content_div_bt);
    document.getElementById("numero_documento").focus();
}

function delete_documentos_function() {
    var div_select = $("input[type='radio'][name='doc_inserido']:checked").val();
    var length = div_select.length;
    var div_deletar = div_select.charAt(length - 1);
    var num_doc = $('#num_doc_' + div_deletar).html();
    var doc_name = $('#tipo_doc_' + div_deletar).html();
    var content_div_doc = $('#' + div_select).html();

    if (confirm('O ' + doc_name.trim() + ' de Número: ' + num_doc.trim() + ' será apagado.')) {
        $('#' + div_select).html("");
        control_div_doc[div_deletar].excluida = 1;
        control_vetor_doc[div_deletar] = 0;
    } else {
        $('#' + div_select).html(content_div_doc);
    }

    var content_div_bt = '<button type="button" id="incluir_documento" onclick="insert_documentos_function()">Incluir</button>';
    $('#div_doc_bt').html(content_div_bt);
    limpa_campos_doc_function();
}

function change_doc_button_function(){
    var content_div_bt = '<button type="button" id="incluir_documento" onclick="insert_documentos_function()">Incluir</button>\n\
                          <button type="button" id="editar_documento" onclick="editar_documentos_function()">Editar</button>\n\
                          <button type="button" id="delete_documento" onclick="delete_documentos_function()">Excluir</button>';
    $('#div_doc_bt').html(content_div_bt);
}

var div_doc_editar;

function editar_documentos_function() {
    var content_div_bt = '<button type="button" id="salvar_documento" onclick="salvar_documentos_function()">Salvar</button>\n\
                          <button type="button" id="cancelar_documento" onclick="cancelar_documentos_function()">Cancelar</button>';
    $('#div_doc_bt').html(content_div_bt);

    var div_select = $("input[type='radio'][name='doc_inserido']:checked").val();
    var content_div_doc = $('#' + div_select).html();
    var length = div_select.length;
    div_doc_editar = div_select.charAt(length - 1);
    var num_doc = control_vetor_doc[div_doc_editar].numero;
    var tipo_doc = control_vetor_doc[div_doc_editar].tipo_doc;
    var dt_emiss_doc = control_vetor_doc[div_doc_editar].dt_emiss;
    var org_emiss_doc = control_vetor_doc[div_doc_editar].org_emiss;

    $('#' + div_select).html("");
    $('#' + div_select).html(content_div_doc);

    content_div_doc = "";

    content_div_doc = 'Número:\n\
                        <input type="text" class="size_25" id="numero_documento" value="' + num_doc + '" onkeypress="javascript: return EntradaNumerico(event);">Tipo do Documento:\n\
                        <select id="tipodoc_list" >';
    for(var i=0;i<control_vetor_doc_tipo.length;i++){
        content_div_doc = content_div_doc + '<option value="' + Number(i+1) + '"  name="option_documento_tipo"';
        if (Number(tipo_doc) === Number(i+1)){
            content_div_doc = content_div_doc + ' selected';
        }
        content_div_doc = content_div_doc + '>' + control_vetor_doc_tipo[i] + '</option>';
    }
    content_div_doc = content_div_doc + '</select>';

    if(dt_emiss_doc === "5000-12-31"){
        content_div_doc = content_div_doc + '<br>Data de Emissão:<input type="date" id="data_emissao">Órgão Emissor:';
    }
    else {
        content_div_doc = content_div_doc + '<br>Data de Emissão:<input type="date" id="data_emissao" value="' + dt_emiss_doc + '">Órgão Emissor:';
    }
    if (org_emiss_doc === "VAZIO"){
        content_div_doc = content_div_doc + '<input class="size_20" type="text" id="orgao_emissor">';
    }
    else {
        content_div_doc = content_div_doc + '<input class="size_20" type="text" id="orgao_emissor" value="'+ org_emiss_doc + '">';
    }

    $('#div_doc').html("");
    $('#div_doc').html(content_div_doc);
}

function limpa_campos_doc_function(){
    var content_div_doc = clean_doc;
    $('#div_doc').html("");
    $('#div_doc').html(content_div_doc);
}

function cancelar_documentos_function(){
    var content_div_bt = '<button type="button" id="incluir_documento" onclick="insert_documentos_function()">Incluir</button>';
    $('#div_doc_bt').html(content_div_bt);

    limpa_campos_doc_function();
}

function salvar_documentos_function(){
    var content_div_bt = '<button type="button" id="incluir_documento" onclick="insert_documentos_function()">Incluir</button>';
    $('#div_doc_bt').html(content_div_bt);

    var div_salvar = div_doc_editar;

    $('#doc_' + div_salvar).html("");    

    var num_doc = document.getElementById("numero_documento").value;
    var doc_list = $('#tipodoc_list').val();
    var doc_name = $('#tipodoc_list :selected').text();
    var dt_emis = document.getElementById("data_emissao").value;
    var org_ems = document.getElementById("orgao_emissor").value;

    if (num_doc === ""){
        alert('Necessário ingressar número do documento.');
        document.getElementById("numero_documento").focus();
        return 0;
    }

    if (doc_name === ""){
        alert('Necessário escolher o tipo do documento.');
        document.getElementById("doc_list").focus();
        return 0;
    }

    switch(doc_list){
        case "1"://RG
            if (dt_emis === "" || org_ems === ""){
                alert('Esse tipo de documento necessita que seja preenchido data de emissão e órgão emissor.');
                if (dt_emis === ""){
                    document.getElementById("data_emissao").focus();
                }
                else {
                    document.getElementById("orgao_emissor").focus();
                }
                return 0;
            }
            break;
        case "2"://CPF
            if(!validarCPF(num_doc)){
                alert('CPF inválido.\n\
Favor digitar o número do documento corretamente.');
                document.getElementById("numero_documento").focus();
                return 0;
            }
            break;
        case "3"://CNPJ
            if(!validarCNPJ(num_doc)){
                alert('CNPJ inválido.\n\
Favor digitar o número do documento corretamente.');
                document.getElementById("numero_documento").focus();
                return 0;
            }
            break;
    }

    if(dt_emis === "")
    	dt_emis = "5000-12-31";
    if(org_ems === "")
    	org_ems = "VAZIO";

    var content_div_doc = '<input type="radio" name="doc_inserido" value="doc_' + control_div_doc[div_salvar].cod + '" onclick="change_doc_button_function()">\n\
                             <div id="num_doc_' + control_div_doc[div_salvar].cod + '">' + num_doc + '</div>&nbsp;&#45;\n\
                             <div id="tipo_doc_' + control_div_doc[div_salvar].cod + '">' + doc_name + '</div>';
    if (dt_emis !== "5000-12-31"){
        content_div_doc = content_div_doc + '&nbsp;&#45;&nbsp;<div id="dt_emiss_doc_' + control_div_doc[div_salvar].cod + '">' + dt_emis + '</div>';
    }else{
        content_div_doc = content_div_doc + '<div id="dt_emiss_doc_' + control_div_doc[div_salvar].cod + '"></div>';
    }
    if (org_ems !== "VAZIO"){
        content_div_doc = content_div_doc + '&nbsp;&#45;&nbsp;<div id="org_emiss_doc_' + control_div_doc[div_salvar].cod + '">' + org_ems + '</div> <br>';
    }
    else{
        content_div_doc = content_div_doc + '<div id="org_emiss_doc_' + control_div_doc[div_salvar].cod + '"></div> <br>';
    }

    $('#doc_' + control_div_doc[div_salvar].cod).html(content_div_doc);
    control_div_doc[div_salvar].excluida = 0;
    control_vetor_doc[div_salvar].numero = num_doc;
    control_vetor_doc[div_salvar].tipo_doc = doc_list;
    control_vetor_doc[div_salvar].dt_emiss = dt_emis;
    control_vetor_doc[div_salvar].org_emiss = org_ems;

    limpa_campos_doc_function();
}

function EntradaNumerico(evt) {
    var key_code = evt.keyCode  ? evt.keyCode  :
                   evt.charCode ? evt.charCode :
                   evt.which    ? evt.which    : void 0;

    // Habilita teclas <DEL>, <TAB>, <ENTER>, <ESC> , <BACKSPACE> , <-> e <subtract>
    if (key_code == 8  ||  key_code == 9  ||  key_code == 13  ||  key_code == 27  ||  key_code == 46 || key_code == 109 || key_code == 189) {
        return true;
    }
    // Habilita teclas <HOME>, <END>, mais as quatros setas de navegaÃ§Ã£o (cima, baixo, direta, esquerda)
    else if ((key_code >= 35)  &&  (key_code <= 40)) {
        return true;
    }
    // Habilita Números de 0 a 9
    // 48 a 57 sÃ£o os cÃ³digos para Números
    else if ((key_code >= 48)  &&  (key_code <= 57)) {
        return true;
    }
    return false;
}

function validarCPF(cpf) {

    cpf = cpf.replace(/[^\d]+/g,'');

    if(cpf == '') return false;

    // Elimina CPFs invalidos conhecidos
    if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || 
                            cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999")
            return false;

    // Valida 1o digito
    var add = 0;
    for (var i=0; i < 9; i ++){
        add += parseInt(cpf.charAt(i)) * (10 - i);
    }
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11){            
        rev = 0;
    }
    if (rev != parseInt(cpf.charAt(9))){
        return false;
    }

    // Valida 2o digito
    add = 0;
    for (i = 0; i < 10; i ++){
        add += parseInt(cpf.charAt(i)) * (11 - i);
    }
    var rev = 11 - (add % 11);
    if (rev == 10 || rev == 11){
        rev = 0;
    }
    if (rev != parseInt(cpf.charAt(10))){
        return false;
    }
    return true;
}

function validarCNPJ(cnpj) {

    cnpj = cnpj.replace(/[^\d]+/g,'');

    if (cnpj.length != 14 || cnpj == "00000000000000" || cnpj == "11111111111111" || cnpj == "22222222222222" || cnpj == "33333333333333" || cnpj == "44444444444444" || 
                             cnpj == "55555555555555" || cnpj == "66666666666666" || cnpj == "77777777777777" || cnpj == "88888888888888" || cnpj == "99999999999999"){
            return false;
    }

    // Valida DVs
    var tamanho = cnpj.length - 2;
    var numeros = cnpj.substring(0,tamanho);
    var digitos = cnpj.substring(tamanho);
    var soma = 0;
    var pos = tamanho - 7;
    for (var i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2){
            pos = 9;
        }
    }
    var resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0)){
        return false;
    }
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0,tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2){
            pos = 9;
        }
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1)){
        return false;
    }
    return true;
}

function vetor_end_inserido(endereco, complemento, bairro, cidade, uf, pais, cep, tipo_end){
    this.endereco = endereco;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cidade = cidade;
    this.uf = uf;
    this.pais = pais;
    this.cep = cep;
    this.tipo_end = tipo_end;    
}

var control_vetor_end = new Array();
control_vetor_end[0] = 0;
var control_vetor_end_tipo = new Array();
var control_vetor_end_pais = new Array();
var control_vetor_end_uf = new Array();

function insert_endereco_function() {
    clean_end = $('#div_end').html();
    var endereco = document.getElementById("endereco").value.trim();
    var complemento = document.getElementById("complemento").value.trim();
    var bairro = document.getElementById("bairro").value.trim();
    var cidade = document.getElementById("cidade").value.trim();
    var uf_list = $('#uf_list').val();
    var uf_nome = $('#uf_list :selected').text();
    var pais_list = $('#pais_list').val();
    var cep = document.getElementById("cep").value.trim();
    var tipo_end_list = $('#tipo_end_list').val();
    var tipo_end_nome = $('#tipo_end_list :selected').text();
    var insert = "X";

    var tipo_endereco_tipo_obj = $('#tipo_end_list');
    var tipo_endereco_tamanho_tipo = tipo_endereco_tipo_obj[0].length;
    for (var i=0; i<tipo_endereco_tamanho_tipo;i++){
        control_vetor_end_tipo[i] = document.getElementsByName("option_endereco_tipo")[i].text;
    }

    var tipo_endereco_pais_obj = $('#pais_list');
    var tipo_endereco_tamanho_pais = tipo_endereco_pais_obj[0].length;
    for (i=0; i<tipo_endereco_tamanho_pais;i++){
        control_vetor_end_pais[i] = document.getElementsByName("option_endereco_pais")[i].text;
    }

    var tipo_endereco_uf_obj = $('#uf_list');
    var tipo_endereco_tamanho_uf = tipo_endereco_uf_obj[0].length;
    for (i=0; i<tipo_endereco_tamanho_uf;i++){
        control_vetor_end_uf[i] = document.getElementsByName("option_endereco_uf")[i].text;
    }

    if (endereco === ""){
        alert('Necessário ingressar o LOGRADOURO do cliente.');
        document.getElementById("endereco").focus();
        return 0;
    }

    if (bairro === ""){
        alert('Necessário ingressar o BAIRRO do cliente.');
        document.getElementById("bairro").focus();
        return 0;
    }

    if (cidade === ""){
        alert('Necessário ingressar a CIDADE do cliente.');
        document.getElementById("cidade").focus();
        return 0;
    }

    if (uf_nome === "OUTRO" && complemento === ""){
        alert('Necessário escolher UF do cliente ou preencher em complemento em caso de OUTRO.');
        document.getElementById("complemento").focus();
        return 0;
    }

    if (cep === ""){
        alert('Necessário ingressar o CEP do cliente.');
        document.getElementById("cep").focus();
        return 0;
    }

    if (tipo_end_nome === ""){
        alert('Necessário escolher o TIPO DE ENDEREÇO do cliente.');
        document.getElementById("tip_end_list").focus();
        return 0;
    }

    if(complemento === null){
        complemento = "";
    }

    i = control_div_end.length;
    var j = 0;

    if (i !== 0){
        while (j < i && insert === "X"){
            if (control_div_end[j].excluida === 1){
                insert = j;
            }
            else{
                j++;
            }
        }
        if (insert === "X"){
            control_div_end[i] = new div_end_inseridas(i,0);
            control_vetor_end[i] = new vetor_end_inserido(endereco,complemento,bairro,cidade,uf_list,pais_list,cep,tipo_end_list);
        }
    }
    else{
        control_div_end[0] = new div_end_inseridas(0,0);
        control_vetor_end[0] = new vetor_end_inserido(endereco,complemento,bairro,cidade,uf_list,pais_list,cep,tipo_end_list);
    }

    if (insert === "X"){
        var content_div_end = $('#endereco_inserido').html();
        content_div_end = content_div_end + '<div id="end_' + control_div_end[i].cod + '" class="div_inseridos">\n\
                                                  <input type="radio" name="end_inserido" value="end_' + control_div_end[i].cod + '" onclick="change_end_button_function()">\n\
                                                  <div id="tipo_end_' + control_div_end[i].cod + '">' + tipo_end_nome + '</div>: \n\
                                                  <div id="endereco_' + control_div_end[i].cod + '">' + endereco + '</div> <br> </div>';

        $('#endereco_inserido').html(content_div_end);
    }
    else {
        var content_div_end = '<input type="radio" name="end_inserido" value="end_' + control_div_end[j].cod + '" onclick="change_end_button_function()">\n\
                                                  <div id="tipo_end_' + control_div_end[j].cod + '">' + tipo_end_nome + '</div>: \n\
                                                  <div id="endereco_' + control_div_end[j].cod + '">' + endereco + '</div> <br>';

        $('#end_' + control_div_end[j].cod).html(content_div_end);
        control_div_end[j].excluida = 0;
        control_vetor_end[j] = new vetor_end_inserido(endereco,complemento,bairro,cidade,uf_list,pais_list,cep,tipo_end_list);
    }
    limpa_campos_end_function();
    var content_div_bt = '<button type="button" id="incluir_endereco" onclick="insert_endereco_function()">Incluir</button>';
    $('#div_end_bt').html(content_div_bt);
    document.getElementById("endereco").focus();
}

function delete_endereco_function() {
    var div_select = $("input[type='radio'][name='end_inserido']:checked").val();
    var length = div_select.length;
    var div_deletar = div_select.charAt(length - 1);
    var tipo_end = $('#tipo_end_' + div_deletar).html();
    var endereco = $('#endereco_' + div_deletar).html();
    var content_div_end = $('#' + div_select).html();

    if (confirm('O endereço ' + tipo_end.trim() + ' ' + endereco.trim() + ' será apagado.')) {
        $('#' + div_select).html("");
        control_div_end[div_deletar].excluida = 1;
        control_vetor_end[div_deletar] = 0;
    } else {
        $('#' + div_select).html(content_div_end);
    }

    var content_div_bt = '<button type="button" id="incluir_endereco" onclick="insert_endereco_function()">Incluir</button>';
    $('#div_end_bt').html(content_div_bt);
    limpa_campos_end_function();
}

function change_end_button_function(){
    var content_div_bt = '<button type="button" id="incluir_endereco" onclick="insert_endereco_function()">Incluir</button>\n\
                          <button type="button" id="editar_endereco" onclick="editar_endereco_function()">Editar</button>\n\
                          <button type="button" id="delete_endereco" onclick="delete_endereco_function()">Excluir</button>';
    $('#div_end_bt').html(content_div_bt);
}

var div_end_editar;

function editar_endereco_function() {
    var content_div_bt = '<button type="button" id="salvar_endereco" onclick="salvar_endereco_function()">Salvar</button>\n\
                          <button type="button" id="cancelar_endereco" onclick="cancelar_endereco_function()">Cancelar</button>';
    $('#div_end_bt').html(content_div_bt);

    var div_select = $("input[type='radio'][name='end_inserido']:checked").val();
    var content_div_end = $('#' + div_select).html();
    var length = div_select.length;
    div_end_editar = div_select.charAt(length - 1);

    var endereco = control_vetor_end[div_end_editar].endereco;
    var complemento = control_vetor_end[div_end_editar].complemento;
    var bairro = control_vetor_end[div_end_editar].bairro;
    var cidade = control_vetor_end[div_end_editar].cidade;
    var uf = control_vetor_end[div_end_editar].uf;
    var pais = control_vetor_end[div_end_editar].pais;
    var cep = control_vetor_end[div_end_editar].cep;
    var tipo_end = control_vetor_end[div_end_editar].tipo_end;

    $('#' + div_select).html("");
    $('#' + div_select).html(content_div_end);

    content_div_end = "";

    content_div_end = 'Logradouro: <input type="text" class="size_97" id="endereco" value="' + endereco + '">\n\
                        <br>';
    if(complemento === null){
        content_div_end = content_div_end + 'Complemento: <input type="text" class="size_23" id="complemento">';
    }
    else{
        content_div_end = content_div_end + 'Complemento: <input type="text" class="size_23" id="complemento" value="' + complemento + '">';
    }
    content_div_end = content_div_end + 'Bairro: <input type="text" class="size_22" id="bairro" value="' + bairro + '">\n\
                        Cidade: <input type="text" class="size_22" id="cidade" value="' + cidade + '">\n\
                        <br>UF:&nbsp;';

    content_div_end = content_div_end + '<select id="uf_list" class="size_8">';
    for(var i=0;i<control_vetor_end_uf.length;i++){
        content_div_end = content_div_end + '<option value="' + Number(i+1) + '" name="option_endereco_uf"';
        if (Number(uf) === Number(i+1)){
            content_div_end = content_div_end + ' selected';
        }
        content_div_end = content_div_end + '>' + control_vetor_end_uf[i] + '</option>';
    }
    content_div_end = content_div_end + '</select>';
    content_div_end = content_div_end + '&nbsp;País:\n\
                        <select id="pais_list" class="size_20">';

    for(i=0;i<control_vetor_end_pais.length;i++){
        content_div_end = content_div_end + '<option value="' + Number(i+1) + '" name="option_endereco_pais"';
        if (Number(pais) === Number(i+1)){
            content_div_end = content_div_end + ' selected';
        }
        content_div_end = content_div_end + '>' + control_vetor_end_pais[i] + '</option>';
    }

    content_div_end = content_div_end + '</select>&nbsp;CEP:\n\
                        <input type="text" class="size_12" id="cep" maxlength="9" value="' + cep + '" onkeypress="javascript: return EntradaNumerico(event);">&nbsp;Tipo de EndereÃ§o:\n\
                        <select id="tipo_end_list" class="size_24">';
    for(i=0;i<control_vetor_end_tipo.length;i++){
    content_div_end = content_div_end + '<option value="' + Number(i+1) + '" name="option_endereco_tipo"';
    if (Number(tipo_end) === Number(i+1)){
        content_div_end = content_div_end + ' selected';
    }
    content_div_end = content_div_end + '>' + control_vetor_end_tipo[i] + '</option>';
    }
    content_div_end = content_div_end + '</select> <br>';

    $('#div_end').html("");
    $('#div_end').html(content_div_end);
}

function limpa_campos_end_function(){
    var content_div_end = clean_end;
    $('#div_end').html("");
    $('#div_end').html(content_div_end);
}

function cancelar_endereco_function(){
    var content_div_bt = '<button type="button" id="incluir_endereco" onclick="insert_endereco_function()">Incluir</button>';
    $('#div_end_bt').html(content_div_bt);

    limpa_campos_end_function();
}

function salvar_endereco_function(){
    var content_div_bt = '<button type="button" id="incluir_endereco" onclick="insert_endereco_function()">Incluir</button>';
    $('#div_end_bt').html(content_div_bt);

    var div_salvar = div_end_editar;

    $('#end_' + div_salvar).html("");    

    var endereco = document.getElementById("endereco").value.trim();
    var complemento = document.getElementById("complemento").value.trim();
    var bairro = document.getElementById("bairro").value.trim();
    var cidade = document.getElementById("cidade").value.trim();
    var uf_list = $('#uf_list').val();
    var uf_nome = $('#uf_list :selected').text();
    var pais_list = $('#pais_list').val();
    var cep = document.getElementById("cep").value.trim();
    var tipo_end_list = $('#tipo_end_list').val();
    var tipo_end_nome = $('#tipo_end_list :selected').text();

    if (endereco === ""){
        alert('Necessário ingressar o LOGRADOURO do cliente.');
        document.getElementById("endereco").focus();
        return 0;
    }

    if (bairro === ""){
        alert('Necessário ingressar o BAIRRO do cliente.');
        document.getElementById("bairro").focus();
        return 0;
    }

    if (cidade === ""){
        alert('Necessário ingressar a CIDADE do cliente.');
        document.getElementById("cidade").focus();
        return 0;
    }

    if (uf_nome === "OUTRO" && complemento === ""){
        alert('Necessário escolher UF do cliente ou preencher em complemento em caso de OUTRO.');
        document.getElementById("complemento").focus();
        return 0;
    }

    if (cep === ""){
        alert('Necessário ingressar o CEP do cliente.');
        document.getElementById("cep").focus();
        return 0;
    }

    if (tipo_end_nome === ""){
        alert('Necessário escolher o TIPO DE ENDEREÇO do cliente.');
        document.getElementById("tip_end_list").focus();
        return 0;
    }

    if(complemento === null){
        complemento = "";
    }

    var content_div_end = '<input type="radio" name="end_inserido" value="end_' + control_div_end[div_salvar].cod + '" onclick="change_end_button_function()">\n\
                                                  <div id="tipo_end_' + control_div_end[div_salvar].cod + '">' + tipo_end_nome + ': </div>\n\
                                                  <div id="endereco_' + control_div_end[div_salvar].cod + '">' + endereco + '</div> <br>';

    $('#end_' + control_div_end[div_salvar].cod).html(content_div_end);
    control_div_end[div_salvar].excluida = 0;

    control_vetor_end[div_salvar].endereco = endereco;
    control_vetor_end[div_salvar].complemento = complemento;
    control_vetor_end[div_salvar].bairro = bairro;
    control_vetor_end[div_salvar].cidade = cidade;
    control_vetor_end[div_salvar].uf = uf_list;
    control_vetor_end[div_salvar].pais = pais_list;
    control_vetor_end[div_salvar].cep = cep;
    control_vetor_end[div_salvar].tipo_end = tipo_end_list;

    limpa_campos_end_function();
}

function vetor_contato_inserido(ddd, numero, tipo_contato, cod_pais, nome, parentesco){
    this.ddd = ddd;
    this.numero = numero;
    this.tipo_contato = tipo_contato;
    this.cod_pais = cod_pais;
    this.nome = nome;
    this.parentesco = parentesco;
}

var control_vetor_contato = new Array();
control_vetor_contato[0] = 0;
var control_vetor_contato_tipo = new Array();
var control_vetor_contato_parentesco = new Array();

function insert_contatos_function() {
    clean_contato = $('#div_contato').html();
    var ddd = document.getElementById("ddd").value.trim();
    var numero = document.getElementById("numero_contato").value.trim();
    var tipo_contato = $('#tipocont_list').val();
    var tipo_contato_nome = $('#tipocont_list :selected').text();
    var cod_pais = document.getElementById("cod_pais").value.trim();
    var nome = document.getElementById("nome_contato").value.trim();
    var parentesco = $('#info_contato_list').val();
    var parentesco_nome = $('#info_contato_list :selected').text();
    var insert = "X";

    var tipo_contato_tipo_obj = $('#tipocont_list');
    var tipo_contato_tamanho = tipo_contato_tipo_obj[0].length;
    for (var i=0; i<tipo_contato_tamanho;i++){
        control_vetor_contato_tipo[i] = document.getElementsByName("option_contato_tipo")[i].text;
    }
    var tipo_contato_parentesco_obj = $('#info_contato_list');
    var tipo_contato_tamanho = tipo_contato_parentesco_obj[0].length;
    for (i=0; i<tipo_contato_tamanho;i++){
        control_vetor_contato_parentesco[i] = document.getElementsByName("option_parentesco_cargo")[i].text;
    }

    if (ddd === ""){
        alert('Necessário ingressar o DDD do contato.');
        document.getElementById("ddd").focus();
        return 0;
    }

    if (numero === ""){
        alert('Necessário ingressar o NÚMERO do contato.');
        document.getElementById("numero_contato").focus();
        return 0;
    }

    if (cod_pais === ""){
        alert('Necessário ingressar o COD. PAÍS do contato.');
        document.getElementById("cod_pais").focus();
        return 0;
    }

    if (nome === ""){
        alert('Necessário ingressar o NOME do contato.');
        document.getElementById("nome_contato").focus();
        return 0;
    }

    i = control_div_contato.length;
    var j = 0;

    if (i !== 0){
        while (j < i && insert === "X"){
            if (control_div_contato[j].excluida === 1){
                insert = j;
            }
            else{
                j++;
            }
        }
        if (insert === "X"){
            control_div_contato[i] = new div_contato_inseridas(i,0);
            control_vetor_contato[i] = new vetor_contato_inserido(ddd, numero, tipo_contato, cod_pais, nome, parentesco);
        }
    }
    else{
        control_div_contato[0] = new div_contato_inseridas(0,0);
        control_vetor_contato[0] = new vetor_contato_inserido(ddd, numero, tipo_contato, cod_pais, nome, parentesco);
    }

    if (insert === "X"){
        var content_div_contato = $('#contato_inserido').html();
        content_div_contato = content_div_contato + '<div id="contato_' + control_div_contato[i].cod + '" class="div_inseridos">\n\
                                                  <input type="radio" name="contato_inserido" value="contato_' + control_div_contato[i].cod + '" onclick="change_contato_button_function()">\n\
                                                  <div id="tipo_contato_' + control_div_contato[i].cod + '">' + tipo_contato_nome + '</div>: \n\
                                                  <div id="nome_contato_' + control_div_contato[i].cod + '">' + nome + '</div>&nbsp;+\n\
                                                  <div id="cod_pais_' + control_div_contato[i].cod + '">' + cod_pais + '</div>\n\
                                                  (<div id="ddd_contato_' + control_div_contato[i].cod + '">' + ddd + '</div>)\n\
                                                  <div id="numero_contato_' + control_div_contato[i].cod + '">' + numero + '</div>&nbsp;-\n\
                                                  <div id="grau_contato_' + control_div_contato[i].cod + '">' + parentesco_nome + '</div> <br> </div>';

        $('#contato_inserido').html(content_div_contato);
    }
    else {
        var content_div_contato = '<input type="radio" name="contato_inserido" value="contato_' + control_div_contato[j].cod + '" onclick="change_contato_button_function()">\n\
                                                  <div id="tipo_contato_' + control_div_contato[j].cod + '">' + tipo_contato_nome + '</div>: \n\
                                                  <div id="nome_contato_' + control_div_contato[j].cod + '">' + nome + '</div>&nbsp;+\n\
                                                  <div id="cod_pais_' + control_div_contato[j].cod + '">' + cod_pais + '</div>\n\
                                                  (<div id="ddd_contato_' + control_div_contato[j].cod + '">' + ddd + '</div>)\n\
                                                  <div id="numero_contato_' + control_div_contato[j].cod + '">' + numero + '</div>&nbsp;-\n\
                                                  <div id="grau_contato_' + control_div_contato[j].cod + '">' + parentesco_nome + '</div> <br>';

        $('#contato_' + control_div_contato[j].cod).html(content_div_contato);
        control_div_contato[j].excluida = 0;
        control_vetor_contato[j] = new vetor_contato_inserido(ddd, numero, tipo_contato, cod_pais, nome, parentesco);
    }
    limpa_campos_contato_function();
    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_function()">Incluir</button>';
    $('#div_contato_bt').html(content_div_bt);
    document.getElementById("ddd").focus();
}

function delete_contatos_function() {
    var div_select = $("input[type='radio'][name='contato_inserido']:checked").val();
    var length = div_select.length;
    var div_deletar = div_select.charAt(length - 1);

    var tipo_contato = $('#tipo_contato_' + div_deletar).html();
    var nome = $('#nome_contato_' + div_deletar).html();
    var cod_pais = $('#cod_pais_' + div_deletar).html();
    var ddd = $('#ddd_contato_' + div_deletar).html();
    var numero = $('#numero_contato_' + div_deletar).html();

    var content_div_contato = $('#' + div_select).html();

    if (confirm('O contato ' + tipo_contato.trim() + ': ' + nome.trim() + ' +' + cod_pais.trim() + '(' + ddd.trim() + ')' + numero.trim() + ' será apagado.')) {
        $('#' + div_select).html("");
        control_div_contato[div_deletar].excluida = 1;
        control_vetor_contato[div_deletar] = 0;
    } else {
        $('#' + div_select).html(content_div_contato);
    }

    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_function()">Incluir</button>';
    $('#div_contato_bt').html(content_div_bt);
    limpa_campos_contato_function();
}

function change_contato_button_function(){
    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_function()">Incluir</button>\n\
                          <button type="button" id="editar_contato" onclick="editar_contatos_function()">Editar</button>\n\
                          <button type="button" id="delete_contato" onclick="delete_contatos_function()">Excluir</button>';
    $('#div_contato_bt').html(content_div_bt);
}

var div_contato_editar;

function editar_contatos_function() {
    var content_div_bt = '<button type="button" id="salvar_contato" onclick="salvar_contatos_function()">Salvar</button>\n\
                          <button type="button" id="cancelar_contato" onclick="cancelar_contatos_function()">Cancelar</button>';
    $('#div_contato_bt').html(content_div_bt);

    var div_select = $("input[type='radio'][name='contato_inserido']:checked").val();
    var content_div_contato = $('#' + div_select).html();
    var length = div_select.length;
    div_contato_editar = div_select.charAt(length - 1);

    var ddd = control_vetor_contato[div_contato_editar].ddd;
    var numero = control_vetor_contato[div_contato_editar].numero;
    var tipo_contato = control_vetor_contato[div_contato_editar].tipo_contato;
    var cod_pais = control_vetor_contato[div_contato_editar].cod_pais;
    var nome = control_vetor_contato[div_contato_editar].nome;
    var parentesco = control_vetor_contato[div_contato_editar].parentesco;

    $('#' + div_select).html("");
    $('#' + div_select).html(content_div_contato);

    content_div_contato = "";

    content_div_contato = 'DDD:<input type="text" class="size_5" id="ddd" maxlength="3" onkeypress="javascript: return EntradaNumerico(event);" value="' + ddd + '">\n\
                        Número:<input type="text" class="size_19" id="numero_contato" maxlength="10" onkeypress="javascript: return EntradaNumerico(event);" value="' + numero + '">\n\
                        Tipo do Contato:\n\
                        <select id="tipocont_list" class="size_21">';
    for(var i=0;i<control_vetor_contato_tipo.length;i++){
        content_div_contato = content_div_contato + '<option value="' + Number(i+1) + '" name="option_contato_tipo"';
        if (Number(tipo_contato) === Number(i+1)){
            content_div_contato = content_div_contato + ' selected';
        }
        content_div_contato = content_div_contato + '>' + control_vetor_contato_tipo[i] + '</option>';
    }
    content_div_contato = content_div_contato + '</select>\n\
                        Cod. País: <input type="text" class="size_5" id="cod_pais" maxlength="3" onkeypress="javascript: return EntradaNumerico(event);" value="' + cod_pais + '">\n\
                        <br>Nome:<input type="text" class="size_32" id="nome_contato" value="' + nome + '">\n\
                        Parentesco / Cargo:\n\
                        <select id="info_contato_list" class="size_35">';
    for(i=0;i<control_vetor_contato_parentesco.length;i++){
        content_div_contato = content_div_contato + '<option value="' + Number(i+1) + '" name="option_parentesco_cargo"';
        if (Number(parentesco) === Number(i+1)){
            content_div_contato = content_div_contato + ' selected';
        }
        content_div_contato = content_div_contato + '>' + control_vetor_contato_parentesco[i] + '</option>';
    }
    content_div_contato = content_div_contato + '</select>';

    $('#div_contato').html("");
    $('#div_contato').html(content_div_contato);
}

function limpa_campos_contato_function(){
    var content_div_contato = clean_contato;
    $('#div_contato').html("");
    $('#div_contato').html(content_div_contato);
}

function cancelar_contatos_function(){
    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_function()">Incluir</button>';
    $('#div_contato_bt').html(content_div_bt);

    limpa_campos_contato_function();
}

function salvar_contatos_function(){
    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_function()">Incluir</button>';
    $('#div_contato_bt').html(content_div_bt);

    var div_salvar = div_contato_editar;

    $('#contato_' + div_salvar).html("");    

    var ddd = document.getElementById("ddd").value.trim();
    var numero = document.getElementById("numero_contato").value.trim();
    var tipo_contato = $('#tipocont_list').val();
    var tipo_contato_nome = $('#tipocont_list :selected').text();
    var cod_pais = document.getElementById("cod_pais").value.trim();
    var nome = document.getElementById("nome_contato").value.trim();
    var parentesco = $('#info_contato_list').val();
    var parentesco_nome = $('#info_contato_list :selected').text();

    if (ddd === ""){
        alert('Necessário ingressar o DDD do contato.');
        document.getElementById("ddd").focus();
        return 0;
    }

    if (numero === ""){
        alert('Necessário ingressar o NÚMERO do contato.');
        document.getElementById("numero_contato").focus();
        return 0;
    }

    if (cod_pais === ""){
        alert('Necessário ingressar o COD. PAÍS do contato.');
        document.getElementById("cod_pais").focus();
        return 0;
    }

    if (nome === ""){
        alert('Necessário ingressar o NOME do contato.');
        document.getElementById("nome_contato").focus();
        return 0;
    }

    var content_div_contato = '<input type="radio" name="contato_inserido" value="contato_' + control_div_contato[div_salvar].cod + '" onclick="change_contato_button_function()">\n\
                                                  <div id="tipo_contato_' + control_div_contato[div_salvar].cod + '">' + tipo_contato_nome + '</div>: \n\
                                                  <div id="nome_contato_' + control_div_contato[div_salvar].cod + '">' + nome + '</div>&nbsp;+\n\
                                                  <div id="cod_pais_' + control_div_contato[div_salvar].cod + '">' + cod_pais + '</div>\n\
                                                  (<div id="ddd_contato_' + control_div_contato[div_salvar].cod + '">' + ddd + '</div>)&nbsp;\n\
                                                  <div id="numero_contato_' + control_div_contato[div_salvar].cod + '">' + numero + '</div>&nbsp;-\n\
                                                  <div id="grau_contato_' + control_div_contato[div_salvar].cod + '">' + parentesco_nome + '</div> <br>';

    $('#contato_' + control_div_contato[div_salvar].cod).html(content_div_contato);
    control_div_contato[div_salvar].excluida = 0;

    control_vetor_contato[div_salvar].ddd = ddd;
    control_vetor_contato[div_salvar].numero = numero;
    control_vetor_contato[div_salvar].tipo_contato = tipo_contato;
    control_vetor_contato[div_salvar].cod_pais = cod_pais;
    control_vetor_contato[div_salvar].nome = nome;
    control_vetor_contato[div_salvar].parentesco = parentesco;

    limpa_campos_contato_function();
}

function vetor_email_inserido(email){
    this.email = email;
}

var control_vetor_email = new Array();
control_vetor_email[0] = 0;

function insert_emails_function() {
    clean_email = $('#div_email').html();
    var email = document.getElementById("email").value.trim();
    var insert = "X";

    if (email === ""){
        alert('Necessário ingressar o EMAIL do contato.');
        document.getElementById("email").focus();
        return 0;
    }

    var i = control_div_email.length;
    var j = 0;

    if (i !== 0){
        while (j < i && insert === "X"){
            if (control_div_email[j].excluida === 1){
                insert = j;
            }
            else{
                j++;
            }
        }
        if (insert === "X"){
            control_div_email[i] = new div_contato_inseridas(i,0);
			control_vetor_email[i] = new vetor_email_inserido(email);
        }
    }
    else{
        control_div_email[0] = new div_contato_inseridas(0,0);
        control_vetor_email[0] = new vetor_email_inserido(email);
    }

    if (insert === "X"){
        var content_div_email = $('#emails_inserido').html();
        content_div_email = content_div_email + '<div id="email_' + control_div_email[i].cod + '" class="div_inseridos">\n\
                                                  <input type="radio" name="email_inserido" value="email_' + control_div_email[i].cod + '" onclick="change_email_button_function()">\n\
                                                  <div id="nome_email_' + control_div_email[i].cod + '">' + email + '</div> <br> </div>';

        $('#emails_inserido').html(content_div_email);
    }
    else {
        var content_div_email = '<input type="radio" name="email_inserido" value="email_' + control_div_email[j].cod + '" onclick="change_email_button_function()">\n\
                                                  <div id="nome_email_' + control_div_email[j].cod + '">' + email + '</div> <br>';

        $('#email_' + control_div_email[j].cod).html(content_div_email);
        control_div_email[j].excluida = 0;
        control_vetor_email[j] = new vetor_email_inserido(email);
    }
    limpa_campos_email_function();
    var content_div_bt = '<button type="button" id="incluir_email" onclick="insert_emails_function()">Incluir</button>';
    $('#div_email_bt').html(content_div_bt);
    document.getElementById("email").focus();
}

function delete_emails_function() {
    var div_select = $("input[type='radio'][name='email_inserido']:checked").val();
    var length = div_select.length;
    var div_deletar = div_select.charAt(length - 1);

    var email = $('#nome_email_' + div_deletar).html();

    var content_div_email = $('#' + div_select).html();

    if (confirm('O email ' + email.trim() + ' será apagado.')) {
        $('#' + div_select).html("");
        control_div_email[div_deletar].excluida = 1;
        control_vetor_email[div_deletar] = 0;
    } else {
        $('#' + div_select).html(content_div_email);
    }

    var content_div_bt = '<button type="button" id="incluir_email" onclick="insert_emails_function()">Incluir</button>';
    $('#div_email_bt').html(content_div_bt);
    limpa_campos_email_function();
}

function change_email_button_function(){
    var content_div_bt = '<button type="button" id="incluir_email" onclick="insert_emails_function()">Incluir</button>\n\
                          <button type="button" id="editar_email" onclick="editar_emails_function()">Editar</button>\n\
                          <button type="button" id="delete_email" onclick="delete_emails_function()">Excluir</button>';
    $('#div_email_bt').html(content_div_bt);
}

var div_emails_editar;

function editar_emails_function() {
    var content_div_bt = '<button type="button" id="salvar_email" onclick="salvar_emails_function()">Salvar</button>\n\
                          <button type="button" id="cancelar_email" onclick="cancelar_emails_function()">Cancelar</button>';
    $('#div_email_bt').html(content_div_bt);

    var div_select = $("input[type='radio'][name='email_inserido']:checked").val();
    var content_div_email = $('#' + div_select).html();
    var length = div_select.length;
    div_emails_editar = div_select.charAt(length - 1);

    var email = control_vetor_email[div_emails_editar].email;

    $('#' + div_select).html("");
    $('#' + div_select).html(content_div_email);

    content_div_email = "";
    content_div_email = '<input type="email" id="email" class="size_100" value="' + email + '">';

    $('#div_email').html("");
    $('#div_email').html(content_div_email);
}

function limpa_campos_email_function(){
    var content_div_email = clean_email;
    $('#div_email').html("");
    $('#div_email').html(content_div_email);
}

function cancelar_emails_function(){
    var content_div_bt = '<button type="button" id="incluir_email" onclick="insert_emails_function()">Incluir</button>';
    $('#div_email_bt').html(content_div_bt);

    limpa_campos_email_function();
}

function salvar_emails_function(){
    var content_div_bt = '<button type="button" id="incluir_email" onclick="insert_emails_function()">Incluir</button>';
    $('#div_email_bt').html(content_div_bt);

    var div_salvar = div_emails_editar;

    $('#email_' + div_salvar).html("");    

    var email = document.getElementById("email").value.trim();

    if (email === ""){
        alert('Necessário ingressar o EMAIL do contato.');
        document.getElementById("email").focus();
        return 0;
    }

    var content_div_email = '<input type="radio" name="email_inserido" value="email_' + control_div_email[div_salvar].cod + '" onclick="change_email_button_function()">\n\
                                                  <div id="nome_email_' + control_div_email[div_salvar].cod + '">' + email + '</div> <br>';

    $('#email_' + control_div_email[div_salvar].cod).html(content_div_email);
    control_div_email[div_salvar].excluida = 0;
    control_vetor_email[div_salvar].email = email;

    limpa_campos_email_function();
}

var control_vetor_doc_json = new Array();
function ajustar_vetor_doc_function(){

	var length = control_vetor_doc.length;
	var flag;
	var j;
	for(var x=0; x<length; x++)
		control_vetor_doc_json[x] = control_vetor_doc[x];

	for(var i=0; i<length; i++){
		if(control_vetor_doc_json[i] === 0){
			flag = true;
			j = i + 1;
			while(flag && j<length){
				if(control_vetor_doc_json[j] !== 0){
					control_vetor_doc_json[i] = control_vetor_doc_json[j];
					control_vetor_doc_json[j] = 0;
					flag = false;
				}
				j++;
			}
		}
	}
	for(i=0;i<length;i++){
		if(control_vetor_doc_json[i] === 0)
			break;
	}
	control_vetor_doc_json.length = i;
}

var control_vetor_end_json = new Array();
function ajustar_vetor_end_function(){

	var length = control_vetor_end.length;
	var flag;
	var j;
	for(var x=0; x<length; x++)
		control_vetor_end_json[x] = control_vetor_end[x];

	for(var i=0; i<length; i++){
		if(control_vetor_end_json[i] === 0){
			flag = true;
			j = i + 1;
			while(flag && j<length){
				if(control_vetor_end_json[j] !== 0){
					control_vetor_end_json[i] = control_vetor_end_json[j];
					control_vetor_end_json[j] = 0;
					flag = false;
				}
				j++;
			}
		}
	}
	for(i=0;i<length;i++){
		if(control_vetor_end_json[i] === 0)
			break;
	}
	control_vetor_end_json.length = i;
}

var control_vetor_contato_json = new Array();
function ajustar_vetor_contato_function(){

	var length = control_vetor_contato.length;
	var flag;
	var j;
	for(var x=0; x<length; x++)
		control_vetor_contato_json[x] = control_vetor_contato[x];

	for(var i=0; i<length; i++){
		if(control_vetor_contato_json[i] === 0){
			flag = true;
			j = i + 1;
			while(flag && j<length){
				if(control_vetor_contato_json[j] !== 0){
					control_vetor_contato_json[i] = control_vetor_contato_json[j];
					control_vetor_contato_json[j] = 0;
					flag = false;
				}
				j++;
			}
		}
	}
	for(i=0;i<length;i++){
		if(control_vetor_contato_json[i] === 0)
			break;
	}
	control_vetor_contato_json.length = i;
}

var control_vetor_email_json = new Array();
function ajustar_vetor_email_function(){

	var length = control_vetor_email.length;
	var flag;
	var j;
	for(var x=0; x<length; x++)
		control_vetor_email_json[x] = control_vetor_email[x];
		
	for(var i=0; i<length; i++){
		if(control_vetor_email_json[i] === 0){
			flag = true;
			j = i + 1;
			while(flag && j<length){
				if(control_vetor_email_json[j] !== 0){
					control_vetor_email_json[i] = control_vetor_email_json[j];
					control_vetor_email_json[j] = 0;
					flag = false;
				}
				j++;
			}
		}
	}
	for(i=0;i<length;i++){
		if(control_vetor_email_json[i] === 0)
			break;
	}
	control_vetor_email_json.length = i;
}

function doc_carregado(num_doc, tipo_doc, dt_emiss, org_emiss){
    this.num_doc = num_doc;
    this.tipo_doc = tipo_doc;
    this.dt_emiss = dt_emiss;
    this.org_emiss = org_emiss;
}

function end_carregado(logradouro, complemento, bairro, cidade, uf, pais, cep, tipo_end){
    this.logradouro = logradouro;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cidade = cidade;
    this.uf = uf;
    this.pais = pais;
    this.cep = cep;
    this.tipo_end = tipo_end;
}

function contato_carregado(ddd, numero, tipo_contato, cod_pais, nome, grau_contato){
    this.ddd = ddd;
    this.numero = numero;
    this.tipo_contato = tipo_contato;
    this.cod_pais = cod_pais;
    this.nome = nome;
    this.grau_contato = grau_contato;
}

function email_carregado(email){
    this.email = email;
}

var doc_carregado_array = new Array();
var end_carregado_array = new Array();
var contato_carregado_array = new Array();
var email_carregado_array = new Array();

function carregar_dados_consulta_cliente_function(){
    var i;
    var j;
    /*Carregar documentos*/
    var div_doc_inserido_obj = $("#docs_inserido");
    clean_doc = $('#div_doc').html();
    var tamanho_doc = div_doc_inserido_obj[0].childElementCount;
    for(i=0;i<tamanho_doc;i++){
        doc_carregado_array[i] = new doc_carregado();
        doc_carregado_array[i].num_doc = $('#num_doc_oculta_' + i).html().trim();
        doc_carregado_array[i].tipo_doc = $('#tipo_doc_oculta_' + i).html().trim();
        doc_carregado_array[i].dt_emiss = $('#dt_emiss_doc_oculta_' + i).html().trim();
        doc_carregado_array[i].org_emiss = $('#org_emiss_doc_oculta_' + i).html().trim();
    }
    var tipo_doc_tipo_obj = $('#tipodoc_list');
    var tipo_doc_tamanho_tipo = tipo_doc_tipo_obj[0].length;
    for (i=0; i<tipo_doc_tamanho_tipo;i++){
        control_vetor_doc_tipo[i] = document.getElementsByName("option_documento_tipo")[i].text;
    }
    for(i=0;i<tamanho_doc;i++){
    	control_div_doc[i] = new div_doc_inseridas(0, 0);
    	control_vetor_doc[i] = new vetor_doc_inserido();
        control_vetor_doc[i].numero = doc_carregado_array[i].num_doc;
        for(j=0;j<tipo_doc_tamanho_tipo;j++){
            if(control_vetor_doc_tipo[j] === doc_carregado_array[i].tipo_doc){
                control_vetor_doc[i].tipo_doc = Number(j+1);
                break;
            }
        }
        if(doc_carregado_array[i].dt_emiss !== "5000-12-31"){
        	control_vetor_doc[i].dt_emiss = doc_carregado_array[i].dt_emiss;
        }else{
        	control_vetor_doc[i].dt_emiss = "";
        }
        if(doc_carregado_array[i].org_emiss !== "VAZIO"){
        	control_vetor_doc[i].org_emiss = doc_carregado_array[i].org_emiss;
        }else{
        	control_vetor_doc[i].org_emiss = "";
        }
    }
    $('#docs_inserido').html("");

    var content_div_doc = "";

    for(i=0;i<tamanho_doc;i++){
    	content_div_doc = content_div_doc + '<div id="doc_' + i + '" class="div_inseridos">\n\
    	<input type="radio" name="doc_inserido" value="doc_' + i + '" onclick="change_doc_button_function()">\n\
        <div id="num_doc_' + i + '">' + control_vetor_doc[i].numero + '</div>&nbsp;&#45;\n\
        <div id="tipo_doc_' + i + '">' + control_vetor_doc_tipo[Number(control_vetor_doc[i].tipo_doc) - 1] + '</div>';
		if (control_vetor_doc[i].dt_emiss !== ""){
		content_div_doc = content_div_doc + '&nbsp;&#45;&nbsp;<div id="dt_emiss_doc_' + i + '">' + control_vetor_doc[i].dt_emiss + '</div>';
		}else{
		content_div_doc = content_div_doc + '<div id="dt_emiss_doc_' + i + '"></div>';
		}
		if (control_vetor_doc[i].org_emiss !== ""){
		content_div_doc = content_div_doc + '&nbsp;&#45;&nbsp;<div id="org_emiss_doc_' + i + '">' + control_vetor_doc[i].org_emiss + '</div> <br> </div>';
		}
		else{
		content_div_doc = content_div_doc + '<div id="org_emiss_doc_' + i + '"></div> <br> </div>';
		}
    }
    $('#docs_inserido').html(content_div_doc);
    
/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
    /*Carregar endereços*/
    var div_end_inserido_obj = $("#endereco_inserido");
    var tamanho_end = div_end_inserido_obj[0].childElementCount;
    clean_end = $('#div_end').html();
    for(i=0;i<tamanho_end;i++){
        end_carregado_array[i] = new end_carregado();
        end_carregado_array[i].logradouro = $('#endereco_oculta_' + i).html().trim();
        end_carregado_array[i].complemento = $('#complemento_oculta_' + i).html().trim();
        end_carregado_array[i].bairro = $('#bairro_oculta_' + i).html().trim();
        end_carregado_array[i].cidade = $('#cidade_oculta_' + i).html().trim();
        end_carregado_array[i].uf = $('#uf_oculta_' + i).html().trim();
        end_carregado_array[i].pais = $('#pais_oculta_' + i).html().trim();
        end_carregado_array[i].cep = $('#cep_oculta_' + i).html().trim();
        end_carregado_array[i].tipo_end = $('#tipo_end_oculta_' + i).html().trim();
    }
    var tipo_endereco_tipo_obj = $('#tipo_end_list');
    var tipo_endereco_tamanho_tipo = tipo_endereco_tipo_obj[0].length;
    for (var i=0; i<tipo_endereco_tamanho_tipo;i++){
        control_vetor_end_tipo[i] = document.getElementsByName("option_endereco_tipo")[i].text;
    }
    var tipo_endereco_pais_obj = $('#pais_list');
    var tipo_endereco_tamanho_pais = tipo_endereco_pais_obj[0].length;
    for (i=0; i<tipo_endereco_tamanho_pais;i++){
        control_vetor_end_pais[i] = document.getElementsByName("option_endereco_pais")[i].text;
    }
    var tipo_endereco_uf_obj = $('#uf_list');
    var tipo_endereco_tamanho_uf = tipo_endereco_uf_obj[0].length;
    for (i=0; i<tipo_endereco_tamanho_uf;i++){
        control_vetor_end_uf[i] = document.getElementsByName("option_endereco_uf")[i].text;
    }
    for(i=0;i<tamanho_end;i++){
    	control_div_end[i] = new div_end_inseridas(0, 0);
    	control_vetor_end[i] = new vetor_end_inserido();
        control_vetor_end[i].endereco = end_carregado_array[i].logradouro;
        control_vetor_end[i].complemento = end_carregado_array[i].complemento;
        control_vetor_end[i].bairro = end_carregado_array[i].bairro;
        control_vetor_end[i].cidade = end_carregado_array[i].cidade;
        control_vetor_end[i].uf = end_carregado_array[i].uf;
        control_vetor_end[i].pais = end_carregado_array[i].pais;
        control_vetor_end[i].cep = end_carregado_array[i].cep;
        control_vetor_end[i].tipo_end = end_carregado_array[i].tipo_end;
    }
    $('#endereco_inserido').html("");

    var content_div_end = "";

    for(i=0;i<tamanho_end;i++){

    	content_div_end = content_div_end + '<div id="end_' + i + '" class="div_inseridos">\n\
							<input type="radio" name="end_inserido" value="end_' + i + '" onclick="change_end_button_function()">\n\
							<div id="tipo_end_' + i + '">' + control_vetor_end_tipo[Number(control_vetor_end[i].tipo_end) - 1] + ': </div>\n\
							<div id="endereco_' + i + '">' + control_vetor_end[i].endereco + '</div> <br>\n\
						</div>';
    }
    $('#endereco_inserido').html(content_div_end);
        
/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
    /*Carregar contatos*/
    var div_contato_inserido_obj = $("#contato_inserido");
    var tamanho_contato = div_contato_inserido_obj[0].childElementCount;
    clean_contato = $('#div_contato').html();
    for(i=0;i<tamanho_contato;i++){
        contato_carregado_array[i] = new contato_carregado();
        contato_carregado_array[i].ddd = $('#ddd_contato_oculta_' + i).html().trim();
        contato_carregado_array[i].numero = $('#numero_contato_oculta_' + i).html().trim();
        contato_carregado_array[i].tipo_contato = $('#tipo_contato_oculta_' + i).html().trim();
        contato_carregado_array[i].cod_pais = $('#cod_pais_oculta_' + i).html().trim();
        contato_carregado_array[i].nome = $('#nome_contato_oculta_' + i).html().trim();
        contato_carregado_array[i].grau_contato = $('#grau_contato_oculta_' + i).html().trim();
    }
    var tipo_contato_tipo_obj = $('#tipocont_list');
    var tipo_contato_tamanho_tipo = tipo_contato_tipo_obj[0].length;
    for (i=0; i<tipo_contato_tamanho_tipo;i++){
        control_vetor_contato_tipo[i] = document.getElementsByName("option_contato_tipo")[i].text;
    }
    var tipo_contato_parentesco_obj = $('#info_contato_list');
    var tipo_contato_tamanho_parentesco = tipo_contato_parentesco_obj[0].length;
    for (i=0; i<tipo_contato_tamanho_parentesco;i++){
        control_vetor_contato_parentesco[i] = document.getElementsByName("option_parentesco_cargo")[i].text;
    }
    for(i=0;i<tamanho_contato;i++){
    	control_div_contato[i] = new div_contato_inseridas(0, 0);
    	control_vetor_contato[i] = new vetor_contato_inserido();
        control_vetor_contato[i].ddd = contato_carregado_array[i].ddd;
        control_vetor_contato[i].numero = contato_carregado_array[i].numero;
        for(j=0;j<tipo_contato_tamanho_tipo;j++){
            if(control_vetor_contato_tipo[j] === contato_carregado_array[i].tipo_contato){
                control_vetor_contato[i].tipo_contato = Number(j+1);
                break;
            }
        }
        control_vetor_contato[i].cod_pais = contato_carregado_array[i].cod_pais;
        control_vetor_contato[i].nome = contato_carregado_array[i].nome;
        for(j=0;j<tipo_contato_tamanho_parentesco;j++){
            if(control_vetor_contato_parentesco[j] === contato_carregado_array[i].grau_contato){
                control_vetor_contato[i].parentesco = Number(j+1);
                break;
            }
        }
    }

    $('#contato_inserido').html("");

    var content_div_contato = "";

    for(i=0;i<tamanho_contato;i++){
    	content_div_contato = content_div_contato + '<div id="contato_' + i + '" class="div_inseridos">\n\
        <input type="radio" name="contato_inserido" value="contato_' + i + '" onclick="change_contato_button_function()">\n\
        <div id="tipo_contato_' + i + '">' + control_vetor_contato_tipo[Number(control_vetor_contato[i].tipo_contato) - 1] + '</div>: \n\
        <div id="nome_contato_' + i + '">' + control_vetor_contato[i].nome + '</div>&nbsp;+\n\
        <div id="cod_pais_' + i + '">' + control_vetor_contato[i].cod_pais + '</div>\n\
        (<div id="ddd_contato_' + i + '">' + control_vetor_contato[i].ddd + '</div>)\n\
        <div id="numero_contato_' + i + '">' + control_vetor_contato[i].numero + '</div>&nbsp;-\n\
        <div id="grau_contato_' + i + '">' + control_vetor_contato_parentesco[Number(control_vetor_contato[i].parentesco) - 1] + '</div> <br> </div>';
    }

    $('#contato_inserido').html(content_div_contato);

/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
    /*Carregar emails*/
    var div_email_inserido_obj = $("#emails_inserido");
    var tamanho_email = div_email_inserido_obj[0].childElementCount;
    clean_email = $('#div_email').html();
    for(i=0;i<tamanho_email;i++){
        email_carregado_array[i] = new email_carregado();
        email_carregado_array[i].email = $('#nome_email_oculta_' + i).html().trim();
    }
    for(i=0;i<tamanho_email;i++){
    	control_div_email[i] = new div_email_inseridas(0, 0);
    	control_vetor_email[i] = new vetor_email_inserido();
        control_vetor_email[i].email = email_carregado_array[i].email;
    }
    $('#emails_inserido').html("");

    var content_div_email = "";

    for(i=0;i<tamanho_email;i++){
    	content_div_email = content_div_email + '<div id="email_' + i + '" class="div_inseridos">\n\
        <input type="radio" name="email_inserido" value="email_' + i + '" onclick="change_email_button_function()">\n\
        <div id="nome_email_' + i + '">' + control_vetor_email[i].email + '</div> <br> </div>';
    }

    $('#emails_inserido').html(content_div_email);
}

function operacional_cadastrar_cliente_cadastrar_function(){
	ajustar_vetor_doc_function();
	ajustar_vetor_end_function();
	ajustar_vetor_contato_function();
	ajustar_vetor_email_function();
	if(confirm('Deseja realizar o ingresso do Cliente?')){
		var values = $("input[type='radio'][name='pessoa']:checked").val();
		var tipo_pessoa;
		if(values === "pessoafisica"){
			tipo_pessoa = 0;
		}else{
			tipo_pessoa = 1;
		}
		var nome = document.getElementById("nome_razaosocial");
		var apelido  = document.getElementById("nomefantasia");
		var dt_nascimento = document.getElementById("data_nasc");
		var site = document.getElementById("url_site");
		var cod_vendedor = $('#vendedor_list :selected').val();
		var cod_usuario = document.getElementById("cod_usuario");

		if(nome.value.trim() === ""){
			alert("Necessário ingressar NOME ou RAZÃO SOCIAL do Cliente.");
			document.getElementById("nome_razaosocial").focus();
			return 0;
		}
		if(apelido.value.trim() === ""){
			if(tipo_pessoa === 1){
				alert("Necessário ingressar NOME FANTASIA do Cliente.");
				document.getElementById("nomefantasia").focus();
				return 0;
			}else{
				apelido.value = "";
			}
		}
		if(dt_nascimento.value.trim() === ""){
			alert("Necessário ingressar DATA DE NASCIMENTO do Cliente");
			document.getElementById("data_nasc").focus();
			return 0;
		}
		if(site.value.trim() === ""){
			site.value = "";
		}

		if(control_vetor_doc_json[0] === 0){
			alert("Necessário ingressar DOCUMENTO do Cliente");
			document.getElementById("numero_documento").focus();
			return 0;
		}

		var adress_aux;
		var documentoLength = control_vetor_doc_json.length;
		adress_aux = '&QDOC=' + documentoLength;
		for(var i=0;i<documentoLength;i++){
			if(control_vetor_doc_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPODOC_'+ i + '=' + control_vetor_doc_json[i].tipo_doc.trim();
				adress_aux = adress_aux + '&NUMDOC_'+ i + '=' + control_vetor_doc_json[i].numero.trim();
				adress_aux = adress_aux + '&DTDOC_'+ i + '=' + control_vetor_doc_json[i].dt_emiss.trim();
				adress_aux = adress_aux + '&ORGDOC_'+ i + '=' + control_vetor_doc_json[i].org_emiss.trim();
			}
		}

		if(control_vetor_end_json[0] === 0){
			alert("Necessário ingressar ENDEREÇO do Cliente");
			document.getElementById("endereco").focus();
			return 0;
		}

		var enderecoLength = control_vetor_end_json.length;
		adress_aux = adress_aux + '&QEND=' + enderecoLength;
		for(i=0;i<enderecoLength;i++){
			if(control_vetor_end_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&END_'+ i + '=' + control_vetor_end_json[i].endereco.trim();
				adress_aux = adress_aux + '&BAIRRO_'+ i + '=' + control_vetor_end_json[i].bairro.trim();
				adress_aux = adress_aux + '&CIDADE_'+ i + '=' + control_vetor_end_json[i].cidade.trim();
				adress_aux = adress_aux + '&UF_'+ i + '=' + control_vetor_end_json[i].uf.trim();
				adress_aux = adress_aux + '&PAIS_'+ i + '=' + control_vetor_end_json[i].pais.trim();
				adress_aux = adress_aux + '&COMP_'+ i + '=' + control_vetor_end_json[i].complemento.trim();
				adress_aux = adress_aux + '&CEP_'+ i + '=' + control_vetor_end_json[i].cep.trim();
				adress_aux = adress_aux + '&TIPOEND_'+ i + '=' + control_vetor_end_json[i].tipo_end.trim();
			}
		}

		if(control_vetor_contato_json[0] === 0){
			alert("Necessário ingressar CONTATO do Cliente");
			document.getElementById("ddd").focus();
			return 0;
		}

		var contatoLength = control_vetor_contato_json.length;
		adress_aux = adress_aux + '&QCTO=' + contatoLength;
		for(i=0;i<contatoLength;i++){
			if(control_vetor_contato_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPOCTO_'+ i + '=' + control_vetor_contato_json[i].tipo_contato.trim();
				adress_aux = adress_aux + '&DDD_'+ i + '=' + control_vetor_contato_json[i].ddd.trim();
				adress_aux = adress_aux + '&NUMCTO_'+ i + '=' + control_vetor_contato_json[i].numero.trim();
				adress_aux = adress_aux + '&PAISCTO_'+ i + '=' + control_vetor_contato_json[i].cod_pais.trim();
				adress_aux = adress_aux + '&NOMECTO_'+ i + '=' + control_vetor_contato_json[i].nome.trim();
				adress_aux = adress_aux + '&PARENCTO_'+ i + '=' + control_vetor_contato_json[i].parentesco.trim();
			}
		}

		if(control_vetor_email_json[0] === 0){
			alert("Necessário ingressar EMAIL do Cliente");
			document.getElementById("email").focus();
			return 0;
		}

		var emailLength = control_vetor_email_json.length;
		adress_aux = adress_aux + '&QMAIL=' + emailLength;
		for(i=0;i<emailLength;i++){
			if(control_vetor_email_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&MAIL_'+ i + '=' + control_vetor_email_json[i].email.trim();
			}
		}

		d = new Date();
		var adress = url_adress + 'services/cliente?OP_CODE=CREATE&TIPO=' + tipo_pessoa.trim() + '&NOME=' + nome.value.trim() + '&NOME_FANTASIA=' + apelido.value.trim();
		adress = adress + '&SITE=' + site.value.trim() + '&DATA_NASCIMENTO=' + dt_nascimento.value.trim() + '&DATA_INGRESSO=' + d.yyyymmdd();
		adress = adress + '&COD_VENDEDOR='+ cod_vendedor.trim() + '&COD_USUARIO=' + cod_usuario.innerHTML.trim();
		adress = adress + adress_aux;
		document.location.href = adress;
	}
	else
		return 0;
}

function operacional_consultar_cliente_salvar_function(){
	alert("Em desenvolvimento!");
	return 0;
	ajustar_vetor_doc_function();
	ajustar_vetor_end_function();
	ajustar_vetor_contato_function();
	ajustar_vetor_email_function();
	if(confirm('Deseja salvar os dados alterados do Cliente?')){
		var values = $("input[type='radio'][name='pessoa']:checked").val();
		var tipo_pessoa;
		if(values === "pessoafisica"){
			tipo_pessoa = 0;
		}else{
			tipo_pessoa = 1;
		}
		var nome = document.getElementById("nome_razaosocial");
		var apelido  = document.getElementById("nomefantasia");
		var dt_nascimento = document.getElementById("data_nasc");
		var site = document.getElementById("url_site");
		var cod_vendedor = $('#vendedor_list :selected').val();
		var cod_usuario = document.getElementById("cod_usuario");

		if(nome.value.trim() === ""){
			alert("Necessário ingressar NOME ou RAZÃO SOCIAL do Cliente.");
			document.getElementById("nome_razaosocial").focus();
			return 0;
		}
		if(apelido.value.trim() === ""){
			if(tipo_pessoa === 1){
				alert("Necessário ingressar NOME FANTASIA do Cliente.");
				document.getElementById("nomefantasia").focus();
				return 0;
			}else{
				apelido.value = "";
			}
		}
		if(dt_nascimento.value.trim() === ""){
			alert("Necessário ingressar DATA DE NASCIMENTO do Cliente");
			document.getElementById("data_nasc").focus();
			return 0;
		}
		if(site.value.trim() === ""){
			site.value = "";
		}

		if(control_vetor_doc_json[0] === 0){
			alert("Necessário ingressar DOCUMENTO do Cliente");
			document.getElementById("numero_documento").focus();
			return 0;
		}

		var adress_aux;
		var documentoLength = control_vetor_doc_json.length;
		adress_aux = '&QDOC=' + documentoLength;
		for(var i=0;i<documentoLength;i++){
			if(control_vetor_doc_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPODOC_'+ i + '=' + control_vetor_doc_json[i].tipo_doc.trim();
				adress_aux = adress_aux + '&NUMDOC_'+ i + '=' + control_vetor_doc_json[i].numero.trim();
				adress_aux = adress_aux + '&DTDOC_'+ i + '=' + control_vetor_doc_json[i].dt_emiss.trim();
				adress_aux = adress_aux + '&ORGDOC_'+ i + '=' + control_vetor_doc_json[i].org_emiss.trim();
			}
		}

		if(control_vetor_end_json[0] === 0){
			alert("Necessário ingressar ENDEREÇO do Cliente");
			document.getElementById("endereco").focus();
			return 0;
		}

		var enderecoLength = control_vetor_end_json.length;
		adress_aux = adress_aux + '&QEND=' + enderecoLength;
		for(i=0;i<enderecoLength;i++){
			if(control_vetor_end_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&END_'+ i + '=' + control_vetor_end_json[i].endereco.trim();
				adress_aux = adress_aux + '&BAIRRO_'+ i + '=' + control_vetor_end_json[i].bairro.trim();
				adress_aux = adress_aux + '&CIDADE_'+ i + '=' + control_vetor_end_json[i].cidade.trim();
				adress_aux = adress_aux + '&UF_'+ i + '=' + control_vetor_end_json[i].uf.trim();
				adress_aux = adress_aux + '&PAIS_'+ i + '=' + control_vetor_end_json[i].pais.trim();
				adress_aux = adress_aux + '&COMP_'+ i + '=' + control_vetor_end_json[i].complemento.trim();
				adress_aux = adress_aux + '&CEP_'+ i + '=' + control_vetor_end_json[i].cep.trim();
				adress_aux = adress_aux + '&TIPOEND_'+ i + '=' + control_vetor_end_json[i].tipo_end.trim();
			}
		}

		if(control_vetor_contato_json[0] === 0){
			alert("Necessário ingressar CONTATO do Cliente");
			document.getElementById("ddd").focus();
			return 0;
		}

		var contatoLength = control_vetor_contato_json.length;
		adress_aux = adress_aux + '&QCTO=' + contatoLength;
		for(i=0;i<contatoLength;i++){
			if(control_vetor_contato_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPOCTO_'+ i + '=' + control_vetor_contato_json[i].tipo_contato.trim();
				adress_aux = adress_aux + '&DDD_'+ i + '=' + control_vetor_contato_json[i].ddd.trim();
				adress_aux = adress_aux + '&NUMCTO_'+ i + '=' + control_vetor_contato_json[i].numero.trim();
				adress_aux = adress_aux + '&PAISCTO_'+ i + '=' + control_vetor_contato_json[i].cod_pais.trim();
				adress_aux = adress_aux + '&NOMECTO_'+ i + '=' + control_vetor_contato_json[i].nome.trim();
				adress_aux = adress_aux + '&PARENCTO_'+ i + '=' + control_vetor_contato_json[i].parentesco.trim();
			}
		}

		if(control_vetor_email_json[0] === 0){
			alert("Necessário ingressar EMAIL do Cliente");
			document.getElementById("email").focus();
			return 0;
		}

		var emailLength = control_vetor_email_json.length;
		adress_aux = adress_aux + '&QMAIL=' + emailLength;
		for(i=0;i<emailLength;i++){
			if(control_vetor_email_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&MAIL_'+ i + '=' + control_vetor_email_json[i].email.trim();
			}
		}
		
		var adress = url_adress + 'services/cliente?OP_CODE=UPDATE&TIPO=' + tipo_pessoa.trim() + '&NOME=' + nome.value.trim() + '&NOME_FANTASIA=' + apelido.value.trim();
		adress = adress + '&SITE=' + site.value.trim() + '&DATA_NASCIMENTO=' + dt_nascimento.value + '&COD_VENDEDOR='+ cod_vendedor.trim();
		adress = adress + '&COD_CLIENTE=' + cod_cliente_consulta.trim() + '&COD_USUARIO=' + cod_usuario.innerHTML.trim();
		adress = adress + adress_aux;
		document.location.href = adress;
	}
	else
		return 0;
}

function operacional_cadastrar_chip_function(){
	if(confirm('Deseja realizar o ingresso do Sim Card?')){
		var nfe = document.getElementById("nfe_chip");
		var iccid = document.getElementById("iccid");
		var operadora_chip = $('#operadora_chip :selected').val();
		var tecnologia_chip = document.getElementById("tecnologia_chip");
		var apn_chip = document.getElementById("apn_chip");
		var estado_chip = $('#estado_chip :selected').val();
		var ddd_chip = document.getElementById("ddd_chip");
		var numero_chip = document.getElementById("numero_chip");
		var cod_usuario = document.getElementById("cod_usuario");

		if(nfe.value.trim() === ""){
			alert("Necessário ingressar a NFe do SIM CARD.");
			document.getElementById("nfe_chip").focus();
			return 0;
		}

		if(iccid.value.trim() === ""){
			alert("Necessário ingressar o ICC-ID do SIM CARD.");
			document.getElementById("iccid").focus();
			return 0;
		}

		if(tecnologia_chip.value.trim() === ""){
			alert("Necessário ingressar a TECNOLOGIA do SIM CARD.");
			document.getElementById("tecnologia_chip").focus();
			return 0;
		}

		if(apn_chip.value.trim() === ""){
			alert("Necessário ingressar a APN do SIM CARD.");
			document.getElementById("apn_chip").focus();
			return 0;
		}

		if(ddd_chip.value.trim() === ""){
			alert("Necessário ingressar o DDD do SIM CARD.");
			document.getElementById("ddd_chip").focus();
			return 0;
		}

		if(numero_chip.value.trim() === ""){
			alert("Necessário ingressar o NÚMERO do SIM CARD.");
			document.getElementById("numero_chip").focus();
			return 0;
		}

		adress = url_adress + 'services/chip?OP_CODE=CREATE&NFE=' + nfe.value.trim() + '&ICCID=' + iccid.value.trim() + '&OPERADORA=' + operadora_chip.trim();
		adress = adress + '&TECNOLOGIA=' + tecnologia_chip.value.trim() + '&APN=' + apn_chip.value.trim() + '&ESTADO=' + estado_chip.trim();
		adress = adress + '&DDD=' + ddd_chip.value.trim() + '&NUMERO=' + numero_chip.value.trim() + '&COD_USUARIO=' + cod_usuario.innerHTML.trim();
		document.location.href = adress;
	}
	else
		return 0;
}

function operacional_consultar_chip_salvar_function(){
	alert("Em desenvolvimento!");
	return 0;
	if(confirm('Deseja realizar o ingresso do Sim Card?')){
		var nfe = document.getElementById("nfe_chip");
		var iccid = document.getElementById("iccid");
		var operadora_chip = $('#operadora_chip :selected').val();
		var tecnologia_chip = document.getElementById("tecnologia_chip");
		var apn_chip = document.getElementById("apn_chip");
		var estado_chip = $('#estado_chip :selected').val();
		var ddd_chip = document.getElementById("ddd_chip");
		var numero_chip = document.getElementById("numero_chip");
		var cod_usuario = document.getElementById("cod_usuario");

		if(nfe.value.trim() === ""){
			alert("Necessário ingressar a NFe do SIM CARD.");
			document.getElementById("nfe_chip").focus();
			return 0;
		}

		if(iccid.value.trim() === ""){
			alert("Necessário ingressar o ICC-ID do SIM CARD.");
			document.getElementById("iccid").focus();
			return 0;
		}

		if(tecnologia_chip.value.trim() === ""){
			alert("Necessário ingressar a TECNOLOGIA do SIM CARD.");
			document.getElementById("tecnologia_chip").focus();
			return 0;
		}

		if(apn_chip.value.trim() === ""){
			alert("Necessário ingressar a APN do SIM CARD.");
			document.getElementById("apn_chip").focus();
			return 0;
		}

		if(ddd_chip.value.trim() === ""){
			alert("Necessário ingressar o DDD do SIM CARD.");
			document.getElementById("ddd_chip").focus();
			return 0;
		}

		if(numero_chip.value.trim() === ""){
			alert("Necessário ingressar o NÚMERO do SIM CARD.");
			document.getElementById("numero_chip").focus();
			return 0;
		}

		adress = url_adress + 'services/chip?OP_CODE=UPDATE&NFE=' + nfe.value.trim() + '&ICCID=' + iccid.value.trim() + '&OPERADORA=' + operadora_chip.trim();
		adress = adress + '&TECNOLOGIA=' + tecnologia_chip.value.trim() + '&APN=' + apn_chip.value.trim() + '&ESTADO=' + estado_chip.trim() + '&DDD=' + ddd_chip.value.trim();
		adress = adress + '&NUMERO=' + numero_chip.value.trim() + '&COD_CHIP=' + cod_chip_consulta.trim() + '&COD_USUARIO=' + cod_usuario.innerHTML.trim();
		document.location.href = adress;
	}
	else
		return 0;
}

function administrativo_cadastrar_vendedor_cadastrar_function(){
	ajustar_vetor_doc_function();
	ajustar_vetor_end_function();
	ajustar_vetor_contato_function();
	ajustar_vetor_email_function();
	if(confirm('Deseja realizar o ingresso do Vendedor?')){
		var values = $("input[type='radio'][name='pessoa']:checked").val();
		var tipo_pessoa;
		if(values === "pessoafisica"){
			tipo_pessoa = 0;
		}else{
			tipo_pessoa = 1;
		}
		var nome = document.getElementById("nome_razaosocial");
		var apelido  = document.getElementById("nomefantasia");
		var dt_nascimento = document.getElementById("data_nasc");
		var site = document.getElementById("url_site");
		var cod_usuario = document.getElementById("cod_usuario");

		if(nome.value.trim() === ""){
			alert("Necessário ingressar NOME ou RAZÃO SOCIAL do Vendedor.");
			document.getElementById("nome_razaosocial").focus();
			return 0;
		}
		if(apelido.value.trim() === ""){
			if(tipo_pessoa === 1){
				alert("Necessário ingressar NOME FANTASIA do Vendedor.");
				document.getElementById("nomefantasia").focus();
				return 0;
			}else{
				apelido.value = "";
			}
		}
		if(dt_nascimento.value.trim() === ""){
			alert("Necessário ingressar DATA DE NASCIMENTO do Vendedor");
			document.getElementById("data_nasc").focus();
			return 0;
		}
		if(site.value.trim() === ""){
			site.value = "";
		}

		if(control_vetor_doc_json[0] === 0){
			alert("Necessário ingressar DOCUMENTO do Vendedor");
			document.getElementById("numero_documento").focus();
			return 0;
		}

		var adress_aux;
		var documentoLength = control_vetor_doc_json.length;
		adress_aux = '&QDOC=' + documentoLength;
		for(var i=0;i<documentoLength;i++){
			if(control_vetor_doc_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPODOC_'+ i + '=' + control_vetor_doc_json[i].tipo_doc.trim();
				adress_aux = adress_aux + '&NUMDOC_'+ i + '=' + control_vetor_doc_json[i].numero.trim();
				adress_aux = adress_aux + '&DTDOC_'+ i + '=' + control_vetor_doc_json[i].dt_emiss.trim();
				adress_aux = adress_aux + '&ORGDOC_'+ i + '=' + control_vetor_doc_json[i].org_emiss.trim();
			}
		}

		if(control_vetor_end_json[0] === 0){
			alert("Necessário ingressar ENDEREÇO do Vendedor");
			document.getElementById("endereco").focus();
			return 0;
		}

		var enderecoLength = control_vetor_end_json.length;
		adress_aux = adress_aux + '&QEND=' + enderecoLength;
		for(i=0;i<enderecoLength;i++){
			if(control_vetor_end_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&END_'+ i + '=' + control_vetor_end_json[i].endereco.trim();
				adress_aux = adress_aux + '&BAIRRO_'+ i + '=' + control_vetor_end_json[i].bairro.trim();
				adress_aux = adress_aux + '&CIDADE_'+ i + '=' + control_vetor_end_json[i].cidade.trim();
				adress_aux = adress_aux + '&UF_'+ i + '=' + control_vetor_end_json[i].uf.trim();
				adress_aux = adress_aux + '&PAIS_'+ i + '=' + control_vetor_end_json[i].pais.trim();
				adress_aux = adress_aux + '&COMP_'+ i + '=' + control_vetor_end_json[i].complemento.trim();
				adress_aux = adress_aux + '&CEP_'+ i + '=' + control_vetor_end_json[i].cep.trim();
				adress_aux = adress_aux + '&TIPOEND_'+ i + '=' + control_vetor_end_json[i].tipo_end.trim();
			}
		}

		if(control_vetor_contato_json[0] === 0){
			alert("Necessário ingressar CONTATO do Vendedor");
			document.getElementById("ddd").focus();
			return 0;
		}

		var contatoLength = control_vetor_contato_json.length;
		adress_aux = adress_aux + '&QCTO=' + contatoLength;
		for(i=0;i<contatoLength;i++){
			if(control_vetor_contato_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPOCTO_'+ i + '=' + control_vetor_contato_json[i].tipo_contato.trim();
				adress_aux = adress_aux + '&DDD_'+ i + '=' + control_vetor_contato_json[i].ddd.trim();
				adress_aux = adress_aux + '&NUMCTO_'+ i + '=' + control_vetor_contato_json[i].numero.trim();
				adress_aux = adress_aux + '&PAISCTO_'+ i + '=' + control_vetor_contato_json[i].cod_pais.trim();
				adress_aux = adress_aux + '&NOMECTO_'+ i + '=' + control_vetor_contato_json[i].nome.trim();
				adress_aux = adress_aux + '&PARENCTO_'+ i + '=' + control_vetor_contato_json[i].parentesco.trim();
			}
		}

		if(control_vetor_email_json[0] === 0){
			alert("Necessário ingressar EMAIL do Vendedor");
			document.getElementById("email").focus();
			return 0;
		}

		var emailLength = control_vetor_email_json.length;
		adress_aux = adress_aux + '&QMAIL=' + emailLength;
		for(i=0;i<emailLength;i++){
			if(control_vetor_email_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&MAIL_'+ i + '=' + control_vetor_email_json[i].email.trim();
			}
		}

		d = new Date();
		var adress = url_adress + 'services/vendedor?OP_CODE=CREATE&TIPO=' + tipo_pessoa.trim() + '&NOME=' + nome.value.trim() + '&NOME_FANTASIA=' + apelido.value.trim();
		adress = adress + '&SITE=' + site.value.trim() + '&DATA_NASCIMENTO=' + dt_nascimento.value.trim() + '&DATA_INGRESSO=' + d.yyyymmdd().trim() + '&COD_USUARIO=' + cod_usuario.innerHTML.trim();
		adress = adress + adress_aux;
		document.location.href = adress;
	}
	else
		return 0;
}

function administrativo_consultar_vendedor_salvar_function(){
	alert("Em desenvolvimento!");
	return 0;
	ajustar_vetor_doc_function();
	ajustar_vetor_end_function();
	ajustar_vetor_contato_function();
	ajustar_vetor_email_function();
	if(confirm('Deseja salvar os dados alterados do Vendedor?')){
		var values = $("input[type='radio'][name='pessoa']:checked").val();
		var tipo_pessoa;
		if(values === "pessoafisica"){
			tipo_pessoa = 0;
		}else{
			tipo_pessoa = 1;
		}
		var nome = document.getElementById("nome_razaosocial");
		var apelido  = document.getElementById("nomefantasia");
		var dt_nascimento = document.getElementById("data_nasc");
		var site = document.getElementById("url_site");
		var cod_usuario = document.getElementById("cod_usuario");

		if(nome.value.trim() === ""){
			alert("Necessário ingressar NOME ou RAZÃO SOCIAL do Vendedor.");
			document.getElementById("nome_razaosocial").focus();
			return 0;
		}
		if(apelido.value.trim() === ""){
			if(tipo_pessoa === 1){
				alert("Necessário ingressar NOME FANTASIA do Vendedor.");
				document.getElementById("nomefantasia").focus();
				return 0;
			}else{
				apelido.value = "";
			}
		}
		if(dt_nascimento.value.trim() === ""){
			alert("Necessário ingressar DATA DE NASCIMENTO do Vendedor");
			document.getElementById("data_nasc").focus();
			return 0;
		}
		if(site.value.trim() === ""){
			site.value = "";
		}

		if(control_vetor_doc_json[0] === 0){
			alert("Necessário ingressar DOCUMENTO do Vendedor");
			document.getElementById("numero_documento").focus();
			return 0;
		}

		var adress_aux;
		var documentoLength = control_vetor_doc_json.length;
		adress_aux = '&QDOC=' + documentoLength;
		for(var i=0;i<documentoLength;i++){
			if(control_vetor_doc_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPODOC_'+ i + '=' + control_vetor_doc_json[i].tipo_doc.trim();
				adress_aux = adress_aux + '&NUMDOC_'+ i + '=' + control_vetor_doc_json[i].numero.trim();
				adress_aux = adress_aux + '&DTDOC_'+ i + '=' + control_vetor_doc_json[i].dt_emiss.trim();
				adress_aux = adress_aux + '&ORGDOC_'+ i + '=' + control_vetor_doc_json[i].org_emiss.trim();
			}
		}

		if(control_vetor_end_json[0] === 0){
			alert("Necessário ingressar ENDEREÇO do Vendedor");
			document.getElementById("endereco").focus();
			return 0;
		}

		var enderecoLength = control_vetor_end_json.length;
		adress_aux = adress_aux + '&QEND=' + enderecoLength;
		for(i=0;i<enderecoLength;i++){
			if(control_vetor_end_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&END_'+ i + '=' + control_vetor_end_json[i].endereco.trim();
				adress_aux = adress_aux + '&BAIRRO_'+ i + '=' + control_vetor_end_json[i].bairro.trim();
				adress_aux = adress_aux + '&CIDADE_'+ i + '=' + control_vetor_end_json[i].cidade.trim();
				adress_aux = adress_aux + '&UF_'+ i + '=' + control_vetor_end_json[i].uf.trim();
				adress_aux = adress_aux + '&PAIS_'+ i + '=' + control_vetor_end_json[i].pais.trim();
				adress_aux = adress_aux + '&COMP_'+ i + '=' + control_vetor_end_json[i].complemento.trim();
				adress_aux = adress_aux + '&CEP_'+ i + '=' + control_vetor_end_json[i].cep.trim();
				adress_aux = adress_aux + '&TIPOEND_'+ i + '=' + control_vetor_end_json[i].tipo_end.trim();
			}
		}

		if(control_vetor_contato_json[0] === 0){
			alert("Necessário ingressar CONTATO do Vendedor");
			document.getElementById("ddd").focus();
			return 0;
		}

		var contatoLength = control_vetor_contato_json.length;
		adress_aux = adress_aux + '&QCTO=' + contatoLength;
		for(i=0;i<contatoLength;i++){
			if(control_vetor_contato_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPOCTO_'+ i + '=' + control_vetor_contato_json[i].tipo_contato.trim();
				adress_aux = adress_aux + '&DDD_'+ i + '=' + control_vetor_contato_json[i].ddd.trim();
				adress_aux = adress_aux + '&NUMCTO_'+ i + '=' + control_vetor_contato_json[i].numero.trim();
				adress_aux = adress_aux + '&PAISCTO_'+ i + '=' + control_vetor_contato_json[i].cod_pais.trim();
				adress_aux = adress_aux + '&NOMECTO_'+ i + '=' + control_vetor_contato_json[i].nome.trim();
				adress_aux = adress_aux + '&PARENCTO_'+ i + '=' + control_vetor_contato_json[i].parentesco.trim();
			}
		}

		if(control_vetor_email_json[0] === 0){
			alert("Necessário ingressar EMAIL do Vendedor");
			document.getElementById("email").focus();
			return 0;
		}

		var emailLength = control_vetor_email_json.length;
		adress_aux = adress_aux + '&QMAIL=' + emailLength;
		for(i=0;i<emailLength;i++){
			if(control_vetor_email_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&MAIL_'+ i + '=' + control_vetor_email_json[i].email.trim();
			}
		}
		
		var adress = url_adress + 'services/cliente?OP_CODE=UPDATE&TIPO=' + tipo_pessoa.trim() + '&NOME=' + nome.value.trim() + '&NOME_FANTASIA=' + apelido.value.trim();
		adress = adress + '&SITE=' + site.value.trim() + '&DATA_NASCIMENTO=' + dt_nascimento.value.trim() + '&COD_VENDEDOR='+ cod_vendedor.trim() + '&COD_USUARIO=' + cod_usuario.innerHTML.trim();
		adress = adress + adress_aux;
		document.location.href = adress;
	}
	else
		return 0;
}

function select_modelo_function(){
	var marca_equip_ = $('#marca_equip').val();
	var marca_equip_val = marca_equip_;
    var content = '';
    if(marca_equip_ != 0){
    	$.ajax({
            url: url_adress + "select_modelo.jsp?COD_MARCA=" + marca_equip_val,
            success: function(result) {
                $('#div_modelo_equip').html(result);
            },
            error: function(e){
                alert('error...');
            }
        });
    }else{
    	content = 'Modelo:<input type="text" id="modelo_equip" class="size_84" disabled="disabled">';
    	$('#div_modelo_equip').html(content);
    }
}

function operacional_cadastrar_equipamento_function(){
	if(confirm('Deseja realizar o ingresso do Equipamento?')){
		var instalado = $("input[type='radio'][name='modulo_instalado']:checked").val();
		var id = document.getElementById("numero_modulo");
		var sn = document.getElementById("sn_modulo");
		var nfe = document.getElementById("numero_nfe");
		var marca = $('#marca_equip :selected').val();
		var modelo = $('#modelo_equip :selected').val();
		var val_datalist_iccid = $('#item_iccid').val();
		var cod_chip_cad_equip = 0;
        if (val_datalist_iccid !== ""){
        	cod_chip_cad_equip = $('#iccid_list option').filter(function() {
                return this.value == val_datalist_iccid;
            }).data('label');
        	if(cod_chip_cad_equip === ""){
    			alert("Necessário selecionar o ICC-ID do Sim Card instalado no Equipamento.");
    			document.getElementById("numero_chip").focus();
    			return 0;
    		}
        }
		
        var cliente_cad_equip = "";
        //TODO SWITCH CASE PARA OS TIPO DE INSTALAÇÃO
        //EVOLUI CONFORME FOREM ACRESCENTANDO ESSES TIPOS
        if(Number(instalado) === 2){
			var val_datalist_cliente = $('#item_nome_razao').val();
	        if (val_datalist_cliente.trim() !== ""){
	        	cliente_cad_equip = $('#nome_list option').filter(function() {
	                return this.value == val_datalist_cliente;
	            }).data('label');
	        }
	        if(cliente_cad_equip === ""){
	        	alert("Necessário selecionar um cliente para o Equipamento!");
	        	document.getElementById("item_nome_razao").focus();
	        	return 0;
	        }
	        //TODO VEÍCULO - AGUARDAR A CRIAÇÃO DO CADASTRO E CONSULTA DE VEÍCULOS
		}
		var cod_usuario = document.getElementById("cod_usuario");

		if(id.value.trim() === ""){
			alert("Necessário ingressar o ID do Equipamento.");
			document.getElementById("numero_modulo").focus();
			return 0;
		}

		if(sn.value.trim() === ""){
			alert("Necessário ingressar o SN do Equipamento.");
			document.getElementById("sn_modulo").focus();
			return 0;
		}

		if(nfe.value.trim() === ""){
			alert("Necessário ingressar a NFe do Equipamento.");
			document.getElementById("numero_nfe").focus();
			return 0;
		}

		if(marca === ""){
			alert("Necessário selecionar a MARCA do Equipamento.");
			document.getElementById("marca_equip").focus();
			return 0;
		}

		if(modelo === ""){
			alert("Necessário selecionar o MODELO do Equipamento.");
			document.getElementById("modelo_equip").focus();
			return 0;
		}

		adress = url_adress + 'services/equipamento?OP_CODE=CREATE&COD_USUARIO=' + cod_usuario.innerHTML.trim() + '&COD_CHIP=' + cod_chip_cad_equip + '&NUM_MODULO=' + id.value.trim();
		if(Number(instalado) !== 1){
			adress = adress + '&COD_CLIENTE=' + cliente_cad_equip;
		}
		adress = adress + '&SN=' + sn.value.trim() + '&NFE=' + nfe.value.trim() + '&COD_MODELO=' + modelo + '&INSTALADO=' + instalado;
		document.location.href = adress;
	}else
		return 0;
}

function operacional_consultar_equipamento_salvar_function(){
	alert("Em desenvolvimento!");
	return 0;
	if(confirm('Deseja salvar os dados alterados do Equipamento?')){
		var instalado = $("input[type='radio'][name='modulo_instalado']:checked").val();
		var id = document.getElementById("numero_modulo");
		var sn = document.getElementById("sn_modulo");
		var nfe = document.getElementById("numero_nfe");
		var marca = $('#marca_equip :selected').val();
		var modelo = $('#modelo_equip :selected').val();
		var val_datalist_iccid = $('#item_iccid').val();
		var cod_chip_cad_equip = 0;
        if (val_datalist_iccid !== ""){
        	cod_chip_cad_equip = $('#iccid_list option').filter(function() {
                return this.value == val_datalist_iccid;
            }).data('label');
        	if(cod_chip_cad_equip === ""){
    			alert("Necessário selecionar o ICC-ID do Sim Card instalado no Equipamento.");
    			document.getElementById("numero_chip").focus();
    			return 0;
    		}
        }
        var cliente_cad_equip = "";
        //TODO SWITCH CASE PARA OS TIPO DE INSTALAÇÃO
        //EVOLUI CONFORME FOREM ACRESCENTANDO ESSES TIPOS
        if(Number(instalado) === 2){
			var val_datalist_cliente = $('#item_nome_razao').val();
	        if (val_datalist_cliente.trim() !== ""){
	        	cliente_cad_equip = $('#nome_list option').filter(function() {
	                return this.value == val_datalist_cliente;
	            }).data('label');
	        }
	        if(cliente_cad_equip === ""){
	        	alert("Necessário selecionar um cliente para o Equipamento!");
	        	document.getElementById("item_nome_razao").focus();
	        	return 0;
	        }
	        //TODO VEÍCULO - AGUARDAR A CRIAÇÃO DO CADASTRO E CONSULTA DE VEÍCULOS
		}
		var cod_usuario = document.getElementById("cod_usuario");

		if(id.value.trim() === ""){
			alert("Necessário ingressar o ID do Equipamento.");
			document.getElementById("numero_modulo").focus();
			return 0;
		}

		if(sn.value.trim() === ""){
			alert("Necessário ingressar o SN do Equipamento.");
			document.getElementById("sn_modulo").focus();
			return 0;
		}

		if(nfe.value.trim() === ""){
			alert("Necessário ingressar a NFe do Equipamento.");
			document.getElementById("numero_nfe").focus();
			return 0;
		}

		if(marca === ""){
			alert("Necessário selecionar a MARCA do Equipamento.");
			document.getElementById("marca_equip").focus();
			return 0;
		}

		if(modelo === ""){
			alert("Necessário selecionar o MODELO do Equipamento.");
			document.getElementById("modelo_equip").focus();
			return 0;
		}

		adress = url_adress + 'services/equipamento?OP_CODE=CREATE&COD_USUARIO=' + cod_usuario.innerHTML.trim() + '&COD_CHIP=' + cod_chip_cad_equip + '&NUM_MODULO=' + id.value.trim();
		if(Number(instalado) !== 1){
			adress = adress + '&COD_CLIENTE=' + cliente_cad_equip;
		}
		adress = adress + '&SN=' + sn.value.trim() + '&NFE=' + nfe.value.trim() + '&COD_MODELO=' + modelo + '&INSTALADO=' + instalado + '&COD_MODULO=' + cod_modulo_consulta;
		document.location.href = adress;
	}else
		return 0;
}

function reloadIFrame() {
	parent.frames['tarefasIFrame'].location.href = "tarefas.jsp";
}

var clean_contato_prospect;
var control_div_contato_prospect = new Array();
function div_contato_inseridas_prospect (cod, excluida){
    this.cod = cod;
    this.excluida = excluida;
}

function vetor_contato_inserido_prospect(ddd, numero, tipo_contato, cod_pais){
    this.ddd = ddd;
    this.numero = numero;
    this.tipo_contato = tipo_contato;
    this.cod_pais = cod_pais;
}

var control_vetor_contato_prospect = new Array();
control_vetor_contato_prospect[0] = 0;
var control_vetor_contato_prospect_tipo = new Array();

function insert_contatos_prospect_function() {
    clean_contato_prospect = $('#div_contato').html();
    var ddd = document.getElementById("ddd").value.trim();
    var numero = document.getElementById("numero_contato").value.trim();
    var tipo_contato = $('#tipocont_list').val();
    var tipo_contato_nome = $('#tipocont_list :selected').text();
    var cod_pais = document.getElementById("cod_pais").value.trim();
    var insert = "X";

    var tipo_contato_tipo_obj = $('#tipocont_list');
    var tipo_contato_tamanho = tipo_contato_tipo_obj[0].length;
    for (var i=0; i<tipo_contato_tamanho;i++){
        control_vetor_contato_prospect_tipo[i] = document.getElementsByName("option_contato_tipo")[i].text;
    }

    if (ddd === ""){
        alert('Necessário ingressar o DDD do contato.');
        document.getElementById("ddd").focus();
        return 0;
    }

    if (numero === ""){
        alert('Necessário ingressar o NÚMERO do contato.');
        document.getElementById("numero_contato").focus();
        return 0;
    }

    if (cod_pais === ""){
        alert('Necessário ingressar o COD. PAÍS do contato.');
        document.getElementById("cod_pais").focus();
        return 0;
    }

    i = control_div_contato_prospect.length;
    var j = 0;

    if (i !== 0){
        while (j < i && insert === "X"){
            if (control_div_contato_prospect[j].excluida === 1){
                insert = j;
            }
            else{
                j++;
            }
        }
        if (insert === "X"){
            control_div_contato_prospect[i] = new div_contato_inseridas_prospect(i,0);
            control_vetor_contato_prospect[i] = new vetor_contato_inserido_prospect(ddd, numero, tipo_contato, cod_pais);
        }
    }
    else{
        control_div_contato_prospect[0] = new div_contato_inseridas_prospect(0,0);
        control_vetor_contato_prospect[0] = new vetor_contato_inserido_prospect(ddd, numero, tipo_contato, cod_pais);
    }

    if (insert === "X"){
        var content_div_contato = $('#contato_inserido').html();
        content_div_contato = content_div_contato + '<div id="contato_' + control_div_contato_prospect[i].cod + '" class="div_inseridos">\n\
                                                  <input type="radio" name="contato_inserido" value="contato_' + control_div_contato_prospect[i].cod + '" onclick="change_contato_prospect_button_function()">\n\
                                                  <div id="tipo_contato_' + control_div_contato_prospect[i].cod + '">' + tipo_contato_nome + '</div>: +\n\
                                                  <div id="cod_pais_' + control_div_contato_prospect[i].cod + '">' + cod_pais + '</div>\n\
                                                  (<div id="ddd_contato_' + control_div_contato_prospect[i].cod + '">' + ddd + '</div>)\n\
                                                  <div id="numero_contato_' + control_div_contato_prospect[i].cod + '">' + numero + '</div> </div>';

        $('#contato_inserido').html(content_div_contato);
    }
    else {
        var content_div_contato = '<input type="radio" name="contato_inserido" value="contato_' + control_div_contato_prospect[j].cod + '" onclick="change_contato_prospect_button_function()">\n\
                                                  <div id="tipo_contato_' + control_div_contato_prospect[j].cod + '">' + tipo_contato_nome + '</div>: +\n\
                                                  <div id="cod_pais_' + control_div_contato_prospect[j].cod + '">' + cod_pais + '</div>\n\
                                                  (<div id="ddd_contato_' + control_div_contato_prospect[j].cod + '">' + ddd + '</div>)\n\
                                                  <div id="numero_contato_' + control_div_contato_prospect[j].cod + '">' + numero + '</div> <br>';

        $('#contato_' + control_div_contato_prospect[j].cod).html(content_div_contato);
        control_div_contato_prospect[j].excluida = 0;
        control_vetor_contato_prospect[j] = new vetor_contato_inserido_prospect(ddd, numero, tipo_contato, cod_pais);
    }
    limpa_campos_contato_prospect_function();
    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_prospect_function()">Incluir</button>';
    $('#div_contato_bt').html(content_div_bt);
    document.getElementById("ddd").focus();
}

function delete_contatos_prospect_function() {
    var div_select = $("input[type='radio'][name='contato_inserido']:checked").val();
    var length = div_select.length;
    var div_deletar = div_select.charAt(length - 1);

    var tipo_contato = $('#tipo_contato_' + div_deletar).html();
    var cod_pais = $('#cod_pais_' + div_deletar).html();
    var ddd = $('#ddd_contato_' + div_deletar).html();
    var numero = $('#numero_contato_' + div_deletar).html();

    var content_div_contato = $('#' + div_select).html();

    if (confirm('O contato ' + tipo_contato.trim() + ': +' + cod_pais.trim() + '(' + ddd.trim() + ')' + numero.trim() + ' será apagado.')) {
        $('#' + div_select).html("");
        control_div_contato_prospect[div_deletar].excluida = 1;
        control_vetor_contato_prospect[div_deletar] = 0;
    } else {
        $('#' + div_select).html(content_div_contato);
    }

    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_prospect_function()">Incluir</button>';
    $('#div_contato_bt').html(content_div_bt);
    limpa_campos_contato_prospect_function();
}

function change_contato_prospect_button_function(){
    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_prospect_function()">Incluir</button>\n\
                          <button type="button" id="editar_contato" onclick="editar_contatos_prospect_function()">Editar</button>\n\
                          <button type="button" id="delete_contato" onclick="delete_contatos_prospect_function()">Excluir</button>';
    $('#div_contato_bt').html(content_div_bt);
}

var div_contato_prospect_editar;

function editar_contatos_prospect_function() {
    var content_div_bt = '<button type="button" id="salvar_contato" onclick="salvar_contatos_prospect_function()">Salvar</button>\n\
                          <button type="button" id="cancelar_contato" onclick="cancelar_contatos_prospect_function()">Cancelar</button>';
    $('#div_contato_bt').html(content_div_bt);

    var div_select = $("input[type='radio'][name='contato_inserido']:checked").val();
    var content_div_contato = $('#' + div_select).html();
    var length = div_select.length;
    div_contato_prospect_editar = div_select.charAt(length - 1);

    var ddd = control_vetor_contato_prospect[div_contato_prospect_editar].ddd;
    var numero = control_vetor_contato_prospect[div_contato_prospect_editar].numero;
    var tipo_contato = control_vetor_contato_prospect[div_contato_prospect_editar].tipo_contato;
    var cod_pais = control_vetor_contato_prospect[div_contato_prospect_editar].cod_pais;

    $('#' + div_select).html("");
    $('#' + div_select).html(content_div_contato);

    content_div_contato = "";

    content_div_contato = 'DDD:<input type="text" class="size_5" id="ddd" maxlength="3" onkeypress="javascript: return EntradaNumerico(event);" value="' + ddd + '">\n\
                        Número:<input type="text" class="size_19" id="numero_contato" maxlength="10" onkeypress="javascript: return EntradaNumerico(event);" value="' + numero + '">\n\
                        Tipo do Contato:\n\
                        <select id="tipocont_list" class="size_21">';
    for(var i=0;i<control_vetor_contato_prospect_tipo.length;i++){
        content_div_contato = content_div_contato + '<option value="' + Number(i+1) + '" name="option_contato_tipo"';
        if (Number(tipo_contato) === Number(i+1)){
            content_div_contato = content_div_contato + ' selected';
        }
        content_div_contato = content_div_contato + '>' + control_vetor_contato_prospect_tipo[i] + '</option>';
    }
    content_div_contato = content_div_contato + '</select>\n\
                        Cod. País: <input type="text" class="size_5" id="cod_pais" maxlength="3" onkeypress="javascript: return EntradaNumerico(event);" value="' + cod_pais + '">';

    $('#div_contato').html("");
    $('#div_contato').html(content_div_contato);
}

function limpa_campos_contato_prospect_function(){
    var content_div_contato = clean_contato_prospect;
    $('#div_contato').html("");
    $('#div_contato').html(content_div_contato);
}

function cancelar_contatos_prospect_function(){
    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_prospect_function()">Incluir</button>';
    $('#div_contato_bt').html(content_div_bt);

    limpa_campos_contato_prospect_function();
}

function salvar_contatos_prospect_function(){
    var content_div_bt = '<button type="button" id="incluir_contato" onclick="insert_contatos_prospect_function()">Incluir</button>';
    $('#div_contato_bt').html(content_div_bt);

    var div_salvar = div_contato_prospect_editar;

    $('#contato_' + div_salvar).html("");    

    var ddd = document.getElementById("ddd").value.trim();
    var numero = document.getElementById("numero_contato").value.trim();
    var tipo_contato = $('#tipocont_list').val();
    var tipo_contato_nome = $('#tipocont_list :selected').text();
    var cod_pais = document.getElementById("cod_pais").value.trim();

    if (ddd === ""){
        alert('Necessário ingressar o DDD do contato.');
        document.getElementById("ddd").focus();
        return 0;
    }

    if (numero === ""){
        alert('Necessário ingressar o NÚMERO do contato.');
        document.getElementById("numero_contato").focus();
        return 0;
    }

    if (cod_pais === ""){
        alert('Necessário ingressar o COD. PAÍS do contato.');
        document.getElementById("cod_pais").focus();
        return 0;
    }

    var content_div_contato = '<input type="radio" name="contato_inserido" value="contato_' + control_div_contato_prospect[div_salvar].cod + '" onclick="change_contato_prospect_button_function()">\n\
                                                  <div id="tipo_contato_' + control_div_contato_prospect[div_salvar].cod + '">' + tipo_contato_nome + '</div>: +\n\
                                                  <div id="cod_pais_' + control_div_contato_prospect[div_salvar].cod + '">' + cod_pais + '</div>\n\
                                                  (<div id="ddd_contato_' + control_div_contato_prospect[div_salvar].cod + '">' + ddd + '</div>)&nbsp;\n\
                                                  <div id="numero_contato_' + control_div_contato_prospect[div_salvar].cod + '">' + numero + '</div> <br>';

    $('#contato_' + control_div_contato_prospect[div_salvar].cod).html(content_div_contato);
    control_div_contato_prospect[div_salvar].excluida = 0;

    control_vetor_contato_prospect[div_salvar].ddd = ddd;
    control_vetor_contato_prospect[div_salvar].numero = numero;
    control_vetor_contato_prospect[div_salvar].tipo_contato = tipo_contato;
    control_vetor_contato_prospect[div_salvar].cod_pais = cod_pais;

    limpa_campos_contato_prospect_function();
}

var control_vetor_contato_prospect_json = new Array();
function ajustar_vetor_contato_prospect_function(){

	var length = control_vetor_contato_prospect.length;
	var flag;
	var j;
	for(var x=0; x<length; x++)
		control_vetor_contato_prospect_json[x] = control_vetor_contato_prospect[x];

	for(var i=0; i<length; i++){
		if(control_vetor_contato_prospect_json[i] === 0){
			flag = true;
			j = i + 1;
			while(flag && j<length){
				if(control_vetor_contato_prospect_json[j] !== 0){
					control_vetor_contato_prospect_json[i] = control_vetor_contato_prospect_json[j];
					control_vetor_contato_prospect_json[j] = 0;
					flag = false;
				}
				j++;
			}
		}
	}
	for(i=0;i<length;i++){
		if(control_vetor_contato_prospect_json[i] === 0)
			break;
	}
	control_vetor_contato_prospect_json.length = i;
}

function comercial_cadastrar_cliente_prospect_cadastrar_function(){
	ajustar_vetor_contato_prospect_function();
	ajustar_vetor_email_function();
	if(confirm('Deseja realizar o ingresso do Cliente?')){
		var values = $("input[type='radio'][name='pessoa']:checked").val();
		var tipo_pessoa;
		if(values === "pessoafisica"){
			tipo_pessoa = 0;
		}else{
			tipo_pessoa = 1;
		}
		var nome = document.getElementById("nome_razaosocial");
		var apelido  = document.getElementById("nomefantasia");
		var cod_vendedor = $('#vendedor_list :selected').val();
		var cod_usuario = document.getElementById("cod_usuario");

		if(nome.value.trim() === ""){
			alert("Necessário ingressar NOME ou RAZÃO SOCIAL do Cliente.");
			document.getElementById("nome_razaosocial").focus();
			return 0;
		}
		if(apelido.value.trim() === ""){
			if(tipo_pessoa === 1){
				alert("Necessário ingressar NOME FANTASIA do Cliente.");
				document.getElementById("nomefantasia").focus();
				return 0;
			}else{
				apelido.value = "";
			}
		}

		if(control_vetor_contato_prospect_json[0] === 0){
			alert("Necessário ingressar CONTATO do Cliente");
			document.getElementById("ddd").focus();
			return 0;
		}

		var contatoLength = control_vetor_contato_prospect_json.length;
		var adress_aux = '&QCTO=' + contatoLength;
		for(var i=0;i<contatoLength;i++){
			if(control_vetor_contato_prospect_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPOCTO_'+ i + '=' + control_vetor_contato_prospect_json[i].tipo_contato.trim();
				adress_aux = adress_aux + '&DDD_'+ i + '=' + control_vetor_contato_prospect_json[i].ddd.trim();
				adress_aux = adress_aux + '&NUMCTO_'+ i + '=' + control_vetor_contato_prospect_json[i].numero.trim();
				adress_aux = adress_aux + '&PAISCTO_'+ i + '=' + control_vetor_contato_prospect_json[i].cod_pais.trim();
			}
		}

		if(control_vetor_email_json[0] === 0){
			alert("Necessário ingressar EMAIL do Cliente");
			document.getElementById("email").focus();
			return 0;
		}

		var emailLength = control_vetor_email_json.length;
		adress_aux = adress_aux + '&QMAIL=' + emailLength;
		for(i=0;i<emailLength;i++){
			if(control_vetor_email_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&MAIL_'+ i + '=' + control_vetor_email_json[i].email.trim();
			}
		}

		d = new Date();
		var adress = url_adress + 'services/prospect?OP_CODE=CREATE&TIPO=' + tipo_pessoa + '&NOME=' + nome.value.trim() + '&NOME_FANTASIA=' + apelido.value.trim();
		adress = adress + '&DATA_INGRESSO=' + d.yyyymmdd() + '&COD_VENDEDOR='+ cod_vendedor.trim() + '&COD_USUARIO=' + cod_usuario.innerHTML.trim();
		adress = adress + adress_aux;
		document.location.href = adress;
	}
	else
		return 0;
}

function comercial_consultar_cliente_prospect_salvar_function(){
	alert("Em desenvolvimento!");
	return 0;
	ajustar_vetor_contato_prospect_function();
	ajustar_vetor_email_function();
	if(confirm('Deseja salvar os dados alterados do Cliente?')){
		var values = $("input[type='radio'][name='pessoa']:checked").val();
		var tipo_pessoa;
		if(values === "pessoafisica"){
			tipo_pessoa = 0;
		}else{
			tipo_pessoa = 1;
		}
		var nome = document.getElementById("nome_razaosocial");
		var apelido  = document.getElementById("nomefantasia");
		var cod_vendedor = $('#vendedor_list :selected').val();
		var cod_usuario = document.getElementById("cod_usuario");

		if(nome.value.trim() === ""){
			alert("Necessário ingressar NOME ou RAZÃO SOCIAL do Cliente.");
			document.getElementById("nome_razaosocial").focus();
			return 0;
		}
		if(apelido.value.trim() === ""){
			if(tipo_pessoa === 1){
				alert("Necessário ingressar NOME FANTASIA do Cliente.");
				document.getElementById("nomefantasia").focus();
				return 0;
			}else{
				apelido.value = "";
			}
		}

		if(control_vetor_contato_prospect_json[0] === 0){
			alert("Necessário ingressar CONTATO do Cliente");
			document.getElementById("ddd").focus();
			return 0;
		}

		var contatoLength = control_vetor_contato_prospect_json.length;
		var adress_aux= '&QCTO=' + contatoLength;
		for(var i=0;i<contatoLength;i++){
			if(control_vetor_contato_prospect_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&TIPOCTO_'+ i + '=' + control_vetor_contato_prospect_json[i].tipo_contato.trim();
				adress_aux = adress_aux + '&DDD_'+ i + '=' + control_vetor_contato_prospect_json[i].ddd.trim();
				adress_aux = adress_aux + '&NUMCTO_'+ i + '=' + control_vetor_contato_prospect_json[i].numero.trim();
				adress_aux = adress_aux + '&PAISCTO_'+ i + '=' + control_vetor_contato_prospect_json[i].cod_pais.trim();
			}
		}

		if(control_vetor_email_json[0] === 0){
			alert("Necessário ingressar EMAIL do Cliente");
			document.getElementById("email").focus();
			return 0;
		}

		var emailLength = control_vetor_email_json.length;
		adress_aux = adress_aux + '&QMAIL=' + emailLength;
		for(i=0;i<emailLength;i++){
			if(control_vetor_email_json[i]===0){
				break;
			}else{
				adress_aux = adress_aux + '&MAIL_'+ i + '=' + control_vetor_email_json[i].email.trim();
			}
		}
		
		var adress = url_adress + 'services/prospect?OP_CODE=UPDATE&TIPO=' + tipo_pessoa + '&NOME=' + nome.value.trim() + '&NOME_FANTASIA=' + apelido.value.trim();
		adress = adress + '&COD_VENDEDOR='+ cod_vendedor.trim() + '&COD_CLIENTE_PROSPECT=' + cod_cliente_prospect_consulta.trim() + '&COD_USUARIO=' + cod_usuario.innerHTML.trim();
		adress = adress + adress_aux;
		document.location.href = adress;
	}
	else
		return 0;
}

var contato_prospect_carregado_array = new Array();

function contato_prospect_carregado(ddd, numero, tipo_contato, cod_pais){
    this.ddd = ddd;
    this.numero = numero;
    this.tipo_contato = tipo_contato;
    this.cod_pais = cod_pais;
}

function vetor_contato_prospect_inserido(ddd, numero, tipo_contato, cod_pais){
    this.ddd = ddd;
    this.numero = numero;
    this.tipo_contato = tipo_contato;
    this.cod_pais = cod_pais;
}

function carregar_dados_consulta_cliente_prospect_function(){
    var i;
    var j;
    /*Carregar contatos*/
    var div_contato_inserido_obj = $("#contato_inserido");
    var tamanho_contato = div_contato_inserido_obj[0].childElementCount;
    clean_contato = $('#div_contato').html();
    for(i=0;i<tamanho_contato;i++){
    	contato_prospect_carregado_array[i] = new contato_prospect_carregado();
    	contato_prospect_carregado_array[i].ddd = $('#ddd_contato_oculta_' + i).html().trim();
    	contato_prospect_carregado_array[i].numero = $('#numero_contato_oculta_' + i).html().trim();
    	contato_prospect_carregado_array[i].tipo_contato = $('#tipo_contato_oculta_' + i).html().trim();
    	contato_prospect_carregado_array[i].cod_pais = $('#cod_pais_oculta_' + i).html().trim();
    }
    var tipo_contato_tipo_obj = $('#tipocont_list');
    var tipo_contato_tamanho_tipo = tipo_contato_tipo_obj[0].length;
    for (i=0; i<tipo_contato_tamanho_tipo;i++){
    	control_vetor_contato_prospect_tipo[i] = document.getElementsByName("option_contato_tipo")[i].text;
    }

    for(i=0;i<tamanho_contato;i++){
    	control_div_contato_prospect[i] = new div_contato_inseridas_prospect(0, 0);
    	control_vetor_contato_prospect[i] = new vetor_contato_prospect_inserido();
    	control_vetor_contato_prospect[i].ddd = contato_prospect_carregado_array[i].ddd;
    	control_vetor_contato_prospect[i].numero = contato_prospect_carregado_array[i].numero;
        for(j=0;j<tipo_contato_tamanho_tipo;j++){
            if(control_vetor_contato_prospect_tipo[j] === contato_prospect_carregado_array[i].tipo_contato){
            	control_vetor_contato_prospect[i].tipo_contato = Number(j+1);
                break;
            }
        }
        control_vetor_contato_prospect[i].cod_pais = contato_prospect_carregado_array[i].cod_pais;
    }
    $('#contato_inserido').html("");

    var content_div_contato = "";

    for(i=0;i<tamanho_contato;i++){
    	content_div_contato = content_div_contato + '<div id="contato_' + i + '" class="div_inseridos">\n\
        <input type="radio" name="contato_inserido" value="contato_' + i + '" onclick="change_contato_prospect_button_function()">\n\
        <div id="tipo_contato_' + i + '">' + control_vetor_contato_prospect_tipo[Number(control_vetor_contato_prospect[i].tipo_contato) - 1] + '</div>: +\n\
        <div id="cod_pais_' + i + '">' + control_vetor_contato_prospect[i].cod_pais + '</div>\n\
        (<div id="ddd_contato_' + i + '">' + control_vetor_contato_prospect[i].ddd + '</div>)\n\
        <div id="numero_contato_' + i + '">' + control_vetor_contato_prospect[i].numero + '</div> <br> </div>';
    }

    $('#contato_inserido').html(content_div_contato);

/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
    /*Carregar emails*/
    var div_email_inserido_obj = $("#emails_inserido");
    var tamanho_email = div_email_inserido_obj[0].childElementCount;
    clean_email = $('#div_email').html();
    for(i=0;i<tamanho_email;i++){
        email_carregado_array[i] = new email_carregado();
        email_carregado_array[i].email = $('#nome_email_oculta_' + i).html().trim();
    }
    for(i=0;i<tamanho_email;i++){
    	control_div_email[i] = new div_email_inseridas(0, 0);
    	control_vetor_email[i] = new vetor_email_inserido();
        control_vetor_email[i].email = email_carregado_array[i].email;
    }
    $('#emails_inserido').html("");

    var content_div_email = "";

    for(i=0;i<tamanho_email;i++){
    	content_div_email = content_div_email + '<div id="email_' + i + '" class="div_inseridos">\n\
        <input type="radio" name="email_inserido" value="email_' + i + '" onclick="change_email_button_function()">\n\
        <div id="nome_email_' + i + '">' + control_vetor_email[i].email + '</div> <br> </div>';
    }

    $('#emails_inserido').html(content_div_email);
}

function TESTE_SCHED_WORK_FUNCTION(){
	var cod_usuario = document.getElementById("cod_usuario");
	var adress = "";
	adress = url_adress + 'services/startservlet?OP_CODE=STARTFLUX&COD_USUARIO=' + cod_usuario.innerHTML.trim() + '&PROCESS_ID=1';	
	document.location.href = adress;
}