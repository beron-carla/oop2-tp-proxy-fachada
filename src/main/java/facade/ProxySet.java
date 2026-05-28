package facade;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ProxySet implements Set<Telefono> {

    private int idPersona;
    private Set<Telefono> sujetoReal;
    private PersonaDao dao;

    public ProxySet(int idPersona, PersonaDao dao) {
        this.idPersona = idPersona;
        this.dao = dao;
        this.sujetoReal = null;
    }

    private void cargarTelefonos() {

        if (sujetoReal == null) {
            sujetoReal = dao.telefonosPorIdPersona(idPersona);
        }
    }

    @Override
    public int size() {
        cargarTelefonos();
        return sujetoReal.size();
    }

    @Override
    public boolean isEmpty() {
        cargarTelefonos();
        return sujetoReal.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        cargarTelefonos();
        return sujetoReal.contains(o);
    }

    @Override
    public Iterator<Telefono> iterator() {
        cargarTelefonos();
        return sujetoReal.iterator();
    }

    @Override
    public Object[] toArray() {
        cargarTelefonos();
        return sujetoReal.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        cargarTelefonos();
        return sujetoReal.toArray(a);
    }

    @Override
    public boolean add(Telefono telefono) {
        cargarTelefonos();
        return sujetoReal.add(telefono);
    }

    @Override
    public boolean remove(Object o) {
        cargarTelefonos();
        return sujetoReal.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        cargarTelefonos();
        return sujetoReal.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends Telefono> c) {
        cargarTelefonos();
        return sujetoReal.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        cargarTelefonos();
        return sujetoReal.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        cargarTelefonos();
        return sujetoReal.removeAll(c);
    }

    @Override
    public void clear() {

    }
}