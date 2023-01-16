package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.utils.Utils;

/**
 * DAO para a entidade ClassificacaoInterna.
 *
 * @author Prof. Dr. David Buzatto
 */
public class ClassificacaoInternaDAO extends DAO<ClassificacaoInterna> {

    public ClassificacaoInternaDAO() throws SQLException {
    }

    @Override
    public void salvar( ClassificacaoInterna obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "classificacao_interna( descricao, valorAluguel ) " + 
                "VALUES( ?, ? );",
                new String[]{ "id" } );

        stmt.setString( 1, obj.getDescricao() );
        stmt.setBigDecimal(2, obj.getValorAluguel() );

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();

    }

    @Override
    public void atualizar( ClassificacaoInterna obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE classificacao_interna " + 
                "SET" + 
                "    descricao = ?," + 
                "    valorAluguel = ? " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setString( 1, obj.getDescricao() );
        stmt.setBigDecimal(2, obj.getValorAluguel() );
        stmt.setLong( 3, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir( ClassificacaoInterna obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM classificacao_interna " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public List<ClassificacaoInterna> listarTodos() throws SQLException {

        List<ClassificacaoInterna> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM classificacao_interna " + 
                "ORDER BY descricao, valorAluguel;" );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            ClassificacaoInterna e = new ClassificacaoInterna();

            e.setId( rs.getLong( "id" ) );
            e.setDescricao( rs.getString( "descricao" ) );
            e.setValorAluguel( rs.getBigDecimal( "valorAluguel" ) );

            lista.add( e );

        }

        rs.close();
        stmt.close();

        return lista;

    }

    @Override
    public ClassificacaoInterna obterPorId( Long id ) throws SQLException {

        ClassificacaoInterna classificacao_interna = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM classificacao_interna " + 
                "WHERE id = ?;" );

        stmt.setLong( 1, id );

        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            classificacao_interna = new ClassificacaoInterna();

            classificacao_interna.setId( rs.getLong( "id" ) );
            classificacao_interna.setDescricao( rs.getString( "descricao" ) );
            classificacao_interna.setValorAluguel( rs.getBigDecimal( "valorAluguel" ) );

        }

        rs.close();
        stmt.close();

        return classificacao_interna;

    }

}
