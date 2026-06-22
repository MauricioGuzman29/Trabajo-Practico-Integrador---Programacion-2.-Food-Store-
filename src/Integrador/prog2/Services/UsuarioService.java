package Integrador.prog2.Services;

import java.util.ArrayList;
import java.util.List;
import Integrador.prog2.Entities.Usuario;
import Integrador.prog2.Enums.Rol;
import Integrador.prog2.Exception.EntidadNoEncontradaException;
import Integrador.prog2.Exception.MailDuplicadoException;

public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();
    private Long ultimoId = 0L;

    public Usuario create(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
    if (mail == null || mail.isBlank()) {
        throw new RuntimeException("❌ El mail no puede estar vacío.");
    }
    if (usuarios.stream().anyMatch(u -> u.getMail().equalsIgnoreCase(mail) && !u.isEliminado())) {
        throw new MailDuplicadoException("⚠️ El mail ya está registrado");
    }

    ultimoId++;
    Usuario u = new Usuario(nombre, apellido, mail, celular, contrasenia, rol, ultimoId);
    usuarios.add(u);
    return u;
}


    public List<Usuario> listar() {
        return usuarios.stream().filter(u -> !u.isEliminado()).toList();
    }

    public Usuario findById(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id) && !u.isEliminado()).findFirst().orElse(null);
    }

    public void update(Long id, String nombre, String apellido, String mail, String celular) {
        Usuario u = findById(id);
        if (u == null || u.isEliminado()) {
            throw new EntidadNoEncontradaException("⚠️ Usuario no encontrado o eliminado");
        }
        if (nombre != null && !nombre.isBlank()) u.setNombre(nombre);
        if (apellido != null && !apellido.isBlank()) u.setApellido(apellido);
        if (mail != null && !mail.isBlank() && !mail.equalsIgnoreCase(u.getMail())) {
            if (usuarios.stream().anyMatch(us -> !us.getId().equals(id) && us.getMail().equalsIgnoreCase(mail) && !us.isEliminado())) {
                throw new MailDuplicadoException("⚠️ El mail ya está registrado");
            }
            u.setMail(mail);
        }
        if (celular != null && !celular.isBlank()) u.setCelular(celular);
    }

    public void delete(Long id) {
        Usuario u = findById(id);
        if (u == null) throw new EntidadNoEncontradaException("⚠️ Usuario no encontrado");
        u.setEliminado(true);
    }
}
