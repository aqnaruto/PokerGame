import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cardPile{
	char [] Rank={'2','3','4','5','6','7','8','9','T','J','Q','K','A'}; // Initiallizing the ranks and the suits. 
	char [] Suit= {'C','D','H','S'};
	ArrayList<card>myPile=new ArrayList<card>();
	ArrayList<card>drawPile=new ArrayList<card>();
	ArrayList<card>discardPile= new ArrayList<card>();
	public cardPile(){  // creating a card pile of size 52.
		for(int i=0;i<Rank.length;i++){
			for(int j=0;j<Suit.length;j++){
				myPile.add(new card(Rank[i],Suit[j]));
			}
		}
	}
	public void printPile(cardPile mypile){
	for(card myCard : myPile){
		System.out.println(myCard.cardRank +" "+myCard.cardSuit);
	   }
	}
	public void shuffle(cardPile mypile){   // a function that will randomly shuffle the pile
		for(int i=0;i<13;i++){
			for(int j=0;j<4;j++){
				Random rand = new Random();
				mypile.set(4*i+j,rand.nextInt(52));
			}
		}
	}
	
	public void set(int index1,int index2){
		card temp= new card('2','C');
		temp.cardRank=myPile.get(index1).cardRank;
		temp.cardSuit=myPile.get(index1).cardSuit;
		myPile.get(index1).cardRank=myPile.get(index2).cardRank;
		myPile.get(index1).cardSuit=myPile.get(index2).cardSuit;
		myPile.get(index2).cardRank=temp.cardRank;
		myPile.get(index2).cardSuit=temp.cardSuit;
	}
	
	public void initdrawPile(){
				drawPile.addAll(myPile);
	  }
	
	public void printDrawPile(){
		System.out.println("Size of draw deck is " + drawPile.size());
		for(int i=0;i<drawPile.size();i++){
			System.out.println(drawPile.get(i).cardRank +"" +drawPile.get(i).cardSuit);
		}
	}
	  
}
