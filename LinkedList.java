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
        if(this.isEmpty()){
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

    public void addList(LinkedList ll){ //Adds a whole linked list
        if(ll == null || ll.isEmpty()) return;
        while(!ll.isEmpty()) this.add(ll.popHead());
    }

    public Card popTail(){
        Card tmp = null;
        if(this.isEmpty()){ return tmp; }
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
        if(this.isEmpty()){ return tmp; }
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

    public LinkedList popList(int index){
        if(this.isEmpty() || this.size <= index) return null;
        while(index < 0) index += this.size;

        LinkedList retLL = new LinkedList();

        if(index == 0){
            retLL.size = this.size;
            retLL.head = this.head;
            this.size = 0;
            this.head = null;
            return retLL;
        }
        
        int pos = 1;
        Node ptr = this.head.next;
        Node tmp = null;
        while(pos < index){
            ptr = ptr.next;
            pos++;
        }

        retLL.size = this.size - index;
        this.size = index;
        retLL.head = ptr;
        tmp = this.head.prev;
        this.head.prev.next = ptr;  //old tail -> new head
        this.head.prev = ptr.prev;  //old head -> new tail
        ptr.prev.next = this.head;  //new tail -> old head
        ptr.prev = tmp;             //new head -> old tail


        return retLL;
    }

    public LinkedList getList(int index){
        if(this.isEmpty() || this.size <= index) return null;
        while(index < 0) index += this.size;

        LinkedList retLL = new LinkedList();

        Node ptr = this.head.next;
        if(index == 0) retLL.add(this.head.data);
        
        int pos = 1;
        ptr = this.head.next;
        Node tmp = null;
        while(pos < index){
            ptr = ptr.next;
            pos++;
        }

        while(ptr != head){
            retLL.add(ptr.data);
            ptr = ptr.next;
        }
        return retLL;

    }

    public Card get(int index){
        if(this.isEmpty() || this.size <= index) return null;
        while(index < 0) index += this.size;

        if(index == 0) return head.data;

        int pos = 1;
        Node ptr = this.head.next;
        while(pos < index){
            ptr = ptr.next;
            pos++;
        }

        if(ptr == this.head) return null;

        return ptr.data;
    }

    public Card getTail(){
        if(this.isEmpty()) return null;
        return this.head.prev.data;

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
        if(this.isEmpty()) return null;
        return this.head.data;
    }

    public boolean isEmpty(){
        return this.size == 0;
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

    public static void main(String[] args){
        LinkedList test = new LinkedList();
        test.add(new Card(1, 'c', true));
        test.add(new Card(2, 'c', true));
        test.add(new Card(3, 'c', true));

        LinkedList test1 = new LinkedList();
        test1.add(new Card(4, 's', true));

        System.out.println("Test 1 before");
        for(int i=0; i < test.size; i++)
            System.out.print(test.get(i).toShortString() + " -> ");
        System.out.println("\nTest 2 before");
        for(int i=0; i < test1.size; i++)
            System.out.print(test1.get(i).toShortString() + " -> ");

        test1.addList(test);

        System.out.println("\n\nTest 1 after");
        for(int i=0; i < test.size; i++)
            System.out.print(test.get(i).toShortString() + " -> ");
        System.out.println("\nTest 2 after");
        for(int i=0; i < test1.size; i++)
            System.out.print(test1.get(i).toShortString() + " -> ");

        test = test1.popList(1);
    
        System.out.println("\n\nTest 1 after 2");
        for(int i=0; i < test.size; i++)
            System.out.print(test.get(i).toShortString() + " -> ");
        System.out.println("\nTest 2 after 2");
        for(int i=0; i < test1.size; i++)
            System.out.print(test1.get(i).toShortString() + " -> ");

    }
}