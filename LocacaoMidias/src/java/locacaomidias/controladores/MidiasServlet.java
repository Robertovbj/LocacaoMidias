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
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 * Servlet para tratar Midias.
 *
 * @author Roberto
 */
@WebServlet( name = "MidiasServlet", 
             urlPatterns = { "/processaMidias" } )
public class MidiasServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( MidiaDAO dao = new MidiaDAO() ) {
            AtorAtrizDAO daoAtor = new AtorAtrizDAO();
            GeneroDAO daoGenero = new GeneroDAO();
            ClassificacaoEtariaDAO daoClassEtaria = new ClassificacaoEtariaDAO();
            TipoDAO daoTipo = new TipoDAO();
            ClassificacaoInternaDAO daoClassInterna = new ClassificacaoInternaDAO();

            if ( acao.equals( "inserir" ) ) {

                String titulo = request.getParameter( "titulo" );
                String anoLancamento = request.getParameter( "anoLancamento" );
                String codigoBarras = request.getParameter( "codigoBarras" );
                Long duracaoEmMinutos = Utils.getLong(
                        request, "duracaoEmMinutos");
                Long idAtorPrincipal = Utils.getLong( 
                        request, "idAtorPrincipal" );
                Long idAtorCoadjuvante = Utils.getLong( 
                        request, "idAtorCoadjuvante" );
                Long idGenero = Utils.getLong( 
                        request, "idGenero" );
                Long idClassEtaria = Utils.getLong(
                        request, "idClassEtaria" );
                Long idTipo = Utils.getLong( 
                        request, "idTipo" );
                Long idClassInterna = Utils.getLong( 
                        request, "idClassInterna" );
                
                AtorAtriz ap = daoAtor.obterPorId( idAtorPrincipal );
                AtorAtriz ac = daoAtor.obterPorId( idAtorCoadjuvante );
                Genero g = daoGenero.obterPorId( idGenero );
                ClassificacaoEtaria ce = daoClassEtaria.obterPorId( idClassEtaria );
                Tipo t = daoTipo.obterPorId( idTipo );
                ClassificacaoInterna ci = daoClassInterna.obterPorId( idClassInterna );

                Midia m = new Midia();
                m.setTitulo( titulo );
                m.setAnoLancamento( anoLancamento );
                m.setCodigoBarras( codigoBarras );
                m.setDuracaoEmMinutos( duracaoEmMinutos );
                m.setAtorPrincipal( ap );
                m.setAtorCoadjuvante( ac );
                m.setGenero( g );
                m.setClassificacaoEtaria( ce );
                m.setTipo( t );
                m.setClassificacaoInterna( ci );

                Utils.validar( m, "id" );
                dao.salvar( m );
                disp = request.getRequestDispatcher(
                        "/formularios/midias/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String titulo = request.getParameter( "titulo" );
                String anoLancamento = request.getParameter( "anoLancamento" );
                String codigoBarras = request.getParameter( "codigoBarras" );
                Long duracaoEmMinutos = Utils.getLong(
                        request, "duracaoEmMinutos");
                Long idAtorPrincipal = Utils.getLong( 
                        request, "idAtorPrincipal" );
                Long idAtorCoadjuvante = Utils.getLong( 
                        request, "idAtorCoadjuvante" );
                Long idGenero = Utils.getLong( 
                        request, "idGenero" );
                Long idClassEtaria = Utils.getLong(
                        request, "idClassEtaria" );
                Long idTipo = Utils.getLong( 
                        request, "idTipo" );
                Long idClassInterna = Utils.getLong( 
                        request, "idClassInterna" );

                AtorAtriz ap = daoAtor.obterPorId( idAtorPrincipal );
                AtorAtriz ac = daoAtor.obterPorId( idAtorCoadjuvante );
                Genero g = daoGenero.obterPorId( idGenero );
                ClassificacaoEtaria ce = daoClassEtaria.obterPorId( idClassEtaria );
                Tipo t = daoTipo.obterPorId( idTipo );
                ClassificacaoInterna ci = daoClassInterna.obterPorId( idClassInterna );

                Midia m = dao.obterPorId(id);
                m.setTitulo( titulo );
                m.setAnoLancamento( anoLancamento );
                m.setCodigoBarras( codigoBarras );
                m.setDuracaoEmMinutos( duracaoEmMinutos );
                m.setAtorPrincipal( ap );
                m.setAtorCoadjuvante( ac );
                m.setGenero( g );
                m.setClassificacaoEtaria( ce );
                m.setTipo( t );
                m.setClassificacaoInterna( ci );

                Utils.validar( m );
                dao.atualizar( m );
                disp = request.getRequestDispatcher(
                        "/formularios/midias/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Midia m = dao.obterPorId( id );

                dao.excluir( m );
                disp = request.getRequestDispatcher(
                        "/formularios/midias/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Midia m = dao.obterPorId( id );
                request.setAttribute( "midia", m );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/midias/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/midias/excluir.jsp" );
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
        return "MidiasServlet";
    }

}
