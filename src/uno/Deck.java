package uno;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList<Card>deck;
	
	///////////////////////////////////////////////////////////////
	
	public Deck() {
		deck=new ArrayList<Card>();
		int[]value_of_normal_card= {0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9};
		int[]value_of_special_card_add2= {2,2};
		String[] color_of_normal_and_special_cards= {"red","yellow","green","blue"};
		String[] value_ofspecial_events_cards= {"stop","swap"};
		
		////////////////////////////////////////////////////////////////
		
		for(String s:color_of_normal_and_special_cards) { ///////////// normal
			for(int i:value_of_normal_card) {
				deck.add(new Card(i,s));
			}
		}
		
		for(String s:color_of_normal_and_special_cards) {  /////////// +2
			for(int i:value_of_special_card_add2) {
				deck.add(new Card(s,i,true));
			}
		}

		for(int i=0;i<4;i++) {                            ///////////// +4
			deck.add(new Card(4,true));
		}
		
		for(int i=0;i<4;i++) {                            ///////////// change color
			deck.add(new Card("Change_color"));
		}
		
		for(String s:color_of_normal_and_special_cards) {  /////////// stop
			for(String ss:value_ofspecial_events_cards) {
				deck.add(new Card(ss,s));
			}
		}
		
		for(String s:color_of_normal_and_special_cards) {  /////////// swap
			for(String ss:value_ofspecial_events_cards) {
				deck.add(new Card(ss,s,-99));
			}
		}
		
		
	}
	
	///////////////////////////////////////////////////////////////
	
	public Deck(ArrayList<Card> c) {        // in case the deck became empty
		deck=c;
	}
	
	//////////////////////////////////////////
	
	public Card TackTopCard() {              // To Take new card BUT this card will be removed
		Card carry=deck.get(0);
		deck.remove(0);
		return carry;
	}
	
	public Card TopCard() {                 // To know what on the Top 
		return deck.get(deck.size()-1);
	}
	
	public boolean isEmpty() {
		if(deck.size()<=0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void shuffle() {               
		Collections.shuffle(deck);
	}
	
}
