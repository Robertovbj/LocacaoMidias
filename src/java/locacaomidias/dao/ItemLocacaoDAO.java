package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;

/**
 * DAO para a entidade ItemLocacao.
 *
 * @author Roberto
 */
public class ItemLocacaoDAO extends DAO<ItemLocacao> {

    public ItemLocacaoDAO() throws SQLException {
    }

    @Override
    public void salvar( ItemLocacao obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "item_locacao(" + 
                "    locacao_id, " + 
                "    exemplar_codigo_interno, " +
                "    valor ) " + 
                "VALUES( ?, ?, ? );" );

        stmt.setLong( 1, obj.getLocacao().getId() );
        stmt.setLong( 2, obj.getExemplar().getId() );
        stmt.setBigDecimal( 3, obj.getValorAluguel() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void atualizar( ItemLocacao obj ) throws SQLException {
        
        // não faz sentido na nossa implementação,
        // pois não é possível atualizar um item
        // da locação armazenado

    }

    @Override
    public void excluir( ItemLocacao obj ) throws SQLException {
        
        // não faz sentido na nossa implementação,
        // pois não é possível excluir um item
        // da locação armazenado
        
    }

    @Override
    public List<ItemLocacao> listarTodos() throws SQLException {

        // nesse caso, não há sentido haver uma listagem por todos
        // os itens de locação, visto que essa entidade é uma entidade
        // de ligação e não faremos atualização em locações 
        // já realizadas, a não ser o cancelamento, mas isso trataremos
        // usando o método obterPorIdLocacao implementado abaixo.
        return null;

    }

    @Override
    public ItemLocacao obterPorId( Long id ) throws SQLException {

        // o identificador dessa entidade é composto!
        // precisamos ter um método especializado se fosse
        // necessário.
        return null;

    }
    
    /**
     * Obtenção de todos os itens de venda por um identificador da locação.
     * Esse método será utilizado para o ajuste do estoque das locações
     * que forem canceladas ou devolvidas. Apenas os valores necessários serão obtidos.
     */
    public List<ItemLocacao> obterPorIdLocacao( Long idLocacao ) throws SQLException {

        List<ItemLocacao> itensLocacao = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    e.codigo_interno idExemplar, " + 
                "    e.disponivel dispExemplar " +
                "FROM" +
                "    item_locacao il, " +
                "    exemplar e " + 
                "WHERE" +
                "    il.exemplar_codigo_interno = e.codigo_interno AND " + 
                "    il.locacao_id = ?;" );

        stmt.setLong( 1, idLocacao );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            ItemLocacao il = new ItemLocacao();
            Exemplar e = new Exemplar();
            
            il.setExemplar( e );
            
            e.setId( rs.getLong( "idExemplar" ) );
            e.setDisponivel(rs.getBoolean("dispExemplar" ) );
            
            itensLocacao.add( il );

        }

        rs.close();
        stmt.close();

        return itensLocacao;

    }

}
