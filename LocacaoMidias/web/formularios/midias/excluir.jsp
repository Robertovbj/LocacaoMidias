<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Excluir Mídia</title>
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
            <a class="path" href="${cp}/formularios/midias/listagem.jsp">Mídias</a>
            >
            <a class="path">Excluir</a>
        </p>

        <h1>Excluir Mídia</h1>

        <form method="post" action="${cp}/processaMidias">

            <input name="acao" type="hidden" value="excluir"/>
            <input name="id" type="hidden" value="${requestScope.midia.id}"/>
            <fmt:setLocale value="pt_BR" />

            <table>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td>${requestScope.midia.titulo}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de Lançamento:</td>
                    <td>${requestScope.midia.anoLancamento}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Código de Barras:</td>
                    <td>${requestScope.midia.codigoBarras}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração:</td>
                    <td>${requestScope.midia.duracaoEmMinutos}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator/Atriz Principal:</td>
                    <td>${requestScope.midia.atorPrincipal.nome} ${requestScope.midia.atorPrincipal.sobrenome}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator/Atriz Coadjuvante:</td>
                    <td>${requestScope.midia.atorCoadjuvante.nome} ${requestScope.midia.atorCoadjuvante.sobrenome}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Gênero:</td>
                    <td>${requestScope.midia.genero.descricao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação Etária:</td>
                    <td>${requestScope.midia.classificacaoEtaria.descricao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Tipo:</td>
                    <td>${requestScope.midia.tipo.descricao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação Interna:</td>
                    <td>${requestScope.midia.classificacaoInterna.descricao}</td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/midias/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td class="alinharDireita">
                        <input type="submit" value="Excluir"/>
                    </td>
                </tr>
            </table>

        </form>

    </body>

</html>
