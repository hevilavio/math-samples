
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Exerc�cio tirado do livro: O poder do pensamento matem�tico - Jordan Ellenberg
 *
 * Segundo Jordan, quanto maior o n�mero de moedas lan�adas no cara-ou-coroa,
 * mais pr�ximo voc� estar� de um resultado 50-50.
 *
 * "Algo est� empurrando esses n�meros para chegar cada vez mais perto de 50%.
 * Esse algo � a fria e forte m�o da lei dos grandes n�meros - Jordan Ellenberg"
 *
 * Let's find out...
 *
 * Created by hevilavio on 10/22/15.
 */
public class CoinFlipper {
    long numberOfCoins = 100;
    long incrementCoins = 1000;

    final int numberOfFlips = 10;
    public static void main(String[] args) throws InterruptedException {
        CoinFlipper coinFlipper = new CoinFlipper();

        for(;;){
            coinFlipper.doTheWork();
            coinFlipper.incrementCoins();

//            Thread.sleep(1 * 1000);
        }
    }

    private void incrementCoins() {
        this.numberOfCoins += incrementCoins;
    }

    public void doTheWork(){
        List<FlipsResult> flips = new LinkedList<>();

        for (int i = 0; i < numberOfFlips; i++) {
            flips.add(flipCoins(numberOfCoins));
        }

        printInput();
        printResults(flips);
        printAvgNoise(flips);

        System.out.println();
    }

    public FlipsResult flipCoins(long numberOfCoins){
        long obverseCount = 0, reverseCount = 0;
        Random r = new Random();

        for (int i = 0; i < numberOfCoins; i++) {
            CoinSide cs = new Coin().flip(r);

            if(cs.equals(CoinSide.OBVERSE)) obverseCount++;
            if(cs.equals(CoinSide.REVERSE)) reverseCount++;
        }
        return new FlipsResult(obverseCount, reverseCount);
    }

    /**
     * M�todos para exibir os resultados.
     *
     * */
    private void printAvgNoise(List<FlipsResult> flips) {

        System.out.print(String.format("\n%-11s| ", "avg noise"));

        List<Double> noises = flips.stream()
            .map(f -> Math.abs(observeNoisePercent(f)))
            .collect(Collectors.toList());

        double sum = noises
            .stream()
            .mapToDouble(n -> n)
            .sum();

        double avg = sum / noises.size();

        System.out.print(String.format("%10.8f", avg));
    }
    private void printInput() {
        System.out.print(String.format("\n%-11s| %s", "flips", numberOfFlips));
        System.out.print(String.format("\n%-11s| %s", "coins", numberOfCoins));
    }

    private void printResults(List<FlipsResult> flips) {

        System.out.print(String.format("\n%-10s |", "observes"));
        flips.stream().forEach(f -> System.out.print(String.format("%10s", f.obverseCount)));

        System.out.print(String.format("\n%-10s |", "observes %"));
        flips.stream().forEach(f -> System.out.print(String.format("%10.2f", observePercent(f))));

        System.out.print(String.format("\n%-10s |", "noise"));
        flips.stream().forEach(f -> System.out.print(String.format("%10.2f", observeNoisePercent(f))));
    }

    /**
     * Retorna a percentagem da quantidade de caras em rela��o ao total de moedas.
     * */
    private double observePercent(FlipsResult flip) {
        return (flip.obverseCount * 100.0) / numberOfCoins;
    }

    /**
     * Retorna a diferenca entre a percentagem e 50% (desvio)
     * */
    private double observeNoisePercent(FlipsResult flip) {
        double x = observePercent(flip);
        return x - 50.0;
    }
}

class Coin {
    public CoinSide flip(Random r){
        return (r.nextInt() % 2) == 0 ? CoinSide.OBVERSE : CoinSide.REVERSE;
    }
}


enum CoinSide {
    OBVERSE, // cara
    REVERSE // coroa
}

/**
 * Resultado do lan�amento de v�rias moedas.
 * */
class FlipsResult {
    public final long obverseCount;
    public final long reverseCount;

    public FlipsResult(long obverseCount, long reverseCount) {
        this.obverseCount = obverseCount;
        this.reverseCount = reverseCount;
    }
}
