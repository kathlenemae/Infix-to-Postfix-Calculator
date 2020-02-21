import java.util.*;

public class PostFixCalculator{

  private static String expression;
  private Stack<String> stack = new Stack<String>();
  
  public String[] tokenize(){
   String [] tokens=null;
   if(expression != null){
    tokens = expression.split(" ");
   }
   return tokens;
  }
  
  public boolean isOperand(String str){
   try{
    Integer.parseInt(str);
   }
   catch(Exception e){
    return false;
   }
   return true;
  }
  
  
  public int calculate(){
  //do the calculation here
 String [] tokens = tokenize();
   for(String token:tokens){
   if(isOperand(token))
      stack.push(token);
   else{
      int num2 = Integer.parseInt(stack.pop());
      int num1 = Integer.parseInt(stack.pop());
      
      if(token.equals("+"))
         stack.push("" + (num1+num2));
      if(token.equals("-"))
         stack.push("" + (num1-num2));
      if(token.equals("*"))
         stack.push("" + (num1*num2));
       if(token.equals("/"))
         stack.push("" + (num1/num2));
  
  }
  }
   return Integer.parseInt(stack.pop());
  }
  
static int precedence(char c){
      switch(c){
       case '+':
       case '-':
       return 1;
       case '*':
       case '/':
       return 2;
       case '^':
       return 3;
       
       }
       return -1;
       }
       
       static String infixToPostFix(String expression){
       String result = "";
       Stack<Character> stack = new Stack<>();
       for (int i = 0; i <expression.length() ; i++) {
       char c = expression.charAt(i);
       
       // check if char is an operator
       if(precedence(c)>0){
       while(stack.isEmpty()==false && precedence(stack.peek())>=precedence(c)){
       result += stack.pop() + " ";
       
       }
       
       stack.push(c);
       }else if(c==')'){
       char x = stack.pop();
       while(x!='('){
       result += x + " ";
       x = stack.pop();
       
       }
       }else if (c=='('){
       stack.push(c);
       }else {
       //character is  neither operation or(
       
       result += c + " ";
       }
       
       }
       for (int i = 0; i <=stack.size() ; i++){
         result += stack.pop() + " ";
         }
         
         return result;
         }

  public void push(){
   String tokens [] = tokenize();
   if(tokens != null){
   for(String token:tokens)
    stack.push(token);
   }
  }
  
  public void displayElements(){
  if(expression != null){
   while(!stack.isEmpty()){
    String item = stack.pop();
    System.out.println(item + "\t" + (isOperand(item)?"operand":"not operand"));
   }
  }
  }
  
  
  public static void main(String [] args){
  Scanner sc = new Scanner(System.in);
   PostFixCalculator calc = new PostFixCalculator();
   System.out.println("\nPlease enter an infix expression");
   calc.expression = sc.nextLine();
   System.out.println("Infix Expression : " +expression);
   System.out.println("Postfix Expression : " + infixToPostFix(expression));
   calc.expression = infixToPostFix(expression);
   calc.push();
   calc.displayElements();
   System.out.println("The result is : " + calc.calculate());

  
  }
}