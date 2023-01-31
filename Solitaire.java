public class Solitaire{
    Deck deckObject;
    LinkedList drawPile;
    LinkedList discardPile;
    LinkedList[] board; 
    LinkedList[] finalPiles;  //0:hearts, 1:clubs, 2:diamonds, 3:spades

    private static final int MAX_DRAW_PILE = 24; //52 - 28
    private static final int MAX_DISCARD_PILE = 24;
    private static final int MAX_BOARD_PILE = 19; //6 + 13
    private static final int NUM_BOARD_PILES = 7;

    public Solitaire(){
        this.deckObject = new Deck();
        this.drawPile = new LinkedList();
        this.discardPile = new LinkedList();
        this.board = new LinkedList[NUM_BOARD_PILES];
        this.finalPiles = new LinkedList[4];

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
        }

        //Populate draw pile
        for(int i=0; i < MAX_DRAW_PILE; i++)
            this.drawPile.add(fullDeck[fdPointer++]);

        //Initialize final piles
        for(int i=0; i<4; i++)
            this.finalPiles[i] = new LinkedList();
    }

    public void draw(){ //TODO: handle edge cases, this is just for testing right now
        for(int i=0; i<3; i++){
            this.discardPile.addHead(this.drawPile.popTail());
        }
    }

    private String CardToString(Card card){
        if(card == null) return "    ";
        return card.toShortString();
    }

    public void printToConsole(){
        System.out.println("|-|------------------------------------------------------------------|");
        System.out.println("|-| Hearts |  Clubs |Diamonds| Spades |------------------------------|");
        System.out.println("|-|   "+this.CardToString(this.finalPiles[0].getHeadCard())+" |   "
           +this.CardToString(this.finalPiles[1].getHeadCard())+" |   "
           +this.CardToString(this.finalPiles[2].getHeadCard())+" |   "
           +this.CardToString(this.finalPiles[3].getHeadCard())+" |-----|  Top |Second| Draw |---|");
        System.out.println("|-|-----------------------------------------| "+this.CardToString(this.discardPile.get(0))+" | "+this.CardToString(this.discardPile.get(1))+" | "+this.CardToString(this.discardPile.get(2))+" |---|");
    }

    public static void main(String[] args){
        Solitaire game = new Solitaire();

        System.out.println(game.drawPile.get(-1).toShortString());
        System.out.println(game.drawPile.get(-2).toShortString());
        System.out.println(game.drawPile.get(-3).toShortString());
        game.draw();

        game.printToConsole();

        System.out.println(game.drawPile.get(-1).toShortString());
        System.out.println(game.drawPile.get(-2).toShortString());
        System.out.println(game.drawPile.get(-3).toShortString());
        game.draw();

        game.printToConsole();
        
    }
}