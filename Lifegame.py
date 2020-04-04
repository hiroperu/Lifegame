import numpy as np
import time
import random
import itertools

# import os

print("")


def init_board(row=10, col=10):
    board = np.zeros((row, col), dtype=int)
    # print([random.randint(0, 9) for i in range(10)])
    rand_row = [random.randint(0, row - 1) for i in range(5)]
    rand_col = [random.randint(0, col - 1) for i in range(5)]
    for i, j in itertools.product(rand_row, rand_col):
        board[i][j] = 1
    # print(i, j)
    # board[5][5] = 1
    # board[4][5] = 1
    # board[6][5] = 1

    return board


def show_life(board):
    row, col = board.shape
    # for i in range(row):
    #     print(i, end="")
    # print("")
    for i in range(row):
        for j in range(col):
            if board[i][j] == 1:
                print("@", end="")
            else:
                print("+", end="")
        print(i)
    time.sleep(1)
    print("\033[11F")


def count_around(board, i, j):
    count = (
        board[i][j + 1]
        + board[i][j - 1]
        + board[i + 1][j]
        + board[i - 1][j]
        + board[i + 1][j + 1]
        + board[i + 1][j - 1]
        + board[i - 1][j + 1]
        + board[i - 1][j - 1]
    )
    # print("count is {}".format(count))
    return count


def next_age(board):
    row, col = board.shape
    next_board = np.zeros((row, col), dtype=int)
    for i in range(1, row - 1):
        for j in range(1, col - 1):
            around = count_around(board, i, j)
            # 誕生：死んでいるセルの隣接セルが生==3の時
            """
            if (
                board[i][j] == 0
                and board[i][j + 1]
                + board[i][j - 1]
                + board[i + 1][j]
                + board[i - 1][j]
                + board[i + 1][j + 1]
                + board[i + 1][j - 1]
                + board[i - 1][j + 1]
                + board[i - 1][j - 1]
                == 3
            ):
            """
            if board[i][j] == 0:
                if around == 3:
                    next_board[i][j] = 1
            else:
                if around >= 4:
                    next_board[i][j] = 0
                elif around <= 1:
                    next_board[i][j] = 0
                else:
                    next_board[i][j] = 1
    # print(next_board)
    return next_board


# board = np.empty((ROW,COL))
# board = np.zeros((ROW,COL), dtype=bool)
# print(board[0][0])
# board[0][0] = True
# board[1][0] = True
board = init_board()
# show_life(board)
# board = next_age(board)
# show_life(board)
# board = next_age(board)
# show_life(board)
for i in range(20):
    board = next_age(board)
    show_life(board)
# print(board.shape)
# print(board)
