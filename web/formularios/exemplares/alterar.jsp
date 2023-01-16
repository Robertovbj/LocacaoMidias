<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Alterar Exemplar</title>
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
            <a class="path" href="${cp}/formularios/exemplares/listagem.jsp">Exemplares</a>
            >
            <a class="path">Alterar</a>
        </p>

        <h1>Alterar Exemplar</h1>

        <form method="post"  action="${cp}/processaExemplares">

            <input name="acao" type="hidden" value="alterar"/>
            <input name="id" type="hidden" value="${requestScope.exemplar.id}"/>

            <table>
                <tr>
                    <td class="alinharDireita">Disponível:</td>
                    <td>

                        <c:choose>
                            <c:when test="${requestScope.exemplar.disponivel}">
                                <input name="disponivel"
                                       type="radio"
                                       required
                                       value="true"
                                       checked/> Sim

                                <input name="disponivel"
                                       type="radio"
                                       required
                                       value="false"/> Não
                            </c:when>
                            <c:otherwise>
                                <input name="disponivel"
                                       type="radio"
                                       required
                                       value="true"/> Sim

                                <input name="disponivel"
                                       type="radio"
                                       required
                                       value="false"
                                       checked/> Não
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td>

                        <jsp:useBean 
                            id="servicos"
                            scope="page"
                            class="locacaomidias.servicos.MidiaServices"/>

                        <select name="idMidia" required>
                            <c:forEach items="${servicos.todos}" var="midia">
                                <c:choose>
                                    <c:when test="${requestScope.exemplar.midia.id eq midia.id}">
                                        <option value="${midia.id}" selected>
                                            ${midia.titulo}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${midia.id}">
                                            ${midia.titulo}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/exemplares/listagem.jsp">Voltar</a>
                    </td>
                    <td class="alinharDireita">
                        <input type="submit" value="Alterar"/>
                    </td>
                </tr>
            </table>

        </form>

    </body>

</html>
