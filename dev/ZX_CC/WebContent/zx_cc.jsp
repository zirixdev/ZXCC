<!DOCTYPE html>
<!--
ZIRIX CONTROL CENTER - MAIN PAGE
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: HTML5 E JAVASCRIPT
-->

<%@ page import="zirix.zxcc.server.*,zirix.zxcc.server.dao.*,java.sql.SQLException,java.util.Vector" %>

<%
	String[] pkVal = {request.getParameter("COD_USUARIO")};
	ZxAccessControlBean bean = new ZxAccessControlBean(pkVal);
	Vector<String[]> permissaoUsuarioList = bean.getPermissaoUsuario();
	Vector<String[]> permissaoGrupoList = bean.getPermissaoGrupo();
%>

<html lang="pt-br">  
    <head>
        <title>ZX_CC</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta charset="utf-8">    
        <link rel="stylesheet" href="css/Bootstrap/bootstrap.min.css">   
        <link rel="stylesheet" href="css/nav.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/modal.css">
        <link rel="stylesheet" href="css/sizes.css">
    </head>  
    <body>    
        <div class="container">        
            <header>
           		&nbsp;Bem-vindo: <%=bean.getNomeUsuario()%>
            </header>
            <div class="page-background">
                <nav id="topNav">
                    <ul>
                        <li class="li-menu">
                            <a id="menu_op">Operacional</a>
                            <ul class="ul-submenu">
                                <li class="li-submenu">
                                    <a id="menu_op_cad">Cadastro</a>
                                    <ul class="ul-submenu-filho">
                                        <li>
                                            <a id="menu_op_cad_cliente" class="modal_btn">Cliente</a>
                                        </li>
                                        <li>
                                            <a id="menu_op_cad_equip" class="modal_btn">Equipamento</a>
                                        </li>
                                        <li class="last">
                                            <a id="menu_op_cad_chip" class="modal_btn">Sim Card</a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="li-submenu-last">
                                    <a id="menu_op_con" class="modal_btn">Consultas</a>
                                </li>
                            </ul>
                        </li>
                        <li class="li-menu">
                            <a id="menu_adm">Administrativo</a>
                            <ul class="ul-submenu">
                                <li class="li-submenu-last">
                                    <a id="menu_adm_cad">Cadastro</a>
                                    <ul class="ul-submenu-filho">
                                        <li class="last">
                                            <a id="menu_adm_cad_vendedor" class="modal_btn">Vendedor</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="li-menu">
                            <a id="menu_com">Comercial</a>
                            <ul class="ul-submenu">
                                <li class="last">
                                    <a id="menu_com_ped" class="modal_btn">Novo Pedido</a>
                                </li>
                            </ul>
                        </li>
                        <li class="li-menu">
                            <a id="menu_rel" class="modal_btn">Relatórios</a>
                        </li>
                        <li class="li-menu">
                            <a id="menu_conf" class="modal_btn">Configurações</a>
                        </li>
                        <li class="li-menu">
                            <a id="menu_aju" class="modal_btn">Ajuda</a>
                        </li>
                        <li class="li-menu">
                            <a id="menu_logoff" class="modal_btn">Sair</a>
                        </li>
                    </ul>
                </nav>
                <section class="tarefas"></section>
                <section class="conteudo"></section>
            </div>
            <footer>
               <h1>Zirix - Control Center</h1>
            </footer>  

            <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <!--Aqui entra o modal a ser chamado-->
                    </div>
                </div>
            </div>        
        </div>    
        <script src="js/jquery.js"></script>
        <script src="js/modernizr.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/menu_functions.js"></script>
        <script src="js/page_functions.js"></script>
  </body>

</html>