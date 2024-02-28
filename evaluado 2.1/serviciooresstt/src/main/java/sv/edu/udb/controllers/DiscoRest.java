package sv.edu.udb.controllers;

import sv.edu.udb.dao.DiscoDao;
import sv.edu.udb.model.Disco;
import sv.edu.udb.util.ConexionBD;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/Discos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DiscoRest {
    private final DiscoDao discoDao;

    public DiscoRest() {
        Connection connection;
        try {
            connection = ConexionBD.obtenerConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al establecer la conexión a la base de datos");
        }
        this.discoDao = new DiscoDao(connection);
    }

    @GET
    public Response obtenerTodos() {
        try {
            List<Disco> discos = discoDao.obtenerTodos();
            return Response.ok().entity(discos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") int id) {
        try {
            Disco disco = discoDao.obtenerPorId(id);
            if (disco != null) {
                return Response.ok().entity(disco).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Disco no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response agregarDisco(Disco disco) {
        try {
            boolean agregado = discoDao.agregarDisco(disco);
            if (agregado) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al agregar el disco").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizarDisco(@PathParam("id") int id, Disco disco) {
        try {
            disco.setIdDisco(id);
            boolean actualizado = discoDao.actualizarDisco(disco);
            if (actualizado) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Disco no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarDisco(@PathParam("id") int id) {
        try {
            boolean eliminado = discoDao.eliminarDisco(id);
            if (eliminado) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Disco no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
