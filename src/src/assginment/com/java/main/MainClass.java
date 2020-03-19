package assginment.com.java.main;

import java.util.Scanner;

public class MainClass {
	static Coordinate head=null;
	private static void createCoordinateList(int size,Scanner scan){
		Coordinate next;
		Coordinate prev=null;
		for(int i=0;i<size;i++) {
			next=new Coordinate();
			System.out.println("Enter "+ (i+1)+" coordinate:");
			next.setValue((int) scan.nextLong());
			if(i==0) {
				head = prev=next;
			}else {
				prev.setNext(next);	
				prev=next;
			}
		}
	}
	private static void processCoordinates(Coordinate head,int threshold,boolean isUseMin){
		Coordinate prev=null;
		Coordinate temp= head;
		while(temp != null) {
			if(null != temp.getNext()) {
				int a=temp.getValue();
				int b=temp.getNext().getValue();
				if(b-a<=threshold){
					if(isUseMin) {
						if(null != temp.getNext())
							temp.setNext(temp.getNext().getNext());
					}else {
						if(head==temp) {
							head=temp.getNext();
							prev=head;
							temp=temp.getNext();
						}else {
							prev.setNext(temp.getNext());
							temp=temp.getNext();
							prev= temp;
						}
					}
				}
				temp=temp.getNext();
			}else {
				temp=null;
			}
		}
	}
	private static void printCoordinates(Coordinate temp) {
		System.out.print("Results: [");
		while(temp != null) {
			if(null!=temp.getNext())
				System.out.print(temp.getValue()+",");
			else
				System.out.print(temp.getValue());
			temp=temp.getNext();
		}
		System.out.print("]");
	}
	public static void main(String[] args) {
		System.out.println("How many coordinates you need to enter?");
		Scanner scan=new Scanner(System.in);
		int size=(int) scan.nextLong();
		createCoordinateList(size,scan);
		System.out.println("Enter threshold value :");
		int threshold=(int) scan.nextLong();
		System.out.println("Use smallest or largest value.Enter 0-(smallest) or 1-(largest) :");
		int minOrmax=(int) scan.nextLong();
		boolean isUseMin=true;
		if(minOrmax==1)
			isUseMin=false;
		processCoordinates(head,threshold,isUseMin);
		printCoordinates(head);
	}
	
}
