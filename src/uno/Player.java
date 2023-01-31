package uno;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private int score;
	private ArrayList<Card> playercard;
	public Player(String name) {
		this.name = name;
		playercard=new ArrayList<Card>();
	}
	
	/////////////////////////////////////////////////
	
	public String getname() {
		return name;
	}
	
	public int getscore() {
		return score;
	}
	
	public void setscore(int s) {
		score=s;
	}
	
	public void addcard(Card c) {
		playercard.add(c);
	}
	
	public Card playcard(int i) {
		return playercard.remove(i);
	}
	
	public int numberofcardsplayerhas() {
		return playercard.size();
	}
	
	public ArrayList<Card> MyCards() {
		return playercard;
	}
	
	public String toString() {
		return this.name;
	}
	
	public boolean Won() {
		if(playercard.size()==0) {
			return true;
		}
		return false;
	}

	
	public void showmycards() {           //show player cards
		String s="";
		for(int i=0;i<playercard.size();i++) {
			s+="| "+playercard.get(i).getValue()+" -- "+playercard.get(i).getColor()+" -- "+"|"+"("+(i+1)+")  ";
		}
		System.out.println(s);
	}
	
	// need to hide cards but it will be good in gui
}
