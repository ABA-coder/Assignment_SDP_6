# Visitor Pattern: Museum Storage

This small Java program uses the Visitor pattern for two separate operations on museum items: estimating transport cost and checking preservation needs. The topic should be confirmed as unique within the group before submission.

## Requirements map

| Assignment requirement | Where it is implemented |
| --- | --- |
| Element interface with `accept(Visitor)` | `MuseumItem.java` |
| Two concrete elements with double dispatch | `Painting.java`, `CeramicArtifact.java` |
| Visitor interface with one method per element | `MuseumVisitor.java` |
| Two visitors, each handling both elements | `TransportCostVisitor.java`, `PreservationVisitor.java` |
| Client traverses an object collection | The `List<MuseumItem>` loop in `Main.java` |

## Run

Use JDK 17 or newer. From the repository root in PowerShell:

```powershell
New-Item -ItemType Directory -Force build | Out-Null
javac -d build src/*.java
java -cp build Main
```

To run the small dependency-free checks:

```powershell
javac -cp build -d build tests/VisitorTest.java
java -cp build VisitorTest
```

Expected output:

```text
Transport cost: 200.00
Adjust humidity for Mountain Dawn
Inspect crack in Ancient Vase
```

The sample prices are illustrative currency units. Painting transport costs `100 + area in m² × 50`: `100 + (100 × 80 ÷ 10,000) × 50 = 140`. Ceramic transport costs `30 + weight in kg × 12`: `30 + 2.5 × 12 = 60`. Total: `200`.

## Clean Code decisions

1. **Meaningful names:** `Painting` and `CeramicArtifact` name the elements; `TransportCostVisitor` and `PreservationVisitor` name the operations. Responsibilities are visible in the class names.
2. **Single Responsibility:** each visitor performs one operation across every element type. Transport pricing and preservation rules are separate.
3. **No type-checking chains:** there is no `instanceof` or class-name comparison. Each element selects its matching `visit...` method in `accept`.
4. **Encapsulation:** element fields are private and immutable. Visitors read only the values needed for their operation through getters, so no visitor can change element state.
5. **Open for new operations:** a third visitor can implement `MuseumVisitor` and be used with the existing elements without changing those element classes.
6. **Small methods and named constants:** each `visit...` handles one element. Pricing rates and the humidity threshold have descriptive names instead of unexplained numbers.

The trade-off is that adding a **new element type** would require a new method in `MuseumVisitor` and implementations in every concrete visitor. Visitor is most suitable when the element types are relatively stable and new operations are more common.

See `DEFENSE.md` for a short explanation of double dispatch and practice questions.
