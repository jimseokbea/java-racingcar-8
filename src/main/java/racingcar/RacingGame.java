package racingcar;

public class RacingGame {
    private final Cars cars;
    private final int attempts;

    public RacingGame(String[] carNames, int attempts) {
        InputValidator.validateAttempts(attempts);
        this.cars = new Cars(carNames);
        this.attempts = attempts;
    }

    public void play() {
        OutputView.printResultHeader();

        for (int i = 0; i < attempts; i++) {
            playRound();
        }

        announceWinners();
    }

    private void playRound() {
        cars.moveAll();
        OutputView.printRoundResult(cars.getCars());
    }

    private void announceWinners() {
        OutputView.printWinners(cars.findWinners());
    }
}