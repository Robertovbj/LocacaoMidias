<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Nova Locação</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
        <script src="${cp}/js/formularios/locacao/novo.js"></script>

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
            <a class="path">Locação</a>
        </p>

        <h1>Nova Locação</h1>



        <div class="listas">

            <div class="lista-midias">

                <jsp:useBean 
                    id="servicosE" 
                    scope="page" 
                    class="locacaomidias.servicos.ExemplarServices"/>

                <c:forEach items="${servicosE.todos}" var="exemplar">
                    <c:choose>
                        <c:when test="${exemplar.disponivel}">
                            <div class="midia" 
                                 onclick="inserirMidia(event)" 
                                 data-id="${exemplar.id}"
                                 data-titulo="${exemplar.midia.titulo}"
                                 data-valor="${exemplar.midia.classificacaoInterna.valorAluguel}"
                                 data-idMidia="${exemplar.midia.id}">
                                <p class="midia-titulo">${exemplar.midia.titulo}</p>
                                <p class="midia-quantidade">Disponível</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="midia indisponivel" data-id="${exemplar.id}">
                                <p class="midia-titulo">${exemplar.midia.titulo}</p>
                                <p class="midia-quantidade">Indisponível</p>
                            </div>
                        </c:otherwise>
                    </c:choose>   

                </c:forEach>

            </div>

            <div class="lista-selecionados">


                <form id="formNovaVenda" method="post" action="${cp}/processaLocacoes">

                    <div>
                        <jsp:useBean 
                            id="servicosC" 
                            scope="page" 
                            class="locacaomidias.servicos.ClienteServices"/>

                        <table>
                            <tr>
                                <td class="alinharDireita">Cliente:</td>
                                <td>
                                    <select id="selectCliente" name="idCliente" required>
                                        <c:forEach items="${servicosC.todos}" var="cliente">
                                            <option value="${cliente.id}">
                                                ${cliente.nome} ${cliente.sobrenome}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="alinharDireita">Data de Fim:</td>
                                <td>
                                    <input name="dataFim"
                                           type="date"
                                           size="8"
                                           placeholder="dd/mm/yyyy"
                                           required/>
                                </td>
                            </tr>
                        </table>

                    </div>

                    <table class="item-selecionados" id="itens-selecionados">

                        <!--                    <tr>
                                                <td class="itens">
                                                    Banana
                                                </td>
                                                <td class="remover">
                                                    Remover
                                                </td>
                                            </tr>-->

                    </table>

                    <input name="acao" type="hidden" value="inserir"/>
                    <input id="hiddenItensLocacao" name="itensLocacao" type="hidden"/>

                    <table class="bottom">
                        <tr>
                            <td id="divTotal" class="total">
                                Total: R$ 0,00
                            </td>
                            <td class="alinharDireita">
                                <input id="btnSalvar" type="submit" value="Salvar"/>
                            </td>
                        </tr>

                    </table>

                </form>

            </div>
        </div>


    </body>

</html>
