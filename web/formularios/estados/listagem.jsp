<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaEstados?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Estados Cadastrados</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>

    <body>

        <div class="header">
            <div class="header-logo">Locadora YYY</div>
            <div class="header-alugar">
                <a href="${cp}/formularios/locacoes/novo.jsp" class="header-alugar-text">
                    Alugar agora
                </a>
            </div>
            <div class="dropdown">
                <button class="dropbtn">Cadastros</button>
                <div class="dropdown-content">
                    <a href="${cp}/formularios/atores/listagem.jsp">
                        Atores
                    </a>
                    <a href="${cp}/formularios/cidades/listagem.jsp">
                        Cidades
                    </a>
                    <a href="${cp}/formularios/classificacoesEtarias/listagem.jsp">
                        Classificações Etárias
                    </a>
                    <a href="${cp}/formularios/classificacoesInternas/listagem.jsp">
                        Classificações Internas
                    </a>
                    <a href="${cp}/formularios/clientes/listagem.jsp">
                        Clientes
                    </a>
                    <a href="${cp}/formularios/estados/listagem.jsp">
                        Estados
                    </a>
                    <a href="${cp}/formularios/exemplares/listagem.jsp">
                        Exemplares
                    </a>
                    <a href="${cp}/formularios/generos/listagem.jsp">
                        Gêneros
                    </a>
                    <a href="${cp}/formularios/locacoes/listagem.jsp">
                        Locações
                    </a>
                    <a href="${cp}/formularios/midias/listagem.jsp">
                        Mídias
                    </a>
                    <a href="${cp}/formularios/tipos/listagem.jsp">
                        Tipo
                    </a>
                </div>
            </div>
            <div class="user">Olá, David Buzzato</div>
        </div>

        <p class="path">
            <a class="path" href="${cp}">Início</a> 
            > 
            <a class="path">Cadastros</a>
            >
            <a class="path">Estados</a>
        </p>

        <h1>Estados Cadastrados</h1>

        <p>
            <a href="${cp}/formularios/estados/novo.jsp">
                Novo Estado
            </a>
        </p>

        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Sigla</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>

                <jsp:useBean 
                    id="servicos"
                    scope="page"
                    class="locacaomidias.servicos.EstadoServices"/>

                <c:forEach items="${servicos.todos}" var="estado">
                    <tr>
                        <td>${estado.id}</td>
                        <td>${estado.nome}</td>
                        <td>${estado.sigla}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${estado.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${estado.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        <p>
            <a href="${cp}/formularios/estados/novo.jsp">
                Novo Estado
            </a>
        </p>

        <p><a href="${cp}/index.jsp">Tela Principal</a></p>

    </body>

</html>
