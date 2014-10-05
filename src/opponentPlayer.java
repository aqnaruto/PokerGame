import java.util.ArrayList;
public class opponentPlayer {
      //userPlayer user = new userPlayer();
      //user.discardUserHand(user.userHand,user);
	  ArrayList<card>opponentHand= new ArrayList<card>();
      opponentPlayer(cardPile mypile){
    	  for(int i=0;i<5;i++){
    		  opponentHand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
    		  mypile.drawPile.remove(mypile.drawPile.get(mypile.drawPile.size()-1));
    	  }
    	  System.out.println(mypile.drawPile.size());
    	  System.out.println("Unsorted hand: ");
    	  for(int j=0;j<5;j++){
    		  System.out.println(opponentHand.get(j).cardRank + " " +opponentHand.get(j).cardSuit);
    	  }
    	 /*
    	  opponentHand.clear();
    	  opponentHand.add(new card('8','H'));
    	  opponentHand.add(new card('8','D'));
    	  opponentHand.add(new card('8','C'));
    	  opponentHand.add(new card('8','S'));
    	  opponentHand.add(new card('2','C'));
    	  */
    	  
    	  sortHand(opponentHand,mypile);
    	  System.out.println("Sorted hand: ");
    	  for(int j=0;j<5;j++){
    		  System.out.println(opponentHand.get(j).cardRank + " " +opponentHand.get(j).cardSuit);
    	  }
      }
      public void sortHand(ArrayList<card>cardsHand,cardPile pile){
    	  sortByRank(cardsHand,pile,0);
    	  card temp1=null;
    	  card temp2=null;
    	  String res=isMatching(cardsHand);
    	  System.out.println(res);
    	  if(isMatching(cardsHand).equals("KfullHouse")&&cardsHand.get(2).cardRank==cardsHand.get(4).cardRank){
    		  //swap the pair with the 3 of a kind
    		  //System.out.println("get here");
    		  temp1= new card(cardsHand.get(3).cardRank,cardsHand.get(3).cardSuit);
			  cardsHand.get(3).cardRank=cardsHand.get(0).cardRank;
			  cardsHand.get(3).cardSuit=cardsHand.get(0).cardSuit;
			  cardsHand.get(0).cardRank=temp1.cardRank;
			  cardsHand.get(0).cardSuit=temp1.cardSuit;
			  temp1= new card(cardsHand.get(4).cardRank,cardsHand.get(4).cardSuit);
			  cardsHand.get(4).cardRank=cardsHand.get(1).cardRank;
			  cardsHand.get(4).cardSuit=cardsHand.get(1).cardSuit;
			  cardsHand.get(1).cardRank=temp1.cardRank;
			  cardsHand.get(1).cardSuit=temp1.cardSuit;
			  return;
    	  }
    	  int numSwaps=0;
    	  int curr=0;
    	  for(int i=0;i<4;i++){
    		  while(i!=4 && cardsHand.get(i).cardRank==cardsHand.get(i+1).cardRank){
    			  temp1= new card(cardsHand.get(i).cardRank,cardsHand.get(i).cardSuit);
    			  cardsHand.get(i).cardRank=cardsHand.get(numSwaps).cardRank;
    			  cardsHand.get(i).cardSuit=cardsHand.get(numSwaps).cardSuit;
    			  cardsHand.get(numSwaps).cardRank=temp1.cardRank;
    			  cardsHand.get(numSwaps).cardSuit=temp1.cardSuit;
    			  numSwaps++;
    			  curr++;
    			  i++;
    		  }
    		  if(curr>0){
    			  temp1= new card(cardsHand.get(i).cardRank,cardsHand.get(i).cardSuit);
    			  cardsHand.get(i).cardRank=cardsHand.get(numSwaps).cardRank;
    			  cardsHand.get(i).cardSuit=cardsHand.get(numSwaps).cardSuit;
    			  cardsHand.get(numSwaps).cardRank=temp1.cardRank;
    			  cardsHand.get(numSwaps).cardSuit=temp1.cardSuit;
    			  numSwaps++;
    			  curr=0;
    		  }
    	  }
    	  //System.out.println("Num of swaps :"+numSwaps);
    	  sortByRank(cardsHand,pile,numSwaps);
      }
      public void sortByRank(ArrayList<card>myhand, cardPile pile, int startIndex){
    	  card temp1 = new card('2','H');
    	  int index1 = -1,index2 = -1;
    	  for(int i=startIndex;i<4;i++){
    		  for(int k=i+1;k<5;k++){
    			   for(int j=0;j<13;j++){
    				  if(myhand.get(i).cardRank==pile.Rank[j]){
    				  index1=j;
    				  }
    				  if(myhand.get(k).cardRank==pile.Rank[j]){
    				  index2=j;
    				  }
    			   }
    			  //System.out.println("This is the index" +index1+" "+index2);
    			  if(index1<index2){
    				  temp1.cardRank= myhand.get(k).cardRank;
    				  temp1.cardSuit= myhand.get(k).cardSuit;
    				  System.out.println("Index2 :"+temp1.cardRank+" " +temp1.cardSuit);
    				  myhand.get(k).cardRank=myhand.get(i).cardRank;
    				  myhand.get(k).cardSuit=myhand.get(i).cardSuit;
    				  myhand.get(i).cardRank=temp1.cardRank;
    				  myhand.get(i).cardSuit=temp1.cardSuit;
    		  }    	  
    		 }
    	  }
      }
      public void DiscardOpHand(ArrayList<card>hand,cardPile mypile){
    	  sortHand(hand,mypile);
    	  if(isStraight(hand,mypile)==true || isFlush(hand)==true ){
    		  System.out.println("These are the computers Cards");
    	  }
    	  
    	  else if(isMatching(hand).startsWith("K")){ //
    		  System.out.println("I got a match");
    			  if(isMatching(hand).equals("KPair")){
    				  System.out.println("I got 2 pair");
    				  for(int i=0;i<3;i++){
    					mypile.discardPile.add(new card(hand.get(2).cardRank,hand.get(2).cardSuit));
    					hand.remove(2);
    					hand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
    				    mypile.drawPile.remove(mypile.drawPile.size()-1);
    				}
    			  }
    			   else if(isMatching(hand).equals("KtwoPair")){
    				   System.out.println("I got a two pair");
    				  mypile.discardPile.add(new card(hand.get(4).cardRank,hand.get(4).cardSuit));
  					  hand.remove(4);
  					  hand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
  				      mypile.drawPile.remove(mypile.drawPile.size()-1);  
    			  }
    			   else if(isMatching(hand).equals("KThree")){
    				   System.out.println("I got a three of a kind");
    					for(int i=0;i<2;i++){
        					mypile.discardPile.add(new card(hand.get(3).cardRank,hand.get(3).cardSuit));
        					hand.remove(3);
        					hand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
        				    mypile.drawPile.remove(mypile.drawPile.size()-1);
        				}
    			  }
    			   else if(isMatching(hand).equals("KFour")){
    				   System.out.println("I got four of a kind");
    				  mypile.discardPile.add(new card(hand.get(4).cardRank,hand.get(4).cardSuit));
  					  hand.remove(4);
  					  hand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
  				      mypile.drawPile.remove(mypile.drawPile.size()-1);  
    			  }
    	  }
    	  else if(isAlmostStraight(hand,mypile)==true){  // It's a high card hand
    	       int offIndex;    
    		  for(int i=0;i<5;i++){
    			  for(int j=i;j<i+1;j++){
    			      for(int k=0;k<13;k++){
    			          if((hand.get(i).cardRank== mypile.Rank[k] &&hand.get(j).cardRank!=mypile.Rank[k+1])&&hand.get(j).cardRank!='2'&&hand.get(i).cardRank!='A'){
    			        	  offIndex=i;
    			        	  System.out.println("Off index is:"+ i);
    			        	  mypile.discardPile.add(new card(hand.get(offIndex).cardRank,hand.get(offIndex).cardSuit));
    			        	  hand.remove(offIndex);
    			        	  hand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
    			        	  mypile.drawPile.remove(mypile.drawPile.size()-1);  
    			    }
    	         }
    	       }
    	    }
    	  }
    	  else if(isAlmostFlush(hand)==true){
    		   int offIndex;
    		   for(int i=0;i<5;i++){
    			   for(int j=i;j<i+1;j++){
    				   if(hand.get(i).cardSuit!=hand.get(j).cardSuit){
    					     offIndex=i;
    					     System.out.println("Off index is:"+ i);
    	    				 mypile.discardPile.add(new card(hand.get(offIndex).cardRank,hand.get(offIndex).cardSuit));
    	    				 hand.remove(offIndex);
    	    				 hand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
    	    			     mypile.drawPile.remove(mypile.drawPile.size()-1);  
    				   }
    			   }
    		   }
    	  }
    	  else if(isAce(hand)==true){
    		     System.out.println("This hand has an ace");
    		    for(int i=1;i<5;i++){
    		       mypile.discardPile.add(new card(hand.get(i).cardRank,hand.get(i).cardSuit));
    		    }
    		    for(int i=4;i>0;i--){
    		    	hand.remove(i);
    		    }
    		    for(int i=0;i<4;i++){
    		    	hand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
    		        mypile.drawPile.remove(mypile.drawPile.size()-1);
    		    }
    	  }
    	  
    	  else{ // discarding last 3 cards
    		     System.out.println("This hand evaluated to high card");
    		    for(int i=2;i<5;i++){
     		       mypile.discardPile.add(new card(hand.get(i).cardRank,hand.get(i).cardSuit));
     		    }
     		    for(int i=4;i>1;i--){
     		    	hand.remove(i);
     		    }
     		    for(int i=0;i<3;i++){
     		    	hand.add(new card(mypile.drawPile.get(mypile.drawPile.size()-1).cardRank,mypile.drawPile.get(mypile.drawPile.size()-1).cardSuit));
     		        mypile.drawPile.remove(mypile.drawPile.size()-1);
     		    }
    	  }
    	  
    	  System.out.println("This is the computer's hand");
    	  for(int i=0;i<5;i++)
    		  System.out.println(hand.get(i).cardRank+" "+hand.get(i).cardSuit);
      }
          public boolean isFlush(ArrayList<card>hand){
    	  int matchCounter=0;
    	  for(int i=0;i<4;i++){
    		  for(int j=i+1;j<5;j++)
    		  if(hand.get(i).cardSuit==hand.get(j).cardSuit)
    			  matchCounter++;
    	  }
    	  if(matchCounter==10)
    		  return true;
    	  else return false;
      }
          public boolean isAlmostFlush(ArrayList<card>hand){
        	  int matchCounter=0;
        	  for(int i=0;i<4;i++){
        		  for(int j=i+1;j<5;j++)
        		  if(hand.get(i).cardSuit==hand.get(j).cardSuit)
        			  matchCounter++;
        	  }
        	  if(matchCounter==6)
        		  return true;
        	  else return false;
          }
          
