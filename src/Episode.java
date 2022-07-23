public class Episode {
    int ep_id;
    String ep_title;
    int ep_duration;
    int ep_index;
    String ep_summary;

    public Episode(int ep_id, String ep_title, int duration, int index, String summary) {
        this.ep_id = ep_id;
        this.ep_title = ep_title;
        this.ep_duration = duration;
        this.ep_index = index;
        this.ep_summary = summary;
    }

    public String ToString() {
        String string_val = "";
        string_val += Integer.toString(this.ep_id) + " " + this.ep_title;
        return string_val;
    }

    public String ToXML() {
        String xml_val = "<Episode title=\"" + this.ep_title
            + "\" ID=\"" + Integer.toString(this.ep_id)
            + "\" index=\"" + Integer.toString(this.ep_index)
            + "\" summary=\"" + this.ep_summary
            + "\" duration=\"" + Integer.toString(this.ep_duration) + "\">";
        xml_val += "</Episode>";
        return xml_val;
    }
}
