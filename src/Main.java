import java.sql.*;
import java.util.ArrayList;

public class Main {
    static final int type_Show = 2;
    static final int type_Season = 3;
    static final int type_Episode = 4;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<TVShow> shows = new ArrayList<TVShow>();
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:media.db");


        Statement stmt = conn.createStatement();
        ResultSet cursor = stmt.executeQuery("SELECT id, metadata_type, title"
                + " FROM metadata_items "
                + " WHERE metadata_type = 2");

        while(cursor.next()) {
            int entry_ID = cursor.getInt("id");
            int metadata_type = cursor.getInt("metadata_type");
            String title = cursor.getString("title");

            if(metadata_type == type_Show){
                TVShow new_show = new TVShow(entry_ID, title);
                shows.add(new_show);
            }
        }


        stmt = conn.createStatement();
        cursor = stmt.executeQuery("SELECT id, metadata_type, parent_id, title"
                + " FROM metadata_items "
                + " WHERE metadata_type = 3");

        while(cursor.next()) {
            int entry_ID = cursor.getInt("id");
            int metadata_type = cursor.getInt("metadata_type");
            int parent_ID = cursor.getInt("parent_id");
            String title = cursor.getString("title");

            if(metadata_type == type_Season) {
                Season new_season = new Season(entry_ID, title);
                for (TVShow show : shows) {
                    if(show.show_id == parent_ID){
                        show.append_season(new_season);
                    }
                }
            }
        }


        stmt = conn.createStatement();
        cursor = stmt.executeQuery("SELECT id, metadata_type, parent_id, title, duration, [index]"
                + " FROM metadata_items "
                + " WHERE metadata_type = 4");

            while(cursor.next()) {
                int entry_ID = cursor.getInt("id");
                int metadata_type = cursor.getInt("metadata_type");
                int parent_ID = cursor.getInt("parent_id");
                String title = cursor.getString("title");
                int duration = cursor.getInt("duration");
                int index = cursor.getInt("index");
                //summary = row[6]

                if(metadata_type == type_Episode) {
                    Episode new_episode = new Episode(entry_ID, title, duration, index, "summary", parent_ID);
                    for(TVShow show : shows){
                        show.DoesEpisodeBelongInShow(new_episode);
                    }
                }
            }


        for(TVShow show : shows) {
            System.out.print(show.ToXML());
        }

        conn.close();
    }
}