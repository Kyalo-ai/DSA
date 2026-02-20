## 🔄 Round Robin Scheduling Using Circular Linked List
# 📌 Project Overview

--This project implements Round Robin CPU Scheduling using a Circular Singly Linked List in Java.

---Round Robin is a preemptive scheduling algorithm where each process is assigned a fixed time slice called a Time Quantum. If a process does not complete within the given time quantum, it is moved to the end of the ready queue.

This implementation simulates process execution using:

--- Circular Linked List

--- Burst Time tracking

--- Remaining Time updates

---- Process completion detection

# 🧠 Data Structure Used
Circular Singly Linked List

--- Each process is stored in a node (CNode) containing:

1.data → Process name

2.burstTime → Total CPU time required

3.remainingTime → Remaining CPU time

4.next → Pointer to next node (circular)

The last node points back to the head, forming a circular structure.

# ⚙️ Features Implemented

✔ Insert process at beginning
✔ Insert process at end
✔ Delete first process
✔ Delete specific process
✔ Display ready queue
✔ Run Round Robin scheduling
✔ Reset processes to original burst time
✔ Count total processes

# ▶ How Round Robin Works (Algorithm Explanation)

-- All processes are stored in a circular ready queue.

-- Each process runs for a fixed Time Quantum.

--If remaining time > quantum → reduce remaining time and move to next.

---- If remaining time = 0 → process completes.

Continue looping until all processes are completed.

## 📝 FULL PSEUDOCODE
## 🔹 Node Structure
START
CLASS CNode
    DECLARE data AS String
    DECLARE burstTime AS Integer
    DECLARE remainingTime AS Integer
    DECLARE next AS CNode
    CONSTRUCTOR CNode(data)
        SET this.data = data
        SET this.next = NULL
    END CONSTRUCTOR
    CONSTRUCTOR CNode(data, burstTime)
        SET this.data = data
        SET this.burstTime = burstTime
        SET this.remainingTime = burstTime
        SET this.next = NULL
    END CONSTRUCTOR
END CLASS
END
# 🔹 RoundRobin Class
START
CLASS RoundRobin
    DECLARE head AS CNode
    CONSTRUCTOR RoundRobin()
        SET head = NULL
    END CONSTRUCTOR
END
# 🔹 Insert Process at End
START
METHOD InsertEnd(data, burstTime)
    CREATE newNode AS CNode(data, burstTime)
    IF head IS NULL THEN
        SET newNode.next = newNode
        SET head = newNode
        RETURN
    END IF
    SET temp = head
    WHILE temp.next IS NOT head DO
        SET temp = temp.next
    END WHILE
    SET temp.next = newNode
    SET newNode.next = head
END METHOD
END
# 🔹 Run Round Robin Scheduling
START
METHOD runRoundRobin(timeQuantum)
    IF head IS NULL THEN
        PRINT "No processes to run"
        RETURN
    END IF
    SET current = head
    SET totalTime = 0
    SET completed = 0
    SET processCount = countProcesses()
    WHILE completed < processCount DO
        IF current.remainingTime > 0 THEN
            SET executeTime = MIN(timeQuantum, current.remainingTime)
            SET current.remainingTime =
                current.remainingTime - executeTime
            SET totalTime = totalTime + executeTime
            IF current.remainingTime = 0 THEN
                INCREMENT completed
            END IF
        END IF
        SET current = current.next
    END WHILE
    PRINT "All processes completed in " + totalTime
END METHOD
END
# 🔹 Count Processes
START
METHOD countProcesses()
    IF head IS NULL THEN
        RETURN 0
    END IF
    SET count = 0
    SET temp = head
    REPEAT
        INCREMENT count
        SET temp = temp.next
    UNTIL temp IS head
    RETURN count
END METHOD
END
# 🔹 Reset Processes
START
METHOD resetProcesses()
    IF head IS NULL THEN
        RETURN
    END IF
    SET temp = head
    REPEAT
        SET temp.remainingTime = temp.burstTime
        SET temp = temp.next
    UNTIL temp IS head
END METHOD

# 📈 Example Execution

If:

Process	Burst Time
P1	300ms
P2	200ms
P3	100ms

Time Quantum = 100ms

Execution Order:

P1 → P2 → P3 → P1 → P2 → P1

Until all complete.

## 🎯 Concepts Covered

1.Circular Linked List

2.CPU Scheduling Algorithms

3.Process Simulation

4.Time Quantum

5.Node Traversal

6.Dynamic Memory Structure