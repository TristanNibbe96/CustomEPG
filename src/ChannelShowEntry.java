import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
            seasonsToInclude.add(show.seasons.get(i).index);
            Collections.sort(seasonsToInclude);
        }
    }

    public int GetCurrentSeason(){
        try {
            Season currSeason = show.GetSeasonFromNumber(seasonsToInclude.get(currentSeasonIndex));
            return currSeason.index;
        }catch (IndexOutOfBoundsException e){
            System.out.print("s");
        }

        return -1;
    }

    public Episode GetNextEpisode(){
        Season currSeason = show.seasons.get(currentSeasonIndex);
        Episode episode = currSeason.episodes.get(currentEpisode);
        currentEpisode++;

        if(currSeason.Size() <= currentEpisode){
            currentSeasonIndex++;
            currentEpisode = 0;
        }

        if(currentSeasonIndex >= seasonsToInclude.size()){
            currentSeasonIndex = 0;
        }

        return episode;
    }
}
