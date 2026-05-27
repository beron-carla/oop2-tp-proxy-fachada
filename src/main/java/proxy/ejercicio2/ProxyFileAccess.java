package proxy.ejercicio2;

import java.io.IOException;

public class ProxyFileAccess implements FileAccessInterface {

    private FileAccess fileAccess; //real sujeto
    private Usuario usuario;
    private String nombre;
    private String ruta;

    public ProxyFileAccess(Usuario user, String nombre, String ruta) {
        this.usuario = user;
        this.nombre = nombre;
        this.ruta = ruta;
        fileAccess = null;
    }

    @Override
    public String readFile() throws IOException {
        if (nombre.startsWith("i")) {
            if (usuario.poseePermiso(Permiso.ADMIN) || usuario.poseePermiso(Permiso.INTERMEDIO)) {
                crearArchivo();
                return fileAccess.readFile();
            }

        }

        if (nombre.startsWith("m"))
            if (usuario.poseePermiso(Permiso.ADMIN) || usuario.poseePermiso(Permiso.INTERMEDIO)) {
                crearArchivo();
                return fileAccess.readFile();
            }

        if (!nombre.startsWith("m") && !nombre.startsWith("i")) {
            fileAccess = new FileAccess(this.ruta, this.nombre);
            return fileAccess.readFile();
        }
        return "No se pudo leer el archivo";
    }

    private void crearArchivo() {
        if (fileAccess == null) {
            fileAccess = new FileAccess(this.ruta, this.nombre);
        }
    }


//    Cualquier otro archivo, lo ven todos los usuarios sin importar qué permiso tengan.
//    Utilice Usuarios#possePermiso para verificar permisos.
//    En caso de intento de lectura sin permiso lance una excepción indicando el error.
}
