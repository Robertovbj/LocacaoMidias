package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.AtorAtrizDAO;
import locacaomidias.entidades.AtorAtriz;

/**
 * Classe de serviços para a entidade AtorAtriz.
 *
 * @author Roberto
 */
public class AtorAtrizServices {

    /**
     * Usa o AtorAtrizDAO para obter todos os AtorAtrizs.
     *
     * @return Lista de AtorAtrizes.
     */
    public List<AtorAtriz> getTodos() {

        List<AtorAtriz> lista = new ArrayList<>();

        try ( AtorAtrizDAO dao = new AtorAtrizDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
