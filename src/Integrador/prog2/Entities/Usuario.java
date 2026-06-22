package Integrador.prog2.Entities;

import java.util.ArrayList;
import Integrador.prog2.Enums.Rol;

public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;
    private ArrayList<Pedido> pedidos;

    public Usuario(String nombre, String apellido, String mail, String celular,
            String contrasenia, Rol rol, Long id) {
        super(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.pedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void agregarPedido(Pedido p) {
        this.pedidos.add(p);
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Nombre: " + nombre + " " + apellido + " | Mail: " + mail + " | Celular: " + celular + " | Rol: " + rol + " | Pedidos: " + pedidos.size();
    }

}
