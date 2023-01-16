package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.AtorAtrizDAO;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.utils.Utils;

/**
 * Servlet para tratar Atores.
 *
 * @author Roberto
 */
@WebServlet( name = "AtoresServlet", 
             urlPatterns = { "/processaAtores" } )
public class AtoresServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( AtorAtrizDAO dao = new AtorAtrizDAO() ){

            if ( acao.equals( "inserir" ) ) {

                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataEstreia = request.getParameter( "dataEstreia" );

                AtorAtriz a = new AtorAtriz();
                a.setNome( nome );
                a.setSobrenome( sobrenome );
                a.setDataEstreia( Utils.getDate( dataEstreia ) );

                Utils.validar( a, "id" );
                dao.salvar( a );
                disp = request.getRequestDispatcher(
                        "/formularios/atores/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataEstreia = request.getParameter( "dataEstreia" );

                AtorAtriz a = dao.obterPorId( id );
                a.setNome( nome );
                a.setSobrenome( sobrenome );
                a.setDataEstreia( Utils.getDate( dataEstreia ) );

                Utils.validar( a );
                dao.atualizar( a );
                disp = request.getRequestDispatcher(
                        "/formularios/atores/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                AtorAtriz a = dao.obterPorId( id );

                dao.excluir( a );
                disp = request.getRequestDispatcher(
                        "/formularios/atores/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                AtorAtriz a = dao.obterPorId( id );
                request.setAttribute( "ator", a );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/atores/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/atores/excluir.jsp" );
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
        return "AtoresServlet";
    }

}
