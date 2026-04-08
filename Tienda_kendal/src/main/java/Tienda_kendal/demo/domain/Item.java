/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda_kendal.demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    // Referencia a la entidad Producto (ya cargada de la BD)
    private Producto producto;

    // Cantidad deseada por el usuario
    private int cantidad;
    private BigDecimal precioHistorico;

    // Método para calcular el subtotal
    public BigDecimal getSubTotal() {
        return producto.getPrecio().multiply(new BigDecimal(cantidad));
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioHistorico() {
        return precioHistorico;
    }

    public void setPrecioHistorico(BigDecimal precioHistorico) {
        this.precioHistorico = precioHistorico;
    }
    
    
}
