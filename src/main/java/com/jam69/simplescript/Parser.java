/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript;

import com.jam69.simplescript.ast.AST;
import com.jam69.simplescript.ast.ASTArgList;
import com.jam69.simplescript.ast.ASTAssign;
import com.jam69.simplescript.ast.ASTBinaryOperation;
import com.jam69.simplescript.ast.ASTCte;
import com.jam69.simplescript.ast.ASTProcedureCall;
import com.jam69.simplescript.ast.ASTStmtIf;
import com.jam69.simplescript.ast.ASTStmtList;
import com.jam69.simplescript.ast.ASTVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/* This program illustrates recursive descent parsing using a
   pure procedural approach.

   The grammar:

   statement = { expression  ";" } "."
   expression = term { ( "+" | "-" ) term }
   term      = factor { ( "*" | "/" ) factor }
   factor    = number | "(" expression ")"

*/

public class Parser {

    private final static Logger log = LoggerFactory.getLogger(Parser.class);

    private Scanner scanner;


public Parser(Scanner scanner) {
    this.scanner = scanner;
} // Parser


public AST parse ( ) {
   scanner.getToken( );
   return statementList( );
} // run


private AST statementList ( ) {
    //   statement = { IDENT '=' expression  ";" } "."
   ASTStmtList value=new ASTStmtList();
    while(scanner.token==Token.letter||scanner.token==Token.ifToken) {
        if(scanner.token == Token.letter){
           String varName=scanner.letter();
           scanner.getToken( );
           if(scanner.token != Token.assignop){
               throw new ScannerException(" var = expression ");
           }
           scanner.getToken( );
           AST e = expression( );
           value.add(new ASTAssign(varName, e));
        }else if(scanner.token == Token.ifToken){
             scanner.getToken( );
             AST e = expression( );
             if(scanner.token != Token.thenToken){
                 throw new ScannerException(" falta then ");
             }
             scanner.getToken( );
              AST ifst = statementList( );
              AST elseSt=null;
             if(scanner.token == Token.elseToken){
                 scanner.getToken( );
                elseSt = statementList();
             }
             value.add(new ASTStmtIf(e, ifst, elseSt));
        }else{
           throw new ScannerException(" var = expression ");
        }
        
        if(scanner.token == Token.elseToken){
            return value;
        }
        if(scanner.token == Token.colon){
            return value;
        }
        if(scanner.token == Token.rparen){
            return value;
        }
        scanner.getToken( );  // flush ";"
    } // while
    return value;
} // statement


private AST expression ( ) {
    //    expression = term { ( "+" | "-" ) term }
    AST left= term( );
    while (scanner.token == Token.plusop ||
	   scanner.token == Token.minusop) {
	int saveToken = scanner.token;
	scanner.getToken( );
	switch (saveToken) {
	    case Token.plusop:
            //left += term( );
            AST f=term();
            left=new ASTBinaryOperation(ASTBinaryOperation.Oper.SUM,left,f);
		break;
	    case Token.minusop:
            //left -= term( );
            AST f2=term();
            left=new ASTBinaryOperation(ASTBinaryOperation.Oper.SUB,left,f2);
		break;
	} // switch
    } // while
    return left;
} // expression


private AST term ( ) {
    //    term = factor { ( "*" | "/" ) factor }
    AST left = factor( );
    while (scanner.token == Token.timesop ||
	   scanner.token == Token.divideop) {
	int saveToken = scanner.token;
	scanner.getToken( );
	switch (saveToken) {
	    case Token.timesop:
		//left *= factor( );
            AST f=factor();
            left=new ASTBinaryOperation(ASTBinaryOperation.Oper.MUL,left,f);
		break;
	    case Token.divideop:
		//left /= factor( );
            AST f2=factor();
            left=new ASTBinaryOperation(ASTBinaryOperation.Oper.DIV,left,f2);
		break;
	} // switch
    } // while
    return left;
} // term


private AST factor ( ) {
    //    factor    = varName| procName "(" args ")" | number | "(" expression ")"
    AST value =null;
    switch (scanner.token) {
    case Token.letter:
	    String varName = scanner.letter();
	    scanner.getToken( );  // flush number
        if(scanner.token==Token.lparen){
            scanner.getToken( );
            ASTArgList arg = args();
            if(scanner.token!=Token.rparen){
                throw new ScannerException("Falta cerrar parentesis");
            }
            value=new ASTProcedureCall(varName, arg);
            scanner.getToken( );
        }else{
          //log.info("varName_"+varName);
          value=new ASTVariable(varName);
        }

	    break;
	case Token.number:
	    value = new ASTCte(scanner.number( ));
	    scanner.getToken( );  // flush number
	    break;
	case Token.lparen:
	    scanner.getToken( );
	    value = expression( );
	    if (scanner.token != Token.rparen)
            scanner.error("Missing ')'");
	    scanner.getToken( );  // flush ")"
	    break;
	default:
	    scanner.error("Expecting number or (  Found " +Token.toString(scanner.token) );
	    break;
    } // switch
    return value;
} // factor

private ASTArgList args(){
    //    args = NIL | { expresion {"," expresion} }
    ASTArgList ret=new ASTArgList();
    if(scanner.token == Token.rparen){
        // No Args
        return ret;  //
    }
    AST v=expression();
    ret.add(v);
    while(scanner.token==Token.colon){
        scanner.getToken();
        AST expr=expression();
        ret.add(expr);
    }
    return ret;
}

} // class Parser
