package com.jam69.simplescript.test;

/*
 *  Copyright &copy; Indra 2016
 */


import com.jam69.simplescript.Parser;
import com.jam69.simplescript.Scanner;
import com.jam69.simplescript.ast.AST;
import com.jam69.simplescript.ast.ASTContextImpl;
import java.io.StringReader;
import java.util.Date;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jamartinm
 */
public class SimpleScriptTest
{
  private final static Logger log = LoggerFactory.getLogger(SimpleScriptTest.class);

    
    public static void main(String[] args){
        SimpleScriptTest t=new SimpleScriptTest();
        t.run();
      
    }
   
    public void run(){
//        evalCheck("2.+3",null,5.);
//        evalCheck("(2+3.)",null,5.);
//        evalCheck("(2.8)+(3.2)",null,6.);
//        evalCheck("((2.888)+(3))",null,5.888);
//        evalCheck("(2)+3",null,5.);
//        evalCheck("2+3+4",null,9.);
//        evalCheck("2+%AA",null,9.7);
//        evalCheck("(2*3)+4",null,10.);
//        evalCheck("20*(30+40)",null,1400.);

        evalCheck("if (1) then h=sum(2,3) else h=7 ",null,10.);
//        evalCheck("kk =2; h= kk ",null,2.);
//        evalCheck("2* 3",null,6.);
//        evalCheck("3+5",null,8.);
//        evalCheck("2*3+5",null,11.);
//        evalCheck("2+3*5",null,17.);
//        evalCheck("2+3+5",null,10.);
//        evalCheck("2*3*5",null,30.);
//        evalCheck("2*3+5*6",null,36.);
//        evalCheck("2+3*5+6",null,23.);
//        evalCheck("1+1+1+1+1+1+1",null,7.);
//        evalCheck("2*2*2*2*2*2*2",null,128.);
//        evalCheck("(2+3)*5",null,25.);
//        evalCheck("2*3;5+6",null,11);
    }
    


    private void evalCheck(String formula, Object obj,Object expected){
        Parser parser=new Parser(new Scanner( new StringReader(formula)));
        AST ast=parser.parse();
       // Object result=ast.getValue(obj, null);
        ASTContextImpl ctx=new ASTContextImpl();
        Object result= ast.execute(ctx);
        if( ! Objects.equals(result,expected)){
                 log.error(" Formula='"+formula+"'->["+ast+"] obj="+obj+" expected="+expected + " result="+result);
        }else{
                 log.info(" Formula='"+formula+"'->["+ast+"] obj="+obj+" expected="+expected + " result="+result);
        }
        ctx.dump();
    }

}
