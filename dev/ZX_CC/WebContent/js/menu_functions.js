/*
ZIRIX CONTROL CENTER - CONTROLE DOS MENUS
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVASCRIPT E AJAX
*/

xmlDoc=loadXMLDoc("js/VariaveisZXCC.xml");
var url_adress = xmlDoc.getElementsByTagName("adress")[0].textContent;
var ambiente = xmlDoc.getElementsByTagName("local")[0].textContent;
var cod_usuario_;

var nav = $("#topNav");
nav.find("li").each(function(){
    if ($(this).find("ul").length > 0) {
        $("<span>").text("^").appendTo($(this).children(":first"));
    }
});
nav.find(".li-menu").each(function() {
    if ($(this).find(".ul-submenu").length > 0) {
        $(this).on("click",(function() {
            $(this).find(".ul-submenu").stop(true, true).slideDown();
        }));
        $(this).mouseleave(function() {
            $(this).find(".ul-submenu").stop(true, true).slideUp();
        });
    }
});
nav.find(".li-menu").each(function() {
    if ($(this).find(".ul-submenu-last").length > 0) {
        $(this).on("click",(function() {
            $(this).find(".ul-submenu-last").stop(true, true).slideDown();
        }));
        $(this).mouseleave(function() {
            $(this).find(".ul-submenu-last").stop(true, true).slideUp();
        });
    }
});
nav.find(".li-submenu").each(function(){
    if ($(this).find(".ul-submenu-filho").length > 0) {
        $(this).mouseenter(function() {
            $(this).find(".ul-submenu-filho").stop(true, true).slideDown();
        });
        $(this).mouseleave(function() {
            $(this).find(".ul-submenu-filho").stop(true, true).slideUp();
        });
    }
});
nav.find(".li-submenu-last").each(function(){
    if ($(this).find(".ul-submenu-filho").length > 0) {
        $(this).mouseenter(function() {
            $(this).find(".ul-submenu-filho").stop(true, true).slideDown();
        });
        $(this).mouseleave(function() {
            $(this).find(".ul-submenu-filho").stop(true, true).slideUp();
        });
    }
});

