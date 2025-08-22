# Copilot Instructions for CatalogoLivros

## Project Overview
- This is a Java application for managing a book catalog using a Binary Search Tree (BST) structure.
- Main entry point: `src/main/java/org/unifan/catalogolivros/CatalogoLivros.java`.
- Core domain classes: `Livro` (Book), `No` (Node), and `BST` (Binary Search Tree), all under `src/main/java/org/unifan/catalogolivros/catalogo/`.

## Architecture & Data Flow
- The `CatalogoLivros` class provides the main logic and user interface for interacting with the catalog.
- The `BST` class implements the binary search tree logic for storing and searching `Livro` objects.
- Each `No` represents a node in the BST and holds a `Livro` instance.
- Data flows from user input in `CatalogoLivros` to operations on the `BST` and its nodes.

## Build & Run
- This is a Maven project (`pom.xml` at root). Use standard Maven commands:
  - Build: `mvn clean package`
  - Run: `mvn exec:java -Dexec.mainClass=org.unifan.catalogolivros.CatalogoLivros`
- Compiled classes are output to `target/classes`.

## Project Conventions
- All main source code is under `src/main/java/org/unifan/catalogolivros/`.
- Domain logic is grouped in the `catalogo` subpackage.
- Class and file names are in Portuguese, reflecting the domain language.
- No custom annotations or frameworks are used; the project is plain Java.

## Testing
- No test files or frameworks detected. If adding tests, follow Maven/Java conventions and place them under `src/test/java/`.

## Integration & Dependencies
- No external dependencies beyond the JDK and Maven standard plugins.
- No network, database, or external service integration.

## Examples
- To add a new domain entity, create a class in `catalogo/` and update `BST`/`CatalogoLivros` as needed.
- To change the data structure, replace or extend `BST` and update usages in `CatalogoLivros`.

---
For questions about project structure or conventions, review the `pom.xml` and the main classes in `catalogo/`.
