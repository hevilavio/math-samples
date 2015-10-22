# math-samples

Alguns algoritmos relacionados com matemática e estatística.  
As ideias vieram de alguma coisas que eu li:
- Livro: O poder do pensamento matemático - Jordan Ellenberg

##### CoinFlipper
Mostra que em uma partida de cara-ou-coroa, quanto maior o numero de moedas lançadas simultaneamente, maior a chance de nos aproximarmos de um resultado 50-50.
* Manipule o valor das variáveis **numberOfCoins** e **incrementCoins** para observar 
como o valor de **avg noise** se **aproxima (mas nunca chega)** a zero.
* Significado:
```
    flips      | quantidade de partidas executadas
    coins      | quantidade de moedas em cada partida
    observes   | quantidade de CARAS do resultado    
    observes % | porcentagem de CARAS para cada partida     
    noise      | quanto o resultado de CARA se desviou de 50%
    avg noise  | média do quanto o resultado de CARA se desviou de 50%
```

* Observe um resultado [aqui](https://raw.githubusercontent.com/hevilavio/math-samples/master/sample.out)
