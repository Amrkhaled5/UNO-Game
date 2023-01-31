package uno;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		Scanner input =new Scanner (System.in);
		int  numberofplayers,numberofbotes;
		String playwithfreinds;
		System.out.println("Do you want to play with freinds yes or no ");
		do {
			playwithfreinds =input.next();
			if(!playwithfreinds.equals("yes")&&!playwithfreinds.equals("no")) {
				System.out.println("Invalid value try agin ");
			}
		}while(!playwithfreinds.equals("yes")&&!playwithfreinds.equals("no"));
		
		if(playwithfreinds.equals("yes")) {
			
			do {
				System.out.println("Enter The number of Players Max 5");
				 numberofplayers=input.nextInt();
			}while(numberofplayers>5||numberofplayers<=1);
			
			String []names1=new String[numberofplayers];
			
			for(int i=1;i<=numberofplayers;i++) {
				System.out.println("Player number "+i+" enter your name ");
				names1[i-1]=input.next();
			}
			int a=0;
			do {
				do {
				GamePlay game=new GamePlay(numberofplayers,names1);
				game.play();
				}while(GamePlay.finishthegame==0);
				
				System.out.println("play again press 1 for YES or 0 for NO");
				a=input.nextInt();
			}while(a==1);
		}
		else {
			do {
				System.out.println("Enter The number of botes Max 4");
				numberofbotes=input.nextInt();
			}while(numberofbotes>4||numberofbotes<1);
			
			String []names2=new String[numberofbotes+1];
			System.out.println("Enter your name: ");
			names2[0]=input.next();
			String enternameforbotes;
			System.out.println("Do you want to enter name for botes yes or no: ");
			do {
				enternameforbotes=input.next();
				if(!enternameforbotes.equals("yes")&&!enternameforbotes.equals("no")) {
					System.out.println("Invalid value try agin ");
				}
			}while(!enternameforbotes.equals("yes")&&!enternameforbotes.equals("no"));
			
			if(enternameforbotes.equals("no")) {
				String s;
				for(int i=2;i<=numberofbotes+1;i++) {
					s="bote"+(i-1)+"`";
					names2[i-1]=s;
				}
			}
			else {
				for(int i=2;i<=numberofbotes+1;i++) {
					System.out.println("bote number "+(i-1)+" enter his name ");
					names2[i-1]=input.next()+"`";
				}
			}
			int a=0;
			do {
				do {
					GamePlay game=new GamePlay(numberofbotes+1,names2,true);
					game.play();
					}while(GamePlay.finishthegame==0);
				System.out.println("play again press 1 for YES or 0 for NO");
				a=input.nextInt();
			}while(a==1);			
		}				
	}
}
