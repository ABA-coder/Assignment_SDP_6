public class TransportCostVisitor implements MuseumVisitor {
    private static final int PAINTING_BASE_COST = 100;
    private static final int PAINTING_COST_PER_SQUARE_METER = 50;
    private static final int CERAMIC_BASE_COST = 30;
    private static final int CERAMIC_COST_PER_KG = 12;

    private double totalCost;

    @Override
    public void visitPainting(Painting painting) {
        double areaSquareMeters = painting.getWidthCm() * painting.getHeightCm() / 10_000.0;
        totalCost += PAINTING_BASE_COST + areaSquareMeters * PAINTING_COST_PER_SQUARE_METER;
    }

    @Override
    public void visitCeramicArtifact(CeramicArtifact artifact) {
        totalCost += CERAMIC_BASE_COST + artifact.getWeightKg() * CERAMIC_COST_PER_KG;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
