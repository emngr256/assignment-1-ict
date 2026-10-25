# KU Campus E-Commerce Demo

A Java console application for Assignment 1: Design and Implementation of a Console-Based E-Commerce System. It demonstrates encapsulation, inheritance, associations, and `ArrayList` collections.

## Requirements

- JDK 17 or newer
- No external libraries

## Run in Windows PowerShell

From this directory:

```powershell
$files = Get-ChildItem src/main/java -Recurse -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d out $files
java -cp out kz.ku.ict.ecommerce.Main
```

In macOS/Linux shells, compile with `javac -encoding UTF-8 -d out src/main/java/kz/ku/ict/ecommerce/*.java`, then run `java -cp out kz.ku.ict.ecommerce.Main`.

## Demo data

At startup the program creates two customers, one employee and three products. Data and orders are kept in memory and are reset when the program exits. The role menu is a teaching/demo switch, not an authentication system.

## Menus

- Customer menu: browse products, create a multi-item order, and print personal receipts.
- Employee menu: add books/electronics/clothing, register customers and employees, list users and inspect all orders.
- Main menu: list users/products and exit.

The order operation checks aggregate quantities against available stock before changing inventory and captures each item's unit price when it enters the cart. Product IDs, order IDs, and user IDs are assigned by the application.

## Project files

- `src/main/java/kz/ku/ict/ecommerce/` - application source code
- `docs/class-diagram.puml` - editable PlantUML UML source

## Design overview

`Customer` and `Employee` extend `User`. `Book`, `Electronics`, and `Clothing` extend `Product`. `EcommerceSystem` manages users, products, and orders. An `Order` contains immutable `OrderLine` snapshots and is associated with one `Customer`.

