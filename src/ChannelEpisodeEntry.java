public class ChannelEpisodeEntry {
    Episode episode;
    String showTitle;
    int SeasonNum;
    int EpisodeNum;

    public ChannelEpisodeEntry(Episode episode, String showTitle, int SeasonNum){
        this.episode = episode;
        this.showTitle = showTitle;
        this.EpisodeNum = episode.ep_index;
        this.SeasonNum = SeasonNum;
    }
}
