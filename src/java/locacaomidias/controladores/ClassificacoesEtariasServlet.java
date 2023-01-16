package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.utils.Utils;

/**
 * Servlet para tratar Classificações Etárias.
 *
 * @author Roberto
 */
@WebServlet( name = "ClassificacoesEtariasServlet", 
             urlPatterns = { "/processaClassificacoesEtarias" } )
public class ClassificacoesEtariasServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( ClassificacaoEtariaDAO dao = new ClassificacaoEtariaDAO() ) {

            if ( acao.equals( "inserir" ) ) {

                String descricao = request.getParameter( "descricao" );

                ClassificacaoEtaria ce = new ClassificacaoEtaria();
                ce.setDescricao( descricao );

                Utils.validar( ce, "id" );
                dao.salvar( ce );
                disp = request.getRequestDispatcher(
                        "/formularios/classificacoesEtarias/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String descricao = request.getParameter( "descricao" );

                ClassificacaoEtaria ce = dao.obterPorId( id );
                ce.setDescricao( descricao );

                Utils.validar( ce );
                dao.atualizar( ce );
                disp = request.getRequestDispatcher(
                        "/formularios/classificacoesEtarias/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                ClassificacaoEtaria ce = dao.obterPorId( id );

                dao.excluir( ce );
                disp = request.getRequestDispatcher(
                        "/formularios/classificacoesEtarias/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                ClassificacaoEtaria ce = dao.obterPorId( id );
                request.setAttribute( "classEtaria", ce );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/classificacoesEtarias/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/classificacoesEtarias/excluir.jsp" );
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
        return "ClassificacoesEtariasServlet";
    }

}
