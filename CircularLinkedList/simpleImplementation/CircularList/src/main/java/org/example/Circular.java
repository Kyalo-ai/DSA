package org.example;
class CNode{
    String data;
    CNode next;
    CNode (String data){
        this.data=data;
        this.next=null;
    }


}


public class Circular {
    CNode head;
    public   Circular(){
        this.head=null;

    }
    public void InsertFirst(String data){
        CNode newNode= new CNode(data);
        if(head == null){
            newNode.next = newNode;
            head =newNode;
        }
        CNode temp = head;
        while(temp.next != head){
            temp = temp.next;

        }
        temp .next = newNode;
        newNode.next=head;
        head= newNode;
    }
    public void InsertEnd(String data){
        CNode newNode= new CNode(data);
        if(head==null){
            newNode.next=newNode;
            head = newNode;
        }
        CNode temp=head;
        while(temp.next !=head){
            temp=temp.next;
        }
        temp.next=newNode;
        newNode.next=head;
    }
    public void deleteFirst(){
        if(head==null){

        }

        assert head != null;
        if(head.next==head){
            head=null;
            return;
        }
        CNode temp = head;
        while(temp.next != head){
            temp = temp.next;
        }
        temp.next = head.next;
        head = head .next;
    }
    public void Display(){
        if(head == null) return;
        CNode temp = head;
        do{
            System.out.print(temp.data +"--->");
            temp= temp.next;
        }
        while(temp !=head);
        System.out.println("Back to end");
    }
}
