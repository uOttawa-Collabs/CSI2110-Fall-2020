/*  CSI2114 Lab 3 - lab3.java
 *
 *  Class to check balanced brackets in math expressions
 *
 *  Usage: java bracketsBalance <exp>
 *
 *  by Jeff Souza
 *
 */

class bracketsBalance
{
    public static void main(String[] args)
    {
        bracketsBalance b      = new bracketsBalance();
        boolean         result = b.bBalance(args[0]);
        
        if (result) System.out.println("The expression is balanced.");
        else System.out.println("The expression is NOT balanced.");
    }
    
    private boolean bBalance(String exp)
    {
        // INCLUDE YOUR CODE HERE
        final String leftBrackets = "{[(";
        final String rightBrackets = "}])";
       
        Stack stack = new ArrayStack();
        
        try
        {
            for (int i = 0; i < exp.length(); ++i)
            {
                char c = exp.charAt(i);
                if (leftBrackets.indexOf(c) != -1)
                    stack.push(c);
                else if (rightBrackets.indexOf(c) != -1)
                {
                    char l = (char) stack.pop();
                    if (leftBrackets.charAt(rightBrackets.indexOf(c)) != l)
                        return false;
                }
            }
        }
        catch (StackEmptyException e)
        {
            return false;
        }
    
        return stack.isEmpty();
    }
}
