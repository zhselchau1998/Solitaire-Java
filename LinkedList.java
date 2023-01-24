class LinkedList{

    Node head;
    int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }
    
    public LinkedList(Card data){
        this.head = null;
        this.size = 0;

        this.add(data);
    }

    public void add(Card data){
        if(this.head = null){
            this.head = new Node(data);
            this.head.next = this.head;
            this.head.prev = this.head;
        }
        else{
            this.head.prev.next = new Node(data);       //old tail.next is new node
            this.head.prev.next.prev = this.head.prev;  //new node.prev is old tail
            this.head.prev = this.head.prev.next;       //head.prev is the new node
            this.head.prev.next = this.head;            //new node.next is the head
        }
        this.size++;
    }

    public Card pop(){
        Card tmp = null;
        if(this.size <= 0){ return tmp; }
        else if(this.size == 1){
            tmp = this.head.data;
            this.head.data = null;
        }
        else{
            tmp = this.head.prev.data;
            this.head.prev.prev.next = this.head;   //node before tail.next is head
            this.head.prev = this.head.prev.prev;   //head.prev = node before tail
        }
        this.size--;
        return tmp;
    }

    class Node{
        Card data;
        Node next;
        Node prev;
        public Node(Card data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
}