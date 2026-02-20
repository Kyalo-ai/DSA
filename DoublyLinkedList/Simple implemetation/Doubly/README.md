# 🌐 DoublyBrowser

> A simple **browser history simulator** built in Java using a **Doubly Linked List**.  
> Demonstrates how real browsers implement **Back / Forward navigation** internally.

---

## ✨ Features

- 🔗 Visit new pages
- ⬅️ Navigate **back**
- ➡️ Navigate **forward**
- 📄 Show current page
- 🧾 Print full browsing history
- 🧠 Clear forward history when a new page is visited (real browser behavior)

---

## 🧱 Data Structure

This project uses a **Doubly Linked List** where each node represents a web page.

Each `Page` stores:

- `url` — page address  
- `title` — page title  
- `prev` — link to previous page  
- `next` — link to next page  

```java
static class Page {
    String url;
    String title;
    Page prev;
    Page next;
}
```


## 🧩 Project Structure

```
DoublyBrowser
├── Page (static inner class)
└── Browser logic
```

### Core Field

```java
private Page currentPage;
```

Tracks the page currently open.

---

## ⚙️ Methods

| Method | Description |
|---|---|
| `visit(url, title)` | Opens a new page and clears forward history |
| `back()` | Moves to the previous page |
| `forward()` | Moves to the next page |
| `showCurrent()` | Displays the current page |
| `showHistory()` | Prints complete history and marks current page |

---

## ▶️ Example Usage
---
```java
DoublyBrowser browser = new DoublyBrowser();
java.util.Scanner scanner = new java.util.Scanner(System.in);

System.out.println("=== Simple Browser ===");

while (true) {
    System.out.println("\nOptions:");
    System.out.println("1. Visit new page");
    System.out.println("2. Go back");
    System.out.println("3. Go forward");
    System.out.println("4. Show current");
    System.out.println("5. Show history");
    System.out.println("6. Exit");
    System.out.print("Choose: ");

    int choice = scanner.nextInt();
    scanner.nextLine(); // consume newline

    switch (choice) {
        case 1:
            System.out.print("URL: ");
            String url = scanner.nextLine();
            System.out.print("Title: ");
            String title = scanner.nextLine();
            browser.visit(url, title);
            break;

        case 2:
            browser.back();
            break;

        case 3:
            browser.forward();
            break;

        case 4:
            browser.showCurrent();
            break;

        case 5:
            browser.showHistory();
            break;

        case 6:
            System.out.println("Goodbye!");
            scanner.close();
            return;

        default:
            System.out.println("Invalid choice");
    }
}
```

### Example Output
using the the console to input the url
```
✓ Now viewing: Google
✓ Now viewing: YouTube
✓ Now viewing: GitHub
← Now viewing: YouTube

=== History ===
1. Google (google.com)
2. YouTube (youtube.com) ← YOU ARE HERE
3. GitHub (github.com)
==============
```

---

## PSEUDO CODE
START
CLASS DoublyBrowser

    CLASS Page
        DECLARE url AS String
        DECLARE title AS String
        DECLARE prev AS Page
        DECLARE next AS Page

        CONSTRUCTOR Page(url, title)
            SET this.url = url
            SET this.title = title
            SET this.prev = NULL
            SET this.next = NULL
        END CONSTRUCTOR

        METHOD toString()
            RETURN title + " (" + url + ")"
        END METHOD
    END CLASS


    DECLARE currentPage AS Page


    METHOD visit(url, title)

        CREATE newPage AS Page(url, title)

        SET newPage.prev = currentPage
        SET newPage.next = NULL

        IF currentPage IS NOT NULL THEN
            SET currentPage.next = NULL
        END IF

        SET currentPage = newPage

        PRINT "Now viewing: " + title

    END METHOD


    METHOD back()

        IF currentPage IS NULL OR currentPage.prev IS NULL THEN
            PRINT "Can't go back"
            RETURN
        END IF

        SET currentPage = currentPage.prev

        PRINT "Now viewing: " + currentPage.title

    END METHOD


    METHOD forward()

        IF currentPage IS NULL OR currentPage.next IS NULL THEN
            PRINT "Can't go forward"
            RETURN
        END IF

        SET currentPage = currentPage.next

        PRINT "Now viewing: " + currentPage.title

    END METHOD


    METHOD showCurrent()

        IF currentPage IS NULL THEN
            PRINT "No page open"
        ELSE
            PRINT "Current: " + currentPage.toString()
        END IF

    END METHOD


    METHOD showHistory()

        IF currentPage IS NULL THEN
            PRINT "No history"
            RETURN
        END IF

        SET first = currentPage

        WHILE first.prev IS NOT NULL DO
            SET first = first.prev
        END WHILE

        PRINT "=== History ==="

        SET p = first
        SET count = 1

        WHILE p IS NOT NULL DO

            IF p IS EQUAL TO currentPage THEN
                PRINT count + ". " + p.toString() + " ← YOU ARE HERE"
            ELSE
                PRINT count + ". " + p.toString()
            END IF

            SET p = p.next
            INCREMENT count

        END WHILE

        PRINT "=============="

    END METHOD

END CLASS
END


## 🧠 Concepts Demonstrated

- Doubly Linked Lists
- Pointer/Reference manipulation
- Navigation state management


---


## 📚 Educational Purpose

This project is intended for learning data structures and Java fundamentals.


---

## 📄 License

free to use for learning and personal projects.


