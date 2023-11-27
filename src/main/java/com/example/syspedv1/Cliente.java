package com.example.syspedv1;

import entity.ClienteEntity;

public interface Cliente {
    public boolean verificarExistencia(String cedula);
    public ClienteEntity obtenerCliente(String cedula);
    public boolean registrarCliente(String cedula, String nombres, String apellidos, String email, String telefono);
}
