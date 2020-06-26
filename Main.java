import java.time.*;

class Main {
    public static void main(String... args){
    	byte b = 1;
    	short s = 1;
    	int i = b + s;
    	long l = 1L;
    	float f = 1f;
    	double d = i;
    	char c = '\u0000';
    	boolean bool = (s > 1);
    }

    float calculateExpression(float a, float b, float c, float d){
    	float result = 0;

    	if (d == 0) {
    		System.err.println("d must be not 0");
    	} else {
    		result = a*(b + (c/d));	
    	}  

    	return result;	
    }


    boolean isSumBetween10And20(int num1, int num2){
    	int sum = num1 + num2;
    	return sum > 10 && sum <= 20;
    }

    void printPositiveOrNegative(int num){
    	String s = (num >= 0) ? "positive" : "negative";
    	System.out.println(s);
    }

    boolean isNegative(int num){
    	return num < 0;
    }

    void printHelloName(String name){
    	System.out.printf("Hello, %s", name);
    }

    void printLeapYear(int year){
    	String s = (year % 4 == 0 && year % 100 != 0 && year % 400 == 0) ? "leap" : "not leap";

    	System.out.println(s);
    }

    void printLeapYear(String date){
    	String s = LocalDate.parse(date).isLeapYear() ? "leap" : "not leap";

    	System.out.println(s);
    }
}
