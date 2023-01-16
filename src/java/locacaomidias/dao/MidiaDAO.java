package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 * DAO para a entidade Midia.
 *
 * @author Roberto
 */
public class MidiaDAO extends DAO<Midia> {

    public MidiaDAO() throws SQLException {
    }

    @Override
    public void salvar( Midia obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "midia(" + 
                "    titulo, " + 
                "    anoLancamento, " + 
                "    codigoBarras, " + 
                "    duracaoEmMinutos, " + 
                "    ator_atriz_principal, " + 
                "    ator_atriz_coadjuvante, " + 
                "    genero_id, " + 
                "    classificacao_etaria_id, " +
                "    tipo_id, " + 
                "    classificacao_interna_id ) " + 
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );",
                new String[]{ "id" } );

        stmt.setString( 1, obj.getTitulo() );
        stmt.setString( 2, obj.getAnoLancamento() );
        stmt.setString( 3, obj.getCodigoBarras() );
        stmt.setLong( 4, obj.getDuracaoEmMinutos() );
        stmt.setLong( 5, obj.getAtorPrincipal().getId() );
        stmt.setLong( 6, obj.getAtorCoadjuvante().getId() );
        stmt.setLong( 7, obj.getGenero().getId() );
        stmt.setLong( 8, obj.getClassificacaoEtaria().getId() );
        stmt.setLong( 9, obj.getTipo().getId() );
        stmt.setLong( 10, obj.getClassificacaoInterna().getId() );

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();

    }

    @Override
    public void atualizar( Midia obj ) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE midia " + 
                "SET" + 
                "    titulo = ?, " + 
                "    anoLancamento = ?, " + 
                "    codigoBarras = ?, " + 
                "    duracaoEmMinutos = ?, " + 
                "    ator_atriz_principal = ?, " + 
                "    ator_atriz_coadjuvante = ?, " + 
                "    genero_id = ?, " + 
                "    classificacao_etaria_id = ?, " +
                "    tipo_id = ?, " + 
                "    classificacao_interna_id = ? " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setString( 1, obj.getTitulo() );
        stmt.setString( 2, obj.getAnoLancamento() );
        stmt.setString( 3, obj.getCodigoBarras() );
        stmt.setLong( 4, obj.getDuracaoEmMinutos() );
        stmt.setLong( 5, obj.getAtorPrincipal().getId() );
        stmt.setLong( 6, obj.getAtorCoadjuvante().getId() );
        stmt.setLong( 7, obj.getGenero().getId() );
        stmt.setLong( 8, obj.getClassificacaoEtaria().getId() );
        stmt.setLong( 9, obj.getTipo().getId() );
        stmt.setLong( 10, obj.getClassificacaoInterna().getId() );
        stmt.setLong( 11, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir( Midia obj ) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM midia " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public List<Midia> listarTodos() throws SQLException {

        List<Midia> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" +
                "    m.id idMidia, " +
                "    m.titulo tituloMidia, " +
                "    m.anoLancamento lancamentoMidia, " +
                "    m.codigoBarras codigoBarrasMidia, " +
                "    m.duracaoEmMinutos duracaoMidia, " +
                "    ap.id idAtorPrincipal, " +
                "    ap.nome nomeAtorPrincipal, " +
                "    ap.sobrenome sobrenomeAtorPrincipal, " +
                "    ap.dataEstreia estreiaAtorPrincipal, " +
                "    ac.id idAtorCoadjuvante, " +
                "    ac.nome nomeAtorCoadjuvante, " +
                "    ac.sobrenome sobrenomeAtorCoadjuvante, " +
                "    ac.dataEstreia estreiaAtorCoadjuvante, " +
                "    g.id idGenero, " +
                "    g.descricao descricaoGenero, " +
                "    ce.id idClassEtaria, " +
                "    ce.descricao descricaoClassEtaria, " +
                "    t.id idTipo, " +
                "    t.descricao descricaoTipo, " +
                "    ci.id idClassInterna, " +
                "    ci.descricao descricaoClassInterna, " +
                "    ci.valorAluguel valorClassInterna " +
                "FROM " +
                "    midia m, " +
                "    ator_atriz ap, " +
                "    ator_atriz ac, " +
                "    genero g, " +
                "    classificacao_etaria ce, " +
                "    tipo t, " +
                "    classificacao_interna ci " +
                "WHERE " +
                "    m.ator_atriz_principal = ap.id AND " +
                "    m.ator_atriz_coadjuvante = ac.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = ce.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id " +
                "ORDER BY m.titulo;" );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            Midia m = new Midia();
            AtorAtriz ap = new AtorAtriz();
            AtorAtriz ac = new AtorAtriz();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();

            m.setId( rs.getLong( "idMidia" ) );
            m.setTitulo( rs.getString( "tituloMidia" ) );
            m.setAnoLancamento( rs.getString( "lancamentoMidia" ) );
            m.setCodigoBarras( rs.getString( "codigoBarrasMidia" ) );
            m.setDuracaoEmMinutos( rs.getLong( "duracaoMidia" ) );
            m.setAtorPrincipal( ap );
            m.setAtorCoadjuvante( ac );
            m.setGenero( g );
            m.setClassificacaoEtaria( ce );
            m.setTipo( t );
            m.setClassificacaoInterna( ci );
            
            ap.setId( rs.getLong( "idAtorPrincipal" ) );
            ap.setNome( rs.getString( "nomeAtorPrincipal" ) );
            ap.setSobrenome( rs.getString( "sobrenomeAtorPrincipal" ) );
            ap.setDataEstreia( rs.getDate( "estreiaAtorPrincipal" ) );
            
            ac.setId( rs.getLong( "idAtorCoadjuvante" ) );
            ac.setNome( rs.getString( "nomeAtorCoadjuvante" ) );
            ac.setSobrenome( rs.getString( "sobrenomeAtorCoadjuvante" ) );
            ac.setDataEstreia( rs.getDate( "estreiaAtorCoadjuvante" ) );
            
            g.setId( rs.getLong( "idGenero" ) );
            g.setDescricao( rs.getString( "descricaoGenero" ) );
            
            ce.setId( rs.getLong( "idClassEtaria" ) );
            ce.setDescricao( rs.getString( "descricaoClassEtaria" ) );

            t.setId( rs.getLong( "idTipo" ) );
            t.setDescricao( rs.getString( "descricaoTipo" ) );
            
            ci.setId( rs.getLong( "idClassInterna" ) );
            ci.setDescricao( rs.getString( "descricaoClassInterna" ) );
            ci.setValorAluguel( rs.getBigDecimal( "valorClassInterna" ) );
            
            lista.add( m );

        }

        rs.close();
        stmt.close();

        return lista;

    }

    @Override
    public Midia obterPorId( Long id ) throws SQLException {

        Midia midia = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" +
                "    m.id idMidia, " +
                "    m.titulo tituloMidia, " +
                "    m.anoLancamento lancamentoMidia, " +
                "    m.codigoBarras codigoBarrasMidia, " +
                "    m.duracaoEmMinutos duracaoMidia, " +
                "    ap.id idAtorPrincipal, " +
                "    ap.nome nomeAtorPrincipal, " +
                "    ap.sobrenome sobrenomeAtorPrincipal, " +
                "    ap.dataEstreia estreiaAtorPrincipal, " +
                "    ac.id idAtorCoadjuvante, " +
                "    ac.nome nomeAtorCoadjuvante, " +
                "    ac.sobrenome sobrenomeAtorCoadjuvante, " +
                "    ac.dataEstreia estreiaAtorCoadjuvante, " +
                "    g.id idGenero, " +
                "    g.descricao descricaoGenero, " +
                "    ce.id idClassEtaria, " +
                "    ce.descricao descricaoClassEtaria, " +
                "    t.id idTipo, " +
                "    t.descricao descricaoTipo, " +
                "    ci.id idClassInterna, " +
                "    ci.descricao descricaoClassInterna, " +
                "    ci.valorAluguel valorClassInterna " +
                "FROM " +
                "    midia m, " +
                "    ator_atriz ap, " +
                "    ator_atriz ac, " +
                "    genero g, " +
                "    classificacao_etaria ce, " +
                "    tipo t, " +
                "    classificacao_interna ci " +
                "WHERE " +
                "    m.id = ? AND " +
                "    m.ator_atriz_principal = ap.id AND " +
                "    m.ator_atriz_coadjuvante = ac.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = ce.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id " +
                "ORDER BY m.titulo;" );

        stmt.setLong( 1, id );

        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            midia = new Midia();
            AtorAtriz ap = new AtorAtriz();
            AtorAtriz ac = new AtorAtriz();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();

            midia.setId( rs.getLong( "idMidia" ) );
            midia.setTitulo( rs.getString( "tituloMidia" ) );
            midia.setAnoLancamento( rs.getString( "lancamentoMidia" ) );
            midia.setCodigoBarras( rs.getString( "codigoBarrasMidia" ) );
            midia.setDuracaoEmMinutos( rs.getLong( "duracaoMidia" ) );
            midia.setAtorPrincipal( ap );
            midia.setAtorCoadjuvante( ac );
            midia.setGenero( g );
            midia.setClassificacaoEtaria( ce );
            midia.setTipo( t );
            midia.setClassificacaoInterna( ci );
            
            ap.setId( rs.getLong( "idAtorPrincipal" ) );
            ap.setNome( rs.getString( "nomeAtorPrincipal" ) );
            ap.setSobrenome( rs.getString( "sobrenomeAtorPrincipal" ) );
            ap.setDataEstreia( rs.getDate( "estreiaAtorPrincipal" ) );
            
            ac.setId( rs.getLong( "idAtorCoadjuvante" ) );
            ac.setNome( rs.getString( "nomeAtorCoadjuvante" ) );
            ac.setSobrenome( rs.getString( "sobrenomeAtorCoadjuvante" ) );
            ac.setDataEstreia( rs.getDate( "estreiaAtorCoadjuvante" ) );
            
            g.setId( rs.getLong( "idGenero" ) );
            g.setDescricao( rs.getString( "descricaoGenero" ) );
            
            ce.setId( rs.getLong( "idClassEtaria" ) );
            ce.setDescricao( rs.getString( "descricaoClassEtaria" ) );

            t.setId( rs.getLong( "idTipo" ) );
            t.setDescricao( rs.getString( "descricaoTipo" ) );
            
            ci.setId( rs.getLong( "idClassInterna" ) );
            ci.setDescricao( rs.getString( "descricaoClassInterna" ) );
            ci.setValorAluguel( rs.getBigDecimal( "valorClassInterna" ) );

        }

        rs.close();
        stmt.close();

        return midia;

    }

}
