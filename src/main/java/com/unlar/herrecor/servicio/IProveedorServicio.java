package com.unlar.herrecor.servicio;

import com.unlar.herrecor.modelo.Cliente;
import com.unlar.herrecor.modelo.Proveedor;

import java.util.List;

public interface IProveedorServicio {
    public List<Proveedor> listarProveedores();

    public Proveedor buscarProveedorPorId(Integer idProveedor);

    public Proveedor guardarProveedor(Proveedor proveedor);

    public void eliminarProveedor(Proveedor proveedor);
}
