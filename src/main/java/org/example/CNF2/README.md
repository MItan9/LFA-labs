# Chomsky Normal Form (CNF) for Formal Languages & Finite Automata

**Author:** Cretu Dumitru  
**Acknowledgements:** Vasile Drumea, Irina Cojuhari

## Overview

Chomsky Normal Form (CNF) is a pivotal concept in the theory of formal languages and automata. This report delves into the significance of CNF, its methods for normalizing grammars, and a practical implementation in Java.

## Objectives

- **Learn about Chomsky Normal Form (CNF)**: Understanding its relevance and applications in computational linguistics.
- **Familiarize with grammar normalization approaches**: Methods to transform any grammar into CNF.
- **Implement normalization method**: Develop and encapsulate a function in Java that converts input grammar into CNF, following a specified method signature.
- **Execute and test functionality**: Ensure the implementation operates correctly and efficiently.
- **Bonus 1**: Implement unit tests to validate all functionalities.
- **Bonus 2**: Extend the functionality to accept dynamic grammars beyond a fixed set.

## Implementation in Java

The Java class `CNF2` encapsulates methods to convert a Context-Free Grammar (CFG) to Chomsky Normal Form. Below is the class outline and descriptions of key functions involved in the conversion.

# Chomsky Normal Form (CNF)

## Definition

Chomsky Normal Form is a standardized way of structuring the production rules of a context-free grammar. The rules in CNF adhere to specific conditions:

1. **Binary Productions**: Each production rule must have either two non-terminal symbols or a single terminal symbol on its right-hand side.
    - Example: `A -> BC` or `A -> a`, where `A`, `B`, and `C` are non-terminals, and `a` is a terminal.
2. **No ε-productions**: Rules producing an empty string (ε) are not allowed, except when the start symbol produces ε directly.
3. **No Unit Productions**: Rules that consist of a single non-terminal symbol transferring to another non-terminal (e.g., `A -> B`) are prohibited.

## Importance of CNF

Chomsky Normal Form is crucial for:

- **Parsing Simplification**: Algorithms like the CYK algorithm require grammars in CNF to function effectively.
- **Theoretical Analysis**: CNF simplifies the analysis of context-free grammars in computational theory.

## Conversion to Chomsky Normal Form

Converting an arbitrary context-free grammar into CNF involves several steps:

1. **Start Symbol Adjustment**: Introduce a new start symbol to ensure the original start symbol does not appear on the right-hand side of any rule.
2. **Elimination of ε-productions**: Remove productions that yield an empty string by identifying nullable variables and adjusting rules without generating ε directly.
3. **Elimination of Unit Productions**: Replace unit productions with the productions of the non-terminals they point to.
4. **Handling Terminal Productions**: Introduce new non-terminal symbols for terminals that appear alongside non-terminals in production rules.
    - For example, transform `A -> BbC` into `A -> BX` and `X -> b`.
5. **Conversion to Binary Productions**: Decompose longer productions into binary productions.
    - Convert `A -> BCD` into `A -> BE` and `E -> CD`.

This structured approach not only aids in theoretical studies but also enhances the efficiency of parsing algorithms and other computational operations involving context-free grammars.


## Java Class Overview
The CNF2 class contains methods to manage and convert grammars stored as strings into CNF. The class utilizes a Map to link variables to their production rules and processes the grammar through several stages to achieve CNF.

### Main Method
The main method initializes an instance of CNF2 and sets a grammar as input. It then calls the conversion method to transform the CFG into CNF.
```java
public static void main(String[] args) {
    CNF2 cnf2 = new CNF2();
    String input = "S → aB | A\n" +
                   "A → bAa|aS|a\n" +
                   "B → AbB|BS|a|e\n" +
                   "C → BA\n" +
                   "D → a";

    cnf2.setInputandLineCount(input, 3);  // Sets the input and the number of productions
    cnf2.convertCFGtoCNF();
}
```

### Conversion Method
The `convertCFGtoCNF` method orchestrates the conversion process by calling other functions to handle each stage of the transformation.

- **Insert New Start Symbol**

This method introduces a new start symbol to ensure the original start symbol is only used on the right side of the first production.

