import java.util.Scanner;

public class Solitaire{
    Deck deckObject;
    LinkedList drawPile;
    LinkedList discardPile;
    LinkedList[] board; 
    LinkedList[] finalPiles;  //0:hearts, 1:clubs, 2:diamonds, 3:spades

    private boolean inGame;

    private static final int MAX_DRAW_PILE = 24; //52 - 28
    private static final int MAX_DISCARD_PILE = 24;
    private static final int NUM_BOARD_PILES = 7;

    public Solitaire(){
        this.deckObject = new Deck();
        this.drawPile = new LinkedList();
        this.discardPile = new LinkedList();
        this.board = new LinkedList[NUM_BOARD_PILES];
        this.finalPiles = new LinkedList[4];
        this.inGame = true;

        initGame();
    }

    private void initGame(){
        Card[] fullDeck = this.deckObject.shuffleCards();   //Full deck to populate piles from
        int fdPointer = 0;

        //Populate board
        for(int x=0; x < NUM_BOARD_PILES; x++){ //should be 28 cards
            this.board[x] = new LinkedList();
            for(int y=0; y<=x; y++)
                this.board[x].add(fullDeck[fdPointer++]);
            this.board[x].get(-1).isVisible = true;
        }

        //Populate draw pile
        for(int i=0; i < MAX_DRAW_PILE; i++)
            this.drawPile.add(fullDeck[fdPointer++]);

        //Initialize final piles
        for(int i=0; i<4; i++)
            this.finalPiles[i] = new LinkedList();
    }

    public void startGame(){
        
        printToConsole();

        gameLoop();
    }

    private void gameLoop(){
        while(this.inGame){
            //gather command
            String action = "";
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter a command: ");
            action = sc.nextLine();
            System.out.println();

            //process command
            this.processCommand(action);

            //print screen
            this.printToConsole();
        }
    }

    private void processCommand(String action){
        action = action.toUpperCase().strip();
        if(action.compareTo("DRAW") == 0) this.draw();
        else if(action.compareTo("EXIT") == 0) this.inGame = false;
        else if(action.compareTo("HELP") == 0) this.printHelp();
        else if(this.isValidMoveSelection(action)) this.moveFrom(action);
        else System.out.println("\nType \"help\" for a list of commands");
    }

    private boolean isValidMoveSelection(String cmd){
        if(cmd.compareTo("1")==0) return true;
        if(cmd.compareTo("2")==0) return true;
        if(cmd.compareTo("3")==0) return true;
        if(cmd.compareTo("4")==0) return true;
        if(cmd.compareTo("5")==0) return true;
        if(cmd.compareTo("6")==0) return true;
        if(cmd.compareTo("7")==0) return true;
        if(cmd.compareTo("FLOP")==0)  return true;
        if(cmd.compareTo("H")==0) return true;
        if(cmd.compareTo("C")==0) return true;
        if(cmd.compareTo("D")==0) return true;
        if(cmd.compareTo("S")==0) return true;
        return false;
    }

