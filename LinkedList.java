class LinkedList{   //Circular linked list

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

    public void add(Card data) { 
        this.addTail(data);
    }

    public void addHead(Card data){
        if(data == null) return;
        this.addTail(data);
        this.head = this.head.prev;
    }

    public void addTail(Card data){ //Adds at tail
        if(data == null) return;
        if(this.head == null){
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

    public Card popTail(){
        Card tmp = null;
        if(this.size <= 0){ return tmp; }
        else if(this.size == 1){
            tmp = this.head.data;
            this.head = null;
        }
        else{
            tmp = this.head.prev.data;
            this.head.prev.prev.next = this.head;   //node before tail.next is head
            this.head.prev = this.head.prev.prev;   //head.prev = node before tail
        }
        this.size--;
        return tmp;
    }

    public Card popHead(){
        Card tmp = null;
        if(this.size <= 0){ return tmp; }
        else if(this.size == 1){
            tmp = this.head.data;
            this.head = null;
        }
        else{
            tmp = this.head.data;
            this.head.prev.next = this.head.next;
            this.head.next.prev = this.head.prev;
            this.head = this.head.next;
        }
        this.size--;
        return tmp;
    }

    public Card get(int index){
        if(this.head == null) return null;
        if(index == 0) return head.data;

        while(index < 0) index += this.size;
        while(index >= this.size) index -= this.size;

        int pos = 1;
        Node ptr = head.next;
        while(pos < index){
            ptr = ptr.next;
            pos++;
        }

        if(ptr == this.head) return null;

        return ptr.data;
    }

    public Card[] toArray(){
        Card[] retArr = new Card[this.size];
        Node ptr = this.head;

        for(int i=0; i<this.size; i++){
            retArr[i] = ptr.data;
            ptr = ptr.next;
        }

        return retArr;
    }

    public Card getHeadCard(){
        if(this.head == null) return null;
        return this.head.data;
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