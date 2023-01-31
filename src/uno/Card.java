package uno;

public class Card {
	private int value;
	private String color;
	private boolean special;
	private int specialvalue;
	private String specialevent;
	//////////////////////////////////////////////////////////////////////////////
	
	public Card(int value, String color) { //for normal cards 0 11 22 33 44 55 66 77 88 99
		this.value = value;
		this.color = color;
		special=false;
		specialvalue=-1;
	}
	
	
	public Card(String color, int specialvalue, boolean special) { //for +2
		this.color = color;
		this.special = special;
		this.specialvalue = specialvalue;
		value=22;
		specialevent="";
	}
	
	
	public Card(int specialvalue, boolean special) { //for +4
		this.specialvalue = specialvalue;
		this.special=special;
		color="";
		value=44;
		specialevent="";
	}
	
	
	public Card(String specialevent) { //for change color
		this.specialevent = specialevent;
		specialvalue = -2;
		special=true;
		color="";
		value=-4;
	}
	
	
	public Card(String specialevent,String color,int value) { //for swap 
		this.specialevent = specialevent;
		this.color=color;
		this.value=value;
		specialvalue = 0;
		special=true;
	}
	
	public Card(String specialevent,String color) { //for stop 
		this.specialevent = specialevent;
		this.color=color;
		value=-100;
		specialvalue = 0;
		special=true;
	}
	
    ///////////////////////////////////////////////////////////////////////////////////
	
	public int getValue() {
		return value;
	}
	
	public void setvalue(int v) {
			value=v;
	}

	public String getColor() {
		return color;
	}
	
	public void setcolor(String c) {
		color=c;
	}
	
	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special=special;
	} 
	
	
	public int getSpecialvalue() {
		return specialvalue;
	}

	public void setSpecialvalue(int x) {
		specialvalue=x;
	}
	
	
	
	public String getSpecialevent() {
		return specialevent;
	}

	public void setSpecialevent(String c) {
		specialevent=c;
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public String toString() {
		return "[ "+this.value+" - "+this.color+" ]";
	}
	
	
}
