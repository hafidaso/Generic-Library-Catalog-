# Generic-Library-Catalog-
This project implements a generic library catalog system in Java, allowing users to manage a collection of library items through a command-line interface.

## Features

- Generic `LibraryItem` class that can use any comparable type for item IDs
- Generic `GenericCatalog` class to manage a collection of library items
- Command-line interface for adding, removing, and viewing items
- Input validation and error handling

## Code Structure

The project consists of three main classes:

1. **LibraryItem<T>**: Represents a generic library item with an ID, title, and author.
2. **GenericCatalog<E>**: Manages a collection of library items of type E, which must extend LibraryItem.
3. **LibraryCatalogApp**: Implements the main method and command-line interface for interacting with the catalog.