    private void moveFrom(String location){
        Card card = null;
        boolean moved = false;
        Card[] batch = null;
        int batchIdx = -1;

        //get card
        if(location.compareTo("FLOP") == 0) card = this.discardPile.getHeadCard();
        else if(location.compareTo("H") == 0) card = this.finalPiles[0].getHeadCard();
        else if(location.compareTo("C") == 0) card = this.finalPiles[1].getHeadCard();
        else if(location.compareTo("D") == 0) card = this.finalPiles[2].getHeadCard();
        else if(location.compareTo("S") == 0) card = this.finalPiles[3].getHeadCard();
        else{ //is board
            int i = Integer.parseInt(location) - 1;
            card = this.board[i].getTail();
            batch = this.board[i].toArray();
            batchIdx = batch.length-1;
        }

        if(card == null) return;

        //first check if this card can go in final piles
        if(card.suit == 'h'){
            if(this.finalPiles[0].isEmpty()) moved = card.value == 1;   //if ace and nothing in pile
            else moved = card.value == this.finalPiles[0].getHeadCard().value+1;
            if(moved) this.finalPiles[0].addHead(card);
        }
        if(card.suit == 'c'){
            if(this.finalPiles[1].isEmpty()) moved = card.value == 1;
            else moved = card.value == this.finalPiles[1].getHeadCard().value+1;
            if(moved) this.finalPiles[1].addHead(card);
        }
        if(card.suit == 'd'){
            if(this.finalPiles[2].isEmpty()) moved = card.value == 1;
            else moved = card.value == this.finalPiles[2].getHeadCard().value+1;
            if(moved) this.finalPiles[2].addHead(card);
        }
        if(card.suit == 's'){
            if(this.finalPiles[3].isEmpty()) moved = card.value == 1;
            else moved = card.value == this.finalPiles[3].getHeadCard().value+1;
            if(moved) this.finalPiles[3].addHead(card);
        }

        //next check if this card can move to anywhere on the board
        for(int i=0; i < this.NUM_BOARD_PILES; i++)
            if(!moved){
                if(this.board[i].isEmpty()) moved = card.value == 13;   //if king and nothing in pile
                else moved = card.value == this.board[i].getTail().value - 1 && !card.isSameColor(this.board[i].getTail());
                if(moved){
                    board[i].add(card);
                }
            }

        //Also check if batch exists
        if(batch != null && !moved)
            for(int i=0; i < this.NUM_BOARD_PILES; i++)
                for(int j=batch.length-2; j>=0; j--)
                    if(!moved && batch[j].isVisible){
                        if(this.board[i].isEmpty()) moved = batch[j].value == 13;   //if king and nothing in pile
                        else moved = batch[j].value == this.board[i].getTail().value - 1 && !batch[j].isSameColor(this.board[i].getTail());
                        if(moved){
                            board[i].addList(board[Integer.parseInt(location) - 1].getList(j));
                            batchIdx = j;
                        }
                    }

        //last if card moved then remove it from old pile
        if(moved){
            if(location.compareTo("FLOP") == 0) this.discardPile.popHead();
            else if(location.compareTo("H") == 0) this.finalPiles[0].popHead();
            else if(location.compareTo("C") == 0) this.finalPiles[1].popHead();
            else if(location.compareTo("D") == 0) this.finalPiles[2].popHead();
            else if(location.compareTo("S") == 0) this.finalPiles[3].popHead();
            else{ //is board
                int i = Integer.parseInt(location) - 1;
                this.board[i].popList(batchIdx);
                if(!this.board[i].isEmpty())this.board[i].getTail().isVisible = true;
            }
        }
    }

    private void printHelp(){
        System.out.println("|-|-----------------------|HELP TEXT|-----------------------|-|");
        System.out.println("|-|1, 2, 3, 4, 5, 6, 7 - All move from respective board pile|-|");
        System.out.println("|-|flop ---------------- Move from the 1st of 3 drawn cards |-|");
        System.out.println("|-|H, C, D, S ---------- All move from respective final pile|-|");
        System.out.println("|-|help ---------------- Prints help menu                   |-|");
        System.out.println("|-|draw ---------------- Draw new set of 3 cards            |-|");
        System.out.println("|-|exit ---------------- exit the game                      |-|");
        System.out.println("|-|---------------------------------------------------------|-|");
    }

    private void draw(){
        if(this.drawPile.isEmpty()){ //reset draw pile
            LinkedList tmp = this.drawPile;
            this.drawPile = this.discardPile;
            this.discardPile = tmp;
        }
        else{   //draw 3 or all that are left, whichever is fewer
            for(int i=0; i<3; i++){
                Card card = this.drawPile.popTail();
                if(card != null){
                    card.isVisible = true;
                    this.discardPile.addHead(card);
                }
            }
        }
    }

    private String CardToString(Card card){
        if(card == null) return "    ";
        return card.toShortString();
    }

    public void printToConsole(){
        System.out.println("|-|---------------------------------------------------------|-|");

        System.out.println("|-|  H  |  C  |  D  |  S  |-----| 1st | 2nd | 3rd | Deck|-----|");
        System.out.print("|-|"+this.CardToString(this.finalPiles[0].getHeadCard())+" |"
           +this.CardToString(this.finalPiles[1].getHeadCard())+" |"
           +this.CardToString(this.finalPiles[2].getHeadCard())+" |"
           +this.CardToString(this.finalPiles[3].getHeadCard())+" |-----|"
           +this.CardToString(this.discardPile.get(0))+" |"
           +this.CardToString(this.discardPile.get(1))+" |"
           +this.CardToString(this.discardPile.get(2))+" |  "
           +this.drawPile.size);
        if(this.drawPile.size < 10) System.out.println("  |-----|");
        else System.out.println(" |-----|");

        System.out.println("|-|---------------------------------------------------------|-|");

        System.out.println("|-|-----|  1  |  2  |  3  |  4  |  5  |  6  |  7  |---------|-|");
        boolean boardExists = true;
        int itr = 0;
        while(boardExists){
            String output = "|-|-----|";
            boardExists = false;
            for(int i=0; i<7; i++){
                Card tmp = this.board[i].get(itr);
                output += this.CardToString(tmp) + " |";
                if(tmp != null) boardExists = true;
            }
            output += "---------|-|";
            itr++;
            System.out.println(output);
        }

        System.out.println("|-|---------------------------------------------------------|-|");
    }

    public static void main(String[] args){
        Solitaire game = new Solitaire();
        game.startGame();
        
    }
}