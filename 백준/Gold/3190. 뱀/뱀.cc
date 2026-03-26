using namespace std;

#include <iostream>
#include <string>
#include <queue>
#include <deque>

int N, K, L, X;
string C;

int board[1001][1001];
int dr[] = {0, 1, 0, -1};
int dc[] = {1, 0, -1, 0};

bool check(int r, int c){
  return r > 0 && r <= N && c > 0 && c <= N && board[r][c] != 2; 
}

int rotate(string dir_str, int dir){
  if(dir_str == "L")
    return (dir + 3) % 4;
  return (dir + 1) % 4;
}


int main(){
  ios::sync_with_stdio(false);
  cin.tie(NULL);

  cin >> N >> K;

  for(int i=0; i<K; i++){
    int r, c;
    cin >> r >> c;

    board[r][c] = 1;
  }

  cin >> L;
  queue<pair<int, string>> q;
  for(int i=0; i<L; i++){
    int X; 
    string C;
    cin >> X >> C;

    q.push({X, C});
  }

  board[1][1] = 2;
  deque<pair<int, int>> snake;
  snake.push_back({1, 1});

  int time = 0; int dir = 0;
  int r = 1, c = 1;

  while (true){
    time++;
    int nr = r + dr[dir];
    int nc = c + dc[dir];

    if(!check(nr, nc)) break;
    
    if(board[nr][nc] == 1){
      board[nr][nc] = 2;
      snake.push_front({nr, nc});
    } else {
      board[nr][nc] = 2;
      snake.push_front({nr, nc});
      pair<int, int> end = snake.back();
      board[end.first][end.second] = 0;
      snake.pop_back();
    }

    r = nr; c = nc;

    if (!q.empty() && q.front().first == time){
      pair<int, string> curr = q.front();
      dir = rotate(curr.second, dir);
      q.pop();
    }

  }

  cout << time << "\n";
  return 0;
}