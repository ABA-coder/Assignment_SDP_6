import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreservationVisitor implements MuseumVisitor {
    private static final int MAX_PAINTING_HUMIDITY_PERCENT = 60;

    private final List<String> tasks = new ArrayList<>();

    @Override
    public void visitPainting(Painting painting) {
        if (painting.getHumidityPercent() > MAX_PAINTING_HUMIDITY_PERCENT) {
            tasks.add("Adjust humidity for " + painting.getTitle());
        }
    }

    @Override
    public void visitCeramicArtifact(CeramicArtifact artifact) {
        if (artifact.hasCrack()) {
            tasks.add("Inspect crack in " + artifact.getTitle());
        }
    }

    public List<String> getTasks() {
        return Collections.unmodifiableList(new ArrayList<>(tasks));
    }
}
