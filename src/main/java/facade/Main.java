package facade;

public class Main {
    public static void main(String args[]) {
        JDBCFacade jdbcFacade = new JDBCFacade("jdbc:mysql://localhost:3307/biblioteca", "root", "");
        PersonaDao dao = new PersonaDao(jdbcFacade);
        Persona p = dao.personaPorId(1);
        System.out.println(p.nombre());
        for (Telefono telefono : p.telefonos()) {
            System.out.println(telefono);
        }


    }
}