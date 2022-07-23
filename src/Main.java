import java.sql.*;
import java.util.ArrayList;

public class Main {
    static final int type_Show = 2;
    static final int type_Season = 3;
    static final int type_Episode = 4;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        ArrayList<TVShow> shows = Main.ExtractShowsFromDB();

        Main.ExtractSeasonsFromDB(shows);
        Main.ExtractEpisodesFromDB(shows);

        for(TVShow show : shows) {
            System.out.print(show.ToXML());
        }
    }

    public static ArrayList<TVShow> ExtractShowsFromDB() throws SQLException {
        ArrayList<TVShow> shows = new ArrayList<>();

        Connection conn = DriverManager.getConnection("jdbc:sqlite:media.db");
        Statement stmt = conn.createStatement();
        ResultSet cursor = stmt.executeQuery("SELECT id, metadata_type, title"
                + " FROM metadata_items "
                + " WHERE metadata_type = " + type_Show);

        while(cursor.next()) {
            int entry_ID = cursor.getInt("id");
            int metadata_type = cursor.getInt("metadata_type");
            String title = cursor.getString("title");

            if(metadata_type == type_Show){
                TVShow new_show = new TVShow(entry_ID, title);
                shows.add(new_show);
            }
        }
        conn.close();
        return shows;
    }

    public static void ExtractSeasonsFromDB(ArrayList<TVShow> shows) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:media.db");
        Statement stmt = conn.createStatement();
        ResultSet cursor = stmt.executeQuery("SELECT id, metadata_type, parent_id, title, [index]"
                + " FROM metadata_items "
                + " WHERE metadata_type = " + type_Season);

        while(cursor.next()) {
            int entry_ID = cursor.getInt("id");
            int metadata_type = cursor.getInt("metadata_type");
            int parent_ID = cursor.getInt("parent_id");
            String title = cursor.getString("title");
            int index = cursor.getInt("index");

            if(metadata_type == type_Season) {
                Season new_season = new Season(entry_ID, title, index);
                for (TVShow show : shows) {
                    if(show.show_id == parent_ID){
                        show.append_season(new_season);
                    }
                }
            }
        }
        conn.close();
    }

    public static void ExtractEpisodesFromDB(ArrayList<TVShow> shows) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:media.db");
        Statement stmt = conn.createStatement();
        ResultSet cursor = stmt.executeQuery("SELECT id, metadata_type, parent_id, title, duration, [index]"
                + " FROM metadata_items "
                + " WHERE metadata_type = " + type_Episode);

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
                    if(show.DoesEpisodeBelongInShow(new_episode)){
                        break;
                    }
                }
            }
        }
        conn.close();
    }
}