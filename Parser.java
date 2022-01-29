import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;
import java.util.*;
 
public class Parser implements IParser {
 
  public Derivation derivationForTree; 
  private Word variableToWord(Variable var){
    Word w = new Word(var);
    return w;
  }
 
  //method that changes Symbol to Word
  private Word symbolToWord(Symbol symbol){
    Word w = new Word(symbol);
    return w;
  }
 
  //generates all possible 1-step derivations for a word
  private List generateDerivationList(List<Rule> rules, Derivation d, int steps){
    Word finalWord = d.getLatestWord();
    List<Derivation> oneStepDers = new ArrayList();
    Derivation firstDerivation = new Derivation(d);
    int index=0;
    for(Symbol s: finalWord){
      if(s.isTerminal()){
        index++;
        continue;
      }
      else{
        for(Rule rule: rules){
        firstDerivation = new Derivation(d);
        Word wordOfRule = variableToWord(rule.getVariable());
        if(wordOfRule.equals(symbolToWord(s))){
          Word derWord = finalWord.replace(index, rule.getExpansion());
          firstDerivation.addStep(derWord, rule, steps);
          Derivation newDerivation = firstDerivation;
          oneStepDers.add(newDerivation);
        }
        else{
          continue;
        }
      }
    }
      break;
    }
    return oneStepDers;
  }
 
  //PART C: method to check if given word is in language
  public boolean isInLanguage(ContextFreeGrammar cfg, Word w){

    List<Derivation> currentDerivations = new ArrayList(); //current ders list
    List<Rule> rulesForGrammar = cfg.getRules(); //rule list
    Variable startVariable = cfg.getStartVariable(); //start variable
    Word startingWord = variableToWord(startVariable); //start variable as Word object
 
    Derivation derivationStartVariable = new Derivation(startingWord);//der for start variable
    currentDerivations.add(derivationStartVariable);//adds der for start variable to der list
    int steps = 0; //derivation step count
    int n = w.length(); //length of the input word
    int derivationSteps; //input word derivation step count
 
    //check if single or 2n-1 step derivation
    if(n>=1){
      derivationSteps = 2*n-1;
    }
    else{
      derivationSteps=1;
    }
 
    while(steps<derivationSteps){
 
      List<Derivation> newCurrentList = new ArrayList();
      List<Derivation> newDerivations = new ArrayList();
 
      for(Derivation derivation: currentDerivations){
        newDerivations = generateDerivationList(rulesForGrammar, derivation, steps);
        for(Derivation der: newDerivations){
          newCurrentList.add(der);
        }  
        newDerivations = new ArrayList();
      }
    currentDerivations = newCurrentList;
    steps++;
    }
 
    //check if any derivations can create the word and if word only conists of terminals
    boolean isInLanguage = false;
    for(Derivation der: currentDerivations){
      Word word=der.getLatestWord();
      int count=0;
      for(Symbol s: word){
        if(s.isTerminal()){
          count++;
        }
      }
      if(count==word.length()) {
        if(word.equals(w)){
          isInLanguage = true;
          derivationForTree = der;
        }
      }
    }
    return isInLanguage;
  }
 
  //PART D: create principle parse tree from given word
  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word w) {
   
    ParseTreeNode printTree;
 
    if(isInLanguage(cfg, w) == false){
      printTree = null;
    }
    else{
      List<ParseTreeNode> finalBranches = new ArrayList();
      for(Step step: derivationForTree){
        Rule ruleStep = step.getRule();
        if (ruleStep != null){
          Word branch = variableToWord(ruleStep.getVariable());
          Word leaf = ruleStep.getExpansion();
          if(leaf.isTerminal()){
            ParseTreeNode leafNode = new ParseTreeNode(leaf.get(0));
            ParseTreeNode addedLeafNode = new ParseTreeNode(branch.get(0), leafNode);
            finalBranches.add(addedLeafNode);
          }
          else{
            List<ParseTreeNode> trees = new ArrayList();
            for(Symbol variable: leaf){
              for(ParseTreeNode incompleteTrees: finalBranches){
                if(incompleteTrees.getSymbol()==variable){
                  trees.add(incompleteTrees);
                }
              }
            }
            finalBranches.add(new ParseTreeNode(branch.get(0), trees.get(0), trees.get(1)));
          }
        }
        else{
          break;
        }
      }
      printTree = finalBranches.get(finalBranches.size()-1);
    }
  return printTree;
  }
}