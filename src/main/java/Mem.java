public class Mem {

    public String link;
    public String image;
    public Integer data_score;

    public Mem(String link, String data_score, String image){
        this.link = link;
        this.data_score = Integer.valueOf(data_score);
        this.image = "https://old.reddit.com/"+image;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public Integer getData_score() {
        return data_score;
    }
}
