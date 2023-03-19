package com.itisluiz.tabelaverdade.tokens.operacoes;

import com.itisluiz.tabelaverdade.tokens.Operacao;
import com.itisluiz.tabelaverdade.tokens.Token;

public class Negacao extends Operacao
{
    public Negacao() { this.operandos = new Token[1]; }

    @Override
    public char identificador() { return '¬'; };

    @Override
    public int precedencia() { return 1; }

    @Override
    public boolean avaliar() { return !this.operandos[0].avaliar(); }
}