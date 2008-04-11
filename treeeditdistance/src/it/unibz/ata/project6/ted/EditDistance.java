package it.unibz.ata.project6.ted;

public class EditDistance {
    
    public static int ed(String s1, String s2){
       
        if( s1 == null || s2 == null ){
            throw new IllegalArgumentException("String shouldn't be null!");
        }
       
        int n = s1.length();
        int m = s2.length();
       
        if ( n == 0 ){
            return m;
        }else if( m == 0 ){
                return n;
        }
        int p[] = new int[n+1]; //'previous' cost array, horizontally
        int d[] = new int[n+1]; // cost array, horizontally
        int t[]; //placeholder to assist in swapping p and d
        // indexes into strings s and t
          int i; // iterates through s
          int j; // iterates through t

          char s2_j; // jth character of t

          int cost; // cost

          for (i = 0; i<=n; i++) {
             p[i] = i;
          }
               
          for (j = 1; j<=m; j++) {
             s2_j = s2.charAt(j-1);
             d[0] = j;
               
             for (i=1; i<=n; i++) {
                cost = s1.charAt(i-1)==s2_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost               
                d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost); 
             }
             // copy current distance counts to 'previous row' distance counts
             t = p;
             p = d;
             d = t;
          }
               
          // our last action in the above loop was to switch d and p, so p now
          // actually has the most recent cost counts
          return p[n];
    }
   
    public static void main( String args[] ){
        String s1 = "hello";
        String s2 = "hallo";
        int res = 0;
        res = ed( s1, s2 );
        if( res != 0 ){
            System.out.println("the ed is: " + res);
        }
    }

}
  