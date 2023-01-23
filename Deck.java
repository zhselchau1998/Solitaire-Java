import java.util.Random;

public class Deck{
    private Card[] cards;
    private int pointer;    //For drawing cards

    public static final int MAX_CARDS = 52;
    public static final int CARDS_PER_SUIT = 13;
    public static final char[] ALL_SUITS = Card.ALL_SUITS;

    public Deck(){
        this.cards = this.initCards();
        this.cards = this.shuffleCards();
        this.pointer = 0;
    }

    private Card[] initCards(){
        Card[] retCards = new Card[MAX_CARDS];

        this.pointer = 0;

        for(int i=0; i < MAX_CARDS; i++)    //Create every card (1-13) in all 4 suits (h,c,d,s)
            retCards[i] = new Card((i % CARDS_PER_SUIT) + 1, ALL_SUITS[i / CARDS_PER_SUIT]);

        return retCards;
    }

    public Card[] shuffleCards(){
        Card[] retCards = new Card[MAX_CARDS];
        Card[] copyCards = new Card[MAX_CARDS];
        Random rand = new Random();

        this.pointer = 0;

        for(int i=0; i<MAX_CARDS; i++) copyCards[i] = this.cards[i];

        for(int i=MAX_CARDS; i>0; i--){
            int chosenIndex = rand.nextInt(i);
            retCards[i-1] = copyCards[chosenIndex];     //Remove card and put into new deck
            copyCards[chosenIndex] = copyCards[i-1];    //Replace removed card
        }

        return retCards;
    }

    public Card[] getCards(){
        Card[] retCards = new Card[MAX_CARDS];
        for(int i=0; i<MAX_CARDS; i++)
            retCards[i] = new Card(this.cards[i]); 
        return retCards;
    }

    public Card draw(){
        if(pointer >= MAX_CARDS) pointer = 0;
        return this.cards[this.pointer++];
    }

    public static void main(String[] args){
        Deck d = new Deck();
        Card[] cards = d.getCards();
        for(Card c : cards)
            System.out.println(c.toString() + " | " + c.toShortString());
    }
}