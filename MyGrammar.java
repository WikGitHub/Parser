import computation.contextfreegrammar.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
 
public class MyGrammar {
 
  //PART B:
  public static ContextFreeGrammar makeGrammar() {
    Variable A0 = new Variable("A0");
    Variable A1 = new Variable("A1");
    Variable A2 = new Variable("A2");
    Variable A3 = new Variable("A3");
    Variable S = new Variable("S");
    Variable S1 = new Variable("S1");
    Variable S2 = new Variable("S2");
    Variable S3 = new Variable("S3");
    Variable T = new Variable("T");
    Variable T1 = new Variable("T1");
    Variable T2 = new Variable("T2");
    Variable F = new Variable("F");
    Variable F1 = new Variable("F1");
    Variable L = new Variable('L');
    Variable A = new Variable('A');
    Variable M = new Variable('M');
    Variable R = new Variable('R');
 
    HashSet<Variable> variables = new HashSet<>();
    variables.add(A0);
    variables.add(A1);
    variables.add(A2);
    variables.add(A3);
    variables.add(S);
    variables.add(S1);
    variables.add(S2);
    variables.add(S3);
    variables.add(T);
    variables.add(T1);
    variables.add(T2);
    variables.add(F);
    variables.add(F1);
    variables.add(L);
    variables.add(A);
    variables.add(M);
    variables.add(R);
 
    Terminal add = new Terminal('+');
    Terminal asterisk = new Terminal('*');
    Terminal obrack = new Terminal('(');
    Terminal cbrack = new Terminal(')');
    Terminal one = new Terminal('1');
    Terminal zero = new Terminal('0');
    Terminal x = new Terminal('x');
 
    HashSet<Terminal> terminals = new HashSet<>();
    terminals.add(add);
    terminals.add(asterisk);
    terminals.add(obrack);
    terminals.add(cbrack);
    terminals.add(one);
    terminals.add(zero);
    terminals.add(x);

    ArrayList<Rule> rules = new ArrayList<>();
    //rules.add(new Rule(A0, Word.emptyWord));
    rules.add(new Rule(A0, new Word(S, A1)));
    rules.add(new Rule(A0, new Word(T, A2)));
    rules.add(new Rule(A0, new Word(L, A3)));
    rules.add(new Rule(A0, new Word(one)));
    rules.add(new Rule(A0, new Word(zero)));
    rules.add(new Rule(A0, new Word(x)));
    rules.add(new Rule(A1, new Word(A, T)));
    rules.add(new Rule(A2, new Word(M, F)));
    rules.add(new Rule(A3, new Word(S, R)));
    rules.add(new Rule(S, new Word(S, S1)));
    rules.add(new Rule(S, new Word(T, S2)));
    rules.add(new Rule(S, new Word(L, S3)));
    rules.add(new Rule(S, new Word(one)));
    rules.add(new Rule(S, new Word(zero)));
    rules.add(new Rule(S, new Word(x)));
    rules.add(new Rule(S1, new Word(A, T)));
    rules.add(new Rule(S2, new Word(M, F)));
    rules.add(new Rule(S3, new Word(S, R)));
    rules.add(new Rule(T, new Word(T, T1)));
    rules.add(new Rule(T, new Word(L, T2)));
    rules.add(new Rule(T, new Word(one)));
    rules.add(new Rule(T, new Word(zero)));
    rules.add(new Rule(T, new Word(x)));
    rules.add(new Rule(T1, new Word(M, F)));
    rules.add(new Rule(T2, new Word(S, R)));
    rules.add(new Rule(F, new Word(L, F1)));
    rules.add(new Rule(F, new Word(one)));
    rules.add(new Rule(F, new Word(zero)));
    rules.add(new Rule(F, new Word(x)));
    rules.add(new Rule(F1, new Word(S, R)));
    rules.add(new Rule(L, new Word(obrack)));
    rules.add(new Rule(A, new Word(add)));
    rules.add(new Rule(M, new Word(asterisk)));
    rules.add(new Rule(R, new Word(cbrack)));
 
    ContextFreeGrammar cfg = new ContextFreeGrammar(variables, terminals, rules, A0);
 
    return cfg;
  }
}