package locacaomidias.controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.utils.Utils;

/**
 * Servlet para tratar Classificações Internas.
 *
 * @author Roberto
 */
@WebServlet( name = "ClassificacoesInternasServlet", 
             urlPatterns = { "/processaClassificacoesInternas" } )
public class ClassificacoesInternasServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( ClassificacaoInternaDAO dao = new ClassificacaoInternaDAO() ) {

            if ( acao.equals( "inserir" ) ) {

                String descricao = request.getParameter( "descricao" );
                BigDecimal valorAluguel = Utils.getBigDecimal(
                        request, "valorAluguel" );

                ClassificacaoInterna ci = new ClassificacaoInterna();
                ci.setDescricao( descricao );
                ci.setValorAluguel( valorAluguel );

                Utils.validar( ci, "id" );
                dao.salvar( ci );
                disp = request.getRequestDispatcher(
                        "/formularios/classificacoesInternas/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String descricao = request.getParameter( "descricao" );
                BigDecimal valorAluguel = Utils.getBigDecimal(
                        request, "valorAluguel" );

                ClassificacaoInterna ci = dao.obterPorId( id );
                ci.setDescricao( descricao );
                ci.setValorAluguel( valorAluguel );

                Utils.validar( ci );
                dao.atualizar( ci );
                disp = request.getRequestDispatcher(
                        "/formularios/classificacoesInternas/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                ClassificacaoInterna ci = dao.obterPorId( id );

                dao.excluir( ci );
                disp = request.getRequestDispatcher(
                        "/formularios/classificacoesInternas/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                ClassificacaoInterna ci = dao.obterPorId( id );
                request.setAttribute( "classInterna", ci );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/classificacoesInternas/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/classificacoesInternas/excluir.jsp" );
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
        return "ClassificacoesInternasServlet";
    }

}
