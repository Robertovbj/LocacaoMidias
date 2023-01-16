package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.utils.Utils;

/**
 * DAO para a entidade AtorAtriz.
 *
 * @author Prof. Dr. David Buzatto
 */
public class AtorAtrizDAO extends DAO<AtorAtriz> {

    public AtorAtrizDAO() throws SQLException {
    }

    @Override
    public void salvar( AtorAtriz obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "ator_atriz(" + 
                "    nome, " + 
                "    sobrenome, " + 
                "    dataEstreia ) " + 
                "VALUES( ?, ?, ? );",
                new String[]{ "id" } );

        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setDate( 3, obj.getDataEstreia() );

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();

    }

    @Override
    public void atualizar( AtorAtriz obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE ator_atriz " + 
                "SET" + 
                "    nome = ?, " + 
                "    sobrenome = ?," + 
                "    dataEstreia = ? " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setDate( 3, obj.getDataEstreia() );
        stmt.setLong( 4, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir( AtorAtriz obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM ator_atriz " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public List<AtorAtriz> listarTodos() throws SQLException {

        List<AtorAtriz> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM ator_atriz " + 
                "ORDER BY nome, sobrenome;" );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            AtorAtriz a = new AtorAtriz();

            a.setId( rs.getLong( "id" ) );
            a.setNome( rs.getString( "nome" ) );
            a.setSobrenome( rs.getString( "sobrenome" ) );
            a.setDataEstreia( rs.getDate( "dataEstreia" ) );

            lista.add( a );

        }

        rs.close();
        stmt.close();

        return lista;

    }

    @Override
    public AtorAtriz obterPorId( Long id ) throws SQLException {

        AtorAtriz ator_atriz = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM ator_atriz " + 
                "WHERE id = ?;" );

        stmt.setLong( 1, id );

        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            ator_atriz = new AtorAtriz();

            ator_atriz.setId( rs.getLong( "id" ) );
            ator_atriz.setNome( rs.getString( "nome" ) );
            ator_atriz.setSobrenome( rs.getString( "sobrenome" ) );
            ator_atriz.setDataEstreia( rs.getDate( "dataEstreia" ) );

        }

        rs.close();
        stmt.close();

        return ator_atriz;

    }

}
