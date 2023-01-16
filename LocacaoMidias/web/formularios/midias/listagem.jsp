<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaMidias?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Midias Cadastradas</title>
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
            <a class="path">Mídias</a>
        </p>

        <h1>Mídias Cadastradas</h1>

        <p>
            <a href="${cp}/formularios/midias/novo.jsp">
                Nova Mídia
            </a>
        </p>

        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Ano de Lançamento</th>
                    <th>Duração (em min.)</th>
                    <th>Class. Interna</th>
                    <th>Ator/Atriz Principal</th>
                    <th>Ator/Atriz Coadjuvante</th>
                    <th>Gênero</th>
                    <th>Class. Etária</th>
                    <th>Tipo</th>
                    <th>Código de Barras</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>

                <fmt:setLocale value="pt_BR" />

                <jsp:useBean 
                    id="servicos"
                    scope="page"
                    class="locacaomidias.servicos.MidiaServices"/>

                <c:forEach items="${servicos.todos}" var="midia">
                    <tr>
                        <td>${midia.id}</td>
                        <td>${midia.titulo}</td>
                        <td>${midia.anoLancamento}</td>
                        <td>${midia.duracaoEmMinutos}</td>
                        <td>${midia.classificacaoInterna.descricao}</td>
                        <td>${midia.atorPrincipal.nome} ${midia.atorPrincipal.sobrenome}</td>
                        <td>${midia.atorCoadjuvante.nome} ${midia.atorCoadjuvante.sobrenome}</td>
                        <td>${midia.genero.descricao}</td>
                        <td>${midia.classificacaoEtaria.descricao}</td>
                        <td>${midia.tipo.descricao}</td>
                        <td>${midia.codigoBarras}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${midia.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${midia.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        <p>
            <a href="${cp}/formularios/midias/novo.jsp">
                Novo Midia
            </a>
        </p>

        <p><a href="${cp}/index.jsp">Tela Principal</a></p>

    </body>

</html>
