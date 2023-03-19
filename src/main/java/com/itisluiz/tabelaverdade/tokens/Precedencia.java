package com.itisluiz.tabelaverdade.tokens;

import com.itisluiz.tabelaverdade.formulas.NotacaoPolonesa;
import com.itisluiz.tabelaverdade.formulas.Tokens;

public class Precedencia implements Token
{
    private final boolean inicia;

    public Precedencia(boolean inicia) { this.inicia = inicia; }

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

    @Override
    public char identificador() { return this.inicia ? '(' : ')'; };

    @Override
    public int precedencia() { return -1; }

    @Override
    public boolean avaliar() { return this.inicia; }
}
