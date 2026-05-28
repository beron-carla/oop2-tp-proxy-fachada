package facade.ejercicio4;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PersonaDao {
    DBFacade dbFacade;

    public PersonaDao(DBFacade facade) {
        this.dbFacade = facade;
    }

    public Persona personaPorId(int id) {
        String sql = "select nombre from personas where id = " + id;
        dbFacade.open();
        List<Map<String, String>> rows = dbFacade.queryResultAsAsociation(sql);
        dbFacade.close();
        Set<Telefono> telefonos = new ProxySet(id, this);
        return new Persona(rows.get(0).get("nombre"), telefonos);
    }

    public Set<Telefono> telefonosPorIdPersona(int idPersona) {
        String sql = "select numero from telefonos where idPersona = " + idPersona;
        dbFacade.open();
        List<Map<String, String>> rows = dbFacade.queryResultAsAsociation(sql);
        dbFacade.close();
        Set<Telefono> telefonos = new HashSet<>();
        for (Map<String, String> row : rows) {
            telefonos.add(new Telefono(row.get("numero")));
        }
        return telefonos;
    }
}