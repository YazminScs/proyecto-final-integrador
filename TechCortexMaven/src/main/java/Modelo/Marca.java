package Modelo;

public class Marca {

    private int idMarca;
    private String nombre;
    private String url_imagen;

    public Marca() {
    }

    public Marca(int idMarca, String nombre, String url_imagen) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.url_imagen = url_imagen;
    }
    
    public Marca(String url_imagen){
        this.url_imagen = url_imagen;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }
}
