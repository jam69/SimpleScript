/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript;

import java.io.BufferedReader;
import java.io.Reader;

public class Scanner {
private char ch = ' ';
private String ident = "";
private Double  numValue = 0.;
private Buffer buffer;
public int token;

/**
 *
 * @author jamartinm
 */
public Scanner (Reader in) {
    buffer = new Buffer(new BufferedReader(in));
    token = Token.semicolon;
} // Scanner


public int getToken ( ) {
    while (Character.isWhitespace(ch))
    {
        ch = buffer.get( );
    }
    if (Character.isLetter(ch)) {
        StringBuffer sb=new StringBuffer(ch);
        while( Character.isLetter(ch) || Character.isDigit(ch)||ch=='_'){
            sb.append(ch);
            ch = buffer.get( );
        }
        ident=sb.toString();
        if(ident.equalsIgnoreCase("IF")){
            token=Token.ifToken;
        }else if(ident.equalsIgnoreCase("THEN")){
            token=Token.thenToken;
        }else if(ident.equalsIgnoreCase("ELSE")){
            token=Token.elseToken;
        }else{
            token = Token.letter;
        }
    }
    else if (Character.isDigit(ch)) {
        numValue = getNumber( );
        token = Token.number;
    }
    else {
	switch (ch) {
	    case ';' : ch = buffer.get( );
		token = Token.semicolon;
		break;

	    case '.' : ch = buffer.get( );
		token = Token.period;
		break;

        case ',' : ch = buffer.get( );
		token = Token.colon;
		break;

	    case '+' : ch = buffer.get( );
		token = Token.plusop;
		break;

	    case '-' : ch = buffer.get( );
		token = Token.minusop;
		break;

	    case '*' : ch = buffer.get( );
		token = Token.timesop;
		break;

	    case '/' : ch = buffer.get( );
		token = Token.divideop;
		break;

	    case '=' : ch = buffer.get( );
		token = Token.assignop;
		break;

	    case '(' : ch = buffer.get( );
		token = Token.lparen;
		break;

	    case ')' : ch = buffer.get( );
		token = Token.rparen;
		break;

        case 0: token=Token.period; // or .eof
            break;
	    default : error ("Illegal character " + ch );
		break;
	} // switch
    } // if
    return token;
} // getToken


public Double number ( ) {
    return numValue;
} // number


public String letter ( ) {
    return ident;
} // letter


public void match (int which) {
    token = getToken( );
    if (token != which) {
	error("Invalid token " + Token.toString(token) +
	      "-- expecting " + Token.toString(which));
//	System.exit(1);
     throw new ScannerException();
    } // if
} // match


public void error (String msg) {
    System.err.println(msg);
  //  System.exit(1);
     throw new ScannerException();
} // error


private Double getNumber ( ) {
//    int rslt = 0;
//    do {
//	rslt = rslt * 10 + Character.digit(ch, 10);
//	ch = buffer.get( );
//    } while (Character.isDigit(ch));
//    return rslt;
    StringBuffer sb=new StringBuffer(ch);
    while (Character.isDigit(ch) || ch=='.' ) {
        sb.append(ch);
        ch = buffer.get( );
    }
    Double r=Double.parseDouble(sb.toString());
    return r;
} // getNumber

} // Scanner

class Buffer {
private String line = "";
private int column = 0;
private int lineNo = 0;
private BufferedReader  in;

public Buffer (BufferedReader in) {
    this.in = in;
} // Buffer


public char get ( ) {
    column++;
    if (column >= line.length()) {
	try {
	    line = in.readLine( );
	} catch (Exception e) {
	    System.err.println("Invalid read operation");
         throw new ScannerException(" leyendo linea en "+line+":"+column );
	 //   System.exit(1);
	} // try
	if (line == null){
	 //   System.exit(0);
        return 0;
       // throw new ScannerException(" leyendo linea en "+line+":"+column );
    }
	column = 0;
	lineNo++;
	System.out.println(line);
	line = line + "\n";
    } // if column
    return line.charAt(column);
} // get

} // class Buffer