          public boolean isStraight(ArrayList<card>Hand,cardPile mypile){
        	  int matchCounter=0;
        	  for(int i=0;i<4;i++){
        		  for(int j=i;j<i+1;j++){
        			  for(int k=0;k<12;k++){
        				  if(Hand.get(i).cardRank== mypile.Rank[k] &&Hand.get(j).cardRank==mypile.Rank[k+1]){
        					  matchCounter++;
        					  break;
        				  }
        		  }
        			  if(Hand.get(0).cardRank=='2'&& Hand.get(4).cardRank=='A'){
        				  matchCounter++;
        			  }
        	  }
        	 }
        	  if(matchCounter==4)
        		  return true;
        	  else return false;
          }
          public boolean isAlmostStraight(ArrayList<card>Hand,cardPile mypile){
        	  int matchCounter=0;
        	  for(int i=0;i<4;i++){
        		  for(int j=i;j<i+1;j++){
        			  for(int k=0;k<12;k++){
        				  if(Hand.get(i).cardRank== mypile.Rank[k] &&Hand.get(j).cardRank==mypile.Rank[k+1]){
        					  matchCounter++;
        					  break;
        				  }
        		  }
        			  if(Hand.get(0).cardRank=='2'&& Hand.get(4).cardRank=='A'){
        				  matchCounter++;
        			  }
        	  }
        	 }
        	  if(matchCounter==3)
        		  return true;
        	  else return false;
          }
          
          public boolean isStraightFlush(ArrayList<card>Hand,cardPile mypile){
        	  if(isStraight(Hand,mypile)==true && isFlush(Hand)== true)
        		  return true;
        	  else return false;
          }
          
          public String isMatching(ArrayList<card>Hand){
        	  int matchCounter=0;
        	  for(int i=0;i<4;i++){
        		  for(int j=i+1;j<5;j++){
        			  if(Hand.get(i).cardRank==Hand.get(j).cardRank)
        				  matchCounter++;
        		  }
        	  }
        	  if(matchCounter==1) return "KPair";
        	  else if(matchCounter==2) return "KtwoPair";
        	  else if(matchCounter==3) return "KThree";
        	  else if(matchCounter==4) return "KfullHouse";
        	  else if(matchCounter==6) return "KFour";
        	  else return "highCard";
          }
          
          public boolean isAce(ArrayList<card>Hand){
        	    if(Hand.get(0).cardRank=='A')return true;
        	    else return false;
          }
}