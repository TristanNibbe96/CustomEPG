import java.util.ArrayList;

public class ChannelShowEntry {
    int currentSeasonIndex;
    int currentEpisode;
    TVShow show;
    ArrayList<Integer> seasonsToInclude;

    public ChannelShowEntry(TVShow show){
        this.show = show;
        currentEpisode = 0;
        currentSeasonIndex = 0;
        seasonsToInclude = new ArrayList<>();

        for (int i = 0; i < show.seasons.size(); i++){
            seasonsToInclude.add(i);
        }
    }

    public int GetCurrentSeason(){
        Season currSeason = show.seasons.get(seasonsToInclude.get(currentSeasonIndex));

        return currSeason.index;
    }

    public Episode GetNextEpisode(){
        Season currSeason = show.seasons.get(currentSeasonIndex);
        Episode episode = currSeason.episodes.get(currentEpisode);
        currentEpisode++;

        if(currSeason.Size() <= currentEpisode){
            currentSeasonIndex++;
            currentEpisode = 0;
        }

        if(currentSeasonIndex > seasonsToInclude.size()){
            currentSeasonIndex = 0;
        }

        return episode;
    }
}