function modal_click(id){
	cod_usuario = document.getElementById("cod_usuario");
	cod_usuario_ = cod_usuario.innerHTML.trim();
    this.menu_option = id.id;
    switch(menu_option) {
        case "menu_opr_cad_cli":
            $.ajax({
                url: url_adress + "cadastro_cliente.jsp",
                success: function(result) {
                    $('.modal-content').html(result);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
        case "menu_opr_cad_eqp":
            $.ajax({
                url: url_adress + "cadastro_equipamento.jsp",
                success: function(result) {
                    $('.modal-content').html(result);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
        case "menu_opr_cad_chp":
            $.ajax({
                url: url_adress + "cadastro_chip.jsp",
                success: function(result) {
                    $('.modal-content').html(result);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
        case "menu_opr_con":
            $.ajax({
                url: url_adress + "pop_up.html",
                success: function(result) {
                    var html = jQuery('<div>').html(result);
                    var content = html.find("div#operacional-consulta-content").html();
                    $('.modal-content').html(content);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
        case "menu_adm_cad_ven":
            $.ajax({
                url: url_adress + "cadastro_vendedor.jsp",
                success: function(result) {
                    $('.modal-content').html(result);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
        case "menu_com_cad_cli":
            $.ajax({
                url: url_adress + "cadastro_cli_prospect.jsp?WORK_ID=0&COD_USUARIO="+cod_usuario_,
                success: function(result) {
                    $('.modal-content').html(result);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
        case "menu_com_cad_ped":
        	if(ambiente === "DEV"){
                $.ajax({
                    url: url_adress + "cadastro_novo_pedido.jsp",
                    success: function(result) {
                        $('.modal-content').html(result);
                        $('.modal').modal({backdrop:'static'});
                    },
                    error: function(e){
                        alert('error');
                    }
                });
        	}
        	break;
        case "menu_com_con":
            $.ajax({
                url: url_adress + "pop_up.html",
                success: function(result) {
                    var html = jQuery('<div>').html(result);
                    var content = html.find("div#comercial-consulta-content").html();
                    $('.modal-content').html(content);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
        case "menu_ext":
        	if(confirm('Deseja sair do sitema?')){
        		//call invalidate session on JAVA and redirect to this url:
        		document.location.href = url_adress + "index.html";
        	}
        	break;
        case "no_permission":
        	alert('Usuário sem permissão para essa função!');
        	break;
        default:
            $.ajax({
                url: url_adress + "pop_up.html",
                success: function(result) {
                    var html = jQuery('<div>').html(result);
                    var content = html.find("div#em-construcao-content").html();
                    $('.modal-content').html(content);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
    }
}

$('.modal-content').on('click', '#cancel_modal', function(e){
    e.preventDefault();
    $('.modal-content').html('');
    $('.modal').modal('hide');
});

window.addEventListener("message", callModalTarefas, false);

function SCHED_WORK_FUNCTION(){
	cod_usuario = document.getElementById("cod_usuario");
	cod_usuario_ = cod_usuario.innerHTML.trim();
	var adress = "";
	adress = url_adress + 'services/startservlet?OP_CODE=STARTFLUX&COD_USUARIO=' + cod_usuario_ + '&PROCESS_ID=1';	
	document.location.href = adress;
}

function SCHED_WORK_START_FUNCTION(work_id,service_name){
	var message = work_id + "//" + service_name;
	window.parent.postMessage(message,'*');
}

function SCHED_WORK_END_FUNCTION(work_id){
	var CheckBox = document.getElementById("checkboxConfirmCliProsp").checked;
    if(CheckBox){
		cod_usuario = document.getElementById("cod_usuario");
		cod_usuario_ = cod_usuario.innerHTML.trim();
		var adress = "";
		adress = url_adress + 'services/startservlet?OP_CODE=ENDWORK&COD_USUARIO=' + cod_usuario_ + '&WORK_ID=' + work_id;
		document.location.href = adress;
    }
}

function callModalTarefas(event){
	cod_usuario = document.getElementById("cod_usuario");
	cod_usuario_ = cod_usuario.innerHTML.trim();
	var data = event.data;
	var work_id = "";
	var service_name = "";
	var stringSize = data.length;
	
	if(stringSize > 0){
		for(var i=0; i<stringSize; i++){
			if(data.charAt(i+1) == "/" && data.charAt(i+2) == "/"){
				work_id = data.slice(Number(0), Number(i+1));
				service_name = data.slice(Number(i+3),Number(stringSize));
				break;
			}
		}	
	    switch(service_name) {
	    case "cli_cad_prospect":
	        $.ajax({
	            url: url_adress + "cadastro_cli_prospect.jsp?WORK_ID="+work_id+"&COD_USUARIO="+cod_usuario_,
	            success: function(result) {
	                $('.modal-content').html(result);
	                $('.modal').modal({backdrop:'static'});
	            },
	            error: function(e){
	                alert('error');
	            }
	        });
	        break;
	    case "cli_verif_cad_prospect":
	    	$.ajax({
	            url: url_adress + "form_cli_prospect_verifica.jsp?WORK_ID="+work_id+"&COD_USUARIO="+cod_usuario_,
	            success: function(result) {
	                $('.modal-content').html(result);
	                $('.modal').modal({backdrop:'static'});
	            },
	            error: function(e){
	                alert('error');
	            }
	        });
	    	break;
	    default:
	        $.ajax({
	            url: url_adress + "pop_up.html",
	            success: function(result) {
	                var html = jQuery('<div>').html(result);
	                var content = html.find("div#em-construcao-content").html();
	                $('.modal-content').html(content);
	                $('.modal').modal({backdrop:'static'});
	            },
	            error: function(e){
	                alert('error');
	            }
	        });
	        break;
	    } 
	}else{
		alert("Serviço não encontrado!");
	} 
}