package proxy.ejercicio2;

import java.io.IOException;

public class ProxyFileAccess implements FileAccessInterface {

    private FileAccess fileAccess;
    private Usuario usuario;
    private String nombre;
    private String ruta;

    public ProxyFileAccess(Usuario usuario, String nombre, String ruta) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.ruta = ruta;
    }

    @Override
    public String readFile() throws IOException {

        if (!tienePermisoDeLectura()) {
            return "Denegado";
        }

        inicializarArchivo();

        return fileAccess.readFile();
    }

    private boolean tienePermisoDeLectura() {

        if (esArchivoImportante()) {
            return usuario.poseePermiso(Permiso.ADMIN);
        }

        if (esArchivoIntermedio()) {
            return usuario.poseePermiso(Permiso.ADMIN)
                    || usuario.poseePermiso(Permiso.INTERMEDIO);
        }

        return true;
    }

    private boolean esArchivoImportante() {
        return nombre.startsWith("i");
    }

    private boolean esArchivoIntermedio() {
        return nombre.startsWith("m");
    }

    private void inicializarArchivo() {

        if (fileAccess == null) {
            fileAccess = new FileAccess(ruta, nombre);
        }
    }
}

