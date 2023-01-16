package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.Tipo;

/**
 * Classe de serviços para a entidade Tipo.
 *
 * @author Roberto
 */
public class TipoServices {

    /**
     * Usa o TipoDAO para obter todos os estados.
     *
     * @return Lista de Tipos.
     */
    public List<Tipo> getTodos() {

        List<Tipo> lista = new ArrayList<>();

        try ( TipoDAO dao = new TipoDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
