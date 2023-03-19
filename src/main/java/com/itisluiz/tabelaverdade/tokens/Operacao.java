package com.itisluiz.tabelaverdade.tokens;

import com.itisluiz.tabelaverdade.formulas.NotacaoPolonesa;
import com.itisluiz.tabelaverdade.formulas.Tokens;

public abstract class Operacao implements Token
{
    protected Token[] operandos;

    public int valencia() { return this.operandos.length; }

    public void inserirOperando(Token operando)
    {
        System.arraycopy(operandos, 0, operandos, 1, this.valencia() - 1);
        operandos[0] = operando;
    }

    @Override
    public void inserirContainer(Tokens tokens)
    {
        tokens.inserirToken(this);
    }

    @Override
    public void executarRPN(NotacaoPolonesa rpn)
    {
        rpn.rpn(this);
    }
}
