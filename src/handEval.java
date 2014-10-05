import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;



public class handEval {
	    ArrayList<Integer>pWinners= new ArrayList<Integer>();
	    Set<Integer>tie=new HashSet<Integer>();
	    int winner;
	    int numPlayers;
	    userPlayer mC;
	    ArrayList<opponentPlayer>oPhands= new ArrayList<opponentPlayer>(); 
	    handEval(int numPlayers){
	    	userPlayer mC = new userPlayer();
	    	mC.discardUserHand(mC.userHand, mC);
	    	this.numPlayers=numPlayers;
	    	for(int i=0;i<numPlayers;i++){
	    		opponentPlayer oP =new opponentPlayer(mC.mypile);
	    		oPhands.add(oP);
	    		oPhands.get(i).DiscardOpHand(oP.opponentHand,mC.mypile);
	    		oPhands.get(i).sortHand(oP.opponentHand,mC.mypile);
	    	}
	    	oPhands.get(0).sortHand(mC.userHand,mC.mypile);
	    	System.out.println(mC.mypile.drawPile.size());
	    	System.out.println("The use's hand is determined at this point");
	  	    System.out.println("This is the user Hand");
	  	    mC.printHand(mC.userHand);
	  	    for(int i=0;i<numPlayers;i++){
	  	    	System.out.println("Hello this is the computer's hand");
	  	    	mC.printHand(oPhands.get(i).opponentHand);
	  	    }
	  	    Evaluation(oPhands,mC);
	    }
	    
	    
	    public void Evaluation(ArrayList<opponentPlayer>oPhands,userPlayer mC){
	    	System.out.println("Hello I am inside of evaluation");
	    	ArrayList<ArrayList<card>>evalHands= new ArrayList<ArrayList<card>>();
	    	for(int j=0;j<oPhands.size();j++){
	    	    evalHands.add(oPhands.get(j).opponentHand);
	    	}
	    	evalHands.add(mC.userHand);
	    	// Printing all the hands to evaluate
	    	for(int k=0;k<evalHands.size();k++){
	    		System.out.println(evalHands.get(k));
	    	}
	    	///////////////////////////////////////////////
	    	//Straight flush
	    	for(int i=0;i<evalHands.size();i++){
	    		if(oPhands.get(0).isStraight(evalHands.get(i),mC.mypile) && oPhands.get(0).isFlush(evalHands.get(i))){
	    		    	pWinners.add(i);
	    		}
	    	}
	            if(pWinners.size()==1){
	            	winner=pWinners.get(0)+1;
	            	System.out.println("Player "+ winner+ "Won the game.Thank you for playing");
	            }
	            else if(pWinners.size()>1)
	                   tieBreaker(pWinners,evalHands);
	            
	            /////////////////////////////////////////
	            else{         // Four of a kind
	            	tie.clear();
	            	 for(int i=0;i<evalHands.size();i++){
	            		 if(oPhands.get(0).isMatching(evalHands.get(i)).equals("KFour")){
	            			 pWinners.add(i);
	            		 }
	            	 }
	                 if(pWinners.size()==1)
	                    System.out.println("Player "+pWinners.get(0)+"won the game");
	                 else if(pWinners.size()>1)
	                 tieBreaker(pWinners,evalHands);
	                //////////////////////////////////////////
	                 else{                           // check for fullhouse
	                	 for(int i=0;i<evalHands.size();i++){
	                	 if(oPhands.get(0).isMatching(evalHands.get(i)).equals("KfullHouse"))
	                		 pWinners.add(i);
	                      }
	                	 if(pWinners.size()==1)
	 	                    System.out.println("Player "+pWinners.get(0)+"won the game");
	 	                 else if(pWinners.size()>1)
	 	                 tieBreaker(pWinners,evalHands);
	 	                 else{      /// check for Flush
	 	                	for(int i=0;i<evalHands.size();i++){
	 		                	 if(oPhands.get(0).isFlush(evalHands.get(i)))
	 		                		 pWinners.add(i);
	 		                      }
	 	                	 if(pWinners.size()==1)
	 	 	                    System.out.println("Player "+pWinners.get(0)+"won the game");
	 	 	                 else if(pWinners.size()>1)
	 	 	                 tieBreaker2(pWinners,evalHands,0);
	 	 	                 else{ // Check for straight
	 	 	                	for(int i=0;i<evalHands.size();i++){
		 		                	 if(oPhands.get(0).isStraight(evalHands.get(i),mC.mypile))
		 		                		 pWinners.add(i);
		 		                      }
		 	                	 if(pWinners.size()==1)
		 	 	                    System.out.println("Player "+pWinners.get(0)+"won the game");
		 	 	                 else if(pWinners.size()>1)
		 	 	                 tieBreaker(pWinners,evalHands);
		 	 	                 else{ // Check for three of a kind
		 	 	                	for(int i=0;i<evalHands.size();i++){
			 		                	 if(oPhands.get(0).isMatching(evalHands.get(i)).equals("KThree"))
			 		                		 pWinners.add(i);
			 		                      }
			 	                	 if(pWinners.size()==1)
			 	 	                    System.out.println("Player "+pWinners.get(0)+"won the game");
			 	 	                 else if(pWinners.size()>1)
			 	 	                 tieBreaker(pWinners,evalHands);
			 	 	                 else{ // check for two pair
			 	 	                	 System.out.println("Checking for a 2 pair");
			 	 	                	for(int i=0;i<evalHands.size();i++){
				 		                	 if(oPhands.get(0).isMatching(evalHands.get(i)).equals("KtwoPair"))
				 		                		 pWinners.add(i);
				 		                      }
				 	                	 if(pWinners.size()==1)
				 	 	                    System.out.println("Player "+pWinners.get(0)+"won the game");
				 	 	                 else if(pWinners.size()>1)
				 	 	                 tieBreaker2(pWinners,evalHands,0);
				 	 	                 else{// check for a pair
				 	 	                	 System.out.println("Checking for a pair");
				 	 	                	 System.out.println("This is the hand size"+evalHands.size());
				 	 	                	for(int i=0;i<evalHands.size();i++){
					 		                	 if(oPhands.get(0).isMatching(evalHands.get(i)).equals("KPair"))
					 		                		 pWinners.add(i);
					 		                      }
					 	                	 if(pWinners.size()==1)
					 	 	                    System.out.println("Player "+pWinners.get(0)+"won the game");
					 	 	                 else if(pWinners.size()>1)
					 	 	                 tieBreaker2(pWinners,evalHands,0);
					 	 	                 else{ //Check for high card
					 	 	                	 System.out.println("checking for a HighCard");
						 	 	                 tieBreaker2(pWinners,evalHands,0);
					 	 	                 }
				 	 	                 }
			 	 	                 }
		 	 	                 }
	 	 	                 }
	 	                 }
	                   }
	                	 
	                }
	            }
	    
	    
	    
