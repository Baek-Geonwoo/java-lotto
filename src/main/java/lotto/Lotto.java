package lotto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    public void printLotto() {
        String result = this.numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(result);
    }
    public void fillSet(Set<Integer> Lottoset) {
        for (Integer num : this.numbers) {
            Lottoset.add(num);
        }
    }

}
