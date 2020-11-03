package services;

import db.ConnectionPool;
import db.Database;
import entities.Videogame;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;


@Stateless
@Path("videogame")
public class VideogameServices {

    @Path("insert")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Videogame insertVideogame(Videogame videogame){
        Database database = ConnectionPool.getAvailableConnection();
        database.insertVideogame(videogame);
        database.setBusy(false);
        return videogame;
    }

    @Path("getall")
    @GET
    @Produces("application/json")
    public ArrayList<Videogame> getAllVideogames(){
        Database database = ConnectionPool.getAvailableConnection();
        ArrayList<Videogame> videogames = database.getAllVideogames();
        database.setBusy(false);
        return videogames;
    }

    @GET
    @Path("gettop3")
    @Produces("application/json")
    public ArrayList<Videogame> getTop3(){
        Database database = ConnectionPool.getAvailableConnection();
        ArrayList<Videogame> videogames = database.getAllVideogames();
        database.setBusy(false);
        return videogames;
    }

    @Path("vote/{id}")
    @PUT
    public void voteForVideogameByID(@PathParam("id") String id){
        Database database = ConnectionPool.getAvailableConnection();
        Videogame videogame = database.getVideogameByID(id);
        database.voteForVideogameByID(videogame);
        database.setBusy(false);
    }

}
