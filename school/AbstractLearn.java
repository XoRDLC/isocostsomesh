package school;
import static java.lang.System.out;


public abstract class AbstractLearn  implements Help{
	
	abstract void area();
	abstract void height();
	
	void start(String s, boolean bShow){if(bShow) out.println("Действие: " + s);}	
}

class A extends AbstractLearn {
	
	private double height, width, depth, temp;
	private boolean bShow = false;
	A(){
		this.height = 10;
		this.width = 10;
		this.depth = 10;		
	}
	
	A(double height, double width, double depth){
		if (height + width <= depth || height + depth <=width || depth + width <=height) 
			{System.out.println("Сумма двух сторон должна быть больше 3-й");}
		if (height<=0||width<=0||depth<=0)
			{System.out.println("Длина сторон должна быть больше нуля");}
		
		this.height = height;
		this.width = width;
		this.depth = depth;
	}
	public void help(){
		System.out.println("Методы:");
		System.out.println(".debug()\t-\tВыводить названия расчтётов при вызове метода. Реализовано в классе А.");
		System.out.println(".area() \t-\tПлощадь треугольника.");
		System.out.println(".height()\t-\tВысота треугольника к 1-му параметру." );
	}
	
	void debug(){this.bShow = true;}
	
	void area() {
		start("Площадь треугольника.", bShow);
		temp = (height+width+depth)/2;
		out.println(Math.sqrt(temp*(temp-height)*(temp-width)*(temp-depth)));
	}

	void height() {
		start("Высота треугольника.", bShow);
		temp = (height+width+depth)/2;
		out.println(2*Math.sqrt(temp*(temp-height)*(temp-width)*(temp-depth))/height);
	}
	
	
}


