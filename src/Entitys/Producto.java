package Entitys;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.database;
import javafx.fxml.FXML;


public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;

    // Constructor
    public Producto(int id, String nombre, String descripcion, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @FXML
    // Métodos para manipular la base de datos
    public void insertar() {
        try {
            Connection conexion = database.getConexion();
            String query = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setDouble(3, this.precio);
            ps.setInt(4, this.cantidad);
            ps.executeUpdate();
            System.out.println("Producto insertado");
        } catch (SQLException e) {
            System.out.println("Error en la inserción del producto: " + e.getMessage());
        }
    }
    @FXML
    public void actualizar() {
        try {
            Connection conexion = database.getConexion();
            String query = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setDouble(3, this.precio);
            ps.setInt(4, this.cantidad);
            ps.setInt(5, this.id);
            ps.executeUpdate();
            System.out.println("Producto actualizado");
        } catch (SQLException e) {
            System.out.println("Error en la actualización del producto: " + e.getMessage());
        }
    }

    @FXML
    public void eliminar() {
        try {
            Connection conexion = database.getConexion();
            String query = "DELETE FROM productos WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, this.id);
            ps.executeUpdate();
            System.out.println("Producto eliminado");
        } catch (SQLException e) {
            System.out.println("Error en la eliminación del producto: " + e.getMessage());
        }
    }

    @FXML
    public static List<Producto> obtenerTodos() {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            Connection conexion = database.getConexion();
            String query = "SELECT * FROM productos";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")
                );
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de productos: " + e.getMessage());
        }
        return listaProductos;
    }

}
