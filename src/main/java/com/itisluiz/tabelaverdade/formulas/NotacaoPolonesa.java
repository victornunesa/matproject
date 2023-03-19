package com.itisluiz.tabelaverdade.formulas;

import com.itisluiz.tabelaverdade.tokens.*;

import java.util.ArrayDeque;
import java.util.List;

public class NotacaoPolonesa
{
    private final ArrayDeque<Token> buffer = new ArrayDeque<Token>();
    private final ArrayDeque<Token> fila = new ArrayDeque<Token>();
    private boolean resolvendo;

    private NotacaoPolonesa() {};

    private void bufferParaFila() { this.fila.add(this.buffer.pop()); }

    private void modo(boolean resolvendo)
    {
        if (resolvendo)
        {
            while (!this.buffer.isEmpty())
                this.bufferParaFila();
        }

        this.resolvendo = resolvendo;
    }

    public void rpn(Variavel token)
    {
        if (this.resolvendo)
            this.buffer.push(token);
        else
            this.fila.add(token);
    }

    public void rpn(Operacao token)
    {
        if (this.resolvendo)
        {
            for (int op = 0; op < token.valencia(); ++op)
                if (this.buffer.isEmpty())
                    throw new FormulaMalFormada("Operador '" + token.identificador() + "' com operandos faltantes");
                else
                    token.inserirOperando(this.buffer.pop());
        }
        else
        {
            int precedencia = this.buffer.isEmpty() ? 0 : this.buffer.peek().precedencia();
            if (precedencia > 0 && precedencia < token.precedencia())
                this.bufferParaFila();
        }
        this.buffer.push(token);
    }

    public void rpn(Precedencia token)
    {
        if (this.resolvendo)
            throw new FormulaMalFormada("Parêntese aberto sem correspondente");
        else
        {
            if (token.avaliar())
                this.buffer.push(token);
            else
            {
                while (!this.buffer.isEmpty())
                {
                    Token tokenTopo = this.buffer.pop();
                    if (tokenTopo.precedencia() == token.precedencia() && tokenTopo.avaliar())
                        return;

                    this.fila.add(tokenTopo);
                }
                throw new FormulaMalFormada("Parêntese fechado sem correspondente");
            }
        }
    }

    private Token executarRPN(List<Token> tokens)
    {
        Token raiz = null;
        for (byte modo = 0; modo < 2; ++modo)
        {
            modo(modo > 0);
            for (Token token : modo > 0 ? this.fila : tokens)
            {
                token.executarRPN(this);
                raiz = token;
            }
        }

        return raiz;
    }

    public static Token calcularRaiz(List<Token> tokens)
    {
        NotacaoPolonesa instancia = new NotacaoPolonesa();
        return instancia.executarRPN(tokens);
    }
}