	    public void tieBreaker(ArrayList<Integer>pWineers,ArrayList<ArrayList<card>>evalHands){
	    	int highCard=0;
        	int index1=0;
        	int index2=0;
	    	for(int i=0;i<pWinners.size()-1;i++){
	    		for(int j=i+1;j<pWinners.size();j++){
            	   for(int k=0;k<13;k++){
            		 if(evalHands.get(pWinners.get(i)).get(0).cardRank==mC.mypile.Rank[k]){
            			 index1=k;
            		 }
            	     if(evalHands.get(pWinners.get(j)).get(0).cardRank==mC.mypile.Rank[k]){
            	    	 index2=k;
            	     }
        	   }
            	if(index1>index2 && index1>highCard)
            		highCard=index1;
            	 else if(index2>index1 && index2>highCard)
            		highCard=index2;
            	 else if(index1==index2 && index1>=highCard){
            		 tie.add(i);
            		 tie.add(j);
            	 }
            }
        }
	    	if(tie.isEmpty()){ // finding to which player the high card belongs
        		for(int i=0;i<evalHands.size();i++){
        			if(evalHands.get(i).get(0).cardRank==mC.mypile.Rank[highCard]){
        				winner=i;
        				System.out.println("Player "+i+"won the game. Thank you for playing");
        		}
        		}
	    	}
	    	else{ // The is a tie
	    		winner=10;
   			 Iterator iter= tie.iterator();
   			 while(iter.hasNext()){
   				 System.out.println("Players: "+iter.next()+"tied in this game");
   			 }
   	     }
	    }
	    
	    
	    
	    public void tieBreaker2(ArrayList<Integer>pWineers,ArrayList<ArrayList<card>>evalHands,int index){
	    	int highCard=0;
        	int index1=0;
        	int index2=0;
        	
        	if(index==5){
        		winner=10;
      			 Iterator iter= pWinners.iterator();
      			 while(iter.hasNext()){
      				 System.out.println("Players: "+iter.next()+"tied in this game");
        	 }
      			 return;
        	}
        	
        	
	    	for(int i=0;i<pWinners.size()-1;i++){
	    		for(int j=i+1;j<pWinners.size();j++){
            	   for(int k=0;k<13;k++){
            		 if(evalHands.get(pWinners.get(i)).get(index).cardRank==mC.mypile.Rank[k]){
            			 index1=k;
            		 }
            	     if(evalHands.get(pWinners.get(j)).get(index).cardRank==mC.mypile.Rank[k]){
            	    	 index2=k;
            	     }
        	   }
            	if(index1>index2 && index1>highCard)
            		highCard=index1;
            	 else if(index2>index1 && index2>highCard)
            		highCard=index2;
            	 else if(index1==index2 && index1>=highCard){
            		 tie.add(i);
            		 tie.add(j);
            	 }
            }
          }
	    	if(tie.isEmpty()){ // finding to which player the high card belongs
        		for(int i=0;i<evalHands.size();i++){
        			if(evalHands.get(i).get(0).cardRank==mC.mypile.Rank[highCard]){
        				winner=i;
        				System.out.println("Player "+i+"won the game.");
        		}
        		}
	    	}
	    	else{
	    	pWinners.clear();
	    	pWinners.addAll(tie);
	    	tie.clear();
	   	    Iterator iter= pWinners.iterator();
			 while(iter.hasNext()){
				 System.out.println("Players: "+iter.next()+"tied in this game");
	        }
	    	tieBreaker2(pWinners,evalHands,index+1);
	    	}
        }
		public static void main(String [] args){
			int numberPlayers;
			System.out.println("Against how many players do you want to play?");
			Scanner in = new Scanner(System.in);
			numberPlayers= Integer.parseInt(in.nextLine());
			handEval hE= new handEval(numberPlayers);
			System.out.println("Thank you for playing");
      }
}
