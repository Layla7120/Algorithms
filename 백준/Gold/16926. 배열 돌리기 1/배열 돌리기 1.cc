#include <iostream>

using namespace std;

int board[1001][1001];
int N, M, R;

void rotate(){
  int cnt = min(N, M) / 2;

  for(int i=0; i<cnt; i++){
    int top = i;
    int left = i;
    int bottom = N - 1 - i;
    int right = M - 1 - i;

    int temp = board[i][i];
    
    // 상단 <-
    for(int j=left; j < right; j++){
      board[top][j] = board[top][j+1];
    }

    // 우측 위로
    for(int j=top; j<bottom; j++){
      board[j][right] = board[j+1][right];
    }

    //하단 ->
    for(int j=right; j>left; j--){
      board[bottom][j] = board[bottom][j-1];
    }

    //좌측 아래로
    for(int j=bottom; j>top; j--){
      board[j][left] = board[j-1][left];
    }

    board[top+1][left] = temp;
  }
}

int main(){
  ios::sync_with_stdio(false);
  cin.tie(NULL);

  cin >> N >> M >> R;
  
  for(int i = 0; i < N; i++){
    for(int j = 0; j < M; j++){
      cin >> board[i][j];
    }
  }

  for(int r=0; r<R; r++){
    rotate();
  }

  for(int i = 0; i < N; i++){
    for(int j = 0; j < M; j++){
      cout << board[i][j] << (j == M-1 ? "" : " ");
    }
    cout << "\n";
  }

  return 0;
}

