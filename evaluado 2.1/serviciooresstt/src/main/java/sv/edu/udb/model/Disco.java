package sv.edu.udb.model;

public class Disco {
    private int idDisco;
    private String nombreDisco;
    private int idArtista;
    private int numeroCanciones;
    private double precio;

    // Constructor por defecto
    public Disco() {
    }

    // Constructor con todos los campos
    public Disco(int idDisco, String nombreDisco, int idArtista, int numeroCanciones, double precio) {
        this.idDisco = idDisco;
        this.nombreDisco = nombreDisco;
        this.idArtista = idArtista;
        this.numeroCanciones = numeroCanciones;
        this.precio = precio;
    }

    // Getters y setters
    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public String getNombreDisco() {
        return nombreDisco;
    }

    public void setNombreDisco(String nombreDisco) {
        this.nombreDisco = nombreDisco;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método toString para imprimir los datos del disco
    @Override
    public String toString() {
        return "Disco{" +
                "idDisco=" + idDisco +
                ", nombreDisco='" + nombreDisco + '\'' +
                ", idArtista=" + idArtista +
                ", numeroCanciones=" + numeroCanciones +
                ", precio=" + precio +
                '}';
    }
}
