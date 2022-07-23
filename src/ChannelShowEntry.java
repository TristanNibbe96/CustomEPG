import java.util.ArrayList;

public class ChannelShowEntry {
    int currentSeason;
    int currentEpisode;
    TVShow show;
    ArrayList<Integer> seasonsToInclude;

    public ChannelShowEntry(TVShow show){
        this.show = show;
        currentEpisode = 1;
        currentSeason = 1;
        seasonsToInclude = new ArrayList<>();

        for (int i = 0; i < show.seasons.size(); i++){
            seasonsToInclude.add(i);
        }
    }
}
