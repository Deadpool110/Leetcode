package leetcode;

public class _374_guessNumber {
    GuessGame game = new GuessGame();

    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            int t = game.guess(mid);
            if (t == -1) {
                r = mid - 1;
            } else if (t == 1) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
class GuessGame {
    int target;

    public GuessGame() {}

    public GuessGame(int target) {
        this.target = target;
    }

    int guess(int num) {
        return Integer.compare(target, num);
    }
}
