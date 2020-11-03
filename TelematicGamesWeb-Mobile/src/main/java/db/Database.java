package db;

import entities.Videogame;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

public class Database {
    private Connection connection;
    private Statement statement;
    private boolean busy = false;

    public Database() throws SQLException {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telematicgames", "root", "");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void voteForVideogameByID(Videogame videogame) {
        String sql = "UPDATE videogames SET numberVotes = %NUMBERVOTES% WHERE id = '%ID%'";
        sql = sql.replace("%ID%", videogame.getId());
        sql = sql.replace("%NUMBERVOTES%", (videogame.getNumberVotes()+1)+"");
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void insertVideogame(Videogame videogame) {
        String sql = "INSERT INTO videogames(id, name, studio, yearOfRelease, numberVotes) " +
                "VALUES ('%ID%' ,'%NAME%','%STUDIO%','%YEAROFRELEASE%', 0)";
        sql = sql.replace("%ID%", UUID.randomUUID().toString());
        sql = sql.replace("%NAME%", videogame.getName());
        sql = sql.replace("%STUDIO%", videogame.getStudio());
        sql = sql.replace("%YEAROFRELEASE%", videogame.getYearOfRelease());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Videogame> getAllVideogames() {
        ArrayList<Videogame> videogames = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM videogames");
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String studio = resultSet.getString(3);
                String yearOfRelease = resultSet.getString(4);
                int numberVotes = Integer.parseInt(resultSet.getString(5));
                Videogame videogame = new Videogame(id, name, studio, yearOfRelease, numberVotes);
                videogames.add(videogame);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Comparator<Videogame> comp = (o1, o2) -> -o1.getNumberVotes()+o2.getNumberVotes();
        Collections.sort(videogames,comp);
        return videogames;
    }

    public Videogame getVideogameByID(String id) {
        try {
            String sql = "SELECT * FROM videogames WHERE id = '%ID%'";
            sql = sql.replace("%ID%", id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                String idV = resultSet.getString(1);
                String name = resultSet.getString(2);
                String studio = resultSet.getString(3);
                String yearOfRelease = resultSet.getString(4);
                int numberVotes = Integer.parseInt(resultSet.getString(5));
                Videogame videogame = new Videogame(idV, name, studio, yearOfRelease, numberVotes);
                return videogame;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
