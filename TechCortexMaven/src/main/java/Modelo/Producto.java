package Modelo;

public class Producto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock;
    private String url_imagen;
    private Categoria categorias;
    private Marca Marcas;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String descripcion, Double precio, int stock, String url_imagen, Categoria categorias, Marca Marcas) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.url_imagen = url_imagen;
        this.categorias = categorias;
        this.Marcas = Marcas;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public Categoria getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria categorias) {
        this.categorias = categorias;
    }

    public Marca getMarcas() {
        return Marcas;
    }

    public void setMarcas(Marca Marcas) {
        this.Marcas = Marcas;
    }

}
