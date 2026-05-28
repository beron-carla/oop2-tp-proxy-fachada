package proxy.ejercicio1;

public class Main {
    public static void main(String args[]) {

        //Cliente: PersonaDao
        //Sujeto: Set
        //Proxy: ProxySet
        //Sujeto Real:  Set<Telefono> sujetoReal

        PersonaDao dao = new PersonaDao();
        Persona p = dao.personaPorId(1);
        System.out.println(p.nombre());
        for (Telefono telefono : p.telefonos()) {
            System.out.println(telefono);
        }


    }
}
