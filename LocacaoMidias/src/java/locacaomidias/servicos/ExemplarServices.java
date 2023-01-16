package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.entidades.Exemplar;

/**
 * Classe de serviços para a entidade Exemplar.
 *
 * @author Roberto
 */
public class ExemplarServices {

    /**
     * Usa o ExemplarDAO para obter todos os estados.
     *
     * @return Lista de Exemplares.
     */
    public List<Exemplar> getTodos() {

        List<Exemplar> lista = new ArrayList<>();

        try ( ExemplarDAO dao = new ExemplarDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
