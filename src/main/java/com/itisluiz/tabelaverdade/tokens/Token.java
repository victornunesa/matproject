package com.itisluiz.tabelaverdade.tokens;

import com.itisluiz.tabelaverdade.formulas.NotacaoPolonesa;
import com.itisluiz.tabelaverdade.formulas.Tokens;

public interface Token
{
    void inserirContainer(Tokens tokens);
    void executarRPN(NotacaoPolonesa rpn);
    char identificador();
    int precedencia();
    boolean avaliar();
}
