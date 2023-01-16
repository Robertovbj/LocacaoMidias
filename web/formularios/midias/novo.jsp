<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Nova Mídia</title>
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
            <a class="path">Novo</a>
        </p>

        <h1>Nova Mídia</h1>

        <form method="post" action="${cp}/processaMidias">

            <input name="acao" type="hidden" value="inserir"/>

            <table>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td>
                        <input name="titulo"
                               type="text"
                               size="20"
                               maxlength="100"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de Lançamento:</td>
                    <td>
                        <input name="anoLancamento"
                               type="text"
                               size="20"
                               maxlength="100"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Código de Barras:</td>
                    <td>
                        <input name="codigoBarras"
                               type="text"
                               size="20"
                               pattern="\d{13}"
                               placeholder="9999999999999"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração em Minutos:</td>
                    <td>
                        <input name="duracaoEmMinutos"
                               type="number"
                               size="8"
                               placeholder="60"
                               min="0"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator/Atriz Principal:</td>
                    <td>

                        <jsp:useBean 
                            id="servicosA" 
                            scope="page" 
                            class="locacaomidias.servicos.AtorAtrizServices"/>

                        <select name="idAtorPrincipal" required>
                            <c:forEach items="${servicosA.todos}" var="ator">
                                <option value="${ator.id}">
                                    ${ator.nome} ${ator.sobrenome}
                                </option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator/Atriz Coadjuvante:</td>
                    <td>

                        <select name="idAtorCoadjuvante" required>
                            <c:forEach items="${servicosA.todos}" var="ator">
                                <option value="${ator.id}">
                                    ${ator.nome} ${ator.sobrenome}
                                </option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Gênero:</td>
                    <td>

                        <jsp:useBean 
                            id="servicosG" 
                            scope="page" 
                            class="locacaomidias.servicos.GeneroServices"/>

                        <select name="idGenero" required>
                            <c:forEach items="${servicosG.todos}" var="genero">
                                <option value="${genero.id}">
                                    ${genero.descricao}
                                </option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação Etária:</td>
                    <td>

                        <jsp:useBean 
                            id="servicosCE" 
                            scope="page" 
                            class="locacaomidias.servicos.ClassificacaoEtariaServices"/>

                        <select name="idClassEtaria" required>
                            <c:forEach items="${servicosCE.todos}" var="classEtaria">
                                <option value="${classEtaria.id}">
                                    ${classEtaria.descricao}
                                </option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Tipo:</td>
                    <td>

                        <jsp:useBean 
                            id="servicosT" 
                            scope="page" 
                            class="locacaomidias.servicos.TipoServices"/>

                        <select name="idTipo" required>
                            <c:forEach items="${servicosT.todos}" var="tipo">
                                <option value="${tipo.id}">
                                    ${tipo.descricao}
                                </option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação Interna:</td>
                    <td>

                        <jsp:useBean 
                            id="servicosCI" 
                            scope="page" 
                            class="locacaomidias.servicos.ClassificacaoInternaServices"/>

                        <select name="idClassInterna" required>
                            <c:forEach items="${servicosCI.todos}" var="classInterna">
                                <option value="${classInterna.id}">
                                    ${classInterna.descricao}
                                </option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/midias/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td class="alinharDireita">
                        <input type="submit" value="Salvar"/>
                    </td>
                </tr>
            </table>

        </form>

    </body>

</html>
