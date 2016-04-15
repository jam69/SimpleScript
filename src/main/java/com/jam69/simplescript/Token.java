/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript;

/**
 *
 * @author jamartinm
 */

class Token {

public static final int semicolon = 0;
public static final int period    = 1; // EOF
public static final int plusop    = 2;
public static final int minusop   = 3;
public static final int timesop   = 4;
public static final int divideop  = 5;
public static final int assignop  = 6;
public static final int lparen    = 7;
public static final int rparen    = 8;
public static final int letter    = 9;
public static final int number    = 10;
public static final int colon    = 11;
public static final int ifToken    = 12;
public static final int thenToken    = 13;
public static final int elseToken    = 14;

private static String[] spelling = {
    ";", ".", "+", "-", "*", "/", "=", "(", ")",
    "letter", "number",",","if","then","else"};

public static String toString (int i) {
    if (i < 0 || i > number)
	return "";
    return spelling[i];
} // toString

} // Token
