import java.util.ArrayList;
import java.util.Random;

public class Channel {
    ArrayList<ChannelShowEntry> shows;
    String ChannelName;

    boolean playEpisodesInOrder;
    int maxConsecutiveEpisode;

    public Channel(String channelName){
        this.shows = new ArrayList<>();
        this.ChannelName = channelName;
        this.maxConsecutiveEpisode = 2;
        this.playEpisodesInOrder = true;
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
        StringBuilder stringVal = new StringBuilder("\n----------\n");
        stringVal.append(this.ChannelName).append("|");

        int currentShow = GetRandomShow(-1);
        int previousShow;
        int consecutiveEpisodes = 1;

        for(int i = 0; i < 50; i++){
            String currTitle = shows.get(currentShow).show.show_title;
            stringVal.append(" " + currTitle + " | ");
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

        stringVal.append("\n----------");

        return stringVal.toString();
    }
}
