package com.itisluiz.tabelaverdade.tokens;

import com.itisluiz.tabelaverdade.formulas.NotacaoPolonesa;
import com.itisluiz.tabelaverdade.formulas.Tokens;

public class Variavel implements Token
{
    private final char identificador;
    private boolean valor;

    public Variavel(char identificador)
    {
        this.identificador = identificador;
    }
    public void atribuirValor(boolean valor) { this.valor = valor; }

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
    public char identificador() { return this.identificador; };

    @Override
    public int precedencia() { return 0; }

    @Override
    public boolean avaliar() { return this.valor; }
}
