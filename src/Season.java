import java.util.ArrayList;

public class Season {
    ArrayList<Episode> episodes;
    int season_id;
    String season_title;
    int index;

    public Season(int season_id, String season_title, int index) {
        this.season_id = season_id;
        this.season_title = season_title;
        this.episodes = new ArrayList<>();
        this.index = index;
    }

    public void AppendEpisode(Episode episode) {
        this.episodes.add(episode);
    }

    public String ToString() {
        StringBuilder string_val = new StringBuilder("Season " + this.index + ":");

        for(Episode episode : this.episodes) {
            string_val.append(episode.ToString());
        }

        return string_val.toString();
    }

    public String ToXML() {
        StringBuilder xml_val = new StringBuilder("\n\t<Season Number=\"" + this.index + "\" ID=\"" + this.season_id + "\">");

        for(Episode episode : this.episodes) {
            xml_val.append("\n\t\t").append(episode.ToXML());
        }

        xml_val.append("\n\t</Season>");
        return xml_val.toString();
    }
}
