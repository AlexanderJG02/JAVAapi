package sv.edu.udb.dao;

//DiscoDao.java

import sv.edu.udb.model.Disco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscoDao {
 private Connection connection;

 public DiscoDao(Connection connection) {
     this.connection = connection;
 }

 public List<Disco> obtenerTodos() throws SQLException {
     List<Disco> discos = new ArrayList<>();
     String query = "SELECT * FROM discos";
     try (PreparedStatement statement = connection.prepareStatement(query);
          ResultSet resultSet = statement.executeQuery()) {
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
     return discos;
 }

 public Disco obtenerPorId(int id) throws SQLException {
     String query = "SELECT * FROM discos WHERE id_disco = ?";
     try (PreparedStatement statement = connection.prepareStatement(query)) {
         statement.setInt(1, id);
         try (ResultSet resultSet = statement.executeQuery()) {
             if (resultSet.next()) {
                 Disco disco = new Disco();
                 disco.setIdDisco(resultSet.getInt("id_disco"));
                 disco.setNombreDisco(resultSet.getString("nombre_disco"));
                 disco.setIdArtista(resultSet.getInt("id_artista"));
                 disco.setNumeroCanciones(resultSet.getInt("numero_canciones"));
                 disco.setPrecio(resultSet.getDouble("precio"));
                 return disco;
             }
         }
     }
     return null;
 }

 public boolean agregarDisco(Disco disco) throws SQLException {
     String query = "INSERT INTO discos (nombre_disco, id_artista, numero_canciones, precio) VALUES (?, ?, ?, ?)";
     try (PreparedStatement statement = connection.prepareStatement(query)) {
         statement.setString(1, disco.getNombreDisco());
         statement.setInt(2, disco.getIdArtista());
         statement.setInt(3, disco.getNumeroCanciones());
         statement.setDouble(4, disco.getPrecio());
         return statement.executeUpdate() > 0;
     }
 }

 public boolean actualizarDisco(Disco disco) throws SQLException {
     String query = "UPDATE discos SET nombre_disco = ?, id_artista = ?, numero_canciones = ?, precio = ? WHERE id_disco = ?";
     try (PreparedStatement statement = connection.prepareStatement(query)) {
         statement.setString(1, disco.getNombreDisco());
         statement.setInt(2, disco.getIdArtista());
         statement.setInt(3, disco.getNumeroCanciones());
         statement.setDouble(4, disco.getPrecio());
         statement.setInt(5, disco.getIdDisco());
         return statement.executeUpdate() > 0;
     }
 }

 public boolean eliminarDisco(int id) throws SQLException {
     String query = "DELETE FROM discos WHERE id_disco = ?";
     try (PreparedStatement statement = connection.prepareStatement(query)) {
         statement.setInt(1, id);
         return statement.executeUpdate() > 0;
     }
 }
}

