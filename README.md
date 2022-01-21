# Torneo
Practica Torneo Divide y Venceras, se organiza un torneo de n jugadores, cada jugador ha de jugar exactamente una vez contra cada uno de sus posibles n-1 competidores, se 
va a jugar un partido cada día, teniendo a lo sumo un día de descanso en todo el torneo.-
Para la ejecución suponemos que n es un número natural > 1. El torneo se realizará en n-1 días si n es par o en n días si es impar.

Para invocarlo, se necesita la sintaxis: 
java torneo [-t] [-h] n [ficheroEntrada]
siendo n el numero de jugadores.

Para facilitar la ejecución, hay un archivo preparado con nombres, se llama con nombres.txt, para ejecutarlo en eclipse podriamos compilar, y en las opciones:
  - run configurations
    - arguments
    y en los program arguments:
      -t -h 10 C:[ubicacion archivo]nombres.txt
