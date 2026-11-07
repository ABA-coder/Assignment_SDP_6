public class Painting implements MuseumItem {
    private final String title;
    private final int widthCm;
    private final int heightCm;
    private final int humidityPercent;

    public Painting(String title, int widthCm, int heightCm, int humidityPercent) {
        this.title = title;
        this.widthCm = widthCm;
        this.heightCm = heightCm;
        this.humidityPercent = humidityPercent;
    }

    public String getTitle() {
        return title;
    }

    public int getWidthCm() {
        return widthCm;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public int getHumidityPercent() {
        return humidityPercent;
    }

    @Override
    public void accept(MuseumVisitor visitor) {
        visitor.visitPainting(this);
    }
}
