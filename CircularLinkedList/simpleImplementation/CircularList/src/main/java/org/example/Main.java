package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
Circular list = new Circular();
list.InsertFirst("HELLO");
list.InsertEnd("WELCOME");
list.Display();
list.InsertEnd("TO MY PAGE");
list.deleteFirst();
list.Display();

    }
}
