/**
 * 
 */
package school;


/**
 * @author �����
 * ������ ����������� ExceptionsLearning - B, B1
 * ���������������� ������ - MyException
 */


public class ExceptionsLearning  implements Help {
	private int a = 10;
	private int b = 0;
	
	void setPair(int a, int b){this.a = a; this.b = b;}
	void show(){
	
	try {a = a /b;}
	catch(ArithmeticException e){System.out.println("������� �� 0? " + e);a=-0;}
	finally{System.out.println("��������� �������: " + a);}
	
	}
	
	public void help(){
		System.out.println("������: ");
		System.out.println(".setPair\t-\t������ ��������� ������� a/b.");
		System.out.println(".show \t-\t�������� ������ � ���������� ����� finally ��� �������� ����������. ���������� ������ ���������.");
	}
}

class B extends ExceptionsLearning {
	
	B(){setPair(1,1);}
	B(int a, int b){setPair(a,b);}
	public void help(){
		super.help();
		System.out.println("����� �:\t-\t����������� �������� ����� .getPair. B() � ����������� 1,1");
	}
}

class B1 extends ExceptionsLearning {
	
	void test(){
		try{
			throw new ArithmeticException("�����");
		}
		catch(ArithmeticException e){
			System.out.println("������: " +e);
			throw e;
		}
	}

	void test2() throws Exception, MyException{
		
		try{throw new Exception("�������� finally");}
		finally{System.out.println("���� finally");
		throw new MyException();}
	}
	
	void test3() throws MyException {
		MyException ttta = new MyException("������� �������");
		ttta.initCause(new ArithmeticException("�������."));
		throw ttta;
	}
	
	public void help(){
		super.help();
		System.out.println(".test1 \t-\t�����, �������� ������ � ���������� ����� �� ����� catch.");
		System.out.println(".test2 \t-\t���� try � ������� Exception � ����� MyException ����� ����� finally.");
		System.out.println(".test3 \t-\t��������� ������. initCause � ������ � getCause ��� ������");
	}
}

class MyException extends Exception{
	
	MyException(){};
	MyException(String e){
		toString(e);
	}
	void toString(String e){System.out.println("������: " + e );}
	public String getMessage(){return "My Exception";};
	public void help(){
		System.out.println("���������������� ����� ������. "
				+ "�������������� ������ .toString, getMessage.");
	}
	
}