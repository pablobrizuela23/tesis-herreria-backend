package com.unlar.herrecor.servicio;

import com.unlar.herrecor.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> listarEmpleados();

    public Cliente buscarClientePorId(Integer idCliente);

    public Cliente guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);
}
