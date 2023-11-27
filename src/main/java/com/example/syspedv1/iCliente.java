package com.example.syspedv1;

import entity.ClienteEntity;

public interface iCliente {


    public boolean verificarExistencia(String cedula);
    public ClienteEntity obtenerCliente(String cedula);
    public void guardarEnDBCliente(String cedula, String nombres, String apellidos, String email, String telefono);

}
