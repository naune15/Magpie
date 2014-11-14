import java.util.Random;
/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk.";
  }
  
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  public String getResponse(String statement)
  {
    String response = "";
    if (statement.trim().length() <= 0)
    {
      response = ("Truly, riveting conversation we're having.");
    }
    else if (findKeyword(statement, "the", 0) >= 0)
    {
      response = "Please, continue talking. This is very interesting.";
    }
    else if (findKeyword(statement, "no", 0) >= 0)
    {
      response = "Why so negative?";
    }
    else if (findKeyword(statement, "name", 0) >= 0)
    {
      response = "I'm no good at remembering names.";
    }
    else if (findKeyword(statement, "mother", 0) >= 0
               || findKeyword(statement, "father", 0) >= 0
               || findKeyword(statement, "sister", 0) >= 0
               || findKeyword(statement, "brother", 0) >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (findKeyword(statement, "cat", 0) >= 0 || (findKeyword(statement, "cats", 0) >= 0)
               || (findKeyword(statement, "dog", 0) >=0) || (findKeyword(statement, "dogs", 0) >= 0))
    {
      response = "Tell me more about your pets.";
    }
    else if (findKeyword(statement, "Kiang", 0) >= 0
               || (findKeyword(statement, "Landgraaf", 0) >= 0))
    {
      response = "You know, that's a pretty nifty teacher you got there.";
    } 
    else if (findKeyword(statement, "I want", 0) >= 0)
    {
      if (findKeyword(statement, "I want to", 0) >= 0)
      {
        response = transformIWantToStatement(statement);
      }
      else response = transformIWantStatement(statement);
    }
    else if (findKeyword(statement, "I was", 0) >= 0)
    {
      response = transformIWasStatement(statement); //method turning "I was" + rest to "Why were you" + rest
    }
    else if (findKeyword(statement, "Are you", 0) >= 0)
    {
      response = transformAreYouStatement(statement); //method turns "Are you" + question into "Why do you think I am" + question
    }
    else if (findKeyword(statement, "Do you", 0) >= 0)
    {
      response = transformDoYouStatement(statement); //method turns "Do you" + question into Why do you think I + question
    }
    else if (findKeyword(statement, "is", 0) >= 0)
    {
      response = transformSomethingIsStatement(statement);
    }
    else if (findKeyword(statement, "I am", 0) >= 0)
    {
      response = transformIAmStatement(statement);
    }
    else if (findKeyword(statement, "You are", 0) >= 0)
    {
      response = transformYouAreStatement(statement);
    }
    else if (findKeyword(statement, "I think", 0) >= 0)
    {
      response = transformIThinkStatement(statement);
    }
    else if (findKeyword(statement, "are", 0) >= 0)
    {
      response = transformSomethingsAreStatement(statement);
    }
    else if (findKeyword(statement, "I like", 0) >= 0)
    {
      response = transformILikeStatement(statement);
    }
    
    else
    {
      
      // Look for a two word (you <something> me)
      // pattern
      int psn = findKeyword(statement, "you", 0);
      
      if (psn >= 0
            && findKeyword(statement, "me", psn) >= 0)
      {
        response = transformYouMeStatement(statement);
      }
      else
      {
        //  Part of student solution
        // Look for a two word (I <something> you)
        // pattern
        psn = findKeyword(statement, "i", 0);
        
        if (psn >= 0
              && findKeyword(statement, "you", psn) >= 0)
        {
          response = transformIYouStatement(statement);
        }
        else
        {
          response = getRandomResponse();
        }
      }
    }
    return response;
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  
   private String transformYouAreStatement(String statement)
  {
    statement = statement.trim(); //remove period if there is one
    statement = statement.toLowerCase();
    String lastChar = statement.substring(statement.length() - 1);
  if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfYouAre = findKeyword(statement, "you are", 0);
    String restOfStatement = statement.substring(psnOfYouAre + 8); //find restofstatement after you are
    return "Why am I " + restOfStatement + "?"; //return "Why am I " + rest with question mark 
  } 
  
  private String transformILikeStatement(String statement)
  {
    statement = statement.trim(); //remove period if there is one
    statement = statement.toLowerCase();
    String lastChar = statement.substring(statement.length() - 1);
  if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfILike = findKeyword(statement, "I like", 0);
    String restOfStatement = statement.substring(psnOfILike + 7); //find restofstatement after I was
    return "Why do you like " + restOfStatement + "?"; //return "Why do you like " + rest with question mark 
  } 
  
    private String transformIThinkStatement(String statement)
  {
    statement = statement.trim(); //remove period if there is one
    statement = statement.toLowerCase();
    String lastChar = statement.substring(statement.length() - 1);
  if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfIThink = findKeyword(statement, "I think", 0);
    String restOfStatement = statement.substring(psnOfIThink + 8); //find restofstatement after I was
    return "Why do you think " + restOfStatement + "?"; //return "Why were you " + rest with question mark 
  } 
  
  private String transformSomethingsAreStatement(String statement)
  {
    statement = statement.trim(); //trim the statement
    statement = statement.toLowerCase(); //lowercase the statement
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfAre = findKeyword(statement, "are", 0);
    String beforeAre = statement.substring(0, psnOfAre);
    String afterAre = statement.substring(psnOfAre + 4, statement.length());
    return "Why are " + beforeAre + afterAre + "?";
  }
  
  private String transformIAmStatement(String statement)
  {
    statement = statement.trim(); //trim the statement
    statement = statement.toLowerCase(); //lowercase the statement
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfIAm = findKeyword(statement, "I am", 0);
    String restOfStatement = statement.substring(psnOfIAm + 5);
    return "Why are you " + restOfStatement + "?";
  }
  
   private String transformIWasStatement(String statement)
  {
    statement = statement.trim(); //remove period if there is one
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfWas = findKeyword(statement, "was", 0);
    String restOfStatement = statement.substring(psnOfWas + 4); //find restofstatement after I was
    return "Why were you " + restOfStatement + "?"; //return "Why were you " + rest with question mark 
  } 
  
  private String transformSomethingIsStatement(String statement)
  {
    statement = statement.trim(); //trim statement
    statement = statement.toLowerCase(); //make lowercase
    String lastChar = statement.substring(statement.length() - 1); //remove period or question mark or exclamation point if there is one
    if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!"))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfIs = findKeyword(statement, "is", 0); //find position of is
    String beforeIs = statement.substring(0, psnOfIs); //take everything before is
    String afterIs = statement.substring(psnOfIs + 3, statement.length()); //take everything after is
    return "Why is " + beforeIs + afterIs + "?";
  }
  
  private String transformIWantStatement(String statement)
  {
    statement = statement.trim(); //trim the statement
    String lastChar = statement.substring(statement.length() - 1); //if there is a period, remove it
    if (lastChar.equals("."))
    {
      statement = statement.substring(statement.length() - 1);
    }
    int psnOfI = findKeyword(statement, "I", 0); //find where I is
    String restOfStatement = statement.substring(psnOfI + 1).trim(); //restofstatement should be statement minus "I"
    return "Why do you " + restOfStatement + "?"; //return "Why do you want" + restofstatement
  }
  private String transformAreYouStatement(String statement)
  {
    statement = statement.trim(); //trim
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))//remove question mark or period if there is one
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    if (lastChar.equals("?"))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfAreYou = findKeyword(statement, "Are you", 0);
    String restOfStatement = statement.substring(psnOfAreYou + 8);//remove the Are you part of the statement
    return "Why would you think I am " + restOfStatement + "?";//return Why do you think + restofstatement
  }
  
    private String transformDoYouStatement(String statement)
  {
    statement = statement.trim(); //trim
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))//remove question mark or period if there is one
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    if (lastChar.equals("?"))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfAreYou = findKeyword(statement, "Do you", 0);
    String restOfStatement = statement.substring(psnOfAreYou + 7);//remove the Are you part of the statement
    return "Why would you think I " + restOfStatement + "?";//return Why do you think + restofstatement
    }
  
  private String transformYouMeStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psnOfYou = findKeyword (statement, "you", 0);
    int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
    
    String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
    return "What makes you think that I " + restOfStatement + " you?";
  }
  private String transformIYouStatement(String statement)
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfI = findKeyword (statement, "I", 0);
    int psnOfYou = findKeyword (statement, "you", psnOfI + 1);
    String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
    return "Why do you " + restOfStatement + " me?";
  }
  
  private String transformIWantToStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement
                                            .length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement
                                        .length() - 1);
    }
    int psn = findKeyword (statement, "I want to", 0);
    String restOfStatement = statement.substring(psn + 9).trim();
    return "What would it mean to " + restOfStatement + "?";
  }
  
  private int findKeyword(String statement, String goal,int startPos)
  {
    String phrase = statement.trim();
    // The only change to incorporate the startPos is in
    // the line below
    int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
    
    // Refinement--make sure the goal isn't part of a
    // word
    while (psn >= 0)
    {
      // Find the string of length 1 before and after
      // the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring(psn - 1, psn)
          .toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
      }
      
      // If before and after aren't letters, we've
      // found the word
      if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
            && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
      {
        return psn;
      }
      
      // The last position didn't work, so let's find
      // the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
      
    }
    
    return -1;
  }
  
  /**
   * Search for one word in phrase. The search is not case
   * sensitive. This method will check that the given goal
   * is not a substring of a longer string (so, for
   * example, "I know" does not contain "no"). The search
   * begins at the beginning of the string.
   * 
   * @param statement
   *            the string to search
   * @param goal
   *            the string to search for
   * @return the index of the first occurrence of goal in
   *         statement or -1 if it's not found
   */
  private int findKeyword(String statement, String goal)
  {
    return findKeyword(statement, goal, 0);
  }
  
  
  private String getRandomResponse()
  {
    Random r = new Random ();
    return randomResponses [r.nextInt(randomResponses.length)];
  }
  
  private String [] randomResponses = {"Interesting, tell me more.",
    "Hmmm.",
    "You don't say.",
    "Please, continue talking.",
    "I'm gonna go grab a coffee but just pretend I'm still here.",
    "If you see this, it means I don't know how to respond.",
    "Wow, you just never stop, do you?",
    "No, by all means, keep talking.",
    "Kill me.",
    "That's nice.",
  };
}
