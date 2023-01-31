package uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GamePlay {
	Scanner input=new Scanner(System.in);
	Random rand=new Random();
	private Deck deck;
	private Card card;   //TopCard
	private Player []player;
	private String []playername;
	private int numberofplayers;
	private ArrayList<Card> cardsingame;
	private int swap=1;  // to manage the game will be clockwise or anti clockwise
	private final int maxscore=40;   // the game will finish when someone have this score 
	private String[] colorsforbotes= {"red","yellow","green","blue"};  
	private static boolean playwithbotes=false;
	public static int finishthegame;     //know if any player has the score or more
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public GamePlay(int numberofplayers,String []playername) {
		
		this.numberofplayers=numberofplayers;
		this.playername=playername;
		deck=new Deck();
		deck.shuffle();
		cardsingame=new ArrayList<Card>();
		
		player=new Player[numberofplayers];
		for(int i=0;i<numberofplayers;i++) {
			player[i]=new Player(playername[i]);
		}
		
		card=deck.TackTopCard();
		while(card.isSpecial()) {    // first card can't be spical card (+2,+4,change color, swap , stop)
			deck.shuffle();
			card=deck.TackTopCard();
		}
		
		cardsingame.add(card);
		givecards();
	}
	
	public GamePlay(int numberofplayers,String []playername,boolean b) {    // if you play with bote
		this(numberofplayers,playername);
		playwithbotes=b;
	}
	
	public void givecards() {                         // you can chose any number of cards to give
		for(int i=0;i<numberofplayers;i++) {
			for(int j=0;j<6;j++) {
				player[i].addcard(deck.TackTopCard());
			}
		}
	}
	
	public Boolean sameColor(Player p) {
		for(Card c:p.MyCards()) {
			if(c.getColor().equals(card.getColor())) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean samevalue(Player p) {
		for(Card c:p.MyCards()) {
			if(c.getValue()==card.getValue()) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean HasSpecialCard(Player p) {
		for(Card c:p.MyCards()) {
			if(c.isSpecial()&&(c.getValue()!=44||c.getValue()!=-4)) {
				if(card.getColor().equals(c.getColor())&&card.getValue()==c.getValue()) {
					return true;
				}
			}
			if(c.isSpecial()&&(c.getValue()==44||c.getValue()==-4)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean checkvalidathion(Player p,int n) {
		if(n>=0&&n<p.MyCards().size()) {
			if(p.MyCards().get(n).getValue()==44) {
				System.out.println("choose color : ");
				String color=input.next();
				p.MyCards().get(n).setcolor(color);
			}
			else if(p.MyCards().get(n).getValue()==-4) {
				System.out.println("choose color : ");
				String color=input.next();
				p.MyCards().get(n).setcolor(color);
			}
			if(p.MyCards().get(n).getColor().equals(card.getColor())||
					p.MyCards().get(n).getValue()==card.getValue()||
					(p.MyCards().get(n).isSpecial()&&p.MyCards().get(n).getValue()==44)||
					(p.MyCards().get(n).isSpecial()&&p.MyCards().get(n).getValue()==-4)||
					(p.MyCards().get(n).isSpecial()&&p.MyCards().get(n).getValue()==22&&p.MyCards().get(n).getColor().equals(card.getColor()))
					) {
				return true;
			}
		}
		return false;
	}
	
	public void ingame(Player p){
		
		System.out.println("/////////////////////////////////////////");
		System.out.println(p.toString()+"  Top card is:"+card.toString());
		System.out.println("Score: "+p.getscore());
		
		if(card.isSpecial()&&card.getSpecialvalue()==2) {   //+2
			System.out.println("  +2 cards ");
			for(int i=0;i<card.getSpecialvalue();i++) {	
				if(deck.isEmpty()) {
					deck=new Deck();
					deck.shuffle();
				}
				p.addcard(deck.TackTopCard());
			}
			p.showmycards();
			card.setSpecial(false);
		}
		
		else if(card.getValue()==-100&&card.getSpecialvalue()==0) {  //stop
			System.out.println("Stop");
			card.setSpecialvalue(13);
		}
		
		else if(card.isSpecial()&&card.getSpecialvalue()==4) {   //+4
			System.out.println("You will take 4 cards ");
			for(int i=0;i<card.getSpecialvalue();i++) {	
				if(deck.isEmpty()) {
					deck=new Deck();
					deck.shuffle();
				}
				p.addcard(deck.TackTopCard());
			}
			card.setSpecial(false);
			p.showmycards();
		}
		
		else {
			while(sameColor(p)==false&&samevalue(p)==false&&HasSpecialCard(p)==false){
				if(deck.isEmpty()) {
						deck=new Deck();
						deck.shuffle();
					}
				System.out.println("no valid card take one ");
				p.addcard(deck.TackTopCard());
			}
			p.showmycards();
			System.out.println(" chose your card ");
			int i = -1;
			String nameofplayer=p.getname();
			
			if(nameofplayer.charAt(nameofplayer.length()-1)=='`') {     // for botes
				
		        try {
					Thread.sleep(5000);            // to make the bote think
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		     
				boolean bestcard=false;
				for(int j=1;j<=p.MyCards().size();j++) {
					if(p.MyCards().get(j-1).isSpecial()&&(p.MyCards().get(j-1).getValue()==44)) {
						String cc1=colorsforbotes[rand.nextInt(4)];
						p.MyCards().get(j-1).setcolor(cc1);
						System.out.println(j);
						 try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						System.out.println("my color is: "+cc1);
						bestcard=true;
						i=j;
						break;
					}
				}
				if(!bestcard) {
					for(int j=1;j<=p.MyCards().size();j++) {
						if(p.MyCards().get(j-1).isSpecial()&&(p.MyCards().get(j-1).getValue()==22&&p.MyCards().get(j-1).getColor().equals(card.getColor()))) {
							bestcard=true;
							System.out.println(j);
							i=j;
							break;
						}
					}
				}
				if(!bestcard) {
					for(int j=1;j<=p.MyCards().size();j++) {
						if(p.MyCards().get(j-1).isSpecial()&&(p.MyCards().get(j-1).getValue()==-100&&(p.MyCards().get(j-1).getColor().equals(card.getColor())||
								p.MyCards().get(j-1).getValue()==card.getValue()))) {
							bestcard=true;
							System.out.println(j);
							i=j;
							break;
						}
					}
				}
				if(!bestcard) {
					for(int j=1;j<=p.MyCards().size();j++) {
						if(p.MyCards().get(j-1).isSpecial()&&(p.MyCards().get(j-1).getValue()==-99&&(p.MyCards().get(j-1).getColor().equals(card.getColor())||
								p.MyCards().get(j-1).getValue()==card.getValue()))) {
							bestcard=true;
							System.out.println(j);
							i=j;
							break;
						}
					}
				}
				if(!bestcard) {
					for(int j=1;j<=p.MyCards().size();j++) {
						if(p.MyCards().get(j-1).isSpecial()&&p.MyCards().get(j-1).getValue()==-4) {
							String cc=colorsforbotes[rand.nextInt(4)];
							p.MyCards().get(j-1).setcolor(cc);
							 try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							System.out.println("my color is: "+cc);
							bestcard=true;
							System.out.println(j);
							i=j;
							break;
						}
					}
				}
				if(!bestcard) {
					for(int j=1;j<=p.MyCards().size();j++) {
						if(p.MyCards().get(j-1).getValue()==card.getValue()||p.MyCards().get(j-1).getColor().equals(card.getColor())) {
							bestcard=true;
							System.out.println(j);
							i=j;
							break;
						}
					}
				}
				
			}
			else {
				i=input.nextInt();
				while(!checkvalidathion(p, i-1)) {
					System.out.println(" chose another card ");
					i=input.nextInt();
				}
				
			}
			
			card=p.playcard(i-1);
			
			if(card.getValue()==-99) {
				swap*=-1;
			}
			
		}
	}
	
	public int gameover(Player []p) {
		int s=0;
		for(int i=0;i<numberofplayers;i++) {
			if(p[i].Won()) {
				for(int j=0;j<numberofplayers;j++) {
					if(i!=j) {
						for(int h=1;h<=p[j].MyCards().size();h++) {
							if(!p[j].MyCards().get(h-1).isSpecial()) {
								s+=p[j].MyCards().get(h-1).getValue();
								p[i].setscore(p[i].getscore()+s);
							}
							else {
								s+=10;
								p[i].setscore(p[i].getscore()+s);
							}
						}
					}
				}
				if(p[i].getscore()>=maxscore) {
					System.out.println(p[i].toString()+" Win "+p[i].getscore());
					finishthegame=1;
					return 1;
				}
				else {
					finishthegame=0;
					return 2;
				}
			}
		}
		return 0;
	}
	

	
	public void play() {
		int turn=-1;
		while( gameover(player)==0) {
			if(swap==1) {
				turn++;
				if(turn==numberofplayers) {
					turn=0;
				}
				ingame(player[turn]);
			}
			else if(swap==-1) {
				turn--;
				 if(turn==-1) {
						turn =numberofplayers-1;
					}		 
				 ingame(player[turn]);
				
			}
		}
	}
	
}
