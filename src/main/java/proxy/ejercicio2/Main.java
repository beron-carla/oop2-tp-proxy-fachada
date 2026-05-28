package proxy.ejercicio2;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var usuario = new Usuario("User", List.of(Permiso.BASICO));
        var proxyFileAccess = new ProxyFileAccess(usuario,
                "importante-hola-mundo.txt",
                "C:\\workspace2026\\unrn\\oop2\\entregas\\proxy-prueba");
        System.out.println(proxyFileAccess.readFile());
    }
}
