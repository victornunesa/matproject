package com.itisluiz.tabelaverdade.tokens;

public class TokenDesconhecido extends RuntimeException
{
    public TokenDesconhecido(char token) { super("Token desconhecido: '" + token + "'"); }
}
