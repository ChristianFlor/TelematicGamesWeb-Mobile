package db;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
    public static ArrayList<Database> connections = new ArrayList<>();


    public static Database getAvailableConnection(){

        for(int i = 0 ; i<connections.size() ; i++){

            if(!connections.get(i).isBusy()){
                connections.get(i).setBusy(true);
                return connections.get(i);
            }

        }

        try {
            Database instance = new Database();
            connections.add(instance);
            instance.setBusy(true);
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
            //Algoritmo de reintento
            return null;
        }


    }

}
