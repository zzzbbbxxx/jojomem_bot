public class Mem {

    public String link;
    public String image;
    public Integer data_score;
    public String timestamp;

    public Mem(String link, String timestamp, String data_score, String image){
        this.link = link;
        this.timestamp = timestamp;
        this.data_score = Integer.valueOf(data_score);
        this.image = "https://old.reddit.com/"+image;
    }

    public String getTimestamp() { return timestamp; }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public Integer getDataScore() {
        return data_score;
    }
}
