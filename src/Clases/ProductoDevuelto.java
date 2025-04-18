/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Caleb
 */
public class ProductoDevuelto {
    private String idProducto;
    private String nombreProducto;
    private String nombreProveedor;
    private int cantidad;
    private String motivo;
    private String rutaImagen; // ruta local de la imagen

    public ProductoDevuelto(String idProducto, String nombreProducto, String nombreProveedor,
                            int cantidad, String motivo, String rutaImagen) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.nombreProveedor = nombreProveedor;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.rutaImagen = rutaImagen;
    }

    public String getIdProducto() { return idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public String getNombreProveedor() { return nombreProveedor; }
    public int getCantidad() { return cantidad; }
    public String getMotivo() { return motivo; }
    public String getRutaImagen() { return rutaImagen; }
}
