public class Solitaire{
    Deck deckObject;
    Card[] drawPile;
    Card[] discardPile;
    Card[][] board; 

    private static final int MAX_DRAW_PILE = 24; //52 - 28
    private static final int MAX_DISCARD_PILE = 24;
    private static final int MAX_BOARD_PILE = 19; //6 + 13
    private static final int NUM_BOARD_PILES = 7;

    public Solitaire(){
        this.deckObject = new Deck();
        this.drawPile = new Card[MAX_DRAW_PILE];
        this.discardPile = new Card[MAX_DISCARD_PILE];
        this.board = new Card[NUM_BOARD_PILES][MAX_BOARD_PILE];
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
}