import java.util.List;

public class VisitorTest {
    public static void main(String[] args) {
        testTransportCost();
        testPreservationTasks();
        System.out.println("All tests passed");
    }

    private static void testTransportCost() {
        TransportCostVisitor visitor = new TransportCostVisitor();
        new Painting("Test painting", 100, 80, 68).accept(visitor);
        new CeramicArtifact("Test vase", 2.5, true).accept(visitor);

        if (Math.abs(visitor.getTotalCost() - 200.0) > 0.0001) {
            throw new AssertionError("Expected transport cost of 200.00");
        }
    }

    private static void testPreservationTasks() {
        PreservationVisitor visitor = new PreservationVisitor();
        new Painting("Wet painting", 100, 80, 68).accept(visitor);
        new CeramicArtifact("Cracked vase", 2.5, true).accept(visitor);
        new Painting("Dry painting", 50, 50, 50).accept(visitor);
        new CeramicArtifact("Whole vase", 1.0, false).accept(visitor);

        List<String> expected = List.of(
                "Adjust humidity for Wet painting",
                "Inspect crack in Cracked vase"
        );
        if (!visitor.getTasks().equals(expected)) {
            throw new AssertionError("Unexpected preservation tasks: " + visitor.getTasks());
        }
    }
}
