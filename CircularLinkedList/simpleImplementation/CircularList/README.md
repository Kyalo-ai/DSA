## Circular Linked List in Java

## 📖 Overview

This project implements a Circular Linked List using Java.A circular linked list is a data structure where the last node points back to the head node, forming a continuous loop.

# This implementation supports:

--1. Insertion at the beginning (InsertFirst)

---2Insertion at the end (InsertEnd)

---3 Deletion of the first node (deleteFirst)

--4 Displaying the list (Display)

## 🛠 Classes

# CNode

--- Represents a single node in the circular linked list.

Fields:

--- String data → stores the node’s value

--- CNode next → reference to the next node

Constructor:

CNode(String data) {
    this.data = data;
    this.next = null;
}

Circular

Manages the circular linked list operations.

## Fields:

CNode head → reference to the first node in the list

##  Constructor:

public Circular() {
    this.head = null;
}

## 🔧 Methods

## 1 InsertFirst(String data)

Inserts a new node at the beginning of the list.

Updates head to the new node.

Maintains the circular property.

## 2 InsertEnd(String data)

Inserts a new node at the end of the list.

Links the new node back to the head.

## 3 deleteFirst()

Deletes the first node in the list.

Updates head to the next node.

Handles edge cases (empty list or single-node list).

## 4 Display()

Traverses the list starting from head.

Prints all nodes until it loops back to head.

# Example output:

A--->B--->C--->Back to end

## ▶️ Example Usage

public class Main {
    public static void main(String[] args) {
        Circular list = new Circular();

        list.InsertEnd("A");
        list.InsertEnd("B");
        list.InsertEnd("C");

        list.Display(); 
        // Output: A--->B--->C--->Back to end

        list.InsertFirst("X");
        list.Display(); 
        // Output: X--->A--->B--->C--->Back to end

        list.deleteFirst();
        list.Display(); 
        // Output: A--->B--->C--->Back to end
    }
}


## PSUEDO CODE
START

CLASS CNode
    DECLARE data AS String
    DECLARE next AS CNode

    CONSTRUCTOR CNode(data)
        SET this.data = data
        SET this.next = NULL
    END CONSTRUCTOR
END CLASS


CLASS Circular

    DECLARE head AS CNode

    CONSTRUCTOR Circular()
        SET head = NULL
    END CONSTRUCTOR


    
    METHOD InsertFirst(data)
    

        CREATE newNode AS CNode(data)

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
        SET head = newNode

    END METHOD


    
    METHOD InsertEnd(data)


        CREATE newNode AS CNode(data)

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


    --
    METHOD deleteFirst()
    

        IF head IS NULL THEN
            RETURN
        END IF

        IF head.next IS head THEN
            SET head = NULL
            RETURN
        END IF

        SET temp = head

        WHILE temp.next IS NOT head DO
            SET temp = temp.next
        END WHILE

        SET temp.next = head.next
        SET head = head.next

    END METHOD


    
    METHOD Display()
    

        IF head IS NULL THEN
            RETURN
        END IF

        SET temp = head

        REPEAT
            PRINT temp.data + " ---> "
            SET temp = temp.next
        UNTIL temp IS head

        PRINT "Back to head"

    END METHOD

END CLASS

END

## 🔍 Notes

This is a singly linked circular list implementation.

Edge cases (empty list, single-node list) are handled in insertion and deletion methods.

The Display() method ensures traversal stops once the list loops back to the head.

## 📜 License

This project is open-source and free to use for educational purposes.