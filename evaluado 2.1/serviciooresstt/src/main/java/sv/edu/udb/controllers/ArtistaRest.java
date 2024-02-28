package sv.edu.udb.controllers;

import sv.edu.udb.dao.ArtistaDao;
import sv.edu.udb.model.Artista;
import sv.edu.udb.model.Disco;
import sv.edu.udb.util.ConexionBD;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/Artistas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistaRest {
    private final ArtistaDao artistaDAO;

    public ArtistaRest() {
        Connection connection;
        try {
            connection = ConexionBD.obtenerConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al establecer la conexión a la base de datos");
        }
        this.artistaDAO = new ArtistaDao(connection);
    }

    @GET
    public Response obtenerTodos() {
        try {
            List<Artista> artistas = artistaDAO.obtenerTodos();
            return Response.ok().entity(artistas).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") int id) {
        try {
            Artista artista = artistaDAO.obtenerPorId(id);
            if (artista != null) {
                return Response.ok().entity(artista).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Artista no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response agregarArtista(Artista artista) {
        try {
            boolean agregado = artistaDAO.agregarArtista(artista);
            if (agregado) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al agregar el artista").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizarArtista(@PathParam("id") int id, Artista artista) {
        try {
            artista.setIdArtista(id);
            boolean actualizado = artistaDAO.actualizarArtista(artista);
            if (actualizado) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Artista no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarArtista(@PathParam("id") int id) {
        try {
            boolean eliminado = artistaDAO.eliminarArtista(id);
            if (eliminado) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Artista no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/discos")
    public Response obtenerDiscosPorArtista(@PathParam("id") int idArtista) {
        try {
            // Obtener los discos asociados con el ID del artista
            List<Disco> discos = artistaDAO.obtenerDiscosPorArtista(idArtista);

            // Verificar si se encontraron discos
            if (!discos.isEmpty()) {
                return Response.ok().entity(discos).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron discos para el artista con ID " + idArtista).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
