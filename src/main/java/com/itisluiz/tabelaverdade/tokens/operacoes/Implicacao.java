package com.itisluiz.tabelaverdade.tokens.operacoes;

import com.itisluiz.tabelaverdade.tokens.Operacao;
import com.itisluiz.tabelaverdade.tokens.Token;

public class Implicacao extends Operacao
{
    public Implicacao() { this.operandos = new Token[2]; }

    @Override
    public char identificador() { return '→'; };

    @Override
    public int precedencia() { return 3; }

    @Override
    public boolean avaliar() { return this.operandos[1].avaliar() || !this.operandos[0].avaliar(); }
}
