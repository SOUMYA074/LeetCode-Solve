class Solution {
    private int[] row = new int[9];
    private int[] col = new int[9];
    private int[] box = new int[9];

    public void solveSudoku(char[][] board) {
        // Build masks
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int bit = 1 << (board[r][c] - '1');
                    row[r] |= bit;
                    col[c] |= bit;
                    box[(r / 3) * 3 + c / 3] |= bit;
                }
            }
        }

        solve(board);
    }

    private boolean solve(char[][] board) {
        int bestR = -1, bestC = -1;
        int bestMask = 0;
        int minChoices = 10;

        // Choose the empty cell with the fewest possibilities
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    int b = (r / 3) * 3 + c / 3;

                    int used = row[r] | col[c] | box[b];
                    int available = (~used) & 0x1FF;

                    int choices = Integer.bitCount(available);

                    if (choices < minChoices) {
                        minChoices = choices;
                        bestR = r;
                        bestC = c;
                        bestMask = available;

                        if (choices == 1) break;
                    }
                }
            }
            if (minChoices == 1) break;
        }

        // No empty cells → solved
        if (bestR == -1) return true;

        int b = (bestR / 3) * 3 + bestC / 3;

        // Try each available digit
        while (bestMask != 0) {
            int bit = bestMask & -bestMask;
            bestMask -= bit;

            int digit = Integer.numberOfTrailingZeros(bit);

            board[bestR][bestC] = (char) ('1' + digit);

            row[bestR] |= bit;
            col[bestC] |= bit;
            box[b] |= bit;

            if (solve(board)) return true;

            // Backtrack
            row[bestR] ^= bit;
            col[bestC] ^= bit;
            box[b] ^= bit;

            board[bestR][bestC] = '.';
        }

        return false;
    }
}