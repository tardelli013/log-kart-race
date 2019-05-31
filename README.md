
# Resultado corrida de Kart!  
Este projeto retorna o resultado final de uma corrida de Kart de acordo com um arquivo de log com informações das voltas dos pilotos.
  
## Stack:  
  
**Java 8**  
**Maven**  
**Junit**  
  
Projeto pode ser executado com Maven com o comando abaixo:  
```  
mvn clean install exec:java  
```  
O projeto também pode ser executado através de sua classe *Main* no pacote *'br.com.tardelli'*

> KartRaceMain.java

Ao executar o projeto, o resultado da corrida será exibido no console da aplicação conforme exemplo abaixo:

```text
*** RESULTADO FINAL *** 

1 - 038-F.MASSA              4 voltas completas    Tempo total: 00:04:11.578    Melhor volta: 00:01:02.769    Vel. Média: 44,25    Termino:   23:52:17.003    Horário término: 23:52:17.003
2 - 002-K.RAIKKONEN          4 voltas completas    Tempo total: 00:04:15.153    Melhor volta: 00:01:03.076    Vel. Média: 43,63    Termino: + 00:00:05.117    Horário término: 23:52:22.120
3 - 033-R.BARRICHELLO        4 voltas completas    Tempo total: 00:04:16.080    Melhor volta: 00:01:03.716    Vel. Média: 43,47    Termino: + 00:00:05.583    Horário término: 23:52:22.586
4 - 023-M.WEBBER             4 voltas completas    Tempo total: 00:04:17.722    Melhor volta: 00:01:04.216    Vel. Média: 43,19    Termino: + 00:00:08.972    Horário término: 23:52:25.975
5 - 015-F.ALONSO             4 voltas completas    Tempo total: 00:04:54.221    Melhor volta: 00:01:07.011    Vel. Média: 38,07    Termino: + 00:00:49.738    Horário término: 23:53:06.741
6 - 011-S.VETTEL             3 voltas completas    Tempo total: 00:06:27.276    Melhor volta: 00:01:18.097    Vel. Média: 25,75    Termino: + 00:02:40.754    Horário término: 23:54:57.757

*** MELHOR VOLTA DA CORRIDA ***
038-F.MASSA Tempo: 00:01:02.769     Vel. média: 44.334
```

 E para rodar os testes unitários com Maven é só rodar o comando abaixo:
```  
mvn clean test  
``` 