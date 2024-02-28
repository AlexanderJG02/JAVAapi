package sv.edu.udb.model;

public class Artista {
    private int idArtista;
    private String nombreArtista;
    private String descripcion;

    // Constructor por defecto
    public Artista() {
    }

    // Constructor con todos los campos
    public Artista(int idArtista, String nombreArtista, String descripcion) {
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método toString para imprimir los datos del artista
    @Override
    public String toString() {
        return "Artista{" +
                "idArtista=" + idArtista +
                ", nombreArtista='" + nombreArtista + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
