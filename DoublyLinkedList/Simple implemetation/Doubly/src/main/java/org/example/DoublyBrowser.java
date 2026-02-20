package org.example;

public class DoublyBrowser {

        static class Page {
            String url;
            String title;
            Page prev;
            Page next;

            Page(String url, String title) {
                this.url = url;
                this.title = title;
            }

            public String toString() {
                return title + " (" + url + ")";
            }
        }

        private Page currentPage;


        public void visit(String url, String title) {
            Page newPage = new Page(url, title);
            newPage.prev = currentPage;
            newPage.next = null;


            if (currentPage != null) {
                currentPage.next = null;
            }

            currentPage = newPage;
            System.out.println("✓ Now viewing: " + title);
        }


        public void back() {
            if (currentPage == null || currentPage.prev == null) {
                System.out.println("✗ Can't go back");
                return;
            }
            currentPage = currentPage.prev;
            System.out.println("← Now viewing: " + currentPage.title);
        }


        public void forward() {
            if (currentPage == null || currentPage.next == null) {
                System.out.println("✗ Can't go forward");
                return;
            }
            currentPage = currentPage.next;
            System.out.println("→ Now viewing: " + currentPage.title);
        }


        public void showCurrent() {
            if (currentPage == null) {
                System.out.println("No page open");
            } else {
                System.out.println("Current: " + currentPage);
            }
        }

        // Sho
        public void showHistory() {
            if (currentPage == null) {
                System.out.println("No history");
                return;
            }

            // Go to first page
            Page first = currentPage;
            while (first.prev != null) {
                first = first.prev;
            }

            // Show all pages
            System.out.println("\n=== History ===");
            Page p = first;
            int count = 1;
            while (p != null) {
                String marker = (p == currentPage) ? " ← YOU ARE HERE" : "";
                System.out.println(count + ". " + p + marker);
                p = p.next;
                count++;
            }
            System.out.println("==============");
        }

    }
