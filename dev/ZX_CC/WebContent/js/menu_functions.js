/*
ZIRIX CONTROL CENTER - MODAL PAGE
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVASCRIPT E AJAX
*/

var ip_adress = "http://192.168.0.50:8080/zxcc/";
//var ip_adress = "http://192.168.0.32:8080/zxcc_prod/";

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
                url: ip_adress + "cadastro_cliente.jsp",
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
                url: ip_adress + "cadastro_equipamento.jsp",
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
                url: ip_adress + "cadastro_chip.jsp",
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
                url: ip_adress + "pop_up.html",
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
                url: ip_adress + "cadastro_vendedor.jsp",
                success: function(result) {
                    $('.modal-content').html(result);
                    $('.modal').modal({backdrop:'static'});
                },
                error: function(e){
                    alert('error');
                }
            });
            break;
        case "menu_ext":
        	if(confirm('Deseja sair do sitema?')){
        		//call invalidate session on JAVA and redirect do this url:
        		document.location.href = ip_adress + "index.html";
        	}
        	break;
        case "no_permission":
        	alert('Usuário sem permissão para essa função!');
        	break;
        default:
            $.ajax({
                url: ip_adress + "pop_up.html",
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