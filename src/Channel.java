import java.util.ArrayList;
import java.util.Random;

public class Channel {
    ArrayList<ChannelShowEntry> shows;
    ArrayList<ChannelEpisodeEntry> schedule;
    String ChannelName;

    boolean playEpisodesInOrder;
    int maxConsecutiveEpisode;

    public Channel(String channelName){
        this.shows = new ArrayList<>();
        this.schedule = new ArrayList<>();
        this.ChannelName = channelName;
        this.maxConsecutiveEpisode = 2;
        this.playEpisodesInOrder = true;
    }

    public ChannelEpisodeEntry CreateEpisodeEntry(int showIndex){
        int SeasonNum = shows.get(showIndex).GetCurrentSeason();
        Episode nextEpisode = shows.get(showIndex).GetNextEpisode();
        String currentShowTitle = shows.get(showIndex).show.show_title;

        return new ChannelEpisodeEntry(nextEpisode, currentShowTitle, SeasonNum);
    }

    public void GenerateSchedule(){
        int currentShow = GetRandomShow(-1);
        int previousShow;
        int consecutiveEpisodes = 1;

        for(int i = 0; i < 50; i++){
            schedule.add(CreateEpisodeEntry(currentShow));

            previousShow = currentShow;

            if(consecutiveEpisodes >= maxConsecutiveEpisode){
                currentShow = GetRandomShow(currentShow);
            }else {
                currentShow = GetRandomShow(-1);
            }

            if(currentShow == previousShow){
                consecutiveEpisodes++;
            }else {
                consecutiveEpisodes = 1;
            }
        }
    }

    public String PrintSchedule(){
        StringBuilder stringVal = new StringBuilder();

        for(ChannelEpisodeEntry episodeEntry : schedule){
            stringVal.append(episodeEntry.showTitle).append(" ");
            stringVal.append("S").append(episodeEntry.SeasonNum);
            stringVal.append("E").append(episodeEntry.EpisodeNum).append(" | ");
        }

        return stringVal.toString();
    }

    public void AddShow(ChannelShowEntry newShow){
        this.shows.add(newShow);
    }

    public int GetRandomShow(int indexToAvoid){
        Random rand = new Random();
        rand.setSeed(System.nanoTime());
        int randomIndex = indexToAvoid;

        while(randomIndex == indexToAvoid) {
            randomIndex = rand.nextInt(shows.size());
        }

        return randomIndex;
    }

    public String PrintChannel(){

        String stringVal = "\n----------\n";
        stringVal += this.ChannelName + "| ";
        stringVal += PrintSchedule();
        stringVal += "\n----------";

        return stringVal;
    }
}
