/*
ZIRIX CONTROL CENTER - CONTROLE DOS MENUS
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVASCRIPT E AJAX
*/

xmlDoc=loadXMLDoc("js/VariaveisZXCC.xml");
var url_adress = xmlDoc.getElementsByTagName("adress")[0].textContent;

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

$('.modal_btn').on("click", function(){
    var id = $(this).attr('id');
    switch(id) {
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
                url: url_adress + "cadastro_cli_prospect.jsp",
                success: function(result) {
                    $('.modal-content').html(result);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
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
});

$('.modal-content').on('click', '#cancel_modal', function(e){
    e.preventDefault();
    $('.modal-content').html('');
    $('.modal').modal('hide');
});