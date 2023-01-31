public class Solitaire{
    Deck deckObject;
    Card[] drawPile;
    Card[] discardPile;
    LinkedList[] board; 
    LinkedList[] finalPiles;  //0:hearts, 1:clubs, 2:diamonds, 3:spades

    private static final int MAX_DRAW_PILE = 24; //52 - 28
    private static final int MAX_DISCARD_PILE = 24;
    private static final int MAX_BOARD_PILE = 19; //6 + 13
    private static final int NUM_BOARD_PILES = 7;

    public Solitaire(){
        this.deckObject = new Deck();
        this.drawPile = new Card[MAX_DRAW_PILE];
        this.discardPile = new Card[MAX_DISCARD_PILE];
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
            this.drawPile[i] = fullDeck[fdPointer++];

        //Initialize final piles
        for(int i=0; i<4; i++)
            this.finalPiles[i] = new LinkedList();
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
        //System.out.println("|-|-----------------------------------------| "++" | "++" | "++" |---|");
    }

    public static void main(String[] args){
        Solitaire game = new Solitaire();
        System.out.println("Draw Pile");
        for(Card c : game.drawPile) System.out.println(c.toString());

        game.printToConsole();
        
    }
}