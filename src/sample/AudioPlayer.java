package sample;

public class AudioPlayer extends Product implements MultimediaControl {

  private String audioSpecification;
  private String mediaType;

  AudioPlayer(String name, String manufacturer, String audioSpecification) {
      super(name, audioSpecification, manufacturer);
      setMediaType(this.mediaType);

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
      return super.toString() + "\nAudio Spec: " + this.audioSpecification + "\nType: " + this.mediaType;
  }
}
