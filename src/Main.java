import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        List<MuseumItem> items = Arrays.asList(
                new Painting("Mountain Dawn", 100, 80, 68),
                new CeramicArtifact("Ancient Vase", 2.5, true)
        );

        TransportCostVisitor costVisitor = new TransportCostVisitor();
        PreservationVisitor preservationVisitor = new PreservationVisitor();

        for (MuseumItem item : items) {
            item.accept(costVisitor);
            item.accept(preservationVisitor);
        }

        System.out.printf(Locale.US, "Transport cost: %.2f%n", costVisitor.getTotalCost());
        for (String task : preservationVisitor.getTasks()) {
            System.out.println(task);
        }
    }
}
