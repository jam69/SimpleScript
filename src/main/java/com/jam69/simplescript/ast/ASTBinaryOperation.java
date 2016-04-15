/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import java.util.Date;

/**
 *
 * @author jamartinm
 */
public class ASTBinaryOperation implements AST
{

    @Override
    public Object execute(ASTContext context)
    {
        Number n1 = (Number) f1.execute(context);
        Number n2 = (Number) f2.execute(context);
         switch (oper)
        {
            case SUM:
                return n1.doubleValue() + n2.doubleValue();
            case MUL:
                return n1.doubleValue() * n2.doubleValue();
            case SUB:
                return n1.doubleValue() - n2.doubleValue();
            case DIV:
                return n1.doubleValue() / n2.doubleValue();
        }
         return null;
     }

    public enum Oper
    {

        SUM, SUB, DIV, MUL
    }

    private final AST f1;
    private final AST f2;
    private final Oper oper;

    public ASTBinaryOperation(Oper oper, AST f1, AST f2)
    {
        this.oper = oper;
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public Object getValue(Object obj, Date d)
    {
        Object v1 = f1.getValue(obj, d);
        Object v2 = f2.getValue(obj, d);
        Number n1 = (Number) v1;
        Number n2 = (Number) v2;
        switch (oper)
        {
            case SUM:
                return n1.doubleValue() + n2.doubleValue();
            case MUL:
                return n1.doubleValue() * n2.doubleValue();
            case SUB:
                return n1.doubleValue() - n2.doubleValue();
            case DIV:
                return n1.doubleValue() / n2.doubleValue();
        }
        return null;
    }

    public static Oper getOperator(String str)
    {
        if (str.equals("+"))
        {
            return Oper.SUM;
        }
        if (str.equals("*"))
        {
            return Oper.MUL;
        }
        if (str.equals("-"))
        {
            return Oper.SUB;
        }
        if (str.equals("/"))
        {
            return Oper.DIV;
        }
        return null;
    }

    public static String opStr(Oper ope)
    {
        switch (ope)
        {
            case SUM:
                return "+";
            case MUL:
                return "*";
            case SUB:
                return "-";
            case DIV:
                return "/";
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "( " + f1.toString() + " " + opStr(oper) + " " + f2.toString() + " )";
    }
}
