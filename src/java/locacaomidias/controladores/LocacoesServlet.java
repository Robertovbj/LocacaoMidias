package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.ItemLocacaoDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 * Servlet para tratar Locações.
 *
 * @author Prof. Dr. David Buzatto
 */
@WebServlet( name = "LocacoesServlet", 
             urlPatterns = { "/processaLocacoes" } )
public class LocacoesServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;
        
        try ( LocacaoDAO daoLocacao = new LocacaoDAO();
              ClienteDAO daoCliente = new ClienteDAO();
              ItemLocacaoDAO daoItemLocacao = new ItemLocacaoDAO();
              MidiaDAO daoMidia = new MidiaDAO();
              ExemplarDAO daoExemplar = new ExemplarDAO() ) {

            if ( acao.equals( "inserir" ) ) {

                Long idCliente = Utils.getLong( request, "idCliente" );
                String dataFim = request.getParameter( "dataFim" );
                String itensLocacao = request.getParameter( "itensLocacao" );
                
                // cria um leitor de json para processar os
                // itens da venda
                JsonReader jsr = Json.createReader( 
                        new StringReader( itensLocacao ) );
                // faz a leitura/parse
                JsonArray jsaItensLocacao = jsr.readArray();
                
                Cliente c = daoCliente.obterPorId( idCliente );
                
                Locacao l = new Locacao();
                l.setDataInicio( Date.valueOf( LocalDate.now() ) );
                l.setDataFim( Utils.getDate(dataFim ) );
                l.setCancelada( false );
                l.setCliente( c );
                
                Utils.validar( l, "id" );
                daoLocacao.salvar( l );
                
                System.out.println("TESTE UM DOIS");
                
                // itera pelos itens da venda genéricos
                for ( JsonValue jsv : jsaItensLocacao ) {
                    
                    // sabemos que cada item é um objeto
                    JsonObject jso = jsv.asJsonObject();
                    
                    // extraímos os atributos 
                    Long idExemplar = Utils.getLong( 
                            jso.getString( "idExemplar" ) );
                    BigDecimal valor = Utils.getBigDecimal(
                            jso.getString( "valor" ) );
                    
                    // obtém o produto e atualiza o estoque
                    Exemplar e = daoExemplar.obterPorId( idExemplar );
                    e.setDisponivel( false );
                    
                    // cria um item da venda
                    ItemLocacao il = new ItemLocacao();
                    il.setLocacao( l );
                    il.setExemplar( e );
                    il.setValorAluguel( valor );
                    
                    // não validaremos o produto, pois
                    // permitiremos estoque negativo na venda
                    daoExemplar.atualizar( e );
                    daoItemLocacao.salvar( il );
                    
                }
                
                disp = request.getRequestDispatcher(
                        "/formularios/locacoes/listagem.jsp" );

            } else if ( acao.equals( "cancelar" ) ) {

                Long id = Utils.getLong( request, "id" );
                
                Locacao l = daoLocacao.obterPorId( id );
                l.setCancelada( true );
                daoLocacao.atualizar( l );
                
                for ( ItemLocacao il : daoItemLocacao.obterPorIdLocacao( id ) ) {
                    Exemplar e = il.getExemplar();
                    e.setDisponivel( true );
                    daoExemplar.atualizarDisponivel( e );
                }
                
                response.setContentType( "application/json;charset=UTF-8" );
                
                JsonObject jo = Json.createObjectBuilder()
                        .add( "status", "ok" )
                        .build();
                
                try ( PrintWriter out = response.getWriter() ) {
                    out.print( jo );
                }

            }

        } catch ( SQLException exc ) {
            disp = Utils.prepararDespachoErro( request, exc.getMessage() );
        }

        if ( disp != null ) {
            disp.forward( request, response );
        }
        
    }

    @Override
    protected void doGet( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    protected void doPost( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    public String getServletInfo() {
        return "LocaçõesServlet";
    }

}
