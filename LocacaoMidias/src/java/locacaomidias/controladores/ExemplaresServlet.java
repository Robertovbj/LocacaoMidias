package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Midia;
import locacaomidias.utils.Utils;

/**
 * Servlet para tratar Exemplares.
 *
 * @author Roberto
 */
@WebServlet( name = "ExemplaresServlet", 
             urlPatterns = { "/processaExemplares" } )
public class ExemplaresServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( ExemplarDAO daoExemplar = new ExemplarDAO();
              MidiaDAO daoMidia = new MidiaDAO() ){

            if ( acao.equals( "inserir" ) ) {

                Boolean disponivel = Utils.getBoolean( request, "disponivel" );
                Long idMidia = Utils.getLong( request, "idMidia" );

                Midia m = daoMidia.obterPorId( idMidia );

                Exemplar e = new Exemplar();
                e.setDisponivel( disponivel );
                e.setMidia( m );

                Utils.validar( e, "id" );
                daoExemplar.salvar( e );
                disp = request.getRequestDispatcher(
                        "/formularios/exemplares/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                Boolean disponivel = Utils.getBoolean( request, "disponivel" );
                Long idMidia = Utils.getLong( request, "idMidia" );

                Midia m = daoMidia.obterPorId( idMidia );

                Exemplar e = daoExemplar.obterPorId( id );
                e.setDisponivel( disponivel );
                e.setMidia( m );

                Utils.validar( e );
                daoExemplar.atualizar( e );
                disp = request.getRequestDispatcher(
                        "/formularios/exemplares/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Exemplar e = daoExemplar.obterPorId( id );

                daoExemplar.excluir( e );
                disp = request.getRequestDispatcher(
                        "/formularios/exemplares/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Exemplar e = daoExemplar.obterPorId( id );
                request.setAttribute( "exemplar", e );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/exemplares/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/exemplares/excluir.jsp" );
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
        return "ExemplaresServlet";
    }

}
