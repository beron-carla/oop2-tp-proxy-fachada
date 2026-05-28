package proxy.ejercicio1;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao {
    //no cierra la conexión
    private Connection obtenerConexion() {
        try {
            String url = "jdbc:mysql://localhost:3307/biblioteca";
            String usuario = "root";
            String password = "basededatos1*";
            return DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Persona personaPorId(int id) {
        String sql = "select nombre from personas where id = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            String nombrePersona = null;
            while (result.next()) {
                nombrePersona = result.getString(1);
                Set<Telefono> telefonos = new ProxySet(id, this);
                return new Persona(id, nombrePersona, telefonos);

//                telefonos.add(new Telefono(result.getString(2)));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Telefono> telefonosPorIdPersona(int idPersona) {
        String sql = "select numero from telefonos where idPersona = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)
        ) {
            statement.setInt(1, idPersona);
            ResultSet result = statement.executeQuery();

            Set<Telefono> telefonos = new HashSet<>();
            while (result.next()) {
                telefonos.add(new Telefono(result.getString(1)));
            }
            return telefonos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}