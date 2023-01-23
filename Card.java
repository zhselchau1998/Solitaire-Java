public class Card{
    protected int value;   //Value between 1 & 13, 1 ace, 11 jack, 12 queen, 13 king
    protected char suit;

    public static final char[] ALL_SUITS = {'h', 'c', 'd', 's'};    //Hearts Clubs Diamonds Spades

    public Card(int value, char suit){
        
        this.value = roundValue(value);
        for(char c : ALL_SUITS)
            if(suit == c){
                this.suit = suit;   //If valid suit accept
                break;
            }
            else this.suit = 's';   //Else default to spades
    }

    public Card(Card otherCard){
        this.value = otherCard.value;
        this.suit = otherCard.suit;
    }

    public int getValue(){ return this.value; }
    public int getSuit(){ return this.suit; }

    public String toString(){
        String retString = "";
        switch(this.value){
            case 1:
                retString = "Ace of ";
                break;
            case 2:
                retString = "Two of ";
                break;
            case 3:
                retString = "Three of ";
                break;
            case 4:
                retString = "Four of ";
                break;
            case 5:
                retString = "Five of ";
                break;
            case 6:
                retString = "Six of ";
                break;
            case 7:
                retString = "Seven of ";
                break;
            case 8:
                retString = "Eight of ";
                break;
            case 9:
                retString = "Nine of ";
                break;
            case 10:
                retString = "Ten of ";
                break;
            case 11:
                retString = "Jack of ";
                break;
            case 12:
                retString = "Queen of ";
                break;
            case 13:
                retString = "King of ";
                break;
        }

        switch(this.suit){
            case 'h':
                return retString + "Hearts";
            case 'c':
                return retString + "Clubs";
            case 'd':
                return retString + "Diamonds";
        }

        return retString + "Spades";
    }

    public String toShortString(){
        String retString = "";

        switch(this.value){
            case 1:
                retString = "A ";
                break;
            case 11:
                retString = "J ";
                break;
            case 12:
                retString = "Q ";
                break;
            case 13:
                retString = "K ";
                break;
            default:
                retString = "" + this.value + " ";
        }

        retString = retString + this.suit;

        return retString.toUpperCase();
    }

    private int roundValue(int inputVal){
        int retVal = (inputVal > 13) ? 13 : inputVal;
        retVal = (retVal < 1) ? 1 : retVal;
        return retVal;
    }

    public static void main(String[] args){
        Card test;
        char[] testChars = {'h', 'c', 'd', 's', 'z'};
        for(int i=0; i<=14; i++)
            for(char c:testChars){
                test = new Card(i, c);
                System.out.println(test.toString());
                Card test2 = new Card(test);
                System.out.println(test2.toShortString());
            }
        
    }
}
