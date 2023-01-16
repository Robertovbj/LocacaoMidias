package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 * Servlet para tratar Tipos.
 *
 * @author Roberto
 */
@WebServlet( name = "TiposServlet", 
             urlPatterns = { "/processaTipos" } )
public class TiposServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( TipoDAO dao = new TipoDAO() ) {

            if ( acao.equals( "inserir" ) ) {

                String descricao = request.getParameter( "descricao" );

                Tipo t = new Tipo();
                t.setDescricao( descricao );

                Utils.validar( t, "id" );
                dao.salvar( t );
                disp = request.getRequestDispatcher(
                        "/formularios/tipos/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String descricao = request.getParameter( "descricao" );

                Tipo t = dao.obterPorId( id );
                t.setDescricao( descricao );

                Utils.validar( t );
                dao.atualizar( t );
                disp = request.getRequestDispatcher(
                        "/formularios/tipos/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Tipo t = dao.obterPorId( id );

                dao.excluir( t );
                disp = request.getRequestDispatcher(
                        "/formularios/tipos/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Tipo t = dao.obterPorId( id );
                request.setAttribute( "tipo", t );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/tipos/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/tipos/excluir.jsp" );
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
        return "TiposServlet";
    }

}
