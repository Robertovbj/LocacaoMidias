<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Excluir Cliente</title>
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
            <a class="path" href="${cp}/formularios/clientes/listagem.jsp">Clientes</a>
            >
            <a class="path">Excluir</a>
        </p>

        <h1>Excluir Cliente</h1>

        <form method="post" action="${cp}/processaClientes">

            <input name="acao" type="hidden" value="excluir"/>
            <input name="id" type="hidden" value="${requestScope.cliente.id}"/>

            <table>
                <tr>
                    <td class="alinharDireita">Nome:</td>
                    <td>${requestScope.cliente.nome}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Sobrenome:</td>
                    <td>${requestScope.cliente.sobrenome}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Data de Nascimento:</td>
                    <td>
                        <fmt:formatDate 
                            pattern="dd/MM/yyyy"
                            value="${requestScope.cliente.dataNascimento}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">CPF:</td>
                    <td>${requestScope.cliente.cpf}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">E-mail:</td>
                    <td>${requestScope.cliente.email}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Logradouro:</td>
                    <td>${requestScope.cliente.logradouro}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Número:</td>
                    <td>${requestScope.cliente.numero}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Bairro:</td>
                    <td>${requestScope.cliente.bairro}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">CEP:</td>
                    <td>${requestScope.cliente.cep}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Cidade:</td>
                    <td>${requestScope.cliente.cidade.nome}</td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/clientes/listagem.jsp">
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
