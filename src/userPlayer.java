
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class userPlayer {
    ArrayList<card>userHand = new ArrayList<card>();
    cardPile mypile= new cardPile();
    userPlayer(){
    	mypile.shuffle(mypile);
    	mypile.initdrawPile();
    	mypile.printDrawPile();
    		for(int j=0;j<5;j++){
    			userHand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
    			mypile.drawPile.remove(mypile.drawPile.get(mypile.drawPile.size()-1));
    		}
    		System.out.println(mypile.drawPile.size());
    		//mypile.printDrawPile();
    }
    public void printHand(ArrayList<card>myHand){
    	System.out.print("This is your hand "+ "\t");
    	for(card hand : myHand){
    		System.out.println(hand.cardRank+" "+hand.cardSuit);
    	}
    }
    public void discardUserHand(ArrayList<card>Hand,userPlayer mC){
    	int flag=0;
    	int index=-1;
    	for(int i=0;i<5;i++){
    		if(Hand.get(i).cardRank=='A'){
    			System.out.println("Since you have an Ace you can discard 4 cards");
    			flag=1;
    		     break;
    		}
    	}
    		if(flag==0)
    			System.out.println("Please Discard 3 Cards by their index ascending order space seperated ");
             mC.printHand(Hand);
    		 Scanner userInput= new Scanner(System.in);
    		 String input= userInput.nextLine();
    		 String [] arr=input.split(" ");
    		 for(int i=arr.length-1;i>-1;i--){
    			 System.out.println(((int) arr[i].charAt(0))-49);
                  index=((int) arr[i].charAt(0))-49;
                  mypile.discardPile.add(new card(Hand.get(index).cardRank,Hand.get(index).cardSuit));
    		       Hand.remove(((int) arr[i].charAt(0))-49);
    		 }
    		 while(Hand.size()<5){
    			 drawCard(Hand);
    		 }
    		 printHand(Hand);
    		// mypile.printDrawPile();
    }
    public void drawCard(ArrayList<card>newHand){
    	    newHand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,(mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit)));
            mypile.drawPile.remove(mypile.drawPile.size()-1);
    }
    
    }