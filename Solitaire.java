public class Solitaire{
    Deck deckObject;
    Card[] drawPile;
    Card[] discardPile;
    Card[][] board; 
    Card[] finalPiles;  //0:hearts, 1:clubs, 2:diamonds, 3:spades

    private static final int MAX_DRAW_PILE = 24; //52 - 28
    private static final int MAX_DISCARD_PILE = 24;
    private static final int MAX_BOARD_PILE = 19; //6 + 13
    private static final int NUM_BOARD_PILES = 7;

    public Solitaire(){
        this.deckObject = new Deck();
        this.drawPile = new Card[MAX_DRAW_PILE];
        this.discardPile = new Card[MAX_DISCARD_PILE];
        this.board = new Card[NUM_BOARD_PILES][MAX_BOARD_PILE];
        //this.finalPiles = 

        initGame();
    }

    private void initGame(){
        Card[] fullDeck = this.deckObject.shuffleCards();   //Full deck to populate piles from
        int fdPointer = 0;

        //Populate board
        for(int x=0; x < NUM_BOARD_PILES; x++)  //should be 28 cards
            for(int y=0; y<=x; y++)
                this.board[x][y] = fullDeck[fdPointer++];

        //Populate draw pile
        for(int i=0; i < MAX_DRAW_PILE; i++)
            this.drawPile[i] = fullDeck[fdPointer++];
    }

    public void printToConsole(){
        System.out.println("-------------------------------------------------------------");
        System.out.println("| Hearts |  Clubs |Diamonds| Spades |------------------------");
        //System.out.println("|   "++" |   "++" |   "++" |   "++" |------------------------");
    }

    public static void main(String[] args){
        Solitaire game = new Solitaire();
        System.out.println("Draw Pile");
        for(Card c : game.drawPile) System.out.println(c.toString());
        System.out.println("\nBoardPiles");
        for(int x=0; x < NUM_BOARD_PILES; x++){
            System.out.println("Board pile "+x);
            for(int y=0; y<=x; y++) System.out.println(game.board[x][y].toString());
            System.out.println();
        }
    }
}