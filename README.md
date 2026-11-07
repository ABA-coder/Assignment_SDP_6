# Assignment 6: Visitor Pattern

I chose museum storage as the topic for this assignment. My program has two types of items: a painting and a ceramic artifact. I use two visitors to do different jobs with those items: calculate transport cost and find preservation tasks.

## How the pattern is used

- `MuseumItem` is the element interface. It declares `accept(MuseumVisitor visitor)`.
- `Painting` and `CeramicArtifact` are the concrete elements. Each `accept` method calls its own `visit...` method and passes `this`.
- `MuseumVisitor` declares one visit method for each concrete element.
- `TransportCostVisitor` and `PreservationVisitor` implement both visit methods, but each visitor has a different job.
- In `Main`, I keep the items in a `List<MuseumItem>` and call `accept` on each item.

This is double dispatch: Java first chooses the concrete item's `accept` method, then the correct visitor method is called for that item. I do not use `instanceof` to check item types.

## How to run

You can run the project with JDK 8 or newer. In PowerShell, use these commands from the project folder:

```powershell
New-Item -ItemType Directory -Force build | Out-Null
javac -d build src/*.java
java -cp build Main
```

The output is:

```text
Transport cost: 200.00
Adjust humidity for Mountain Dawn
Inspect crack in Ancient Vase
```

I used simple example prices. The painting costs `100 + (100 × 80 ÷ 10,000) × 50 = 140` to transport. The ceramic artifact costs `30 + 2.5 × 12 = 60`. Together they cost `200`.

## Clean Code choices

1. **Meaningful names:** the element classes describe museum items, and the visitor classes describe what they do. This makes their roles easy to find.
2. **Single Responsibility:** `TransportCostVisitor` only calculates cost. `PreservationVisitor` only collects preservation tasks. I kept these rules out of the item classes.
3. **No type-checking chains:** the elements call the matching visit methods directly, so I do not need `instanceof` or casts.
4. **Encapsulation:** item fields are private and final. The cost visitor reads painting dimensions and ceramic weight; the preservation visitor reads humidity and crack status. Getters provide only those values, so visitors cannot change the items.
5. **Easy to add a new operation:** I can add another class implementing `MuseumVisitor` and call it in the loop without editing `Painting` or `CeramicArtifact`.
6. **Small methods and named constants:** each visit method handles one item type. I gave the price rates and humidity limit names so the numbers are understandable.

Visitor fits this example because the item types are stable, while I may want to add more operations later. The trade-off is that adding a new item type would require a new method in `MuseumVisitor` and changes in both existing visitors.
