package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.entidades.Genero;
import locacaomidias.utils.Utils;

/**
 * Servlet para tratar Generos.
 *
 * @author Roberto
 */
@WebServlet( name = "GenerosServlet", 
             urlPatterns = { "/processaGeneros" } )
public class GenerosServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( GeneroDAO dao = new GeneroDAO() ) {

            if ( acao.equals( "inserir" ) ) {

                String descricao = request.getParameter( "descricao" );

                Genero g = new Genero();
                g.setDescricao( descricao );

                Utils.validar( g, "id" );
                dao.salvar( g );
                disp = request.getRequestDispatcher(
                        "/formularios/generos/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String descricao = request.getParameter( "descricao" );

                Genero g = dao.obterPorId( id );
                g.setDescricao( descricao );

                Utils.validar( g );
                dao.atualizar( g );
                disp = request.getRequestDispatcher(
                        "/formularios/generos/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Genero g = dao.obterPorId( id );

                dao.excluir( g );
                disp = request.getRequestDispatcher(
                        "/formularios/generos/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Genero g = dao.obterPorId( id );
                request.setAttribute( "genero", g );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/generos/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/generos/excluir.jsp" );
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
        return "GenerosServlet";
    }

}
