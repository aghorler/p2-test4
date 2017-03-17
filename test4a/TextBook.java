/* Aaron Horler - s3481341 */

package test4a;

public class TextBook{
	private String title, author;
	private int year;
	private double price;
	
	public TextBook(String title, String author, int year, double price){
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}
	
	public int getYear(){
		return year;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String toString(){
		return title + ". By " + author + ". Year: " + year + ". Price: " + price;
	}
}
