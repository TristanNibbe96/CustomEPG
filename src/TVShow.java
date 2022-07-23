import java.util.ArrayList;

public class TVShow {
    ArrayList<Season> seasons;
    int show_id;
    String show_title;

    public TVShow(int show_id, String show_title) {
        this.show_id = show_id;
        this.show_title = show_title;
        this.seasons = new ArrayList<Season>();
    }

    public void append_season(Season season) {
        season.SetSeasonNumber(this.seasons.size() + 1);
        this.seasons.add(season);
    }

    public String ToString() {
        StringBuilder string_val = new StringBuilder(this.show_title);

        for( Season season : this.seasons) {
            string_val.append("\n").append(season.ToString());
        }

        return string_val.toString();
    }

    public boolean DoesEpisodeBelongInShow(Episode episode){
        for(Season season : this.seasons) {
            if(episode.parent_ID == season.season_id){
                season.AppendEpisode(episode);
                return true;
            }
        }

        return false;
    }

    public String ToXML() {
        StringBuilder xml_val = new StringBuilder("\n<TVShow title=\"" + this.show_title + "\" ID=\"" + Integer.toString(this.show_id) + "\">");

        for(Season season : this.seasons) {
            xml_val.append(season.ToXML());
        }

        xml_val.append("\n</TVShow>");
        return xml_val.toString();
    }
}
