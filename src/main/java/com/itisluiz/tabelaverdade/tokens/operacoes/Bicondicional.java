package com.itisluiz.tabelaverdade.tokens.operacoes;

import com.itisluiz.tabelaverdade.tokens.Operacao;
import com.itisluiz.tabelaverdade.tokens.Token;

public class Bicondicional extends Operacao
{
    public Bicondicional() { this.operandos = new Token[2]; }

    @Override
    public char identificador() { return '↔'; };

    @Override
    public int precedencia() { return 2; }

    @Override
    public boolean avaliar() { return this.operandos[1].avaliar() == this.operandos[0].avaliar(); }
}
