package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        RoundRobin scheduler = new RoundRobin();

        System.out.println("🚀 SIMPLE ROUND ROBIN SCHEDULER");
        System.out.println("================================");

        // Add processes
        scheduler.InsertEnd("P1", 10);  // Process 1 needs 10ms
        scheduler.InsertEnd("P2", 5);   // Process 2 needs 5ms
        scheduler.InsertEnd("P3", 8);   // Process 3 needs 8ms
        scheduler.InsertEnd("P4", 6);   // Process 4 needs 6ms

        // Display initial queue
        scheduler.Display();

        // Run Round Robin with 3ms time quantum
        scheduler.runRoundRobin(3);

        // Show final queue (all should be completed)
        scheduler.Display();
    }
}
