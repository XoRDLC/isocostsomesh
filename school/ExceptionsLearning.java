/**
 * 
 */
package school;


/**
 * @author Кляус
 * Классы наследующие ExceptionsLearning - B, B1
 * ПОльзовательская ошибка - MyException
 */


public class ExceptionsLearning  implements Help {
	private int a = 10;
	private int b = 0;
	
	void setPair(int a, int b){this.a = a; this.b = b;}
	void show(){
	
	try {a = a /b;}
	catch(ArithmeticException e){System.out.println("Поделим на 0? " + e);a=-0;}
	finally{System.out.println("Результат деления: " + a);}
	
	}
	
	public void help(){
		System.out.println("Методы: ");
		System.out.println(".setPair\t-\tзадать параметры расчёта a/b.");
		System.out.println(".show \t-\tперехват ошибки и выполнение блока finally для заданных параметров. Показывает только результат.");
	}
}

class B extends ExceptionsLearning {
	
	B(){setPair(1,1);}
	B(int a, int b){setPair(a,b);}
	public void help(){
		super.help();
		System.out.println("Класс В:\t-\tконструктор вызывает метод .getPair. B() с параметрами 1,1");
	}
}

class B1 extends ExceptionsLearning {
	
	void test(){
		try{
			throw new ArithmeticException("Проба");
		}
		catch(ArithmeticException e){
			System.out.println("Ошибка: " +e);
			throw e;
		}
	}

	void test2() throws Exception, MyException{
		
		try{throw new Exception("Проверка finally");}
		finally{System.out.println("Блок finally");
		throw new MyException();}
	}
	
	void test3() throws MyException {
		MyException ttta = new MyException("Верхний уровень");
		ttta.initCause(new ArithmeticException("Причина."));
		throw ttta;
	}
	
	public void help(){
		super.help();
		System.out.println(".test1 \t-\tвызов, перехват ошибки и повтороный вызов из блока catch.");
		System.out.println(".test2 \t-\tблок try с ошибкой Exception и вызов MyException после блока finally.");
		System.out.println(".test3 \t-\tвложенная ошибка. initCause в методе и getCause при вызове");
	}
}

class MyException extends Exception{
	
	MyException(){};
	MyException(String e){
		toString(e);
	}
	void toString(String e){System.out.println("Ошибка: " + e );}
	public String getMessage(){return "My Exception";};
	public void help(){
		System.out.println("Пользовательский класс ошибки. "
				+ "Переопределены методы .toString, getMessage.");
	}
	
}