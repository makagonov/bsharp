import java.util.*;

public class SpaceWarDiv2 {
  class Enemy implements Comparable<Enemy> {
    int strength;
    int count;
    
    public Enemy(int s, int c) {
      this.strength = s;
      this.count = c;
    }
    
    @Override
    public int compareTo(Enemy o) {
      if (this.strength < o.strength)
        return -1;
      if (this.strength > o.strength)
        return 1;
      return count - o.count;
    }
  }
  public int minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength, int[] enemyCount) {
    int n = magicalGirlStrength.length, m = enemyCount.length;
    int maxPossibleFatigue = 0;
    Enemy [] enemies = new Enemy[m];
    for (int i = 0; i < m; i++) {
      enemies[i] = new Enemy(enemyStrength[i], enemyCount[i]);
      maxPossibleFatigue += enemyCount[i]; 
    }
    Arrays.sort(enemies);
    Arrays.sort(magicalGirlStrength);
    int lo = 0, hi = maxPossibleFatigue + 1, mid = 1;
    boolean canBeat = false;
    while (lo < hi) {
      mid = (lo + hi) / 2;
      if (canBeatAllEnemies(magicalGirlStrength, createEnemiesCopy(enemies), mid)) {
        canBeat = true;
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    if (!canBeat) return -1;
    return hi;
  }
  
  Enemy [] createEnemiesCopy(Enemy [] enemies) {
    Enemy [] result = new Enemy[enemies.length];
    for (int i = 0; i < enemies.length; i++)
      result[i] = new Enemy(enemies[i].strength, enemies[i].count);
    return result;
  }
  
  boolean canBeatAllEnemies(int [] girls, Enemy [] enemies, int maxF) {
    int curGirl = girls.length - 1;
    int curEnemy = enemies.length - 1;
    int [] girlsF = new int[girls.length];
    while(true) {
      if (curEnemy < 0) return true;
      if (curGirl < 0) return false;
      if (enemies[curEnemy].count > 0 && girls[curGirl] < enemies[curEnemy].strength)
        return false;
      
      int fatigueLeft = maxF - girlsF[curGirl];
      
      if (enemies[curEnemy].count <= fatigueLeft) {
        girlsF[curGirl] += enemies[curEnemy].count; 
        enemies[curEnemy].count = 0;
        curEnemy--;
      } else {
        enemies[curEnemy].count -= fatigueLeft;
        girlsF[curGirl] = 0;
        curGirl--;
      }
    }
  }

}
