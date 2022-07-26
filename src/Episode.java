public class Episode {
    int ep_id;
    String ep_title;
    int ep_duration;
    int ep_index;
    String ep_summary;
    int parent_ID;

    public Episode(int ep_id, String ep_title, int duration, int index, String summary, int parent_ID) {
        this.ep_id = ep_id;
        this.ep_title = ep_title;
        this.ep_duration = duration;
        this.ep_index = index;
        this.ep_summary = summary;
        this.parent_ID = parent_ID;
    }

    public String ToString() {
        String string_val = "";
        string_val += this.ep_id + " " + this.ep_title;
        return string_val;
    }

    public String ToXML() {
        String xml_val = "<Episode title=\"" + this.ep_title
            + "\" ID=\"" + this.ep_id
            + "\" index=\"" + this.ep_index
            + "\" summary=\"" + this.ep_summary
            + "\" duration=\"" + this.ep_duration + "\">";
        xml_val += "</Episode>";
        return xml_val;
    }
}
