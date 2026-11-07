public class CeramicArtifact implements MuseumItem {
    private final String title;
    private final double weightKg;
    private final boolean hasCrack;

    public CeramicArtifact(String title, double weightKg, boolean hasCrack) {
        this.title = title;
        this.weightKg = weightKg;
        this.hasCrack = hasCrack;
    }

    public String getTitle() {
        return title;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public boolean hasCrack() {
        return hasCrack;
    }

    @Override
    public void accept(MuseumVisitor visitor) {
        visitor.visitCeramicArtifact(this);
    }
}
