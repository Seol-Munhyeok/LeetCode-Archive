class Solution {

    int M, N;
    int[][] heights;
    boolean[][] pVisited, aVisited;
    final int[] dy = {-1, 0, 1, 0};
    final int[] dx = {0, 1, 0, -1};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // 변수 초기화
        M = heights.length;
        N = heights[0].length;
        this.heights = new int[M][N];
        for (int i = 0; i < M; i++) {
            this.heights[i] = heights[i].clone();
        }

        // Pacific Ocean BFS
        pVisited = new boolean[M][N];
        for (int x = 0; x < N; x++) {
            if (pVisited[0][x]) continue;
            bfs(0, x, pVisited);
        }
        for (int y = 0; y < M; y++) {
            if (pVisited[y][0]) continue;
            bfs(y, 0, pVisited);
        }

        // Atlantic Ocean BFS
        aVisited = new boolean[M][N];
        for (int x = 0; x < N; x++) {
            if (aVisited[M - 1][x]) continue;
            bfs(M - 1, x, aVisited);
        }
        for (int y = 0; y < M; y++) {
            if (aVisited[y][N - 1]) continue;
            bfs(y, N - 1, aVisited);
        }
        
        // 정답 구하기
        List<List<Integer>> result = new ArrayList<>();
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (pVisited[y][x] && aVisited[y][x]) {
                    result.add(Arrays.asList(y, x));
                }
            }
        }

        return result;
    }

    void bfs(int y, int x, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {y, x});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0], cx = cur[1];

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];
                
                if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
                if (visited[ny][nx]) continue;
               
                if (heights[ny][nx] >= heights[cy][cx]) {
                    q.offer(new int[] {ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
    }
}