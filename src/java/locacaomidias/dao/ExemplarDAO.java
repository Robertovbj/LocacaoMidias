package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 * DAO para a entidade Midia.
 *
 * @author Roberto
 */
public class ExemplarDAO extends DAO<Exemplar> {

    public ExemplarDAO() throws SQLException {
    }

    @Override
    public void salvar( Exemplar obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "exemplar(" + 
                "    disponivel, " + 
                "    midia_id ) " + 
                "VALUES( ?, ? );",
                new String[]{ "id" } );

        stmt.setBoolean(1, obj.getDisponivel() );
        stmt.setLong( 2, obj.getMidia().getId() );

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();

    }

    @Override
    public void atualizar( Exemplar obj ) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE exemplar " + 
                "SET" + 
                "    disponivel = ?, " + 
                "    midia_id = ? " + 
                "WHERE" + 
                "    codigo_interno = ?;" );

        stmt.setBoolean( 1, obj.getDisponivel() );
        stmt.setLong( 2, obj.getMidia().getId() );
        stmt.setLong( 3, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir( Exemplar obj ) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM exemplar " + 
                "WHERE" + 
                "    codigo_interno = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public List<Exemplar> listarTodos() throws SQLException {

        List<Exemplar> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" +
                "    e.codigo_interno idExemplar, " +
                "    e.disponivel dispExemplar, " +
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
                "    exemplar e, " +
                "    midia m, " +
                "    ator_atriz ap, " +
                "    ator_atriz ac, " +
                "    genero g, " +
                "    classificacao_etaria ce, " +
                "    tipo t, " +
                "    classificacao_interna ci " +
                "WHERE " +
                "    e.midia_id = m.id AND " +
                "    m.ator_atriz_principal = ap.id AND " +
                "    m.ator_atriz_coadjuvante = ac.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = ce.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id " +
                "ORDER BY e.codigo_interno, m.titulo;" );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            Exemplar e = new Exemplar();
            Midia m = new Midia();
            AtorAtriz ap = new AtorAtriz();
            AtorAtriz ac = new AtorAtriz();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();

            e.setId( rs.getLong( "idExemplar" ) );
            e.setDisponivel( rs.getBoolean( "dispExemplar" ) );
            e.setMidia( m );
            
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
            
            lista.add( e );

        }

        rs.close();
        stmt.close();

        return lista;

    }

    @Override
    public Exemplar obterPorId( Long id ) throws SQLException {

        Exemplar exemplar = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" +
                "    e.codigo_interno idExemplar, " +
                "    e.disponivel dispExemplar, " +
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
                "    exemplar e, " +
                "    midia m, " +
                "    ator_atriz ap, " +
                "    ator_atriz ac, " +
                "    genero g, " +
                "    classificacao_etaria ce, " +
                "    tipo t, " +
                "    classificacao_interna ci " +
                "WHERE " +
                "    e.codigo_interno = ? AND " +
                "    e.midia_id = m.id AND " +
                "    m.ator_atriz_principal = ap.id AND " +
                "    m.ator_atriz_coadjuvante = ac.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = ce.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id " +
                "ORDER BY e.codigo_interno, m.titulo;" );

        stmt.setLong( 1, id );

        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            exemplar = new Exemplar();
            Midia m = new Midia();
            AtorAtriz ap = new AtorAtriz();
            AtorAtriz ac = new AtorAtriz();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            exemplar.setId( rs.getLong( "idExemplar" ) );
            exemplar.setDisponivel( rs.getBoolean( "dispExemplar" ) );
            exemplar.setMidia( m );

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

        }

        rs.close();
        stmt.close();

        return exemplar;

    }
    
    /**
     * Atualização do estoque para o cancelamento de vendas.
     */
    public void atualizarDisponivel( Exemplar obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE exemplar " + 
                "SET" + 
                "    disponivel = ? " + 
                "WHERE" + 
                "    codigo_interno = ?;" );

        stmt.setBoolean( 1, obj.getDisponivel() );
        stmt.setLong( 2, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

}
