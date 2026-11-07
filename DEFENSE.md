# Visitor Pattern Defense Notes

## Explain the idea in 30 seconds

The museum storage has two element types: `Painting` and `CeramicArtifact`. I want to add operations without putting transport pricing and preservation rules inside those elements. Each element has `accept(MuseumVisitor)`. A visitor has a separate method for each element type. `TransportCostVisitor` calculates cost, and `PreservationVisitor` builds a task list.

## Show double dispatch on paper

```java
MuseumItem item = new Painting("Mountain Dawn", 100, 80, 68);
MuseumVisitor visitor = new TransportCostVisitor();
item.accept(visitor);
```

1. At runtime, Java selects `Painting.accept` from the actual object type, even though the variable is declared as `MuseumItem`.
2. Inside `Painting.accept`, the call is `visitor.visitPainting(this)`. Java selects the visitor implementation from the actual visitor object type. The `this` value is statically known there as `Painting`, so the matching visit method is chosen.
3. `TransportCostVisitor.visitPainting` calculates `100 + widthCm × heightCm ÷ 10,000 × 50`.

The two selections are why this is called double dispatch. No `instanceof` is needed.

## Likely defense questions

**Why use Visitor here?** The set of element types is stable, while operations such as transport pricing, preservation, and future reports can be added separately.

**What happens if a third visitor is added?** Create a class implementing both methods of `MuseumVisitor`, then call `item.accept(newVisitor)` in the client loop. Existing element classes do not change.

**What happens if a third element is added?** Add `visitNewElement` to `MuseumVisitor`, implement it in every visitor, and make the new element call it from `accept`. This is Visitor's main trade-off.

**Why are there getters?** Visitors need selected element data to calculate results. Private final fields preserve encapsulation; getters expose values without allowing mutation.

**Where is the object structure?** `Main` stores both objects in `List<MuseumItem>` and loops over it, calling `accept` for each visitor.

**Why does each visitor have two visit methods?** The interface requires one method per concrete element. Each visitor supplies its own algorithm for both types.

**How is the result checked?** In the sample, painting transport costs `140`, ceramic transport costs `60`, and total transport cost is `200`; humidity and damage each produce one preservation task.
