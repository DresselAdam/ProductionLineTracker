package sample;

public class AudioPlayer extends Product implements MultimediaControl {

    private String audioSpecification;
    private String mediaType;

    AudioPlayer(String name, String manufacturer,String audioSpecification, String mediaType) {
        super(name,itemType.AUDIO.toString(), manufacturer);
        this.audioSpecification = audioSpecification;
        this.mediaType = mediaType;

    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public void play() {
        System.out.println("Playing");
    }

    @Override
    public void stop() {
        System.out.println("Stopping");
    }

    @Override
    public void previous() {
        System.out.println("Previous");
    }

    @Override
    public void next() {
        System.out.println("Next");
    }

    public String toString() {
        return super.toString() + "\nSupported Audio Formats: " + this.audioSpecification + "\nSupported Playlist Formats: " + this.mediaType;
    }
}

