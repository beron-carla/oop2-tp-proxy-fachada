package facade;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCFacade implements DBFacade {
    public String url;
    public String user;
    public String password;
    Connection conn;

    public JDBCFacade(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    @Override
    public void open() {
        try {
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Map<String, String>> queryResultAsAsociation(String sql) {
        try {
            PreparedStatement statement = this.conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            ResultSetMetaData meta = result.getMetaData();
            int cantColumnas = meta.getColumnCount();
            List<Map<String, String>> datos = new ArrayList<>();
            while (result.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= cantColumnas; i++) {
                    String nombreColumna = meta.getColumnName(i);
                    String valor = result.getString(i);
                    row.put(nombreColumna, valor);
                }
                datos.add(row);

            }
            return datos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String[]> queryResultAsArray(String sql) {
        try {
            PreparedStatement statement = this.conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            ResultSetMetaData meta = result.getMetaData();
            int cantColumnas = meta.getColumnCount();
            List<String[]> datos = new ArrayList<>();
            while (result.next()) {
                String[] arreglo = new String[cantColumnas];
                for (int i = 1; i <= cantColumnas; i++) {
                    String valor = result.getString(i);
                    arreglo[i - 1] = valor;
                }
                datos.add(arreglo);
            }
            return datos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

        try {
            this.conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