```java
public void insertNewStartSymbol() {
    String newStart = "S0";
    ArrayList<String> newProduction = new ArrayList<>();
    newProduction.add("S");
    mapVariableProduction.put(newStart, newProduction);
}
```

- **Remove ε-Productions**

The `removeEProductions` method identifies nullable variables and adjusts the grammar to eliminate ε-productions.

```java
public void removeEProductions() {
        ...
        if (productionRow.contains("e")) {
        if (productionRow.size() > 1) {
        productionRow.remove("e");
        epselonFound = entry.getKey().toString();
        ...
}
```

- **Remove Unit Productions**

The `removeUnitProductions` method replaces unit productions with the productions of the non-terminals they point to.

```java
public void removeUnitProductions() {
    ...
    if (productionRow.size() == 1 && productionRow.get(0).length() == 1 && Character.isUpperCase(productionRow.get(0).charAt(0))) {
        ...
        productionRow.remove(0);
        ...
    }
}
```

- **Convert to Binary Productions**

The `convertToBinaryProductions` method decomposes longer productions into binary productions.

```java
public void convertToBinaryProductions() {
    ...
    if (productionRow.size() > 2) {
        ...
        String newVariable = productionRow.get(0) + productionRow.get(1);
        ...
        productionRow.remove(0);
        productionRow.remove(0);
        productionRow.add(0, newVariable);
        ...
    }
}
```

## Unit Testing
Unit testing is a software testing method where individual components of a software application, known as "units," are tested independently to verify that each part performs as expected. A unit typically refers to the smallest testable part of an application, which could be a function, method, procedure, module, or object.

In unit testing, developers write tests for each non-trivial function or method to ensure that the code behaves as expected under various conditions. This practice helps in identifying problems early in the development cycle and enhances code quality.

### Purpose of the Tests
The provided Java test class CNF2Test is designed to validate the functionality of the CNF2 class, which is responsible for converting a context-free grammar (CFG) into Chomsky Normal Form (CNF). The test class uses JUnit 5, a popular framework for writing and running repeatable tests in Java.

### Key Annotations and Methods in JUnit 5
- **@BeforeEach:** This annotation is used on a method that runs before each test. It is commonly used for preparing the test environment (e.g., initializing objects).
- **@Test:** This annotation denotes that a method is a test method.
- **assertXXX():** These methods from org.junit.jupiter.api.Assertions are used to assert conditions in the test. If the condition fails, the test fails.

### Test for Input Conversion to Map
The test method `testInputConversionToMap` validates the conversion of input grammar into a map structure where each variable is mapped to its corresponding production rules.

```java
@Test
void testInputConversionToMap() {
    cnf2.convertStringtoMap();
    assertFalse(cnf2.getMapVariableProduction().isEmpty(), "Map should not be empty after parsing input.");
    assertTrue(cnf2.getMapVariableProduction().containsKey("S"), "Map should contain key 'S'.");
    assertTrue(cnf2.getMapVariableProduction().get("S").contains("aB"), "Production of 'S' should contain 'aB'.");
}
```

### Test for Removing Epsilon Productions
The test method `testRemoveEProductions` verifies the removal of ε-productions from the grammar.

```java
@Test
void testRemoveEpselon() {
    cnf2.eliminateEpsilon();
    assertTrue(cnf2.getMapVariableProduction().values().stream()
                        .noneMatch(prods -> prods.contains("e")),
                "Epsilon should be removed from all productions.");
}

```

### Test for Removing Single Variable Productions
The test method `testRemoveUnitProductions` checks the elimination of unit productions from the grammar.

```java
@Test
void testRemoveSingleVariable() {
        cnf2.eliminateSingleVariable();
        cnf2.getMapVariableProduction().values().forEach(prods ->
        prods.forEach(prod ->
        assertFalse(cnf2.getMapVariableProduction().containsKey(prod) && prod.length() == 1,
        "No production should be a single non-terminal pointing to others.")
        )
        );
        }

```
## Conclusion
The CNF2 class provides a robust implementation for converting context-free grammars into Chomsky Normal Form. By following a structured approach and leveraging Java's object-oriented features, the class offers a practical solution for handling grammars in computational linguistics and automata theory. The unit tests further validate the correctness and reliability of the conversion methods, ensuring that the class functions as intended across various scenarios.























































































