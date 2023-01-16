package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.utils.Utils;

/**
 * DAO para a entidade ClassificacaoEtaria.
 *
 * @author Roberto
 */
public class ClassificacaoEtariaDAO extends DAO<ClassificacaoEtaria> {

    public ClassificacaoEtariaDAO() throws SQLException {
    }

    @Override
    public void salvar( ClassificacaoEtaria obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "classificacao_etaria( descricao ) " + 
                "VALUES( ? );",
                new String[]{ "id" } );

        stmt.setString( 1, obj.getDescricao() );

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();

    }

    @Override
    public void atualizar( ClassificacaoEtaria obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE classificacao_etaria " + 
                "SET" + 
                "    descricao = ? " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setString( 1, obj.getDescricao() );
        stmt.setLong( 2, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir( ClassificacaoEtaria obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM classificacao_etaria " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public List<ClassificacaoEtaria> listarTodos() throws SQLException {

        List<ClassificacaoEtaria> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM classificacao_etaria " + 
                "ORDER BY descricao;" );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            ClassificacaoEtaria t = new ClassificacaoEtaria();

            t.setId( rs.getLong( "id" ) );
            t.setDescricao( rs.getString( "descricao" ) );

            lista.add( t );

        }

        rs.close();
        stmt.close();

        return lista;

    }

    @Override
    public ClassificacaoEtaria obterPorId( Long id ) throws SQLException {

        ClassificacaoEtaria classificacao_etaria = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM classificacao_etaria " + 
                "WHERE id = ?;" );

        stmt.setLong( 1, id );

        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            classificacao_etaria = new ClassificacaoEtaria();

            classificacao_etaria.setId( rs.getLong( "id" ) );
            classificacao_etaria.setDescricao( rs.getString( "descricao" ) );

        }

        rs.close();
        stmt.close();

        return classificacao_etaria;

    }

}
