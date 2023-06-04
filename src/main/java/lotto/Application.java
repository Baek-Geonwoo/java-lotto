package lotto;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashSet;
import java.util.Set;

public class Application {
    public static int compare(Set<Integer> lotto, Set<Integer> target) {
        Set<Integer> intersection = new HashSet<>(lotto);
        intersection.retainAll(target);
        return intersection.size();
    }
    public static void statLotto(Lotto[] tickets, int[] nums, int[] stats) {
        Set<Integer> setMatch = new HashSet<>();
        for (Integer num : nums) {
            setMatch.add(num);
        }
        int c;
        Set<Integer>[] sets = new Set[tickets.length];
        for (int i = 0; i < tickets.length; i++) {
            sets[i] = new HashSet<>();
            tickets[i].fillSet(sets[i]);
            c = compare(sets[i], setMatch);
            if (sets[i].contains(nums[6]) && c == 6) {
                stats[3] -= 1;
                stats[4] += 1;
            }
        }
    }

    public static void printStat(int[] stats, float amount) {
        float money = stats[0]*5_000+stats[1]*50_000+stats[2]*1_500_000+stats[4]*2_000_000_000+stats[3]*30_000_000;
        System.out.println("당첨 통계\n---");
        System.out.println("3개 일치 (5,000원) - " + stats[0] + "개");
        System.out.println("4개 일치 (50,000원) - " + stats[1] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + stats[2] + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + stats[4] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + stats[3] + "개");
        System.out.println("총 수익률은 " + money/amount*100 + "%입니다.");
    }
    public static void makeLotto(Lotto[] tickets, int ticketNum) {
        for (int i = 0; i < ticketNum; i++) {
            tickets[i] = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        }
    }

    public static int saveAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = Integer.parseInt(Console.readLine());
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("금액이 1000으로 나누어 떨어지지 않습니다.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("금액은 양수여야합니다.");
        }
        return amount;
    }

    public static void saveIntegers(int[] arr) {

        System.out.println("당첨 번호를 입력해 주세요.");
        String[] numStrings = Console.readLine().split(",");
        if (numStrings.length != 6) {
            throw new IllegalArgumentException("로또 번호는 6개입니다.");
        }
        for (int i = 0; i < numStrings.length; i++) {
            arr[i] = Integer.parseInt(numStrings[i].trim());
            if (arr[i] < 1 || arr[i] > 45) {
                throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
        System.out.println("보너스 번호를 입력해 주세요.");
        arr[6] = Integer.parseInt(Console.readLine());
        if (arr[6] < 1 || arr[6] > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int amount = 0;
        //구입 금액 입력
        try {
            amount = saveAmount(); // 총 금액
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        int ticketNum = amount / 1000;
        System.out.println(ticketNum + "개를 구매했습니다.");

        Lotto[] tickets = new Lotto[ticketNum];
        makeLotto(tickets, ticketNum);
        for (Lotto ticket : tickets) {
            ticket.printLotto();
        }
        int[] nums = new int[7];
        saveIntegers(nums);

        int[] stats = new int[5];
        statLotto(tickets, nums, stats);
        printStat(stats, amount);
    }
}
