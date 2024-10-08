package org.CCristian.hibernateapp.repositories;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.entity.Cliente;

import java.util.List;

public class ClienteRepository implements CrudRepository<Cliente> {

    /*Acceder a la base de datos*/

    private EntityManager em;

    public ClienteRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Cliente> listar() {
        return em.createQuery("SELECT c FROM Cliente AS c", Cliente.class).getResultList();
    }

    @Override
    public Cliente porId(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public void guardar(Cliente cliente) {
        if (cliente.getId() > 0) {
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }

    @Override
    public void eliminar(Long id) {
        Cliente cliente = porId(id);
        em.remove(cliente);
    }
}
