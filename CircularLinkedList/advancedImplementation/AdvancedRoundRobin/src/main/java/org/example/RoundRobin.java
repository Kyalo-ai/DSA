package org.example;
class CNode {
    String data;
    int burstTime;
    int remainingTime;
    CNode next;

    CNode(String data) {
        this.data = data;
        this.next = null;
    }


    CNode(String data, int burstTime) {
        this.data = data;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.next = null;
    }
}

public class RoundRobin {
    CNode head;

    public RoundRobin() {
        this.head = null;
    }


    public void InsertFirst(String data) {
        CNode newNode = new CNode(data);
        if (head == null) {
            newNode.next = newNode;
            head = newNode;
            return;
        }
        CNode temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = head;
        head = newNode;
    }

    public void InsertEnd(String data, int burstTime) {
        CNode newNode = new CNode(data, burstTime);
        if (head == null) {
            newNode.next = newNode;
            head = newNode;
            return;
        }
        CNode temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = head;
        System.out.println("✅ Added: " + data + " (Burst: " + burstTime + "ms)");
    }

    public void deleteFirst() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (head.next == head) {
            System.out.println("❌ Removed: " + head.data);
            head = null;
            return;
        }

        CNode temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        System.out.println("❌ Removed: " + head.data);
        temp.next = head.next;
        head = head.next;
    }


    public void deleteNode(String data) {
        if (head == null) return;

        CNode curr = head;
        CNode prev = null;


        do {
            if (curr.data.equals(data)) break;
            prev = curr;
            curr = curr.next;
        } while (curr != head);


        if (curr.data.equals(data)) {

            if (curr.next == head && prev == null) {
                head = null;
            }

            else if (curr == head) {
                CNode last = head;
                while (last.next != head) {
                    last = last.next;
                }
                head = head.next;
                last.next = head;
            }

            else {
                prev.next = curr.next;
            }
            System.out.println("❌ Removed: " + data);
        }
    }

    public void Display() {
        if (head == null) {
            System.out.println("📭 No processes in queue");
            return;
        }
        System.out.print("\n🔄 Ready Queue: ");
        CNode temp = head;
        do {
            if (temp.remainingTime > 0) {
                System.out.print(temp.data + "(" + temp.remainingTime + "ms) ");
            } else {
                System.out.print(temp.data + " ");
            }
            temp = temp.next;
        } while (temp != head);
        System.out.println("→ back to " + head.data);
    }


    public void runRoundRobin(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to run");
            return;
        }

        System.out.println("\n⚡ ROUND ROBIN SCHEDULING (Quantum: " + timeQuantum + "ms)");
        System.out.println("=================================");

        CNode current = head;
        int totalTime = 0;
        int processCount = countProcesses();
        int completed = 0;

        while (completed < processCount) {

            if (current.remainingTime > 0) {
                int executeTime = Math.min(timeQuantum, current.remainingTime);
                current.remainingTime -= executeTime;
                totalTime += executeTime;

                System.out.println("▶ " + current.data + " ran for " + executeTime +
                        "ms [Total: " + totalTime + "ms]");


                if (current.remainingTime == 0) {
                    completed++;
                    System.out.println("  ✓ " + current.data + " COMPLETED!");
                } else {
                    System.out.println("  ⏳ " + current.remainingTime + "ms remaining");
                }
            }

            current = current.next;
        }

        System.out.println("=================================");
        System.out.println("🎉 All processes completed in " + totalTime + "ms");
    }


    private int countProcesses() {
        if (head == null) return 0;

        int count = 0;
        CNode temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }


    public void resetProcesses() {
        if (head == null) return;

        CNode temp = head;
        do {
            temp.remainingTime = temp.burstTime;
            temp = temp.next;
        } while (temp != head);
        System.out.println("🔄 Processes reset to initial burst times");
    }
}
