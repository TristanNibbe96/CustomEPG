import java.util.ArrayList;

public class Season {
    ArrayList<Episode> episodes;
    int season_id;
    String season_title;
    int season_number;

    public Season(int season_id, String season_title) {
        this.season_id = season_id;
        this.season_title = season_title;
        this.episodes = new ArrayList<Episode>();
    }

    public void SetSeasonNumber(int season_number) {
        this.season_number = season_number;
    }

    public void AppendEpisode(Episode episode) {
        this.episodes.add(episode);
    }

    public String ToString() {
        StringBuilder string_val = new StringBuilder("Season " + Integer.toString(this.season_number) + ":");

        for(Episode episode : this.episodes) {
            string_val.append(episode.ToString());
        }

        return string_val.toString();
    }

    public String ToXML() {
        StringBuilder xml_val = new StringBuilder("\n\t<Season Number=\"" + Integer.toString(this.season_number) + "\" ID=\"" + Integer.toString(this.season_id) + "\">");

        for(Episode episode : this.episodes) {
            xml_val.append("\n\t\t").append(episode.ToXML());
        }

        xml_val.append("\n\t</Season>");
        return xml_val.toString();
    }
}
