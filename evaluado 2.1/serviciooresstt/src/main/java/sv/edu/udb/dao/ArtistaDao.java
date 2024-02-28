package sv.edu.udb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sv.edu.udb.model.Artista;
import sv.edu.udb.model.Disco; // Importar la clase Disco

public class ArtistaDao {
    private Connection connection;

    public ArtistaDao(Connection connection) {
        this.connection = connection;
    }

    // Método para obtener todos los artistas
    public List<Artista> obtenerTodos() throws SQLException {
        List<Artista> artistas = new ArrayList<>();
        String query = "SELECT * FROM artistas";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Artista artista = new Artista();
                artista.setIdArtista(resultSet.getInt("id_artista"));
                artista.setNombreArtista(resultSet.getString("nombre_artista"));
                artista.setDescripcion(resultSet.getString("descripcion"));
                artistas.add(artista);
            }
        }
        return artistas;
    }

    // Método para obtener un artista por su ID
    public Artista obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM artistas WHERE id_artista = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Artista artista = new Artista();
                    artista.setIdArtista(resultSet.getInt("id_artista"));
                    artista.setNombreArtista(resultSet.getString("nombre_artista"));
                    artista.setDescripcion(resultSet.getString("descripcion"));
                    return artista;
                }
            }
        }
        return null;
    }

    // Método para agregar un nuevo artista
    public boolean agregarArtista(Artista artista) throws SQLException {
        String query = "INSERT INTO artistas (nombre_artista, descripcion) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, artista.getNombreArtista());
            statement.setString(2, artista.getDescripcion());
            return statement.executeUpdate() > 0;
        }
    }

    // Método para actualizar un artista
    public boolean actualizarArtista(Artista artista) throws SQLException {
        String query = "UPDATE artistas SET nombre_artista = ?, descripcion = ? WHERE id_artista = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, artista.getNombreArtista());
            statement.setString(2, artista.getDescripcion());
            statement.setInt(3, artista.getIdArtista());
            return statement.executeUpdate() > 0;
        }
    }

    // Método para eliminar un artista por su ID
    public boolean eliminarArtista(int id) throws SQLException {
        String query = "DELETE FROM artistas WHERE id_artista = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    // Método para obtener los discos de un artista por su ID
    public List<Disco> obtenerDiscosPorArtista(int idArtista) throws SQLException {
        List<Disco> discos = new ArrayList<>();
        String query = "SELECT * FROM discos WHERE id_artista = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idArtista);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Disco disco = new Disco();
                    disco.setIdDisco(resultSet.getInt("id_disco"));
                    disco.setNombreDisco(resultSet.getString("nombre_disco"));
                    disco.setIdArtista(resultSet.getInt("id_artista"));
                    disco.setNumeroCanciones(resultSet.getInt("numero_canciones"));
                    disco.setPrecio(resultSet.getDouble("precio"));
                    discos.add(disco);
                }
            }
        }
        return discos;
    }
}
